/**
 * Shuffleboard.java
 *
 * @author Noah Husby
 * @since 6/10/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.subsystems;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1701.robot.Stabilization;
import org.usfirst.frc.team1701.robot.controls.OI;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;
public class Shuffleboard {
    public static SendableChooser<Number> location;
    public static SendableChooser<Number> goal;
    /**
     * This method runs everytime the robot gets an update from the driver station (About 20ms), and displays values
     * on shuffleboard.
     */
    public static void updateDashboard() {
        SmartDashboard.putNumber("Angle", Robot.driveTrain.getNavxAngle());
        SmartDashboard.putBoolean("Mecanum Mode", Robot.driveTrain.isMecanum());
        SmartDashboard.putBoolean("Field Oriented", Robot.driveTrain.getFieldOrientation());
        SmartDashboard.putBoolean("Stable", Stabilization.getInstance().isStabilizationActive());
        SmartDashboard.putString("PidMode", Robot.driveTrain.mPidMode.toString());
        SmartDashboard.putNumber("CurrentCannon", RobotMap.currentCannonSelection);
    }
}
