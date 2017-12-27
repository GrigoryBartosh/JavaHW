package ru.spbau.gbarto;

import org.jetbrains.annotations.NotNull;

/**
 * Function of one argument ("f (x)").
 *
 * @param <T> type of argument
 * @param <U> type of result
 */
public abstract class Function1<T, U> {
    /**
     * Returns the result of applying function.
     *
     * @param x arguments to be applied
     * @return the result of applying function.
     */
    public abstract U apply(T x);

    /**
     * Returns composition of two functions.
     *
     * @param g specified function
     * @param <V> type of result
     * @return composition of two functions
     */
    public <V> Function1<T, V> compose(@NotNull Function1<? super U, ? extends V> g) {
        return new Function1<T, V>() {
            @Override
            public V apply(@NotNull T x) {
                return g.apply(Function1.this.apply(x));
            }
        };
    }
}
