package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
  public static FollowerConstants followerConstants = new FollowerConstants()
      .mass(10);

  public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

  public static MecanumConstants driveConstants = new MecanumConstants()
      .maxPower(1)
      .xVelocity(74.960)
      .yVelocity(0)
      .rightFrontMotorName("right_front_drive")
      .rightRearMotorName("right_back_drive")
      .leftRearMotorName("left_back_drive")
      .leftFrontMotorName("left_front_drive")
      .leftFrontMotorDirection(DcMotor.Direction.REVERSE)
      .leftRearMotorDirection(DcMotor.Direction.REVERSE)
      .rightFrontMotorDirection(DcMotor.Direction.FORWARD)
      .rightRearMotorDirection(DcMotor.Direction.FORWARD);

  public static PinpointConstants localizerConstants = new PinpointConstants()
      .forwardPodY(1.1129)
      .strafePodX(-7.8274858)
      .distanceUnit(DistanceUnit.INCH)
      .hardwareMapName("odo")
      .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
      .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
      .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

  public static Follower createFollower(HardwareMap hardwareMap) {
    return new FollowerBuilder(followerConstants, hardwareMap)
        .pathConstraints(pathConstraints)
        .mecanumDrivetrain(driveConstants)
        .pinpointLocalizer(localizerConstants)
        .build();
  }
}
