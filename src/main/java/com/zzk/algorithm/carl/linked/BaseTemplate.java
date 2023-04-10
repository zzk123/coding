package com.zzk.algorithm.carl.linked;

import java.util.Stack;

/**
 * 链表模板
 */
public class BaseTemplate {

    /**
     * 1、判断链表是否有环
     * 快慢指针判断
     */
    public boolean hasCycle(ListNode head){
        ListNode fast, slow;
        fast = slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            //如果存在环，快慢针必然相遇
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 2、已知链表中含有环，返回这个环的起始位置
     */
    public ListNode findCycle(ListNode head){
        ListNode fast, slow;
        fast = slow = head;
        //查找相遇点
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        slow = head;
        //相同速度前进
        while(slow != fast){
            fast = fast.next;
            slow = slow.next;
        }
        //两个指针相遇点就是环的起点
        return slow;
    }

    /**
     * 3、寻找无环单链表的中点
     * 快慢指针
     */
    public ListNode findMidNode(ListNode head){
        ListNode fast, slow;
        fast = slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    /**
     * 4、寻找单链表的倒数第k个元素
     * 先让快指针先走k步，然后快慢指针同时前进，这样当快指针走到末尾时，慢指针所在的位置就是倒数第k个链表节点了
     */
    public ListNode findKNode(ListNode head, int k){
        ListNode fast, slow;
        fast = slow = head;
        while(k -- > 0){
            fast = fast.next;
        }
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 5、回文链表判断
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head){
        if(head == null || head.next == null){
            return true;
        }
        ListNode right = head.next;
        ListNode cur = head;
        //查找链表中间链表
        while(cur.next != null && cur.next.next != null){
            right = right.next;
            cur = cur.next.next;
        }

        //栈传入一半链表的值
        Stack<ListNode> stack = new Stack<>();
        while(right != null){
            stack.push(right);
            right = right.next;
        }

        while(!stack.isEmpty()){
            if(head.val != stack.pop().val){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 6、递归反转整个链表 - 通过递归
     */
    public ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode last = reverse(head.next);
        //核心，反转上一个节点指向前一个节点
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 7、递归反转链表部分 - m - n 的节点
     * 将 m-n 转为 1-（m-n）的问题
     */
    public ListNode reverseBetween(ListNode head, int m, int n){
        if(m == 1){
            return reverseN(head, n);
        }
        //前进到m的位置进行反转
        head.next = reverseBetween(head.next, m-1, n-1);
        return head;
    }

    //反转链表前N个节点
    ListNode successor = null;
    public ListNode reverseN(ListNode head, int n){
        if(n == 1){
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n-1);
        //核心
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     * 8、每k个节点进行反转
     */
    public ListNode reverseGroup(ListNode head, int k){
        if(head == null){
            return null;
        }
        ListNode a, b;
        a = b = head;
        for(int i = 0; i < k; i++){
            if(b == null){
                return head;
            }
            b = b.next;
        }
        //区间反转
        ListNode newHead = reverse(a, b);
        //进行递归查找反转
        a.next = reverseGroup(b, k);
        return newHead;
    }
    //反转区间 a,b
    public ListNode reverse(ListNode a, ListNode b){
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        while(cur != b){
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
