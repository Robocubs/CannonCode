package com.team1701.frc2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team1701.frc2019.Constants;
import com.team1701.lib.drivers.CubTalonSRX;
import com.team1701.lib.drivers.TalonSRXFactory;
import com.team1701.lib.loops.ILooper;
import com.team1701.lib.loops.Loop;
import com.team1701.lib.subsystem.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Turret extends Subsystem {

    private static Turret mInstance = null;

    private final CubTalonSRX mPanMaster, mTiltMaster;
    private final Solenoid c1, c2, c3, c4, c5, c6;
    private final PeriodicIO mPeriodic = new PeriodicIO();

    private CurrentBarrel mBarrel = CurrentBarrel.ONE;

    public static Turret getInstance() {
        if(mInstance == null) {
            mInstance = new Turret();
        }

        return mInstance;
    }

    public void setOpenLoop(double pan, double tilt) {
        mPeriodic.pan_demand = pan;
        mPeriodic.tilt_demand = tilt;
    }

    @Override
    public void registerEnabledLoops(ILooper in) {
        in.register(new Loop() {
            @Override
            public void onStart(double timestamp) {

            }

            @Override
            public void onLoop(double timestamp) {

            }

            @Override
            public void onStop(double timestamp) {

            }
        });
    }

    private Turret() {
        mPanMaster = TalonSRXFactory.createDefaultTalon(Constants.kTurretPanID);
        mTiltMaster = TalonSRXFactory.createDefaultTalon(Constants.kTurretTiltID);

        c1 = new Solenoid(0,2);
        c2 = new Solenoid(0,3);
        c3 = new Solenoid(0,4);
        c4 = new Solenoid(0,5);
        c5 = new Solenoid(0,6);
        c6 = new Solenoid(0,7);
    }

    @Override
    public void writePeriodicOutputs() {
        mPanMaster.set(ControlMode.PercentOutput, mPeriodic.pan_demand);
        mTiltMaster.set(ControlMode.PercentOutput, mPeriodic.tilt_demand);
    }

    @Override
    public void outputTelemetry() {

    }

    public void shoot() {
        switch(mBarrel) {
            case ONE:
                c6.set(false);
                c1.set(true);
                mBarrel = CurrentBarrel.TWO;
                break;
            case TWO:
                c1.set(false);
                c2.set(true);
                mBarrel = CurrentBarrel.THREE;
                break;
            case THREE:
                c2.set(false);
                c3.set(true);
                mBarrel = CurrentBarrel.FOUR;
                break;
            case FOUR:
                c3.set(false);
                c4.set(true);
                mBarrel = CurrentBarrel.FIVE;
                break;
            case FIVE:
                c4.set(false);
                c5.set(true);
                mBarrel = CurrentBarrel.SIX;
                break;
            case SIX:
                c5.set(false);
                c6.set(true);
                mBarrel = CurrentBarrel.ONE;
                break;
        }
    }

    @Override
    public void stop() { }

    public class PeriodicIO {
        public double tilt_demand;
        public double pan_demand;
    }

    public enum CurrentBarrel {
        ONE,TWO,THREE,FOUR,FIVE,SIX
    }
}
