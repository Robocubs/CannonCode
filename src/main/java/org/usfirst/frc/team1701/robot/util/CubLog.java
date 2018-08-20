package org.usfirst.frc.team1701.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1701.robot.subsystems.DriveTrain;

import java.sql.Driver;

public class CubLog {
    private static CubLog instance;
    public static CubLog getInstance() {
        if(instance == null) {
            instance = new CubLog();
        }
        return instance;
    }

    private String getFormalMessageType(String type,String message) {
        return "["+ Timer.getFPGATimestamp()+"] "+type+": "+message;
    }

    public void message(String message) {
        System.out.println(getFormalMessageType("Info",message));
    }

    public void warning(String message) {
        DriverStation.reportWarning(getFormalMessageType("Warning",message),true);
    }

    public void error(String message) {
        DriverStation.reportError(getFormalMessageType("Erorr",message),true);
    }
}
