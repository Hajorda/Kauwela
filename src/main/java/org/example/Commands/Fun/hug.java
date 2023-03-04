package org.example.Commands.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.RandomWaifu.RandomWaifu;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class hug extends ACommand {
    public hug(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("hug", "Keşke bana da birileri sarılsa").addOption(OptionType.MENTIONABLE, "hedef", "Ona sıkıca sarılın", false);

    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        if (event.getOptions().size() != 0) {
            event.reply(event.getUser().getAsMention() + " " + event.getOptions().get(0).getAsUser().getAsMention() + " kişisine sarılıyor").queue();
            event.getChannel().sendMessageEmbeds(new EmbedBuilder()
                    .setImage(new RandomWaifu("sfw", "hug").getImageUrl())
                    .build()).queue();
        } else {
            event.replyEmbeds(new EmbedBuilder().setImage(new RandomWaifu("sfw", "hug").getImageUrl()).build()).queue();
        }
    }
}
