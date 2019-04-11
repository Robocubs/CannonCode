package com.team1701.frc2019.subsystems;

import com.team1701.lib.loops.ILooper;
import com.team1701.lib.loops.Loop;
import com.team1701.lib.subsystem.Subsystem;
import edu.wpi.first.wpilibj.Compressor;

public class Hardware extends Subsystem {

    private final Compressor comp;

    private static final Hardware mInstance = new Hardware();

    private final Loop mLoop = new Loop() {
        @Override
        public void onStart(double timestamp) {
            synchronized (Hardware.this) { }
        }

        @Override
        public void onLoop(double timestamp) {
            synchronized (Hardware.this) { }
        }

        @Override
        public void onStop(double timestamp) {
            synchronized (Hardware.this) { }
        }
    };

    @Override
    public void registerEnabledLoops(ILooper in) {
        in.register(mLoop);
    }

    public static Hardware getInstance() {
        return mInstance;
    }

    private Hardware() {
        comp = new Compressor();
        comp.clearAllPCMStickyFaults();
    }

    @Override
    public void outputTelemetry() { }

    @Override
    public void stop() {
        comp.stop();
    }
}
