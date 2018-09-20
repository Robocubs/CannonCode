/**
 * subsystems/LED.java
 *
 * @author Noah Husby
 * @since 9/20/2018
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import org.usfirst.frc.team1701.robot.RobotMap;
import org.usfirst.frc.team1701.robot.states.LEDState;

public class LED {
    private final Spark trainLED = RobotMap.trainLED;
    private final Spark turretLED = RobotMap.turretLED;
    public LED() {
    }

    /**
     * Sets the REV Robotics Blinkin to the given state on the drive train
     * @param state See states/LEDState.java
     */
    public void setTrainLighting(LEDState state) {
        trainLED.set(state.sV);
    }

    /**
     * Sets the REV Robotics Blinkin to the given state on the turret
     * @param state See states/LEDState.java
     */
    public void setTurretLighting(LEDState state) {
        turretLED.set(state.sV);
    }
}
