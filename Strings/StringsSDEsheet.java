package Strings;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

public class StringsSDEsheet {
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuffer str = new StringBuffer();

        for (char C : s.toCharArray()) {
            if (Character.isLetter(C) || Character.isDigit(C))
                str.append(C);
        }

        s = str.toString();
        char ch[] = s.toCharArray();
        System.out.println(Arrays.toString(ch));
        int low = 0, high = ch.length - 1;

        while (low < high) {
            if (ch[low] != ch[high])
                return false;
            low++;
            high--;
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int arr[] = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 97]++;
        }
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i) - 97]--;
        }

        for (int i : arr) {
            if (i != 0)
                return false;
        }
        return true;
    }

    public String removeConsecutiveCharacter(String S) {
        String res = "";
        res += S.charAt(0);
        int i = 1;
        int j = 0;
        while (i < S.length()) {
            if (S.charAt(i) == res.charAt(j)) {
                i++;
                continue;
            }
            res += S.charAt(i);
            i++;
            j++;
        }
        return res;
    }

    public static boolean isValidUtil(char c) {
        if (c == '(' || c == '[' || c == '{') {
            return true;
        }
        return false;
    }

    private static char isValidUtil2(char c) {
        switch (c) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                return '*';
        }
    }

    public static boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                continue;
            char c = s.charAt(i);
            if (isValidUtil(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && isValidUtil2(c) == stack.peek()) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        if (!stack.isEmpty())
            return false;
        return true;
    }

    public static String longestCommanPrefixUtil(String str1, String str2) {
        String res = "";
        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
            res += str1.charAt(i);
        }
        return res;
    }

    public static String longestCommonPrefix(String[] strs) {
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            res = longestCommanPrefixUtil(res, strs[i]);
        }
        return res;
    }

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> hMap = new HashMap<>();

        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (hMap.containsKey(ch)) {
                int newStart = hMap.get(ch) + 1;
                if (newStart > start) {
                    start = newStart;
                }
                hMap.replace(ch, i);
            } else {
                hMap.put(ch, i);
            }
            maxLen = Math.max(maxLen, i + 1 - start);
        }
        return maxLen;

    }

    public int characterReplacement(String s, int k) {
        // AABABBA k = 1
        char c = '$';
        int j = k;
        int MaxLen = 1;
        int prevIndex = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                if (j > 0 && (c == '$' || s.charAt(i) == c)) {
                    c = s.charAt(i);
                    j--;
                } else {
                    j = k;
                    c = '$';
                    prevIndex = i;
                }
            }
            MaxLen = Math.max(MaxLen, i + 1 - prevIndex);
        }
        return MaxLen;
    }

    public static void main(String[] args) {
        String string = "A man, a plan, a canal: Panama";
        String string2 = "()";
        String[] strs = { "flower", "flow", "flight" };
        System.out.println(longestCommonPrefix(strs));
        // System.out.println(isValid(string2));
        // System.out.println(isPalindrome(string));

        String s = "bbbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
