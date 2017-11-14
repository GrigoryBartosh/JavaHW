package ru.spbau.gbarto;

import com.sun.istack.internal.NotNull;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

public class MyTreeSet<K> extends AbstractSet<K> implements MyTreeSetInterface<K> {
    private int size = 0;
    private Node root = null;
    private Comparator<? super K> comparator;
    private boolean reverse = true;

    private class Node {
        Node left = null;
        Node right = null;
        Node parent = null;
        K key;

        Node(K key) {
            this.key = key;
        }

        Node(K key, Node p) {
            this.key = key;
            this.parent = p;
        }
    }

    private class MyTreeSetIterator implements Iterator<K> {
        boolean reverse = MyTreeSet.this.reverse;
        Node first = firstNode();
        Node last = lastNode();
        Node cur = null;

        MyTreeSetIterator(boolean changeOrder) {
            reverse ^= changeOrder;
        }

        @Override
        public boolean hasNext() {
            return cur != (reverse ? last : first);
        }

        void nextAscendingOrder() {
            if (cur == null) {
                cur = first;
                return;
            }
            cur = getNextNode(cur);
        }

        void nextDescendingOrder() {
            if (cur == null) {
                cur = last;
                return;
            }
            cur = getPreviousNode(cur);
        }

        @Override
        public K next() {
            if (reverse) {
                nextAscendingOrder();
            } else {
                nextDescendingOrder();
            }
            return cur.key;
        }
    }

    public MyTreeSet() {
        this.comparator = (o1, o2) -> ((Comparable) o1).compareTo(o2);
    }

    public MyTreeSet(@NotNull Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    private MyTreeSet(@NotNull MyTreeSet<K> set, boolean changeOrder) {
        this.size = set.size;
        this.root = set.root;
        this.comparator = set.comparator;
        this.reverse = set.reverse ^ changeOrder;
    }

    private Node getNextNode(@NotNull Node n) {
        if (n.right != null) {
            n = n.right;
            while (n.left != null) {
                n = n.left;
            }
        } else {
            while (n.parent != null && n.parent.right == n) {
                n = n.parent;
            }
            n = n.parent;
        }

        return n;
    }

    private Node getPreviousNode(@NotNull Node n) {
        if (n.left != null) {
            n = n.left;
            while (n.right != null) {
                n = n.right;
            }
        } else {
            while (n.parent != null && n.parent.left == n) {
                n = n.parent;
            }
            n = n.parent;
        }

        return n;
    }

    private Node findNode(@NotNull Object key) {
        Node n = root;
        while (n != null) {
            int cmp = comparator.compare((K) key, n.key);
            if (cmp == 0) {
                return n;
            }

            if (cmp < 0) {
                n = n.left;
            } else {
                n = n.right;
            }
        }

        return null;
    }

    private void removeNode(@NotNull Node n) {
        if (n.left == null || n.right == null) {
            boolean flagLeft = n.parent != null && n.parent.left == n;
            if (n.left != null) {
                n.left.parent = n.parent;
                if (n.parent != null) {
                    if (flagLeft) {
                        n.parent.left = n.left;
                    } else {
                        n.parent.right = n.left;
                    }
                }
            } else {
                if (n.right!= null) {
                    n.right.parent = n.parent;
                }
                if (n.parent != null) {
                    if (flagLeft) {
                        n.parent.left = n.right;
                    } else {
                        n.parent.right = n.right;
                    }
                }
            }
            if (n == root) {
                if (n.left != null) {
                    root = n.left;
                } else {
                    root = n.right;
                }
            }
        } else {
            Node next = getNextNode(n);
            K tmp_key = n.key;
            n.key = next.key;
            next.key = tmp_key;
            removeNode(next);
        }
    }

    private Node firstNode() {
        Node n = root;
        while (n != null && n.left != null) {
            n = n.left;
        }
        return n;
    }

    private Node lastNode() {
        Node n = root;
        while (n != null && n.right != null) {
            n = n.right;
        }
        return n;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(@NotNull K key) {
        if (root == null) {
            root = new Node(key);
            size++;
            return true;
        }

        Node p = null;
        Node n = root;
        int cmp = 0;

        while (n != null) {
            cmp = comparator.compare(key, n.key);
            if (cmp == 0) {
                return false;
            }

            p = n;
            n = cmp < 0 ? n.left : n.right;
        }

        n = new Node(key, p);
        if (cmp < 0) {
            p.left = n;
        } else {
            p.right = n;
        }
        size++;

        return true;
    }

    @Override
    public boolean contains(@NotNull Object key) {
        return findNode(key) != null;
    }

    @Override
    public boolean remove(@NotNull Object key) {
        Node n = findNode(key);
        if (n == null)
            return false;

        size--;
        removeNode(n);
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyTreeSetIterator(false);
    }

    @Override
    public Iterator<K> descendingIterator() {
        return new MyTreeSetIterator(true);
    }

    @Override
    public MyTreeSetInterface<K> descendingSet() {
        return new MyTreeSet<>(this, true);
    }

    @Override
    public K first() {
        Node n = reverse ? firstNode() : lastNode();
        return n == null ? null : n.key;
    }

    @Override
    public K last() {
        Node n = reverse ? lastNode() : firstNode();
        return n == null ? null : n.key;
    }

    @Override
    public K floor(@NotNull K key) {
        Node n = root;
        Node res = null;
        while (n != null) {
            if (reverse ? comparator.compare(key, n.key) < 0 : comparator.compare(key, n.key) <= 0) {
                if (!reverse) {
                    res = n;
                }
                n = n.left;
            } else {
                if (reverse) {
                    res = n;
                }
                n = n.right;
            }
        }

        return res == null ? null : res.key;
    }

    @Override
    public K lower(@NotNull K key) {
        Node n = root;
        Node res = null;
        while (n != null) {
            if (reverse ? comparator.compare(key, n.key) <= 0 : comparator.compare(key, n.key) < 0) {
                if (!reverse) {
                    res = n;
                }
                n = n.left;
            } else {
                if (reverse) {
                    res = n;
                }
                n = n.right;
            }
        }

        return res == null ? null : res.key;
    }

    @Override
    public K ceiling(@NotNull K key) {
        Node n = root;
        Node res = null;
        while (n != null) {
            if (reverse ? comparator.compare(key, n.key) <= 0 : comparator.compare(key, n.key) < 0) {
                if (reverse) {
                    res = n;
                }
                n = n.left;
            } else {
                if (!reverse) {
                    res = n;
                }
                n = n.right;
            }
        }

        return res == null ? null : res.key;
    }

    @Override
    public K higher(@NotNull K key) {
        Node n = root;
        Node res = null;
        while (n != null) {
            if (reverse ? comparator.compare(key, n.key) < 0 : comparator.compare(key, n.key) <= 0) {
                if (reverse) {
                    res = n;
                }
                n = n.left;
            } else {
                if (!reverse) {
                    res = n;
                }
                n = n.right;
            }
        }

        return res == null ? null : res.key;
    }
}