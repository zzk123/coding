package com.zzk.concurrent.stampedLock;

import java.util.concurrent.locks.StampedLock;

/**
 * @ClassName StampedLock
 * @Description StampedLockDemo
 * @Author zzk
 * @Date 2023/4/15 17:28
 **/
public class StampedLockDemo {

    class Point{
        private double x, y;
        private final StampedLock sl = new StampedLock();
        void move(double deltaxX, double deltaY){
            long stamp = sl.writeLock();
            try{
                x += deltaxX;
                y += deltaY;
            }finally {
                sl.unlockWrite(stamp);
            }
        }
        //乐观读锁
        double distanceFromOrigin(){
            //获取乐观读锁
            long stamp = sl.tryOptimisticRead();
            double currentX = x, currentY = y;
            //校验是否有其他写锁发生
            if(!sl.validate(stamp)){
                //没有就获取一个读悲观锁
                stamp = sl.readLock();
                try{
                    currentX = x;
                    currentY = y;
                }finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }

        //悲观读锁
        void moveIfAtOrigin(double newX, double newY){
            long stamp = sl.readLock();
            try{
                //循环校验状态
                while(x == 0.0 && y == 0.0){
                    //读锁转换为写锁
                    long ws = sl.tryConvertToWriteLock(stamp);
                    if(ws != 0L){
                        stamp = ws;
                        x = newX;
                        y = newY;
                        break;
                    }else{
                        //释放读锁
                        sl.unlockRead(stamp);
                        //直接进行写锁，然后循环尝试
                        stamp = sl.writeLock();
                    }
                }
            }finally {
                sl.unlock(stamp);
            }
        }
    }


}
