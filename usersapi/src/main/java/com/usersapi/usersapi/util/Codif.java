package com.usersapi.usersapi.util;

import java.security.MessageDigest;

public class Codif {
    public static String hash(String raw){
        try{
            MessageDigest sha256=MessageDigest.getInstance("SHA-256");
            sha256.update(raw.getBytes("UTF-8"));
            byte[] digest = sha256.digest();
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<digest.length;i++){
                sb.append(String.format("%02x", digest[i]));
            }
            String hash=sb.toString();
            return hash;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
      
    public static String fecha_nac(String fec){
        String[] sep = fec.split("-");
        String newSt = String.format("%s-%2s-%2s", sep[2],sep[1],sep[0]);
        return newSt;
    }
}
