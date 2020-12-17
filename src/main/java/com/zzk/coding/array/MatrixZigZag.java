package com.zzk.coding.array;

/**
 * @ClassName MatrixZigZag
 * @Description 3.“之”字形打印矩阵
 * @Author zzk
 * @Date 2020/12/17 22:11
 **/
public class MatrixZigZag {

    public void printMatrixZigZag(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while(tR != endR + 1){
            printLevel(matrix, tR, tC, tR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp =! fromUp;
        }
        System.out.println();
    }

    public void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f){
        if(f){
            while(tR != dR + 1){
                System.out.print(m[tR++][tC--] + " ");
            }
        }else{
            while(dR != tR - 1){
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }
}
