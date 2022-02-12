# Morris算法

## 介绍
空间复杂度为O(1)的二叉树遍历算法

## 实现原则
记当前节点为cur
1. 如果cur无左孩子，cur向右移动（cur = cur.right）
2. 如果cur有左孩子，找到cur左子树最右的节点，记为mostright
 - 如果 mostright 的right指针指向空，让其指向cur，cur向左移动（cur = cur.left）
 - 如果 mostright 的right指针指向cur，让其指向空，cur向右移动（cur = cur.right）
 
 ## 遍历实质
 建立一种机制，对于没有左子树的节点只到达一次，对于有左子树的节点会到达两次
 
 ## 实现代码
 
 看MorrisTree.java实现