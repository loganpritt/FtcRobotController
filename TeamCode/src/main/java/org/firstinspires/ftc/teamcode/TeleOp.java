package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.hardwareMaps.MecanumDrive;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "brogan")
public class TeleOp extends OpMode {

    //Declare the objects (all your motors/servos, etc.)

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor leftShoulder;
    double targetPositionMotor1 = 0;
    DcMotor rightShoulder;
    double targetPositionMotor2 = 0;

    Servo joe;


    double xMovement;
    double yMovement;
    double rotation;
    double drivePower;


    org.firstinspires.ftc.teamcode.hardwareMaps.MecanumDrive drive;

    public void init() {

        //Initialize the objects

        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeft = hardwareMap.dcMotor.get("backLeft");
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        backRight = hardwareMap.dcMotor.get("backRight");
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftShoulder = hardwareMap.dcMotor.get("leftShoulder");
        leftShoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftShoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        rightShoulder = hardwareMap.dcMotor.get("rightShoulder");
        rightShoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightShoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        joe = hardwareMap.servo.get("joe");
        joe.setPosition(0);

        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

    }

    public void loop() {
        double triggerInput = gamepad1.right_trigger;

        // Map the trigger input to target positions
        int minTargetPosition = 0;
        int maxTargetPosition = 100; // Adjust the maximum position as needed

        // Reduce the maximum position to slow down the descent
        int targetPosition = (int) (minTargetPosition + triggerInput * (maxTargetPosition - minTargetPosition));

        // Set the target positions for both motors
        leftShoulder.setTargetPosition(targetPosition);
        rightShoulder.setTargetPosition(-targetPosition); // Set the right motor with the opposite sign

        // Set the run mode to RUN_TO_POSITION for both motors
        leftShoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightShoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set a lower power for slower movement
        double power = 0.2; // Reduce the power for slower movement
        leftShoulder.setPower(power);
        rightShoulder.setPower(-power); // Set the right motor with the opposite sign

        // Handle resetting the encoders for both motors using dpad_down
        if (gamepad1.dpad_down) {
            leftShoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftShoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightShoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightShoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        // Your other code for driving and servo control
        xMovement = gamepad1.left_stick_x;
        yMovement = gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;
        drivePower = 0.6 * Math.max(Math.max(Math.abs(xMovement), Math.abs(yMovement)), Math.abs(rotation));

        drive.moveInTeleop(xMovement, yMovement, rotation, drivePower);

        if (gamepad1.a) {
            joe.setPosition(1);
        } else if (gamepad1.b) {
            joe.setPosition(0);
        }
    }
}



