package com.example.pruebalogmeal.API;

import java.io.Serializable;

public class ImgBBResponse implements Serializable {

    private Data data;
    private boolean success;
    private int status;

    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public static class Data implements Serializable {
        private String url;

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }
}