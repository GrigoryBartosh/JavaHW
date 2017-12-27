package ru.spbau.gbarto;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * A container from one element, in which there may or may not be a value.
 *
 * @param <T> a type of object which is storing.
 */
public class Maybe<T> {
    private T val;
    private static Maybe maybeNothing = null;

    private Maybe(T object) {
        val = object;
    }

    /**
     * Creates a new object of type Maybe, which stores the value of object.
     *
     * @param object an element to store.
     * @param <U> a class of element to store.
     * @return a new Maybe object.
     */
    public static <U> Maybe<U> just(@NotNull U object) {
        return new Maybe<U>(object);
    }

    /**
     * Creates a new object of type Maybe without a stored value.
     *
     * @param <U> a class of element.
     * @return a new Maybe object.
     */
    public static <U> Maybe<U> nothing() {
        if (maybeNothing == null) {
            maybeNothing = new Maybe(null);
        }
        return (Maybe<U>) maybeNothing;
    }

    /**
     * Returns the stored value if it is, otherwise throws an exception.
     *
     * @return the stored value if it is, otherwise throws an exception.
     */
    public T get() throws MaybeException {
        if (val == null) {
            throw new MaybeException("data is null pointer");
        }
        return val;
    }

    /**
     * Returns True if the value is, otherwise False
     */
    public boolean isPresent() {
        return val != null;
    }

    /**
     * Takes a function and returns a new Maybe object with the value obtained by applying the function to the stored value or empty Maybe, if the current Maybe is empty.
     *
     * @param mapper a function to apply
     * @return result a new Maybe object with the value obtained by applying the function to the stored value or empty Maybe, if the current Maybe is empty.
     */
    public Maybe<T> map(Function<? super T, ? extends T> mapper) {
        if (val == null) {
            return nothing();
        }
        return new Maybe<T>(mapper.apply(val));
    }
}
