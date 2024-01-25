package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BLUE_AUDIENCE_Auto")
public class ScissorBLUE_AUDIENCE_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';
    private static final int tilesDriveToCenter = 1;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        switch (tilesDriveToCenter) {
            case 1:
                // stay the closest to the wall
                encoderDrive(0500, 1.5,1.5,1.5,1.5,15);
                encoderDrive(1200, -72,72,72,-72,6);
                turnToHeading(-90);
                return;
            case 2:
                // strafe left after placing the pixel on the spike mark
                encoderDrive(0500,25,25,25,25,10);
                encoderDrive(1200, -72,72,72,-72,6);
                turnToHeading(-90);
                return;
            case 3:
                // go under the stage door, closer to the alliance's side
                encoderDrive(1000,40,0,0,40,4);
                encoderDrive(1000, 36,36,36,36,3);
                //encoderDrive(1000,56,56,56,56,13);
                encoderDrive(1200, -92,92,92,-92,6);
                turnToHeading(-90);
                return;
        }
        switch (super.robot.zone) {
            case 1:
                // zone 1 (LEFT)
                return;
            case 2:
                // zone 2 (CENTER)
                return;
            case 3:
                // zone 3 (RIGHT)
                return;
        }
        if (parkDir == 'L') {
            // park left
        } else {
            // park right
        }
    }
}
