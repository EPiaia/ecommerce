package com.mycompany.ecommerce.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Piaia
 */
public class StringUtil implements Serializable {

    public static String MD5(String txt) {
        if (txt == null || txt.isEmpty()) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(txt.getBytes("UTF-8"));
            BigInteger bigInt = new BigInteger(1, digest);
            String hash = bigInt.toString(16);
            return "0".repeat(32 - hash.length()) + hash;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isNullOrEmpty(String txt) {
        return txt == null || txt.isEmpty();
    }

    public static boolean containsOnlyNumbers(String txt) {
        return txt.matches("[0-9]+");
    }

    public static String coalesce(String s1, String s2) {
        if (s1 == null || s1.isEmpty()) {
            return s2;
        } else {
            return s1;
        }
    }
}
