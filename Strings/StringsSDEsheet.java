package Strings;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

import javax.print.DocFlavor.STRING;
import javax.swing.JApplet;

public class StringsSDEsheet {
    // public static boolean isPalindrome(String s) {
    // s = s.toLowerCase();
    // StringBuffer str = new StringBuffer();

    // for (char C : s.toCharArray()) {
    // if (Character.isLetter(C) || Character.isDigit(C))
    // str.append(C);
    // }

    // s = str.toString();
    // char ch[] = s.toCharArray();
    // System.out.println(Arrays.toString(ch));
    // int low = 0, high = ch.length - 1;

    // while (low < high) {
    // if (ch[low] != ch[high])
    // return false;
    // low++;
    // high--;
    // }
    // return true;
    // }

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

    // public int characterReplacement(String s, int k) {
    // // AABABBA k = 1
    // char c = '$';
    // int j = k;
    // int MaxLen = 1;
    // int prevIndex = 0;
    // for (int i = 1; i < s.length(); i++) {
    // if (s.charAt(i) != s.charAt(i - 1)) {
    // if (j > 0 && (c == '$' || s.charAt(i) == c)) {
    // c = s.charAt(i);
    // j--;
    // } else {
    // j = k;
    // c = '$';
    // prevIndex = i;
    // }
    // }
    // MaxLen = Math.max(MaxLen, i + 1 - prevIndex);
    // }
    // return MaxLen;
    // }

    public int characterReplacement(String s, int k) {
        // will use sliding window algo

        int l = 0;
        int maxCount = 0;
        int ans = 0;
        HashMap<Character, Integer> hMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            hMap.put(s.charAt(i), hMap.getOrDefault(s.charAt(i), 0) + 1);
            maxCount = Math.max(maxCount, hMap.get(s.charAt(i)));

            while (l <= i && i - l + 1 - maxCount > k) {
                hMap.replace(s.charAt(l), hMap.get(s.charAt(l)) - 1);
                l++;
            }
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        // strs = ["eat","tea","tan","ate","nat","bat"]
        // [["bat"],["nat","tan"],["ate","eat","tea"]]

        List<List<String>> res = new ArrayList<>();

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String string : strs) {
            char ch[] = new char[26];
            for (char c : string.toCharArray()) {
                ch[c - 'a']++;
            }
            String key = new String(ch);

            if (map.containsKey(key)) {
                map.get(key).add(string);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(string);
                map.put(key, list);
            }
        }

        res.addAll(map.values());
        return res;

    }

    public static String longestPalindrome(String s) {
        // babad
        // bab
        HashSet<String> map = new HashSet<>();

        String lonString = "";
        for (int i = 0; i < s.length(); i++) {
            int l = 0;
            while (l <= i) {
                String str = s.substring(l, i + 1);
                if (map.contains(str)) {
                    l++;
                    continue;
                }
                boolean isPalindrome = isPalindrome(str);
                map.add(str);
                if (isPalindrome) {
                    if (i + 1 - l > lonString.length()) {
                        lonString = str;
                    }
                }
                l++;
            }
        }
        return lonString;
    }

    public static boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

    public int countSubstrings(String s) {
        // aaa
        // 6 "a", "a", "a", "aa", "aa", "aaa".
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            // for odd len palindroms
            int l = i;
            int r = i;
            res += countSubstringsUtil(s, l, r);

            // for even len palindroms
            l = i;
            r = i + 1;
            res += countSubstringsUtil(s, l, r);
        }
        return res;
    }

    public static long countSubstringsUtil(String s, int l, int r) {
        long res = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            res++;
            l--;
            r++;
        }
        return res;
    }

    long countPS(String str) {
        long N = 1000000009;
        int n = str.length();
        long dp[][] = new long[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int L = 2; L <= n; L++) {
            for (int k = 0; k <= n - L; k++) {
                int j = L - k - 1;
                if (str.charAt(k) == str.charAt(j)) {
                    dp[k][j] = (dp[k + 1][j] + dp[k][j - 1] + 1) % N;
                } else {
                    dp[k][j] = (dp[k + 1][j] + dp[k][j - 1] - dp[k + 1][j - 1]) % N;
                }
            }
        }
        return dp[0][n - 1];
    }

    // longest proper prefix which is also sufix (used widely in kmp searching
    // algorithm)

    int lps(String s) {
        // abab
        // { 0, 0, 1, 2 }
        // len = 0 , i = 1 (a != b) i++
        // len = 0, i = 2 (a == a) i++ j++
        // len = 1, i = 3 (b == b) i++ j++
        // i > s.length so loop breaks
        int len = 0;
        int i = 1;
        int lps[] = new int[s.length()];
        lps[0] = 0;

        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps[s.length() - 1];
    }

    public int transfigure(String A, String B) {
        if (A.length() != B.length()) {
            return -1;
        }
        int count[] = new int[256];
        for (int i = 0; i < A.length(); i++) {
            count[A.charAt(i)]++;
            count[B.charAt(i)]--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return -1;
            }
        }
        int i = A.length() - 1;
        int j = B.length() - 1;
        int res = 0;

        while (i >= 0) {
            if (A.charAt(i) != B.charAt(j)) {
                res++;
            } else {
                j--;
            }
            i--;
        }

        return res;

    }

    public String minWindow(String s, String t) {
        String resString = "";
        int minLen = Integer.MAX_VALUE;
        if (s.length() < t.length()) {
            return resString;
        }

        int hash_pattern[] = new int[256];
        int hash_text[] = new int[256];

        for (int i = 0; i < t.length(); i++) {
            hash_pattern[t.charAt(i)]++;
        }

        int j = 0;
        int count = 0;
        // int start_index = -1;

        for (int i = 0; i < s.length(); i++) {
            hash_text[s.charAt(i)]++;
            if (hash_text[s.charAt(i)] <= hash_pattern[s.charAt(i)]) {
                count++;
            }

            if (count == t.length()) {
                // minimize the window

                while (hash_text[s.charAt(j)] > hash_pattern[s.charAt(j)] || hash_pattern[s.charAt(j)] == 0) {
                    if (hash_text[s.charAt(j)] > hash_pattern[s.charAt(j)])
                        hash_text[s.charAt(j)]--;
                    j++;
                }
                int newLen = i - j + 1;
                if (newLen < minLen) {
                    minLen = newLen;
                    // start_index = j;
                    resString = s.substring(j, i + 1);
                }
            }
        }

        return resString;
    }

    public static void main(String[] args) {
        String string = "A man, a plan, a canal: Panama";
        String string2 = "()";
        String[] strs = { "flower", "flow", "flight" };
        // System.out.println(longestCommonPrefix(strs));
        // System.out.println(isValid(string2));
        System.out.println(longestPalindrome("babad"));

        String s = "bbbb";
        // System.out.println(lengthOfLongestSubstring(s));
    }
}
