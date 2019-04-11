package com.team1701.lib.loops;

@SuppressWarnings("unused")
public interface Loop {

    void onStart(double timestamp);

    void onLoop(double timestamp);

    void onStop(double timestamp);
}
