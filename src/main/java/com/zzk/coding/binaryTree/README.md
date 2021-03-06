# 二叉树问题

## 1.分别用递归和非递归方式实现二叉树先序、中序和后序遍历
### 题目：
用递归和非递归方式，分别按照二叉树先序、中序和后序打印所有的节点。我们约定：先序遍历顺便为根、左、右；中序遍历顺序为左、根、右；
后序遍历顺序为左、右、根

## 2.打印二叉树的边界节点
### 题目：
给定一棵二叉树的头节点head，按照如下两种标准分别实现二叉树边界节点的逆时针打印

标准一：
1. 头节点为边界节点
2. 叶节点为边界节点
3. 如果节点在其所在的层中是最左或者最右的，那么也是边界节点

标准二：
1. 头节点为边界节点
2. 叶节点为边界节点
3. 树左边界延伸下去的路径为边界节点
4. 树右边界延伸下去的路劲为边界节点

例如：
                      1
               /           \
              2             3
               \           /  \
                4         5    6
               / \       / \
              7   8      9  10
                   \     /
                  11    12
                  / \   / \
                 13 14 15 16
 
 按标准一的打印结果为： 1,2,4,7,11,13,14,15,16,12,10,6,3
 按标准二的打印结果为：1,2,4,7,13,14,15,16,10,6,3
 ## 要求
 1. 如果节点数为N，两种标准实现的时间复杂度要求都为O(N)，额外空间复杂度要求都为O(h)，h为二叉树的高度
 2. 两种标准都要求逆时针顺序且不重复打印所有的边界节点
 
## 3.如何较为直观的打印二叉树
### 题目
二叉树可以用常规的三种遍历结果来描述其结构，但是不够直观，尤其是二叉树中有重复值的时候，仅通过三种遍历的结果来构造二叉树的
真是结构更是难上加难，有时候根本不可能。给定一棵二叉树的头节点head，已知二叉树节点值的类型为32位整型，请实现一个打印二叉树的函数，
可以直观地展示树的形式，也便于画出真实的结构

## 4.二叉树的序列化和反序列化 
### 题目
二叉树被记录成文件的过程叫作二叉树的序列化，通过文件内容重建原来二叉树的过程叫作二叉树的反序列化。给定一棵二叉树的头节点head，并已知
二叉树节点值的类型为32位整型，请设计一种二叉树序列化和反序列化的方案，并用代码实现

## 5.遍历二叉树的神级方法
### 题目
给定一棵二叉树的头节点head，完成二叉树的先序、中序和后序遍历。如果二叉树的节点数为N，要求时间复杂度为O(N)，额外空间复杂度为O(1)

## 6.在二叉树中找到累加和为指定值的最长路径长度
### 题目
给定一棵二叉树的头节点head和一个32位整数sum，二叉树节点值类型为整型，求累加和为sum的最长路径长度。路径是从某个节点往下，每次最多选择一个孩子节点或者
不选所形成的节点链

例如，二叉树如下

                            -3
                          /      \
                         3        -9
                       /   \      /  \
                     1      0    2    1
                           /  \
                          1    6
如果sum=6，那么累加和为6的最长路劲为：-3,3,0,6，所以返回4
如果sum=-9，那么累加和为-9的最长路径为：-9，所以返回1
注:本题不用考虑节点值相加可能溢出的问题

## 7.找到二叉树中的最大搜索二叉树
### 题目
给定一棵二叉树的头节点head，已知其中所有节点的值都不一样，找到含有节点最多的搜索二叉树，并返回这棵子树的头节点
例如，二叉树如图1所示
这棵树中的最大搜索二叉子树如图2所示

                    6
                  /   \
                 1      12
               /  \     /  \
              0    3   10    13                      10
                      /  \   /  \                   /  \
                     4   14  20  16               4     14
                    / \   / \                    /  \   /  \
                   2  5  11 15                  2   5   11  15
                        图1                          图2
                     
### 要求
如果节点数为N，要求时间复杂度为O(N)，额外空间复杂度为O(h)，h为二叉树的高度
### 注
二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质
的二叉树： 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。

## 8.找到二叉树中符合搜索二叉树条件的最大拓扑结构
### 题目
给定一棵二叉树的头节点head，已知所有节点的值都不一样，返回其中最大的且符合搜索二叉树条件的最大拓扑结构的大小
例如，二叉树如下图所示
                          6
                       /      \
                      1        12
                     /  \    /     \
                    0    3  10      13
                            / \     / \
                           4   14  20  16
                          / \  / \
                         2  5  11 15
 其中最大的且符合搜索二叉树条件的最大拓扑结构的如下图
                              6
                           /      \
                          1        12
                         /  \    /     \
                        0    3  10      13
                                           \
                                            16
这个拓扑结构节点数为8，所以返回8
                        
## 9.二叉树的按层打印与ZigZag打印
### 题目
给定一棵二叉树的头节点head，分别实现按层打印和ZigZag打印二叉树的函数
例如，二叉树如下图所示
                        1
                       /  \
                      2    3
                     /   /   \
                    4    5    6
                        / \
                       7   8
 按层打印时，输出格式必须如下：
    Level 1 ： 1
    Level 2 ： 2 3
    Level 3 ： 4 5 6 
    Level 4 ： 7 8
 ZigZag打印时，输出格式必须如下：
    Level 1 from  left  to  right： 1
    Level 2 from  left  to  right： 3 2
    Level 3 from  left  to  right： 4 5 6
    Level 4 from  left  to  right： 8 7
   

## 10.调整搜索二叉树中两个错误的节点

### 题目
一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再是搜索二叉树，请找到这两个错误节点并返回。
已知二叉树中所有节点的值都不一样，给点二叉树的头节点head，返回一个长度为2的二叉树节点类型的数组errs，errs[0]表示一个
错误节点，errs[1]表示另一个错误节点

### 进阶
如果在原问题中得到这两个错误节点，我们可以通过交换这两个节点的节点值的方式让整棵二叉树重新成为搜索二叉树，但是现在要求你
不能这么做，而是在结构上完全交换两个节点的位置，请实现调整的函数

## 11.判断t1树是否包含t2树全部的拓扑结构
### 题目
给定彼此独立的两颗树头结点分别为t1和t2，判断t1树是否包含t2树全部的拓扑结构。
例如，下图所示的t1树和t2树
                
                    1
                   /  \
                  2     3                           2
                /  \   /  \                        /  \              
               4    5  6   7                      4    5
              / \   /                            /
             8   9  10                          8
                    t1树                             t2树
                    
 t1树包含t2树全部的拓扑结构，所以返回true
 
## 12.判断t1树中是否有与t2树拓扑结构完全相同的子树
### 题目
给定彼此独立的两颗树头节点分别为t1和t2，判断t1中是否有与t2树拓扑结构完全相同的子树
例如，下图所示的t1树和t2树
             1                          
          /    \
         2       3                  2
       /   \    /  \              /   \
      4     5  6    7            4     5
       \    /                     \    / 
        8  9                       8  9 
        t1树                         t2树
 t1树与t2树拓扑结构完全相同的子树，所以返回true，但如果t1树和t2树分别下图所示，则t1树没有与t2树拓扑结构完全
 相同的子树，所以返回false
 
                1                           
              /   \
             2       3               2
           /  \     /  \           /   \
          4    5    6   7         4     5
           \   /                   \
            8  9                    8

## 13.判断二叉树是否为平衡二叉树
### 题目
平衡二叉树的性质为：要么是一棵空树，要么任何一个节点的左右子树高度差的绝对值不超过1，要定一棵二叉树的头节点head，判断这棵二叉树是否为平衡二叉树
### 要求
如果二叉树的节点数为N，要求时间复杂度为O(N)

## 14.根据后序数组重建搜索二叉树
### 题目
给定一个整型数组arr，已知其中没有重复值，判断arr是否可能是节点值类型为整型的搜索二叉树后序遍历的结果
### 进阶
如果整型数组arr中没有重复值，且已知是一棵搜素二叉树的后序遍历结果，通过数组arr重构二叉树

## 15.判断一棵二叉树是否为搜索二叉树和完全二叉树
### 题目
给定一个二叉树的头节点head，已知其中没有重复值的节点，实现两个函数分别判断这棵二叉树是否是搜索二叉树和完全二叉树

## 16.通过有序数组生成平衡搜索二叉树
### 题目
给定一个有序数组sortArr，已知其中没有重复值，用这个有序数组生成一棵平衡搜索二叉树，并且该搜索二叉树中序遍历的结果与sortArr一致

## 17.在二叉树中找到一个节点的后继节点
### 题目
现在有一种新的二叉树节点类型如下：

    public class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        
        public Node(int data){
            this.value = data;
        }
    }
该结构比普通二叉树节点结构多了一个指向父结点的parent指针。假设有一颗Node类型的节点组成的二叉树，树中每个节点的parent指针都正确地指向自己的父结点，
头节点的parent指向null。只给一个在二叉树中的某个节点node，请实现返回node的后继节点的函数，在二叉树的中序遍历的序列中，node的下一个节点叫node的后继节点。

例如，如下图的二叉树

                         6
                      /     \
                     3        9
                   /  \      / \
                  1    4    8   10
                   \    \   /
                    2    5 7
中序遍历的结果：1,2,3,4,5,6,7,8,9,10
所以节点1的后继为节点2，节点2的后继为节点3，......，节点10的后继为null

## 18.在二叉树中找到两个节点的最近公共祖先
### 题目
给定一棵二叉树的头节点head，以及这棵树中的两个节点o1和o2，请返回o1和o2的最近公共祖先节点
例如，下图
                        1
                      /    \
                     2      3
                   /   \   /  \
                   4    5  6   7
                              /
                             8
 节点4和节点5的最近公共祖先节点为节点2，节点5和节点2的最近公共祖先节点为节点2，节点6和节点8的最近公共祖先节点为节点3，节点5和节点8的最近公共祖先节点为1
 
 ### 进阶
 如果查询两个节点的最近公共祖先的操作十分频繁，想法让单条查询的查询时间减少
 
 ### 再进阶
 给定二叉树的头节点head，同时给定所有想要进行的查询，二叉树的节点数量为N，查询条数为M，请在时间复杂度为O(N+M)内返回所有查询结果

## 19.Tarjan算法与并查集解决二叉树节点间最近公共祖先的批量查询问题
### 题目
如下的Node类是标准的二叉树节点结构：
    
    public class Node{
        public int value;
        public Node left;
        public Node right
        
        public Node(int data){
            this.value = data;
        }
    }
再定义Query类如下：
    
    public class Query{
        public Node o1;
        public Node o2;
        
        public Query(Node o1, Node o2){
            this.o1 = o1;
            this.o2 = o2;
        }   
    }
一个Query类的实例表示一条查询语句，表示想要查询o1节点和o2节点的最近公共祖先节点
给定一棵二叉树的头节点head，并给定所有的查询语句，即一个Query类型的数组Query[] ques，请返回Node类型的数组Node[] ans，ans[i]代表
ques[i]这条查询的答案，即ques[i].o1和ques[i].o2的最近公共祖先

### 要求：
如果二叉树的节点数为N，查询语句的条数为M，整个处理过程的时间复杂度要求达到O(N+M)

## 20.二叉树节点间的最大距离问题
### 题目：
从二叉树的节点A出发，可以向上或者向下走，但沿途的节点只能经过一次，当到达节点B时，路径上的节点数叫作A到B的距离 
比如下图，节点4和节点2的距离为2，节点5和节点6的距离为5，给定一棵二叉树的头节点head，求整棵树上节点间的最大距离
                          1
                        /    \
                       2      3
                      /  \   /  \
                     4    5 6    7
### 要求
如果二叉树的节点数为N，时间复杂度要求为O(N)

## 21.先序、中序和后序数组两两结合重构二叉树
### 题目
已知一棵二叉树的所有节点值都不同，给定这棵二叉树正确的先序，中序和后序数组，请分别用三个函数实现任意两种数组结合重构原来的二叉树，并返回重构二叉树的头节点

## 22.通过先序和中序数组生成后序数组
### 题目：
已知一棵二叉树的所有节点值都不同，给定这棵树正确的先序和中序数组，不要重建整棵树，而是通过这两个数组直接生成正确的后序数组

## 23.统计和生成所有不同的二叉树
### 题目
给定一个整数N，如果N<1，代表空树结构，否则代表中序遍历的结果为{1,2,3....N}。请返回可能的二叉树结构有多少
例如，N=-1时，代表空树结构，返回1；N=2时，满足中序遍历为{1,2}的二叉树结构只有如下图所示的两种，所以返回结果为2

             1                          2
            /  \                      /   \
         null    2                   1     null
               /    \              /   \
              null null           null null
### 进阶
N的含义不变，假设可能的二叉树结构有M种，请返回M个二叉树头节点，每一棵二叉树代表一种可能的结构

## 24.统计完全二叉树的节点数
### 题目
给定一棵完全二叉树的头节点head，返回这棵树的节点个数
### 要求
如果完全二叉树的节点数为N，请实现时间复杂度低于O(N)的解法