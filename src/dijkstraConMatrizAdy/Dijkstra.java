package dijkstraConMatrizAdy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Dijkstra {
	private int nodoInicial;
	private int cantNodos;
	private int cantAristas;
	private List<Arista> aristas = new ArrayList<Arista>();
	private Integer[] solucionAlgoritmo;
	private String path;
	private Integer[] distancias;
	private Integer[] predecesores;
	private Grafo grafo;

	/**
	 * Esta solucion es con implementacion de una matriz de adyacencia y para grafos
	 * dirigidos. Para grafos no dirigidos hay que descomentar una linea de codigo.
	 * En la clase app creamos un objeto de la clase Dijkstra y le ingresamos el
	 * path del archivo de entrada. El archivo de entrada esta compuesto de la
	 * siguiente manera: 
	 * -Una fila que repretenta a nodoInicial, cantidadNodos y cantAristas respectivamente.
	 * -N filas que representan a las aristas con nodoDesde nodoHasta costo(N=cantAristas).		
	 * 
	 */
	public Dijkstra(String path) {
		this.path = path;
		cargarDatos();
		grafo = new Grafo(aristas, cantNodos, nodoInicial);
		solucionAlgoritmo = algoritmo(nodoInicial);
		guardarResultados();
	}

	public Integer[] algoritmo(int nodoInicial) {
		int nodoActual, distanciaAristaActual;
		Integer proximoNodo;
		this.nodoInicial = nodoInicial;
		List<Integer> adyacentesNodoActual;
		Set<Integer> nodosNoVisitados = new HashSet<Integer>();
		distancias = new Integer[this.cantNodos];
		predecesores = new Integer[this.cantNodos];
		distancias[nodoInicial] = 0;
		for (int i = 0; i < this.cantNodos; i++) {
			if (i != nodoInicial)
				nodosNoVisitados.add(i);
		}
		nodoActual = this.nodoInicial;
		while (!nodosNoVisitados.isEmpty()) {
			adyacentesNodoActual = getAdyacentes(nodoActual);
			for (Integer adyacente : adyacentesNodoActual) {
				distanciaAristaActual = grafo.getCosto(nodoActual, adyacente) + distancias[nodoActual];
				if (distancias[adyacente] == null || distancias[adyacente] > distanciaAristaActual) {
					distancias[adyacente] = distanciaAristaActual;
					predecesores[adyacente] = nodoActual;
				}
			}
			nodosNoVisitados.remove(nodoActual);
			proximoNodo = null;
			for (Integer noVisitado : nodosNoVisitados) {
				if (proximoNodo == null || distancias[proximoNodo] == null
						|| distancias[noVisitado] != null && distancias[proximoNodo] > distancias[noVisitado])
					proximoNodo = noVisitado;
			}
			if (proximoNodo == null)
				return distancias;
			nodoActual = proximoNodo;
		}
		return distancias;
	}

	public List<Integer> getAdyacentes(int nodoActual) {
		List<Integer> adyacentes = new ArrayList<Integer>();
		for (int i = 0; i < this.cantNodos; i++) {
			if (grafo.getCosto(nodoActual, i) != null)
				adyacentes.add(i);
		}
		return adyacentes;
	}

	public List<Integer> getCamino(int nodoHasta) {
		int i = nodoHasta;
		List<Integer> camino = new ArrayList<Integer>();
		camino.add(i);
		while (i != nodoInicial) {
			i = predecesores[i];
			camino.add(i);
		}
		return camino;
	}
	
	private void cargarDatos() {
		try {
			Scanner sc = new Scanner(new File(path + ".in"));
			Arista a;
			this.nodoInicial = sc.nextInt() - 1;
			this.cantNodos = sc.nextInt();
			this.cantAristas = sc.nextInt();
			for (int i = 0; i < this.cantAristas; i++) {
				a = new Arista(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
				aristas.add(a);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void guardarResultados() {
		try {
			PrintWriter pw = new PrintWriter(new File(path + ".out"));
			pw.println("Vector distancias minimas");
			for (int nodo : solucionAlgoritmo) {
				pw.print(nodo + " ");
			}
			System.out.println("Archivo de salida generado con exito!");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
