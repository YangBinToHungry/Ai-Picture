package com.thinkdifferent.aipicturebackend;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    @org.junit.jupiter.api.Test
    public void testdemo1() {
        // 测试字符串包含各种空格
        String test = "Hello\u00A0World\u200B!\u3000\u00A0\u200B";
        System.out.println("原始字符串长度: " + test.length());
        System.out.println("原始: '" + test + "'");
        // 测试各种方法
        System.out.println("\n使用trim(): '" + test.trim() + "'"+"字符串长度："+test.trim().length());
        System.out.println("使用trimAllSpaces(): '" + UniversalSpaceRemover.trimAllSpaces(test) + "'"+"字符串长度："+UniversalSpaceRemover.trimAllSpaces(test).length());
        System.out.println("使用removeAllSpaces(): '" + UniversalSpaceRemover.removeAllSpaces(test) + "'"+"字符串长度："+UniversalSpaceRemover.removeAllSpaces(test).length());
    }
}
