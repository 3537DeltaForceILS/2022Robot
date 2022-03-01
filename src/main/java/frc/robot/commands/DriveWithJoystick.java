// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoystick extends CommandBase {
  private final DriveTrain m_driveTrain;
  private final Joystick m_joystick;
  //priavte final XboxController m_xboxController
  /** Creates a new DriveWithJoystick. */

  public DriveWithJoystick(DriveTrain driveTrain, Joystick joystick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_joystick = joystick;
    addRequirements(driveTrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double multiplier = (m_joystick.getThrottle()*-.25)+.75;
    SmartDashboard.putNumber("Speed", -m_joystick.getY()*1);
    m_driveTrain.Drive(-m_joystick.getY(), m_joystick.getZ()*1);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
