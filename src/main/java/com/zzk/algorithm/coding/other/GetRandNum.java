package com.zzk.algorithm.coding.other;

/**
 * @ClassName GetRamdNum
 * @Description 1.从5随机到7及其扩展
 * @Author zzk
 * @Date 2021/2/21 15:26
 **/
public class GetRandNum {

    /**
     * 原题目 ： 实现随机产生 1 ~ 7的随机函数
     * @return
     */

    public int rand1To5(){
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 1.rand1To5() 等概率随机产生 1，2，3，4，5
     * 2.rand1To5()-1 等概率随机产生0，1，2，3，4
     * 3.(rand1To5()-1)*5 等概率随机产生0，5，10，15，20
     * 4.(rand1To5()-1)*5+(rand1To5()-1) 等概率随机产生 0~24的数值
     * 5.筛选0~20的结果 num，进行%7操作，就会等概率的随机产生 0~6
     * @return
     */
    public int rand1To7(){
        int num = 0;
        do {
           num = (rand1To5() - 1) * 5 + rand1To5() - 1;
        } while (num  > 20);
        return num % 7 + 1;
    }


    /**
     * 补充问题；实现随机产生1~6的随机函数
     * @return
     */

    /**
     * p概率产生0，1-p概率产生1
     * @return
     */
    public int rand01p(){
        double p = 0.83;
        return Math.random() < p ? 0 : 1;
    }

    /**
     * 等概率随机产生0和1
     * P(0)=p      P‘(0)=p
     * P(1)=1-p    P’(1)=1-p
     *
     * 00=p*p 01=p*(1-p)
     * 10=p*(1-p)  11=(1-p)*(1-p)
     * @return
     */
    public int rand01(){
        int num;
        do{
            num = rand01p();
        }while (num == rand01p());
        return num;
    }

    /**
     * rand01() 等概率产生 0,1
     * rand01()*2 等概率产生 0,2
     * rand01() * 2 + rand01() 等概率产生 0,1,2,3
     * @return
     */
    public int rand0To3(){
        return rand01() * 2 + rand01();
    }

    /**
     * rand0To3() * 4 + rand0To3() 等概率产生0~15
     * @return
     */
    public int rand1To6(){
        int num = 0;
        do{
            num = rand0To3() * 4 + rand0To3();
        }while(num > 11);
        return num % 6 + 1;
    }


    /**
     * 进阶问题：输入m和n，实现用rand1ToM(m)随机产生1~n的随机函数
     */
    public int rand1ToM(int m){
        return (int) (Math.random() * m) + 1;
    }

    public int rand1ToN(int n, int m){
        int[] nMSys = getMSysNum(n-1, m);
        int[] randNum = getRanMSysNumLessN(nMSys, m);
        return getNumFromMSysNum(randNum, m) + 1;
    }

    /**
     * 将value转成m进制数
     * @param value
     * @param m
     * @return
     */
    public int[] getMSysNum(int value, int m){
        int[] res =  new int[32];
        int index = res.length - 1;
        while(value != 0){
            res[index--] = value % m;
            value = value / m;
        }
        return res;
    }

    /**
     * 等概率随机产生一个0~nMsys范围的数，只不过用m进制表达
     * @param nMSys
     * @param m
     * @return
     */
    public int[] getRanMSysNumLessN(int[] nMSys, int m){
        int[] res = new int[nMSys.length];
        int start = 0;
        while(nMSys[start] == 0){
            start++;
        }
        int  index = start;
        boolean lastEqual = true;
        while(index != nMSys.length){
            res[index] = rand1ToM(m) - 1;
            if(lastEqual){
                if(res[index] > nMSys[index]){
                    index = start;
                    lastEqual = true;
                    continue;
                }else{
                    lastEqual = res[index] == nMSys[index];
                }
            }
            index++;
        }
        return res;
    }

    /**
     * 把m进制数转成十进制数
     * @param mSysnNum
     * @param m
     * @return
     */
    public int getNumFromMSysNum(int[] mSysnNum, int m){
        int res = 0;
        for(int i = 0; i != mSysnNum.length; i++){
            res = res * m + mSysnNum[i];
        }
        return res;
    }
}
