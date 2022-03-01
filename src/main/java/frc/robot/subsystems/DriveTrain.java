// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class DriveTrain extends SubsystemBase {
  private final CANSparkMax LeftFrontMotor = new CANSparkMax(11, CANSparkMaxLowLevel.MotorType.kBrushed); 
  private final CANSparkMax RightFrontMotor = new CANSparkMax(13, CANSparkMaxLowLevel.MotorType.kBrushed); 
  private final CANSparkMax LeftRearMotor = new CANSparkMax(12, CANSparkMaxLowLevel.MotorType.kBrushed); 
  private final CANSparkMax RightRearMotor = new CANSparkMax(14, CANSparkMaxLowLevel.MotorType.kBrushed);
  private final MotorControllerGroup RightMotors = new MotorControllerGroup(RightFrontMotor, RightRearMotor);
  private final MotorControllerGroup LeftMotors = new MotorControllerGroup(LeftFrontMotor, LeftRearMotor);
  private final DifferentialDrive chassiss = 
    new DifferentialDrive(LeftMotors,RightMotors);
  private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
 
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    RightMotors.setInverted(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Left Speed", LeftMotors.get());
    SmartDashboard.putNumber("Right Speed", RightMotors.get());
    SmartDashboard.putNumber("tx", getlimelighttx());
    // This method will be called once per scheduler run
  }
  public void Drive(double speed, double twist){
    SmartDashboard.putNumber("speedOut", speed);
    chassiss.arcadeDrive(speed, twist);
  }
  public double getlimelighttx(){
    return -table.getEntry("tx").getDouble(0);

  }

  

  public void setpipeline(int pipeline){
    NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
    pipelineEntry.setNumber(pipeline);
  }
}
