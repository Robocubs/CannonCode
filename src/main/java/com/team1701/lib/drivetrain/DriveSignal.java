/*
  Robocubs Library
  Contains all information needed for controller -> robot interaction, can be used as object

  @author Noah Husby
 */
package com.team1701.lib.drivetrain;

public class DriveSignal {

    public DriveSignal(double frontLeft, double frontRight, double rearLeft, double rearRight) {
        this.front_left = frontLeft;
        this.front_right = frontRight;

        this.rear_left = rearLeft;
        this.rear_right = rearRight;

    }

    public final double front_left;

    public final double front_right;

    public final double rear_left;

    public final double rear_right;

}
