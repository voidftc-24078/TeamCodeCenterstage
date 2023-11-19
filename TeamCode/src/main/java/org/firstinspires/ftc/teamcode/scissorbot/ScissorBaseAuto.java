package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.scissorbot.hardware.CsHardware;

@Disabled
@Autonomous(name="DO NOT USE!!! AUTO BASE LIBRARY !!!!")
public class ScissorBaseAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    CsHardware robot = new CsHardware(this);
    ElapsedTime time = new ElapsedTime();

    @Override
    public void runOpMode() {
        time.reset();
        robot.init();
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
            sleep(50);
            robot.drivetrain.areTheMotorsBusy(); // I DON'T KNOW WHY BUT THIS FIXES EVERYTHING
            while ( (opModeIsActive() &&
                    robot.drivetrain.areTheMotorsBusy())
                    && (runtime.seconds() < timeoutSeconds)

            ){
                sleep(20); // does this need to be here?
                //periodic();
            }
            //log(opModeIsActive() + " " + robot.drivetrain.areTheMotorsBusy() + " " + (runtime.seconds() < timeoutSeconds));
            robot.drivetrain.autoStopTheMotors();
            sleep(300);
        }
    }
}