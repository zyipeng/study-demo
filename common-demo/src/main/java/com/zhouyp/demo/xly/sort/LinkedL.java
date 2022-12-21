/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.xly.sort;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B>  <BR>
 *
 * @author zhouyp
 * @since 2022/1/27 16:49
 */
public
class LinkedL<E> {
    Node head;
    Node end;

    public LinkedL() {
        head = null;
        end = null;
    }

    public LinkedL(E e) {
        final Node<E> eNode = new Node<>(e);
        head = eNode;
        end = eNode;
    }

    public void insertE(E e) {
        final Node<E> eNode = new Node<>(e);
        if (head == null) {
            head = eNode;
            end = eNode;
        } else {
            end.next = eNode;
            eNode.previous = end;
            end = eNode;
        }
    }

    public void insertE(Node node,E e) {
        final Node<E> eNode = new Node<>(e, node,node.next);
        if (end == node) {
            end = node;
        } else {
            node.next.previous=eNode;
        }
        node.next = eNode;
    }
    public void insertS(Node node,E e) {
        final Node<E> eNode = new Node<>(e, node.previous, node);
        if (head == node) {
            head = eNode;
        } else {
            node.previous.next=eNode;
        }
        node.previous = eNode;
    }

    public void insertS(E e) {
        final Node<E> eNode = new Node<E>(e,null, head);
        if (end == null) {
            end = eNode;
        } else {
            eNode.next = head;
            head.previous = eNode;
        }
        head = eNode;
    }

    public void println() {
        Node<Integer> node = head;
        System.out.print("[");
        while (node != null) {
            System.out.print(node.e + ", ");
            node = node.next;
        }
        System.out.print("]");
    }
}

class Node<E> {
    E e;
    Node next;
    Node previous;

    public Node(E e) {
        this.e = e;
        next = null;
        previous = null;
    }

    public Node(E e, Node previous, Node next) {
        this.e = e;
        this.next = next;
        this.previous = previous;
    }
}