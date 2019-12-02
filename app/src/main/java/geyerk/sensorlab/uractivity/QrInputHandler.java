package geyerk.sensorlab.uractivity;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


class QrInputHandler {

    private final String input;

    QrInputHandler(String input, Context context){
        this.input = input;
        Gson gson = new Gson();
        String json  = gson.toJson(analyseInput());
        SharedPreferences sharedPreferences = context.getSharedPreferences("QRInput", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("instructions from QR", json).apply();
    }

    private QRInput analyseInput() {
        String[] rows = input.split("\n");
        HashMap<String, Integer> dataSources = new HashMap<>();
        Set<String> contextualDataSources = new HashSet<>();
        Set<String> prospectiveDataSources = new HashSet<>();
        int daysToMonitor = 0;
        if(rows[1].charAt(3) == 'T'){
            dataSources.put("contextual", returnOrder(rows[1]));
            if(rows[1].charAt(8) == 'T'){
                contextualDataSources.add("installed");
            }
            if(rows[1].charAt(12) == 'T'){
                contextualDataSources.add("permission");
            }
            if(rows[1].charAt(16) == 'T'){
                contextualDataSources.add("response");
            }

        }
        if(rows[2].charAt(3) == 'T'){
            dataSources.put("usage", returnOrder(rows[2]));
            String suspectDaysToMonitor = String.valueOf(rows[2].charAt(8));
            if(onlyDigits(suspectDaysToMonitor)){
                daysToMonitor = Integer.valueOf(suspectDaysToMonitor);
            }
        }
        if(rows[3].charAt(3) == 'T'){
            dataSources.put("prospective", returnOrder(rows[3]));
            if(rows[3].charAt(8) == 'T'){
                prospectiveDataSources.add("screen");
            }
            if(rows[3].charAt(12) == 'T'){
                prospectiveDataSources.add("app");
            }
            if(rows[3].charAt(16) == 'T'){
                prospectiveDataSources.add("notification");
            }
            if(rows[3].charAt(20) == 'T'){
                prospectiveDataSources.add("installed");
            }
        }
        dataSources.put("finish", dataSources.size());

        return new QRInput(dataSources, contextualDataSources, daysToMonitor, prospectiveDataSources);
    }

    private int returnOrder(final String row){
        int position = 4;
        final String characterOfInterest = String.valueOf(row.charAt(row.length()-2));
        if(onlyDigits(characterOfInterest)){
            position = Integer.valueOf(characterOfInterest);
        }
        return position;
    }


    private boolean onlyDigits (final String toTest){
        return toTest.matches("[0-9]+");
    }
}
