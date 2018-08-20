/**
 * commands/auto/DriveVertical.java
 *
 * @author Noah Husby
 * @since 6/28/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.auto;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.auto.actions.Turn;

public class FindTarget extends Command {
    boolean isFinished = false;
    public FindTarget() {
        requires(Robot.driveTrain);
    }
    protected void initialize() {
    }
    protected void execute() {
        if(!Robot.vision.getTarget()) {
            new Turn(5);
        } else {
            isFinished = true;
        }
    }
    protected boolean isFinished() {
        return isFinished;
    }
    protected void end() {
        Robot.driveTrain.stopPID();
    }
    protected void interrupted() {}
}
