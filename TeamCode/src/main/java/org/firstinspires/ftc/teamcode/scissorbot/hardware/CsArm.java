package org.firstinspires.ftc.teamcode.scissorbot.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CsArm {
    // create arm object and initialize it.
    public DcMotorEx arm = null;

    private CsHardware hardware;
    private Telemetry.Item armTelemetry = null;
    
    public CsArm(CsHardware hw) { hardware = hw; }

    public void init() {
        arm = hardware.hwMap.get(DcMotorEx.class, "arm");
        
        arm.setDirection(DcMotor.Direction.FORWARD);
        
        // default  the runmode to RUN_USING_ENCODER for now, CsHardware can define otherwise
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setPower(0);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
    }

    public void autoStopTheMotors() {

        // Stop all motion;
        arm.setVelocity(0);

        // Turn off RUN_TO_POSITION
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
