package com.example.pruebalogmeal;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LOGMEAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            File imageFile = copyImageFromRaw();
            testLogMealApi(imageFile);
        } catch (Exception e) {
            Log.e(TAG, "Error preparando la imagen", e);
        }
    }

    /**
     * Copia res/raw/prueba.jpg a un archivo real accesible por Retrofit
     */
    private File copyImageFromRaw() throws Exception {
        InputStream is = getResources().openRawResource(R.raw.macarron);
        File outFile = new File(getExternalFilesDir(null), "macarron.jpg");

        FileOutputStream fos = new FileOutputStream(outFile);
        byte[] buffer = new byte[4096];
        int read;
        while ((read = is.read(buffer)) != -1) {
            fos.write(buffer, 0, read);
        }

        fos.close();
        is.close();

        Log.d(TAG, "Imagen copiada en: " + outFile.getAbsolutePath());
        return outFile;
    }

    private void testLogMealApi(File file) {

        if (!file.exists()) {
            Log.e(TAG, "Archivo no encontrado");
            return;
        }

        RequestBody requestFile =
                RequestBody.create(file, MediaType.parse("image/jpeg"));

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        LogMealApi api = ApiClient.getClient().create(LogMealApi.class);
        String token = "Bearer " + BuildConfig.LOGMEAL_APIUSER_TOKEN;



        api.recognizeFood(token, body).enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {

                if (!response.isSuccessful() || response.body() == null) {
                    Log.e("LOGMEAL", "Error HTTP " + response.code());
                    return;
                }

                FoodResponse data = response.body();

                if (data.recognition_results == null || data.recognition_results.isEmpty()) {
                    Log.e("LOGMEAL", "No se reconoció ningún plato");
                    return;
                }

                FoodItem best = data.recognition_results.get(0);

                String nombre = best.name;
                float prob = best.prob;

                Log.d("LOGMEAL", "PLATO DETECTADO: " + nombre + " (" + prob + ")");
            }


            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                Log.e(TAG, "Fallo en la llamada", t);
            }
        });
    }
}
