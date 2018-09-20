/**
 * subsystems/DriveTrain.java
 *
 * @author Noah Husby
 * @since 6/10/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.Stabilization;
import org.usfirst.frc.team1701.robot.controls.OI;
import org.usfirst.frc.team1701.robot.RobotMap;
import org.usfirst.frc.team1701.robot.commands.TeleopDrive;
import com.kauailabs.navx.frc.AHRS;
import org.usfirst.frc.team1701.robot.states.PIDState;
import org.usfirst.frc.team1701.robot.util.CubLog;

public class DriveTrain extends PIDSubsystem {
  /*
   * Set of motors.
   */
  private final WPI_TalonSRX mLeft1 = RobotMap._frontLeftMotor;
  private final WPI_TalonSRX mLeft2 = RobotMap._rearLeftMotor;
  private final WPI_TalonSRX mRight1 = RobotMap._frontRightMotor;
  private final WPI_TalonSRX mRight2 = RobotMap._rearRightMotor;
  private int encPidIdx = RobotMap.encPidIdx;


  private final AHRS navx = RobotMap._navx;
  /*
   * Motor state variables and unsorted values
   */
  private boolean mMecanumDrive = true;
  private boolean fieldOrientation = true;

  public PIDState.mode mPidMode;
  private double mTeleopPIDturn = 0;


  /*
   * PID Variables and Control
   */
  public double driveSpeed = 0; //The speed of lateral movement of the robot (not tilt)
  public DriveTrain() {
    super(0.03,0,0);
    this.setOutputRange(-0.5,0.5);
    this.setAbsoluteTolerance(5);
    setBrakeMode(); //Helps gliding on mecanum, may disable for diff
  }
  /**
   * Sets angle for robot to turn to using PIDSubsystem
   * @param startAngle angle to turn to
   */
  public void setAngle(double startAngle) {
    this.setSetpoint(startAngle);
  }
  /**
   * Begins PID cycle with set angle and vertical/horizontal control
   */
  public void startPID() {
    this.getPIDController().enable();
  }
  /**
   * Stops PID cycle
   */
  public void stopPID() {
    this.getPIDController().disable();
  }
  /**
   * Stop all motors.
   */
  public void stopMotors() {
    mRight1.stopMotor();
    mRight2.stopMotor();
    mLeft1.stopMotor();
    mLeft2.stopMotor();
  }
  /**
   * Checks navX for angle used for auto and field orinted driving.
   * @return navX angle
   */
  public double getNavxAngle()
  {
    return navx.getAngle();
  }
  /**
   * Initialize teleoperated control.
   * @param forwardsBackwardsAxis Controls vertical movement in differential and mecanum
   * @param horizontalAxis Controls horizontal movement of robot (without tilting) in mecanum
   * @param turningAxis Controls tilting of robot in one place in differential and mecanum
   */
  public void teleopControl(double forwardsBackwardsAxis, double horizontalAxis, double turningAxis) {
    //Fix inverted joystick
    if(mMecanumDrive) {
      if(fieldOrientation) {
        if(mPidMode == PIDState.mode.teleopTurn) {
          RobotMap.mecanumDrive.driveCartesian(horizontalAxis, forwardsBackwardsAxis, mTeleopPIDturn, getNavxAngle());
        } else {
          RobotMap.mecanumDrive.driveCartesian(horizontalAxis, forwardsBackwardsAxis, turningAxis, getNavxAngle());
        }
      }
      else {
        if(mPidMode == PIDState.mode.teleopTurn) {
          RobotMap.mecanumDrive.driveCartesian(horizontalAxis, forwardsBackwardsAxis, mTeleopPIDturn);
        } else {
          RobotMap.mecanumDrive.driveCartesian(horizontalAxis, forwardsBackwardsAxis, turningAxis);
        }
      }
    }
    else {
      RobotMap.differentialDrive.arcadeDrive(forwardsBackwardsAxis,turningAxis);
    }
  }
  /**
   * Initialize the default command for this subsystem.
   */
  public void initDefaultCommand() {
      setDefaultCommand(new TeleopDrive());
  }
  /**
   * Method is automatically called by PID to get the angle from the gyroscope
   * @return Raw Angle from navX MXP
   */
  @Override
  protected double returnPIDInput() {
    return navx.getAngle();
  }
  /**
   * Method is automatically called by PID to tilt the robot
   * @param output Speed control for tilt of robot
   */
  @Override
  protected void usePIDOutput(double output) {

    switch(mPidMode) {
      case vertical:
        teleopControl(driveSpeed,0,output);
        break;
      case horizontal:
        teleopControl(0,driveSpeed,output);
        break;
      case teleopTurn:
        this.mTeleopPIDturn = output;
        break;
      case disabled:
        break;
      default:
        CubLog.getInstance().warning("No PID Mode Selected");
        break;
    }

  }
  /**
   * Forces all talons into brake mode, creating a stronger friction when motors are not moving
   */
  public void setBrakeMode() {
    mLeft1.setNeutralMode(NeutralMode.Brake);
    mLeft2.setNeutralMode(NeutralMode.Brake);
    mRight1.setNeutralMode(NeutralMode.Brake);
    mRight2.setNeutralMode(NeutralMode.Brake);
  }
  /**
   * Forces all talons into coast mode, letting them coast after the motors stop moving
   */
  public void setCoastMode() {
    mLeft1.setNeutralMode(NeutralMode.Coast);
    mLeft2.setNeutralMode(NeutralMode.Coast);
    mRight1.setNeutralMode(NeutralMode.Coast);
    mRight2.setNeutralMode(NeutralMode.Coast);
  }
  /**
   * Changes teleop to mecanum drive and switches double solenoid to place omni wheels
   */
  public void setMecanumDrive() {
    if(mMecanumDrive) {
      CubLog.getInstance().warning("DT is already mecanum!");
      return;
    }
    this.mMecanumDrive = true;
    RobotMap.mecanumSwitch.set(DoubleSolenoid.Value.kForward);
    setBrakeMode();
  }
  /**
   * Changes teleop to differential drive and switches double solenoid to place standard wheels
   */
  public void setDifferentialDrive() {
    if(!mMecanumDrive) {
      CubLog.getInstance().warning("DT is already differential");
      return;
    }
    this.mMecanumDrive = false;
    RobotMap.mecanumSwitch.set(DoubleSolenoid.Value.kReverse);
    setCoastMode();
  }
  /**
   * Activates field orientation for mecanum drive
   * @param fieldOrin True for enabled; false or disabled
   */
  public void setFieldOrientation(boolean fieldOrin) {
    this.fieldOrientation = fieldOrin;
  }


  public void setPIDMode(PIDState.mode mode) {
    mPidMode = mode;
  }


  /**
   * Checks the current state of the drive mode
   * @return True for Mecanum Drive; False for Differential Drive
   */
  public boolean isMecanum() {
    return this.mMecanumDrive;
  }
  /**
   * Checks if field orientation is enabled for mecanum
   * @return True for enabled; false for disabled;
   */
  public boolean getFieldOrientation() {
    return this.fieldOrientation;
  }

  /**
   * Resets CTRE Mag Encoders on each side of drive train
   */
  public synchronized void resetEnc() {
    mLeft1.setSelectedSensorPosition(0,encPidIdx,0);
    mRight1.setSelectedSensorPosition(0,encPidIdx,0);
  }
  /**
   * Resets navX Yaw and drive train
   */
  public void zeroSensors() {
    resetEnc();
    navx.zeroYaw();
  }

}
