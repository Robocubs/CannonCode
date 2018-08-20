/**
 * commands/auto/Turn.java
 *
 * @author Noah Husby
 * @since 6/20/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.auto.actions;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.Robot;
public class Turn extends Command {
    double startAngle = 0;
    boolean isFinished = false;
    public Turn(double angle) {
        requires(Robot.driveTrain);
        this.startAngle = angle + Robot.driveTrain.getNavxAngle();
    }
    protected void initialize() {
    }
    protected void execute() {
        Robot.driveTrain.driveSpeed = 0;
        Robot.driveTrain.setAngle(startAngle);
        Robot.driveTrain.startPID();
        if(Robot.driveTrain.onTarget()) {
            Robot.driveTrain.stopPID();
            Robot.driveTrain.stopMotors();
            isFinished = true;
        }

    }
    protected boolean isFinished() {
        return isFinished;
    }
    protected void end() {}
    protected void interrupted() {}
}
