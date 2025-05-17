package com.ghj.ghjaiagent.demo.invoke;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LangChain4jAiInvoke implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        QwenChatModel model = QwenChatModel.builder()
                .apiKey("sk-fa60d70faa1d46fc868f4c5205bc3873")
                .modelName("qwen-plus")
                .build();

        String answer = model.chat("Say 'Hello World'");
        System.out.println(answer); // Hello World

    }
}
