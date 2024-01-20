package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoRight")
public class ScissorAutoRight extends ScissorBaseAuto{
    @Override
    public void runOpMode() {
        //initialize robot
        super.runOpMode();
        encoderDrive(0300, 24,24,24,24,15);
    }
}
