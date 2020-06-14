package dijkstraConMatrizAdy;
import java.util.List;

public class Grafo {
	private Integer[][] matriz;
	private Integer[] predecesores;
	private int cantNodos;
	private int nodoInicial;
	
	public Grafo(List<Arista> aristas, int cantidadNodos, int nodoInicial) {
		this.cantNodos = cantidadNodos;
		this.nodoInicial = nodoInicial;
		matriz = new Integer[this.cantNodos][this.cantNodos];
		for(Arista a:aristas) {
			matriz[a.getDesde()][a.getHasta()] = a.getPeso();
			//Si fuera un grafo no dirigido hay que descomentarlo
			//matriz[a.getHasta()][a.getDesde()] = a.getPeso();
		}	
	}
	
	public Integer getCosto(int i, int j) {
		return matriz[i][j];
	}	
}
