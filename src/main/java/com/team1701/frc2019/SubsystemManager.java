package com.team1701.frc2019;
import com.team1701.frc2019.controlboard.Controls;
import com.team1701.lib.loops.ILooper;
import com.team1701.lib.loops.Loop;
import com.team1701.lib.loops.Looper;
import com.team1701.lib.subsystem.Subsystem;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage subsystems in a more effective way than other methods.
 * @author Noah Husby
 */
public class SubsystemManager implements ILooper {
    private final List<Subsystem> mAllSubsystems;
    private final List<Loop> mLoops = new ArrayList<>();

    public SubsystemManager(List<Subsystem> allSubsystems) {
        this.mAllSubsystems = allSubsystems;
    }

    /**
     * Reload telemetry for all subsystems.
     */
    private void outputToSmartDashboard() {
        mAllSubsystems.forEach(Subsystem::outputTelemetry);
    }
    /**
     * Stop all subsystems.
     */
    private void stop() {
        mAllSubsystems.forEach(Subsystem::stop);
    }
    /**
     * Enabled loop implementation. Loop implements threading.
     */
    private class EnabledLoop implements Loop {
        @Override
        public void onStart(double timestamp) {
            for (Loop l : mLoops) {
                l.onStart(timestamp);
            }
        }

        @Override
        public void onLoop(double timestamp) {
            for (Subsystem s : mAllSubsystems) {
                s.readPeriodicInputs();
            }

            for(Loop l : mLoops) {
                l.onLoop(timestamp);
            }

            for (Subsystem s : mAllSubsystems) {
                s.writePeriodicOutputs();
            }

            outputToSmartDashboard();

            Controls.actionControls();
        }

        @Override
        public void onStop(double timestamp) {
            stop();
        }
    }

    /**
     * Opposite of EnabledLoop.
     */
    private class DisabledLoop implements Loop {

        @Override
        public void onStart(double timestamp) {

        }

        @Override
        public void onLoop(double timestamp) {
            for (Subsystem s : mAllSubsystems) {
                s.readPeriodicInputs();
            }
            for (Subsystem s : mAllSubsystems) {
                s.writePeriodicOutputs();
            }
        }

        @Override
        public void onStop(double timestamp) {

        }
    }

    /**
     * Register enabled loops in the subsystem manager.
     */
    public void registerEnabledLoops(Looper enabledLooper) {
        mAllSubsystems.forEach((s) -> s.registerEnabledLoops(this));
        enabledLooper.register(new EnabledLoop());
    }

    /**
     * Register disabled loops in the subsystem manager.
     */
    public void registerDisabledLoops(Looper disabledLooper) {
        disabledLooper.register(new DisabledLoop());
    }

    /**
     * Register loops.
     */
    @Override
    public void register(Loop loop) {
        mLoops.add(loop);
    }
}
