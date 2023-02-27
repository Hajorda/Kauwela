package org.example.RandomCat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class RandomCuteKedy {

    public String getImage(String tag) throws IOException {
        URL url = new URL("https://cataas.com/cat/"+tag+"?json=true");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("https://cataas.com"+mapper.readTree(url).get("url").asText());
        return "https://cataas.com"+mapper.readTree(url).get("url").asText();

    }
    private List<String> getChoicesname(int optionsize) throws IOException {
        List<String> s = new ArrayList<>();

        File url = new File("response.json");
        int i = 0;
        int size = optionsize;
        ObjectMapper mapper = new ObjectMapper();
        for ( ;i < size ;i++){
            //System.out.println(mapper.readTree(url).get(i).asText());
            if (mapper.readTree(url).get(i).asText().equals("") ||mapper.readTree(url).get(i).asText().equals("\n")){
                System.out.println("boş");
                size++;


            }else {
                s.add(mapper.readTree(url).get(i).asText());
            }

        }

        return s;

    }
    public  List<Command.Choice> getChoices(int size) throws IOException {
        List<Command.Choice> choices = new ArrayList<>();
        for (String s : getChoicesname(size)){
            choices.add(new Command.Choice(s.toLowerCase(),s));
        }
        //choices.add(new Command.Choice(DataObject.fromJson(new InputStreamReader())));
        return choices;

    }


}
