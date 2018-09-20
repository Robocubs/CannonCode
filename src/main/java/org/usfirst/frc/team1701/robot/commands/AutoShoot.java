package org.usfirst.frc.team1701.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;

public class AutoShoot extends Command {
    public AutoShoot() {
        requires(Robot.driveTrain);
    }
    protected void execute() {
        double time = 0.5;
                RobotMap.cannon1.set(true);
                Timer.delay(time);
                RobotMap.cannon1.set(false);
                RobotMap.cannon2.set(true);
                Timer.delay(time);
                RobotMap.cannon2.set(false);
                RobotMap.cannon3.set(true);
                Timer.delay(time);
                RobotMap.cannon3.set(false);
                RobotMap.cannon4.set(true);
                Timer.delay(time);
                RobotMap.cannon4.set(false);
                RobotMap.cannon5.set(true);
                Timer.delay(time);
                RobotMap.cannon5.set(false);
                RobotMap.cannon6.set(true);
                Timer.delay(time);
                RobotMap.cannon6.set(false);

    }
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    }
    protected void interrupted() {}
}
