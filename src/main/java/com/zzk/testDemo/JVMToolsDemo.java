package com.zzk.testDemo;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description TODO
 * @Author zzk
 * @Dare 2024/1/22
 **/
public class JVMToolsDemo {


    /**
     * 启动10个死循环线程，每个线程分配一个 10MB左右的字符串，然后休眠10s
     */
    @Test
    public void test() throws Exception{
        IntStream.rangeClosed(1, 10).mapToObj( i -> new Thread(() -> {
            while (true){
                //每个线程都是一个死循环，休眠10s.打印10M的数据
                String payload = IntStream.rangeClosed(1, 100000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();
                try{
                    TimeUnit.SECONDS.sleep(10);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                System.out.println(payload.length());
            }
        })).forEach(Thread::start);
        TimeUnit.HOURS.sleep(1);
    }
}
