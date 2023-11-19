package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Encoder Test")
public class ScissorEncoderTestAuto extends ScissorBaseAuto {
    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        // we WILL be using encoders here, so
        // we don't disable them this time.

        // now drive forward 12 inches at speed 0.3, and then wait a second.
        encoderDrive(0.3, 12, 12, 12, 12, 1);
    }
}
