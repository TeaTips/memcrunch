package org.memcrunch.mem;

import org.memcrunch.util.UnsafeUtil;
import sun.misc.Unsafe;

import static java.lang.String.format;
import static org.memcrunch.util.SizeOf.Type.*;
import static org.memcrunch.util.SizeOf.sizeof;

public final class UnsafeMemory implements Memory {

    private static final Unsafe UNSAFE = UnsafeUtil.instance();

    private final long addr;
    private final long size;
    private final long limit;

    public UnsafeMemory(long address, long size) {
        assert address > 0 : "Address cannot be <= zero";
        assert size > 0 : "Size cannot be <= zero";

        //TODO add pagesize coz DirectByteBuffer does. Revisit this!
        this.addr = address;
        this.size = size;
        this.limit = address + this.size;
    }

    public long size() {
        return size;
    }

    public long address() {
        return addr;
    }

    public long limit() {
        return limit;
    }

    @Override
    public boolean getBool(long offs) {
        assert addr + offs + sizeof(BOOL) < limit : overflowMsg(offs, sizeof(BOOL));
        return UNSAFE.getByte(addr + offs) == 1;
    }

    @Override
    public byte getByte(long offs) {
        assert addr + offs + sizeof(BYTE) < limit : overflowMsg(offs, sizeof(BYTE));
        return UNSAFE.getByte(addr + offs);
    }

    @Override
    public void getBytes(long offs, int len, byte[] dst) {
        //TODO
    }

    @Override
    public char getChar(long offs) {
        assert addr + offs + sizeof(CHAR) < limit : overflowMsg(offs, sizeof(CHAR));
        return UNSAFE.getChar(addr + offs);
    }

    @Override
    public short getShort(long offs) {
        assert addr + offs + sizeof(SHORT) < limit : overflowMsg(offs, sizeof(SHORT));
        return UNSAFE.getShort(addr + offs);
    }

    @Override
    public int getInt(long offs) {
        assert addr + offs + sizeof(INT) < limit : overflowMsg(offs, sizeof(INT));
        return UNSAFE.getInt(addr + offs);
    }

    @Override
    public long getLong(long offs) {
        assert addr + offs + sizeof(LONG) < limit : overflowMsg(offs, sizeof(LONG));
        return UNSAFE.getLong(addr + offs);
    }

    @Override
    public float getFloat(long offs) {
        assert addr + offs + sizeof(FLOAT) < limit : overflowMsg(offs, sizeof(FLOAT));
        return UNSAFE.getFloat(addr + offs);
    }

    @Override
    public double getDouble(long offs) {
        assert addr + offs + sizeof(DOUBLE) < limit : overflowMsg(offs, sizeof(DOUBLE));
        return UNSAFE.getDouble(addr + offs);
    }

    @Override
    public void putBool(long offs, boolean val) {
        assert addr + offs + sizeof(val) < limit : overflowMsg(offs, sizeof(val));
        UNSAFE.putByte(addr + offs, val ? (byte) 1 : 0);
    }

    @Override
    public void putByte(long offs, byte val) {
        assert addr + offs + sizeof(val) < limit : overflowMsg(offs, sizeof(val));
        UNSAFE.putByte(addr + offs, val);
    }

    @Override
    public void putBytes(long offs, int len, byte[] src) {
        //TODO
    }

    @Override
    public void putChar(long offs, char val) {
        assert addr + offs + sizeof(val) < limit : overflowMsg(offs, sizeof(val));
        UNSAFE.putChar(addr + offs, val);
    }

    @Override
    public void putShort(long offs, short val) {
        assert addr + offs + sizeof(val) < limit : overflowMsg(offs, sizeof(val));
        UNSAFE.putShort(addr + offs, val);
    }

    @Override
    public void putInt(long offs, int val) {
        assert addr + offs + sizeof(val) < limit : overflowMsg(offs, sizeof(val));
        UNSAFE.putInt(addr + offs, val);
    }

    @Override
    public void putLong(long offs, long val) {
        assert addr + offs + sizeof(val) < limit : overflowMsg(offs, sizeof(val));
        UNSAFE.putLong(addr + offs, val);
    }

    @Override
    public void putFloat(long offs, float val) {
        assert addr + offs + sizeof(val) < limit : overflowMsg(offs, sizeof(val));
        UNSAFE.putFloat(addr + offs, val);
    }

    @Override
    public void putDouble(long offs, double val) {
        assert addr + offs + sizeof(val) < limit : overflowMsg(offs, sizeof(val));
        UNSAFE.putDouble(addr + offs, val);
    }

    private String overflowMsg(long offs, int sizeof) {
        return format("Overflow: %X + %d + %d (%X) > %X",
                addr, offs, sizeof(BOOL), (addr + offs + sizeof(BOOL)), limit);
    }

    static class Allocator implements MemoryAllocator {

        @Override
        public Memory alloc(long byteCount) {
            //XXX add pagesize coz it's done is DirectByteBuffer
            final long addr = UNSAFE.allocateMemory(byteCount + UNSAFE.pageSize());
            return new UnsafeMemory(addr, byteCount);
        }

        @Override
        public void free(Memory mem) {
            UnsafeMemory umem = (UnsafeMemory) mem;
            UNSAFE.freeMemory(umem.addr);
        }
    }
}
