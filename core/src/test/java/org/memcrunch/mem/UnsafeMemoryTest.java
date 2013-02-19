package org.memcrunch.mem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.memcrunch.util.SizeOf.Type;
import static org.memcrunch.util.SizeOf.sizeof;

public class UnsafeMemoryTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test16MBAllocPutGetAndFree() throws Exception {
        final long SIZE = 16 * 1024 * 1024;

        final UnsafeMemory memory = (UnsafeMemory) Allocators.UNSAFE.alloc(SIZE);
        assertThat(memory.address(), not(equalTo(0L)));
        assertThat(memory.size(), equalTo(SIZE));
        assertThat(memory.limit(), equalTo(memory.address() + memory.size()));

        int ptr = 0;
        memory.putBool(ptr, true);
        ptr += sizeof(Type.BOOL);

        memory.putByte(ptr, (byte) 0xFF);
        ptr += sizeof(Type.BYTE);

        memory.putChar(ptr, 'X');
        ptr += sizeof(Type.CHAR);

        memory.putShort(ptr, (short) 0xFFEE);
        ptr += sizeof(Type.SHORT);

        memory.putInt(ptr, 0xFFEEDDCC);
        ptr += sizeof(Type.INT);

        memory.putLong(ptr, 0xFFEEDDCCBBAA9988L);
        ptr += sizeof(Type.LONG);

        memory.putFloat(ptr, 123.456f);
        ptr += sizeof(Type.FLOAT);

        memory.putDouble(ptr, 123456.7890123);
        ptr += sizeof(Type.DOUBLE);


        ptr = 0;

        assertThat(memory.getBool(ptr), equalTo(true));
        ptr += sizeof(Type.BOOL);

        assertThat(memory.getByte(ptr), equalTo((byte)0xFF));
        ptr += sizeof(Type.BYTE);

        assertThat(memory.getChar(ptr), equalTo('X'));
        ptr += sizeof(Type.CHAR);

        assertThat(memory.getShort(ptr), equalTo((short)0xFFEE));
        ptr += sizeof(Type.SHORT);

        assertThat(memory.getInt(ptr), equalTo(0xFFEEDDCC));
        ptr += sizeof(Type.INT);

        assertThat(memory.getLong(ptr), equalTo(0xFFEEDDCCBBAA9988L));
        ptr += sizeof(Type.LONG);

        assertThat(memory.getFloat(ptr), equalTo(123.456f));
        ptr += sizeof(Type.FLOAT);

        assertThat(memory.getDouble(ptr), equalTo(123456.7890123));
    }
}
