package org.usfirst.frc.team1701.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team1701.robot.commands.AutoCommandGroup;
import org.usfirst.frc.team1701.robot.controls.OI;
import org.usfirst.frc.team1701.robot.states.PIDState;
import org.usfirst.frc.team1701.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1701.robot.subsystems.LED;
import org.usfirst.frc.team1701.robot.subsystems.Shuffleboard;
import org.usfirst.frc.team1701.robot.subsystems.Vision;

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
    public static Vision vision;
    public static LED led;
    @Override
    public void robotInit() {
        /*
        Create and setup all devices, like motor controllers, connected to the roboRIO
         */
        RobotMap.init();
        /*
        Initialize all subsystems for parts of the robot
         */
        driveTrain = new DriveTrain();
        vision = new Vision();
        oi = new OI();
        led = new LED();
        /*
        Reset gyroscope to face front of robot, or starting position
         */
        driveTrain.setPIDMode(PIDState.mode.disabled);
        Stabilization.getInstance().reset();
    }
    @Override
    public void disabledInit() { }
    @Override
    public void autonomousInit() {
        CommandGroup autonomousCommand = new AutoCommandGroup(Shuffleboard.goal.getSelected(),Shuffleboard.location.getSelected());
        autonomousCommand.start();
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