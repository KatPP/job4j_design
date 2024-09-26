package ru.job4j.algo.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    /**
     * @param str
     * @return string with max length, which consist of unique symbols.
     * Time complexity O(n + m): n - length @param str, m - count of unique strings.
     * Memory complexity: 1) when String is Empty: 0 byte;
     * 2) when String has all Unique characters: 292 bytes;
     * 3) when map contains two Unique Strings: 307 bytes;
     * 4) when Unique String length = 1: 288 bytes.
     */
    public static String longestUniqueSubstring(String str) {
        if (str.isEmpty()) {
            return str;
        } else {
            Map<String, Integer> map = new HashMap<>();
            StringBuilder builder = new StringBuilder();
            Integer countLength = 0;
            Integer maxLength = Integer.MIN_VALUE;
            String result = "";
            for (int i = 0; i < str.length(); i++) {
                String letter = String.valueOf(str.charAt(i));
                if (!builder.toString().contains(letter)) {
                    countLength++;
                    builder.append(letter);
                    if (i + 1 == str.length()) {
                        map.put(builder.toString(), countLength);
                    }
                } else {
                    map.put(builder.toString(), countLength);
                    builder = new StringBuilder();
                    builder.append(letter);
                    countLength = 1;
                }
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() > maxLength) {
                    maxLength = entry.getValue();
                    result = entry.getKey();
                }
            }
            return result;
        }
    }
}
