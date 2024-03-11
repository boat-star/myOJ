package com.mhc.myoj.leetcode;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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
        int num = 4;
        int[][] items = new int[][]{{1,3},{1,2},{4,5},{3,7}};
        Arrays.sort(items, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });
        for(int i = 0; i < num; i++) {
            System.out.println("{" + items[i][0] + " " + items[i][1] + "}");
        }
    }

    // 对每组数据的sout
    @org.junit.jupiter.api.Test
    public void everyOutput() {
        Scanner in = new Scanner(System.in);
        System.out.println("输入数量：");
        int num = in.nextInt(); // 物品数量
        // int[i][0] 表示第i件物品的x属性
        // int[i][1] 表示第i件物品的y属性
        int[][] items = new int[num][2];
        // 记录x，y属性
        for(int i = 0; i < num; i++) {
            items[i][0] = in.nextInt();
        }
        for(int i = 0; i < num; i++) {
            items[i][1] = in.nextInt();
        }
        Arrays.sort(items, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });
        for(int i = 0; i < num; i++) {
            System.out.println("{" + items[i][0] + " " + items[i][1] + "}");
        }
    }
}
