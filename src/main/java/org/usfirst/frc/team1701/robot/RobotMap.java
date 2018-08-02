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


public class RobotMap {
  /*
   * Create all static variables, most (exception of logger) filled
   * by the init() method below.
   */
  public static WPI_TalonSRX _frontLeftMotor;
  public static WPI_TalonSRX _rearLeftMotor;
  public static WPI_TalonSRX _frontRightMotor;
  public static WPI_TalonSRX _rearRightMotor;
  public static WPI_TalonSRX _turretX;
  public static WPI_TalonSRX _turretY;
  public static SpeedControllerGroup _leftMotors;
  public static SpeedControllerGroup _rightMotors;
  public static DifferentialDrive differentialDrive;
  public static MecanumDrive mecanumDrive;
  public static DoubleSolenoid mecanumSwitch;
  public static AHRS _navx;
  public static int encPidIdx;
  /**
   * Initialize the public values above.
   */
  public static void init() {
    _frontLeftMotor = new WPI_TalonSRX(4);
    _rearLeftMotor = new WPI_TalonSRX(8);
    _frontRightMotor = new WPI_TalonSRX(5);
    _rearRightMotor = new WPI_TalonSRX(3);
    _turretX = new WPI_TalonSRX(2);
    _turretY = new WPI_TalonSRX(1);
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
     * Instantiate NavX.
     */
    _navx = new AHRS(SPI.Port.kMXP); //SPI navX-MXP
  }

}
