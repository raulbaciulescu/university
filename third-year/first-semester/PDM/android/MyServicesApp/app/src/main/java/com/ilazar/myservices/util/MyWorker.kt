package com.ilazar.myservices.util

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit.SECONDS

class WorkerTest(
    context: Context,
    val workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        for (i in 1..5) {
            SECONDS.sleep(1)
            Log.d("MyWorker", "progress: $i")
        }
        return Result.success()
    }
}