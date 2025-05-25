package com.ghj.ghjaiagent.demo.invoke;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoveAppTest {

    @Autowired
    LoveApp loveApp;

    @Test
    void doChatWithReport() {
        String chatId = "123456";
        String message = "你好我叫高慧杰";
       loveApp.doChatWithReport(message, chatId);
     //   assertNotNull(loveReport);

        message = "怎么谈恋爱";
        loveApp.doChatWithReport(message, chatId);
  //      assertNotNull(loveReport);

        message = "我叫什么名字";
        loveApp.doChatWithReport(message, chatId);
     //   assertNotNull(loveReport);

    }
}