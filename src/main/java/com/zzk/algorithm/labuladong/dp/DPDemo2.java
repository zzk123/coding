package com.zzk.algorithm.labuladong.dp;

/**
 * @program: coding
 * @description:
 *  经典动态规划：编辑距离
 *
 * @author: zzk
 * @create: 2022-03-06 23:43
 */
public class DPDemo2 {

    static class Node{
        int val;
        int choice;
        Node(int val, int choice){
            this.val = val;
            this.choice = choice;
        }
    }

    /**
     * 动态规划化
     * @param str1
     * @param str2
     * @return
     */
    public static int minDistance(String str1, String str2){
        int m = str1.length();
        int n = str2.length();
        Node[][] dp = new Node[m+1][n+1];

        for(int i=0; i<= m; i++){
            dp[i][0] = new Node(i, 2);
        }

        for(int j=0; j<= n; j++){
            dp[0][j] = new Node(j, 2);
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    Node node = dp[i-1][j-1];
                    dp[i][j] = new Node(node.val, 0);
                }else{
                    dp[i][j] = minNode(
                            dp[i-1][j],
                            dp[i][j-1],
                            dp[i-1][j-1]
                    );
                    dp[i][j].val++;
                }
            }
        }
        printResult(dp, str1, str2);
        return dp[m][n].val;
    }

    /**
     * 输出打印
     * @param dp
     * @param s1
     * @param s2
     */
    public static void printResult(Node[][] dp, String s1, String s2){
        int rows = dp.length;
        int cols = dp[0].length;
        int i = rows - 1, j = cols - 1;
        System.out.println("Change s1=" + s1 + "  to s2=" + s2 + ":\n");
        while(i != 0 && j != 0) {
            char c1 = s1.charAt(i-1);
            char c2 = s2.charAt(j-1);
            int choice = dp[i][j].choice;
            System.out.print("s1[" + (i - 1) + "]:");
            switch (choice) {
                case 0:
                    System.out.println("skip '" + c1 + "'");
                    i--;
                    j--;
                    break;
                case 1:
                    System.out.println("insert '" + c2 + "'");
                    j--;
                    break;
                case 2:
                    System.out.println("delete '" + c1 + "'");
                    i--;
                    break;
                case 3:
                    System.out.println("replace '" + c1 + "'" + " with '" + c2 + "'");
                    i--;
                    j--;
                    break;
            }
        }

        while(i>0){
            System.out.print("s1[" + (i-1) + "]:");
            System.out.println("delete '" + s1.charAt(i-1) + "'");
            i--;
        }

        while(j>0){
            System.out.print("s1[0]:");
            System.out.println("insert '" + s2.charAt(j -1) + "'");
            j--;
        }

    }

    /**
     * 计算 delete、insert、replace中代价最小的操作
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static Node minNode(Node a, Node b, Node c){
        Node res = new Node(a.val, 2);
        if(res.val > b.val){
            res.val = b.val;
            res.choice = 1;
        }
        if(res.val < c.val){
            res.val = c.val;
            res.choice = 3;
        }
        return res;
    }


    public static void main(String[] args) {
        String str1 = "intention";
        String str2 = "execution";
        minDistance(str1, str2);
    }
}
