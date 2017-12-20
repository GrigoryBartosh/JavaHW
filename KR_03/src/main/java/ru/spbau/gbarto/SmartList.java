package ru.spbau.gbarto;

import java.util.*;

import static java.lang.Math.min;

public class SmartList<E> implements List<E> {
    private int size = 0;
    private Object list = null;

    private E getFrom(Object list, int size, int i) {
        if (i >= size) {
            return null;
        }

        if (size == 0) {
            return null;
        }

        if (size == 1) {
            return (E) list;
        }

        if (size <= 5) {
            return ((E[]) list)[i];
        }

        ArrayList<E> al = (ArrayList) list;
        return al.get(i);
    }

    private Object setTo(Object list, int size, int i, E e) {
        if (i >= size) {
            return list;
        } else if (size == 0) {
            return list;
        } else if (size == 1) {
            list = e;
        } else if (size <= 5) {
            ((E[]) list)[i] = e;
        } else {
            ArrayList<E> al = (ArrayList) list;
            al.set(i, e);
        }
        return list;
    }

    private void resize(int new_size) {
        Object new_list = null;
        if (new_size > 1) {
            if (new_size <= 5) {
                new_list = new Object[5];
            } else {
                new_list = new ArrayList<E>(new_size);
            }
        }

        for (int i = 0; i < min(size, new_size); i++) {
            new_list = setTo(new_list, new_size, i, getFrom(list, size, i));
        }

        for (int i = size; i < new_size; i++) {
            new_list = setTo(new_list, new_size, i, null);
        }

        size = new_size;
    }

    SmartList() {

    }

    SmartList(Collection<? extends E> collection) {
        addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] ans = new Object[size];
        for (int i = 0; i < size; i++) {
            ans[i] = get(i);
        }
        return ans;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(E e) {
        resize(size + 1);
        set(size - 1, e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1) {
            return false;
        }
        remove(i);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object e : collection) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E e : collection) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        int j = i;
        for (E e : collection) {
            add(j++, e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object e : collection) {
            remove(e);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        for (Object e : collection) {
            if (!contains(e))
            remove(e);
        }

        return true;
    }

    @Override
    public void clear() {
        list = null;
        size = 0;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }

        return getFrom(list, size, i);
    }

    @Override
    public E set(int i, E e) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }

        list = setTo(list, size, i, e);

        return e;
    }

    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException();
        }

        resize(size + 1);

        for (int j = size - 1; j > i; j--) {
            set(j, get(j-1));
        }

        set(i, e);
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException();
        }

        E res = get(i);
        for (int j = i + 1; j < size; j++) {
            set(j - 1, get(j));
        }
        resize(size - 1);

        return res;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (get(i).equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int res = -1;

        for (int i = 0; i < size; i++) {
            if (get(i).equals(o)) {
                res = i;
            }
        }

        return res;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return null;
    }

    @Override
    public List<E> subList(int i, int i1) {
        return null;
    }
}
