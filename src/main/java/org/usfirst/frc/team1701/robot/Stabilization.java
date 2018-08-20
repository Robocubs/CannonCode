package org.usfirst.frc.team1701.robot;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1701.robot.controls.OI;
import org.usfirst.frc.team1701.robot.states.PIDState;
import org.usfirst.frc.team1701.robot.subsystems.DriveTrain;

public class Stabilization implements IStabilization{

    public double mOnZFinishAngle = 0;
    private boolean mZZeroed;
    private boolean mZEnable = true;
    private boolean mZLastState = false;
    private static Stabilization instance = null;
    public double mFinishTime = 0;



    public static Stabilization getInstance() {
        if(instance == null) {
            instance = new Stabilization();
        }
        return instance;
    }

    /*
     * Stabilization manager for butterfly drive
     */
    public void stabilizeZ(double deadZoneZ) {
        if(mZEnable && Timer.getFPGATimestamp() > mFinishTime + 1) {
            Robot.driveTrain.driveSpeed = 0;
            double rawZ = Math.abs(deadZoneZ);
            if(rawZ > 0) {
                Robot.driveTrain.stopPID();
                Robot.driveTrain.setPIDMode(PIDState.mode.disabled);
                mZZeroed = true;
                mFinishTime = Timer.getFPGATimestamp();
            } else {
                if(mZZeroed) {
                    mOnZFinishAngle = -Robot.driveTrain.getNavxAngle();
                    Robot.driveTrain.setAngle(mOnZFinishAngle);
                    mZZeroed = false;
                }
                Robot.driveTrain.setPIDMode(PIDState.mode.teleopTurn);
                Robot.driveTrain.startPID();
            }
        } else {
            Robot.driveTrain.setPIDMode(PIDState.mode.disabled);
        }
    }

    @Override
    public double getForward() {
        return mOnZFinishAngle;
    }

    @Override
    public void disableZStabilization() {
        mZLastState = mZEnable;
        mZEnable = false;

    }

    @Override
    public void enableZStabilization(boolean currentActive) {
        if(currentActive) {
            this.mOnZFinishAngle = Robot.driveTrain.getNavxAngle();
        }
        mZEnable = true;
    }

    @Override
    public boolean isStabilizationActive() {
        return mZEnable;
    }

    @Override
    public boolean getLastStabilizationState() {
        return false;
    }

    @Override
    public void setZStabilization(boolean stable) {
        this.mZEnable = stable;
    }

    @Override
    public void reset() {
        Robot.driveTrain.setAngle(-Robot.driveTrain.getNavxAngle());
    }

}
