package com.team1701.lib.drivetrain;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.drive.Vector2d;

@SuppressWarnings("all")
public class CubDriveHelper {

    private double mPowerMultiplier = 0.7;

    public DriveSignal driveCartesian(double ySpeed, double xSpeed, double zRotation, double gyroAngle, double l_strafe,
                                      double r_strafe) {

        /**
         * Strafe Controls
         */
        /**
        xSpeed += r_strafe;
        xSpeed -= l_strafe;
         */

        ySpeed = ySpeed * mPowerMultiplier;
        xSpeed = xSpeed * mPowerMultiplier;
        zRotation = zRotation * mPowerMultiplier;

        if(ySpeed > 1) {
            ySpeed = 1;
        } else if (ySpeed < -1) {
            ySpeed = -1;
        }

        if(xSpeed > 1) {
            xSpeed = 1;
        } else if(xSpeed < -1) {
            xSpeed = -1;
        }

        ySpeed = deadband(ySpeed);
        xSpeed = deadband(xSpeed);
        zRotation = deadband(zRotation);

        Vector2d input = new Vector2d(ySpeed, xSpeed);
        input.rotate(-gyroAngle);

        double[] wheelSpeeds = new double[4];
        wheelSpeeds[RobotDriveBase.MotorType.kFrontLeft.value] = input.x + input.y + zRotation;
        wheelSpeeds[RobotDriveBase.MotorType.kFrontRight.value] = -input.x + input.y - zRotation;
        wheelSpeeds[RobotDriveBase.MotorType.kRearLeft.value] = -input.x + input.y + zRotation;
        wheelSpeeds[RobotDriveBase.MotorType.kRearRight.value] = input.x + input.y - zRotation;

        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        for (int i = 1; i < wheelSpeeds.length; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        if (maxMagnitude > 1.0) {
            for (int i = 0; i < wheelSpeeds.length; i++) {
                wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
            }
        }

        int kMaxOutput = 1;
        double fL = wheelSpeeds[RobotDriveBase.MotorType.kFrontLeft.value] * kMaxOutput;
        double m_rightSideInvertMultiplier = -1.0;
        double fR = wheelSpeeds[RobotDriveBase.MotorType.kFrontRight.value] * kMaxOutput * m_rightSideInvertMultiplier;
        double rL = wheelSpeeds[RobotDriveBase.MotorType.kRearLeft.value] * kMaxOutput;
        double rR = wheelSpeeds[RobotDriveBase.MotorType.kRearRight.value] * kMaxOutput * m_rightSideInvertMultiplier;

        return new DriveSignal(fL,fR,rL,rR);
    }

    public void setMultiplierConstant(double mult) {
        this.mPowerMultiplier = mult;
    }

    private double deadband(double speed) {
        double kDeadband = 0.09;
        if(Math.abs(speed) < kDeadband) {
            return 0;
        }

        return speed;
    }
}
