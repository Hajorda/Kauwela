package org.example.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import okhttp3.internal.http2.Http2Connection;

import java.awt.*;


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

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals("feedback")) {
            String subject = event.getValue("subject").getAsString();
            String body = event.getValue("body").getAsString();
            String user = event.getUser().getName();
            String userPp = event.getUser().getAvatarUrl();

            EmbedBuilder feedbackEmbed = new EmbedBuilder()
                    .addField("Subject", subject, false)
                    .addField("Description", body, false)
                    .setFooter(user, userPp)
                    .setColor(Color.green);

            event.reply("Thanks for your feedback!").setEphemeral(true).queue();
            event.getJDA().getGuildById("984469721455919174").getTextChannelById("1079155021876297758").sendMessageEmbeds(feedbackEmbed.build()).queue();
        }
    }


}