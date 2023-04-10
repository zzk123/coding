package com.zzk.algorithm.carl.array;

public class ModifyInSpace {

    /**
     * 1、移除元素
     * 给你一个数组 `nums` 和一个值 `val`，你需要 **[原地](https://baike.baidu.com/item/原地算法)** 移除所有数值等于 `val` 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 `O(1)` 额外空间并 **[原地 ](https://baike.baidu.com/item/原地算法)修改输入数组**。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素
     *
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
     */
    /**
     * 暴力解决
     * 时间 O(n^2)
     * 空间 O(1)
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val){
        int size = nums.length;
        for(int i = 0; i < size; i++){
            if(nums[i] == val){
                for(int j = i + 1; j < size; j++){
                    nums[j-1] = nums[j];
                }
                i--;
                size--;
            }
        }
        return size;
    }

    /**
     * 双指针
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val){
        int fastIndex = 0;
        int slowIndex = 0;
        for(slowIndex = 0; fastIndex < nums.length; fastIndex++){
            if(nums[fastIndex] != val){
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param nums
     * @param val
     * @return
     */
    public int removeElement3(int[] nums, int val){
        int left = 0;
        int right = nums.length;
        while(left < right){
            if(nums[left] == val){
                nums[left] = nums[right-1];
                right--;
            }else{
                left++;
            }
        }
        return left;
    }

    /**
     * 2、删除有序数组中的重复项
     * 给你一个 **升序排列** 的数组 `nums` ，请你**[ 原地](http://baike.baidu.com/item/原地算法)** 删除重复出现的元素，使每个元素 **只出现一次** ，返回删除后数组的新长度。元素的 **相对顺序** 应该保持 **一致** 。
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 `k` 个元素，那么 `nums` 的前 `k` 个元素应该保存最终结果。
     * 将最终结果插入 `nums` 的前 `k` 个位置后返回 `k` 。
     * 不要使用额外的空间，你必须在 **[原地 ](https://baike.baidu.com/item/原地算法)修改输入数组** 并在使用 O(1) 额外空间的条件下完成。
     *
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2,_]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素
     */
    public int removeDuplicates(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                 nums[p+1] = nums[q];
                 p++;
            }
            q++;
        }
        return p+1;
    }

    /**
     * 双指针优化
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                if(q - p > 1){
                    nums[p+1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p+1;
    }

    /**
     * 3、移动零
     * 给定一个数组 `nums`，编写一个函数将所有 `0` 移动到数组的末尾，同时保持非零元素的相对顺序。
     * **请注意** ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     */
    /**
     * 两次遍历
     */
    public void moveZeros(int[] nums){
        if(nums == null){
            return;
        }
        int j = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[j++] = nums[i];
            }
        }

        for(int i = j; i <nums.length; i++){
            nums[i] = 0;
        }
    }

    /**
     * 一次遍历
     * @param nums
     */
    public void moveZeros1(int[] nums){
        if(nums == null){
            return;
        }
        int j = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

    public void moveZeros2(int[] nums){
        int length;
        if(nums == null || (length = nums.length) == 0){
            return ;
        }
        int j = 0;
        for(int i = 0; i < length; i++){
            if(nums[i] != 0){
                if(i > j){
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}
