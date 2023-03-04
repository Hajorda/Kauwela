package org.example.Commands.Music;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.Commands.ACommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Music extends ACommand {
    protected  TextChannel textChannel;
    protected   Dotenv env;
    protected   String botID;

    public Music(HashMap<String,ACommand> hashMap){
        super(hashMap);
        env = Dotenv.configure().load();
        botID = env.get("BOTID");
    }
    protected String thumbnail(String url)  {
        if (url.contains("youtube")){


            return   "http://img.youtube.com/vi/"+ url.substring(url.indexOf("v=")+2)+"/0.jpg";
        }
        else if(url.contains("youtube") && url.contains("&index")){
            return   "http://img.youtube.com/vi/"+ url.substring(url.indexOf("v=")+2,url.indexOf("&index"))+"/0.jpg";
        }
        return null;
    }

    protected long[] calculateTime(long time){
        long[] duration = new long[4];
        duration[0]= time / (1000 * 60 * 60);
        time = time % (1000 * 60 * 60);
        duration[1]= time / (1000 * 60);
        time = time % (1000 * 60);
        duration[2]= time / 1000;
        time = time % 1000;
        duration[3] = time;
        return  duration;


    }
    @Override
    public void doSomething(@NotNull SlashCommandInteractionEvent event) {
        //Nothing to do
    }
}
