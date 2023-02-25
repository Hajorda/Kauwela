package org.example.Commands;

import com.sedmelluq.discord.lavaplayer.track.AudioReference;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.sedmelluq.discord.lavaplayer.track.info.AudioTrackInfoBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.ChatGPT.ChatGPT;
import org.example.MusicPlayer.GuildMusicManager;
import org.example.MusicPlayer.PlayerManager;
import org.example.MusicPlayer.TrackScheduler;
import org.example.RandomCat.RandomCat;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CommandManager extends ListenerAdapter{
    private List<EmbedBuilder> embedBuilders;
    private Random random;
    private int randomNumber;
    private Dotenv env;
    private String botID;
    public static TextChannel textChannel;

    public CommandManager() {
        env= Dotenv.configure().load();
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

            if(!event.getMember().getVoiceState().inAudioChannel()){

                event.reply("İlk önce ses kanalına girmelisin" ).queue();
            }else {
                AudioManager audioManager = event.getGuild().getAudioManager();
                VoiceChannel memberchanne = event.getMember().getVoiceState().getChannel().asVoiceChannel();
                audioManager.openAudioConnection(memberchanne);

                PlayerManager.getInstance().loadAndPlay(event.getChannel().asTextChannel(),event.getOptions().get(0).getAsString());

                textChannel = event.getChannel().asTextChannel();
                event.reply(event.getMember().getAsMention()+"tarafından listeye şarkı eklendi.").queue();





            }

        } else if (command.equals("pause")) {

            GuildMusicManager musicManager =PlayerManager.getInstance().getMusicManager(event.getGuild());
            musicManager.scheduler.player.stopTrack();

            event.reply("Müzik durduruldu").queue();


        }
        else if(command.equals("leave")){
            if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()){
                event.reply("Ses kanalında değilim la").queue();

            }
            else {

                GuildMusicManager musicManager =PlayerManager.getInstance().getMusicManager(event.getGuild());
                AudioManager audioManager = event.getGuild().getAudioManager();
                musicManager.scheduler.player.stopTrack();
                musicManager.scheduler.queue.clear();
                audioManager.closeAudioConnection();

                event.reply("Hoççağalın ğidiyom ben").queue();
            }


        }
        else if (command.equals("resume")){
            GuildMusicManager musicManager =PlayerManager.getInstance().getMusicManager(event.getGuild());


        }else if (command.equals("skip")) {

        }
        else if(command.equals(("credits"))){

            EmbedBuilder creditEmbed = new EmbedBuilder()
                    .setAuthor("Credits")
                    .setColor(Color.YELLOW)
                    .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setFooter("Kauwela Bot","https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .addField("Developers","Hajorda#6261 , Cicikuş#0309",false)
                    .addField("Contact","You can contact us for any feedback with using Discord" ,false)
                    .addField("","For additional information you can use the buttons." ,false);

                event.replyEmbeds(creditEmbed.build()).addActionRow(Button.link("https://github.com/Hajorda", "Hajorda's GitHub"),
                        Button.link("https://github.com/Cicikuss", "Cicikus's GitHub")).queue();
        }
        else if (command.equals("userinfo")) {
            event.reply("Geliştirilme Aşamasında ZAA").queue();

        }
        else if (command.equals("serverinfo")) {
            EmbedBuilder serverEmbed = new EmbedBuilder()
                    .setAuthor(event.getGuild().getName(),event.getGuild().getIconUrl())
                    .setColor(Color.YELLOW)
                    .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setFooter("Kauwela Bot","https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .addField("Server Name",event.getGuild().getName(),true)
                    .addField("Server ID","" ,true)
                    .addField("Owner","" ,true)
                    .addField("Creation Date","" ,true)
                    .addField("Members","" ,true)
                    .addField("Other","" ,true);

            event.replyEmbeds(serverEmbed.build()).queue();
        }
        else if (command.equals("support")) {
            event.reply("Click the button for **Offical Kauwela Bot Support Server**").addActionRow(Button.link("https://discord.gg/jXpT9rtHMN","Invite")).queue();
        }
        else if (command.equals("invite")) {
            event.reply("Click the button for Invite Kauwela Bot to your server").addActionRow(Button.link("https://ptb.discord.com/api/oauth2/authorize?client_id=984469828008026192&permissions=8&scope=bot%20applications.commands","Invite")).queue();

        }
        else if (command.equals("help")) {
            EmbedBuilder embedHelp = new EmbedBuilder()
                    .setAuthor("Kauwela Bot","https://ptb.discord.com/api/oauth2/authorize?client_id=984469828008026192&permissions=8&scope=bot%20applications.commands")
                    .setColor(Color.YELLOW)
                    .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setFooter("Kauwela Bot","https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                    .setDescription("Kauwela Bot is a multipurpose disocrd managment and fun bot. It's writen in Java using JDA and  it's curently developing")
                    .addField(" \uD83D\uDCC1 Core Commands","" +
                            "`/help`  For command list \n" +
                            "`/invite`  Bot's imvite link \n" +
                            "`/support`  Bot's support server invite link\n" +
                            "`/credits`  People behind the Bot\n" +
                            "`/uptime`  How long the bot has been running",false)
                    .addField("\uD83C\uDFB5 Music Commands","`/play`  Playing music\n" +
                                    "`/skip`  Skip the music\n" +
                            "`/pause`  Pausing the music\n" +
                            "`/leave`  Bot leaves the channel",true)
                    .addField("Info Commands","`/userinfo`  User's info\n" +
                            "`/serverinfo` Server's info",true)
                    .addField("Fun Commands","`/8top` Ask Yes No questions",true)
                    .addField("","Also When you right click a user from the apps menu you can get the profile image",true);



            event.replyEmbeds(embedHelp.build()).queue();

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
        public void onGuildReady (GuildReadyEvent event){
            List<CommandData> commandData = new ArrayList<CommandData>();
            commandData.add(Commands.slash("play", "Hardalı dinle çok iyi grup").addOption(OptionType.STRING, "music", "Gece Vakti'ini açmazsan darılırım", true));
            commandData.add(Commands.slash("uptime", "Bakalım köle bot ne kadardır çalışıyor"));
            commandData.add(Commands.slash("leave", "sg buradan bot"));
            commandData.add(Commands.slash("pause", "Şarkıyı durdurur."));
            commandData.add(Commands.slash("credits", "Kredi skorunu gösterir"));
            commandData.add(Commands.slash("8top", "Anneni sor").addOption(OptionType.STRING, "soru", "Sorunu sor bakem", true));
            commandData.add(Commands.slash("userinfo", "Kullanıcının bilgilerine bak").addOption(OptionType.STRING, "Kullanıcı", "Kullanıcın bilgilerini gösterir", true));
            commandData.add(Commands.slash("serverinfo", "Sunucunun bilgilerine bak"));
            commandData.add(Commands.slash("invite", "İnviting for Kauwela Bot"));
            commandData.add(Commands.slash("support", "Kauwela Bot support server"));
            commandData.add(Commands.slash("help", "Command list"));
            commandData.add(Commands.context(Command.Type.USER, "Get user avatar"));
            commandData.add(Commands.message("Count words"));
           // commandData.add(Commands.slash("ask", "ChatGPT2  ile flörtme şansı").addOption(OptionType.STRING, "soru", "Anneni sor", true));
            commandData.add(Commands.slash("kedy", "Günlük kedy dozunu karşılar"));
            event.getGuild().updateCommands().addCommands(commandData).queue();
        }



    }


