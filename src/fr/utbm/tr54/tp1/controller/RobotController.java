package fr.utbm.tr54.tp1.controller;

import fr.utbm.tr54.tp1.Robot;

public abstract class RobotController implements Runnable {
	protected Robot robot;
	
	public RobotController(Robot robot) {
		this.robot = robot;
	}
}
