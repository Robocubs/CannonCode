/*
  Robocubs Library
  Organizes all DriverStation outputs, and csv logs

  @author Noah Husby
 */
package com.team1701.lib.util;

import edu.wpi.first.wpilibj.Timer;

@SuppressWarnings("SameParameterValue")
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

}
