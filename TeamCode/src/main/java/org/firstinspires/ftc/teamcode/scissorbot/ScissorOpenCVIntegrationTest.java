package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ScissorOpenCVIntegrationTest")
public class ScissorOpenCVIntegrationTest extends ScissorBaseAuto {

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        switch (super.robot.zone) {
            case 1:
                log("zone 1 (LEFT)");
                return;
            case 2:
                log("zone 2 (CENTER):");
                return;
            case 3:
                log("zone 3 (RIGHT)");
                return;
        }
    }
}
