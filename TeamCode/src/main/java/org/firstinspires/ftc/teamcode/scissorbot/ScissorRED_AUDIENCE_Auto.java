package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RED_AUDIENCE_Auto")
public class ScissorRED_AUDIENCE_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';
    private static final int tilesDriveToCenter = 2;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        switch (super.robot.zone) {
            case 1:
                // zone 1 (LEFT)
                break;
            case 2:
                // zone 2 (CENTER)
                break;
            case 3:
                // zone 3 (RIGHT)
                break;
        }
        switch (tilesDriveToCenter) {
            case 1:
                // stay the closest to the wall
                break;
            case 2:
                // strafe right after placing the pixel on the spike mark
                break;
            case 3:
                // go under the stage door, closer to the alliance's side
                break;
        }
        if (parkDir == 'L') {
            // park left
        } else {
            // park right
        }
    }
}
