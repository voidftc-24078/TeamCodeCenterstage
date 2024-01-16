package org.firstinspires.ftc.teamcode.scissorbot;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;


@Autonomous(name="OpenCV Test")
public class ScissorOpenCVTest extends OpMode {
    OpenCvWebcam webcam = null;
    
    @Override
    public void init() {
        WebcamName webcamName;
        webcamName = hardwareMap.get(WebcamName.class, "camera");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        webcam.setPipeline(new examplePipeline());

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
          public void onOpened() {
            webcam.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
          }

          public void onError(int errorCode) {
          }
        });
    }
    
    @Override
    public void loop() {
        
    }

    class examplePipeline extends OpenCvPipeline {
        Mat YCbCr = new Mat();
        Mat output = new Mat();
        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input,YCbCr,Imgproc.COLOR_RGB2YCrCb);
            output = input;
            return(output);
        }
    }
}
