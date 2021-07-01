package com.ariefzuhri.movee.utils;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

// Buat implementasi class dari Executor agar bisa dijalankan pada thread yang sama
public class TestExecutor implements Executor {

    @Override
    public void execute(@NotNull Runnable runnable) {
        runnable.run();
    }
}