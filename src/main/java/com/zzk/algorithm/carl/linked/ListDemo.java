package com.zzk.algorithm.carl.linked;

/**
 * 链表算法
 */
public class ListDemo {

    /**
     * 1、移除链表元素
     * 给你一个链表的头节点 `head` 和一个整数 `val` ，请你删除链表中所有满足 `Node.val == val` 的节点，并返回 **新的头节点**
     */
    /**
     * 添加虚拟节点
     * 时间 O(N)
     * 空间 O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 不添加虚拟节点
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public ListNode removeElement2(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 2、反转链表
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     */
    /**
     * 双指针
     */
    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null;
        while(cur != null){
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
    /**
     * 递归
     */
    public ListNode reverseList2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode last = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 3、两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
     */
    /**
     * 递归
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     */
    public ListNode swapPairs(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
    /**
     * 非递归
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     */
    public ListNode swapPairs2(ListNode head){
        ListNode dummyHead = new ListNode(0, null);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while(temp.next != null && temp.next.next != null){
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }

    /**
     * 4、删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点
     *
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     */

    /**
     * 时间复杂度 O(L)
     * 空间复杂度 O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0, head);
        ListNode fast = head;
        ListNode low = dummy;
        while(n-- > 0){
            fast = fast.next;
        }
        while(fast != null){
            fast = fast.next;
            low = low.next;
        }
        low.next = low.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    /**
     * 5、链表相交
     * 给你两个单链表的头节点 `headA` 和 `headB` ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 `null` 。
     */
    /**
     * 双指针
     * 时间复杂度 O(m+n)
     * 空间复杂度 O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        if(headA == null || headB == null){
            return null;
        }
        ListNode pA = headA, pB = headB;
        //如果存在节点，那么肯会有相等的节点
        while(pA != pB){
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 6、环形链表 II
     * 给定一个链表的头节点  `head` ，返回链表开始入环的第一个节点。 *如果链表无环，则返回 `null`。*
     * 如果链表中有某个节点，可以通过连续跟踪 `next` 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 `pos` 来表示链表尾连接到链表中的位置（**索引从 0 开始**）。如果 `pos` 是 `-1`，则在该链表中没有环。**注意：`pos` 不作为参数进行传递**，仅仅是为了标识链表的实际情况
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     */
    public ListNode findCycle(ListNode head){
        ListNode low = head;
        ListNode fast = head;
        boolean hasCycle = false;
        //判断是否有环
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            low = low.next;
            if(fast == low){
                hasCycle = true;
                break;
            }
        }
        if(!hasCycle){
            return null;
        }
        //回到原点
        low = head;
        while(low != fast){
            low = low.next;
            fast = fast.next;
        }
        return low;
    }
}
