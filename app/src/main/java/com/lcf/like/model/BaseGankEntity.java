package com.lcf.like.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: gank api return base data
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/27 16:23
 */
public class BaseGankEntity<T> {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("results")
    @Expose
    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        if (results == null) {
            results = new ArrayList<>();
        }
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
