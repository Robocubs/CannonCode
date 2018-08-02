/**
 * commands/Auto/DriveVertical.java
 *
 * @author Noah Husby
 * @since 6/20/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.commands.Auto;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;
public class DriveVertical extends Command {
    boolean isFinished = false;
    double distance = 0;
    double currentSpeed;
    double startAngle;
    public DriveVertical(double distance, double speed) {
        requires(Robot.driveTrain);
        Robot.driveTrain.driveSpeed = 0;
        this.distance = distance + RobotMap._navx.getDisplacementY();
        this.currentSpeed = speed;
    }
    protected void initialize() {
        startAngle = Robot.driveTrain.getNavxAngle();
        Robot.driveTrain.setPidVertical();
    }
    protected void execute() {
        Robot.driveTrain.setMecanumDrive();
        if(RobotMap._navx.getDisplacementY() < distance) {
            Robot.driveTrain.driveSpeed = -currentSpeed;
            Robot.driveTrain.setAngle(startAngle);
            Robot.driveTrain.startPID();
        } else {
            Robot.driveTrain.stopPID();
            Robot.driveTrain.stopMotors();
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
