package com.team1701.lib.drivers;

import com.revrobotics.CANSparkMax;

public class CubSparkMAX extends CANSparkMax {

    private double mLastValue = Double.NaN;

    public CubSparkMAX(int deviceID, MotorType type) {
        super(deviceID, type);
    }

    @Override
    public void set(double value) {
        if(value != mLastValue) {
            mLastValue = value;
            super.set(value);
        }
    }
}
