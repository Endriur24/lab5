package lab3;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
import java.util.Timer;
import javax.swing.JApplet;
import java.awt.event.*;
public class SpringApplet extends JApplet implements MouseMotionListener, MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	boolean przeciaganie; // wartosc logiczna przeciagania myszki
	double polx;
	double poly;
	private SimEngine simEngine;
	private SimTask simTask;
	private Timer Timer;
	private Button buttonReset;
	private TextField m,k,c,G,lo;
	public void init() {
		przeciaganie=false;
		addMouseListener(this);
		addMouseMotionListener(this);
		setSize(900,600);
		simEngine = new SimEngine(15, 5, 0.9, 150, new Vector2D(0,100), new Vector2D(0,-50), new Vector2D(0,100));
		simTask = new SimTask(0.1,this,simEngine);
		Timer = new Timer();
		Timer.scheduleAtFixedRate(simTask, 0, 10);
		// utworzenie przycisku
		buttonReset= new Button("Reset");
		buttonReset.addActionListener(this);
		add(buttonReset);
		// utworzenie obiektow
		m= new TextField("15",1);
		k= new TextField("5",1);
		c= new TextField("2",1);
		G= new TextField("9,81",1);
		lo= new TextField("150",1 );
		// dodanie elementow GUI
		add(m);
		add(k);
		add(c);
		add(G);
		add(lo);
		}
	public void mousePressed(MouseEvent e){
		polx=(int)simEngine.getPunktZawieszenia().x;
		poly=(int)simEngine.getPunktZawieszenia().y;
		if((polx>=(int)simEngine.getPunktZawieszenia().x-25 && polx<=(int)simEngine.getPunktZawieszenia().x+25)
		&& (poly>=(int)simEngine.getPunktZawieszenia().y && poly<=(int)simEngine.getPunktZawieszenia().y+50))
		{
	Timer.cancel();
	simEngine.reset();
	przeciaganie=true;
	}
e.consume();
}
public void mouseReleased(MouseEvent e){
	if(przeciaganie=true){
		simEngine.reset();
		simTask= new SimTask(0.1, this, simEngine);
		Timer= new Timer();
		Timer.scheduleAtFixedRate(simTask,0,15);
		przeciaganie=false;
	}
	e.consume();
}
public void mouseDragged(MouseEvent e){
	if(przeciaganie=true){
		double pol_x=(double)simEngine.getPunktZawieszenia().x;
		double pol_y=(double)simEngine.getPunktZawieszenia().y;
		simEngine.setPunktZawieszenia(simEngine.getPolozenie());
		repaint();
	}
	e.consume();
}
public void paint(Graphics gd) {
	gd.clearRect(0, 0, getWidth(), getHeight()); //wyczyszczenie okna
	gd.translate(900/2, 600/2);
	int xo = (int)simEngine.getPunktZawieszenia().x;
	int yo = -(int)simEngine.getPunktZawieszenia().y;
	gd.translate(xo, yo);
	gd.drawLine(0, 0, (int)simEngine.getPolozenie().x, -(int)simEngine.getPolozenie().y); //rysowanie linii - sprezyna
	gd.fillOval((int)simEngine.getPolozenie().x - 10, -(int)simEngine.getPolozenie().y, 20, 20); //rysowanie kola - mase
	// rysowanie przyciskow
	gd.setColor(Color.BLACK);
	gd.drawString("Masa",-350,-150 );
	m.setBounds(30,40,40,20);
	gd.drawString("K",-350,-85);
	k.setBounds(30,100,40,20);
	gd.drawString("C",-350,-25);
	c.setBounds(30,160,40,20);
	gd.drawString("Lo",-350,35);
	lo.setBounds(30,220,40,20);
	gd.drawString("G", -350,95);
	G.setBounds(30,280,40,20);
	buttonReset.setBounds(30,340,60,40);
}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==buttonReset) {
		Timer.cancel();
		double M = Double.parseDouble(m.getText());
		double K = Double.parseDouble(k.getText());
		double C = Double.parseDouble(c.getText());
		double g = Double.parseDouble(G.getText());
		double l = Double.parseDouble(lo.getText());
		simEngine = new SimEngine(M, K, C, l, new Vector2D(300,100), new Vector2D(300,100), new Vector2D(0,100));
		simEngine.setG((double) g);
		simTask = new SimTask(0.1, this, simEngine);
		Timer = new Timer();
		Timer.scheduleAtFixedRate(simTask, 0, 15);
	}
}
@Override
public void mouseClicked(MouseEvent e) {
}
@Override
public void mouseEntered(MouseEvent e) {
}
@Override
public void mouseExited(MouseEvent e) {
}
@Override
public void mouseMoved(MouseEvent e) {
}
}