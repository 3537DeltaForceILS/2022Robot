// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto extends SequentialCommandGroup {
  private final DriveTrain m_driveTrain;
  private final Shooter m_Shooter;
  /** Creates a new Auto. */
  public Auto(DriveTrain driveTrain, Shooter shooterIn) {
    m_driveTrain = driveTrain;
    m_Shooter = shooterIn;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new DriveSetSpeed(m_driveTrain, -.6).withTimeout(.5),
                new DriveSetSpeed(m_driveTrain, .5).withTimeout(.75),
                new DriveSetSpeed(m_driveTrain, 0).withTimeout(1),
                new RunShooter(m_Shooter).withTimeout(1),
                new StopShooter(m_Shooter).withTimeout(1),
                new DriveSetSpeed(m_driveTrain, -.5).withTimeout(2));
  }
}
