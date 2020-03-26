package com.blankj.medium._146;

import java.util.Hashtable;

/**
 * Created by tyreke.xu on 2020-03-24.
 * LRU
 */
public class LRUCache {


    private class DLinkNode {
        private int key;
        private int value;

        private DLinkNode prev;
        private DLinkNode next;
    }

    private int capacity;
    private int size;
    private DLinkNode head, tail;
    private Hashtable<Integer, DLinkNode> cache = new Hashtable<>();


    public void LRUCache(int capacity) {
        head = new DLinkNode();
        tail = new DLinkNode();

        this.capacity = capacity;
        this.size = 0;

        head.next = tail;
        tail.prev = head;
    }

    private void addNode(DLinkNode node) {
        node.next = head.next;
        node.prev = head;

        head.next = node;
        head.next.prev = node;

    }

    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addNode(node);

    }

    private DLinkNode popTail() {
        DLinkNode res = tail.prev;
        removeNode(res);
        return res;
    }

    private void removeNode(DLinkNode node) {
        DLinkNode prev = node.prev;
        DLinkNode next = node.next;

        prev.next = next;
        next.prev = prev;

    }


    private int get(int key) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    private void set(int key, int value) {
        DLinkNode node = cache.get(key);

        if (node == null) {
            DLinkNode newNode = new DLinkNode();
            newNode.key = key;
            newNode.value = value;

            addNode(newNode);
            size++;
            cache.put(key, newNode);
            if (size > capacity) {
                DLinkNode tail = popTail();
                cache.remove(tail.key);
                size--;
            }

        } else {
            node.value = value;
            moveToHead(node);
        }

    }

}
