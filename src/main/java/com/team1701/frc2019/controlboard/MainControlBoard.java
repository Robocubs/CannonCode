/*
  controlboard/MainControlBoard.java

  @author Noah Husby
 * @since 2018-11-3
 * @license BSD-3-Clause
 */
package com.team1701.frc2019.controlboard;

import com.team1701.frc2019.Constants;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class controls the main control board implementation.
 */
public class MainControlBoard implements IControlBoard {

    private static MainControlBoard mInstance;

    private final Joystick kDriveController, kOperatorControler;

    private MainControlBoard() {
        kDriveController = new Joystick(Constants.kMainXBOXDriver);
        kOperatorControler = new Joystick(Constants.kOPXbox);
    }

    public static MainControlBoard getInstance() {
        if(mInstance == null)
            mInstance = new MainControlBoard();
        return mInstance;
    }

    @Override
    public double getThrottle() {
        return kDriveController.getRawAxis(1);
    }

    @Override
    public double getRotation() {
        return kDriveController.getRawAxis(4);
    }

    @Override
    public double getPan() {
        return kOperatorControler.getRawAxis(0);
    }

    @Override
    public boolean shoot() {
        return kOperatorControler.getRawButton(1);
    }

}
