package ru.franq.exampleapplication.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import ru.franq.exampleapplication.model.Task;

public class FileService {

    private static final String FILE_NAME = "tasks.json";

    private FileService(){}

    public static boolean saveFile(Context context, List<Task> dataList){

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(dataList);

        FileOutputStream fileOutputStream = null;

        try{
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(json.getBytes());
            Log.d("FILE", "Файл успешно записан!");
            return true;
        } catch (IOException e) {
            Log.e("FILE", e.getMessage());
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.e("FILE", e.getMessage());
                }
            }
        }

        return false;

    }

    public static List<Task> importTaskFromFile(Context context){
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = context.openFileInput(FILE_NAME);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new GsonBuilder().create();
            Type listType = new TypeToken<List<Task>>(){}.getType();
            return gson.fromJson(streamReader, listType);
        } catch (IOException e) {
            Log.e("FILE", e.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    Log.e("FILE", e.getMessage());
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    Log.e("FILE", e.getMessage());
                }
            }
        }

        return null;
    }

}
