public class Prime {
    public static boolean isprime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        double counterdiviion = Math.sqrt(n);
        for (double i = 2; i <= counterdiviion; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void checkPrime(int... args) {
        for (int i : args) {
            if (isprime(i)) {
                System.out.printf("%d ", i);
            }
        }
        System.out.print("\n");
    }

    public static byte[] HexfromString(String s) {
        int i = s.length();
        byte abyte0[] = new byte[(i + 1) / 2];
        int j = 0;
        int k = 0;
        if (i % 2 == 1)
            abyte0[k++] = (byte) HexfromDigit(s.charAt(j++));
        while (j < i)
            abyte0[k++] = (byte) (HexfromDigit(s.charAt(j++)) << 4 | HexfromDigit(s.charAt(j++)));
        return abyte0;
    }

    public static int HexfromDigit(char c) {
        if (c >= '0' && c <= '9')
            return c - 48;
        if (c >= 'A' && c <= 'F')
            return (c - 65) + 10;
        if (c >= 'a' && c <= 'f')
            return (c - 97) + 10;
        else
            throw new IllegalArgumentException("invalid hex digit: " + c);
    }

    public static void main(String[] args) {
        // checkPrime(5);
        System.out.println(HexfromString("abcde"));
    }
}
