package org.firstinspires.ftc.teamcode.scissorbot.pipelines;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.scissorbot.hardware.CsHardware;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;
import org.openftc.easyopencv.PipelineRecordingParameters;

public class CsOpenCVPipeline {
    OpenCvWebcam webcam = null;
    private Telemetry.Item pipelineTelemetry = null;
    private CsHardware hardware;
    public CsOpenCVPipeline(CsHardware hw) { hardware = hw; }
    public void init() {
        pipelineTelemetry = (Telemetry.Item) hardware.telemetry.addLine("initializing 320x240 webcam viewport...");
        WebcamName webcamName;
        webcamName = hardwareMap.get(WebcamName.class, "camera");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        pipelineTelemetry = (Telemetry.Item) hardware.telemetry.addLine("starting pipeline...");
        webcam.setPipeline(new centerstagePipeline());

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
          public void onOpened() {
            webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            pipelineTelemetry = (Telemetry.Item) hardware.telemetry.addLine("done initializing camera!");
          }
          public void onError(int errorCode) {
          }
        });
    }

    class centerstagePipeline extends OpenCvPipeline {
        //public centerstagePipeline(Telemetry.Item pipelineTelemetry) {Telemetry.Item pipelineTelemetry = pipelineTelemetry}
        Mat mat = new Mat();
        final Rect LEFT = new Rect(
                new Point(0,240),
                new Point(80,160));
        final Rect RIGHT = new Rect(
                new Point(320,240),
                new Point(240,160));
        final Rect CENTER = new Rect(
                new Point(80,240),
                new Point(240,160));
        public Mat processFrame(Mat input) {
            Scalar lowHSV = new Scalar(0,200,0);

            Scalar highHSV = new Scalar(255,255,255);

            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

            Core.inRange(mat,lowHSV,highHSV,mat);

            Mat left = mat.submat(LEFT);
            Mat right = mat.submat(RIGHT);
            Mat center = mat.submat(CENTER);

            double leftValue = Core.sumElems(left).val[0];
            double rightValue = Core.sumElems(right).val[0];
            double centerValue = Core.sumElems(center).val[0];

            left.release();
            right.release();
            center.release();

            boolean leftCertainty = (boolean) (leftValue>centerValue&&leftValue>rightValue);
            boolean centerCertainty = (boolean) (centerValue>leftValue&&centerValue>rightValue);
            boolean rightCertainty = (boolean) (rightValue>leftValue&&rightValue>centerValue);

            pipelineTelemetry = hardware.telemetry.addData("left certainty", (boolean) leftCertainty);
            pipelineTelemetry = hardware.telemetry.addData("center certainty ", (boolean) centerCertainty);
            pipelineTelemetry = hardware.telemetry.addData("right certainty ", (boolean) rightCertainty);

            if (leftCertainty == true && centerCertainty == false && rightCertainty == false) {
                hardware.zone = 1;
            } else if (leftCertainty == false && centerCertainty == true && rightCertainty == false) {
                hardware.zone = 2;
            } if (leftCertainty == false && centerCertainty == false && rightCertainty == true) {
                hardware.zone = 3;
            }

            pipelineTelemetry = hardware.telemetry.addData("ZONE = ", hardware.zone);

            return(mat);
        }
    }
}
