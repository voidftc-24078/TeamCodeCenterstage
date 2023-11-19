package org.firstinspires.ftc.teamcode.scissorbot.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.util.Range;

//import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CsDrivetrain {
    public DcMotorEx fl = null;
    public DcMotorEx fr = null;
    public DcMotorEx bl = null;
    public DcMotorEx br = null;

    private CsHardware hardware;

    public CsDrivetrain(CsHardware hw) { hardware = hw; }

    public void init() {
        fl = hardware.hwMap.get(DcMotorEx.class, "fl");
        fr = hardware.hwMap.get(DcMotorEx.class, "fr");
        bl = hardware.hwMap.get(DcMotorEx.class, "bl");
        br = hardware.hwMap.get(DcMotorEx.class, "br");

        // make sure that the front-left motor is running in reverse,
        // as that's what makes it work.
        fl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);

        // default settings for motor zero power
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}

