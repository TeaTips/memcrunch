package org.memcrunch.mem;

public interface Memory {

    long size();

    boolean getBool(long offs);

    byte getByte(long offs);

    void getBytes(long offs, int len, byte[] dst);

    char getChar(long offs);

    short getShort(long offs);

    int getInt(long offs);

    long getLong(long offs);

    float getFloat(long offs);

    double getDouble(long offs);

    void putBool(long offs, boolean val);

    void putByte(long offs, byte val);

    void putBytes(long offs, int len, byte[] src);

    void putChar(long offs, char val);

    void putShort(long offs, short val);

    void putInt(long offs, int val);

    void putLong(long offs, long val);

    void putFloat(long offs, float val);

    void putDouble(long offs, double val);

}
