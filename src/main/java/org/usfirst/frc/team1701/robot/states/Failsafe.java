package org.usfirst.frc.team1701.robot.states;

import edu.wpi.first.wpilibj.command.Command;

public class Failsafe {
    double mFailsafeClock; //Time of failsafe
    double mStartTime; //FPGA time of start
    double mFailsafeTimeLeft; //Time left
    int mCurrentFailsafeDeploy = 0;
    boolean mFailsafeRunning;
    boolean mFailsafeFinished;
    Command[] failsafeDeploy;

    public Failsafe() {
        failsafeDeploy = new Command[3];
        //runClock();
    }

    public void startFailsafe() {
        mFailsafeRunning = true;
    }

    public void stopFailsafe() {

    }

    public void reset() {

    }

    public void setTime(double time) {
        this.mFailsafeClock = time;
    }

    public void addAction(Command action) {
        failsafeDeploy[mCurrentFailsafeDeploy] = action;
        mCurrentFailsafeDeploy++;
    }

    public double getStartTime() {
        return this.mFailsafeClock;
    }

    private synchronized void runClock() {
        while(true) {
            while(mFailsafeRunning) {

            }
        }
    }



}
