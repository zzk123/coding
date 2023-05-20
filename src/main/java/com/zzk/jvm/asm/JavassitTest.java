package com.zzk.jvm.asm;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @Description 使用 javassist 字节码框架实现 aop
 * @Author zzk
 * @Dare 2023/5/18
 **/
public class JavassitTest {

    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.zzk.jvm.asm.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end\"); }");
        Class c = cc.toClass();
        cc.writeFile("C:\\Users\\Administrator\\Desktop");
        Base h = (Base) c.newInstance();
        h.process();
    }
}
