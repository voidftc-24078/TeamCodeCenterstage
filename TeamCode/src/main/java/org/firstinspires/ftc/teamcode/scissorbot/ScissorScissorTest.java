package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ScissorTestAuto")
public class ScissorScissorTest extends ScissorBaseAuto {

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        closeClaw();
        sleep(1000);
        goUp();
        openClaw();
        sleep(1000);
        goDown();
        openClaw();
        sleep(1000);
    }
}
