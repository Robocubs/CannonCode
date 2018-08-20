/**
 * commands/ToggleFieldOrientation.java
 *
 * @author Noah Husby
 * @since 6/18/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.Robot;

public class Center extends Command {
    public Center() {
        requires(Robot.driveTrain);
    }
    protected void execute() {
        Robot.driveTrain.center();
    }
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    }
    protected void interrupted() {}
}
