package org.example.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.managers.AudioManager;
import okhttp3.internal.http2.Http2Connection;
import org.example.MusicPlayer.PlayerManager;

import java.awt.*;


public class appCommands extends ListenerAdapter {
    public static String TextChannel;
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
                    .setAuthor("Feedback")
                    .setFooter(user, userPp)
                    .setColor(Color.green)
                    .setThumbnail("https://cdn.discordapp.com/avatars/984469828008026192/50f4e125b07d64833a21925b26d22cb6.png");

            event.reply("Thanks for your feedback!").setEphemeral(true).queue();
            event.getJDA().getGuildById("984469721455919174").getTextChannelById("1079155021876297758").sendMessageEmbeds(feedbackEmbed.build()).queue();
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

    }


    @Override
    public void onGenericMessageReaction(GenericMessageReactionEvent event) {
      if(!(event.getUserId().equals("984469828008026192")) && event.getChannel().asTextChannel().getId().equals(TextChannel))

          if(event.getEmoji().equals(Emoji.fromUnicode("U+1F601"))){
        event.getChannel().sendMessage("Emoji1").queue();
              AudioManager audioManager = event.getGuild().getAudioManager();
              VoiceChannel memberchannel = event.getMember().getVoiceState().getChannel().asVoiceChannel();
              audioManager.openAudioConnection(memberchannel);
              PlayerManager.getInstance().loadAndPlayNonEmbed(event.getGuild().getTextChannels().get(0), "https://www.youtube.com/watch?v=2kwA5T7AjXY");
       }
        else if(event.getEmoji().equals(Emoji.fromUnicode("U+1F910"))){
            event.getChannel().sendMessage("Emoji2").queue();
            AudioManager audioManager = event.getGuild().getAudioManager();
            VoiceChannel memberchannel = event.getMember().getVoiceState().getChannel().asVoiceChannel();
            audioManager.openAudioConnection(memberchannel);
            PlayerManager.getInstance().loadAndPlayNonEmbed(event.getGuild().getTextChannels().get(0), "https://www.youtu.be/UZ3kt2T0m-Q");
        }
        else if(event.getEmoji().equals(Emoji.fromUnicode("U+1F922"))){
            event.getChannel().sendMessage("Emoji3").queue();
        }
    }
}