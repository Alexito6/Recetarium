package com.example.pruebalogmeal.API;

import com.example.pruebalogmeal.base.Parameters;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Connector {

    private static Connector connector;
    private static Conversor conversor;
    private static CallMethods callMethodsObject;

    public static Connector getConector() {
        if (connector == null) {
            connector = new Connector();
            conversor = Conversor.getConversor();
            callMethodsObject = CallMethods.getCallMethodsObject();
        }
        return connector;
    }

    public <T> List<T> getAsList(Class<T> clazz, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJsonList(jsonResponse, clazz);
        return null;
    }

    public <K, V> Map<K, V> getAsMap(Class<K> clazzK, Class<V> clazzV, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJsonMap(jsonResponse, clazzK, clazzV);
        return null;
    }

    public <T> T get(Class<T> clazz, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public <R, E> R post(Class<R> clazz, E data, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonObject = conversor.toJson(data);

        RequestBody body = RequestBody.create(jsonObject, MediaType.parse("application/json"));

        String jsonResponse = callMethodsObject.post(url, body);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }


    public <T> T put(Class<T> clazz, T data, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonObject = conversor.toJson(data);

        RequestBody body = RequestBody.create(jsonObject, MediaType.parse("application/json"));

        String jsonResponse = callMethodsObject.put(url, body);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public <T> T delete(Class<T> clazz, String path) throws Exception {
        String url = Parameters.URL_API_BASE + path;
        String jsonResponse = callMethodsObject.delete(url);
        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public <T> T putWithParams(Class<T> clazz, Map<String, String> params, String path) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(Parameters.URL_API_BASE + path);

        if (params != null && !params.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            urlBuilder.setLength(urlBuilder.length() - 1);
        }

        String url = urlBuilder.toString();
        RequestBody body = RequestBody.create("", MediaType.parse("application/json"));
        String jsonResponse = callMethodsObject.put(url, body);

        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

    public <T> T postWithParams(Class<T> clazz, Map<String, String> params, String path) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(Parameters.URL_API_BASE + path);

        if (params != null && !params.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            urlBuilder.setLength(urlBuilder.length() - 1);
        }

        String url = urlBuilder.toString();

        // Enviamos un cuerpo vacío ya que los datos van en la URL como Query Params
        RequestBody body = RequestBody.create("", MediaType.parse("application/json"));

        String jsonResponse = callMethodsObject.post(url, body);

        if (jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }
}