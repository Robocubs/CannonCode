/*
  Robocubs Library
  Contains all information needed for controller -> robot interaction, can be used as object

  @author Noah Husby
 */
package com.team1701.lib.drivetrain;

public class DriveSignal {

    public DriveSignal(double left, double right) {
        this.left = left;
        this.right = right;
    }

    public final double left;

    public final double right;

}
