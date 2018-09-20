/**
 * states/LEDState.java
 *
 * @author Noah Husby
 * @since 9/20/2018
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.states;

public class LEDState {
    public static final LEDState kOff = new LEDState(0.99);

    public static final LEDState kEnabled = new LEDState(0.59);
    public static final LEDState kCannonShot = new LEDState(-0.03);

    public LEDState() {
    }

    public LEDState(double s) {
        sV = s;
    }

    public double sV;
}
