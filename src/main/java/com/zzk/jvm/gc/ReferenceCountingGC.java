package com.zzk.jvm.gc;

/**
 * @program: coding
 * @description: 两个对象相互引用
 * @author: zzk
 * @create: 2022-01-26 22:59
 */
public class ReferenceCountingGC {

    static class ReferenceCountGCObj {

        private static final int _1MB = 1024 * 1024;

        public ReferenceCountGCObj instance;
    }

    public static void main(String[] args) {
        ReferenceCountGCObj referenceCountGCObjA = new ReferenceCountGCObj();
        ReferenceCountGCObj referenceCountGCObjB = new ReferenceCountGCObj();

        referenceCountGCObjA.instance = referenceCountGCObjB;
        referenceCountGCObjB.instance = referenceCountGCObjA;

        referenceCountGCObjA = null;
        referenceCountGCObjB = null;

        System.gc();
    }
}
