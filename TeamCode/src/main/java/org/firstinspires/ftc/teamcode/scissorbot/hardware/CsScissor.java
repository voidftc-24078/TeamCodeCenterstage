package org.firstinspires.ftc.teamcode.scissorbot.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CsScissor {
    public DcMotorEx scissorLeft = null;
    public DcMotorEx scissorRight = null;

    private CsHardware hardware;
    private Telemetry.Item scissorTelemetry = null;

    public CsScissor(CsHardware hw) { hardware = hw; }

    public void init() {
        scissorLeft = hardware.hwMap.get(DcMotorEx.class, "scissorLeft");
        scissorRight = hardware.hwMap.get(DcMotorEx.class, "scissorRight");

        scissorLeft.setDirection(DcMotor.Direction.FORWARD);
        scissorRight.setDirection(DcMotor.Direction.FORWARD);

        scissorLeft.setPower(0);
        scissorRight.setPower(0);

        scissorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        scissorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
}
