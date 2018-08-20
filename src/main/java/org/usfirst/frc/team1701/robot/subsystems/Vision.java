/**
 * subsystems/Vision.java
 *
 * @author Nicholas Hubbard
 * @since 2018-01-20
 * @license BSD-3-Clause
 */
package org.usfirst.frc.team1701.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
public class Vision extends Subsystem {
    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    /**
     * Get targeting status.
     * @return boolean: true if target found, false if not
     */
    public boolean getTarget() {
        NetworkTableEntry tv_key = table.getEntry("tv");
        double tv = tv_key.getDouble(-1);
      return !(tv == 0);
    }
    /**
     * Get horizontal offset.
     * @return double of horizontal offset
     */
    public double getHorizontalOffset() {
        NetworkTableEntry tx_key = table.getEntry("tx");
        return tx_key.getDouble(-1);
    }
    /**
     * Get vertical offset.
     * @return double of vertical offset.
     */
    public double getVerticalOffset() {
        NetworkTableEntry ty_key = table.getEntry("ty");
        return ty_key.getDouble(-1);
    }
    /**
     * Get targeting area.
     * @return double of area.
     */
    public double getTargetArea() {
        NetworkTableEntry ta_key = table.getEntry("ta");
        return ta_key.getDouble(-1);
    }
    /**
     * Get target skew.
     * @return double of skew.
     */
    public double getTargetSkew() {
        NetworkTableEntry ts_key = table.getEntry("ts");
        return ts_key.getDouble(-1);
    }
    /**
     * Set LED mode.
     * @param mode 0 for on, 1 for off, 2 for blink
     */
    public void setLedMode(Number mode) {
        NetworkTableEntry ledMode_key = table.getEntry("ledMode");
        ledMode_key.setNumber(mode);
    }
    /**
     * Set camera mode.
     * @param mode processing is 0, driver camera is 1
     */
    public void setCameraMode(Number mode) {
        NetworkTableEntry cameraMode_key = table.getEntry("cameraMode");
        cameraMode_key.setNumber(mode);
    }
    /**
     * Set the pipeline.
     * @param pipeline Pipeline number, ranges from 0 to 9
     */
    public void setPipeline(Number pipeline) {
        NetworkTableEntry pipeline_key = table.getEntry("pipeline");
        pipeline_key.setNumber(pipeline);
    }
    /**
     * Set the PIP mode.
     * @param mode PIP mode. See Limelight NetworkTables API documentation.
     */
    public void setPIPMode(Number mode) {
        NetworkTableEntry stream_key = table.getEntry("stream");
        stream_key.setNumber(mode);
    }
    /**
     * Initialize default command. Currently nothing.
     */
    public void initDefaultCommand() {}
}
