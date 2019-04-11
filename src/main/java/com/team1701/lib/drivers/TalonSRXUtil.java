package com.team1701.lib.drivers;

import com.ctre.phoenix.ErrorCode;
import edu.wpi.first.wpilibj.DriverStation;

public class TalonSRXUtil {
    public static void checkError(ErrorCode errorCode, String message) {
        if (errorCode != ErrorCode.OK) {
            DriverStation.reportError(message + errorCode, false);
        }
    }
}