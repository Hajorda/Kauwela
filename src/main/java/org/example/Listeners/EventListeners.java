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

public class EventListeners extends ListenerAdapter {
    private Dotenv dot;

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
            event.getChannel().sendMessageFormat(event.getJDA().getGatewayPing()+" ms"+" "+event.getJDA().getHttpClient().minWebSocketMessageToCompress()).queue();

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


