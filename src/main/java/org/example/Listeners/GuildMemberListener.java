package org.example.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GuildMemberListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        String avatarUrl = event.getUser().getEffectiveAvatarUrl();
        String name = event.getUser().getName();
        System.out.println("dadassssssssssssssss");
        System.out.println(name+" sunucuya katıldı!");

        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(name+" katıldı").queue();

        EmbedBuilder joinEmbed = new EmbedBuilder()
                .setColor(new Color(10, 238, 13))
                .setThumbnail(avatarUrl)
                .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setAuthor("Sunucuya yeni biri katıldı!")
                .addField(name+" adlı kullanıcı sunucuya katıldı!", "", false);

        event.getJDA().getGuildById("984469721455919174").getTextChannelById("984473410044305459").sendMessageEmbeds(joinEmbed.build()).queue();
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        String avatarUrl = event.getUser().getEffectiveAvatarUrl();
        String name = event.getUser().getName();

        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(name+" ayrıldı").queue();

        EmbedBuilder leaveEmbed = new EmbedBuilder()
                .setColor(new Color(231, 9, 9))
                .setThumbnail(avatarUrl)
                .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setAuthor("Sunucuya biri ayrıldı")
                .addField(name+" adlı kullanıcı sunucuya terk etti!", "", false);

        event.getJDA().getGuildById("984469721455919174").getTextChannelById("984473410044305459").sendMessageEmbeds(leaveEmbed.build()).queue();

    }
}
