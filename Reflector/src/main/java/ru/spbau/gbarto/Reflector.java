package ru.spbau.gbarto;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reflector {
    private static StringBuilder printPackage(@NotNull String packageName) {
        StringBuilder str = new StringBuilder();

        str.append("package ");
        str.append(packageName);
        str.append(";\n");

        return str;
    }

    private static Set<String> getImportsType(@NotNull Class<?> type) {
        Set<String> imports = new HashSet<>();

        if (!type.isPrimitive() && type != Object.class) {
            if (type.isArray()) {
                imports.addAll(getImportsType(type.getComponentType()));
            } else {
                imports.add(type.getCanonicalName());
            }
        }

        return imports;
    }

    private static Set<String> getImportsFields(@NotNull Field[] declaredFields) {
        Set<String> imports = new HashSet<>();

        Arrays.stream(declaredFields).forEach(f -> imports.addAll(getImportsType(f.getType())));

        return imports;
    }

    private static Set<String> getImportsConstructors(@NotNull Constructor<?>[] constructors) {
        Set<String> imports = new HashSet<>();

        for (Constructor<?> cons : constructors) {
            for (Class<?> type : cons.getParameterTypes()) {
                imports.addAll(getImportsType(type));
            }
        }

        return imports;
    }

    private static Set<String> getImportsMethod(@NotNull Class<?> someClass) {
        Set<String> imports = new HashSet<>();

        for (Method method : someClass.getDeclaredMethods()) {
            for (Class<?> type : method.getParameterTypes()) {
                imports.addAll(getImportsType(type));
            }

            if (method.getReturnType() != someClass) {
                imports.addAll(getImportsType(method.getReturnType()));
            }
        }

        return imports;
    }

    private static Set<String> getImportsInherited(@NotNull Class someClass) {
        Set<String> imports = new HashSet<>();

        if (someClass.getSuperclass() != null) {
            imports.addAll(getImportsType(someClass.getSuperclass()));
        }

        for (Class<?> superclass : someClass.getInterfaces()) {
            imports.addAll(getImportsType(superclass));
        }

        return imports;
    }

    private static Set<String> getImportsInner(@NotNull Class[] classes) {
        Set<String> imports = new HashSet<>();

        for (Class<?> inner : classes) {
            imports.addAll(collectImports(inner));
        }

        return imports;
    }

    private static Set<String> collectImports(@NotNull Class<?> someClass) {
        Set<String> imports = new HashSet<>();

        imports.addAll(getImportsFields(someClass.getDeclaredFields()));
        imports.addAll(getImportsConstructors(someClass.getConstructors()));
        imports.addAll(getImportsMethod(someClass));

        imports.addAll(getImportsInherited(someClass));
        imports.addAll(getImportsInner(someClass.getDeclaredClasses()));

        return imports;
    }

    private static StringBuilder printImports(@NotNull Class<?> someClass)
            throws NoSuchMethodException {
        Set<String> imports = collectImports(someClass);

        StringBuilder text = new StringBuilder();
        text.append("\n");
        imports.stream().map(s -> "import " + s + ";\n").forEach(text::append);

        return text;
    }

    private static String getClassGenericSignature(@NotNull Class<?> someClass) {
        TypeVariable<? extends Class<?>>[] parameters = someClass.getTypeParameters();

        String str = "";
        if (parameters.length > 0) {
            str = Arrays.stream(parameters)
                    .map(TypeVariable::getName)
                    .collect(Collectors.joining(", ", "<", ">"));
        }

        return str;
    }

    private static StringBuilder getDependencies(@NotNull Class<?> someClass) {
        StringBuilder text = new StringBuilder();

        Class<?> superClass = someClass.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            text.append(" extends ");
            text.append(superClass.getSimpleName());
        }

        if (someClass.getInterfaces().length != 0 || someClass.getGenericInterfaces().length != 0) {
            text.append(
                    Arrays.stream(someClass.getInterfaces())
                        .map(i -> " " + i.getSimpleName())
                        .collect(Collectors.joining(",", " implements", ""))
            );
        }

        return text;
    }

    private static StringBuilder printHeader(@NotNull Class<?> someClass) {
        StringBuilder text = new StringBuilder();

        text.append(Modifier.toString(someClass.getModifiers()));
        text.append(" ");
        text.append(someClass.isInterface() ? "" : "class ");
        text.append(someClass.getSimpleName());
        text.append(getClassGenericSignature(someClass));

        text.append(getDependencies(someClass));
        text.append(" {\n");
        return text;
    }

    private static String getGenericFieldType(@NotNull Field field) {
        return field.getGenericType().getTypeName();
    }

    private static String fieldToString(@NotNull Field field) {
        return Modifier.toString(field.getModifiers()) +
                " " +
                getGenericFieldType(field) +
                " " +
                field.getName();
    }

    private static String getDefaultValue(@NotNull Class<?> type) {
        if (!type.isPrimitive()) {
            return "null";
        }

        if (type == boolean.class) {
            return "false";
        }

        return "0";
    }

    private static StringBuilder printFields(@NotNull Field[] declaredFields) {
        StringBuilder text = new StringBuilder();

        for (Field f : declaredFields) {
            text.append(fieldToString(f));

            if (Modifier.isFinal(f.getModifiers())) {
                text.append(" = ");
                text.append(getDefaultValue(f.getType()));
            }

            text.append(";\n");
        }

        return text;
    }

    private static String makeGenericSignature(@NotNull Executable executable) {
        Pattern pattern = Pattern.compile("(<.*?>)");
        String str = executable.toGenericString();
        Matcher matcher = pattern.matcher(str.substring(0, str.indexOf('(')));
        return matcher.find() ? matcher.group(1) + " " : "";
    }

    private static String makeParameterList(@NotNull Executable executable) {
        Parameter[] parameters = executable.getParameters();
        Type[] parameterTypes = executable.getGenericParameterTypes();

        Stream.Builder<String> builder = Stream.builder();
        for (int i = 0; i < parameters.length; i++) {
            builder.add(parameterTypes[i].getTypeName() + " " + parameters[i].getName());
        }
        return builder.build().collect(Collectors.joining(", ", "(", ")"));
    }

    private static StringBuilder printConstructors(@NotNull Class<?> someClass) {
        StringBuilder text = new StringBuilder();

        for (Constructor constructor : someClass.getDeclaredConstructors()) {
            text.append(constructor.getModifiers() == 0 ?
                    "" : Modifier.toString(constructor.getModifiers()) + " ")
                    .append(makeGenericSignature(constructor))
                    .append(someClass.getSimpleName())
                    .append(makeParameterList(constructor))
                    .append(" {\n}\n\n");
        }

        return text;
    }

    private static String methodToString(@NotNull Method method) {
        int modifiers = method.getModifiers();

        String str = "";
        if (modifiers != 0) {
            str =   Modifier.toString(modifiers) +
                    " " + makeGenericSignature(method) +
                    method.getReturnType().getSimpleName() +
                    " " + method.getName() +
                    makeParameterList(method);
        }

        return str;
    }

    private static String getContent(@NotNull Method method) {
        Class<?> cls = method.getReturnType();

        if (cls == void.class) {
            return "";
        }

        String val = "null";
        if (cls.isPrimitive()) {
            val = "0";
        }
        if (cls == boolean.class) {
            val = "false";
        }

        return "return " + val + ";";
    }

    private static StringBuilder printMethods(@NotNull Class<?> someClass) {
        StringBuilder text = new StringBuilder();

        for (Method method : someClass.getDeclaredMethods()) {
            int mod = method.getModifiers();
            text.append(methodToString(method));

            if (!Modifier.isNative(mod) && !Modifier.isAbstract(mod)) {
                text.append(" {\n");
                text.append(getContent(method));
                text.append("\n}");
            } else {
                text.append(";");
            }
            text.append("\n\n");
        }

        return text;
    }

    private static StringBuilder printClass(@NotNull Class<?> someClass) {
        StringBuilder text = new StringBuilder();

        text.append("\n");
        text.append(printHeader(someClass));
        text.append(printFields(someClass.getDeclaredFields()));
        text.append("\n");
        text.append(printConstructors(someClass));
        text.append(printMethods(someClass));

        for (Class<?> inner : someClass.getDeclaredClasses()) {
            text.append(printClass(inner));
        }

        text.append("}");
        return text;
    }

    private static void writeToFile(@NotNull String fileName, @NotNull String text)
            throws IOException {

        File file = new File(fileName);
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(text);
        }
    }

    public static void printStructure(@NotNull Class<?> someClass, String path, @NotNull String packageName)
            throws IOException, NoSuchMethodException {
        String text =  String.valueOf(printPackage(packageName)) +
                            printImports(someClass) +
                            printClass(someClass);

        writeToFile(path + someClass.getSimpleName() + ".java", text);
    }

    private static boolean printDifferent(@NotNull Class<?> classA, @NotNull Class<?> classB, @NotNull PrintStream writer) {
        Set<String> fields = Arrays.stream(classA.getDeclaredFields())
                .map(Reflector::fieldToString)
                .collect(Collectors.toCollection(HashSet::new));

        Set<String> methods = Arrays.stream(classA.getDeclaredMethods())
                .map(Reflector::methodToString)
                .collect(Collectors.toCollection(HashSet::new));

        boolean resFields = Arrays.stream(classB.getDeclaredFields())
                .map(Reflector::fieldToString)
                .filter(s -> !fields.contains(s))
                .peek(writer::println)
                .count() > 0;

        boolean resMethods = Arrays.stream(classB.getDeclaredMethods())
                .map(Reflector::methodToString)
                .filter(s -> !methods.contains(s))
                .peek(writer::println)
                .count() > 0;

        return resFields || resMethods;
    }

    public static boolean diffClasses(@NotNull Class<?> classA, @NotNull Class<?> classB, @NotNull PrintStream writer) {
        return printDifferent(classA, classB, writer)  |
               printDifferent(classB, classA, writer);
    }
}
