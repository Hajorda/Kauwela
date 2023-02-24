package org.example.Commands;

import com.sedmelluq.discord.lavaplayer.track.AudioReference;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.sedmelluq.discord.lavaplayer.track.info.AudioTrackInfoBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
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

        }else if(command.equals("ask")){
            String question = (ChatGPT.chatgpt(event.getOptions().get(0).getAsString()));
            //System.out.println(question);
            String answer = ChatGPT.answer(question);
            System.out.println(answer);

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(event.getMember().getUser().getName())
                    .addField("Soru",event.getOptions().get(0).getAsString(),false)
                            .addField("Cevap",answer,false)
                                    ;
            event.deferReply().addEmbeds(embedBuilder.build()).queue();
        }

    }


  /*  @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<CommandData>();
        commandData.add(Commands.slash("uptime","Bakalım köle bot ne kadardır çalışıyor"));
        commandData.add(Commands.slash("8top","Anneni sor").addOption(OptionType.STRING,"soru","Sorunu sor bakem",true));
         commandData.add(Commands.slash("kedy","Günlük kedy dozunu karşılar"));
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
            commandData.add(Commands.slash("8top", "Anneni sor").addOption(OptionType.STRING, "soru", "Sorunu sor bakem", true));
            commandData.add(Commands.slash("ask", "ChatGPT ile flörtme şansı").addOption(OptionType.STRING, "soru", "Anneni sor", true));
            commandData.add(Commands.slash("kedy", "Günlük kedy dozunu karşılar"));
            event.getGuild().updateCommands().addCommands(commandData).queue();


        }
    }


