package com.nqnewlin.pokegenderdex.csv;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.nqnewlin.pokegenderdex.models.Pokemon;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvService {

    public List<Pokemon> openCsv(Context context) {
        List<Pokemon> results = new ArrayList<>();
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("data/Pokemon.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));

            String[] nextLine;
            boolean header = true;
            while ((nextLine = reader.readNext()) != null) {

                //TODO map all pokemon values
                if (!header) {
                    Pokemon pokemon = new Pokemon(
                            Integer.valueOf(nextLine[0]),
                            nextLine[1],
                            Integer.valueOf(nextLine[2]),
                            Boolean.parseBoolean(nextLine[3]),
                            Boolean.parseBoolean(nextLine[4]),
                            Integer.valueOf(nextLine[5]),
                            Boolean.parseBoolean(nextLine[6]),
                            nextLine[7],
                            Boolean.parseBoolean(nextLine[8]),
                            nextLine[9],
                            Boolean.parseBoolean(nextLine[10]),
                            Boolean.parseBoolean(nextLine[11]),
                            nextLine[12]);
                    results.add(pokemon);

                    if (!nextLine[7].equals("")) {
                        String line = nextLine[7];
                        line = line.replace("[", "");
                        line = line.replace("]", "");
                        String[] array = line.split(",");
                        List<String> list = Arrays.stream(array).collect(Collectors.toList());
                    }
                } else { header = false; }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }






}
