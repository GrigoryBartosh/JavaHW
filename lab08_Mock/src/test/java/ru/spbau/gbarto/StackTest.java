package ru.spbau.gbarto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    private Stack<Integer> stk;

    @BeforeEach
    void init() {
        stk = new Stack<>();
    }

    @Test
    void isEmpty() {
        assertTrue(stk.isEmpty());
    }

    @Test
    void push() {
        stk.push(1);
        assertFalse(stk.isEmpty());
    }

    @Test
    void pop() {
        stk.push(1);
        stk.pop();
        assertTrue(stk.isEmpty());
    }

    @Test
    void getTop() {
        stk.push(8);
        stk.push(8);
        stk.push(0);
        stk.push(0);
        assertEquals(0, (int)stk.getTop());
    }

    @Test
    void clear() {
        stk.push(8);
        stk.push(8);
        stk.push(0);
        stk.push(0);
        stk.clear();
        assertTrue(stk.isEmpty());
    }

}