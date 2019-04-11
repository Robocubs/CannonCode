/*
  Robocubs Library
  Abstract class for subsystem, which can be referenced from SubsystemManager

  @author Noah Husby
 */
package com.team1701.lib.subsystem;

import com.team1701.lib.loops.ILooper;

@SuppressWarnings("unused")
public abstract class Subsystem {

    public void readPeriodicInputs() {}

    public void writePeriodicOutputs() {}

    public abstract void outputTelemetry();

    public abstract void stop();

    public abstract void registerEnabledLoops(ILooper in);

    public void zeroSensors() {}
}
