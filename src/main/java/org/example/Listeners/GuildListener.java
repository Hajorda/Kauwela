package org.example.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GuildListener extends ListenerAdapter {


    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        System.out.println("Bot yeni bir sunucuya girdi!");


        //Yeni Sunucuya girdiğinde sunucuya göndereceği mesaj

        EmbedBuilder guildJoinEmbed = new EmbedBuilder();
        guildJoinEmbed.setColor(new Color(255,255,0));
        guildJoinEmbed.setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png");
        guildJoinEmbed.setFooter("Kauwela Bot");
        guildJoinEmbed.setAuthor("Merhaba Ben Kauwela Bot!");
        guildJoinEmbed.addField("Beni sunucuya davet ettiğin için teşekkürler.","",false);
        guildJoinEmbed.addField("Komutlarımı /yardım veya !!yardım yazarak görebilirsin.","",false);

        event.getGuild().getTextChannels().get(0).sendMessageEmbeds(guildJoinEmbed.build()).queue();


        //Yeni Sunucuya girdiğinde log kanalına gönderilecek mesaj

        EmbedBuilder guildJoinEmbedA = new EmbedBuilder();
        guildJoinEmbedA.setColor(new Color(10, 238, 13));
        guildJoinEmbedA.setThumbnail(event.getGuild().getIconUrl());
        guildJoinEmbedA.setFooter("Kauwela Bot","https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png");
        guildJoinEmbedA.setAuthor("Bot yeni bir sunucuya katıldı!");
        guildJoinEmbedA.addField("Sunucu Adı",event.getGuild().getName(),true);
        guildJoinEmbedA.addField("Sunucu Sahibi",event.getGuild().getOwner().getUser().getName(),true);
        guildJoinEmbedA.addField("Kişi Sayısı",event.getGuild().getMemberCount()+"",true);
        guildJoinEmbedA.addField("Oluşturulma Tarihi",event.getGuild().getTimeCreated()+"",true);
        guildJoinEmbedA.addField("İnvite Durumu.",event.getGuild().isInvitesDisabled()+"",true);
        guildJoinEmbedA.addField("Boost Tieri",event.getGuild().getBoostTier()+"",true);
        guildJoinEmbedA.addField("Sunucu ID",event.getGuild().getId()+"",true);

        event.getJDA().getGuildById("984469721455919174").getTextChannelById("1077707638596448276").sendMessageEmbeds(guildJoinEmbedA.build()).queue();
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {

        //Botun bir sunucudan ayrılacağında göndereceği mesaj

        EmbedBuilder guildLeft = new EmbedBuilder()
            .setColor(new Color(231, 9, 9))
            .setThumbnail(event.getGuild().getIconUrl())
            .setFooter("Kauwela Bot","https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
            .setAuthor("Bot bir sunucudan ayrıldı!")
            .addField("Sunucu Adı",event.getGuild().getName(),true)
            .addField("Sunucu ID",event.getGuild().getId()+"",true);

        event.getJDA().getGuildById("984469721455919174").getTextChannelById("1077707638596448276").sendMessageEmbeds(guildLeft.build()).queue();
    }

    //Botun çalışmaya başladığında göndereceği mesaj
    @Override
    public void onReady(ReadyEvent event) {
    String date = java.time.LocalTime.now()+"";
        EmbedBuilder startEmbed = new EmbedBuilder()
                .setColor(new Color(16, 208, 26))
                .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setFooter("Kauwela Bot")
                .setAuthor("Bot başlatıldı!")
                .addField("Bot "+date.substring(0,5)+" saatinde çalışmaya başladı.","",false);

        event.getJDA().getGuildById("984469721455919174").getTextChannelById("1077722638643908679").sendMessageEmbeds(startEmbed.build()).queue();


    }


}
