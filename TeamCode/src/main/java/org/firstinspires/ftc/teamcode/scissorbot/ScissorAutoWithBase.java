package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoWithBase")
public class
ScissorAutoWithBase extends ScissorBaseAuto {
    @Override
    public void runOpMode() {
        super.runOpMode();
        driveBasic(0.3f);
        sleep(300);
        driveBasic(0);
    }
}
