package org.example.Listeners;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.OffsetDateTime;

public class EventListeners extends ListenerAdapter {
    private final Dotenv dot;

    public EventListeners(Dotenv dot) {
        this.dot = dot;
    }


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String pre = dot.get("PREFIX");
        if(message.equalsIgnoreCase("Ali")){
            event.getChannel().sendMessageFormat("Enayi").queue();
        }else if (message.equalsIgnoreCase(pre +"annen")){
            event.getChannel().sendMessageFormat("Baban").queue();
        } else if (message.equalsIgnoreCase(pre+"ping")) {
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

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
       // super.onGuildMemberJoin(event);
        User user = event.getUser();
        String message = user.getAsTag() + " hoşgeldin bıdık yerim seni";
        System.out.println("çalışıyor");
        //event.getGuild().getDefaultChannel().asTextChannel().sendMessageFormat(message).queue();
        event.getGuild().getTextChannelById("984473410044305459").sendMessageEmbeds(new EmbedBuilder().setImage(user.getAvatarUrl()).setDescription(message).build()).queue();


    }
}


