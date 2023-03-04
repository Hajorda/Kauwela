package org.example.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public  abstract class  ACommand {
    protected  String url;
    protected String commandName;
    protected OptionData optionData;
    protected SlashCommandData slashCommandData;

    public ACommand(HashMap<String,ACommand>hashMap){
        commandName = this.getClass().getSimpleName();
        hashMap.put(this.commandName,this);

    } public ACommand(HashMap<String,ACommand>hashMap,String s){
        commandName = s;
        hashMap.put(this.commandName,this);

    }
    public abstract void doSomething(@NotNull SlashCommandInteractionEvent event);

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public OptionData getOptionData() {
        return optionData;
    }

    public void setOptionData(OptionData optionData) {
        this.optionData = optionData;
    }

    public SlashCommandData getSlashCommandData() {
        return slashCommandData;
    }

    public void setSlashCommandData(SlashCommandData slashCommandData) {
        this.slashCommandData = slashCommandData;
    }
}
