package org.example.Commands;


import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;

import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.ChatGPT.ChatGPT;
import org.example.ChatGPT.DallE;
import org.example.Listeners.MessageListener;
import org.example.MusicPlayer.GuildMusicManager;
import org.example.MusicPlayer.PlayerManager;

import org.example.RSS.RssReader;
import org.example.RandomCat.RandomCat;
import org.example.RandomCat.RandomCuteKedy;
import org.example.RandomWaifu.RandomMeme;
import org.example.RandomWaifu.RandomWaifu;
import org.jetbrains.annotations.NotNull;


import java.awt.*;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CommandManager extends ListenerAdapter {
    private final RandomCuteKedy randomCuteKedy = new RandomCuteKedy();
    private final List<EmbedBuilder> embedBuilders;
    private final Random random;
    private int randomNumber;
    private final Dotenv env;
    private final String botID;
    public static TextChannel textChannel;
    public static String url;

    public CommandManager() {
        env = Dotenv.configure().load();
        botID = env.get("BOTID");
        embedBuilders = new ArrayList<>();
        random = new Random();
        embedBuilders.add(new EmbedBuilder().setDescription("Evet"));
        embedBuilders.add(new EmbedBuilder().setDescription("Hayır"));
        embedBuilders.add(new EmbedBuilder().setDescription("Belki"));
        embedBuilders.add(new EmbedBuilder().setDescription("Kesin bir şey diyemem"));
        embedBuilders.add(new EmbedBuilder().setDescription("Asla Enayi"));
        embedBuilders.add(new EmbedBuilder().setDescription("İnan bilmiyorum").setImage("https://galeri14.uludagsozluk.com/844/inan-bilmiyorum_2286860.jpg"));
    }

    private String thumbnail(String url)  {
        if (url.contains("youtube")){


            return   "http://img.youtube.com/vi/"+ url.substring(url.indexOf("v=")+2)+"/0.jpg";
        }
        else if(url.contains("youtube") && url.contains("&index")){
            return   "http://img.youtube.com/vi/"+ url.substring(url.indexOf("v=")+2,url.indexOf("&index"))+"/0.jpg";
        }
        return null;
    }

    private long[] calculateTime(long time){
        long[] duration = new long[4];
        duration[0]= time / (1000 * 60 * 60);
        time = time % (1000 * 60 * 60);
        duration[1]= time / (1000 * 60);
        time = time % (1000 * 60);
        duration[2]= time / 1000;
        time = time % 1000;
        duration[3] = time;
        return  duration;


    }
//ASASADASDSD

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("8top")) {

            randomNumber = random.nextInt(0, embedBuilders.size());

            EmbedBuilder soru = new EmbedBuilder().setDescription(event.getOptions().get(0).getAsString()).setTitle("Soru");
            event.replyEmbeds(soru.build(), embedBuilders.get(randomNumber).setColor(Color.red).build()).queue();

        } else if (command.equals("kedy")) {

            RandomCat kedy = new RandomCat();
            System.out.println(kedy.getFact());
            EmbedBuilder randomkedi = new EmbedBuilder()
                    .setDescription("**Fact: **" + kedy.getFact())
                    .setImage(kedy.getImageURL());
            System.out.println(kedy.getImageURL());
            event.replyEmbeds(randomkedi.build()).queue();
            //System.out.println(event.getJDA().getInviteUrl(Permission.ADMINISTRATOR));

        } else if (command.equals("uptime")) {
            RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
            long uptime = rb.getUptime();
            System.out.println(uptime);
            long day = uptime / (1000 * 60 * 60 * 24);
            uptime = uptime % (1000 * 60 * 60 * 24);
            long hour = uptime / (1000 * 60 * 60);
            uptime = uptime % (1000 * 60 * 60);
            long minute = uptime / (1000 * 60);
            uptime = uptime % (1000 * 60);
            long second = uptime / 1000;
            uptime = uptime % 1000;
            EmbedBuilder embed = new EmbedBuilder()
                    .setColor(new Color(204, 255, 255))
                    .setTitle("Botun çalışma süresi")
                    .addField("Day", "" + day, true)
                    .addField("Hour", "" + hour, true)
                    .addField("Minute", "" + minute, true)
                    .addField("Second", "" + second, true)
                    .addField("Milliseconds", "" + uptime, true)
                    .setFooter("KauwelaBot", event.getJDA().getSelfUser().getAvatarUrl());

            event.replyEmbeds(embed.build()).queue();


        } else if (command.equals("play")) {

            if (!event.getMember().getVoiceState().inAudioChannel()) {

                event.reply("İlk önce ses kanalına girmelisin").queue();
            } else {
                AudioManager audioManager = event.getGuild().getAudioManager();
                VoiceChannel memberchanne = event.getMember().getVoiceState().getChannel().asVoiceChannel();
                audioManager.openAudioConnection(memberchanne);

                PlayerManager.getInstance().loadAndPlay(event.getChannel().asTextChannel(), event.getOptions().get(0).getAsString());

                textChannel = event.getChannel().asTextChannel();
                event.reply(event.getMember().getAsMention() + "tarafından listeye şarkı eklendi.").queue();


            }

        } else if (command.equals("pause")) {

            GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
                event.reply("Ses kanalında değilim la").queue();

            } else if (!event.getMember().getVoiceState().inAudioChannel()) {
                event.reply("Kardeşşşş ses kanalında değilsin").queue();
            } else {
                musicManager.scheduler.player.setPaused(true);

                event.reply("Müzik durduruldu").queue();
            }


        } else if (command.equals("leave")) {
            if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
                event.reply("Ses kanalında değilim la").queue();

            } else if (!event.getMember().getVoiceState().inAudioChannel()) {
                event.reply("Kardeşşşş ses kanalında değilsin").queue();
            } else {

                GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                AudioManager audioManager = event.getGuild().getAudioManager();
                musicManager.scheduler.player.stopTrack();
                musicManager.scheduler.queue.clear();
                audioManager.closeAudioConnection();

                event.reply("Hoççağalın ğidiyom ben").queue();
            }


        } else if (command.equals("resume")) {
            GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            musicManager.scheduler.player.setPaused(false);
            event.reply("Müzik başlatıldı.").queue();

        } else if (command.equals("skip")) {
            GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            AudioPlayer audioPlayer = musicManager.audioPlayer;
            if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
                event.reply("Ses kanalında değilim la").queue();

            } else if (audioPlayer.getPlayingTrack() == null) {
                event.replyEmbeds(new EmbedBuilder().setDescription("Şuan herhangi bir şarkı çalmıyor").build()).queue();
            } else if (!event.getMember().getVoiceState().inAudioChannel()) {
                event.reply("Kardeşşşş ses kanalında değilsin").queue();
            } else {
                musicManager.scheduler.nextTrack();
                event.reply("Sıradaki şarkıya geçildi.").queue();

            }

        } else if (command.equals(("credits"))) {

            EmbedBuilder creditEmbed = new EmbedBuilder()
                    .setAuthor("Credits")
                    .setColor(Color.YELLOW)
                    .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .addField("Developers", "Hajorda#6261 , Cicikuş#0309", false)
                    .addField("Contact", "You can contact us for any feedback with using Discord", false)
                    .addField("", "For additional information you can use the buttons.", false);

            event.replyEmbeds(creditEmbed.build()).addActionRow(Button.link("https://github.com/Hajorda", "Hajorda's GitHub"),
                    Button.link("https://github.com/Cicikuss", "Cicikus's GitHub")).queue();
        } else if (command.equals("clear")) {

            System.out.println("CLEAR KOMUDUU!!!");
            int sayi = event.getOptions().get(0).getAsInt();
            if (sayi < 100) {
                event.getChannel().asTextChannel().getIterableHistory().takeAsync(sayi + 1).thenAccept(event.getChannel()::purgeMessages);

                event.getChannel().sendMessage(sayi + " kadar mesaj silindi!").queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            } else
                event.reply("100 den fazla mesaj silemezsin!").queue(m -> m.deleteOriginal().queueAfter(5, TimeUnit.SECONDS));

        } else if (command.equals("serverinfo")) {
            EmbedBuilder serverEmbed = new EmbedBuilder()
                    .setAuthor(event.getGuild().getName(), event.getGuild().getIconUrl())
                    .setColor(Color.YELLOW)
                    .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .addField("Server Name", event.getGuild().getName(), true)
                    .addField("Server ID", "", true)
                    .addField("Owner", "", true)
                    .addField("Creation Date", "", true)
                    .addField("Members", "", true)
                    .addField("Other", "", true);

            event.replyEmbeds(serverEmbed.build()).queue();
        } else if (command.equals("support")) {
            event.reply("Click the button for **Offical Kauwela Bot Support Server**").addActionRow(Button.link("https://discord.gg/jXpT9rtHMN", "Invite")).queue();
        } else if (command.equals("invite")) {
            event.reply("Click the button for Invite Kauwela Bot to your server").addActionRow(Button.link("https://ptb.discord.com/api/oauth2/authorize?client_id=984469828008026192&permissions=8&scope=bot%20applications.commands", "Invite")).queue();

        } else if (command.equals("help")) {
            EmbedBuilder embedHelp = new EmbedBuilder()
                    .setAuthor("Kauwela Bot", "https://ptb.discord.com/api/oauth2/authorize?client_id=984469828008026192&permissions=8&scope=bot%20applications.commands")
                    .setColor(Color.YELLOW)
                    .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setDescription("Kauwela Bot is a multipurpose disocrd managment and fun bot. It's writen in Java using JDA and  it's curently developing")
                    .addField(" \uD83D\uDCC1 Core Commands", """
                            `/help`  For command list\s
                            `/invite`  Bot's imvite link\s
                            `/support`  Bot's support server invite link
                            `/credits`  People behind the Bot
                            `/uptime`  How long the bot has been running
                            `/status`  Stats of Bot""", false)
                    .addField("\uD83C\uDFB5 Music Commands", """
                            `/play`  Playing music
                            `/skip`  Skip the music
                            `/pause`  Pausing the music
                            `/leave`  Bot leaves the channel
                            `/nowplaying`  Shows the current song
                            `/queue`  Shows the queuueueueu""", true)
                    .addField("ℹ️ Info Commands", """
                            `/userinfo`  User's info
                            `/serverinfo`  Server's info
                            `/clear`  Cleans the chat
                            `/feedback`  Any feedback for Bot""", true)
                    .addField("\uD83C\uDF88 Fun Commands", """
                            `/8top` Ask Yes No questions
                            `/kedy`  Random cat photos and fun facts
                            `/soundboard`  Generates the soundboard
                            `/randomgpt`  Chat with GPT3 but random settings
                            `/rimage`  Generates images with Dale2
                            `/randommeme`  Gives you memes
                            `/trmeme`  Gives you turkish memes
                            `/waifu`  Gives you a random waifu picture
                            `/cutekedy`  Gives you cute kedys
                            `/kedysearch`  Biggest kedy searcing engine system in discord""", true)
                    .addField("", "Also When you right click a user from menu -> apps, you can get the users profile image`\n" +
                            "\uD83E\uDD16 Ayrıca botu etiketleyip bot ile GPT3 kullanarak sohbet edebilirsin.", true);


            event.replyEmbeds(embedHelp.build()).addActionRow(Button.link("https://ptb.discord.com/api/oauth2/authorize?client_id=984469828008026192&permissions=8&scope=bot%20applications.commands", "Invite"),
                    Button.link("https://discord.gg/jXpT9rtHMN", "Support Server")).queue();

        } else if (command.equals("feedback")) {
            if (event.getName().equals("feedback")) {
                TextInput subject = TextInput.create("subject", "Subject", TextInputStyle.SHORT)
                        .setPlaceholder("Subject of this feedback")
                        .setMinLength(10)
                        .setMaxLength(100) // or setRequiredRange(10, 100)
                        .build();

                TextInput body = TextInput.create("body", "Body", TextInputStyle.PARAGRAPH)
                        .setPlaceholder("Your feedback goes here")
                        .setMinLength(30)
                        .setMaxLength(1000)
                        .build();

                Modal modal = Modal.create("feedback", "Feedback")
                        .addActionRows(ActionRow.of(subject), ActionRow.of(body))
                        .build();

                event.replyModal(modal).queue();
            }

        } else if (command.equals("soundboard")) {

            EmbedBuilder soundEmbed = new EmbedBuilder()
                    .setAuthor("SoundBoard")
                    .setDescription("Emojilere tıkla bacım")
                    .setColor(Color.YELLOW)
                    .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .addField("Sounds", "`HihihiHa` \uD83E\uDD2A", true);


            event.reply("Soundboard Aktif").queue();
            appCommands.TextChannel = event.getChannel().asTextChannel().getId();
            event.getChannel().sendMessageEmbeds(soundEmbed.build()).queue(message -> {
                message.addReaction(Emoji.fromUnicode("U+1F601")).queue();
                message.addReaction(Emoji.fromUnicode("U+1F910")).queue();
                message.addReaction(Emoji.fromUnicode("U+1F922")).queue();
            });
        } else if (command.equals("randomgpt")) {
            System.out.println("Çalıştı");
            String prompt = event.getOptions().get(0).getAsString();
            event.deferReply().queue();
            try {
                String response = ChatGPT.chatgptRandom(prompt);

                event.getHook().sendMessage(response.substring(0, response.lastIndexOf("index") - 3)).queue();
            } catch (Exception e) {
                event.getHook().sendMessage("API de sıkıntı çıktı").queue();
            }
        } else if (command.equals("status")) {
            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("KauwelaBot'un Durumu", null);
            eb.setColor(new Color(0x59E010));
            eb.setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png");

            eb.setDescription("");
            eb.addField("Ping", "" + event.getJDA().getGatewayPing() + "ms", true);
            eb.addField("Kullanıcı Sayısı", "" + MessageListener.totalMember(event), true);
            eb.addField("Sunucu Sayısı", "" + MessageListener.totalServer(event), true);

            eb.addField("Bot Durumu", "Aktif", true);
            eb.addField("Sunucu Shard", "" + event.getJDA().getShardInfo().getShardString(), true);


            eb.setFooter("KauwelaBot", event.getJDA().getSelfUser().getAvatarUrl());

            //event.getChannel().sendMessageFormat(eb.build()).queue();
            event.replyEmbeds(eb.build()).queue();
        } else if (command.equals("rimage")) {

            String prompt = event.getOptions().get(0).getAsString();
            event.deferReply().queue();


            try {
                url = DallE.ImageCreaterRandomizer(prompt);

                event.getHook().sendMessageEmbeds(new EmbedBuilder().setImage(url).setFooter(event.getUser().getName(), event.getUser().getAvatarUrl()).setAuthor(prompt).setColor(Color.magenta).build()).addActionRow(Button.primary("rimage_save", "Save")).queue();
            } catch (Exception e) {
                event.getHook().sendMessage("API de sıkıntı çıktı").queue();
            }

        } else if (command.equals("waifu")) {
            event.replyEmbeds(new EmbedBuilder().setImage(new RandomWaifu("sfw", "waifu").getImageUrl()).build()).queue();
        } else if (command.equals("hug")) {
            if (event.getOptions().size() != 0) {
                event.reply(event.getUser().getAsMention() + " " + event.getOptions().get(0).getAsUser().getAsMention() + " kişisine sarılıyor").queue();
                event.getChannel().sendMessageEmbeds(new EmbedBuilder()
                        .setImage(new RandomWaifu("sfw", "hug").getImageUrl())
                        .build()).queue();
            } else {
                event.replyEmbeds(new EmbedBuilder().setImage(new RandomWaifu("sfw", "hug").getImageUrl()).build()).queue();
            }
        } else if (command.equals("cutekedy")) {
            RandomCat kedy = new RandomCat();
            try {
                RandomCuteKedy cat = new RandomCuteKedy();
                EmbedBuilder randomkedi = new EmbedBuilder()
                        .setDescription("**Fact: **" + kedy.getFact())
                        .setImage(cat.getImage("cute"));
                // System.out.println(url);
                event.replyEmbeds(randomkedi.build()).queue();

            } catch (IOException e) {
                event.replyEmbeds(new EmbedBuilder().setDescription("API de sıkıntı var ").build()).queue();
                throw new RuntimeException(e);
            }


        } else if (command.equals("kedysearch")) {
            try {
                String prompt = event.getOptions().get(0).getAsString();
                RandomCuteKedy cat = new RandomCuteKedy();
                EmbedBuilder randomkedi = new EmbedBuilder()
                        .setDescription(prompt)
                        .setImage(cat.getImage(prompt));
                // System.out.println(url);
                event.replyEmbeds(randomkedi.build()).queue();

            } catch (IOException e) {
                event.replyEmbeds(new EmbedBuilder().setDescription("Bulunamadı").build()).queue();
                throw new RuntimeException(e);

            }

        } else if (command.equals("animerush")) {
            try {
                RssReader reader = new RssReader("https://www.animerush.tv/rss.xml");
               /* EmbedBuilder anime = new EmbedBuilder()
                        .setDescription(reader.getAnimeLink())
                        .setThumbnail("https/www.animerush.tv/anime-images-big/")
                        .setTitle(reader.getLatestAnime());*/

                event.reply(reader.getAnimeLink()).queue();

            } catch (Exception e) {
                event.reply("Hata baby").queue();
                throw new RuntimeException(e);
            }


        }else if (command.equals("nowplaying")){
            if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
                event.reply("Ses kanalında değilim la").queue();

            } else if (!event.getMember().getVoiceState().inAudioChannel()) {
                event.reply("Kardeşşşş ses kanalında değilsin").queue();
            }
            GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            AudioManager audioManager = event.getGuild().getAudioManager();
            AudioPlayer audioPlayer = musicManager.audioPlayer;
            AudioTrack track = audioPlayer.getPlayingTrack();
            if (track == null) {
                event.reply("Herhangi müzik çalmıyor").queue();

            }else {
                AudioTrackInfo info = track.getInfo();
                long[] times = calculateTime(info.length);
                EmbedBuilder builder = new EmbedBuilder()
                        .setAuthor(info.author)
                        .setDescription(info.title)
                        .addField("",times[0]+":"+times[1]+":"+times[2]+":"+times[3],false)
                        .setThumbnail(thumbnail(info.uri));
                event.replyEmbeds(builder.build()).queue();

            }

        }
        else if(command.equals("randommeme")){
            try {
                event.replyEmbeds(RandomMeme.randomMemeGenerate().build()).queue();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        else if(command.equals("trmeme")){
            try {
                event.replyEmbeds(RandomMeme.trmemeGenerator().build()).queue();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }



        /*else if(command.equals("ask")){
            String question = (ChatGPT.chatgpt(event.getOptions().get(0).getAsString()));
            System.out.println(question);
            String answer = ChatGPT.answer(question);
            System.out.println(answer);

            event.deferReply().delay(10,TimeUnit.SECONDS).queue();
            event.getHook().sendMessage(answer).queue();


        }*/

    }


  /* @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<CommandData>();
        commandData.add(Commands.slash("credits", "Kredi skorunu gösterir"));
        event.getJDA().updateCommands().addCommands(commandData).queue();

    }*/


    /*Test için ideal*/
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<CommandData>();

        commandData.add(Commands.slash("play", "Hardalı dinle çok iyi grup").addOption(OptionType.STRING, "music", "Gece Vakti'ini açmazsan darılırım", true));
        try {
            OptionData kedyOption = new OptionData(OptionType.STRING, "tag", "kedyler çok sevimli", true)

                    .addChoices(randomCuteKedy.getChoices(10));
            commandData.add(Commands.slash("kedysearch", "Kedy Arama Motoru").addOptions(kedyOption));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        commandData.add(Commands.slash("uptime", "Bakalım köle bot ne kadardır çalışıyor"));
        commandData.add(Commands.slash("leave", "sg buradan bot"));
        commandData.add(Commands.slash("resume", "Şarkıyı devam ettirir"));
        commandData.add(Commands.slash("pause", "Şarkıyı durdurur."));
        commandData.add(Commands.slash("credits", "Kredi skorunu gösterir"));
        commandData.add(Commands.slash("8top", "Anneni sor").addOption(OptionType.STRING, "soru", "Sorunu sor bakem", true));
        commandData.add(Commands.slash("randomgpt", "Anneni sor").addOption(OptionType.STRING, "soru", "Sorunu sor bakem", true));
        commandData.add(Commands.slash("rimage", "Resim yap enayi").addOption(OptionType.STRING, "metin", "Nasıl bir şey olsun bacım", true));
        commandData.add(Commands.slash("clear", "Sohbet kaydını temizler").addOption(OptionType.INTEGER, "mesajsayisi", "Temizleyeceğin mesaj sayisini gir", true));
        commandData.add(Commands.slash("serverinfo", "Sunucunun bilgilerine bak"));
        commandData.add(Commands.slash("invite", "İnviting for Kauwela Bot"));
        commandData.add(Commands.slash("support", "Kauwela Bot support server"));
        commandData.add(Commands.slash("soundboard", "SoundBoard"));
        commandData.add(Commands.slash("help", "Command list"));
        commandData.add(Commands.slash("feedback", "Feedback"));
        commandData.add(Commands.slash("animerush", "Son çıkan animeyi gösterir"));
        commandData.add(Commands.slash("skip", "Şarkıyı geçer"));
        commandData.add(Commands.slash("cutekedy", "Tatliş kediler gönderir."));
        commandData.add(Commands.slash("randommeme", "MEME"));
        commandData.add(Commands.slash("trmeme", "MEME BUT TR"));
        commandData.add(Commands.slash("status", "Status of Bot"));
        commandData.add(Commands.slash("hug", "Keşke bana da birileri sarılsa").addOption(OptionType.MENTIONABLE, "hedef", "Ona sıkıca sarılın", false));
        commandData.add(Commands.slash("waifu", "Keşke anime kızları gerçek olsa"));

        commandData.add(Commands.context(Command.Type.USER, "Get user avatar"));
        commandData.add(Commands.message("Count words"));
        // commandData.add(Commands.slash("ask", "ChatGPT2  ile flörtme şansı").addOption(OptionType.STRING, "soru", "Anneni sor", true));
        commandData.add(Commands.slash("kedy", "Günlük kedy dozunu karşılar"));
        commandData.add(Commands.slash("nowplaying", "Çalan şarkıyı gösterir."));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }


}


