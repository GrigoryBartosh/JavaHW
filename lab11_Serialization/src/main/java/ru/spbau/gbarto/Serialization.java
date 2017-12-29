package ru.spbau.gbarto;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Realization of serialization of structures.
 */
public class Serialization {

    /**
     * Writes structure to stream
     *
     * @param structure object to write
     * @param os output stream
     * @throws IOException on io fail
     * @throws ReflectiveOperationException when failed to work with class
     * @throws RuntimeException on bad object
     */
    public static void serialize(Object structure, OutputStream os) throws IOException, ReflectiveOperationException, RuntimeException {
        Class<?> clss = structure.getClass();
        Field[] fields = clss.getFields();
        Arrays.sort(fields, Comparator.comparing(Field::getName));

        DataOutputStream dos = new DataOutputStream(os);
        for (Field f: fields) {
            if (f.getType() == byte.class) {
                dos.writeByte(f.getByte(structure));
            } else if (f.getType() == short.class) {
                dos.writeShort(f.getShort(structure));
            } else if (f.getType() == int.class) {
                dos.writeInt(f.getInt(structure));
            } else if (f.getType() == long.class) {
                dos.writeLong(f.getLong(structure));
            } else if (f.getType() == float.class) {
                dos.writeFloat(f.getFloat(structure));
            } else if (f.getType() == double.class) {
                dos.writeDouble(f.getDouble(structure));
            } else if (f.getType() == String.class) {
                dos.writeUTF((String) f.get(structure));
            } else
                throw new RuntimeException("bad input");
        }
    }

    /**
     * Reads structure from stream
     *
     * @param clss given class
     * @param is input stream
     * @throws IOException on io fail
     * @throws ReflectiveOperationException when failed to work with class
     * @throws RuntimeException on bad object
     */
    public static <T> T deserialize(Class<T> clss, InputStream is) throws IOException, ReflectiveOperationException {
        T structure = clss.newInstance();
        Field[] fields = clss.getFields();
        Arrays.sort(fields, Comparator.comparing(Field::getName));

        DataInputStream dataIs = new DataInputStream(is);
        for (Field f: fields) {
            if (f.getType() == byte.class) {
                f.setByte(structure, dataIs.readByte());
            } else if (f.getType() == short.class) {
                f.setShort(structure, dataIs.readShort());
            }  else if (f.getType() == int.class) {
                f.setInt(structure, dataIs.readInt());
            } else if (f.getType() == long.class) {
                f.setLong(structure, dataIs.readLong());
            } else if (f.getType() == float.class) {
                f.setFloat(structure, dataIs.readFloat());
            } else if (f.getType() == double.class) {
                f.setDouble(structure, dataIs.readDouble());
            } else if (f.getType() == String.class) {
                f.set(structure, dataIs.readUTF());
            } else{
                throw new RuntimeException("bad input");
            }
        }

        return structure;
    }
}
