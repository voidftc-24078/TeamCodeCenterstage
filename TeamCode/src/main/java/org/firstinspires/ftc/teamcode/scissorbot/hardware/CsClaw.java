package org.firstinspires.ftc.teamcode.scissorbot.hardware;

import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CsClaw {
    public static double clawOpenPosition = 0.79;
    public static double clawClosedPosition = 0.75;

    public ServoImplEx claw = null;

    private CsHardware hardware;
    private Telemetry.Item clawTelemetry = null;

    public CsClaw(CsHardware hw) { hardware = hw; }

    public void init() {

        claw = hardware.hwMap.get(ServoImplEx.class, "claw");

        claw.setPosition(clawOpenPosition);

    }
}
