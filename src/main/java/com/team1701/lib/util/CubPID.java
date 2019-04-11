package com.team1701.lib.util;

@SuppressWarnings("all")
public class CubPID {

    private final double kP,kI,kD;
    private double valueRet = 0;
    private double integral = 0;
    private double setpoint = 0;
    private double deadband = 0.2;
    private double tolerance = 0;

    public CubPID(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public void setDeadband(double db) {
        this.deadband = db;
    }

    public void setTolerance(double t) {
        this.tolerance = t;
    }

    private double limit(double value) {
        if (value > 1) {
            return 1;
        } else if (value < -1) {
            return -1;
        }
        return value;
    }

    private double excludeDeadband(double value) {
        if(Math.abs(value) < deadband) {
            return 0;
        }
        return limit(value);
    }

    private double assumeTolerance(double input) {
        if(setpoint-tolerance <= input && input < setpoint + tolerance) {
            return setpoint;
        }

        return input;
    }

    public double getValue() {
        return valueRet;
    }

    public double calculate(double input) {
        double error = setpoint - assumeTolerance(input);
        this.integral += (error *.02);
        double previous_error = 0;
        double derivative = (error - previous_error) / .02;
        valueRet = excludeDeadband(kP*error + kI*this.integral + kD*derivative);
        return valueRet;
    }
}
