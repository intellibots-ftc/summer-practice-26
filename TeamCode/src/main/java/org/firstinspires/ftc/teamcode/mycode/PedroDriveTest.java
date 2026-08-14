package org.firstinspires.ftc.teamcode.mycode;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "PedroDriveTest")
public class PedroDriveTest extends LinearOpMode {

  @Override
  public void runOpMode() {

    // Starting position of the robot on the field.
    // Change these values once you know your team's coordinate system.
    Pose startingPose = new Pose(0, 0, 0);

    follower.setStartingPose(startingPose);

    // Tell Pedro that we're manually driving.
    follower.startTeleopDrive();

    waitForStart();

    while (opModeIsActive()) {

      // Update Pedro's localization and drive system.
      follower.update();

      /*
       * Pedro receives:
       *   Y  = forward/backward
       *   X  = strafe
       *   RX = rotation
       */
      follower.setTeleOpDrive(
              -gamepad1.left_stick_y,
              -gamepad1.left_stick_x,
              -gamepad1.right_stick_x,
              true
      );

      telemetry.addData("X", follower.getPose().getX());
      telemetry.addData("Y", follower.getPose().getY());
      telemetry.addData("Heading", follower.getPose().getHeading());
      telemetry.update();
    }
  }
}