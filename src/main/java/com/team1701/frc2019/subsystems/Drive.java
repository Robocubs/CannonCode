package com.team1701.frc2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team1701.frc2019.Constants;
import com.team1701.lib.drivers.*;
import com.team1701.lib.loops.ILooper;
import com.team1701.lib.loops.Loop;
import com.team1701.lib.subsystem.Subsystem;
import com.team1701.lib.drivetrain.DriveSignal;
import com.team1701.lib.util.CubLog;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Drive subsystem. Controls drivetrain and steering.
 */
public class Drive extends Subsystem {
  private final CubTalonSRX mLeftFrontMaster, mRightFrontMaster, mLeftRearMaster, mRightRearMaster;
  private static final Drive mInstance = new Drive();
  private PeriodicIO mPeriodic = new PeriodicIO();

  private final Loop mLoop = new Loop() {
    @Override
    public void onStart(double timestamp) {
      synchronized (Drive.this) {}
    }

    @Override
    public void onLoop(double timestamp) {
      synchronized (Drive.this) {

      }
    }

    @Override
    public void onStop(double timestamp) {
      synchronized (Drive.this) {}
    }
  };

  /**
   * Register enabled loops in the subsystem.
   */
  @Override
  public void registerEnabledLoops(ILooper in) {
    in.register(mLoop);
  }

  /**
   * Initialize the Drive class. Creates four Talons using TalonSRX factory class, and initializes the NavX.
   */
  private Drive() {

    mLeftFrontMaster = TalonSRXFactory.createDefaultTalon(Constants.kLeftFrontDriveMasterID);

    mLeftRearMaster = TalonSRXFactory.createDefaultTalon(Constants.kLeftRearDriveMasterID);

    mRightFrontMaster = TalonSRXFactory.createDefaultTalon(Constants.kRightFrontDriveMasterID);

    mRightRearMaster = TalonSRXFactory.createDefaultTalon(Constants.kRightRearDriveMasterID);
  }

  /**
   * Get an instance of the Drive class.
   * @return Drive instance
   */
  public static Drive getInstance() {
    return mInstance;
  }

  /**
   * Set the drivetrain to open loop mode.
   * @param signal DriveSignal
   */
  public void setOpenLoop(DriveSignal signal) {
    mPeriodic.left = signal.left;
    mPeriodic.right = signal.right;
  }

  /**
   * Override from SubsystemManager, that will automatically stop all action
   */
  @Override
  public void stop() {}

  /**
   * Output drive telemetry to Shuffleboard.
   */
  @Override
  public void outputTelemetry() { }

  /**
   * Output Drive information.
   */
  @Override
  public synchronized void writePeriodicOutputs() {
     mRightFrontMaster.set(ControlMode.PercentOutput, mPeriodic.right);
      mLeftFrontMaster.set(ControlMode.PercentOutput, mPeriodic.left);
      mRightRearMaster.set(ControlMode.PercentOutput, mPeriodic.right);
      mLeftRearMaster.set(ControlMode.PercentOutput, mPeriodic.left);

  }

  public static class PeriodicIO {


    // OUTPUTS
    public double left;
    public double right;
  }

}