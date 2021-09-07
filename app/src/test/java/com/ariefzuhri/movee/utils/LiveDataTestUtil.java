package com.ariefzuhri.movee.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// Help to get value from LiveData
public class LiveDataTestUtil {

    @SuppressWarnings("unchecked")
    public static <T> T getValue(@NotNull LiveData<T> liveData) {
        Object[] data = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);

        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };

        liveData.observeForever(observer);

        try {
            latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return (T) data[0];
    }
}
