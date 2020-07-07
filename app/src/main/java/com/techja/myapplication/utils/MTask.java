package com.techja.myapplication.utils;

import android.os.AsyncTask;

public final class MTask extends AsyncTask<Object, Object, Object> {
    private String key;

    private OnMTaskCallBack callBack;

    public MTask(String key, OnMTaskCallBack callBack) {
        this.key = key;
        this.callBack = callBack;
    }

    public void startAsync(Object... value) {
        executeOnExecutor(THREAD_POOL_EXECUTOR, value);
    }

    public void start(Object... value) {
        execute(value);
    }

    public void stop() {
        cancel(true);
        callBack.taskComplete(key, false);
    }

    @Override
    protected Object doInBackground(Object... value) {
        return callBack.executeTask(key, value);
    }

    public void requestUpdate(Object... values) {
        publishProgress(values);
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        callBack.updateUI(key, values);
    }

    @Override
    protected void onPostExecute(Object value) {
        callBack.taskComplete(key, value);
    }

    public interface OnMTaskCallBack {

        Object executeTask(String key, Object... value);

        default void updateUI(String key, Object... values) {
            // do nothing
        }

        default void taskComplete(String key, Object value) {
            // do nothing
        }
    }

    // Observer Java
    //RxAndroid
}

