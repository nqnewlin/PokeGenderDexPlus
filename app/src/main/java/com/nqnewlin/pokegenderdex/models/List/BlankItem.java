package com.nqnewlin.pokegenderdex.models.List;

public class BlankItem extends ListItem {

    public BlankItem() {}

    @Override
    public int getType() { return TYPE_EMPTY; }

    public String getName() { return null; }
}
