public class Grafo {
	
	private double[][] matriz;
	public int size;
	private String[] nombreVertice;
	private Hashtable<String, Integer> posVertice;
	
	
	public Grafo(int iniCapacity) {
		this.size = 0;
		this.matriz= new double[iniCapacity][iniCapacity];
		this.nombreVertice = new String[iniCapacity];
		this.posVertice = new Hashtable<>();
	}
	
	public Grafo() {
		this(20);
	}
	
	public void agregaVertice(String nombre) {
		//validar si ya esta llena la matriz
		//crecer la capacidad en 20 espacios, podemos usar array copy para cuidar eficiencia
		if (this.size == this.nombreVertice.length) {
			double[][] matriz_nueva = new double[this.size+20][this.size+20];
			String[] nombre_nuevo = new String[this.size+20];
			System.arraycopy(this.matriz, 0, matriz_nueva, 0, this.matriz.length);
			System.arraycopy(this.nombreVertice, 0, nombre_nuevo, 0, this.nombreVertice.length);
			
			this.matriz = matriz_nueva;
			this.nombreVertice = nombre_nuevo;
		}else {
			this.nombreVertice[this.size] = nombre;
			this.posVertice.put(nombre, this.size++);
		}
		
	}
	
	public void agregaVertices(String[] nombres) {
		if (this.size + nombres.length >= this.nombreVertice.length) {
			double[][] matriz_nueva = new double[this.size+20+nombres.length][this.size+20+nombres.length];
			String[] nombre_nuevo = new String[this.size+20+nombres.length];
			System.arraycopy(this.matriz, 0, matriz_nueva, 0, this.matriz.length);
			System.arraycopy(this.nombreVertice, 0, nombre_nuevo, 0, this.nombreVertice.length);
			
			this.matriz = matriz_nueva;
			this.nombreVertice = nombre_nuevo;
			
			for (int i = 0; i < nombres.length; i++) {
				this.nombreVertice[this.size] = nombres[i];
				this.posVertice.put(nombres[i], this.size++);
			}
		}else {
			for (int i = 0; i < nombres.length; i++) {
				this.nombreVertice[this.size] = nombres[i];
				this.posVertice.put(nombres[i], this.size++);
			}
		}
	}
	
	public void ImprimeNombres() {
		for (int i = 0; i < this.nombreVertice.length; i++) {
			System.out.println(nombreVertice[i]);
		}
	}
	
	public void agregarArista(String origen, String destino, double costo) {
		//validar que exista el origen y el destino
		//arrojar un NoSuchElementExecption

		this.matriz[this.posVertice.get(origen)][this.posVertice.get(destino)] = costo; 
	}

	public void imprime(double[][] matriz) {

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j]+", ");
			}
			System.out.println();
		}
	}
	
	public int getIndex(String nombre) {
		return posVertice.get(nombre);
	}
	
	public String getNombre(int index) {
		return this.nombreVertice[index];
	}

	

	public double[] dijkstra(String origen) {

		int posActual = this.posVertice.get(origen); //posicion en la matriz del origen
		boolean[] found = new boolean[this.size]; //los caminos mas cortos encontrados
		found[posActual] = true; //marcamos la pos actual como encontrado
		double[] sw = new double[this.size]; //arreglo donde se guardan los costos

		//inicializamos el arreglo de costos, aqui vamos 
		//a tener en infinito los valores que no tienen coneccion directa con el
		//origen. Ponemos en 0 el camino del origen al mismo origen
		for (int i = 0; i < this.size; i++) {

			if(posActual == i) {
				sw[i] = 0; //origen
				
			}else if(this.matriz[posActual][i] == 0.0) {
				sw[i] = Double.MAX_VALUE; //nodos que no tienen coneccion directa
			}else {
				sw[i] = this.matriz[posActual][i]; //nodos con coneccion directa
			}
			
		}
		
		
		for (int j=0; j<sw.length; j++) {
			//encontramos el vertice a trabajar
			//primero encontramos el valor mas chico que NO haya sido encontrado
			double min = Double.MAX_VALUE;
			int index = 0;
			for (int i = 0; i < sw.length; i++) {
				if(sw[i] < min && !found[i]) {//valor mas chico y NO encontrado
					min = sw[i];
					index = i;
				}
			}
			//ya teniendo su indice lo marcamos como encontrado
			found[index] = true;
			
			//revisamos los nodos NO encontrados
			for (int i = 0; i < sw.length; i++) {
				if(!found[i] && this.matriz[index][i] != 0 && sw[index]+this.matriz[index][i] < sw[i]) {
					//si el nodo no ha sido encontrado y la suma del costo al nodo actual (index)
					// mas el camino del nodo actual al que estamos revisando es menor a lo que 
					//teniamos guardado en sw, entonces actualizamos ese valor.
					sw[i] = sw[index]+this.matriz[index][i];
				}
			}//este for se va a correr cada vez que encontremos un nuevo nodo no revisado
		}
		return sw; //nos regresa un arreglo con los valores
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grafo g1 = new Grafo();
		g1.agregaVertice("A");
		g1.agregaVertice("B");
		g1.agregaVertice("C");

		
		g1.agregarArista("A", "C", 1);
		g1.agregarArista("B", "A", 2);
		g1.agregarArista("B", "C", 3);
		g1.agregarArista("C", "B", 4);
		
		double[] corto = g1.dijkstra("A");
		
		for (int i = 0; i < corto.length; i++) {
			System.out.print(corto[i]+", ");
		}

	}
	
}
