/*
  Robocubs Library
  Factory for creating TalonSRX's with default settings

  @author Noah Husby
 */
package com.team1701.lib.drivers;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class TalonSRXFactory {

    private final static int kTimeoutMs = 100;

    static class Configuration {
        final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;

        int FRAMESTATUS = 5;

        final double NEUTRAL_DEADBAND = 0.1;

        final boolean INVERTED = false;
        final boolean SENSOR_PHASE = false;

        final double OPENLOOP_RAMP = 0.0;
        final double CLOSEDLOOP_RAMP = 0.0;

        FeedbackDevice FEEDBACK_DEVICE = FeedbackDevice.QuadEncoder;
    }

    private static final Configuration kDefualtConfig = new Configuration();
    private static final Configuration kSlaveConfig = new Configuration();

    static {
        kSlaveConfig.FEEDBACK_DEVICE = FeedbackDevice.Analog;
        kSlaveConfig.FRAMESTATUS = 100;
    }

    /**
     * Creates a talon object based on factory configuration
     * @param id CANBUS ID for TalonSRX
     * @return TalonSRX object
     */
    public static CubTalonSRX createDefaultTalon(int id) {
        return generateTalon(id, kDefualtConfig);
    }

    @SuppressWarnings("unused")
    public static CubTalonSRX createSlaveTalon(int id, int masterid) {
        final CubTalonSRX talon = generateTalon(id, kSlaveConfig);
        talon.set(ControlMode.Follower, masterid);
        return talon;
    }

    private static CubTalonSRX generateTalon(int id, Configuration config) {
        CubTalonSRX talon = new CubTalonSRX(id);
        talon.set(ControlMode.PercentOutput,0.0);

        talon.clearStickyFaults(kTimeoutMs);

        talon.changeMotionControlFramePeriod(config.FRAMESTATUS);

        talon.configNominalOutputForward(0, kTimeoutMs);
        talon.configNominalOutputReverse(0, kTimeoutMs);

        talon.setNeutralMode(config.NEUTRAL_MODE);
        talon.configNeutralDeadband(config.NEUTRAL_DEADBAND, kTimeoutMs);

        talon.configSelectedFeedbackSensor(config.FEEDBACK_DEVICE, 0, kTimeoutMs);

        talon.configPeakOutputForward(1.0, kTimeoutMs);
        talon.configPeakOutputReverse(-1.0, kTimeoutMs);

        talon.configOpenloopRamp(config.OPENLOOP_RAMP,kTimeoutMs);
        talon.configClosedloopRamp(config.CLOSEDLOOP_RAMP, kTimeoutMs);

        talon.configVoltageCompSaturation(0.0, kTimeoutMs);
        talon.configVoltageMeasurementFilter(32, kTimeoutMs);
        talon.enableVoltageCompensation(false);

        talon.setInverted(config.INVERTED);
        talon.setSensorPhase(config.SENSOR_PHASE);

        return talon;
    }
}
