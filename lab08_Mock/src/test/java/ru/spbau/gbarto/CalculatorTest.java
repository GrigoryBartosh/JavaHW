package ru.spbau.gbarto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorTest {

    private Stack<Double> mockedStackValues;

    @Mock
    private Stack<Character> operatorsMock;
    @Mock
    private Stack<Double> valuesMock;

    @Test
    void testCalculateReversePolishNotation() {
        MockitoAnnotations.initMocks(this);
        Calculator calculator = new Calculator(operatorsMock, valuesMock);

        String expression = "1+(3+3)+5";

        doNothing().when(operatorsMock).push(anyChar());
        when(operatorsMock.pop()).thenReturn('+', '(', '+', '+');
        when(operatorsMock.isEmpty()).thenReturn(true, false, false, false, false, true, false, true);
        when(operatorsMock.getTop()).thenReturn('(', '+', '(', '+');

        assertEquals("133++5+", calculator.getReversePolishNotation(expression));

        InOrder inOrder = inOrder(operatorsMock);
        inOrder.verify(operatorsMock).push('+');
        inOrder.verify(operatorsMock).push('(');
        inOrder.verify(operatorsMock).push('+');
        inOrder.verify(operatorsMock).pop();
        inOrder.verify(operatorsMock).pop();
        inOrder.verify(operatorsMock).pop();
        inOrder.verify(operatorsMock).push('+');
        inOrder.verify(operatorsMock).pop();
    }

    @Test
    void testCalculateExpression() {
        MockitoAnnotations.initMocks(this);
        Calculator calculator = new Calculator(operatorsMock, valuesMock);

        String expression = "1374-*+5/";

        doNothing().when(valuesMock).push(anyDouble());
        when(valuesMock.pop()).thenReturn(4.0, 7.0, 3.0, 3.0, 9.0, 1.0, 5.0, 10.0, 2.0);

        assertEquals(2.0, calculator.calculateExpression(expression));

        InOrder inOrder = inOrder(valuesMock);
        inOrder.verify(valuesMock).push(1.0);
        inOrder.verify(valuesMock).push(3.0);
        inOrder.verify(valuesMock).push(7.0);
        inOrder.verify(valuesMock).push(4.0);
        inOrder.verify(valuesMock, times(2)).pop();
        inOrder.verify(valuesMock).push(3.0);
        inOrder.verify(valuesMock, times(2)).pop();
        inOrder.verify(valuesMock).push(9.0);
        inOrder.verify(valuesMock, times(2)).pop();
        inOrder.verify(valuesMock).push(10.0);
        inOrder.verify(valuesMock).push(5.0);
        inOrder.verify(valuesMock, times(2)).pop();
        inOrder.verify(valuesMock).push(2.0);
        inOrder.verify(valuesMock).pop();
    }
}

