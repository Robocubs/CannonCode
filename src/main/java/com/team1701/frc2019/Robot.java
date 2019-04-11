package com.team1701.frc2019;
import com.team1701.frc2019.controlboard.Controls;
import com.team1701.frc2019.controlboard.IControlBoard;
import com.team1701.frc2019.subsystems.*;
import com.team1701.lib.CubRobot;
import com.team1701.lib.drivetrain.CubDriveHelper;
import com.team1701.lib.loops.Looper;
import com.team1701.lib.util.ButtonFeed;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Arrays;

/** @noinspection WeakerAccess, WeakerAccess */
public class Robot extends CubRobot {
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
    private final Hardware mHardware = Hardware.getInstance();
    private final Turret mTurret = Turret.getInstance();

    private final IControlBoard mControls = Controls.getControls();
    private final CubDriveHelper mDriveHelper = new CubDriveHelper();

    private final ButtonFeed mShoot = new ButtonFeed();

    private final Looper mEnabledLoop = new Looper();
    private final Looper mDisabledLoop = new Looper();
    private final SubsystemManager mSubsystemManager = new SubsystemManager(
        Arrays.asList(
                mDrive,
                mHardware
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
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() {
        Drive.getInstance().setOpenLoop(mDriveHelper.driveCartesian(-mControls.getOmni(), mControls.getThrottle(),
                -mControls.getRotation(), 0, 0, 0));

        mTurret.setOpenLoop(mControls.getPan(), mControls.getTilt());

        if(mShoot.state(Controls.getControls().shoot())) {
            mShoot.feedout();
            mTurret.shoot();
        }
    }


    @Override
    public void testPeriodic() {
        SmartDashboard.putString("Cycle", "TEST");
    }
}