/*
  controlboard/IControlBoard.interface

  @author Noah Husby
 * @since 2018-11-3
 * @license BSD-3-Clause
 */
package com.team1701.frc2019.controlboard;

/**
 * The basic requirements for a control board.
 */
public interface IControlBoard {
    double getThrottle();

    double getRotation();

    double getPan();

    double getTilt();

    boolean shoot();
}
