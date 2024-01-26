package org.firstinspires.ftc.teamcode.scissorbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "RED_BACKSTAGE_Auto")
public class ScissorRED_BACKSTAGE_Auto extends ScissorBaseAuto {
    private static final char parkDir = 'R';

    private float apriltagOffset = 0f;

    private float apriltagSpacing = 6.25f;

    private double totalApriltagOffset = 0;

    private static final double parkDriveDistance = 16;

    @Override
    public void runOpMode() {
        // initialize robot
        super.runOpMode();
        switch (super.robot.zone) {
            case 1:
                // zone 1 (LEFT)
                apriltagOffset = apriltagSpacing;
                break;
            case 2:
                // zone 2 (CENTER)
                apriltagOffset = 0;
                break;
            case 3:
                // zone 3 (RIGHT)
                apriltagOffset = -apriltagSpacing;
                break;
        }
        closeClaw();
        encoderDrive(0500,48,0,0,48, 15);
        totalApriltagOffset = 3+apriltagOffset;
        encoderDrive(0500,totalApriltagOffset,totalApriltagOffset,totalApriltagOffset,totalApriltagOffset,15);
        turnToHeading(90);
        // now place the pixel
        goUp();
        adjustScissor(0.44, 0.2);
        setWristPosition(super.robot.wrist.wristPosition+0.16);
        encoderDrive(0500, -16,-16,-16,-16,6);
        //waitScissor();
        openClaw();
        sleep(800);
        encoderDrive(1000, 16,16,16,16,4);
        //while(true); //temp !!
        goDown();
        if (parkDir == 'L') {
            // park left
            switch (super.robot.zone) {
                case 1:
                    encoderDrive(1000, 15, -15, -15, 15,4);
                    break;
                case 2:
                    encoderDrive(1000, (15+apriltagSpacing), -(15+apriltagSpacing), -(15+apriltagSpacing), (15+apriltagSpacing), 4);
                    break;
                case 3:
                    encoderDrive(1000, (15+(2*apriltagSpacing)), -(15+2*(apriltagSpacing)), -(15+2*(apriltagSpacing)), (15+2*(apriltagSpacing)), 4);
            }
        } else {
            // park right
            switch (super.robot.zone) {
                case 1:
                    encoderDrive(1000, -15, 15, 15, -15,4);
                    break;
                case 2:
                    encoderDrive(1000, -(15+apriltagSpacing), (15+apriltagSpacing), (15+apriltagSpacing), -(15+apriltagSpacing), 4);
                    break;
                case 3:
                    encoderDrive(1000, -(15+(2*apriltagSpacing)), (15+2*(apriltagSpacing)), (15+2*(apriltagSpacing)), -(15+2*(apriltagSpacing)), 4);
            }
        }
        encoderDrive(1000, -20, -20,-20,-20, 6);

    }
}
