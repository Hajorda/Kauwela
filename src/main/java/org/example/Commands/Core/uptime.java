package org.example.Commands.Core;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;

public class uptime extends ACommand {
    public uptime(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData = Commands.slash("uptime","Bakalım köle bot ne kadardır çalışıyor");

    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long uptime = rb.getUptime();
        System.out.println(uptime);
        long day = uptime / (1000 * 60 * 60 * 24);
        uptime = uptime % (1000 * 60 * 60 * 24);
        long hour = uptime / (1000 * 60 * 60);
        uptime = uptime % (1000 * 60 * 60);
        long minute = uptime / (1000 * 60);
        uptime = uptime % (1000 * 60);
        long second = uptime / 1000;
        uptime = uptime % 1000;
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(new Color(204, 255, 255))
                .setTitle("Botun çalışma süresi")
                .addField("Day", "" + day, true)
                .addField("Hour", "" + hour, true)
                .addField("Minute", "" + minute, true)
                .addField("Second", "" + second, true)
                .addField("Milliseconds", "" + uptime, true)
                .setFooter("KauwelaBot", event.getJDA().getSelfUser().getAvatarUrl());

        event.replyEmbeds(embed.build()).queue();
    }
}
