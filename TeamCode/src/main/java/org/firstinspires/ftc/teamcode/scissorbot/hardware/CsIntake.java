package org.firstinspires.ftc.teamcode.scissorbot.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CsIntake {
    public DcMotorEx intake = null;

    private CsHardware hardware;
    private Telemetry.Item intakeTelemetry = null;

    public CsIntake(CsHardware hw) { hardware = hw; }

    public void init() {
        intake = hardware.hwMap.get(DcMotorEx.class, "intake");

        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //idk maybe FLOAT is better NOTE: might change to BRAKE
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        intake.setPower(0);
    }
}
