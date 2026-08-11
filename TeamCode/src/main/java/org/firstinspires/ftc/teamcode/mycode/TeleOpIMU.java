package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "IMU + Accel Basic")
public class TeleOpIMU extends LinearOpMode {

  private IMU imu;
  private DcMotorEx leftBackMotor;
  private DcMotorEx rightBackMotor;

  @Override
  public void runOpMode() {

    imu = hardwareMap.get(IMU.class, "imu");

    imu.initialize(new IMU.Parameters(
        new com.qualcomm.hardware.rev.RevHubOrientationOnRobot(
            com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP,
            com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.FORWARD)));

    waitForStart();

    while (opModeIsActive()) {

      YawPitchRollAngles robotOrientation = imu.getRobotYawPitchRollAngles();
      AngularVelocity angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);

      telemetry.addData("Yaw", robotOrientation.getYaw(AngleUnit.DEGREES));
      telemetry.addData("Pitch", robotOrientation.getPitch(AngleUnit.DEGREES));
      telemetry.addData("Roll", robotOrientation.getRoll(AngleUnit.DEGREES));

      telemetry.addData("Angular Velocity X", angularVelocity.xRotationRate);
      telemetry.addData("Angular Velocity Y", angularVelocity.yRotationRate);
      telemetry.addData("Angular Velocity Z", angularVelocity.zRotationRate);

      telemetry.update();

      // Perform a rotation check (blocking loop - logic assumes specific conditions)
      while (robotOrientation.getYaw(AngleUnit.DEGREES) <= 90) {
        double targetWheelRpm = 500;
        double targetWheelRps = targetWheelRpm / 60;
        double targetWheelVelocityTicks = targetWheelRps * 28;
        leftBackMotor.setVelocity(targetWheelVelocityTicks);
        rightBackMotor.setVelocity(0);
      }
    }
  }
}
