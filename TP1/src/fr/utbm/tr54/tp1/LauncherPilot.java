package fr.utbm.tr54.tp1;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * Launcher class for pilot unit tests
 * @author Alexandre Lombard
 *
 */
public class LauncherPilot {
	
	public static void main(String[] args) {
		// Initialize the pilot with motors
		final RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		final RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
		
		final Pilot pilot = new Pilot(leftMotor, rightMotor, 0.12f, 0.056f);
		
		// Unit test for forward function
		pilot.forward();
		
		Delay.msDelay(2000);
		
		// Unit test for stop function
		pilot.stop();
		
		Delay.msDelay(1000);
		
		// Unit test for rotate function
		pilot.rotate((float)(Math.PI / 2));
	}

}
