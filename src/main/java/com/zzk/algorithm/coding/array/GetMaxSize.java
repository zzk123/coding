package com.zzk.algorithm.coding.array;

/**
 * @ClassName GetMaxSize
 * @Description TODO
 * @Author zzk
 * @Date 2021/2/4 22:15
 **/
public class GetMaxSize {

    /**
     * 时间复杂度为O(N^3)
     * @param m
     * @param right
     * @param down
     */
    public void setBorderMap(int[][]  m, int[][] right, int[][] down){
        int r = m.length;
        int c = m[0].length;
        if(m[r-1][c-1] == 1){
            right[r-1][c-1] = 1;
            down[r-1][c-1] = 1;
        }
        for(int i = r - 2; i != -1; i--){
            if(m[i][c - 1] == 1){
                right[i][c-1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for(int i = c - 2; i != -1; i--){
            if(m[r - 1][i] == 1){
                right[r - 1][i] = right[r - 1][ i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        for(int i = r - 2; i != -1; i--){
            for(int j = c - 2; j != -1; j--){
                if(m[i][j] == 1){
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    public int getMaxSize(int[][] m){
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
        for(int size = Math.min(m.length, m[0].length); size != 0; size--){
            if(hasSizeOfBorder(size, right, down)){
                return size;
            }
        }
        return 0;
    }

    public boolean hasSizeOfBorder(int size, int[][] right, int[][] down){
        for(int i = 0; i != right.length - size +  1; i++){
            for(int j = 0; j != right[0].length - size + 1; j++){
                if(right[i][j] >= size && down[i][j] >= size
                        && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size){
                    return true;
                }
            }
        }
        return false;
    }
}
