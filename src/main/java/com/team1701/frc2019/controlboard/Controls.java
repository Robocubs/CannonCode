package com.team1701.frc2019.controlboard;

/**
 * Controls sets the method of control to a choice of Control Board or Gamepad.
 */
public class Controls {

    public static IControlBoard getControls() {
            return MainControlBoard.getInstance();
    }

    /**
     * Updates every iteration and checks for button presses
     */
    @SuppressWarnings("EmptyMethod")
    public static void actionControls() {
    }

}
