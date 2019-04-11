package com.team1701.lib.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

@SuppressWarnings("all")
public class Limelight {
    private final NetworkTable table;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    /**
     * Get targeting status
     * @return True: target found, False otherwise
     */
    public boolean getTarget() {
        NetworkTableEntry tv_key = table.getEntry("tv");
        double tv = tv_key.getDouble(-1);
        return !(tv == 0);
    }
    /**
     * Get targeting status
     * @return 1: target not found, 0: found
     */
    public double getRawTarget() {
        NetworkTableEntry tv_key = table.getEntry("tv");
        return tv_key.getDouble(-1);
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
     * Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
     * @return double of vertical offset
     */
    public double getVerticalOffset() {
        NetworkTableEntry ty_key = table.getEntry("ty");
        return ty_key.getDouble(-1);
    }
    /**
     * Target Area (0% of image to 100% of image)
     * @return double of target area
     */
    public double getTargetArea() {
        NetworkTableEntry ta_key = table.getEntry("ta");
        return ta_key.getDouble(-1);
    }
    /**
     * Skew or rotation (-90 degrees to 0 degrees)
     * @return double of skew
     */
    public double getTargetSkew() {
        NetworkTableEntry ts_key = table.getEntry("ts");
        return ts_key.getDouble(-1);
    }
    /**
     * Sidelength of shortest side of the fitted bounding box (pixels)
     * @return double of short side
     */
    public double getTargetShortSide() {
        NetworkTableEntry tshort_key = table.getEntry("tshort");
        return tshort_key.getDouble(-1);
    }
    /**
     * Sidelength of longest side of the fitted bounding box (pixels)
     * @return double of long side
     */
    public double getTargetLongSide() {
        NetworkTableEntry tlong_key = table.getEntry("tlong");
        return tlong_key.getDouble(-1);
    }
    /**
     * Horizontal sidelength of the rough bounding box (0 - 320 pixels)
     * @return double of horizontal sidelength
     */
    public double getHorizontalSidelength() {
        NetworkTableEntry thor_key = table.getEntry("thor");
        return thor_key.getDouble(-1);
    }
    /**
     * Vertical sidelength of the rough bounding box (0 - 320 pixels)
     * @return double of vertical sidelength
     */
    public double getVerticalSidelength() {
        NetworkTableEntry tvert = table.getEntry("tvert");
        return tvert.getDouble(-1);
    }
    /**
     * True active pipeline index of the camera (0 .. 9)
     * @return int of pipeline
     */
    public int getPipeline() {
        NetworkTableEntry getpipe_key = table.getEntry("getpipe");
        return (int) getpipe_key.getNumber(-1);
    }

    /**
     * Sets limelight’s LED state
     * @param ledMode 0	use the LED Mode set in the current pipeline, 1	force off, 2 force blink, 3 force on
     */
    public void setLEDMode(LedMode ledMode) {
        NetworkTableEntry ledMode_key = table.getEntry("ledMode");
        ledMode_key.setNumber(ledMode.getNumVal());
    }

    /**
     * Sets limelight’s operation mode
     * @param cameraMode 0 Vision Processor, 1 Driver Camera (Increases exposure, disables vision processing)
     */
    public void setCameraMode(CameraMode cameraMode) {
        NetworkTableEntry camMode_key = table.getEntry("camMode");
        camMode_key.setNumber(cameraMode.getCamVal());
    }

    /**
     * Sets limelight’s current pipeline
     * @param pipeline Pipeline Number
     */
    public void setPipeline(int pipeline) {
        NetworkTableEntry pipeline_key = table.getEntry("pipeline");
        pipeline_key.setNumber(pipeline);
    }

    /**
     * Sets limelight’s streaming mode
     * @param streamMode 0 Standard, 1 PiP Main, 2 PiP Secondary
     */
    public void setStream(StreamMode streamMode) {
        NetworkTableEntry stream_key = table.getEntry("stream");
        stream_key.setNumber(streamMode.getStreamVal());
    }


    @SuppressWarnings("unused")
    public enum LedMode {
        kPipeline(0),
        kOff(1),
        kBlink(2),
        kOn(3);
        private final double ledVal;

        LedMode(double ledVal) {
            this.ledVal = ledVal;
        }

        double getNumVal() {return ledVal;}
    }

    @SuppressWarnings("unused")
    public enum CameraMode {
        kVision(0),
        kDriver(1);
        private final double camVal;

        CameraMode(double camVal) {this.camVal = camVal;}

        double getCamVal() {return camVal;}
    }

    @SuppressWarnings("unused")
    public enum StreamMode {
        kStandard(0),
        kMain(1),
        kSecondary(2);
        private final double streamVal;

        StreamMode(double streamVal) {this.streamVal = streamVal;}

        double getStreamVal() {return streamVal;}
    }
}
