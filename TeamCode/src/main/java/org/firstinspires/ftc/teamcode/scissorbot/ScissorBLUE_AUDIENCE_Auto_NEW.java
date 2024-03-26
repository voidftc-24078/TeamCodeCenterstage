package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BLUE_AUDIENCE_Auto")
public class ScissorBLUE_AUDIENCE_Auto_NEW extends ScissorBaseAuto {
    private static final char parkDir = 'R';
    private static final int tilesDriveToCenter = 1;

    private float apriltagLeftDistance = 0f;

    private float apriltagSpacing = 6.25f;

    private float apriltagOffset = 0f; // default to left apriltag

    private double totalApriltagDistance = 0;

    static final double parkDist = 25;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        closeClaw();
        switch (tilesDriveToCenter) {
            case 1:
                // stay the closest to the wall
                apriltagLeftDistance = 28f-(apriltagSpacing*2);
                encoderDrive(0500, 2.3, 2.3, 2.3, 2.3, 15);
                encoderDrive(1000, -72, 72, 72, -72, 6);
                break;
            case 2:
                // strafe left after placing the pixel on the spike mark
                apriltagLeftDistance = -apriltagSpacing;
                encoderDrive(0500, 25, 25, 25, 25, 10);
                encoderDrive(1000, -72, 72, 72, -72, 6);
                break;
            case 3:
                // go under the stage door, closer to the alliance's side
                apriltagLeftDistance = 21f+(apriltagSpacing+2);
                encoderDrive(1000, 40, 0, 0, 40, 4);
                encoderDrive(1000, 36, 36, 36, 36, 3);
                //encoderDrive(1000,56,56,56,56,13);
                encoderDrive(1400, -92, 92, 92, -92, 5);
                break;
        }
        if (tilesDriveToCenter == 3) {
            switch (super.robot.zone) {
                case 1:
                    // zone 1 (LEFT)
                    apriltagOffset = 0;
                    break;
                case 2:
                    // zone 2 (CENTER)
                    apriltagOffset = 1 * apriltagSpacing;
                    // return because we are already in the right position
                    break;
                case 3:
                    // zone 3 (RIGHT)
                    apriltagOffset = 2 * apriltagSpacing;
                    break;
            }
            totalApriltagDistance = apriltagLeftDistance + apriltagOffset;
            encoderDrive(0500, -totalApriltagDistance, -totalApriltagDistance, -totalApriltagDistance, -totalApriltagDistance, 8);
        } else if (tilesDriveToCenter == 2) {
            switch (super.robot.zone) {
                case 1:
                    // zone 1 (LEFT)
                    apriltagOffset = 2 * apriltagSpacing;
                    break;
                case 2:
                    // zone 2 (CENTER)
                    apriltagOffset = 1 * apriltagSpacing;
                    // return because we are already in the right position
                    break;
                case 3:
                    // zone 3 (RIGHT)
                    apriltagOffset = 0;
                    break;
            }
            totalApriltagDistance = apriltagLeftDistance + apriltagOffset;
            encoderDrive(0500, totalApriltagDistance, totalApriltagDistance, totalApriltagDistance, totalApriltagDistance, 8);
        } else {
            switch (super.robot.zone) {
                case 1:
                    // zone 1 (LEFT)
                    apriltagOffset = 0;
                    break;
                case 2:
                    // zone 2 (CENTER)
                    apriltagOffset = 1 * apriltagSpacing;
                    // return because we are already in the right position
                    break;
                case 3:
                    // zone 3 (RIGHT)
                    apriltagOffset = 2 * apriltagSpacing;
                    break;
            }
            totalApriltagDistance = apriltagLeftDistance - apriltagOffset;
            encoderDrive(0500, totalApriltagDistance, totalApriltagDistance, totalApriltagDistance, totalApriltagDistance, 8);
        }
        turnToHeading(90);
        // place pixel
        goUp();
        adjustScissor(0.4, 0.2);
        setWristPosition(super.robot.wrist.wristPosition + 0.16);
        encoderDrive(0500, -15, -15, -15, -15, 3);
        //waitScissor();
        openClaw();
        sleep(800);
        encoderDrive(1000, 4, 4, 4, 4, 1);
        //while(true); //temp !!
        goDown();
        if (parkDir == 'L') {
            // park left
            switch (super.robot.zone) {
                case 1:
                    encoderDrive(1000, parkDist-11, -(parkDist-11), -(parkDist-11), parkDist-11,4);
                    break;
                case 2:
                    encoderDrive(1000, ((parkDist+apriltagSpacing)-11), -((parkDist+apriltagSpacing)-11), -((parkDist+apriltagSpacing)-11), ((parkDist+apriltagSpacing)-11), 4);
                    break;
                case 3:
                    encoderDrive(1000, ((parkDist+(2*apriltagSpacing))-11), -((parkDist+(2*apriltagSpacing))-11), -((parkDist+(2*apriltagSpacing))-11), ((parkDist+(2*apriltagSpacing))-11), 4);
            }
        } else {
            // park right
            switch (super.robot.zone) {
                case 1:
                    encoderDrive(1000, -((parkDist+(2*apriltagSpacing))-11), ((parkDist+(2*apriltagSpacing))-11), ((parkDist+(2*apriltagSpacing))-11), -((parkDist+(2*apriltagSpacing))-11), 4);
                    break;
                case 2:
                    encoderDrive(1000, -((parkDist+apriltagSpacing)-11), ((parkDist+apriltagSpacing)-11), ((parkDist+apriltagSpacing)-11), -((parkDist+apriltagSpacing)-11), 4);
                    break;
                case 3:
                    encoderDrive(1000, -(parkDist-11), (parkDist-11), (parkDist-11), -(parkDist-11),4);
            }
        }
        //encoderDrive(1000, -10, -10, -10, -10, 6);

    }
}
