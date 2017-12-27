package ru.spbau.gbarto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    @Test
    void isEmpty() {
        Stack<Integer> stk = new Stack<Integer>();
        assertTrue(stk.isEmpty());
    }

    @Test
    void push() {
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(1);
        assertFalse(stk.isEmpty());
    }

    @Test
    void pop() {
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(1);
        stk.pop();
        assertTrue(stk.isEmpty());
    }

    @Test
    void getTop() {
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(8);
        stk.push(8);
        stk.push(0);
        stk.push(0);
        assertEquals(0, (int)stk.getTop());
    }

    @Test
    void clear() {Stack<Integer> stk = new Stack<Integer>();
        stk.push(8);
        stk.push(8);
        stk.push(0);
        stk.push(0);
        stk.clear();
        assertTrue(stk.isEmpty());
    }

}