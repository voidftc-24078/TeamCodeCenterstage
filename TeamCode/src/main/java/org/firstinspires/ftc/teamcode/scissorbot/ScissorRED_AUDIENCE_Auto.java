package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RED_AUDIENCE_Auto")
public class ScissorRED_AUDIENCE_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';
    private static final int tilesDriveToCenter = 3;

    private float apriltagLeftDistance = 0f;

    private float apriltagSpacing = 6.25f;

    private float apriltagOffset = 0f; // default to left apriltag

    private double totalApriltagDistance = 0;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        closeClaw();
        switch (tilesDriveToCenter) {
            case 1:
                // stay the closest to the wall
                apriltagLeftDistance = 17.4f;
                encoderDrive(0500, 3, 3, 3, 3, 15);
                encoderDrive(1000, 72, -72, -72, 72, 6);
                break;
            case 2:
                // strafe left after placing the pixel on the spike mark
                apriltagLeftDistance = -apriltagSpacing+1;
                encoderDrive(0500, 25, 25, 25, 25, 10);
                encoderDrive(1000, 72, -72, -72, 72, 6);
                break;
            case 3:
                // go under the stage door, closer to the alliance's side
                apriltagLeftDistance = 19f;
                encoderDrive(1000, 0, 40, 40, 0, 4);
                encoderDrive(1000, 36, 36, 36, 36, 3);
                //encoderDrive(1000,56,56,56,56,13);
                encoderDrive(1500, 92, -92, -92, 92, 10);
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
            encoderDrive(1000, -totalApriltagDistance, -totalApriltagDistance, -totalApriltagDistance, -totalApriltagDistance, 1);
        } else {
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
            encoderDrive(1000, totalApriltagDistance, totalApriltagDistance, totalApriltagDistance, totalApriltagDistance, 2);
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
                    encoderDrive(1000, 15, -15, -15, 15,4);
                    break;
                case 2:
                    encoderDrive(1000, (15+apriltagSpacing), -(15+apriltagSpacing), -(15+apriltagSpacing), (15+apriltagSpacing), 4);
                    break;
                case 3:
                    encoderDrive(1000, (15+(2*apriltagSpacing)), -(15+2*(apriltagSpacing)), -(15+2*(apriltagSpacing)), (15+2*(apriltagSpacing)), 4);
            }
        } else {
            // park right
            switch (super.robot.zone) {
                case 1:
                    encoderDrive(1000, -(15+(2*apriltagSpacing)), (15+2*(apriltagSpacing)), (15+2*(apriltagSpacing)), -(15+2*(apriltagSpacing)), 4);
                    break;
                case 2:
                    encoderDrive(1000, -(15+apriltagSpacing), (15+apriltagSpacing), (15+apriltagSpacing), -(15+apriltagSpacing), 4);
                    break;
                case 3:
                    encoderDrive(1000, 15, -15, -15, 15,4);
            }
        }
        //encoderDrive(1000, -10, -10, -10, -10, 6);

    }
}
