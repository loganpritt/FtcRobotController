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
    Servo joe;


    double xMovement;
    double yMovement;
    double rotation;
    double drivePower;
    double degrees = 0;

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

        joe = hardwareMap.servo.get("joe");
        joe.setPosition(degrees);

        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

    }

    public void loop() {

        xMovement = gamepad1.left_stick_x;
        yMovement = gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;
        drivePower = 0.8 * Math.max(Math.max(Math.abs(xMovement), Math.abs(yMovement)), Math.abs(rotation));

        drive.moveInTeleop(xMovement, yMovement, rotation, drivePower);

        if (gamepad1.a) {

            joe.setPosition(0.5);
        } else if (gamepad1.b) {
            joe.setPosition(degrees);
        }



    }

}