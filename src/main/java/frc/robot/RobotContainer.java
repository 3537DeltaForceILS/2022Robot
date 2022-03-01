// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Auto;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.RunClimber;
import frc.robot.commands.RunClimberBackwards;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunIntakeAndSteer;
import frc.robot.commands.RunLifter;
import frc.robot.commands.RunShooter;
import frc.robot.commands.StopClimber;
import frc.robot.commands.StopIntake;
import frc.robot.commands.StopLifter;
import frc.robot.commands.StopShooter;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Intake m_Intake = new Intake();
  private final Shooter m_shooter = new Shooter();
  private final Lifter m_Lifter = new Lifter();
  private final Climber m_Climber = new Climber();
  private final Joystick m_joystick = new Joystick(0);
  private final XboxController m_manipulator = new XboxController(1);
  
private final Auto m_autocommand = new Auto(m_driveTrain, m_shooter);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_driveTrain.setDefaultCommand(new DriveWithJoystick(m_driveTrain, m_joystick));
    m_Intake.setDefaultCommand(new StopIntake(m_Intake));
    m_shooter.setDefaultCommand(new StopShooter(m_shooter));
    m_Lifter.setDefaultCommand(new StopLifter(m_Lifter));
    m_Climber.setDefaultCommand(new StopClimber(m_Climber));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton buttonB = new JoystickButton(m_manipulator, 2);
    buttonB.whileHeld(new RunIntake(m_Intake));
    final JoystickButton buttonRB = new JoystickButton(m_manipulator, 6);
    buttonRB.whileHeld(new RunShooter(m_shooter));
    final JoystickButton buttonLB = new JoystickButton(m_manipulator, 5);
    buttonLB.whileHeld(new RunIntakeAndSteer(m_Intake, m_driveTrain, m_joystick));
    final JoystickButton buttonA = new JoystickButton(m_manipulator, 1);
    buttonA.whileHeld(new RunLifter(m_Lifter));
    final JoystickButton buttonX = new JoystickButton(m_manipulator, 3);
    buttonX.whenHeld(new RunClimber(m_Climber));
    final JoystickButton buttonY = new JoystickButton(m_manipulator, 4);
    buttonY.whenHeld(new RunClimberBackwards(m_Climber));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autocommand;
  }
  public DriveTrain getDrivetrain(){
    return m_driveTrain;
  }
}
