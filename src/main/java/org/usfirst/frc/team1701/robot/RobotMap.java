/**
 * RobotMap.java
 *
 * @author Noah Husby
 * @since 6/10/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team1701.robot.util.CubTalonSRX;


public class RobotMap {
  /*
   * Create all static variables, most (exception of logger) filled
   * by the init() method below.
   */
  public static CubTalonSRX _frontLeftMotor;
  public static CubTalonSRX _rearLeftMotor;
  public static CubTalonSRX _frontRightMotor;
  public static CubTalonSRX _rearRightMotor;
  public static SpeedControllerGroup _leftMotors;
  public static SpeedControllerGroup _rightMotors;
  public static DifferentialDrive differentialDrive;
  public static MecanumDrive mecanumDrive;
  public static DoubleSolenoid mecanumSwitch;
  public static AnalogInput pressureSensor;
  public static AnalogInput frontSensor;
  public static AnalogInput backSensor;
  public static AHRS _navx;
  public static int encPidIdx;
  public static PowerDistributionPanel pdp;
  /**
   * Initialize the public values above.
   */
  public static void init() {
    _frontLeftMotor = new CubTalonSRX(3);
    _frontLeftMotor.enableCurrentLimit(false);
    _rearLeftMotor = new CubTalonSRX(5);
    _rearLeftMotor.enableCurrentLimit(false);
    _frontRightMotor = new CubTalonSRX(1);
    _frontRightMotor.enableCurrentLimit(false);
    _rearRightMotor = new CubTalonSRX(2);
    _rearRightMotor.enableCurrentLimit(false);

    /**
     * Encoder PID index.
     * @value 0 for primary closed-loop, 1 for cascaded closed-loop.
     */
    encPidIdx = 0;
    /**
     * Create 4-wheel drivetrain object using DifferentialDrive and SpeedControllerGroups.
     */
    _leftMotors = new SpeedControllerGroup(_frontLeftMotor, _rearLeftMotor);
    _rightMotors = new SpeedControllerGroup(_frontRightMotor,_rearRightMotor);
    differentialDrive = new DifferentialDrive(_leftMotors, _rightMotors);
    mecanumDrive = new MecanumDrive(_frontLeftMotor,_rearLeftMotor,_frontRightMotor,_rearRightMotor);
    mecanumDrive.setSafetyEnabled(false);
    differentialDrive.setSafetyEnabled(false);
    /**
     * Initialize Pneumatics
     */
    mecanumSwitch = new DoubleSolenoid(0,0,1);
    /**
     * Initialize Analog Inputs
     */
    pressureSensor = new AnalogInput(0);
    frontSensor = new AnalogInput(3);
    backSensor = new AnalogInput(2);
    /**
     * Instantiate NavX.
     */
    _navx = new AHRS(SPI.Port.kMXP); //SPI navX-MXP
    pdp = new PowerDistributionPanel();
    LiveWindow.add(pdp);
  }

}
