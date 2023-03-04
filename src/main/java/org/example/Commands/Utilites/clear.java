package org.example.Commands.Utilites;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class clear extends ACommand {
    public clear(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("clear", "Sohbet kaydını temizler").addOption(OptionType.INTEGER, "mesajsayisi", "Temizleyeceğin mesaj sayisini gir", true);
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {

        System.out.println("CLEAR KOMUDUU!!!");
        int sayi = event.getOptions().get(0).getAsInt();
        if (sayi < 100) {
            event.getChannel().asTextChannel().getIterableHistory().takeAsync(sayi + 1).thenAccept(event.getChannel()::purgeMessages);

            event.getChannel().sendMessage(sayi + " kadar mesaj silindi!").queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
        } else
            event.reply("100 den fazla mesaj silemezsin!").queue(m -> m.deleteOriginal().queueAfter(5, TimeUnit.SECONDS));
    }
}
