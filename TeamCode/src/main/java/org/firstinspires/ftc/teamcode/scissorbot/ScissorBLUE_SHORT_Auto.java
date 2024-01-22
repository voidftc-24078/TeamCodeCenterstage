package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="BLUEAutoShort")
public class ScissorBLUE_SHORT_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'L';

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        if (parkDir == 'L') {
            // park left
        } else {
            // park right
        }
    }
}
