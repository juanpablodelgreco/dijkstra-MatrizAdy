package dijkstraConMatrizAdy;

public class Arista {
	private int desde;
	private int hasta;
	private int peso;
	
	public Arista(int desde, int hasta, int peso) {
		this.desde = desde;
		this.hasta = hasta;
		this.peso = peso;
	}

	public int getDesde() {
		return desde;
	}

	public int getHasta() {
		return hasta;
	}

	public int getPeso() {
		return peso;
	}
	
	
}
