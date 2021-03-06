# 数组和矩阵问题
## 1.转圈打印矩阵
### 题目
给定一个整型矩阵 matrix,请按照转圈的方式打印它
例如：
    1   2   3   4  
    5   6   7   8
    9   10  11  12
    13  14  15  16
 打印结果为：1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
 
### 要求
额外空间复杂度为O(1)

## 2.将正方形矩阵顺时针
### 题目
给定一个N*N的矩阵matrix，把这个矩阵调整成顺时针转动90度后的形式
例如：
    1   2   3   4  
    5   6   7   8 
    9   10  11  12 
    13  14  15  16
顺时针转动90度后为
    13  9   5   1   
    14  10  6   2   
    15  11  7   3   
    16  12  8   4
    
### 要求
额外空间复杂度为O(1)

## 3.“之”字形打印矩阵
### 题目
给定一个矩阵matrix，按照“之”字形的方式打印这个矩阵，例如
1   2   3   4
5   6   7   8
9   10  11  12
“之”字形打印的结果为：1，2，5，9，6，3，4，7，10，11，8，12
### 要求
空间复杂度为O(1)

## 4.找到无序数组中最小的k个数
### 题目
给定一个无序的整型数组arr，找到其中最小的k个数

### 要求
如果数组arr的长度为N，排序之后自然可以得到最小的k个数，此时时间复杂度与排序的时间复杂度相同，均为O(NlogN).本题要求读者实现时间复杂度为O(Nlogk)
和O(N)的方法

## 5.需要排序的最短子数组长度
### 题目
给定一个无序数组arr，求出需要排序的最短子数组长度
例如：arr=[1,5,3,4,2,6,7]返回4，因为只有[5,3,4,2]需要排序

## 6.在数组中找到出现次数大于N/K的数
### 题目
给定一个整型数组arr，打印其中出现次数大于一半的数，如果没有这样的数，打印提示信息

### 进阶
给定一个整型数组arr，再给定一个整数K，答应所有出现次数大于N/K的数，如果没有这样的数，打印提示信息

### 要求
原问题要求时间复杂度为O(N)，额外空间复杂度为O(1)，进阶问题要求时间复杂度为O(N * K)，额外空间复杂度为O(K)

## 7.在行列都排好序的矩阵中找数
### 题目
给定一个有N*M的整型矩阵matrix和一个整数K，matrix的每一行和每一列都是排好序的，实现一个函数，判断K是否在matrix中
例如：
    0   1   2   5
    2   3   4   7
    4   4   4   8   
    5   7   7   9
    如果K为7，返回true;如果K为6，返回false

### 要求
时间复杂度为O(N+M)，额外空间复杂度为O(1)

## 8.最长的可整合子数组的长度
### 题目
   先给出可整合数组的定义，如果一个数组在排序之后，每相邻两个数差的绝对值都为1，则该数组为可整合数组，例如，[5,3,4,6,2]排序之后
为[2,3,4,5,6]，符合每相邻两个数差的绝对值都为1，所以这个数组为可整合数组
   给定一个整型数组arr，请返回其中最大可整合子数组的长度。例如，[5,5,3,2,6,4,3]的最大可整合子数组为[5,3,2,6,4]，所以返回5

## 9.不重复打印排序数组中相加和为给定值的所有二元组和三元组
### 题目
给定排序数组arr和整数k，不重复打印arr中所有相加和为k的不降序二元组
例如，arr=[-8,-4,-3,0,1,2,4,5,8,9],k=10,打印结果为:
1,9
2,8

### 补充题目
给定排序数组arr和整数k，不重复打印arr中所有相加和为k的不降序三元组。
例如，arr=[-8,-4,-3,0,1,2,4,5,8,9]，k=10，打印结果为:
-4,5,9
-3,4,9
-3,5,8
0,1,9
0,2,8
1,4,5

## 10.未排序正数数组中累加和为给定值的最长子数组长度
### 题目
给定一个数组arr，该数组无序，但每个值均为正数，再给定一个数组k。求arr的所有子数组中所有元素相加和为k的最长子数组长度
例如：arr=[1,2,1,1,1], k=3
累加和为3的最长子数组为[1,1,1]，所以结果返回3

## 11.未排序数组中累加和为给定值的最长子数组系列问题
### 题目
给定一个无序数组arr，其中元素可正、可负、可0，给定一个整数k。求arr所有的子数组中累加和为k的最长子数组长度

### 补充题目
给定一个无序数组arr，其中元素可正、可负、可0.求arr所有的子数组中正数与负数个数相等的最长子数组长度

### 补充题目
给定一个无序数组arr，其中元素只是1或者0，求arr所有的子数组中0和1个数相等的最长子数组长度

## 12.未排序数组中累加和小于或者等于给定值的最长子数组长度 
### 题目
给定一个无序数组arr，其中元素可正、可负、可0，给定一个整数k。求arr所有的子数组中累加和小于或等于k的最长子数组长度】
例如：arr=[3,-2,-4,0,6]，k=-2，相加和小于或等于-2的最长子数组为{3, -2, -4, 0}，所以结果返回4

## 13.计算数组的小和
### 题目
  数组小和的定义如下：
  例如，数组s=[1,3,5,2,4,6]，在s[0]的左边小鱼或等于s[0]的数的和为0，在s[1]的左边小于或等于s[1]的数的和为1，在s[2]的左边小于或等于s[2]的数的和为
和为1+3=4，在s[3]的左边小于或等于s[3]的数的和为1，在s[4]的左边小于或者等于s[4]的数的和为1+3+2=6，在s[5]的左边小于或等于s[5]的数的和为1+3+5+2+4=15,
所以s的小和为0+1+4+1+6+15=27.
  给定一个数组s，实现函数返回的s的小和

## 14.自然数数组的排序
### 题目
  给定一个长度为N的整型数组arr，其中有N个互不相等的自然数1~N，请实现arr的排序，但是不要把下标0~N-1位置上的数通过直接赋值的方式替换成1~N

### 要求
  时间复杂度为O(N)，额外空间复杂度为O(1)

## 15.奇数下标都是奇数或者偶数下标都是偶数
### 题目
给定一个长度不小于2的数组arr，实现一个函数调整arr，要么让所有的偶数下标都是偶数，要么让所有的奇数下标都是奇数

### 要求
如果arr的长度为N，函数要求时间复杂度为O(N)，空间复杂度为O(1)

## 16.子数组的最大累加和问题
### 题目
给定一个数组arr，返回子数组的最大累加和
例如，arr=[1,-2,3,5,-2,6,-1]，所有的子数组中，[3,5,-2,6]可以累加最大的和为12，所以返回12

### 要求
如果arr的长度为N，要求时间复杂度为O(N)，额外空间复杂度为O(1)

## 17.子矩阵的最大累加和问题
### 题目
给定一个矩阵matrix，其中的值有正、有负、有0，返回子矩阵的最大累加和
例如，矩阵matrix为：
-90  48  78  
64  -40  64 
81  -7   66
其中，最大累加和的子矩阵为：
48  78
-40 64  
-7  66
所以返回累加和209
例如，matrix为：
-1  -1  -1
-1  2  2
-1  -1  -1
其中，最大累加和的子矩阵为：
2   2   
所以返回累加和4

## 18.在数组中找到一个局部最小的位置
### 题目
   定义局部最小的概念。arr长度为1时，arr[0]是局部最小。arr的长度为N(N>1)时，如果arr[0]<arr[1]，那么arr[0]是
局部最小；如果arr[N-1]<arr[N-2]，那么arr[N-1]是局部最小;如果0<i<N-1，既有arr[i]<arr[i-1]，又有arr[i]<arr[i+1]，
那么arr[i]是局部最小
   给定无序数组arr，已知arr中任意两个相邻的数都不相等，写一个函数，只需返回arr中任意一个局部最小出现的位置即可

## 19.数组中子数组的最大累乘积
### 题目
给定一个double类型的数组arr，其中的元素可正、可负、可0，返回子数组累乘的最大乘积。例如，arr=[-2.5,  4, 0, 3, 0.5, 8, -1]，
子数组[3, 0.5, 8]累乘可以获得最大的乘积12，所以返回12

## 20.打印N个数组整体最大的Top K
### 题目
  有N个长度不一的数组，所有的数组都是有序的，请从大到小打印这N个数组整体最大的前K个数
  例如，输入含有N行元素的二维数组可以代表N个一维数组
  219,405,538,845,971
  148,558
  52,99,348,691
  再输入整数 k=5，则打印
  Top 5 : 971,845,691,558,538

### 要求
1. 如果所有数组的元素个数小于K，则从大到小打印所有的数
2. 要求时间复杂度为O(KlogN)

## 21.边界都是1的最大正方形大小
### 题目
   给定一个N*N的矩阵 matrix，在这个矩阵中，只有0和1两种值，返回边框全是1的最大正方形的边长长度
   例如：
   0    1   1   1   1
   0    1   0   0   1
   0    1   0   0   1
   0    1   1   1   1   
   0    1   0   1   1
   其中，边框全是1的最大正方形的大小为4*4，所以返回4
    
## 22.不包含本位置值的累乘数组
### 题目
  给定一个整型数组arr，返回不包含本位置值的累乘数组
  
### 要求
  1.时间复杂度为O(N)
  2.除需要返回的结果数组外，额外空间复杂度为O(1)
  
### 进阶题目
对时间和空间复杂度的要求不变，而且不能使用除法

## 23.数组的partition调整
### 题目
  给定一个有序数组arr，调整arr使得这个数组的左半部份没有重复元素且升序，而不用保证右部分是否有序
  例如，arr=[1,2,2,2,3,3,4,5,6,6,7,7,8,8,8,9]，调整之后arr=[1,2,3,4,5,6,7,8,9,...]

### 补充题目
  给定一个数组arr，其中只可能含有0、1、2三个值，请实现arr的排序
  另一种问法为：有一个数组，其中只由红球、蓝球和黄球，请实现红球全放在数组的左边，蓝球放在中间，黄球放在右边
  另一种问法为：有一个数组，再给定一个值k，请实现比k小的数都放在数组的左边，等于k的数都放在数组的中间，比k大
的数都放在数组的右边

### 要求
 1. 所有题目实现的时间复杂度为O(N)
 2. 所有题目实现的额外空间复杂度为O(1)
 
## 24.求最短通路值
### 题目
   用一个整型矩阵matrix表示一个网络，1代表有路，0代表无路，每一个位置只要不越界，都有上下左右4个方向，求从最左上角到
最右下角的最短通路值
    例如，matrix为
     1   0   1   1   1   
     1   0   1   0   1
     1   1   1   0   1
     0   0   0   0   1 
     通路只有一条，由12个1构成，所以返回12
       
## 25.数组中未出现的最小正整数
### 题目
  给定一个无序整型数组arr，找到数组中未出现的最小正整数
  
### 举例
  arr = [-1,2,3,4].返回1
  arr = [1,2,3,4].返回5

## 26.数组排序之后相邻数的最大差值
### 题目
   给定一个整型数组arr，返回排序后的相邻两数的最大差值

### 举例
   arr = [9,3,1,10].如果排序，结果为[1,3,9,10]，9和3的差为最大差值，故返回6
   arr = [5,5,5,5]。返回0
   
### 要求
   如果arr的长度为N，请做到时间复杂度为O(N)