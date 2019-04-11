package com.team1701.lib.loops;

import com.team1701.lib.Constants;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Looper {

    private boolean running_;

    private final Notifier notifier_;
    private final List<Loop> loops_;
    private final Object taskRunningLock_ = new Object();
    private double timestamp_ = 0;

    public Looper() {
        CrashTrackingRunnable runnable_ = new CrashTrackingRunnable() {
            @Override
            public void runCrashTracked() {
                synchronized (taskRunningLock_) {
                    if (running_) {
                        double now = Timer.getFPGATimestamp();

                        for (Loop loop : loops_) {
                            loop.onLoop(now);
                        }

                        double dt_ = now - timestamp_;
                        timestamp_ = now;
                    }
                }
            }
        };
        notifier_ = new Notifier(runnable_);
        running_ = false;
        loops_ = new ArrayList<>();
    }

    public synchronized void register(Loop loop) {
        synchronized (taskRunningLock_) {
            loops_.add(loop);
        }
    }

    public synchronized void register(List<Loop> loopArr) {
        synchronized (taskRunningLock_) {
            loops_.addAll(loopArr);
        }
    }

    public synchronized void start() {
        if (!running_) {
            System.out.println("Starting loops");
            synchronized (taskRunningLock_) {
                timestamp_ = Timer.getFPGATimestamp();
                for (Loop loop : loops_) {
                    loop.onStart(timestamp_);
                }
                running_ = true;
            }
            double kPeriod = Constants.kLooperDt;
            notifier_.startPeriodic(kPeriod);
        }
    }


    public synchronized void stop() {
        if (running_) {
            //ConsoleReporter.report("Stopping loops");
            notifier_.stop();
            synchronized (taskRunningLock_) {
                running_ = false;
                timestamp_ = Timer.getFPGATimestamp();
                for (Loop loop : loops_) {
                    //ConsoleReporter.report("Stopping " + loop);
                    loop.onStop(timestamp_);
                }
            }
        }
    }

}