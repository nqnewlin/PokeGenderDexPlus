package com.nqnewlin.pokegenderdex.models.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Types {

    public static List<String> types = Arrays.asList("Normal", "Fire", "Water", "Electric",
            "Grass", "Ice", "Fighting", "Poison", "Ground", "Flying", "Psychic", "Bug",
            "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy");

    private String name;

    private boolean selected;

    public Types() {}

    public Types(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public void setSelected(boolean selected) { this.selected = selected; }

    public boolean getSelected() { return selected; }

    public static List<Types> buildList() {
        List<Types> typeList = new ArrayList<>();
        for (String typeString : types) {
            Types type = new Types(typeString, true);
            typeList.add(type);
        }
        return typeList;
    }

}
