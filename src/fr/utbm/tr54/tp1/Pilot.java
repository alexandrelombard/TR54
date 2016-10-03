package fr.utbm.tr54.tp1;

import lejos.robotics.RegulatedMotor;

/**
 * Pilot class for differential robot
 * @author Alexandre Lombard
 */
public class Pilot {
	
	private final RegulatedMotor leftMotor;
	private final RegulatedMotor rightMotor;
	
	private final float wheelDiameter;
	private final float wheelBase;
	
	/**
	 * Pilot constructor
	 * @param leftMotor the left motor
	 * @param rightMotor the right motor
	 * @param wheelDiameter the wheel diameter
	 * @param wheelBase the wheel base
	 */
	public Pilot(RegulatedMotor leftMotor, RegulatedMotor rightMotor, float wheelDiameter, float wheelBase) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.wheelDiameter = wheelDiameter;
		this.wheelBase = wheelBase;
	}
	
	/**
	 * Makes the robot goes forward in straight line
	 */
	public void forward() {
		// Synchronize left and right motors
		this.leftMotor.synchronizeWith(new RegulatedMotor[] { this.rightMotor });
	
		// All motors method called between startSynchronization and endSynchronization will be called
		// simultaneously when endSynchronization is called
		this.leftMotor.startSynchronization();
		
		this.leftMotor.forward();
		this.rightMotor.forward();
		
		this.leftMotor.endSynchronization();
	}
	
	/**
	 * Stops the robot
	 */
	public void stop() {
		// When immediate return is not set (or set to false), the instruction waits for effective stop of the motor
		// When immediate return is set to true, the instruction is not blocking (i.e. the next instruction
		// will be run without waiting for the motor to be effectively stopped)
		this.leftMotor.stop(true);
		this.rightMotor.stop();
	}
	
	/**
	 * Rotates the robot (the pivot of the rotation is the middle of the wheels)
	 * @param angle the angle of rotation in rad (trigonometric orientation)
	 */
	public void rotate(float angle) {
		final float ratio = this.wheelBase / this.wheelDiameter;
		final float radRotation = (float)(angle * ratio);
		final float degRotation = (float)(radRotation / Math.PI * 180);
		
		this.leftMotor.rotate((int)degRotation, true);
		this.rightMotor.rotate((int)-degRotation);
	}

	/**
	 * Sets the speed of the robot
	 * @param speed the speed (from 0 for stop, to 100 for max speed)
	 */
	public void setSpeed(float speed) {
		this.leftMotor.setSpeed((int)(speed / 100f * this.leftMotor.getMaxSpeed()));
		this.rightMotor.setSpeed((int)(speed / 100f * this.rightMotor.getMaxSpeed()));
	}
	
}
