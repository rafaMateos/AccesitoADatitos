package probandoSAX;



public class ConductorSAX {
public static void main (String[] args){
	String nombreArchivo = "assets\\discos.xml";
	PruebaSAX1 probando = new PruebaSAX1 (nombreArchivo);
	probando.andale();
}// Fin main

} // Fin conductorSAX
