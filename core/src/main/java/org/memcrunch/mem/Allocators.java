package org.memcrunch.mem;

public enum Allocators implements MemoryAllocator {

    UNSAFE(new UnsafeMemory.Allocator());

    private final MemoryAllocator allocator;

    private Allocators(MemoryAllocator allocator) {
        this.allocator = allocator;
    }

    @Override
    public Memory alloc(long byteCount) {
        return allocator.alloc(byteCount);
    }

    @Override
    public void free(Memory mem) {
        allocator.free(mem);
    }
}
