package com.takoy3466.manaitamtk.api;

public final class MTKInt {
    private final int baseInt;
    private final int multiple;
    private final int remainder;

    public MTKInt(int baseInt, int multiple, int remainder) {
        this.baseInt = baseInt;
        this.multiple = multiple;
        this.remainder = remainder;
    }

    public int getBaseInt() {
        return baseInt;
    }

    public int getMultiple() {
        return multiple;
    }

    public int getRemainder() {
        return remainder;
    }

}
