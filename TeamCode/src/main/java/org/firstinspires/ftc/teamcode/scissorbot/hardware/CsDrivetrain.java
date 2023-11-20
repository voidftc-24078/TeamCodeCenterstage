package org.firstinspires.ftc.teamcode.scissorbot.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
//import com.qualcomm.robotcore.util.Range;

//import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CsDrivetrain {
    // create drivetrain speed variables and initialize them to null
    public DcMotorEx fl = null;
    public DcMotorEx fr = null;
    public DcMotorEx bl = null;
    public DcMotorEx br = null;

    private CsHardware hardware;

    private Telemetry.Item motorPowerTelemetry = null;
    private Telemetry.Item turningTelemetry = null;
    private Telemetry.Item driveInputTelemetry = null;

    private static final double VELOCITY_MULTIPLIER = 2400;

    public CsDrivetrain(CsHardware hw) { hardware = hw; }

    public void init() {
        // get hardware mapping for each motor
        fl = hardware.hwMap.get(DcMotorEx.class, "fl");
        fr = hardware.hwMap.get(DcMotorEx.class, "fr");
        bl = hardware.hwMap.get(DcMotorEx.class, "bl");
        br = hardware.hwMap.get(DcMotorEx.class, "br");

        // make sure that the front-left motor is running in reverse,
        // as that's what makes it work.
        fl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.FORWARD);

        // by default, the robot will run using encoders.
        // this can be disabled with the "disableEncoders" class
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // default settings for motor zero power
        // the motors will brake when speed is set to 0.
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //fl.setVelocityPIDFCoefficients(10, 3, 0, 15);
        //fr.setVelocityPIDFCoefficients(10, 3, 0, 15);
        //bl.setVelocityPIDFCoefficients(10, 3, 0, 15);
        //br.setVelocityPIDFCoefficients(10, 3, 0, 15);

        //fl.setPositionPIDFCoefficients(10);
        //fr.setPositionPIDFCoefficients(10);
        //bl.setPositionPIDFCoefficients(10);
        //br.setPositionPIDFCoefficients(10);

        if (!hardware.autonomous) {
            driveInputTelemetry = hardware.telemetry.addData("Drive inputs (F/T/S)", "Unknown");
            motorPowerTelemetry = hardware.telemetry.addData("Set wheel vl. (FL/FR/BL/BR)", "Unknown");
            motorPowerTelemetry.setRetained(true);
            driveInputTelemetry.setRetained(true);
        } else {
            turningTelemetry = hardware.telemetry.addData("Wheel turning", "Unknown").setRetained(true);
            turningTelemetry.setRetained(true);
        }

    }
    public void drive(double forward, double turn, double side) {
        driveInputTelemetry.setValue("%.2f %.2f %.2f", forward, turn, side);
        double frontLeftPower  = forward + side + turn;
        double frontRightPower = forward - side - turn;
        double backLeftPower   = forward - side + turn;
        double backRightPower  = forward + side - turn;

        double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower  /= max;
            frontRightPower /= max;
            backLeftPower   /= max;
            backRightPower  /= max;
        }

        setDriveVelocity(frontLeftPower*VELOCITY_MULTIPLIER ,
                frontRightPower *VELOCITY_MULTIPLIER,
                backLeftPower *VELOCITY_MULTIPLIER,
                backRightPower *VELOCITY_MULTIPLIER);
    }

    public void setDriveVelocity(double frontLeftVelocity, double frontRightVelocity, double backLeftVelocity,
                                 double backRightVelocity) {
        motorPowerTelemetry.setValue("%.2f %.2f %.2f %.2f", frontLeftVelocity, frontRightVelocity, backLeftVelocity, backRightVelocity);
        fl.setPower(frontLeftVelocity);
        fr.setPower(frontRightVelocity);
        bl.setPower(backLeftVelocity);
        br.setPower(backRightVelocity);
    }

    public void enableEncoders() {
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void disableEncoders() {
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void autoStopTheMotors() {

        // Stop all motion;
        fl.setVelocity(0);
        fr.setVelocity(0);
        bl.setVelocity(0);
        br.setVelocity(0);

        // Turn off RUN_TO_POSITION
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    static final double     COUNTS_PER_MOTOR_REV    = 537.7 ;    // eg: 5203 GoBilda Motor
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 5.511811024 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public void encoderSet(double speed,
                           double frontLeftInches, double frontRightInches, double backLeftInches, double backRightInches) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Determine new target position, and pass to motor controller
        newFrontLeftTarget = fl.getCurrentPosition() + (int)(frontLeftInches * COUNTS_PER_INCH);
        newFrontRightTarget = fr.getCurrentPosition() + (int)(frontRightInches * COUNTS_PER_INCH);
        newBackLeftTarget = bl.getCurrentPosition() + (int)(backLeftInches * COUNTS_PER_INCH);
        newBackRightTarget = br.getCurrentPosition() + (int)(backRightInches * COUNTS_PER_INCH);
        fl.setTargetPosition(newFrontLeftTarget);
        fr.setTargetPosition(newFrontRightTarget);
        bl.setTargetPosition(newBackLeftTarget);
        br.setTargetPosition(newBackRightTarget);
        // Turn On RUN_TO_POSITION
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setVelocity(Math.abs(speed));
        fr.setVelocity(Math.abs(speed));
        bl.setVelocity(Math.abs(speed));
        br.setVelocity(Math.abs(speed));
    }

    public boolean areTheMotorsBusy() {
        return fl.isBusy() || fr.isBusy() || bl.isBusy() || br.isBusy();
    }

    // Returns FALSE to END. Returns TRUE to CONTINUE.
    public boolean autoTurnToHeading(double wanted) {
        double heading = Math.toDegrees(hardware.imu.getHeading());
        double error = wanted - heading;
        if (error < -180) {
            error = error + 360;
        } else if (error > 180) {
            error = error - 360;
        }
        double velocity = error * 40 + 80 * Math.signum(error); // P control-ish
        velocity = Range.clip(velocity, -2000, 2000);
        fl.setVelocity(-velocity);
        fr.setVelocity(velocity);
        bl.setVelocity(-velocity);
        br.setVelocity(velocity);
        if (Math.abs(error) < 0.5) {
            fl.setVelocity(0);
            fr.setVelocity(0);
            bl.setVelocity(0);
            br.setVelocity(0);
            return false;
        }
        turningTelemetry.setValue("%.2f %.2f %.2f %.2f", velocity, error, heading, fl.getVelocity());
        hardware.telemetry.update();
        return true;
    }
}
