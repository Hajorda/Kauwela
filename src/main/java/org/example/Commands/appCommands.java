package org.example.Commands;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class appCommands extends ListenerAdapter {
    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        System.out.println("deneme1");
        if (event.getName().equals("Get user avatar")) {
            System.out.println("deneme2");
            // event.deferReply().queue();
           // event.reply("DENEME").queue();
            event.reply("Avatar: " + event.getTarget().getEffectiveAvatarUrl()).queue();
        }
    }

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        if (event.getName().equals("Count words")) {
            event.reply("Words: " + event.getTarget().getContentRaw().split("\\s+").length).queue();
        }
    }
}
