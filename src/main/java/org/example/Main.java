package org.example;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.example.Commands.CommandManager;
import org.example.Listeners.EventListeners;

import javax.security.auth.login.LoginException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private final Dotenv config;
    private final ShardManager shardManager;

    /**
     * Loads environment variables and builds the bot shard manager.
     * @throws LoginException occurs when bot token is invalid.
     */

    private String[] messages={"Ananı1","Babanı"};
    private int currentIndex=0;
//Run this once

    public Main() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");




        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("Ananı"));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES,GatewayIntent.GUILD_INVITES,GatewayIntent.GUILD_MEMBERS,GatewayIntent.MESSAGE_CONTENT);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS);


        shardManager = builder.build();
        new Timer().schedule(new TimerTask(){
            public void run(){
                builder.setActivity(Activity.watching(messages[currentIndex]));
                currentIndex=(currentIndex+1)%messages.length;

            }},0,30_000);

       shardManager.addEventListener(new EventListeners(config),new CommandManager());




    }

    /**
     * Retrieves the bot environment variables.
     * @return the DotEnv instance for the bot.
     */
    public Dotenv getConfig() { return config; }

    /**
     * Retrieves the bot shard manager.
     * @return the ShardManager instance for the bot.
     */
    public ShardManager getShardManager() { return shardManager; }


    /**
     * Main method where we start our bot.
     */
    public static void main(String[] args) {
        try {

            Main bot = new Main();


        } catch (LoginException e) {
            System.out.println("ERROR: Provided bot token is invalid!");
        }
    }
}