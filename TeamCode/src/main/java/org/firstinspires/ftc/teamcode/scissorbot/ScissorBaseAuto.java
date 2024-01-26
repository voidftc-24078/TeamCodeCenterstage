package org.firstinspires.ftc.teamcode.scissorbot;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.scissorbot.hardware.CsHardware;
import org.firstinspires.ftc.teamcode.scissorbot.pipelines.CsOpenCVPipeline;

@Disabled
@Autonomous(name="DO NOT USE!!! AUTO BASE LIBRARY !!!!")
public class ScissorBaseAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    CsHardware robot = new CsHardware(this);
    ElapsedTime time = new ElapsedTime();

    Telemetry.Item status;

    private Telemetry.Item autoEncoderLog = null;

    public static final double DRIVE_SPEED = 1900;

    public static final double encoderResolution = 1425.1;

    public int scissorDirection = 0;

    public double armDoEncode = 0;

    public double scissorDoEncode = 0;

    @Override
    public void runOpMode() {
        telemetry.addData("<h2>BaseAuto</h2>", "initializing");
        telemetry.addLine("resetting time...");
        robot.autonomous = true;
        time.reset();
        telemetry.addLine("initializing robot...");
        robot.init();
        //telemetry.addLine("waiting for opencv...");
        //while (robot.zone==0) {}
        telemetry.addLine("READY");
        //status = telemetry.addData("<h2>Pipeline</h2>", "initializing");
        telemetry.update();
        waitForStart();
        time.reset();
        telemetry.addData("ZONE", robot.zone);
        robot.openCVPipeline.enableNullPipeline();
        telemetry.update();
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
                //autoEncoderLog = telemetry.addData("bl velocity", robot.drivetrain.bl.getVelocity());
                //autoEncoderLog = telemetry.addData("br velocity", robot.drivetrain.br.getVelocity());
                //autoEncoderLog = telemetry.addData("fl velocity", robot.drivetrain.fl.getVelocity());
                //autoEncoderLog = telemetry.addData("fr velocity", robot.drivetrain.fr.getVelocity());
                //autoEncoderLog = telemetry.addData("bl position", robot.drivetrain.bl.getCurrentPosition());
                //autoEncoderLog = telemetry.addData("br position", robot.drivetrain.br.getCurrentPosition());
                //autoEncoderLog = telemetry.addData("fl position", robot.drivetrain.fl.getCurrentPosition());
                //autoEncoderLog = telemetry.addData("fr position", robot.drivetrain.fr.getCurrentPosition());
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

    public void openClaw() {
        robot.claw.claw.setPosition(robot.claw.clawOpenPosition);
    }

    public void closeClaw() {
        robot.claw.claw.setPosition(robot.claw.clawClosedPosition);
    }

    public void setWristPosition(double position) {
        robot.wrist.wristPosition=position;
        robot.wrist.wrist.setPosition(position);
    }

    public void setIntakeSpeed(double speed) {
        robot.intake.intake.setPower(speed);
    }

    public void goUp() {
        scissorDirection = 2;
        closeClaw();
        scissorStage1();
        waitScissor();
        scissorStage2();
    }

    public void goDown () {
        scissorDirection = 3;
        closeClaw();
        scissorStage4();
        waitScissor();
        scissorDown();
        //waitScissor();
        while (robot.arm.arm.isBusy()) {}
        sleep(6000);
        robot.scissor.scissorLeft.setPower(0);
        robot.scissor.scissorRight.setPower(0);
        sleep(800);
        //robot.arm.arm.setPower(0);
        openClaw();
        armDoEncode = 0;
        scissorDoEncode = 0;
        scissorDirection = 0;
        sleep(800);
        robot.arm.arm.setPower(0);
    }

    public void waitScissor () {
        while (robot.scissor.scissorLeft.isBusy() && robot.scissor.scissorRight.isBusy() && robot.arm.arm.isBusy()) {} // danger: this IS a while loop...
    }

    public void scissorStage1 () {
        robot.arm.arm.setPower(0);

        setWristPosition(robot.wrist.startPositionWrist);

        robot.scissor.scissorLeft.setTargetPosition((int) (0.5 * encoderResolution));
        robot.scissor.scissorRight.setTargetPosition((int) (0.5 * encoderResolution));

        robot.scissor.scissorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.scissor.scissorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.scissor.scissorLeft.setPower(0.3);
        robot.scissor.scissorRight.setPower(0.3);

        robot.arm.arm.setTargetPosition((int) (0.028 * encoderResolution));

        robot.arm.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.arm.arm.setPower(0.2);

        armDoEncode = 2;
        scissorDoEncode = 2;
    }

    public void scissorStage2 () {
        setWristPosition(0.2);

        robot.scissor.scissorLeft.setTargetPosition((int) (0.68 * encoderResolution));
        robot.scissor.scissorRight.setTargetPosition((int) (0.68 * encoderResolution));

        robot.scissor.scissorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.scissor.scissorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.scissor.scissorLeft.setPower(0.4);
        robot.scissor.scissorRight.setPower(0.4);

        robot.arm.arm.setTargetPosition((int) (-0.2 * encoderResolution));

        robot.arm.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.arm.arm.setPower(0.8);

        armDoEncode = 3;
        scissorDoEncode = 3;
    }

    public void scissorStage4 () {
        robot.arm.arm.setPower(0);

        setWristPosition(robot.wrist.startPositionWrist);

        robot.scissor.scissorLeft.setTargetPosition((int) (0.6 * encoderResolution));
        robot.scissor.scissorRight.setTargetPosition((int) (0.6 * encoderResolution));

        robot.scissor.scissorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.scissor.scissorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.scissor.scissorLeft.setPower(0.3);
        robot.scissor.scissorRight.setPower(0.3);

        robot.arm.arm.setTargetPosition((int) (0 * encoderResolution));

        robot.arm.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.arm.arm.setPower(-0.5);

        armDoEncode = 4;
        scissorDoEncode = 4;
    }

    public void scissorDown () {
        robot.arm.arm.setPower(0);

        setWristPosition(robot.wrist.startPositionWrist);

        //robot.scissor.scissorLeft.setTargetPosition((int) (0 * encoderResolution));
        //robot.scissor.scissorRight.setTargetPosition((int) (0 * encoderResolution));

        //robot.scissor.scissorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //robot.scissor.scissorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //robot.scissor.scissorLeft.setPower(0);
        //robot.scissor.scissorRight.setPower(0);

        robot.scissor.scissorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.scissor.scissorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.scissor.scissorLeft.setPower(-0.04);
        robot.scissor.scissorRight.setPower(-0.04);

        robot.arm.arm.setTargetPosition((int) (0.001 * encoderResolution));

        robot.arm.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.arm.arm.setPower(0.3);

        armDoEncode = 5;
        scissorDoEncode = 5;
    }
}