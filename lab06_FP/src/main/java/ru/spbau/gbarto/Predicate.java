package ru.spbau.gbarto;

import org.jetbrains.annotations.NotNull;

/**
 * Predicate for one argument.
 *
 * @param <T> type of argument
 */
public abstract class Predicate<T> extends Function1<T, Boolean> {
    /**
     * Constant function that always returns true.
     */
    public static <U> Predicate<U> ALWAYS_TRUE() {
        return new Predicate<U>() {
            @Override
            public Boolean apply(Object x) {
                return Boolean.TRUE;
            }
        };
    }

    /**
     * Constant function that always returns false.
     */
    public static <U> Predicate<U> ALWAYS_FALSE() {
        return new Predicate<U>() {
            @Override
            public Boolean apply(Object x) {
                return Boolean.FALSE;
            }
        };
    }

    /**
     * Returns the result of applying function.
     *
     * @param x arguments to be applied
     * @return the result of applying function
     */
    public abstract Boolean apply(@NotNull T x);

    /**
     * Returns predicate that equals to the operator or.
     *
     * @param p specified predicate
     * @return predicate that equals to the operator or
     */
    public Predicate<T> or(@NotNull Predicate<? super T> p) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(@NotNull T x) {
                return Predicate.this.apply(x) || p.apply(x);
            }
        };
    }

    /**
     * Returns predicate that equals to the operator and.
     *
     * @param p specified predicate
     * @return predicate that equals to the operator and
     */
    public Predicate<T> and(@NotNull Predicate<? super T> p) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(@NotNull T x) {
                return Predicate.this.apply(x) && p.apply(x);
            }
        };
    }

    /**
     * Returns predicate that equals to the operator not.
     *
     * @return predicate that equals to the operator not
     */
    public Predicate<T> not() {
        return new Predicate<T>() {
            @Override
            public Boolean apply(@NotNull T x) {
                return !Predicate.this.apply(x);
            }
        };
    }
}
