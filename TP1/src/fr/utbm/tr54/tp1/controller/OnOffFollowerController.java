package fr.utbm.tr54.tp1.controller;

import fr.utbm.tr54.tp1.Robot;

/**
 * Controller for the "all-or-nothing" policy
 * @author Alexandre Lombard
 *
 */
public class OnOffFollowerController extends RobotController {
	
	public OnOffFollowerController(Robot robot) {
		super(robot);
	}

	@Override
	public void run() {
		// If the distance is below 0.2m, the robot stop
		if(this.robot.getDistance() < 0.2) {
			this.robot.getPilot().stop();
		}
		// Otherwise, the robot goes forward
		else {
			this.robot.getPilot().forward();
		}
	}
}
