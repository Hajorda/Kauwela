package org.example.Commands.Fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.RandomWaifu.RandomMeme;
import org.jetbrains.annotations.NotNull;

import java.net.URISyntaxException;
import java.util.HashMap;

public class randommeme extends ACommand {
    public randommeme(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("randommeme", "MEME");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        try {
            event.replyEmbeds(RandomMeme.randomMemeGenerate().build()).queue();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
