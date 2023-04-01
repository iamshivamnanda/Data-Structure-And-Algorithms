import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;


class UPISecurity {
        private SecretKeySpec skeySpec;
        private Cipher cipher;

        /**
         * Constructor
         */
        public UPISecurity() {
                skeySpec = null;
                cipher = null;
        }

        /**
         * This method is used to init encryption
         *
         * @param key
         * @throws Exception
         */
        public void initEncrypt(String key) throws Exception {
                try {
                        skeySpec = new SecretKeySpec(HexUtil.HexfromString(key), "AES");
                        cipher = Cipher.getInstance("AES");
                        cipher.init(1, skeySpec);
                } catch (NoSuchAlgorithmException nsae) {
                        throw new Exception("Invalid Java Version");
                } catch (NoSuchPaddingException nse) {
                        throw new Exception("Invalid Key");
                }
        }

        /**
         * This method is used to init decryption
         *
         * @param key
         * @throws Exception
         */
        public void initDecrypt(String key) throws Exception {
                try {
                        skeySpec = new SecretKeySpec(HexUtil.HexfromString(key), "AES");
                        cipher = Cipher.getInstance("AES");
                        cipher.init(2, skeySpec);

                } catch (NoSuchAlgorithmException nsae) {
                        throw new Exception("Invalid Java Version");
                } catch (NoSuchPaddingException nse) {
                        throw new Exception("Invalid Key");
                }
        }

        /**
         * This method is used to return encrypted data.
         *
         * @param instr
         * @return String
         * @throws Exception
         */
        public String encrypt(String message,String enc_key) throws Exception {
                try {
                        initEncrypt(enc_key);
                       
                        byte encstr[] = cipher.doFinal(message.getBytes());
                        return HexUtil.HextoString(encstr);
                } catch (BadPaddingException nse) {
                        throw new Exception("Invalid input String");
                }
        }

        /**
         * This method is used to return decrypted data.
         *
         * @param instr
         * @return String
         * @throws Exception
         */
        public String decrypt(String message,String dec_key) throws Exception {
                try {
                       
                        initDecrypt(dec_key);
                       
                        byte encstr[] = cipher.doFinal(HexUtil.HexfromString(message));
                        return new String(encstr);
                } catch (BadPaddingException nse) {
                        throw new Exception("Invalid input String");
                }
        }

}

class HexUtil {

        /**
         * Constructor
         */
        public HexUtil() {
        }

        /**
         * This method is used to convert string in to hex format
         *
         * @param s
         * @return byte[]
         */
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

        /**
         * This method is used to return Hex form Digit
         *
         * @param c
         * @return int
         */
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

        /**
         * This method is used to return string as Hex
         *
         * @param buf
         * @return String
         */
        public static String asHex(byte buf[]) {
                StringBuffer strbuf = new StringBuffer(buf.length * 2);
                for (int i = 0; i < buf.length; i++) {
                        if ((buf[i] & 0xff) < 16)
                                strbuf.append("0");
                        strbuf.append(Long.toString(buf[i] & 0xff, 16));
                }

                return strbuf.toString();
        }

        /**
         * This method is used to convert Hex to string
         *
         * @param abyte0
         * @param i
         * @param j
         * @return String
         */
        public static String HextoString(byte abyte0[], int i, int j) {
                char ac[] = new char[j * 2];
                int k = 0;
                for (int l = i; l < i + j; l++) {
                        byte byte0 = abyte0[l];
                        ac[k++] = hexDigits[byte0 >>> 4 & 0xf];
                        ac[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(ac);
        }

        /**
         * This method is used to convert Hex to string
         *
         * @param abyte0
         * @return String
         */
        public static String HextoString(byte abyte0[]) {
                return HextoString(abyte0, 0, abyte0.length);
        }

        private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

}

public class JavaKit{
public static void main(String[] args) {
        UPISecurity upiSecurity = new UPISecurity();
        try {
                Request rq = new Request();
                rq.requestInfo = new RequestInfo("HDFC000008237596", "MYBANK3D4BCE69E1B645A8A1093CBD214");
                rq.payeeType = new payeeType("rockstar@hdfcbank");
                Gson gson = new Gson();
                String jsonString = gson.toJson(rq);
                System.out.println(jsonString);
                // System.out.println("{\"payeeType\":{\"virtualAddress\":\"rockstar@hdfcbank\"},\"requestInfo\":{\"pgMerchantId\":\"HDFC000008237596\",\"pspRefNo\":\"2e5c363c93307e3d9989a007e862\"}");
                // System.out.println(upiSecurity.encrypt("{\"payeeType\":{\"virtualAddress\":\"rockstar@hdfcbank\"},\"requestInfo\":{\"pgMerchantId\":\"HDFC000008237596\",\"pspRefNo\":\"2e5c363c93307e3d9989a007e862\"}", "7deb3f49d2a824f6bd3463a2d73dd126"));
                System.out.println(upiSecurity.encrypt("{\"payeeType\":{\"virtualAddress\":\"rockstar@hdfcbank\"},\"requestInfo\":{\"pgMerchantId\":\"HDFC000008237596\",\"pspRefNo\":\"3c5545bcb2ee52d493a7c85cdfc5\"}}", "7deb3f49d2a824f6bd3463a2d73dd126"));

                System.out.println(upiSecurity.decrypt("3FA33DA6A249F4FC65210D9884614B9CABE09620D367F204270AF3CF9607534FDFA776F4DE241D32CDA87CF3A4FBA3634FC5F4BB3BE4537213AD55E892AD5AA53CB1B4B25C37A9D817D03F6AB21399AE234C3A4030EF75CD5306A8C50EAA584B3E0D33514B49236ED5E3E4EB81BE12FA0835AAB0095B8993E6BC5FD6DD2FF34AB2C88CE0B43358425E3C1B5EBB187BD7FE95431148C486AED400AEAA380B81C76BE92DF7978FE99B652FE0ACEB42876837C5DBD0CBA6F2F55F5497F6CC0B2C71", "7deb3f49d2a824f6bd3463a2d73dd126"));
        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
}
}



class Request{
        RequestInfo requestInfo;
        payeeType payeeType;        
}


class RequestInfo {
        String pgMerchantId;
        String pspRefNo;
        public RequestInfo(String pgMerchantId, String pspRefNo) {
                this.pgMerchantId = pgMerchantId;
                this.pspRefNo = pspRefNo;
        }

}

class payeeType{
        String virtualAddress;

        public payeeType(String virtualAddress) {
                this.virtualAddress = virtualAddress;
        }
        
}