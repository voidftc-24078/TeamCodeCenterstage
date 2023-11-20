package org.firstinspires.ftc.teamcode.scissorbot;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.scissorbot.hardware.CsHardware;

@Disabled
@Autonomous(name="DO NOT USE!!! AUTO BASE LIBRARY !!!!")
public class ScissorBaseAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    CsHardware robot = new CsHardware(this);
    ElapsedTime time = new ElapsedTime();

    Telemetry.Item status;

    private Telemetry.Item autoEncoderLog = null;

    public static final double DRIVE_SPEED = 1900;

    @Override
    public void runOpMode() {
        status = telemetry.addData("<h2>BaseAuto</h2>", "initializing");
        telemetry.log().add("resetting time...");
        robot.autonomous = true;
        time.reset();
        telemetry.log().add("initializing robot...");
        robot.init();
        telemetry.log().add("READY");
        waitForStart();
        time.reset();
    }
    public void driveBasic(float speed) {
        robot.drivetrain.fl.setPower(speed);
        robot.drivetrain.fr.setPower(speed);
        robot.drivetrain.bl.setPower(speed);
        robot.drivetrain.br.setPower(speed);
    }

    public void turnToHeading(double wanted) {
        while (opModeIsActive() && robot.drivetrain.autoTurnToHeading(wanted));
        sleep(400);
    }

    public void encoderDrive(double speed, double frontLeftInches, double frontRightInches, double backLeftInches, double backRightInches, double timeoutSeconds) {
        if (opModeIsActive()) {
            robot.drivetrain.encoderSet(speed, frontLeftInches, frontRightInches, backLeftInches, backRightInches);
            runtime.reset();
            log("started drive!");
            sleep(50);
            robot.drivetrain.areTheMotorsBusy(); // I DON'T KNOW WHY BUT THIS FIXES EVERYTHING
            while ( (opModeIsActive() &&
                    robot.drivetrain.areTheMotorsBusy())
                    && (runtime.seconds() < timeoutSeconds)

            ){
                sleep(20); // does this need to be here?
                //periodic();
                //autoEncoderLog = robot.telemetry.addData("bl velocity", robot.drivetrain.bl.getVelocity());
                //autoEncoderLog = robot.telemetry.addData("br velocity", robot.drivetrain.br.getVelocity());
                //autoEncoderLog = robot.telemetry.addData("fl velocity", robot.drivetrain.fl.getVelocity());
                //autoEncoderLog = robot.telemetry.addData("fr velocity", robot.drivetrain.fr.getVelocity());
                //autoEncoderLog = robot.telemetry.addData("bl position", robot.drivetrain.bl.getCurrentPosition());
                //autoEncoderLog = robot.telemetry.addData("br position", robot.drivetrain.br.getCurrentPosition());
                //autoEncoderLog = robot.telemetry.addData("fl position", robot.drivetrain.fl.getCurrentPosition());
                //autoEncoderLog = robot.telemetry.addData("fr position", robot.drivetrain.fr.getCurrentPosition());
                //telemetry.update();
            }
            log("finished drive!");
            //log(opModeIsActive() + " " + robot.drivetrain.areTheMotorsBusy() + " " + (runtime.seconds() < timeoutSeconds));
            robot.drivetrain.autoStopTheMotors();
            sleep(300);
        }
    }
    @SuppressLint("DefaultLocale")
    public void log(String str) {
        str = String.format("[%.2f s]:", time.seconds()) + str;
        System.out.println("!!!!! AUTO LOG: !!!!!: " + str);
        robot.log.add(str);
    }

    //public void encoderDrive(double frontLeftInches, double frontRightInches, double backLeftInches, double backRightInches, double timeoutSeconds) {
    //    encoderDrive(DRIVE_SPEED, frontLeftInches, frontRightInches, backLeftInches, backRightInches, timeoutSeconds);
    //}

}