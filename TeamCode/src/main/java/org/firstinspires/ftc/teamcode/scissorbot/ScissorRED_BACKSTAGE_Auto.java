package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RED_BACKSTAGE_Auto")
public class ScissorRED_BACKSTAGE_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
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
