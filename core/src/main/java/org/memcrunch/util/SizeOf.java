package org.memcrunch.util;

public class SizeOf {

    public static enum Type {
        BOOL,
        BYTE,
        CHAR,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE
    }

    public static int sizeof(Type t) {
        switch (t) {
            case BOOL:
                return 1;
            case BYTE:
                return 1;
            case CHAR:
                return 2;
            case SHORT:
                return 2;
            case INT:
                return 4;
            case LONG:
                return 8;
            case FLOAT:
                return 4;
            case DOUBLE:
                return 8;
        }
        throw new IllegalStateException();
    }


    public static int sizeof(boolean v) {
        return 1;
    }

    public static int sizeof(byte v) {
        return 1;
    }

    public static int sizeof(char v) {
        return 2;
    }

    public static int sizeof(short v) {
        return 2;
    }

    public static int sizeof(int v) {
        return 4;
    }

    public static int sizeof(long v) {
        return 8;
    }

    public static int sizeof(float v) {
        return 4;
    }

    public static int sizeof(double v) {
        return 8;
    }

}
