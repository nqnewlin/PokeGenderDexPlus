package com.nqnewlin.pokegenderdex.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "pokedex_table")
public class Pokemon {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "evolution_chain")
    private int evoChain;

    @ColumnInfo(name = "is_baby")
    private boolean baby;

    @ColumnInfo(name = "gender_difference")
    private boolean genderDifference;

    @ColumnInfo(name = "gender_rate")
    private int genderRate;

    @ColumnInfo(name = "has_forms")
    private boolean hasForms;

    @ColumnInfo(name = "forms")
    private String forms;

    @ColumnInfo(name = "is_form")
    private boolean form;

    @ColumnInfo(name = "types")
    private String types;

    @ColumnInfo(name = "is_legendary")
    private boolean legendary;

    @ColumnInfo(name = "is_mythical")
    private boolean mythical;

    @ColumnInfo(name = "region")
    private String region;


    //Add fields for owned data

    @ColumnInfo(name = "is_shadow")
    private boolean isShadow;

    @ColumnInfo(name = "owned_shadow")
    private boolean ownedShadow;

    @ColumnInfo(name = "owned_male")
    private boolean ownedMale;

    @ColumnInfo(name = "owned_female")
    private boolean ownedFemale;

    @ColumnInfo(name = "owned_genderless")
    private boolean ownedGenderless;

    @ColumnInfo(name = "owned_shiny")
    private boolean ownedShiny;

    public Pokemon(@NotNull int id,
                   @NotNull String name,
                   @NotNull int evoChain,
                   @NotNull boolean baby,
                   @NotNull boolean genderDifference,
                   @NotNull int genderRate,
                   @NotNull boolean hasForms,
                   String forms,
                   @NotNull boolean form,
                   @NotNull String types,
                   @NotNull boolean legendary,
                   @NotNull boolean mythical,
                   @NotNull String region,
                   @NotNull boolean isShadow,
                   @NotNull boolean ownedShadow,
                   @NotNull boolean ownedFemale,
                   @NotNull boolean ownedMale,
                   @NotNull boolean ownedGenderless,
                   @NotNull boolean ownedShiny
    ){
        this.id = id;
        this.name = name;
        this.evoChain = evoChain;
        this.baby = baby;
        this.genderDifference = genderDifference;
        this.genderRate = genderRate;
        this.hasForms = hasForms;
        this.forms = forms;
        this.form = form;
        this.types = types;
        this.legendary = legendary;
        this.mythical = mythical;
        this.region = region;
        this.isShadow = isShadow;
        this.ownedShadow = ownedShadow;
        this.ownedFemale = ownedFemale;
        this.ownedMale = ownedMale;
        this.ownedGenderless = ownedGenderless;
        this.ownedShiny = ownedShiny;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEvoChain() { return evoChain; }
    public void setEvoChain(int evoChain) { this.evoChain = evoChain; }

    public boolean isBaby() {
        return baby;
    }

    public void setBaby(boolean baby) {
        this.baby = baby;
    }

    public boolean isGenderDifference() {
        return genderDifference;
    }

    public void setGenderDifference(boolean genderDifference) {
        this.genderDifference = genderDifference;
    }

    public int getGenderRate() {
        return genderRate;
    }

    public void setGenderRate(int genderRate) {
        this.genderRate = genderRate;
    }

    public boolean isHasForms() {
        return hasForms;
    }

    public void setHasForms(boolean hasForms) {
        this.hasForms = hasForms;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public String getForms() { return forms; }

    public void setForms(String forms) {
        this.forms = forms;
    }

    public boolean isLegendary() {
        return legendary;
    }

    public void setLegendary(boolean legendary) {
        this.legendary = legendary;
    }

    public boolean isMythical() {
        return mythical;
    }

    public void setMythical(boolean mythical) {
        this.mythical = mythical;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isShadow() { return isShadow; }

    public void setShadow(boolean shadow) { this.isShadow = shadow; }

    public void setOwnedShadow(boolean ownedShadow) {
        this.ownedShadow = ownedShadow;
    }

    public boolean isOwnedShadow() { return ownedShadow; }

    public boolean isOwnedFemale() {
        return ownedFemale;
    }

    public void setOwnedFemale(boolean ownedFemale) { this.ownedFemale = ownedFemale; }

    public boolean isOwnedMale() {
        return ownedMale;
    }

    public void setOwnedGenderless(boolean ownedGenderless) {
        this.ownedGenderless = ownedGenderless;
    }

    public boolean isOwnedGenderless() {
        return ownedGenderless;
    }

    public void setOwnedMale(boolean ownedFemale) { this.ownedMale = ownedMale; }

    public boolean isOwnedShiny() {
        return ownedShiny;
    }

    public void setOwnedShiny(boolean ownedShiny) { this.ownedShiny = ownedShiny; }

    @Override
    public String toString() {
        String pokemon = "ID: " + this.getId() +
                " Name: " + this.getName() +
                " EvoChain: " + this.getEvoChain() +
                " baby: " + this.isBaby() +
                " genderDifference: " + this.genderDifference +
                " genderRate: " + this.genderRate +
                " hasForms: " + this.hasForms +
                " forms: " + this.forms +
                " form: " + this.form +
                " types: " + this.types +
                " legendary: " + this.legendary +
                " mythical: " + this.mythical;
        return pokemon;
    }
}
