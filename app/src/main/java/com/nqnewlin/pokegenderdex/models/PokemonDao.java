package com.nqnewlin.pokegenderdex.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonDao {

    //TODO add query for all nonForm pokemon

    @Query("SELECT * FROM pokedex_table")
    List<Pokemon> test();

    @Query("SELECT * FROM pokedex_table")
    LiveData<List<Pokemon>> getAll();

    @Query("SELECT * FROM pokedex_table WHERE id IN (:pokeId)")
    LiveData<Pokemon> loadById(int pokeId);

    @Query("SELECT * FROM pokedex_table WHERE evolution_chain IN (:chain)")
    List<Pokemon> loadAllByChain(int chain);

    @Query("SELECT * FROM pokedex_table WHERE region LIKE :region")
    List<Pokemon> findByRegion(String region);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pokemon pokemon);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Pokemon> pokemons);

    @Delete
    void delete(Pokemon pokemon);

    @Query("DELETE FROM pokedex_table")
    void deleteAll();
}
