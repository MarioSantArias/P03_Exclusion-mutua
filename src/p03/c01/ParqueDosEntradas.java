package p03.c01;

public class ParqueDosEntradas {

	private static ParqueDosEntradas instance = null;
	private final int CAPACIDADPARQUE = 40;
	private final int CAPACIDADPUERTA = 20;
	private int contadorA;
	private int contadorB;
	private long inicio, actual;

	public ParqueDosEntradas() {
		this.contadorA = 0;
		this.contadorB = 0;
		inicio = System.currentTimeMillis();
	}

	public synchronized void entrar(String puerta) {
		actual = System.currentTimeMillis();
		if (puerta.equals("A")) {
			contadorA++;
			System.out.println("Entrada al parque por la puerta A");
		} else if (puerta.equals("B")) {
			contadorB++;
			System.out.println("Entrada al parque por la puerta B");
		} 
		System.out.println("-->Personas en el parque " + (contadorA + contadorB) + " tiempo medio de estancia: " + ((actual - inicio)/1000.0));
		System.out.println("--->Por puerta A " + contadorA);
		System.out.println("--->Por puerta B " + contadorB);
		checkInvariantes();
	}

	private void checkInvariantes() {
		assert contadorA <= CAPACIDADPUERTA : "Sobrepasado limite de puerta A";
		assert contadorB <= CAPACIDADPUERTA : "Sobrepasado limite de puerta B";
		assert (contadorA + contadorB) <= CAPACIDADPARQUE : "Sobrepasado limite del parque";

	}

	public static ParqueDosEntradas getInstance() {
		if (instance == null) {
			instance = new ParqueDosEntradas();
		}
		return instance;
	}
	
	public int getCapacidadPuerta(){
		return this.CAPACIDADPUERTA;
	}
	
	public static void main(String[] args){
		ParqueDosEntradas parque = getInstance();
		Thread t1 = new Thread(new Puerta(parque, "A", parque.getCapacidadPuerta()));
		Thread t2 = new Thread(new Puerta(parque, "B", parque.getCapacidadPuerta()));
		
		t1.start();
		t2.start();
		
		
	}
}
