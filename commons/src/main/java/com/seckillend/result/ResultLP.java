package com.seckillend.result;

import com.seckillend.enums.ResultStatus;

import java.io.Serializable;

public class ResultLP<T> extends AbstractResult implements Serializable {
    private static final long serialVersionUID = 867933019328199779L;
    private T data;
    private Integer count;

    public ResultLP(){
    }

    public ResultLP(ResultStatus status, String message) {
        super(status, message);
    }
    public ResultLP(ResultStatus status) {
        super(status);
    }
    public static <T> ResultLP<T> build() {
        return new ResultLP(ResultStatus.SUCCESS, (String)null);
    }

    public static <T> ResultLP<T> build(String message) {
        return new ResultLP(ResultStatus.SUCCESS, message);
    }

    public static <T> ResultLP<T> error(ResultStatus status) {
        return new ResultLP<T>(status);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void success(T value) {
        this.success();
        this.data = value;
        this.count = 0;
    }

    @Override
    public String toString() {
        return "ResultGeekQ{" +
                "data=" + data +
                ", count=" + count +
                '}';
    }
}
