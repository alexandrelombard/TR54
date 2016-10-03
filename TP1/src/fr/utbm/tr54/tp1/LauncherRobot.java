package fr.utbm.tr54.tp1;

import fr.utbm.tr54.tp1.controller.ColorStopController;
import fr.utbm.tr54.tp1.controller.OnOffFollowerController;
import fr.utbm.tr54.tp1.controller.RobotController;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.utility.Timer;
import lejos.utility.TimerListener;

/**
 * Launcher class for robot integration tests
 * @author Alexandre Lombard
 *
 */
public class LauncherRobot {
	
	public static void main(String[] args) {
		// Initialize the robot with pilot and sensors
		final RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		final RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
		
		final Pilot pilot = new Pilot(leftMotor, rightMotor, 0.12f, 0.056f);
		
		final EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S2);
		final EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
		
		final Robot robot = new Robot(pilot, ultrasonicSensor.getDistanceMode(), colorSensor.getRGBMode());
		
		// Initialize a robot controller, the code that will control the robot's behavior
		final RobotController robotController = new ColorStopController(robot);			// Stop robot when it steps on a dark color
//		final RobotController robotController = new OnOffFollowerController(robot);		// Stop robot when it is too close from an obstacle
		
		// Schedule the robot controller to be executed every 50ms
		final Timer scheduler = new Timer(50, new TimerListener() {

			@Override
			public void timedOut() {
				robotController.run();
			}
			
		});
		
		// Start the scheduler and wait 10s
		scheduler.start();
		
		Delay.msDelay(10000);
		
		scheduler.stop();
		
		// Do not forget to close sensors
		ultrasonicSensor.close();
		colorSensor.close();
	}

}
