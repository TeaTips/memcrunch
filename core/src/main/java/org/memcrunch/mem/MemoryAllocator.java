package org.memcrunch.mem;

public interface MemoryAllocator {

    Memory alloc(long byteCount);

    void free(Memory mem);

}
