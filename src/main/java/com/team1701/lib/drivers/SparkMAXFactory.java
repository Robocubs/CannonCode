package com.team1701.lib.drivers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class SparkMAXFactory {

    private final static int kTimeoutMs = 100;

    static class Configuration {
        final CANSparkMax.IdleMode IDLE_MODE = CANSparkMax.IdleMode.kCoast;
    }

    private static final Configuration kDefaultConfig = new Configuration();
    private static final Configuration kSlaveConfig = new Configuration();

    public static CubSparkMAX createDefaultSpark(int address, CANSparkMaxLowLevel.MotorType motorType) {
        return generateSparkMAX(address, motorType, kDefaultConfig);
    }

    public static CubSparkMAX createSlaveSpark(int address, CANSparkMaxLowLevel.MotorType motorType) {
        return generateSparkMAX(address, motorType, kSlaveConfig);
    }

    private static CubSparkMAX generateSparkMAX(int address, CANSparkMaxLowLevel.MotorType type, Configuration config) {
        CubSparkMAX spark = new CubSparkMAX(address, type);

        spark.setCANTimeout(kTimeoutMs);
        spark.setIdleMode(config.IDLE_MODE);

        return spark;
    }
}
