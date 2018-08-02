/**
 * commands/ToggleMecanum.java
 *
 * @author Noah Husby
 * @since 6/16/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.Robot;
public class ToggleMecanum extends Command {
    public ToggleMecanum() {
        requires(Robot.driveTrain);
    }
    protected void execute() {
        if(Robot.driveTrain.isMecanum()) {
            Robot.driveTrain.setDifferentialDrive();
            Robot.driveTrain.setFieldOrientation(false);
        }
        else {
            Robot.driveTrain.setMecanumDrive();
            Robot.driveTrain.setFieldOrientation(true);
        }
    }
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    }
    protected void interrupted() {}
}

