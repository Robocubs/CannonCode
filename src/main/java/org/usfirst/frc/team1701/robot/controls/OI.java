/*
 * OI.java
 *
 * @author Noah Husby
 * @since 6/10/18
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.controls;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team1701.robot.commands.Center;
import org.usfirst.frc.team1701.robot.commands.ToggleFieldOrientation;
import org.usfirst.frc.team1701.robot.commands.ToggleMecanum;
import org.usfirst.frc.team1701.robot.commands.ToggleZStable;

/*
 * This class sets up and enables the Joysticks and Buttons on your console.
 */
public class OI {

  public static Joystick operation;
  public static Joystick drive_FB;
  public static Joystick drive_T;
  public static JoystickButton mecanumSwitch;
  public static JoystickButton fieldOr;
  public static JoystickButton center;
  /**
   * Instead of an init() function, we call this on OI startup.
   */
  public OI() {
    /*
     * Enable the physical joysticks.
     */
    operation = new Joystick(0);
    drive_FB = new Joystick(1);
    drive_T = new Joystick(2);
    /*
     * Assign commands to buttons.
     */
    mecanumSwitch = new JoystickButton(drive_FB,1);
    mecanumSwitch.whenPressed(new ToggleMecanum());
    fieldOr = new JoystickButton(drive_FB,4);
    fieldOr.whenPressed(new ToggleFieldOrientation());
    center = new JoystickButton(drive_FB,5);
    center.whenPressed(new ToggleZStable());
  }
}