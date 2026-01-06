package com.thinkdifferent.aipicturebackend;

import java.util.regex.Pattern;

public class UniversalSpaceRemover {
    
    // 正则表达式匹配所有类型的空格
    private static final Pattern ALL_SPACES_PATTERN = 
        Pattern.compile("[\\p{Z}\\s\\p{Cf}\\p{Cc}\\p{Cn}]");
    
    private static final Pattern LEADING_TRAILING_SPACES_PATTERN = 
        Pattern.compile("^[\\p{Z}\\s\\p{Cf}\\p{Cc}\\p{Cn}]+|[\\p{Z}\\s\\p{Cf}\\p{Cc}\\p{Cn}]+$");
    
    /**
     * 去除字符串两端的各种空格（兼容所有Java版本）
     * 包括：
     * - \p{Z} - 所有Unicode分隔符（空格、行分隔符等）
     * - \s - 所有空白字符
     * - \p{Cf} - 格式控制字符（零宽度空格等）
     * - \p{Cc} - 控制字符
     * - \p{Cn} - 未分配字符
     */
    public static String trimAllSpaces(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return LEADING_TRAILING_SPACES_PATTERN.matcher(str).replaceAll("");
    }
    
    /**
     * 去除字符串中所有位置的各种空格
     */
    public static String removeAllSpaces(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return ALL_SPACES_PATTERN.matcher(str).replaceAll("");
    }
    
    /**
     * 更精确的空格去除，只去除真正的空格类字符
     */
    public static String removeExactSpaces(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        // 匹配各种空格字符
        Pattern exactSpaces = Pattern.compile(
            "[\\u0020\\u00A0\\u1680\\u2000-\\u200A\\u202F\\u205F\\u3000\\uFEFF" +  // 各种空格
            "\\u200B\\u200C\\u200D\\u2060\\uFEFF]" +  // 零宽度字符
            "|[\\t\\n\\x0B\\f\\r]"  // 传统空白字符
        );
        
        return exactSpaces.matcher(str).replaceAll("");
    }
}