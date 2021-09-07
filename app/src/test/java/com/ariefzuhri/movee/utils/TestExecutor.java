package com.ariefzuhri.movee.utils;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

// Create a class implementation of Executor so that it can run on the same thread
public class TestExecutor implements Executor {

    @Override
    public void execute(@NotNull Runnable runnable) {
        runnable.run();
    }
}