package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class basicEncoders extends LinearOpMode{

    DcMotor frontRight, frontLeft, backRight, backLeft, arm;

    /**
     * This number will be different depending on what motor you use
     *      -We'll say for this example we are using the same exact motor for the wheels and arm,
     *      but that is normally not the case so you normally have different ticks per revolution
     *      for the arm and then the wheels
     *
     * If its a goBILDA servo you can find it as Encoder Resolution
     * If its a REV motor you can find it as Encoder Counts per Revolution
     *
     * This number is the amount of ticks that is counted as one full rotation
      */
    double ticksPerRev = 536.97;
    double wheelDiameter = 6;

    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("Front Left");
        frontRight = hardwareMap.dcMotor.get("Front Right");
        backLeft = hardwareMap.dcMotor.get("Back Left");
        backRight = hardwareMap.dcMotor.get("Back Right");
        arm = hardwareMap.dcMotor.get("Arm");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        /**
         * When it comes to the wheels you'll typically want to be able to use inches and have
         * that translated to ticks since that makes things easier, so here's how you would do that
         *
         * The example will calculate 6 inches into ticks then go forward
         */
        double inchesForward = 6; // You want the robot to go 6 inches

        double circumference = 3.14 * wheelDiameter; // Gives you the circumference of the wheels
        double rotationsNeeded = inchesForward / circumference; // This translates the inches into wheel rotations
        int target = (int) (rotationsNeeded * ticksPerRev); // This translates the wheel rotations to ticks

        frontLeft.setTargetPosition(target); // This basically tells the motors where they want to be
        frontRight.setTargetPosition(target); // they don't go there yet but it sets them up
        backLeft.setTargetPosition(target); // Think about like this tells them their purpose in life
        backRight.setTargetPosition(target); // Just don't let them know they don't have free will.

        frontLeft.setPower(1); // This will actually let them fulfill their purpose
        frontRight.setPower(1);
        backRight.setPower(1);
        backLeft.setPower(1);

        while (frontRight.isBusy() || frontLeft.isBusy() || backRight.isBusy() || backLeft.isBusy()) {
        } // Wont do anything while they have a purpose

        frontLeft.setPower(0); // Stops the motors once they have reached their goal
        frontRight.setPower(0); // You don't want them to get there and then just pass it
        backRight.setPower(0);
        backLeft.setPower(0);


    }
}
