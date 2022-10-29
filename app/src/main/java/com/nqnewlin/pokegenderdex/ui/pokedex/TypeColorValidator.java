package com.nqnewlin.pokegenderdex.ui.pokedex;

import com.nqnewlin.pokegenderdex.R;

public class TypeColorValidator {

    public static int colorValidator(String type) {
        switch (type) {
            case "normal": {
                return R.color.normal;
            }
            case "fire": {
                return R.color.fire;
            }
            case "water": {
                return R.color.water;
            }
            case "electric": {
                return R.color.electric;
            }
            case "grass": {
                return R.color.grass;
            }
            case "ice": {
                return R.color.ice;
            }
            case "fighting": {
                return R.color.fighting;
            }
            case "poison": {
                return R.color.poison;
            }
            case "ground": {
                return R.color.ground;
            }
            case "flying": {
                return R.color.flying;
            }
            case "psychic": {
                return R.color.psychic;
            }
            case "bug": {
                return R.color.bug;
            }
            case "rock": {
                return R.color.rock;
            }
            case "ghost": {
                return R.color.ghost;
            }
            case "dragon": {
                return R.color.dragon;
            }
            case "dark": {
                return R.color.dark;
            }
            case "steel": {
                return R.color.steel;
            }
            case "fairy": {
                return R.color.fairy;
            }
            default: {
                return R.color.white;
            }
        }
    }
}





