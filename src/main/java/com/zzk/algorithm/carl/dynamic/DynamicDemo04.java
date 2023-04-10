package com.zzk.algorithm.carl.dynamic;

/**
 * 动态规划
 * 子序列问题
 */
public class DynamicDemo04 {

    //子序列 - 不连续
    /**
     * 1、最长递增子序列
     * 给你一个整数数组 `nums` ，找到其中最长严格递增子序列的长度。
     * **子序列** 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，`[3,6,2,7]` 是数组 `[0,3,1,6,2,2,7]` 的子序列。
     *
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     */
    /**
     * 动态规划
     * 思路：
     * dp[i] 表示 i 之前包括 i 的 以 nums[i] 结尾最长上升子序列的长度
     * 当 nums[i] > nums[j]，dp[i] = Math.max(dp[i], dp[j]+1)
     *
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(n)
     */
    public int lengthOfLIS(int[] nums){
        if(nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for(int i=1; i<nums.length; i++){
            dp[i] = 1;
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            System.out.println(dp);
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     * 2、最长公共子序列
     * 给定两个字符串 `text1` 和 `text2`，返回这两个字符串的最长 **公共子序列** 的长度。如果不存在 **公共子序列** ，返回 `0` 。
     * 一个字符串的 **子序列** 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * - 例如，`"ace"` 是 `"abcde"` 的子序列，但 `"aec"` 不是 `"abcde"` 的子序列。
     * 两个字符串的 **公共子序列** 是这两个字符串所共同拥有的子序列。
     *
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     *
     * 动态规划
     * 定义：dp[i] [j] 长度为[0，i-1] 的字符串 text1 与 长度为 [0, j -1]的字符串 text2 的最长公共子序列为 dp[i] [j]
     * 公式
     *  text[i-1]和 text[j]相同
     *      dp[i][j] = dp[i-1][j-1]+1
     *  text[i-1]和 text[j]不相同，比较 text[0, i-1],text[0, j-1]的最长公共子序列长度，查找最大
     *      dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     *
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     */
    public int longestCommonSubSequence(String text1, String text2){
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i=1; i<=text1.length(); i++){
            char char1 = text1.charAt(i-1);
            for(int j=1; j<text2.length(); j++){
                char char2 = text2.charAt(j-1);
                if(char1 == char2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
    /**
     * 3、不相交的线
     * 在两条独立的水平线上按给定的顺序写下 `nums1` 和 `nums2` 中的整数。
     * 现在，可以绘制一些连接两个数字 `nums1[i]` 和 `nums2[j]` 的直线，这些直线需要同时满足满足：
     * -  `nums1[i] == nums2[j]`
     * -  且绘制的直线不与任何其他连线（非水平线）相交。
     * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
     * 以这种方法绘制线条，并返回可以绘制的最大连线数。
     *
     * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
     * 输出：2
     * 解释：可以画出两条不交叉的线，如上图所示。
     * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交
     *
     * 转换为求两个字符串的最长公共子序列长度
     */
    public int maxUncrossedLines(int[] A, int[] B){
        int[][] dp = new int[A.length+1][B.length+1];
        for(int i=1; i<=A.length; i++){
            for(int j=1; j<=B.length; j++){
                if(A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[A.length][B.length];
    }

    //子序列 - 连续
    /**
     * 4、最长连续递增序列
     * 给定一个未经排序的整数数组，找到最长且 **连续递增的子序列**，并返回该序列的长度。
     * **连续递增的子序列** 可以由两个下标 `l` 和 `r`（`l < r`）确定，如果对于每个 `l <= i < r`，都有 `nums[i] < nums[i + 1]` ，
     * 那么子序列 `[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]` 就是连续递增子序列
     *
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     *
     * 动态规划
     *
     * dp[i] 以下标为i为结尾的数组的连续递增的子序列长度为 dp[i]
     * dp[i+1] = dp[i]+1
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int findLengthOfLCIS(int[] nums){
        int[] dp = new int[nums.length];
        for(int i=0; i<dp.length; i++){
            dp[i] = 1;
        }
        int res = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i+1] > nums[i]){
                dp[i+1] = dp[i] + 1;
            }
            res = Math.max(res, dp[i + 1]);
        }
        return res;
    }

    /**
     * 贪心算法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int findLengthOfLCIS2(int[] nums){
        if(nums.length == 0){
            return 0;
        }
        int res = 1;
        int count = 1;
        for(int i=0; i<nums.length-1; i++){
            if(nums[i+1] > nums[i]){
                count++;
            }else{
                count=1;
            }
            if(count > res){
                res = count;
            }
        }
        return res;
    }

    /**
     * 5、最长重复子数组
     * 给两个整数数组 `nums1` 和 `nums2` ，返回 *两个数组中 **公共的** 、长度最长的子数组的长度*
     *
     * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * 输出：3
     * 解释：长度最长的公共子数组是 [3,2,1] 。
     *
     * 动态规划
     * 定义：
     * dp[i] [j] 以下标 i-1 为结尾的A，和以下标为 j-1 为结尾的B，最长重复子数组长度为 dp[i] [j]
     * 公式：
     * dp[i] [j] = dp[i-1] [j-1] + 1
     *
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     */
    public int findLength(int[] nums1, int[] nums2){
        int result = 0;
        int[][] dp = new int[nums1.length+1][nums2.length+1];

        for(int i=1; i<nums1.length; i++){
            for(int j=1; j<nums2.length; j++){
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    /**
     * 6、最大子数组和
     * 给你一个整数数组 `nums` ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * **子数组** 是数组中的一个连续部分
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    /**
     * 动态规划
     * 递推
     *   - dp[i-1] + nums[i]，加入当前连续子序列的和
     *   - nums[i]，从头开始计算当前连续子序列的和
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int maxSubArray(int[] nums){
        if(nums.length == 0){
            return 0;
        }
        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //判断距离
    /**
     * 7、判断子序列
     * 给定字符串 **s** 和 **t** ，判断 **s** 是否为 **t** 的子序列。
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，`"ace"`是`"abcde"`的一个子序列，而`"aec"`不是）
     */
    /**
     * 动态规划
     * 定义
     * dp[i][j] 表示以下标为 i-1 为结尾的字符串 s 和 以下标为 j-1 为结尾的字符串t，相同子序列的长度为 dp[i][j]
     * 递推
     *  - s[i] = t[i] -> dp[i][j] = s[i-1][j-1] + 1
     *  - s[i] != t[i] -> dp[i][j] = s[i][j-1]（相当于t删除了一个元素继续匹配）
     *
     *  时间复杂度：O(length1 * length2)
     *  空间复杂度：O(length1 * length2)
     */
    public boolean isSubsequence(String s, String t){
        int length1 = s.length();
        int length2 = t.length();
        int[][] dp = new int[length1+1][length2+1];
        for(int i=1; i<=length1; i++){
            for(int j=1; j<=length2; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        if(dp[length1][length2] == length1){
            return true;
        }
        return false;
    }

    /**
     * 8、不同的子序列
     * 给定一个字符串 `s` 和一个字符串 `t` ，计算在 `s` 的子序列中 `t` 出现的个数。
     * 字符串的一个 **子序列** 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，`"ACE"` 是 `"ABCDE"` 的一个子序列，而 `"AEC"` 不是）
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     * 输入：s = "rabbbit", t = "rabbit"
     * 输出：3
     * 解释：
     * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
     * rabbbit
     * rabbbit
     * rabbbit
     */
    /**
     * 动态规划
     * 定义
     * dp[i][j]：以 i-1 为结尾的s子序列中出现以 j-1 为结尾的t的个数为 dp[i][j]
     * 递推公式
     * s[i-1] = t[j-1]
     *   一部分用 s[i-1]匹配，个数为dp[i-1][j-1]
     *   一部分不用 s[i-1]匹配，个数为 dp[i-1][j]
     *   即 dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
     * s[i-1] != t[j-1]
     *   只有部分组成，不用 s[i-1]匹配，dp[i-1][j]
     */
    public int numDistinct(String s, String t){
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i=0; i<=s.length(); i++){
            dp[i][0] = 1;
        }

        for(int i=1; i<=s.length(); i++){
            for(int j=1; j<=t.length(); j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }


    /**
     * 9、两个字符串的删除操作
     * 给定两个单词 `word1` 和 `word2` ，返回使得 `word1` 和 `word2` **相同**所需的**最小步数**。
     * **每步** 可以删除任意一个字符串中的一个字符
     *
     * 输入: word1 = "sea", word2 = "eat"
     * 输出: 2
     * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
     *
     * dp[i] [j] 是以 i-1 结尾的字符串 word1和以 j-1 结尾的字符串 word2，达到相等时所需要删除元素的最少次数
     *
     * 递推公式：
     *
     * - word1[i-1] == word2[j-1]
     *   - dp[i] [j] = dp[i-1] [j-1] + 1
     * - word1[i-1] != word2[j-1]，有三种情况
     *   - 删除 word1[i-1]，dp[i-1] [j] + 1
     *   - 删除 word2[j-1]，do[i] [j-1] + 1
     *   - 同时删除 word1[i-1] 和 word2[j-1]，dp[i-1] [j-1] + 2
     *   - 三种情况取最小的操作，dp[i] [j] = Math.min(dp[i - 1] [j - 1] + 2, Math.min(dp[i - 1] [j] + 1, dp[i] [j - 1] + 1));
     */
    public int minDistance(String word1, String word2){
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i=0; i<word1.length()+1; i++){
            dp[i][0] = i;
        }
        for(int j=0; j<word2.length()+1; j++){
            dp[0][j] = j;
        }

        for(int i=1; i<word1.length()+1; i++){
            for(int j=1; j<word2.length()+1; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1]+2,
                            Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    /**
     * 10、编辑距离
     * 给你两个单词 `word1` 和 `word2`， *请返回将 `word1` 转换成 `word2` 所使用的最少操作数* 。
     * 你可以对一个单词进行如下三种操作：
     * - 插入一个字符
     * - 删除一个字符
     * - 替换一个字符
     *
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     *
     * if (word1[i - 1] == word2[j - 1])
     *     不操作
     * if (word1[i - 1] != word2[j - 1])
     *     增
     *     删
     *     换
     */
    public int minDistance2(String word1, String word2){
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1+1][n2+1];
        for(int j=1; j<=n2; j++){
            dp[0][j] = j;
        }
        for(int i=1; i<=n1; i++){
            dp[i][0] = i;
        }
        for(int i=1; i<=n1; i++){
            for(int j=1; j<=n2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
                }
            }
        }
        return dp[n1][n2];
    }

    //回文
    /**
     * 11、回文子串
     * 给你一个字符串 `s` ，请你统计并返 回这个字符串中 **回文子串** 的数目。
     * **回文字符串** 是正着读和倒过来读一样的字符串。
     * **子字符串** 是字符串中的由连续字符组成的一个序列。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     *
     * 输入：s = "abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     */
    /**
     * 动态规划
     * 复杂度
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    public int countSubStrings(String s){
        int len, ans = 0;
        if(s == null || (len = s.length()) < 1){
            return 0;
        }
        boolean[][] dp = new boolean[len][len];
        for(int j=0; j<len; j++){
            for(int i=0; i<=j; i++){
                if(s.charAt(i) == s.charAt(j)){
                    if(j-i < 3){
                        dp[i][j] = true;
                    }else{
                        //收缩字符判断
                        dp[i][j] = dp[i+1][j-1];
                    }
                }else{
                    //不是回文字符串
                    dp[i][j] = false;
                }
            }
        }
        //遍历每一个字符串，统计回文个数
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                if(dp[i][j]){
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 12、最长回文子串
     * 给你一个字符串 `s`，找到 `s` 中最长的回文子串
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     */
    /**
     * 动态规划
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N62)
     */
    public String longestPalindrome(String s){
        int len = s.length();
        if(len < 2){
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for(int i=0; i<len; i++){
            dp[i][i] = true;
        }

        for(int j=1; j<len; j++){
            for(int i=0; i<j; i++){
                if(charArray[i] != charArray[j]){
                    dp[i][j] = false;
                }else{
                    if(j-i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if(dp[i][j] && j-i+1 > maxLen){
                    maxLen = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * Manacher算法（马拉算法）
     * 时间复杂度：O(N)
     */
    public int maxLcpsLength(String str){
        if(str == null || str.length() == 0){
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MAX_VALUE;
        for(int i=0; i!=charArr.length; i++){
            pArr[i] = pR > i ? Math.min(pArr[2 * index - 1], pR - 1) : 1;
            while(i + pArr[i] < charArr.length && i - pArr[i] > -1){
                if(charArr[i+pArr[i]] == charArr[i-pArr[i]]) {
                    pArr[i]++;
                }else{
                    break;
                }
            }

            if(i + pArr[i] > pR){
                pR = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max-1;
    }

    public char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for(int i=0; i != res.length; i++){
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    /**
     * 13、最长回文子序列
     * 给你一个字符串 `s` ，找出其中最长的回文子序列，并返回该序列的长度。
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     *
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     */
    /**
     * 动态规划
     * dp[i][j]表示字符串s在[i,j]范围内最长的回文子序列的长度为 dp[i][j]
     * s[i] == s[j] -> dp[i][j] = dp[i+1][j-1] + 2
     * s[i] != s[j] -> dp[i] [j] = Math.max(dp[i+1] [j], Math.max(dp[i] [j], dp[i] [j-1]))
     */
    public int longestPalindromeSubseq(String s){
        int len = s.length();
        int[][] dp = new int[len+1][len+1];
        for(int i=len-1; i>=0; i--){
            dp[i][i] = 1;
            for(int j=i+1; j<len; j++){
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], Math.max(dp[i][j], dp[i][j-1]));
                }
            }
        }
        return dp[0][len-1];
    }

}
