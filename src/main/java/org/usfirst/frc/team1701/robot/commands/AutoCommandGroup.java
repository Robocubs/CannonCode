/**
 * commands/AutoCommandGroup.java
 *
 * @author Noah Husby
 * @since 6/8/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandGroup extends CommandGroup {
    /**
     * distance = wheel_circumference * wheel_rotations
     * wheel_rotations = distance / wheel_circumference
     * <p>
     * wheel_circumference = 4 * pi
     * distance = X - 38 (Robot Length)
     */
    public AutoCommandGroup(Number goal, Number location) {
        switch ((int) goal) {
            default:
                break;
        }

    }
}



