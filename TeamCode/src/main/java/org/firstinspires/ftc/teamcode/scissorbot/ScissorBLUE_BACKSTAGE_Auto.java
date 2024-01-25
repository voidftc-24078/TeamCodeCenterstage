package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BLUEAutoShort")
public class ScissorBLUE_BACKSTAGE_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';

    private static final float apriltagLeftDistance = 17f;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        encoderDrive(0500, 0, 96, 96,0, 15);
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
        if (parkDir == 'L') {
            // park left
        } else {
            // park right
        }
    }
}
