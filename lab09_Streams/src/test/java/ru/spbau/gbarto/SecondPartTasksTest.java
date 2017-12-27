package ru.spbau.gbarto;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static ru.spbau.gbarto.SecondPartTasks.piDividedBy4;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        List<String> res = SecondPartTasks.findQuotes(Arrays.asList(
                "src/test/resources/a.txt",
                "src/test/resources/b.txt",
                "src/test/resources/c.txt"),
                "java");
        Collections.sort(res);

        List<String> ans = Arrays.asList(   "FirstPartTasks.java",
                                            "SecondPartTasks.java",
                                            "java",
                                            "javascript",
                                            "not in java though");

        assertArrayEquals(ans.toArray(), res.toArray());
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, piDividedBy4(), 1e-3);
    }

    @Test
    public void testFindPrinter() {
        TreeMap<String, List<String>> map = new TreeMap<>();
        map.put("1", Arrays.asList("a", "b", "c", "d"));
        map.put("2", Arrays.asList("aaa", "bbb", "ccc"));
        map.put("3", Arrays.asList("a", "bcbcb", "ca"));
        map.put("4", Arrays.asList("ac", "cbb", "ba"));
        assertEquals("2", SecondPartTasks.findPrinter(map));
    }

    @Test
    public void testCalculateGlobalOrder() {
        TreeMap<String, Integer> x = new TreeMap<>();
        TreeMap<String, Integer> y = new TreeMap<>();
        TreeMap<String, Integer> z = new TreeMap<>();

        x.put("a", 8);
        y.put("a", 8);
        y.put("b", 5);
        z.put("b", 5);
        x.put("c", 5);
        y.put("c", 3);
        z.put("c", 5);
        x.put("d", 3);
        y.put("d", 5);

        ArrayList<Map<String, Integer>> request = new ArrayList<>();
        request.addAll(Arrays.asList(x, y, z));

        TreeMap<String, Integer> ans = new TreeMap<>();
        ans.put("a", 16);
        ans.put("b", 10);
        ans.put("c", 13);
        ans.put("d", 8);

        assertArrayEquals(ans.entrySet().toArray(), SecondPartTasks.calculateGlobalOrder(request).entrySet().toArray());
    }
}