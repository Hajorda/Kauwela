package org.example.Commands.Music;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.Commands.ACommand;
import org.example.MusicPlayer.GuildMusicManager;
import org.example.MusicPlayer.PlayerManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class pause extends Music{
    public pause(HashMap<String, ACommand> hashMap){
        super(hashMap);
        slashCommandData = Commands.slash("pause", "Şarkıyı durdurur.");
    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {

        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        if (!event.getGuild().getMemberById(botID).getVoiceState().inAudioChannel()) {
            event.reply("Ses kanalında değilim la").queue();

        } else if (!event.getMember().getVoiceState().inAudioChannel()) {
            event.reply("Kardeşşşş ses kanalında değilsin").queue();
        } else {
            musicManager.scheduler.player.setPaused(true);

            event.reply("Müzik durduruldu").queue();
        }

    }
}
