package com.nqnewlin.pokegenderdex.models.List;

public abstract class ListItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_POKE = 1;

    abstract public int getType();

}
