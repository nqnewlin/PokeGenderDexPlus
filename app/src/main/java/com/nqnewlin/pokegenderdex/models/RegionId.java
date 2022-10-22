package com.nqnewlin.pokegenderdex.models;

public class RegionId implements Comparable<RegionId> {

    private int id;

    private String name;

    public RegionId(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Override
    public int compareTo(RegionId regionId) {
        return this.getId() - regionId.getId();
    }
}
