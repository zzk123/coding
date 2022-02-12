package com.zzk.algorithm.coding.array;

/**
 * @ClassName Rotate
 * @Description 2.将正方形矩阵顺时针
 * @Author zzk
 * @Date 2020/12/17 21:53
 **/
public class Rotate {

    public void retate(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while(tR < dR){
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public void rotateEdge(int[][] m, int tR, int tC, int dR, int dC){
        int times = dC - tC;
        int tmp = 0;
        for(int i = 0; i != times; i++){
            tmp = m[tR][tC + i];
            m[tR][tC + i] = m[dR - i][tC];
            m[dR - i][tC] = m[tR][dC - i];
            m[dR][dC - i] = m[tR + i][dC];
            m[tR + i][dC] = tmp;
        }
    }
}
