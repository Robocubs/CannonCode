/**
 * states/PIDState.java
 *
 * @author Noah Husby
 * @since 9/20/2018
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.states;

public class PIDState {
    public enum mode {
        vertical,
        horizontal,
        teleopTurn,
        disabled
    }
}
