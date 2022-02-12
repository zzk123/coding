package com.zzk.algorithm.coding.list;

/**
 * @program: coding
 * @description:
 *   11.两个单链表相交的一系列问题
 *   问题1：如何判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回null
 *   问题2：如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回null
 *   问题3：如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null
 * @author: zzk
 * @create: 2020-09-21 22:54
 */
public class getIntersectNode {

    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 1. 判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回null
     * @param head
     * @return
     */
    public Node getLoopNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return  null;
        }
        Node n1 = head.next;
        Node n2 = head.next.next;
        while(n1 != n2){
            if(n2.next == null || n2.next.next == null){
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head;
        while(n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }


    /**
     * 2. 如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回null
     * 注：如果两个无环链表相交，那么从相交节点开始，一直到两个链表终止这一点，是两个链表共享的
     * @param head1
     * @param head2
     * @return
     */
    public Node noLoop(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while(cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        if(cur1 != cur2){
            return null;
        }

        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while(n != 0){
            n--;
            cur1 = cur1.next;
        }
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }


    /**
     * 3. 如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null
     * @param head1
     * @param loop1 第一个入环的节点
     * @param head2
     * @param loop2 第一个入环的节点
     * @return
     */
    public Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        if(loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while(cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while(cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while(n != 0){
                n--;
                cur1 = cur1.next;
            }
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{
            cur1 = loop1.next;
            while(cur1 != loop1){
                if(cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }


    /**
     * 主方法
     * @param head1
     * @param head2
     * @return
     */
    public Node getIntersectNode(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if(loop1 == null && loop2 == null){
            return noLoop(head1, head2);
        }
        if(loop1 != null && loop2 != null){
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }
}
