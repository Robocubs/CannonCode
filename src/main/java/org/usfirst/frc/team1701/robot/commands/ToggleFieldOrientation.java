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
public class ToggleFieldOrientation extends Command {
    public ToggleFieldOrientation() {
        requires(Robot.driveTrain);
    }
    protected void execute() {
        if(Robot.driveTrain.isMecanum() || Robot.driveTrain.getFieldOrientation()) {
            Robot.driveTrain.setFieldOrientation(!Robot.driveTrain.getFieldOrientation());

        }
    }
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    }
    protected void interrupted() {}
}
