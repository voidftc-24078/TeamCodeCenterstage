package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RED_BACKSTAGE_Auto")
public class ScissorRED_BACKSTAGE_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';

    private float apriltagOffset = 0f;

    private float apriltagSpacing = 6.25f;

    private double totalApriltagOffset = 0;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        encoderDrive(0500,48,0,0,48, 15);
        switch (super.robot.zone) {
            case 1:
                // zone 1 (LEFT)
               apriltagOffset = apriltagSpacing;
                break;
            case 2:
                // zone 2 (CENTER)
                apriltagOffset = 0;
                break;
            case 3:
                // zone 3 (RIGHT)
                apriltagOffset = -apriltagSpacing;
                break;
        }
        totalApriltagOffset = 3+apriltagOffset;
        encoderDrive(0500,totalApriltagOffset,totalApriltagOffset,totalApriltagOffset,totalApriltagOffset,15);
        turnToHeading(90);
        if (parkDir == 'L') {
            // park left
        } else {
            // park right
        }
    }
}
