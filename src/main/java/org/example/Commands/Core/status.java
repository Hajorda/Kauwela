package org.example.Commands.Core;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.Listeners.MessageListener;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class status extends ACommand {
    public status(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("status", "Status of Bot");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("KauwelaBot'un Durumu", null);
        eb.setColor(new Color(0x59E010));
        eb.setThumbnail("https://media.discordapp.net/attachments/984469722500329474/1076536703365435522/image.png");

        eb.setDescription("");
        eb.addField("Ping", "" + event.getJDA().getGatewayPing() + "ms", true);
        eb.addField("Kullanıcı Sayısı", "" + MessageListener.totalMember(event), true);
        eb.addField("Sunucu Sayısı", "" + MessageListener.totalServer(event), true);

        eb.addField("Bot Durumu", "Aktif", true);
        eb.addField("Sunucu Shard", "" + event.getJDA().getShardInfo().getShardString(), true);

        eb.setFooter("KauwelaBot", event.getJDA().getSelfUser().getAvatarUrl());

        //event.getChannel().sendMessageFormat(eb.build()).queue();
        event.replyEmbeds(eb.build()).queue();
    }
}
