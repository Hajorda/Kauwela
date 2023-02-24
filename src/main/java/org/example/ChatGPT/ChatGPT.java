package org.example.ChatGPT;


import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

public class ChatGPT {
    public static String chatgpt(String text) {
        AtomicReference<String> answer = new AtomicReference<>("");
        OpenAiService service = new OpenAiService(Dotenv.load().get("APIKEY"), Duration.ofMillis(1000000000));

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(text)
                .model("text-davinci-003")
                .echo(true)
                .maxTokens(4000)

                .build();

        service.createCompletion(completionRequest).getChoices().forEach(name -> {
            answer.updateAndGet(v -> v + name);
        });
        return answer.get().trim();


    }

    public static String answer(String text) {

        System.out.println(text.contains("\n"));
        if (text.length() > 60) {
            if (text.contains("\n")){
                System.out.println("1");
                return text.substring(text.indexOf("\n"), text.indexOf("index") - 2);

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
            return text.substring(text.indexOf("\n"), text.indexOf("index") - 2);

        }
    }
}