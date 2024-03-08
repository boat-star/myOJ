package com.mhc.myoj.leetcode;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@SpringBootTest
public class Test {
    public String compress(String str) {
        // write code here
        String ret = "";
        String inner = "";
        int n = str.length();
        int i = 0;
        Stack<Map<String, String>> stack = new Stack<>();
        while (i < n) {
            if (str.charAt(i) == '[') {
                String num = "";
                i++;
                while (str.charAt(i) != '|') {
                    num += str.charAt(i);
                    i++;
                }
                String s = "";
                i++;
                while (str.charAt(i) != ']' && str.charAt(i) != '[') {
                    s += str.charAt(i);
                    i++;
                }
                Map<String, String> map = new HashMap<>();
                map.put(num, s);
                stack.push(map);
            } else if (str.charAt(i) == ']') {
                Map<String, String> m = stack.peek();
                // 移除元素
                stack.pop();
                // 组合内部inner
                for (String key : m.keySet()) {
                    int cnt = Integer.parseInt(key);
                    String temp = m.get(key) + inner;
                    inner = temp;
                    for (int k = 1; k < cnt; k++) {
                        inner += temp;
                    }
                }
                if (stack.isEmpty()) {
                    ret += inner;
                    inner = "";
                }
                i++;
            } else {
                if(stack.isEmpty())
                    ret += str.charAt(i);
                else
                    inner += str.charAt(i);
                i++;
            }
        }
        return ret;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        String str = "[3|A[2|B][2|C]]";
        System.out.println(compress(str));
    }
}
