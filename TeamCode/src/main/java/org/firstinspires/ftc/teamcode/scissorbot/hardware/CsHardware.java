package org.firstinspires.ftc.teamcode.scissorbot.hardware;

import com.qualcomm.hardware.lynx.LynxModule;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.scissorbot.pipelines.CsOpenCVPipeline;

import java.util.List;

public class CsHardware {
    OpMode opMode;
    public Telemetry telemetry;
    public HardwareMap hwMap;

    public int zone = 0;

    public CsDrivetrain drivetrain = new CsDrivetrain(this);
    public CsWrist wrist = new CsWrist(this);
    public CsImu imu = new CsImu(this);
    public CsOpenCVPipeline openCVPipeline = new CsOpenCVPipeline(this);
    public CsScissor scissor = new CsScissor(this);
    public CsClaw claw = new CsClaw(this);
    public CsIntake intake = new CsIntake(this);

    public CsArm arm = new CsArm(this);

    public CsHardware(OpMode opmode) {
        opMode = opmode;
    }

    public boolean autonomous = false;
    public Telemetry.Log log;

    public void init() {
        telemetry = opMode.telemetry;
        telemetry.setMsTransmissionInterval(100);
        log = telemetry.log();
        hwMap = opMode.hardwareMap;
        // show all telemetry in html (good looks)
        List<LynxModule> allHubs = hwMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.HTML);
        telemetry.setAutoClear(true);
        telemetry.addLine("<h3>Hardware</h3>");
        telemetry.addLine("init drivetrain");
        drivetrain.init();
        telemetry.addLine("init wrist");
        wrist.init();
        telemetry.addLine("init imu");
        imu.init();
        if (autonomous) {
            telemetry.addLine("init webcam/pipeline");
            openCVPipeline.init();
        }
        telemetry.addLine("init arm");
        arm.init();
        telemetry.addLine("init scissor");
        scissor.init();
        telemetry.addLine("init claw");
        claw.init();
        telemetry.addLine("init intake");
        intake.init();
        //telemetry.addData("ZONE = ", zone);
        telemetry.update();
    }

}
