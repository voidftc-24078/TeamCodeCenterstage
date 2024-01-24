package org.firstinspires.ftc.teamcode.scissorbot.hardware;

import com.qualcomm.robotcore.hardware.ServoImplEx;

public class CsWrist {
    public ServoImplEx wrist = null;
    private CsHardware hardware;
    public static double startPositionWrist = 0.54;
    public double wristPosition = startPositionWrist;

    public CsWrist (CsHardware hw) { hardware = hw; }
    public void init() {
        wrist = hardware.hwMap.get(ServoImplEx.class, "wrist");
        wrist.setPosition(wristPosition);
    }
}
