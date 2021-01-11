package com.zzk.coding.array;

/**
 * @ClassName Contains
 * @Description 7.在行列都排好序的矩阵中找数
 * @Author zzk
 * @Date 2021/1/4 22:26
 **/
public class Contains {

    /**
     * 时间复杂度为O(N+M)，空间复杂度为O(1)
     * @param matrix
     * @param K
     * @return
     */
    public boolean isContains(int[][] matrix, int K){
        int row = 0;
        int col = matrix[0].length - 1;
        while(row < matrix.length && col > -1){
            if(matrix[row][col] == K){
                return true;
            }else if(matrix[row][col] > K){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }
}
