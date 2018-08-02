/**
 * subsystems/DriveTrain.java
 *
 * @author Noah Husby
 * @since 6/10/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.subsystems;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import org.usfirst.frc.team1701.robot.OI;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;
import org.usfirst.frc.team1701.robot.commands.TeleopDrive;
import com.kauailabs.navx.frc.AHRS;
public class DriveTrain extends PIDSubsystem {
  /*
   * Set of motors.
   */
  private final WPI_TalonSRX left_1 = RobotMap._frontLeftMotor;
  private final WPI_TalonSRX left_2 = RobotMap._rearLeftMotor;
  private final WPI_TalonSRX right_1 = RobotMap._frontRightMotor;
  private final WPI_TalonSRX right_2 = RobotMap._rearRightMotor;
  private int encPidIdx = RobotMap.encPidIdx;
  /*
   * NavX. 
   */
  private final AHRS navx = RobotMap._navx;
  /*
   * Motor state variables and unsorted values
   */
  private boolean mecanum = true;
  private boolean fieldOrientation = true;
  /*
   * PID Variables and Control
   */
  public double driveSpeed = 0; //The speed of lateral movement of the robot (not tilt)
  private boolean pidVert = true; //Changes whether the driveSpeed controls vertical or horizontal lateral
  public DriveTrain() {
    super(0.03,0,0);
    this.setInputRange(-360,360);
    this.setOutputRange(-0.5,0.5);
    this.setAbsoluteTolerance(5);
    this.getPIDController().setContinuous(false);
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
    right_1.stopMotor();
    right_2.stopMotor();
    left_1.stopMotor();
    left_2.stopMotor();
  }
  /**
   * Checks navX for angle used for auto and field orinted driving.
   * @return navX angle
   */
  public double getNavxAngle()
  {
    return -navx.getAngle();
  }
  /**
   * Initialize teleoperated control.
   * @param forwardsBackwardsAxis Controls vertical movement in differential and mecanum
   * @param horizontalAxis Controls horizontal movement of robot (without tilting) in mecanum
   * @param turningAxis Controls tilting of robot in one place in differential and mecanum
   */
  public void teleopControl(double forwardsBackwardsAxis, double horizontalAxis, double turningAxis) {
    //Fix inverted joystick
    if(mecanum) {
      if(fieldOrientation) {
        RobotMap.mecanumDrive.driveCartesian(horizontalAxis, forwardsBackwardsAxis, turningAxis, getNavxAngle());
      }
      else {
        RobotMap.mecanumDrive.driveCartesian(horizontalAxis, forwardsBackwardsAxis, turningAxis);
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
    if(pidVert) {
      teleopControl(driveSpeed,0,output);
    } else {
      teleopControl(0,driveSpeed,output);
    }
  }
  /**
   * Forces all talons into brake mode, creating a stronger friction when motors are not moving
   */
  public void setBrakeMode() {
    left_1.setNeutralMode(NeutralMode.Brake);
    left_2.setNeutralMode(NeutralMode.Brake);
    right_1.setNeutralMode(NeutralMode.Brake);
    right_2.setNeutralMode(NeutralMode.Brake);
  }
  /**
   * Forces all talons into coast mode, letting them coast after the motors stop moving
   */
  public void setCoastMode() {
    left_1.setNeutralMode(NeutralMode.Coast);
    left_2.setNeutralMode(NeutralMode.Coast);
    right_1.setNeutralMode(NeutralMode.Coast);
    right_2.setNeutralMode(NeutralMode.Coast);
  }
  /**
   * Changes teleop to mecanum drive and switches double solenoid to place omni wheels
   */
  public void setMecanumDrive() {
    this.mecanum = true;
    RobotMap.mecanumSwitch.set(DoubleSolenoid.Value.kForward);
    setBrakeMode();
  }
  /**
   * Changes teleop to differential drive and switches double solenoid to place standard wheels
   */
  public void setDifferentialDrive() {
    RobotMap.mecanumSwitch.set(DoubleSolenoid.Value.kReverse);
    this.mecanum = false;
    setCoastMode();
  }
  /**
   * Activates field orientation for mecanum drive
   * @param fieldOrin True for enabled; false or disabled
   */
  public void setFieldOrientation(boolean fieldOrin) {
    this.fieldOrientation = fieldOrin;
  }
  /**
   * PID's drive speed variable controls vertical movement
   */
  public void setPidVertical() {
    this.pidVert = true;
  }
  /**
   * PID's drive speed variable controls horizontal movement
   */
  public void setPidHorizontal() {
    this.pidVert = false;
  }
  /**
   * Checks the current state of the drive mode
   * @return True for Mecanum Drive; False for Differential Drive
   */
  public boolean isMecanum() {
    return this.mecanum;
  }
  /**
   * Checks if field orientation is enabled for mecanum
   * @return True for enabled; false for disabled;
   */
  public boolean getFieldOrientation() {
    return this.fieldOrientation;
  }
  /**
   * Checks if the current PID state is set to Vertical or Horizontal
   * @return True for Vertical; False for Horizontal
   */
  public boolean isPIDVert() {
    return this.pidVert;
  }
}
