package org.firstinspires.ftc.teamcode.scissorbot;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
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


@Autonomous(name="OpenCV Test")
public class ScissorOpenCVTest extends OpMode {
    OpenCvWebcam webcam = null;
    
    @Override
    public void init() {
        WebcamName webcamName;
        webcamName = hardwareMap.get(WebcamName.class, "camera");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        webcam.setPipeline(new centerstagePipeline(telemetry));

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
          public void onOpened() {
            webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
          }

          public void onError(int errorCode) {
          }
        });
    }
    
    @Override
    public void loop() {
        
    }

    class centerstagePipeline extends OpenCvPipeline {
        Telemetry telemetry;
        public centerstagePipeline(Telemetry t) { telemetry = t; }
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
            telemetry.addData("left certainty ", (boolean) (leftValue>centerValue&&leftValue>rightValue));
            telemetry.addData("center certainty ", (boolean) (centerValue>leftValue&&centerValue>rightValue));
            telemetry.addData("right certainty ", (boolean) (rightValue>leftValue&&rightValue>centerValue));

            mat = input;
            return(mat);
        }
    }
}
