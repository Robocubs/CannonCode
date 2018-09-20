/**
 * util/CubTalonSRX.java
 *
 * @author Noah Husby
 * @since 9/20/2018
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CubTalonSRX extends WPI_TalonSRX {
    protected double mLastValue = Double.NaN;
    protected ControlMode mLastCM = null;

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

    @Override
    public void set(double value) {
        if(value != mLastValue) {
            super.set(value);
        }
    }
}
