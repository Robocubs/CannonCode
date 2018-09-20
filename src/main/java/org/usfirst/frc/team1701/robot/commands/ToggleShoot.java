/**
 * commands/ToggleShoot.java
 *
 * @author Noah Husby
 * @since 9/20/2018
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;
import org.usfirst.frc.team1701.robot.Stabilization;
import org.usfirst.frc.team1701.robot.states.LEDState;

public class ToggleShoot extends Command {
    public ToggleShoot() {
        requires(Robot.driveTrain);
    }
    protected void execute() {
        double time = 0.5;
        if(RobotMap.currentCannonSelection > 6) {
            RobotMap.currentCannonSelection = 1;
        }
        switch(RobotMap.currentCannonSelection) {
            case 1:
                Robot.led.setTrainLighting(LEDState.kCannonShot);
                RobotMap.cannon1.set(true);
                Timer.delay(time);
                RobotMap.cannon1.set(false);
                Robot.led.setTrainLighting(LEDState.kEnabled);
                break;
            case 2:
                Robot.led.setTrainLighting(LEDState.kCannonShot);
                RobotMap.cannon2.set(true);
                Timer.delay(time);
                RobotMap.cannon2.set(false);
                Robot.led.setTrainLighting(LEDState.kEnabled);
                break;
            case 3:
                Robot.led.setTrainLighting(LEDState.kCannonShot);
                RobotMap.cannon3.set(true);
                Timer.delay(time);
                RobotMap.cannon3.set(false);
                Robot.led.setTrainLighting(LEDState.kEnabled);
                break;
            case 4:
                Robot.led.setTrainLighting(LEDState.kCannonShot);
                RobotMap.cannon4.set(true);
                Timer.delay(time);
                RobotMap.cannon4.set(false);
                Robot.led.setTrainLighting(LEDState.kEnabled);
                break;
            case 5:
                Robot.led.setTrainLighting(LEDState.kCannonShot);
                RobotMap.cannon5.set(true);
                Timer.delay(time);
                RobotMap.cannon5.set(false);
                Robot.led.setTrainLighting(LEDState.kEnabled);
                break;
            case 6:
                Robot.led.setTrainLighting(LEDState.kCannonShot);
                RobotMap.cannon6.set(true);
                Timer.delay(time);
                RobotMap.cannon6.set(false);
                Robot.led.setTrainLighting(LEDState.kEnabled);
                break;
        }
        RobotMap.currentCannonSelection++;
    }
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    }
    protected void interrupted() {}
}
