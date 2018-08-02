package org.usfirst.frc.team1701.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team1701.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1701.robot.subsystems.Shuffleboard;

public class Robot extends IterativeRobot {
    /*
      _____   ________  ________    _____
     / __  \ |\_____  \|\   __  \  / __  \
    |\/_|\  \ \|___/  /\ \  \|\  \|\/_|\  \
    \|/ \ \  \    /  / /\ \  \\\  \|/ \ \  \
         \ \  \  /  / /  \ \  \\\  \   \ \  \
          \ \__\/__/ /    \ \_______\   \ \__\
           \|__||__|/      \|_______|    \|__|
      Team 1701 The Robocubs
      Code by Noah Husby
    */
    public static OI oi;
    public static DriveTrain driveTrain;
    @Override
    public void robotInit() {
        RobotMap.init();
        Shuffleboard.init();
        driveTrain = new DriveTrain();
        oi = new OI();
    }
    @Override
    public void disabledInit() { }
    @Override
    public void autonomousInit() {
    }
    @Override
    public void teleopInit() { }
    @Override
    public void testInit() { }
    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        }
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        }
    @Override
    public void testPeriodic() { }
}