package org.seckill.dto;

/**
 * Created by Admin on 2016/6/28.
 */
public class SeckillResult<T> {
    private boolean success;
    private  T data;

    private  String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(String error, T data) {
        this.error = error;
        this.data = data;
    }

    public SeckillResult(String error, boolean success) {
        this.error = error;
        this.success = success;
    }

    public SeckillResult(T data, String error) {
        this.data = data;
        this.error = error;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public SeckillResult(boolean success, T data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
