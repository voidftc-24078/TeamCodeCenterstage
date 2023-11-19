package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "TestAuto")
public class ScissorAutoTest extends LinearOpMode {

    private DcMotor bl;
    private DcMotor br;
    private DcMotor fl;
    private DcMotor fr;

    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            bl.setPower(0.3);
            br.setPower(0.3);
            fl.setPower(0.3);
            fr.setPower(0.3);
            sleep(300);
            bl.setPower(0);
            br.setPower(0);
            fl.setPower(0);
            fr.setPower(0);
            while (opModeIsActive()) {
                // Put loop blocks here.
                telemetry.update();
            }
        }
    }
}
