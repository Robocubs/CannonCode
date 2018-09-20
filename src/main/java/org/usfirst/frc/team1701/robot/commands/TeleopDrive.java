/**
 * commands/TeleopDrive.java
 *
 * @author Noah Husby
 * @since 6/10/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.RobotMap;
import org.usfirst.frc.team1701.robot.Stabilization;
import org.usfirst.frc.team1701.robot.controls.OI;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.subsystems.Shuffleboard;
import org.usfirst.frc.team1701.robot.util.TwoPhaseDigitalStick;

public class TeleopDrive extends Command {
  public TeleopDrive() {
    requires(Robot.driveTrain);
  }
  protected void initialize() {
   Robot.driveTrain.stopPID();
   Robot.driveTrain.setCoastMode();
   Stabilization.getInstance().reset();
  }
  protected void execute() {
    Shuffleboard.updateDashboard();
    double deadConst = .10;
    double fBInput = checkDeadZone(OI.drive_FB.getY(), deadConst);
    double hInput = checkDeadZone(OI.drive_FB.getX(), deadConst);
    double tInput = checkDeadZone(OI.drive_T.getX(),deadConst);
    Robot.driveTrain.teleopControl(-1* fBInput, hInput*-1, -1*tInput);
    RobotMap._turretTilt.set(TwoPhaseDigitalStick.getAnalogValue(OI.turretD, OI.turretU));
    RobotMap._turretRotate.set(TwoPhaseDigitalStick.getAnalogValue(OI.turretR,OI.turretL));
    //Stabilization.getInstance().stabilizeZ(tInput);
  }
  protected boolean isFinished() {
    return false;
  }
  protected void end() {}
  protected void interrupted() {}
  private double checkDeadZone(double input, double deadConst) {
    if (input > 0) {
      if (deadConst >= input) {
        input = 0;
      }
    } else {
      if (-deadConst <= input) {
        input = 0;
      }
    }
    return input;
  }
}
