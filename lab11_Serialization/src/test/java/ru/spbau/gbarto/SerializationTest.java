package ru.spbau.gbarto;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.io.*;

public class SerializationTest {
    public static final double EPS = 1e-6;

    public static class A {
        public byte b;
        public int i;
        public short r;
        public long l;
        public float f;
        public double d;
        public String s;

        public boolean equals(Object other) {
            if (!(other instanceof A)) {
                return false;
            }

            A a = (A) other;
            return  b == a.b &&
                    i == a.i &&
                    l == a.l &&
                    r == a.r &&
                    Math.abs(f - a.f) < EPS &&
                    Math.abs(d - a.d) < EPS &&
                    s.equals(a.s);
        }
    };

    public static class B {
        public boolean equals(Object other) {
            return other.getClass() == this.getClass();
        }
    };

    private byte[] serialize(Object structure) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Serialization.serialize(structure, os);

        return os.toByteArray();
    }

    @Test
    public void testAllTypes() throws Exception {
        A a = new A();
        a.s = "8-80";
        a.b = 0;
        a.i = 5;
        a.r = 5;
        a.l = 5;
        a.f = 3.5f;
        a.d = 3.5;

        Object structure = Serialization.deserialize(A.class, new ByteArrayInputStream(serialize(a)));

        assertTrue(a.equals(structure));
    }

    @Test
    public void testEmpty() throws Exception {
        B b = new B();
        Object structure = Serialization.deserialize(B.class, new ByteArrayInputStream(serialize(b)));

        assertTrue(b.equals(structure));
    }
}