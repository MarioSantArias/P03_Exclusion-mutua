package p03.c01.variasEntradas;

public class ParqueVariasEntradas {

	private static ParqueVariasEntradas instance = null;
	private final int CAPACIDADPUERTA = 5;
	private int capacidadParque;
	private int visitantes[];
	private int visitantesTotales;
	private long inicio, actual;

	public ParqueVariasEntradas(int numeroPuertas) {
		capacidadParque = numeroPuertas*CAPACIDADPUERTA;
		visitantes = new int[numeroPuertas];
		for(int i = 0; i < visitantes.length; i++){
			visitantes[i] = 0;
		}
		visitantesTotales = 0;
		inicio = System.currentTimeMillis();
	}

	public synchronized void entrar(int puerta) {
		actual = System.currentTimeMillis();
		visitantes[puerta]++;
		visitantesTotales++;
		
		System.out.println("Entrada al parque por la puerta " + puerta);
		for(int i = 0; i < visitantes.length; i++){
			System.out.println("-->Personas por la puerta " + i + ": " + visitantes[i]);
		}
		System.out.println("-->Personas en el parque " + visitantesTotales + " tiempo medio de estancia: " + ((actual - inicio)/1000.0));
		checkInvariantes();
	}

	private void checkInvariantes() {
		for(int i = 0; i < visitantes.length; i++){
			assert visitantes[i] <= CAPACIDADPUERTA : ("Sobrepasado limite de puerta " + i);
		}
		assert visitantesTotales <= capacidadParque : "Sobrepasado el limite del parque";

	}


	public static ParqueVariasEntradas getInstance(int numeroPuertas) {
		if (instance == null) {
			instance = new ParqueVariasEntradas(numeroPuertas);
		}
		return instance;
	}
	
	public int getCapacidadPuerta(){
		return this.CAPACIDADPUERTA;
	}
	
	public static void main(String[] args){
		int numPuertas = 15;
		ParqueVariasEntradas parque = getInstance(numPuertas);
		Thread[] puertas = new Thread[numPuertas];
		int numeroPuerta = 0;
		
		for(int i = 0; i < numPuertas; i++){
			puertas[i] = new Thread(new Puerta(parque, numeroPuerta, parque.getCapacidadPuerta()));
			puertas[i].start();
			numeroPuerta++;
		}	
	}
}
