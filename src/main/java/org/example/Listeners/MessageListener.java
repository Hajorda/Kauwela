package org.example.Listeners;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
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

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.equals(prefix+"ping")){
            EmbedBuilder eb = new EmbedBuilder();
            try{
                eb.setTitle("KauwelaBot'un Durumu", null);
                eb.setColor(new Color(0x59E010));
                eb.setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png");

                eb.setDescription("");
                eb.addField("Ping",""+event.getJDA().getGatewayPing()+"ms",true);
                eb.addField("Kullanıcı Sayısı",""+event.getGuild().getMemberCount(),true);

                eb.addField("Bot Durumu","Aktif",true);
                eb.addField("Sunucu Shard",""+event.getJDA().getShardInfo().getShardString(),true);

                eb.setFooter("KauwelaBot",event.getJDA().getSelfUser().getAvatarUrl());

                //event.getChannel().sendMessageFormat(eb.build()).queue();
                event.getChannel().sendMessageEmbeds(eb.build()).queue();}
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
