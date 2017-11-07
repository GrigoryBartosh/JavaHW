package ru.spbau.gbarto;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The class contains various methods for working with functions.
 */
public class Collections {
    /**
     * Maps specified function to the elements of specified container.
     *
     * @param f specified function
     * @param l specified container
     * @param <T> type of elements of specified container
     * @param <U> type of result
     * @return array of new values
     */
    public static <T, U> ArrayList<U> map(@NotNull Function1<? super T, ? extends U> f, @NotNull Iterable<? extends T> l) {
        ArrayList<U> nl = new ArrayList<>();

        for (T x : l) {
            nl.add(f.apply(x));
        }

        return nl;
    }

    /**
     * Maps elements of specified container by specified predicate.
     *
     * @param p specified predicate
     * @param l specified container
     * @param <T> type of elements of specified container
     * @return array of filtered values
     */
    public static <T> ArrayList<T> filter(@NotNull Predicate<? super T> p, @NotNull Iterable<? extends T> l) {
        ArrayList<T> nl = new ArrayList<>();

        for (T x : l) {
            if (p.apply(x)) {
                nl.add(x);
            }
        }

        return nl;
    }

    /**
     * Takes prefix of the elements of specified container that satisfy specified predicate.
     *
     * @param p specified predicate
     * @param l specified container
     * @param <T> type of elements of specified container
     * @return array of elements
     */
    public static <T> ArrayList<T> takeWhile(@NotNull Predicate<? super T> p, @NotNull Iterable<? extends T> l) {
        ArrayList<T> nl = new ArrayList<>();

        for (T x : l) {
            if (!p.apply(x)) {
                break;
            }

            nl.add(x);
        }

        return nl;
    }

    /**
     * Takes prefix of the elements of specified container that not satisfy specified predicate.
     *
     * @param p specified predicate
     * @param l specified container
     * @param <T> type of elements of specified container
     * @return array of elements
     */
    public static <T> ArrayList<T> takeUnless(@NotNull Predicate<? super T> p,  @NotNull Iterable<? extends T> l) {
        return takeWhile(p.not(), l);
    }

    private static <T, U> U foldr_helper(@NotNull Function2<? super T, ? super U, ? extends U> f, @NotNull U ini, @NotNull Iterator<? extends T> it) {
        if (it.hasNext()) {
            return f.apply(it.next(), foldr_helper(f, ini, it));
        }

        return ini;
    }

    /**
     * Reduces the specified container using the specified function.
     *
     * @param f specified function
     * @param ini initial value
     * @param l specified container
     * @param <T> type of elements of specified container
     * @param <U> type of result
     * @return result of reduction
     */
    public static <T, U> U foldr(@NotNull Function2<? super T, ? super U, ? extends U> f, @NotNull U ini, @NotNull Iterable<? extends T> l) {
        return foldr_helper(f, ini, l.iterator());
    }

    /**
     * Reduces the specified container using the specified function.
     *
     * @param f specified function
     * @param ini initial value
     * @param l specified container
     * @param <T> type of elements of specified container
     * @param <U> type of result
     * @return result of reduction
     */
    public static <T, U> T foldl(@NotNull Function2<? super T, ? super U, ? extends T> f, @NotNull T ini, @NotNull Iterable<? extends U> l) {
        T res = ini;

        for (U v : l) {
            res = f.apply(res, v);
        }

        return res;
    }
}
