package org.usfirst.frc.team1701.robot;

public interface IStabilization {
    double getForward();

    void disableZStabilization();

    void enableZStabilization(boolean currentActive);

    boolean isStabilizationActive();

    boolean getLastStabilizationState();

    void setZStabilization(boolean stable);

    void reset();
}
