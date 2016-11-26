package com.creejee.phoneeamusement;

import java.math.BigInteger;

/**
 * Created by my on 2016-11-26.
 */

public class stringUtil {
    static String arrayToString(String str[],String subStr){
        int len = str.length;
        String ret = "";
        for (int i = 0; i < len; i++){
            ret += str[i] + subStr;
        }
        return ret;
    }
    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1,data));
    }
}
