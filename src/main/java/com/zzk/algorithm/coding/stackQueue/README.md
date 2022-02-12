# 栈和队列

## 1. 设计一个有getMin功能的栈
### 题目：
实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
### 要求
1. pop、push、getMin操作的时间复杂度都是O(1)
2. 设计的栈类型可以使用现成的栈结构


## 2.由两个栈组成的队列
### 题目：
编写一个类，用两个栈实现队列，支持队列的基本操作（add，poll，peek）


## 3.如何仅用递归函数和栈操作逆序一个栈
### 题目：
一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1.将这个栈转置后，从栈顶到栈底为1、2、3、4、5，也就是实现栈元素的逆序，但是只能用递归函数来实现，不能用其他数据结构


## 4.猫狗队列

    public class Pet {

        private String type;

        public Pet(String type){
            this.type = type;
        }

        public String getPetType(){
            return this.type;
        }
    }

    public class Dog extends Pet{
        public Dog(){
            super("dog");
        }
    }

    public class Cat extends Pet{
        public Cat(){
            super("cat");
        }
    }
    
 实现一种狗猫队列的结构，要求如下
 - 用户可以调用add方法将cat类或dog类的实例放入队列中
 - 用户可以调用pollAll方法，将队列中所有的实例按照进队列的先后顺序依次弹出
 - 用户可以调用pollDog方法，将队列中的dog类的实例按照进队列的先后顺序依次弹出
 - 用户可以调用pollCat方法，将队列中的cat类的实例按照进队列的先后顺序依次弹出
 - 用户可以调用isEmpty方法，检查队列中的是否还有dog或者cat的实例
 - 用户可以调用isDogEmpty方法，检查队列中是否还有dog类的实例
 - 用户可以调用isCatEmpty方法，检查队列中是否还有cat类的实例
 
 ## 5.用一个栈实现另一个栈的排序
 ### 题目
 一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，只许申请一个栈，除此之外，可以申请新的变量，但不能申请额外的数据结构。如何完成排序
 
 ## 6.用栈来求解汉诺塔问题
 汉诺塔问题比较经典，这里修改一下游戏规则：现在限制不能从最左侧的塔直接移动到最右侧，也不能从最右侧直接移动到最左侧，而是必须经过中间，求当塔有N层的时候，打印最优移动过程和最优移动总步数
 
 例如，当塔数为两层时，最上层的塔记为1，最下层的塔记为2，则打印
    
    Move 1 from left to mid
    Move 1 from mid to right
    Move 2 from left to mid
    Move 1 from right to mid
    Move 1 from mid to left
    Move 2 from mid to right
    Move 1 from left to mid
    Move 1 from mid to right
 
 
 ## 6. 生成窗口最大值数组
 ### 题目
 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑倒最右边，窗口每次向右边滑一个位置
 例如，数组为[4,3,5,4,3,3,6,7],窗口大小为3时：
   
    [4  3  5] 4  3  3  6  7        窗口最大值为5
     4 [3  5  4] 3  3  6  7        窗口最大值为5
     4  3 [5  4  3] 3  6  7        窗口最大值为5
     4  3  5 [4  3  3] 6  7        窗口最大值为4
     4  3  5  4 [3  3  6] 7        窗口最大值为6
     4  3  5  4  3 [3  6  7]       窗口最大值为7
 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值
 请实现一个函数
 - 输入：整型数组arr，窗口大小为w
 - 输出：一个长度为n-w+1的数组res，res[i] 表示每一种窗口状态下的最大值
 
 ## 7. 构造数组的MaxTree
 
定义二叉树节点如下：
    
    public class Node{
        public int value;
        public Node left;
        public Node right;
        
        public Node(int data){
            this.value = value;
        }
    }
 一个数组的MaxTree定义如下
  - 数组必须没有重复元素
  - MaxTree是一颗二叉树，数组的每个值对应一个二叉树节点
  - 包括MaxTree树在内且在其中的每一颗子树上，值最大的节点都是树的头
 给定一个没有重复元素的数组arr，写出生成这个数组的MaxTree的函数，要求如果数组长度为N，则时间复杂度为O(N)、额外空间复杂度为O(N)
 
 ## 8. 求最大子矩阵的大小
 给定一个整型矩阵map，其中的值只有0和1两种，求其中全是1的所有矩阵区域中，最大的矩阵区域为1的数量
 
    例如：-
    1 1 1 0
    其中，最大的矩阵区域数量有3个1，返回3
    再如：
    1 0 1 1
    1 1 1 1
    1 1 1 0
    其中，最大的矩阵区域有6个1，所以返回6
    
 ## 9.最大值减去最小值小于或等于num的子数组数量
 给定数组arr和整数num，共返回多少个子数组满足如下情况
 max(arr[i..j]) - min(arr[i..j]) <= num
 max(arr[i..j])表示子数组arr[i..j]中的最大值，min(arr[i..j])表示子数组arr[i..j]中的最小值
 
 要求：
 如果数组长度为N，请实现时间复杂度为O(N)的解法