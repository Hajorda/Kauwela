package org.example.Commands.Utilites;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class serverinfo extends ACommand {
    public serverinfo(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("serverinfo", "Sunucunun bilgilerine bak");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder serverEmbed = new EmbedBuilder()
                .setAuthor(event.getGuild().getName(), event.getGuild().getIconUrl())
                .setColor(Color.YELLOW)
                .setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .setFooter("Kauwela Bot", "https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png")
                .addField("Server Name", event.getGuild().getName(), true)
                .addField("Server ID", "", true)
                .addField("Owner", "", true)
                .addField("Creation Date", "", true)
                .addField("Members", "", true)
                .addField("Other", "", true);

        event.replyEmbeds(serverEmbed.build()).queue();

    }
}
