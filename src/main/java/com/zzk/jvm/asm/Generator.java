package com.zzk.jvm.asm;

import java.io.File;
import java.io.FileOutputStream;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;

/**
 * @Description 利用 ASM 实现 AOP
 * @Author zzk
 * @Dare 2023/5/18
 **/
public class Generator {

    public static void main(String[] args) throws Exception {
        //读取
        ClassReader classReader;
        classReader = new ClassReader("com/zzk/jvm/asm/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //处理
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        //输出
        File f = new File("D:\\work\\workspace\\test\\coding\\target\\classes\\com\\zzk\\jvm\\asm\\Base.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!!");
    }
}
