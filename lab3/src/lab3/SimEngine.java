package lab3;

public class SimEngine {
	private double m; //masa
	private double k; //wspolczynnik sprezystosci
	private double c; //wspolczynnik tlumienia
	private double lo; //dlugosc swobodna sprezyny
	private Vector2D polozenie; //polozenie masy
	private Vector2D v; //predkosc masy
	private Vector2D punktZawieszenia; //polozenie punktu zawieszenia
	private Vector2D a; //przyspieszenie
	private Vector2D silaGrawitacji; //sila grawitacji
	private Vector2D silaSprezystosci; //sila sprezystosci
	private Vector2D silaTlumienia; //sila tlumienia
	private Vector2D silaWypadkowa; //sila wypdakowa
	private static double G = 9.81;
	SimEngine(double m,double k, double c, double lo, Vector2D punktZawieszenia, Vector2D polozenie, Vector2D v) { 
		//konstruktor z parametrami
		this.m = m;
		this.k = k;
		this.c = c;
		this.lo = lo;
		this.polozenie = polozenie.subVect(punktZawieszenia);
		this.punktZawieszenia = punktZawieszenia;
		this.v = v;
		silaGrawitacji = new Vector2D(0, -m*G);
	}
	public void reset() { //metoda resetujaca symulacje
		v = new Vector2D();
	}
	public void symulacja(double Czas) { //metoda symulujaca dzialanie sprezyny
		silaTlumienia = v.constMultVect(-c);
		silaSprezystosci = polozenie.normalize().constMultVect(-k*(polozenie.length()-lo));
		silaWypadkowa = silaGrawitacji.sumVect(silaSprezystosci).sumVect(silaTlumienia);
		a = silaWypadkowa.constMultVect(1/m);
		v = v.sumVect(a.constMultVect(Czas));
		polozenie = polozenie.sumVect(v.constMultVect(Czas));
	}
	//akcesory
	public double getM() {
		return m;
	}
	public void setM(double m) {
		this.m = m;
	}
	public double getK() {
		return k;
	}
	public void setK(double k) {
		this.k = k;
	}
	public double getC() {
		return c;
	}
	public void setG(double G) {
		SimEngine.G=9.81;
	}
	public double getG() {
		return G;
	}
	public void setC(double c) {
		this.c = c;
	}
	public double getLo() {
		return lo;
	}
	public void setLo(double lo) {
		this.lo = lo;
	}
	public Vector2D getPunktZawieszenia() {
		return punktZawieszenia;
	}
	public void setPunktZawieszenia(Vector2D punktZawieszenia) {
		this.punktZawieszenia = punktZawieszenia;
	}
	public Vector2D getPolozenie() {
		return polozenie;
	}
	public void setPolozenie(double x, double y) {
		this.polozenie = polozenie;
	}
	public Vector2D getV() {
		return v;
	}
	public void setV(Vector2D v) {
		this.v = v;
	}
}

