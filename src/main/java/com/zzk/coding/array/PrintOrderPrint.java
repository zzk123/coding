package com.zzk.coding.array;

/**
 * @ClassName PrintOrderPrint
 * @Description 1.转圈打印矩阵
 * @Author zzk
 * @Date 2020/12/13 15:47
 **/
public class PrintOrderPrint {

    public void spiralOrderPrint(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while(tR <= dR && tC <= dC){
            prictEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public void prictEdge(int[][] m, int tR, int tC, int dR, int dC){
        if(tR == dR){
            for(int i = tC; i <= dC; i++){
                System.out.println(m[tR][i] + "  ");
            }
        }else if(tC == dC){
            for(int i = tR; i <= dR; i++){
                System.out.println(m[i][tC] + "  ");
            }
        }else{
            int curC = tC;
            int curR = tR;
            while(curC != dC){
                System.out.println(m[tR][curC] + "  ");
                curC++;
            }
            while(curR != dR){
                System.out.println(m[curR][dC] + "  ");
                curR++;
            }
            while(curC != tC){
                System.out.println(m[dR][curC] + "  ");
                curC--;
            }
            while(curR != tR){
                System.out.println(m[curR][tC] + "  ");
                curR--;
            }
        }
    }
}
