// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This sample program shows how to control a motor using a joystick. In the operator control part
 * of the program, the joystick is read and the value is written to the motor.
 *
 * <p>Joystick analog values range from -1 to 1 and motor controller inputs also range from -1 to 1
 * making it easy to work together.
 *
 * <p>In addition, the encoder value of an encoder connected to ports 0 and 1 is consistently sent
 * to the Dashboard.
 */
public class Robot extends TimedRobot {

  private CANSparkMax m_motorA;
  private Joystick m_joystickA;
  private CANSparkMax m_motorB;
  private Joystick m_joystickB;
  private RelativeEncoder m_encoderA;
  private RelativeEncoder m_encoderB;

  @Override
  public void robotInit() {
    m_motorA = new CANSparkMax(Constants.Channels.MotorA, MotorType.kBrushless);
    m_motorB = new CANSparkMax(Constants.Channels.MotorB, MotorType.kBrushless);
    m_joystickA = new Joystick(Constants.Channels.JoystickA);
    m_joystickB = new Joystick(Constants.Channels.JoystickB);
    m_encoderA = m_motorA.getEncoder();
    m_encoderB = m_motorB.getEncoder();

  }

  /*
   * The RobotPeriodic function is called every control packet no matter the
   * robot mode.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("EncoderA", m_encoderA.getPosition());
    SmartDashboard.putNumber("EncoderB", m_encoderB.getPosition());
    SmartDashboard.putNumber("JoystickA", getJoystickValue(m_joystickA));
    SmartDashboard.putNumber("JoystickB", getJoystickValue(m_joystickB));
  }

  @Override
  public void teleopPeriodic() {
    m_motorA.set(getJoystickValue(m_joystickA));
    m_motorB.set(getJoystickValue(m_joystickB));
  }

  public double getJoystickValue(Joystick joystick) {
    if(Math.abs(joystick.getY()) > Constants.Parameters.JoystickDeadband) {
      return joystick.getY() * Constants.Parameters.JoystickMultiplier;
    } else {
      return 0.0;
    }
  }
}
