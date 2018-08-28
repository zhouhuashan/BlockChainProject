package com.cy.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class BaseUtil {

    /**
     *  生成UUID
     * @return
     */
    public static String getUUID32(){

        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }


    /**
     *  SHA256 加密
     * @param str
     * @return
     */
    public static String String2SHA256(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try{
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return encdeStr;
    }



}
