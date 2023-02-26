package org.example.Listeners;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.FileUpload;
import org.example.ChatGPT.ChatGPT;
import org.example.ChatGPT.DallE;

import java.awt.*;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class MessageListener extends ListenerAdapter {
    private Dotenv dot;
    private String prefix;
    public static String url="";
    public MessageListener(Dotenv dot) {
        this.dot = dot;
        prefix = dot.get("PREFIX");
    }

    public static int totalMember(MessageReceivedEvent event){
        int sum = 0;

        for (Guild g : event.getJDA().getGuilds()){
            sum += g.getMemberCount();
        }
        return sum;
    }
    public static int totalMember(SlashCommandInteractionEvent event){
        int sum = 0;

        for (Guild g : event.getJDA().getGuilds()){
            sum += g.getMemberCount();
        }
        return sum;
    }

    public static int totalServer(MessageReceivedEvent event){
        int sum = 0;

        for (Guild g : event.getJDA().getGuilds()){
            sum += 1;
        }
        return sum;
    }
    public static int totalServer(SlashCommandInteractionEvent event){
        int sum = 0;

        for (Guild g : event.getJDA().getGuilds()){
            sum += 1;
        }
        return sum;
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();




        if (message.equals(prefix+"status")){
            event.getMessage().addReaction(Emoji.fromUnicode("U+2705")).queue();
            EmbedBuilder eb = new EmbedBuilder();

                eb.setTitle("KauwelaBot'un Durumu", null);
                eb.setColor(new Color(0x59E010));
                eb.setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png");

                eb.setDescription("");
                eb.addField("Ping",""+event.getJDA().getGatewayPing()+"ms",true);
                eb.addField("Kullanıcı Sayısı",""+totalMember(event),true);
                eb.addField("Sunucu Sayısı",""+totalServer(event),true);

                eb.addField("Bot Durumu","Aktif",true);
                eb.addField("Sunucu Shard",""+event.getJDA().getShardInfo().getShardString(),true);


                eb.setFooter("KauwelaBot",event.getJDA().getSelfUser().getAvatarUrl());

                //event.getChannel().sendMessageFormat(eb.build()).queue();
                event.getChannel().sendMessageEmbeds(eb.build()).queue();

        }
        if (message.equals(prefix+"hihihaha")){
           GuildMemberListener.hihihaha = !GuildMemberListener.hihihaha;
            event.getChannel().sendMessage("HihiHaha durumu: "+GuildMemberListener.hihihaha).queue();
        }
        else if(message.equals(prefix+"guncelle")){
            event.getMessage().addReaction(Emoji.fromUnicode("U+2705")).queue();
            try {

                int tm = totalMember(event);
                int ts = totalServer(event);

                VoiceChannel channel = event.getJDA().getGuildById("984469721455919174").getVoiceChannelById("1077996705938669688");
                channel.getManager()
                        .setName("⚖️ Total Users: " + tm)
                        .queue();
                VoiceChannel channel1 = event.getJDA().getGuildById("984469721455919174").getVoiceChannelById("1077996895789650081");
                channel1.getManager()
                        .setName("♀️Total Servers: " + ts)
                        .queue();

                event.getChannel().sendMessage(":disguised_face: Güncelledim la TM: "+tm+" TS: "+ts).queue();

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else if(message.equalsIgnoreCase(prefix+"kapat")){
            event.getMessage().addReaction(Emoji.fromUnicode("U+2705")).queue();
           String userId = event.getAuthor().getId();

           if(userId.equals("477144014655586315") || userId.equals("362965641357033472") || userId.equals("358698333751214080")) {
               String user = "Bilinmeyen";
               user = event.getAuthor().getName();

               String date = java.time.LocalTime.now() + "";

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

               EmbedBuilder endEmbed = new EmbedBuilder()
                       .setColor(new Color(231, 9, 9))
                       .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                       .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                       .setAuthor("Bot Kapatıldı!")
                       .addField("Bot " + user + " tarafından " + date.substring(0, 5) + " zamanında" + " kapatıldı.", "", false)
                       .addField("Toplam Çalışma Süresi:", day + " gün, " + hour + " saat, " + minute + " dakika, " + second + " saniye", false);

               event.getJDA().getGuildById("984469721455919174").getTextChannelById("1077722638643908679").sendMessageEmbeds(endEmbed.build()).queue();

               try {
                   Thread.sleep(3000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }

               event.getJDA().shutdown();



               System.exit(0);
           }
           else {
               event.getChannel().sendMessage("Bu komutu kullanmak için Yetkili olman lazım.").queue();
           }

        }
        else if (event.getMessage().getMentions().getUsers().size()> 0){
            if (event.getMessage().getMentions().getUsers().get(0).getId().equals("984469828008026192")){

                String prompt = message.replace("<@984469828008026192>","");
                event.getChannel().sendTyping();
                String answer =  ChatGPT.chatgpt(prompt);

                event.getChannel().sendMessage(answer).queue();
            }
        }
        else if(message.indexOf(prefix+"randomgpt") !=-1){
            event.getMessage().addReaction(Emoji.fromUnicode("U+2705")).queue();
            System.out.println("Çalıştı");
            String prompt = message.replace(prefix+"randomgpt","");
            event.getChannel().sendTyping().queue();
           try {
               String response = ChatGPT.chatgptRandom(prompt);
               event.getChannel().sendMessage(response.substring(0,response.lastIndexOf("index")-3)) .queue();
           }catch (Exception e){
               event.getChannel().sendMessage("API de sıkıntı çıktı").queue();
           }

        }else if(message.indexOf(prefix+"rimage") !=-1){
            event.getMessage().addReaction(Emoji.fromUnicode("U+2705")).queue();
            System.out.println("Çalıştı");
            String prompt = message.replace(prefix+"rimage","");
            event.getChannel().sendTyping().queue();
            try {
             url = DallE.ImageCreaterRandomizer(prompt);

             event.getChannel().sendMessageEmbeds(new EmbedBuilder().setImage(url).build()).addActionRow(Button.primary("rimage_save","Save")).queue();
            }catch (Exception e){
                event.getChannel().sendMessage("API de sıkıntı çıktı").queue();
            }


        }

    }



    }

