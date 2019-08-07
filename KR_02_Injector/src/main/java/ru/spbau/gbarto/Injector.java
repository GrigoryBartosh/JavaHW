package ru.spbau.gbarto;

import java.lang.reflect.Constructor;
import java.util.*;

public class Injector {
    private static boolean checkImplements(Class<?> implClass, Class<?> type) {
        if (implClass == null) {
            return false;
        }

        for (Class<?> intr : implClass.getInterfaces()) {
            if (intr.equals(type) || checkImplements(intr, type)) {
                return true;
            }
        }

        return checkImplements(implClass.getSuperclass(), type);
    }

    private static boolean checkExtends(Class<?> implClass, Class<?> type) {
        while (implClass.getSuperclass() != null) {
            if (implClass.equals(type)) {
                return true;
            }
            implClass = implClass.getSuperclass();
        }
        return false;
    }

    private static Class<?> getImpl(Class<?> type, List<Class<?>> classes) throws Exception {
        Class<?> impl = null;
        for (Class<?> cls : classes) {
            boolean checked = (type.isInterface() ? checkImplements(cls, type) : checkExtends(cls, type));

            if (checked) {
                if (impl != null) {
                    throw new AmbiguousImplementationException();
                }
                impl = cls;
            }
        }

        if (impl == null) {
            throw new ImplementationNotFoundException();
        }

        return impl;
    }

    private static Object dfs(Class<?> cls, ArrayList<Class<?>> classes, HashSet<Class<?>> edges, HashMap<Class<?>, Object> visited) throws Exception {
        if (visited.containsKey(cls)) {
            return visited.get(cls);
        }
        if (edges.contains(cls)) {
            throw new InjectionCycleException();
        }
        edges.add(cls);

        Constructor<?> constructor = cls.getDeclaredConstructors()[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object res;
        if (parameterTypes.length == 0) {
            res = constructor.newInstance();
        } else {
            List<Object> objs = new ArrayList<>();
            for (Class<?> type : parameterTypes) {
                Class<?> implementationClass = getImpl(type, classes);
                objs.add(dfs(implementationClass, classes, edges, visited));
            }

            res = constructor.newInstance(objs.toArray(new Object[objs.size()]));
        }

        edges.remove(cls);
        visited.put(cls, res);
        return res;
    }

    public static Object initialize(String rootClassName, List<String> implClasses) throws Exception {
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (String cls : implClasses) {
            classes.add(Class.forName(cls));
        }
        Class<?> rootClass = Class.forName(rootClassName);
        classes.add(rootClass);

        return dfs(rootClass, classes, new HashSet<>(), new HashMap<>());
    }
}
