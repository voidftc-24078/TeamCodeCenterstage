package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BLUE_AUDIENCE_Auto")
public class ScissorBLUE_AUDIENCE_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';
    private static final int tilesDriveToCenter = 1;

    private float apriltagLeftDistance = 0f;

    private float apriltagSpacing = 6.25f;

    private float aprtiltagOffset = 0f; // default to left apriltag

    private double totalApriltagDistance = 0;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        switch (tilesDriveToCenter) {
            case 1:
                // stay the closest to the wall
                apriltagLeftDistance = 17f;
                encoderDrive(0500, 1.5,1.5,1.5,1.5,15);
                encoderDrive(1200, -72,72,72,-72,6);
                break;
            case 2:
                // strafe left after placing the pixel on the spike mark
                apriltagLeftDistance = -apriltagSpacing;
                encoderDrive(0500,25,25,25,25,10);
                encoderDrive(1200, -72,72,72,-72,6);
                break;
            case 3:
                // go under the stage door, closer to the alliance's side
                apriltagLeftDistance = -37.5f;
                encoderDrive(1000,40,0,0,40,4);
                encoderDrive(1000, 36,36,36,36,3);
                //encoderDrive(1000,56,56,56,56,13);
                encoderDrive(1200, -92,92,92,-92,6);
                break;
        }
        turnToHeading(-90);
        switch (super.robot.zone) {
            case 1:
                // zone 1 (LEFT)
                aprtiltagOffset = 0;
                break;
            case 2:
                // zone 2 (CENTER)
                aprtiltagOffset = 1*apriltagSpacing;
                // return because we are already in the right position
                break;
            case 3:
                // zone 3 (RIGHT)
                aprtiltagOffset = 2*apriltagSpacing;
                break;
        }
        totalApriltagDistance = apriltagLeftDistance + aprtiltagOffset;
        encoderDrive(1000, -totalApriltagDistance, totalApriltagDistance, totalApriltagDistance, -totalApriltagDistance, 15);
        // place pixel
        if (parkDir == 'L') {
            // park left
        } else {
            // park right
        }
    }
}
