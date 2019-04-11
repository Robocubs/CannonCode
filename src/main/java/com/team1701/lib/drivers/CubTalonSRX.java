/*
  Robocubs Library
  A lightweight TalonSRX library that compresses the amount of data in the CANBUS

  @author Noah Husby
 */
package com.team1701.lib.drivers;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class CubTalonSRX extends TalonSRX {
    private double mLastValue = Double.NaN;
    private ControlMode mLastCM = null;

    public CubTalonSRX(int deviceNumber) {
        super(deviceNumber);
    }

    @Override
    public void set(ControlMode mode, double value) {
        if(value != mLastValue || mode != mLastCM) {
            mLastValue = value;
            mLastCM = mode;
            super.set(mode, value);
        }
    }
}
