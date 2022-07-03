package Strings;

public class Search {
    static boolean search(String pat, String txt)
    {
            int patl = pat.length();
            int txtl = txt.length();
            for (int i = 0; i <= txtl-patl; i++) {
                boolean flag = true;
                for (int j = 0; j < patl; j++){
                    if(pat.charAt(j) != txt.charAt(i+j)){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    return true;
                }
            }
            return false;
    }
    public static int binarySubstring(int a, String str)
    {
        int count =0;
        for (int i = 0; i < a; i++) {
            if(str.charAt(i) == '1'){
                count++;
            }
        }
        return (count *(count-1) )/2;
    }
    public static boolean isAnagram(String a,String b)
    {
        int cha[] = new int[256];

        if(a.length() != b.length()){
            return false;
        }
        for (int i = 0; i < a.length(); i++) {
            cha[a.charAt(i)]++;
            cha[b.charAt(i)]--;
        }

        for (int i = 0; i < cha.length; i++) {
            if(cha[i]!=0){
                return false;
            }
        }
        return true;
        
    }
    public static void main(String[] args) {
        
    }
}
