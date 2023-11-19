package org.firstinspires.ftc.teamcode.scissorbot.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * This file contains a class to interface with the IMU.
 */

public class CsImu {
    private CsHardware hardware;
    private Telemetry.Item headingTelemetry = null;
    private IMU imu = null;

    double heading = 0;

    public CsImu(CsHardware hw) {
        hardware = hw;
    }

    public void init()    {
        //headingTelemetry = hardware.telemetry.addData("LAST IMU Heading (Deg)", 0);
        imu = hardware.hwMap.get(IMU.class, "imu");
        // TODO: check if these are actually correct
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        resetHeading();
    }

    public double getHeading() {
        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        headingTelemetry.setValue(Math.toDegrees(heading));
        return heading;
    }

    public void resetHeading() {
        imu.resetYaw();
    }
}