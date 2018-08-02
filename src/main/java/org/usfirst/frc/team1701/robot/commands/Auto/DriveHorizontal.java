/**
 * commands/Auto/DriveHorizontal.java
 *
 * @author Noah Husby
 * @since 6/16/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.commands.Auto;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;
public class DriveHorizontal extends Command {
    boolean isFinished = false;
    double distance = 0;
    double currentSpeed;
    double startAngle;
    public DriveHorizontal(double meters, double speed) {
        requires(Robot.driveTrain);
        Robot.driveTrain.driveSpeed = 0;
        this.distance = meters + RobotMap._navx.getDisplacementX();
        this.currentSpeed = speed;
    }
    protected void initialize() {
        startAngle = Robot.driveTrain.getNavxAngle();
        Robot.driveTrain.setPidHorizontal();
        Robot.driveTrain.setMecanumDrive();
    }
    protected void execute() {
        if(RobotMap._navx.getDisplacementX() < distance) {
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
