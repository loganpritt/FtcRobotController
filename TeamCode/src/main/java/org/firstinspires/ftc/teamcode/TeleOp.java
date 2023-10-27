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
    DcMotor rightShoulder;
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

        rightShoulder = hardwareMap.dcMotor.get("rightShoulder");
        rightShoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        joe = hardwareMap.servo.get("joe");
        joe.setPosition(0);

        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

    }

    public void loop() {
         // Replace with your desired target position
        if (gamepad1.left_bumper) {
            leftShoulder.setTargetPosition(10);
            rightShoulder.setTargetPosition(10);
            leftShoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightShoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftShoulder.setPower(0.5); // Set the desired power
            rightShoulder.setPower(0.5); // Set the desired power



        }
        if (gamepad1.dpad_down) {
            // Reset the current position to zero for the leftShoulder motor
            leftShoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftShoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightShoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightShoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }




        xMovement = gamepad1.left_stick_x;
        yMovement = gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;
        drivePower = 0.8 * Math.max(Math.max(Math.abs(xMovement), Math.abs(yMovement)), Math.abs(rotation));

        drive.moveInTeleop(xMovement, yMovement, rotation, drivePower);

        if (gamepad1.a) {

            joe.setPosition(1);
        } else if (gamepad1.b) {
            joe.setPosition(0);
                }


        }



    }


