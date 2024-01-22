package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RED_LONG_Auto")
public class ScissorRED_LONG_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';
    private static final int tilesDriveToCenter = 2;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        switch (tilesDriveToCenter) {
            case 1:
                // stay the closest to the wall
                return;
            case 2:
                // strafe right after placing the pixel on the spike mark
                return;
            case 3:
                // go under the stage door, closer to the alliance's side
                return;
        }
        if (parkDir == 'L') {
            // park left
        } else {
            // park right
        }
    }
}
