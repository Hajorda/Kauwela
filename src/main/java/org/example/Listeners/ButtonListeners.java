package org.example.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ButtonListeners extends ListenerAdapter{
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("rimage_save")) {
            EmbedBuilder saveGPT = new EmbedBuilder()
                    .setColor(Color.orange)
                    .setImage(MessageListener.url)
                    .setFooter(event.getUser().getName(), event.getUser().getAvatarUrl());
            event.getGuild().getTextChannelById("1079329718446342244").sendMessageEmbeds(saveGPT.build()).queue();
        }
    }
/*public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("hello")) {
            event.reply("Click the button to say hello")
                    .addActionRow(
                            Button.primary("hello", "Click Me"), // Button with only a label
                            Button.success("emoji", Emoji.fromMarkdown("<:minn:245267426227388416>"))) // Button with only an emoji
                    .queue();
        } else if (event.getName().equals("info")) {
            event.reply("Click the buttons for more info")
                    .addActionRow( // link buttons don't send events, they just open a link in the browser when clicked
                            Button.link("https://github.com/DV8FromTheWorld/JDA", "GitHub")
                                    .withEmoji(Emoji.fromMarkdown("<:github:849286315580719104>")), // Link Button with label and emoji
                            Button.link("https://ci.dv8tion.net/job/JDA/javadoc/", "Javadocs")) // Link Button with only a label
                    .queue();
        }
    }

    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("hello")) {
            event.reply("Hello :)").queue();
        }
    }*/
}
