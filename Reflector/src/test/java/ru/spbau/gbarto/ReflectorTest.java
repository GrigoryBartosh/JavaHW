package ru.spbau.gbarto;

import org.junit.Test;
import ru.spbau.gbarto.classes.*;

import java.io.*;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class ReflectorTest {
    private final String generatedClassPath = "src/test/java/ru/spbau/gbarto/generated/";
    private final String packageName = "ru.spbau.gbarto.generated";
    private final String classPath = "src/test/java/ru/spbau/gbarto/classes/";

    private boolean check(String fileA, String fileB) throws IOException {
        Scanner readerA = new Scanner(new File(fileA));
        Scanner readerB = new Scanner(new File(fileB));

        if (!readerA.hasNext() || !readerB.hasNext()) {
            return false;
        }

        readerA.nextLine();
        readerB.nextLine();

        String strA = "";
        String strB = "";
        while (readerA.hasNext() && readerB.hasNext()) {
            while (readerA.hasNext()) {
                strA = readerA.nextLine().replace(" ", "");
                if (strA.length() != 0) {
                    break;
                }
            }

            while (readerB.hasNext()) {
                strB = readerB.nextLine().replace(" ", "");
                if (strB.length() != 0) {
                    break;
                }
            }

            if (strA.length() != 0 && strB.length() != 0 && !strA.equals(strB)) {
                return false;
            }
        }

        return readerA.hasNext() == readerB.hasNext();
    }

    @Test
    public void testPrint() throws IOException, NoSuchMethodException {
        Reflector.printStructure(Empty.class, generatedClassPath, packageName);
        Reflector.printStructure(A.class, generatedClassPath, packageName);
        Reflector.printStructure(B.class, generatedClassPath, packageName);
        Reflector.printStructure(G.class, generatedClassPath, packageName);

        assertEquals(true, check(generatedClassPath + "Empty.java", classPath + "Empty.java"));
        assertEquals(true, check(generatedClassPath + "A.java", classPath + "A.java"));
        assertEquals(true, check(generatedClassPath + "B.java", classPath + "B.java"));
        assertEquals(true, check(generatedClassPath + "G.java", classPath + "G.java"));
    }

    @Test
    public void testDiffIntegerLong() throws IOException, NoSuchMethodException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertEquals(true, Reflector.diffClasses(Integer.class, Long.class, System.out));

        System.setOut(null);
    }

    @Test
    public void testDiffAB() throws IOException, NoSuchMethodException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertEquals(true, Reflector.diffClasses(A.class, B.class, System.out));
        assertEquals("public int getB()", outContent.toString().trim());

        System.setOut(null);
    }

    @Test
    public void testDiffPrint() throws IOException, NoSuchMethodException, ClassNotFoundException {
        Reflector.printStructure(A.class, generatedClassPath, packageName);
        Class <?> generated = ClassLoader.getSystemClassLoader().loadClass(packageName + ".A");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertEquals(false, Reflector.diffClasses(generated, A.class, System.out));
        assertEquals("", outContent.toString().trim());

        System.setOut(null);
    }
}