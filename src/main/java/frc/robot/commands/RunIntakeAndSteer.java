// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RunIntakeAndSteer extends PIDCommand {
  private final DriveTrain m_DriveTrain;
  private final Intake m_Intake;
  /** Creates a new RunIntakeAndSteer. */
  public RunIntakeAndSteer(Intake intakeIn, DriveTrain driveTrainIn, Joystick joyStickIn) {
    super(
        // The controller that the command will use
        new PIDController(.05, 0, 0),
        // This should return the measurement
        () -> driveTrainIn.getlimelighttx(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
          if (driveTrainIn.getlimelightty()<0 && driveTrainIn.getlimelighttx()>-25 && driveTrainIn.getlimelighttx()<25){
            driveTrainIn.Drive(-joyStickIn.getY(), output);
          }
          else{
            driveTrainIn.Drive(-joyStickIn.getY(), joyStickIn.getZ());
          }
          
          intakeIn.spinIntake(1);
        });
        m_Intake = intakeIn;
        m_DriveTrain = driveTrainIn;
        addRequirements(m_Intake, m_DriveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
