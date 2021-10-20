package ru.vsu.byldina;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a singly linked list.
 */
public final class LinkedList<E> implements Collection<E> {
    /**
     * First element in list.
     */
    private LinkedListNode head;

    /**
     * Last element in list.
     */
    private LinkedListNode tail;

    /**
     * Size of list.
     */
    private int size;

    /**
     * Initializes the linked list.
     */
    public LinkedList() {
        clear();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        var eo = (E) o;
        for (E e : this) {
            if (e.equals(eo))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(this.head);
    }

    @Override
    public Object[] toArray() {
        var arr = new Object[this.size];

        int i = 0;
        for (E e : this)
            arr[i++] = e;

        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a == null)
            throw new NullPointerException("a is null.");

        if (this.size > a.length) {
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        int i = 0;
        Object[] oa = a;
        for (E e : this)
            oa[i++] = e;

        if (this.size < oa.length)
            oa[i] = null;

        return (T[]) oa;
    }

    @Override
    public boolean add(E e) {
        var node = new LinkedListNode(e);

        if (this.head == null) {
            this.head = this.tail = node;
        } else {
            this.tail.setNext(node);
            this.tail = node;
        }

        ++this.size;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        if (this.head == null || this.tail == null)
            return false;

        LinkedListNode prevDeletedNode = findPrevNode((E) o);
        if (prevDeletedNode == this.tail)
            return false;

        if (prevDeletedNode == null) {
            this.head = this.head.next();

            if (this.head == null)
                this.tail = null;
        } else {
            LinkedListNode curDeletedNode = prevDeletedNode.next();
            prevDeletedNode.setNext(curDeletedNode.next());

            if (this.tail == curDeletedNode)
                this.tail = prevDeletedNode;
        }

        --this.size;
        return true;
    }

    private LinkedListNode findPrevNode(E e) {
        LinkedListNode prev = null;
        LinkedListNode cur = this.head;

        while (cur != null) {
            if (cur.value().equals(e))
                return prev;
            
            prev = cur;
            cur = cur.next();
        }

        return prev;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        var s = true;
        
        for (E e : c) {
            if (!add(e))
                s = false;
        }

        return s;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (this.head == null || this.tail == null)
            return false;

        var s = true;
        for (Object o : c) {
            if (!remove(o))
                s = false;
        }

        return s;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        var s = true;
        for (E e : this) {
            if (!c.contains(e))
                remove(e);
            else
                s = false;
        }
        return s;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Represents a singly linked list node.
     */
    private final class LinkedListNode {
        /**
         * Value of node.
         */
        private E value;

        /**
         * Next node.
         */
        private LinkedListNode next;

        /**
         * Initializes the singly linked list node.
         * 
         * @param value Value of node
         */
        public LinkedListNode(E value) {
            this.value = value;
        }

        /**
         * Returns value of node.
         * @return
         */
        public E value() {
            return this.value;
        }

        /**
         * Returns next node.
         * 
         * @return Next node
         */
        public LinkedListNode next() {
            return this.next;
        }

        /**
         * Sets new value for next node.
         * @param value New value for next node
         */
        public void setNext(LinkedListNode value) {
            this.next = value;
        }
    }

    /**
     * Represents a singly linked list iterator.
     */
    private final class LinkedListIterator implements Iterator<E> {
        /**
         * Current list node.
         */
        private LinkedListNode currentNode;

        /**
         * Initializes the singly linked list iterator.
         * @param startNode Node from which the enumeration will start.
         */
        public LinkedListIterator(LinkedListNode startNode) {
            this.currentNode = startNode;
        }

        @Override
        public boolean hasNext() {
            return this.currentNode != null;
        }

        @Override
        public E next() {
            if (this.currentNode == null)
                throw new NoSuchElementException("hasNext has value false.");

            LinkedListNode node = this.currentNode;
            this.currentNode = this.currentNode.next();
            return node.value();
        }
    }
}
