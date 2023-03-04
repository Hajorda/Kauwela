package org.example.Commands.Music;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.Commands.ACommand;
import org.example.MusicPlayer.GuildMusicManager;
import org.example.MusicPlayer.PlayerManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class leave extends Music{
    public leave(HashMap<String, ACommand> hashMap){
        super(hashMap);
        slashCommandData= Commands.slash("leave","sg buradan bot");

    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
            event.reply("Ses kanalında değilim la").queue();

        } else if (!event.getMember().getVoiceState().inAudioChannel()) {
            event.reply("Kardeşşşş ses kanalında değilsin").queue();
        } else {

            GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            AudioManager audioManager = event.getGuild().getAudioManager();
            musicManager.scheduler.player.stopTrack();
            musicManager.scheduler.queue.clear();
            audioManager.closeAudioConnection();

            event.reply("Hoççağalın ğidiyom ben").queue();
        }

    }
}
