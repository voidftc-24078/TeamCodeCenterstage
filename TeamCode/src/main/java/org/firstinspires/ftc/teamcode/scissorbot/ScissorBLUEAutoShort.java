package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="BLUEAutoShort")
public class ScissorBLUEAutoShort extends ScissorBaseAuto{
    @Override
    public void runOpMode() {
        //initialize robot
        super.runOpMode();
        encoderDrive(1200, 12,12,12,12, 1);
        encoderDrive(1200, 0,84, 84,0,8);
        encoderDrive(1000,-4,-4,-4,-4, 2);
    }
}