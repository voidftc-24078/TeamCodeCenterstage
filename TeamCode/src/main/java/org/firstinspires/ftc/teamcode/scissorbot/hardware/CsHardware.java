package org.firstinspires.ftc.teamcode.scissorbot.hardware;

//import com.qualcomm.hardware.lynx.LynxModule;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

public class CsHardware {
    OpMode opMode;
    Telemetry telemetry;
    HardwareMap hwMap;

    public CsDrivetrain drivetrain = new CsDrivetrain(this);

    public CsHardware(OpMode opmode) {
        opMode = opmode;
    }

    public void init() {
        telemetry = opMode.telemetry;
        telemetry.setMsTransmissionInterval(100);
        Telemetry.Log log = telemetry.log();
        hwMap = opMode.hardwareMap;

        //List<LynxModule> allHubs = hwMap.getAll(LynxModule.class);
        //for (LynxModule hub : allHubs) {
        //    hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        //}
        //telemetry.setDisplayFormat(Telemetry.DisplayFormat.HTML);
        //telemetry.setAutoClear(false);

        //telemetry.addLine("<h3>Hardware</h3>");

        // initialize drivetrain
        drivetrain.init();
    }

}