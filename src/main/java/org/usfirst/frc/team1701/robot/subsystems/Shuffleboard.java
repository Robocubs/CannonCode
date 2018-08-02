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
import org.usfirst.frc.team1701.robot.OI;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;
public class Shuffleboard {
    public static SendableChooser<Number> location;
    public static SendableChooser<Number> goal;
    /**
     * Creates our shuffleboard objects
     */
    public static void init() {
        /*
         * Initialize Autonomous Location Chooser;
         * Allows operator/technician to select robot start position before match
         */
        location = new SendableChooser<>();
        location.addDefault("Default Location",2);
        SmartDashboard.putData("Autonomous Location", location);
        /*
         * Initialize Autonomous Chooser
         * Allows operator/technician to select type of autonomous to be run
         */
        goal = new SendableChooser<>();
        goal.addDefault("Defualt Autonomous", 1);
        SmartDashboard.putData("Autonomous Chooser",goal);

        /*
         * Shuffleboard elements to be updated during teleop period
         */
        SmartDashboard.putBoolean("Mecanum Mode", false);
        SmartDashboard.putBoolean("Field Oriented", false);
    }
    /**
     * Method called during teleop to update shuffleboard elements live
     */
    public static void updateDashboard() {
        SmartDashboard.putBoolean("Mecanum Mode", Robot.driveTrain.isMecanum());
        SmartDashboard.putBoolean("Field Oriented", Robot.driveTrain.getFieldOrientation());
    }
}
