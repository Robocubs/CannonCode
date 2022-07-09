package com.team1701.cannon;

import com.team1701.cannon.controlboard.Controls;
import com.team1701.cannon.controlboard.IControlBoard;
import com.team1701.cannon.subsystems.*;
import com.team1701.lib.CubRobot;
import com.team1701.lib.drivetrain.CubDriveHelper;
import com.team1701.lib.loops.Looper;
import com.team1701.lib.util.ButtonFeed;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.util.Arrays;

/** @noinspection WeakerAccess, WeakerAccess */
public class Robot extends TimedRobot {
    /**
     *   _____   ________  ________    _____
     *  / __  \ |\_____  \|\   __  \  / __  \
     * |\/_|\  \ \|___/  /\ \  \|\  \|\/_|\  \
     * \|/ \ \  \    /  / /\ \  \\\  \|/ \ \  \
     *      \ \  \  /  / /  \ \  \\\  \   \ \  \
     *       \ \__\/__/ /    \ \_______\   \ \__\
     *        \|__||__|/      \|_______|    \|__|
     * Team 1701 The Robocubs
     * Code by Noah Husby & Nick Hubbard
     */

    private final Drive mDrive = Drive.getInstance();
    private final Turret mTurret = Turret.getInstance();

    private final IControlBoard mControls = Controls.getControls();
    private final CubDriveHelper mDriveHelper = new CubDriveHelper();

    private boolean shootBool;

    private final Looper mEnabledLoop = new Looper();
    private final Looper mDisabledLoop = new Looper();
    private final SubsystemManager mSubsystemManager = new SubsystemManager(
        Arrays.asList(
                mDrive,
                mTurret
        )
    );

    @Override
    public void robotInit() {
        mSubsystemManager.registerEnabledLoops(mEnabledLoop);
        mSubsystemManager.registerDisabledLoops(mDisabledLoop);
    }

    @Override
    public void disabledInit() {
        SmartDashboard.putString("Cycle", "DISABLED");
        mEnabledLoop.stop();
        mDisabledLoop.start();
    }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() {
        mDisabledLoop.stop();
        mEnabledLoop.start();

        mDriveHelper.setMultiplierConstant(1);
    }

    @Override
    public void testInit() {
        SmartDashboard.putString("Cycle", "TEST");
    }

    @Override
    public void disabledPeriodic() {
        // Set mode to DISABLED on dashboard.
        SmartDashboard.putString("Cycle", "DISABLED");
        // Run Scheduler.
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
        Drive.getInstance().setOpenLoop(mDriveHelper.driveCartesian(mControls.getThrottle(),
                -mControls.getRotation()));

        mTurret.setOpenLoop(mControls.getPan(), mControls.getTilt());

        if(Controls.getControls().shoot() && !shootBool) {
            shootBool = true;
            mTurret.shoot();
        } else if(!Controls.getControls().shoot()) {
            shootBool = false;
        }
    }


    @Override
    public void testPeriodic() {
        SmartDashboard.putString("Cycle", "TEST");
    }
}