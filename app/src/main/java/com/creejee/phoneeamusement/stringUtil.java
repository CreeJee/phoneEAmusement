package com.creejee.phoneeamusement;
import android.support.annotation.NonNull;


/**
 * Created by my on 2016-11-26.
 */

public class StringUtil {
    static String arrayToString(String str[],String subStr){
        int len = str.length;
        String ret = "";
        for (int i = 0; i < len; i++){
            ret += str[i] + subStr;
        }
        return ret;
    }
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    @NonNull
    public static  String hexToString(String hex){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i+=2) {
            String str = hex.substring(i, i+2);
            output.append((char)Integer.parseInt(str, 16));
        }
        return output.toString();
    }
}
