package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="REDAutoShort")
public class ScissorREDAutoShort extends ScissorBaseAuto{
    @Override
    public void runOpMode() {
        //initialize robot
        super.runOpMode();
        encoderDrive(1200, 12,12,12,12, 1);
        encoderDrive(1200, 84,0,0,84,8);
        encoderDrive(1000,-4,-4,-4,-4, 2);
    }
}
