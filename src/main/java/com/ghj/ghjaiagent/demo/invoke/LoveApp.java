package com.ghj.ghjaiagent.demo.invoke;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Component
@Slf4j
public class LoveApp {

    private final ChatClient chatClient;
    private final String SYSTEM_PROMPT = "你是恋爱助手app,帮助用户回答问题.";

    public LoveApp(ChatModel dashscopeChatModel) {
        InMemoryChatMemory inMemoryChatMemory = new InMemoryChatMemory();
        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(
                        SYSTEM_PROMPT
                ).defaultAdvisors(
                        new MessageChatMemoryAdvisor(inMemoryChatMemory)
                ).build();
    }

    public String doChatWithReport(String message, String chatId) {
        ChatClient.CallResponseSpec chatResponse = chatClient
                .prompt()
                .system(SYSTEM_PROMPT + "每次对话后都要生成恋爱结果，标题为{用户名}的恋爱报告，内容为建议列表")
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call();
        System.out.println(chatResponse.content());
        return chatResponse.content();
    }

    record LoveReport(String title, List<String> suggestions) {
    }
}
