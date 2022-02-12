package com.zzk.jvm.gc;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-01-26 23:07
 */
public class FinializeEscapeGC {

    public static FinializeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("活着.......");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("触发finalize调用");
        FinializeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Exception {

        SAVE_HOOK = new FinializeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();

        Thread.sleep(500);

        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("死了......");
        }

        //自救失败，因为finalize调用只会调用一次
        SAVE_HOOK = null;
        System.gc();

        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("死了......");
        }
    }
}
