package org.example.Commands.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.Commando.appCommands;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class soundboard extends ACommand {
    public soundboard(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("soundboard", "SoundBoard");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {

        EmbedBuilder soundEmbed = new EmbedBuilder()
                .setAuthor("SoundBoard")
                .setDescription("Emojilere tıkla bacım")
                .setColor(Color.YELLOW)
                .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .addField("Sounds", "`HihihiHa` \uD83E\uDD2A", true);


        event.reply("Soundboard Aktif").queue();
        appCommands.TextChannel = event.getChannel().asTextChannel().getId();
        event.getChannel().sendMessageEmbeds(soundEmbed.build()).queue(message -> {
            message.addReaction(Emoji.fromUnicode("U+1F601")).queue();
            message.addReaction(Emoji.fromUnicode("U+1F910")).queue();
            message.addReaction(Emoji.fromUnicode("U+1F922")).queue();
        });
    }
}
