package fr.utbm.tr54.tp1.controller;

import fr.utbm.tr54.tp1.Robot;

/**
 * Controller stopping the robot on dark colors
 * @author Alexandre Lombard
 *
 */
public class ColorStopController extends RobotController {
	
	public ColorStopController(Robot robot) {
		super(robot);
	}

	@Override
	public void run() {
		// If the color is dark, the robot stops
		final float[] color = this.robot.getColor();
		if(color[0] < 0.1 && color[1] < 0.1 && color[2] < 0.1) {
			this.robot.getPilot().stop();
		}
		// Otherwise, the robot goes forward
		else {
			this.robot.getPilot().forward();
		}
	}
}
