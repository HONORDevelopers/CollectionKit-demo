/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo.utils;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadPool
 *
 * @since 2024-08-30
 */
public class ThreadPool {
    private static final String TAG = "ThreadPool";

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int THREAD_POOL_MAX_LENGTH = CPU_COUNT * 2 + 1;

    private static volatile ThreadPool sInstance = new ThreadPool();

    private static final Object LOCK = new Object();

    private final RejectedExecutionHandler mExecutionHandler = new ThreadPoolExecutor.DiscardPolicy() {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
            super.rejectedExecution(runnable, executor);
        }
    };

    private final ExecutorService mThreadPool = Executors.newFixedThreadPool(THREAD_POOL_MAX_LENGTH);

    private ThreadPool() {
    }

    /**
     * Get the instance of Thread pool.
     *
     * @return instance of Thread pool
     */
    public static ThreadPool getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new ThreadPool();
                }
            }
        }
        return sInstance;
    }

    /**
     * Execute Runnable.
     *
     * @param runnable object of runnable
     */
    public void execute(Runnable runnable) {
        if (runnable != null) {
            mThreadPool.execute(runnable);
        }
    }

    /**
     * get executor service
     *
     * @return executor service
     */
    public ExecutorService getExecutorservice() {
        return mThreadPool;
    }
}