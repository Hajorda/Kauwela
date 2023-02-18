package org.example.Listeners;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    private Dotenv dot;
    private String prefix;

    public MessageListener(Dotenv dot) {
        this.dot = dot;
        prefix = dot.get("PREFIX");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

    }
}
