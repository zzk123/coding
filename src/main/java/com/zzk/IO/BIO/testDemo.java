package com.zzk.IO.BIO;

import java.io.*;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-06 11:57
 */
public class testDemo {

    /**
     * 递归列出目录下所有文件
     * @param dir
     */
    public static void listAllFile(File dir){
        if(dir == null || !dir.exists()){
            return;
        }
        if(dir.isFile()){
            System.out.println(dir.getName());
        }
        for(File file : dir.listFiles()){
            listAllFile(file);
        }
    }

    /**
     * 字节流复制文件
     * @param src
     * @param dist
     * @throws IOException
     */
    public static void copyFile(String src, String dist) throws IOException{
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dist);
        byte[] buffer = new byte[20 * 1024];

        while(in.read(buffer, 0, buffer.length) != -1){
            out.write(buffer);
        }

        in.close();
        out.close();
    }


    /**
     * 逐行输出文本文件的内容
     * @param filePath
     * @throws IOException
     */
    public static void readFileContent(String filePath) throws IOException{

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }

        bufferedReader.close();
    }


}
