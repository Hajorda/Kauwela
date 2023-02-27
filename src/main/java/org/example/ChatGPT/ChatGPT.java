package org.example.ChatGPT;


import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
//SAJNSJKANJDNASJKDNAJKD
public class ChatGPT {

    public static String chatgpt(String text,int maxToken, double tempaerature,double topP,double frequencyPenalty,double preesencePenalty) {
        AtomicReference<String> answer = new AtomicReference<>("");
        OpenAiService service = new OpenAiService(Dotenv.load().get("APIKEY"), Duration.ofMillis(1000000000));
        String birText = "AI: My name is Kauwela Bot and I am a discord bot.\n" +
                "Human: Help\n" +
                "AI: You can get help with writing !help command to chat\nHuman:"+text;
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(birText)
                .model("text-davinci-003")

                .maxTokens(maxToken)
                .temperature(tempaerature)
                .topP(topP)
                .frequencyPenalty(frequencyPenalty)
                .presencePenalty(preesencePenalty)
                .build();

        service.createCompletion(completionRequest).getChoices().forEach(name -> {
            answer.updateAndGet(v -> v + name);
        });
        System.out.println(answer.get());
        return answer.get().substring(answer.get().indexOf("\n")).trim();


    }
    public static String chatgpt(String text) {
        AtomicReference<String> answer = new AtomicReference<>("");
        OpenAiService service = new OpenAiService(Dotenv.load().get("APIKEY"), Duration.ofMillis(1000000000));
        String birText = "AI: My name is Kauwela Bot and I am a discord bot. I can speak both Turkish and English.\n" +
                "Human: Help me\n" +
                "AI: You can get help with writing !help command to chat\nHuman:"+text;
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(birText)
                .model("text-davinci-003")

                .maxTokens(150)
                .temperature(0.9)
                .topP(1.0)
                .frequencyPenalty(0.0)
                .presencePenalty(0.6)
                .build();

        service.createCompletion(completionRequest).getChoices().forEach(name -> {
            answer.updateAndGet(v -> v + name);
        });
        System.out.println(answer.get());
        return answer.get().substring(answer.get().indexOf("\n")).trim();


    }
    public static String chatgptRandom(String text) {
        AtomicReference<String> answer = new AtomicReference<>("");
        OpenAiService service = new OpenAiService(Dotenv.load().get("APIKEY"), Duration.ofMillis(1000000000));

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(text)
                .model("text-davinci-003")

                .maxTokens(new Random().nextInt(4001))
                .temperature(new Random().nextDouble(1))
                .topP(1.0)
                .frequencyPenalty(new Random().nextDouble(1))
                .presencePenalty(new Random().nextDouble(1))
                .build();

        service.createCompletion(completionRequest).getChoices().forEach(name -> {
            answer.updateAndGet(v -> v + name);
        });
        System.out.println(answer.get());
        return answer.get().substring(answer.get().indexOf("\n")).trim();


    }

    public static String answer(String text) {

        System.out.println(text.contains("\n"));
        if (text.length() > 60) {
            if (text.contains("\n")){
                System.out.println("1");
                return  text.substring(0, text.indexOf("index") - 3);
            }
            else {
                int tempCountedChar = 0;
                String temp = "";
                int firstindex=0;
                for (int i = 0 ; i < text.length();i++){
                    tempCountedChar ++;
                    if (text.charAt(i) == ' ' && tempCountedChar >=60){
                        temp += text.substring(firstindex,i)+"\n";
                        firstindex = i;
                        tempCountedChar =0;
                    }
                }
                System.out.println(2);
                return temp;
            }

        } else {
            System.out.println(3);
            return text.substring(0, text.indexOf("index") - 3);

        }
    }
}