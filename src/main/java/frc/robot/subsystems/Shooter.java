// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private final CANSparkMax shooterMotor1 = new CANSparkMax(18,MotorType.kBrushed);
  private final CANSparkMax shooterMotor2 = new CANSparkMax(17,MotorType.kBrushed);
  /** Creates a new Intake. */
  public Shooter() {
    shooterMotor2.follow(shooterMotor1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void spinShooter(double speedIn){
    shooterMotor1.set(speedIn);
  }
}
