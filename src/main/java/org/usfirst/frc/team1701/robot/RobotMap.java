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
  public static CubTalonSRX _turretRotate;
  public static CubTalonSRX _turretTilt;
  public static SpeedControllerGroup _leftMotors;
  public static SpeedControllerGroup _rightMotors;
  public static DifferentialDrive differentialDrive;
  public static MecanumDrive mecanumDrive;
  public static DoubleSolenoid mecanumSwitch;
  public static Solenoid cannon1;
  public static Solenoid cannon2;
  public static Solenoid cannon3;
  public static Solenoid cannon4;
  public static Solenoid cannon5;
  public static Solenoid cannon6;
  public static AHRS _navx;
  public static int encPidIdx;
  public static int currentCannonSelection = 1;
  public static Spark trainLED;
  public static Spark turretLED;
  /*
   * Initialize the static objects above, this is how we access our electronics.
   */
  public static void init() {
    /*
    Initialize the motor controllers
     */
    _frontLeftMotor = new CubTalonSRX(3);
    _frontLeftMotor.enableCurrentLimit(false);
    _rearLeftMotor = new CubTalonSRX(4);
    _rearLeftMotor.enableCurrentLimit(false);
    _frontRightMotor = new CubTalonSRX(2);
    _frontRightMotor.enableCurrentLimit(false);
    _rearRightMotor = new CubTalonSRX(0);
    _rearRightMotor.enableCurrentLimit(false);
    _turretRotate = new CubTalonSRX(5);
    _turretTilt = new CubTalonSRX(6);

    /*
     * Encoder PID index.
     * @value 0 for primary closed-loop, 1 for cascaded closed-loop.
     */
    encPidIdx = 0;
    /*
     * Create 4-wheel drivetrain object using DifferentialDrive and SpeedControllerGroups.
     */
    _leftMotors = new SpeedControllerGroup(_frontLeftMotor, _rearLeftMotor);
    _rightMotors = new SpeedControllerGroup(_frontRightMotor,_rearRightMotor);
    differentialDrive = new DifferentialDrive(_leftMotors, _rightMotors);
    mecanumDrive = new MecanumDrive(_frontLeftMotor,_rearLeftMotor,_frontRightMotor,_rearRightMotor);
    mecanumDrive.setSafetyEnabled(false);
    differentialDrive.setSafetyEnabled(false);
    /*
     * Initialize pneumatic controller and each solenoid name
     */
    mecanumSwitch = new DoubleSolenoid(1, 1,0);
    cannon1 = new Solenoid(0,2);
    cannon2 = new Solenoid(0,3);
    cannon3 = new Solenoid(0,4);
    cannon4 = new Solenoid(0,5);
    cannon5 = new Solenoid(0,6);
    cannon6 = new Solenoid(0,7);
    /*
     * Initialize gryoscope.
     */
    _navx = new AHRS(SPI.Port.kMXP); //SPI navX-MXP
    /*
     * Initialize Rev Robotics Blinkin LED Driver as a spark motor controller
     * http://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
     */
    trainLED = new Spark(0);
    turretLED = new Spark(1);
  }

}
