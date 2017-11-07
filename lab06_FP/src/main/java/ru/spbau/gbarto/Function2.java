package ru.spbau.gbarto;

import com.sun.istack.internal.NotNull;

/**
 * Function of two argument ("f (x, y)").
 *
 * @param <T1> type of first argument
 * @param <T2> type of second argument
 * @param <U> type of result
 */
public abstract class Function2<T1, T2, U> {
    /**
     * Returns the result of applying function.
     *
     * @param x first arguments to be applied
     * @param y second arguments to be applied
     * @return the result of applying function
     */
    public abstract U apply(T1 x, T2 y);

    /**
     * Returns composition of two functions.
     *
     * @param g specified function
     * @param <V> type of result
     * @return composition of two functions
     */
    public <V> Function2<T1, T2, V> compose(@NotNull Function1<? super U, ? extends V> g) {
        return new Function2<T1, T2, V>() {
            @Override
            public V apply(@NotNull T1 x, @NotNull T2 y) {
                return g.apply(Function2.this.apply(x, y));
            }
        };
    }

    /**
     * Binds first argument of the function to the specified value.
     *
     * @param x value to be bound with
     * @return function of one argument
     */
    public Function1<T2, U> bind1(@NotNull T1 x) {
        return new Function1<T2, U>() {
            @Override
            public U apply(@NotNull T2 y) {
                return Function2.this.apply(x, y);
            }
        };
    }

    /**
     * Binds second argument of the function to the specified value.
     *
     * @param y value to be bound with
     * @return function of one argument
     */
    public Function1<T1, U> bind2(@NotNull T2 y) {
        return new Function1<T1, U>() {
            @Override
            public U apply(@NotNull T1 x) {
                return Function2.this.apply(x, y);
            }
        };
    }

    /**
     * Curries second argument of the function.
     *
     * @param y value to be substituted
     * @return function of one argument
     */
    public Function1<T1, U> curry(@NotNull T2 y) {
        return bind2(y);
    }
}
