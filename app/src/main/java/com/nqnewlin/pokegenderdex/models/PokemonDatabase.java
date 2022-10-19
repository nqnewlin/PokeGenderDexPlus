package com.nqnewlin.pokegenderdex.models;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nqnewlin.pokegenderdex.App;
import com.nqnewlin.pokegenderdex.csv.CsvService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
abstract class PokemonDatabase extends RoomDatabase {
    abstract PokemonDao pokemonDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile PokemonDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PokemonDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PokemonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PokemonDatabase.class, "pokemon_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.

                PokemonDao dao = INSTANCE.pokemonDao();
                dao.deleteAll();

                CsvService csvService = new CsvService();
                Context context = App.getContext();
                List<Pokemon> pokemonList = csvService.openCsv(context);
                dao.insertAll(pokemonList);

            });
        }
    };

}
