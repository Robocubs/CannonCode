/**
 * TwoPhaseDigitalStick.java
 *
 * @author Noah Husby
 * @since 9/20/2018
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.util;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class TwoPhaseDigitalStick {
    private static final double kMax = 0.5;

    /**
     * Returns a set double based on weather a digital joystick(Two States, On or Off) is pushed
     * Used to control motors easier with digital joysticks
     */
    public static double getAnalogValue(JoystickButton phase1, JoystickButton phase2) {
       if(phase1.get()) {
           return kMax;
       } else if (phase2.get()) {
           return kMax*(-1);
       }
       return 0;
    }
}
