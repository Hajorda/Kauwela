package org.example.Commands.Utilites;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class feedback extends ACommand {
    public feedback(HashMap<String,ACommand> hashMap){
        super(hashMap);
        slashCommandData=Commands.slash("feedback", "Feedback");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("feedback")) {
            TextInput subject = TextInput.create("subject", "Subject", TextInputStyle.SHORT)
                    .setPlaceholder("Subject of this feedback")
                    .setMinLength(10)
                    .setMaxLength(100) // or setRequiredRange(10, 100)
                    .build();

            TextInput body = TextInput.create("body", "Body", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Your feedback goes here")
                    .setMinLength(30)
                    .setMaxLength(1000)
                    .build();

            Modal modal = Modal.create("feedback", "Feedback")
                    .addActionRows(ActionRow.of(subject), ActionRow.of(body))
                    .build();

            event.replyModal(modal).queue();
        }
    }
}
