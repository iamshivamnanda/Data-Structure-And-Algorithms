package Strings;

import java.util.Arrays;

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
        if(s.length() != t.length())
            return false;
        int arr[] = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)-97]++;
        }
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i)-97]--;
        }

        for (int i : arr) {
            if(i != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String string = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(string));
    }
}
