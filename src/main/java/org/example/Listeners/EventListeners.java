package org.example.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListeners extends ListenerAdapter {

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


