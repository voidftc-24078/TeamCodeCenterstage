package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoWithBase")
public class ScissorAutoWithBase extends ScissorBaseAuto {
    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        // run without encoders for this auto.
        robot.drivetrain.disableEncoders();
        // now go forward at speed 0.3 for 0.3 seconds.
        // the "f" here means float (a decimal number)
        driveBasic(0.3f);
        sleep(300);
        driveBasic(0);
    }
}
