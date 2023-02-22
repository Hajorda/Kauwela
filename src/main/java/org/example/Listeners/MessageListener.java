package org.example.Listeners;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class MessageListener extends ListenerAdapter {
    private Dotenv dot;
    private String prefix;

    public MessageListener(Dotenv dot) {
        this.dot = dot;
        prefix = dot.get("PREFIX");
    }

    private int totalMember(MessageReceivedEvent event){
        int sum = 0;

        for (Guild g : event.getJDA().getGuilds()){
            sum += g.getMemberCount();
        }
        return sum;
    }

    private int totalServer(MessageReceivedEvent event){
        int sum = 0;

        for (Guild g : event.getJDA().getGuilds()){
            sum += 1;
        }
        return sum;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.equals(prefix+"ping")){
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
        else if(message.equals(prefix+"guncelle")){

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

    }



    }

