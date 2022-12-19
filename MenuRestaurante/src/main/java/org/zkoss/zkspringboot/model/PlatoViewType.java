package org.zkoss.zkspringboot.model;

public enum PlatoViewType {
    EDIT(0),
    NEW(1),
    READ(2);

    public final int value;

    PlatoViewType(int value) {
        this.value = value;
    }


    public static PlatoViewType getForValue(int value) {
        for (PlatoViewType platoViewType : PlatoViewType.values())
            if (platoViewType.value == value)
                return platoViewType;

        return null;
    }
}