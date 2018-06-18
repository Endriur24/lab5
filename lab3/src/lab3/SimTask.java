package lab3;

import java.util.TimerTask;
public class SimTask extends TimerTask{
	private SimEngine simEngine;
	private SpringApplet springApplet;
	private double Czas;
	//konstruktor
	SimTask(double Czas, SpringApplet springApplet, SimEngine simEngine){
		this.Czas = Czas;
		this.simEngine = simEngine;
		this.springApplet = springApplet;
	}
	public void run() {
		springApplet.repaint();
		simEngine.symulacja(Czas);
	}
}