package com.team1701.cannon.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team1701.cannon.Constants;
import com.team1701.lib.drivers.CubTalonSRX;
import com.team1701.lib.drivers.TalonSRXFactory;
import com.team1701.lib.loops.ILooper;
import com.team1701.lib.loops.Loop;
import com.team1701.lib.subsystem.Subsystem;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

        c1 = new Solenoid(1, PneumaticsModuleType.CTREPCM, 0);
        c1.setPulseDuration(1000);
        c2 = new Solenoid(1, PneumaticsModuleType.CTREPCM, 1);
        c2.setPulseDuration(1000);
        c3 = new Solenoid(1, PneumaticsModuleType.CTREPCM, 2);
        c3.setPulseDuration(1000);
        c4 = new Solenoid(1, PneumaticsModuleType.CTREPCM, 3);
        c4.setPulseDuration(1000);
        c5 = new Solenoid(1, PneumaticsModuleType.CTREPCM, 4);
        c5.setPulseDuration(1000);
        c6 = new Solenoid(1, PneumaticsModuleType.CTREPCM, 5);
        c6.setPulseDuration(1000);
    }

    @Override
    public void writePeriodicOutputs() {
        mPanMaster.set(ControlMode.PercentOutput, mPeriodic.pan_demand);
        mTiltMaster.set(ControlMode.PercentOutput, mPeriodic.tilt_demand);
    }

    @Override
    public void outputTelemetry() {
        SmartDashboard.putString("CANNON", mBarrel.name());
    }

    public void shoot() {
        switch(mBarrel) {
            case ONE:
                c1.startPulse();
                mBarrel = CurrentBarrel.TWO;
                break;
            case TWO:
                c2.startPulse();
                mBarrel = CurrentBarrel.THREE;
                break;
            case THREE:
                c3.startPulse();
                mBarrel = CurrentBarrel.FOUR;
                break;
            case FOUR:
                c4.startPulse();
                mBarrel = CurrentBarrel.FIVE;
                break;
            case FIVE:
                c5.startPulse();
                mBarrel = CurrentBarrel.SIX;
                break;
            case SIX:
                c6.startPulse();
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
