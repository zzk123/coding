package com.zzk.javaversion;

import org.junit.Before;

public class Test {

    private String str;
    @Before
    public void init(){
        str = "已经初始化了！！！！";
    }
    @org.junit.Test
    public void testThis(){
        System.out.println(Test.this.str);
    }
}
