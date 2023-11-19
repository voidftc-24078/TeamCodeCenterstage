package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoWithBase")
public class
ScissorAutoWithBase extends ScissorBaseAuto {
    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        // now go forward at speed 0.3 for 0.3 seconds.
        driveBasic(0.3f); // the "f" here means float (a decimal number)
        sleep(300);
        driveBasic(0);
    }
}
