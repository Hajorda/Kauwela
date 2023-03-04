package org.example.Commands.Fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.ChatGPT.ChatGPT;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class randomgpt extends ACommand {
    public randomgpt(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("randomgpt", "Anneni sor").addOption(OptionType.STRING, "soru", "Sorunu sor bakem", true);
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        System.out.println("Çalıştı");
        String prompt = event.getOptions().get(0).getAsString();
        event.deferReply().queue();
        try {
            String response = ChatGPT.chatgptRandom(prompt);

            event.getHook().sendMessage(response.substring(0, response.lastIndexOf("index") - 3)).queue();
        } catch (Exception e) {
            event.getHook().sendMessage("API de sıkıntı çıktı").queue();
        }
    }
}
