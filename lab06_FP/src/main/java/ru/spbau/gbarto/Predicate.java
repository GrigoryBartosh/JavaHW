package ru.spbau.gbarto;

import com.sun.istack.internal.NotNull;

/**
 * Predicate for one argument.
 *
 * @param <T> type of argument
 */
public abstract class Predicate<T> extends Function1<T, Boolean> {
    /**
     * Predicate that always equals TRUE.
     */
    public static final Predicate ALWAYS_TRUE = new Predicate() {
        @Override
        public Boolean apply(Object x) {
            return Boolean.TRUE;
        }
    };

    /**
     * Predicate that always equals False.
     */
    public static final Predicate ALWAYS_FALSE = new Predicate() {
        @Override
        public Boolean apply(Object x) {
            return Boolean.FALSE;
        }
    };

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
