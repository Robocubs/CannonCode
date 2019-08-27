package com.team1701.lib.drivetrain;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.drive.Vector2d;

@SuppressWarnings("all")
public class CubDriveHelper {

    private double mPowerMultiplier = 0.7;

    public DriveSignal driveCartesian(double xSpeed, double zRotation) {
        xSpeed = limit(xSpeed);
        xSpeed = applyDeadband(xSpeed);

        zRotation = limit(zRotation);
        zRotation = applyDeadband(zRotation);

        double leftMotorOutput;
        double rightMotorOutput;

        double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

        if (xSpeed >= 0.0) {
            // First quadrant, else second quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            } else {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            }
        } else {
            // Third quadrant, else fourth quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            } else {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            }
        }
        return new DriveSignal(limit(leftMotorOutput), limit(rightMotorOutput) * -1);
    }

    public void setMultiplierConstant(double mult) {
        this.mPowerMultiplier = mult;
    }

    private double applyDeadband(double speed) {
        double kDeadband = 0.09;
        if(Math.abs(speed) < kDeadband) {
            return 0;
        }

        return speed;
    }

    protected double limit(double value) {
        if (value > 1.0) {
            return 1.0;
        }
        if (value < -1.0) {
            return -1.0;
        }
        return value;
    }
}
