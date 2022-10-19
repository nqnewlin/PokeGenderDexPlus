package com.nqnewlin.pokegenderdex.models;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PokemonRepository {

    private PokemonDao mPokemonDao;
    private LiveData<List<Pokemon>> mAllPokemon;
    private LiveData<Pokemon> mPokemon;

    public PokemonRepository(Application application) {
        PokemonDatabase db = PokemonDatabase.getDatabase(application);
        mPokemonDao = db.pokemonDao();
        mAllPokemon = mPokemonDao.getAll();


    }

    public LiveData<List<Pokemon>> getAllPokemon() {

        return mAllPokemon;
    }

    public LiveData<Pokemon> getSinglePokemon() { return mPokemon; }

    public void insert(Pokemon pokemon) {
        new InsertPokemonAsyncTask(mPokemonDao).execute(pokemon);
    }

    // creating a method to delete the data in our database.
    public void delete(Pokemon pokemon) {
        new DeletePokemonAsyncTask(mPokemonDao).execute(pokemon);
    }

    // below is the method to delete all the courses.
    public void deleteAllPokemon() {
        new DeleteAllPokemonAsyncTask(mPokemonDao).execute();
    }

    // we are creating a async task method to insert new course.
    private static class InsertPokemonAsyncTask extends AsyncTask<Pokemon, Void, Void> {
        private PokemonDao dao;

        private InsertPokemonAsyncTask(PokemonDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            // below line is use to insert our modal in dao.
            dao.insert(pokemons[0]);
            return null;
        }
    }
    // we are creating a async task method to delete course.
    private static class DeletePokemonAsyncTask extends AsyncTask<Pokemon, Void, Void> {
        private PokemonDao dao;

        private DeletePokemonAsyncTask(PokemonDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(pokemons[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllPokemonAsyncTask extends AsyncTask<Void, Void, Void> {
        private PokemonDao dao;
        private DeleteAllPokemonAsyncTask(PokemonDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAll();
            return null;
        }
    }
}
