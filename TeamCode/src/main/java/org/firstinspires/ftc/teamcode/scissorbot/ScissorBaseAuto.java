package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.scissorbot.hardware.CsHardware;

@Disabled
@Autonomous(name="DO NOT USE!!! AUTO BASE LIBRARY !!!!")
public class ScissorBaseAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    CsHardware robot = new CsHardware(this);
    ElapsedTime time = new ElapsedTime();

    @Override
    public void runOpMode() {
        time.reset();
        robot.init();
        waitForStart();
        time.reset();
    }
}
