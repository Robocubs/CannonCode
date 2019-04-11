/*
  Robocubs Library
  Protects buttons commands from actuating every iteration

  @author Noah Husby
 */
package com.team1701.lib.util;

public class ButtonFeed {

    private boolean lastState = false;

    private boolean enabledFromFeed = true;

    public ButtonFeed() { }

    public boolean state(boolean button) {
        if(button != lastState && button) {
            this.lastState = true;
            return enabledFromFeed;
        } else if(!button && !enabledFromFeed) {
            lastState = false;
            enabledFromFeed = true;
        }

        return false;
    }

    public void feedout() {
        enabledFromFeed = false;
    }

}
