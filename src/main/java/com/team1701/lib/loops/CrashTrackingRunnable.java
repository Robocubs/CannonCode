package com.team1701.lib.loops;

abstract class CrashTrackingRunnable implements Runnable {

    @Override
    public void run() {
        runCrashTracked();
    }

    protected abstract void runCrashTracked();

}
