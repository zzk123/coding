package com.zzk.testDemo;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

import java.net.StandardSocketOptions;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @ClassName UrlDemo
 * @Description TODO
 * @Author zzk
 * @Date 2023/5/1 17:16
 **/
public class UrlDemo {

    public static final String BASE_62_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int BASE = BASE_62_CHAR.length();

    @Test
    public void testBase64() throws Exception{
        String strUrl = "https://www.google.com/search?q=HBase+%E6%98%AF%E4%BB%80%E4%B9%88&newwindow=1&ei=pGhPZMbXA8yS-Aba8KLQCQ&ved=0ahUKEwjGucXzytP-AhVMCd4KHVq4CJoQ4dUDCA8&uact=5&oq=HBase+%E6%98%AF%E4%BB%80%E4%B9%88&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQA0oECEEYAFAAWABgwgVoAHABeACAAQCIAQCSAQCYAQCgAQKgAQHAAQE&sclient=gws-wiz-serp";
        String md5Str = MD5Utils.string2MD5(strUrl);
        System.out.println(strUrl);
        System.out.println(md5Str);
        String base64encodedString = Base64.getEncoder().encodeToString(md5Str.getBytes("utf-8"));
        System.out.println(base64encodedString);
    }

    @Test
    public void testBase642() throws Exception{
        int start = 1000;
        for(int i=start; i<start+100; i++){
            //62编码
            System.out.println(fromBase10(i));
        }
    }

    @Test
    public void testBase62() throws Exception{
        int start = 1000;
        for(int i=start; i<start+100; i++){
            System.out.println();
        }
    }

    /**
     * 十进制转62进制
     * @param i
     * @return
     */
    public static String fromBase10(long i) {
        StringBuilder sb = new StringBuilder("");
        if (i == 0) {
            return "a";
        }
        while (i > 0) {
            i = fromBase10(i, sb);
        }
        return sb.reverse().toString();
    }
    private static long fromBase10(long i, final StringBuilder sb) {
        int rem = (int)(i % BASE);
        sb.append(BASE_62_CHAR.charAt(rem));
        return i / BASE;
    }

    /**
     * 62进制转10进制
     * @param String
     * @return
     */
    public static long toBase10(String str) {
        //从右边开始
        return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
    }
    private static long toBase10(char[] chars) {
        long n = 0;
        int pow = 0;
        for(char item: chars){
            n += toBase10(BASE_62_CHAR.indexOf(item),pow);
            pow++;
        }
        return n;
    }

    private static long toBase10(int n, int pow) {
        return n * (long) Math.pow(BASE, pow);
    }

    @Test
    public void testHash(){
        String strUrl = "https://www.google.com/search?q=HBase+%E6%98%AF%E4%BB%80%E4%B9%88&newwindow=1&ei=pGhPZMbXA8yS-Aba8KLQCQ&ved=0ahUKEwjGucXzytP-AhVMCd4KHVq4CJoQ4dUDCA8&uact=5&oq=HBase+%E6%98%AF%E4%BB%80%E4%B9%88&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQA0oECEEYAFAAWABgwgVoAHABeACAAQCIAQCSAQCYAQCgAQKgAQHAAQE&sclient=gws-wiz-serp";
        Long murmurVal = genHexHashString(strUrl);
        System.out.println(murmurVal);
    }

    /**
     * 哈希算法 - Murmur算法
     * @param str
     * @return
     */
    public static Long genHexHashString(String str){
        HashFunction hashFunction = Hashing.murmur3_128();
        return hashFunction.hashUnencodedChars(str).padToLong();
    }

}
