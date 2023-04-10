package com.zzk.algorithm.carl.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 棋盘问题
 * 回溯算法
 */
public class BackTrackChessBoardDemo {

    /**
     * 1、N 皇后
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     * **n 皇后问题** 研究的是如何将 `n` 个皇后放置在 `n×n` 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 `n` ，返回所有不同的 **n 皇后问题** 的解决方案。
     * 每一种解法包含一个不同的 **n 皇后问题** 的棋子放置方案，该方案中 `'Q'` 和 `'.'` 分别代表了皇后和空位。
     *
     * 输入：n = 4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * 解释：如上图所示，4 皇后问题存在两个不同的解法
     */
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> solveNQueens(int n){
        char[][] chessboard = new char[n][n];
        for(char[] c : chessboard){
            Arrays.fill(c, '.');
        }
        backTrack(n, 0, chessboard);
        return res;
    }

    public void backTrack(int n, int row, char[][] chessboard){
        if(row == n){
            res.add(Array2List(chessboard));
            return;
        }
        for(int col = 0; col < n; col++){
            if(isValid(row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backTrack(n, row + 1, chessboard);
                chessboard[row][col] = '.';
            }
        }
    }

    public boolean isValid(int row, int col, int n, char[][] chessboard){
        for(int i=0; i<row; i++){
            if(chessboard[i][col] == 'Q'){
                return false;
            }
        }
        //左斜线
        for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--){
            if(chessboard[i][j] == 'Q'){
                return false;
            }
        }
        //右斜线
        for(int i=row-1, j=col+1; i>=0 && j<n-1; i--,j++){
            if(chessboard[i][j] == 'Q'){
                return false;
            }
        }

        return true;
    }

    public List Array2List(char[][] chessboard){
        List<String> list = new ArrayList<>();
        for(char[] c : chessboard){
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    /**
     * 2、解数独
     * 编写一个程序，通过填充空格来解决数独问题。
     * 数独的解法需 **遵循如下规则**：
     * 1. 数字 `1-9` 在每一行只能出现一次。
     * 2. 数字 `1-9` 在每一列只能出现一次。
     * 3. 数字 `1-9` 在每一个以粗实线分隔的 `3x3` 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 `'.'` 表示
     *
     * 输入：board =
     * [
     * ["5","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]]
     * 输出：
     * [
     * ["5","3","4","6","7","8","9","1","2"],
     * ["6","7","2","1","9","5","3","4","8"],
     * ["1","9","8","3","4","2","5","6","7"],
     * ["8","5","9","7","6","1","4","2","3"],
     * ["4","2","6","8","5","3","7","9","1"],
     * ["7","1","3","9","2","4","8","5","6"],
     * ["9","6","1","5","3","7","2","8","4"],
     * ["2","8","7","4","1","9","6","3","5"],
     * ["3","4","5","2","8","6","1","7","9"]
     * ]
     * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
     */

    public void solveSudoku(char[][] board){
        solveSudokuHelper(board);
    }

    private boolean solveSudokuHelper(char[][] board){
        //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
        // 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！」
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] != '.'){
                    continue;
                }
                for(char k='1'; k <= '9'; k++){ // (i, j) 这个位置放k是否合适
                    if(isValidSudoku(i, j, k, board)){
                        board[i][j] = k;
                        if(solveSudokuHelper(board)){ // 如果找到合适一组立刻返回
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValidSudoku(int row, int col, char val, char[][] board){
        // 同行是否重复
        for(int i=0; i<9; i++){
            if(board[row][i] == val){
                return false;
            }
        }
        // 同列是否重复
        for(int j=0; j<9; j++){
            if(board[j][col] == val){
                return false;
            }
        }
        // 9宫格里是否重复
        int startRow = (row/3) * 3;
        int startCol = (col/3) * 3;
        for(int i=startRow; i<startRow+3; i++){
            for(int j = startCol; j < startCol+3; j++){
                if(board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
}
