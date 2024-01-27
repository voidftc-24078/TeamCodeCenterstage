package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BLUE_AUDIENCE_Auto")
public class ScissorBLUE_AUDIENCE_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';
    private static final int tilesDriveToCenter = 3;

    private float apriltagLeftDistance = 0f;

    private float apriltagSpacing = 6.25f;

    private float apriltagOffset = 0f; // default to left apriltag

    private double totalApriltagDistance = 0;

    private double travelDist = 15;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        closeClaw();
        switch (tilesDriveToCenter) {
            case 1:
                // stay the closest to the wall
                apriltagLeftDistance = 17.4f;
                encoderDrive(0500, 2, 2, 2, 2, 15);
                encoderDrive(1000, -72, 72, 72, -72, 6);
                break;
            case 2:
                // strafe left after placing the pixel on the spike mark
                apriltagLeftDistance = apriltagSpacing;
                encoderDrive(0500, 25, 25, 25, 25, 10);
                encoderDrive(1000, -72, 72, 72, -72, 6);
                break;
            case 3:
                // go under the stage door, closer to the alliance's side
                apriltagLeftDistance = -60f;
                encoderDrive(1000, 40, 0, 0, 40, 2);
                encoderDrive(1000, 36, 36, 36, 36, 3);
                //encoderDrive(1000,56,56,56,56,13);
                encoderDrive(1500, -92, 92, 92, -92, 3);
                break;
        }
        switch (super.robot.zone) {
            case 1:
                // zone 1 (LEFT)
                apriltagOffset = 0;
                break;
            case 2:
                // zone 2 (CENTER)
                apriltagOffset = 1 * apriltagSpacing;
                break;
            case 3:
                // zone 3 (RIGHT)
                apriltagOffset = 2 * apriltagSpacing;
                break;
        }
        totalApriltagDistance = apriltagLeftDistance + apriltagOffset;
        if (tilesDriveToCenter == 3) {
            encoderDrive(1000, -totalApriltagDistance, -totalApriltagDistance, -totalApriltagDistance, -totalApriltagDistance, 1);
        } else {
            encoderDrive(1000, totalApriltagDistance, totalApriltagDistance, totalApriltagDistance, totalApriltagDistance, 1);
        }
        turnToHeading(-90);
        // place pixel
        goUp();
        adjustScissor(0.4, 0.2);
        setWristPosition(super.robot.wrist.wristPosition + 0.16);
        encoderDrive(0500, -13, -13, -13, -13, 3);
        //waitScissor();
        openClaw();
        //sleep(800);
        encoderDrive(1000, 13, 13, 13, 13, 1);
        //while(true); //temp !!
        goDown();
        if (parkDir == 'L') {
            // park left
            switch (super.robot.zone) {
                case 1:
                    encoderDrive(1000, travelDist, -travelDist, -travelDist, travelDist, 4);
                    break;
                case 2:
                    encoderDrive(1000, (travelDist + apriltagSpacing), -(travelDist + apriltagSpacing), -(travelDist + apriltagSpacing), (travelDist + apriltagSpacing), 4);
                    break;
                case 3:
                    encoderDrive(1000, (travelDist + (apriltagSpacing*2)), -(travelDist + (apriltagSpacing*2)), -(travelDist + (apriltagSpacing*2)), (travelDist + (apriltagSpacing*2)), 4);
            }
        } else {
            // park right
            switch (super.robot.zone) {
                case 1:
                    encoderDrive(1000, -travelDist, travelDist, travelDist, -travelDist, 4);
                    break;
                case 2:
                    encoderDrive(1000, -(travelDist + apriltagSpacing), (travelDist + apriltagSpacing), (travelDist + apriltagSpacing), -(travelDist + apriltagSpacing), 4);
                    break;
                case 3:
                    encoderDrive(1000, -(travelDist + (apriltagSpacing*2)), (travelDist + (apriltagSpacing*2)), (travelDist + (apriltagSpacing*2)), -(15 + (apriltagSpacing*2)), 4);
            }
        }
        encoderDrive(1000, -20, -20, -20, -20, 6);

    }
}
