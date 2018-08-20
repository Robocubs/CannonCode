package org.usfirst.frc.team1701.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.Stabilization;

public class ToggleZStable extends Command {
    public ToggleZStable() {
        requires(Robot.driveTrain);
    }
    protected void execute() {
        Stabilization.getInstance().setZStabilization(!Stabilization.getInstance().isStabilizationActive());
        Stabilization.getInstance().reset();
    }
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    }
    protected void interrupted() {}
}
