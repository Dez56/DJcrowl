import java.util.ArrayList;
import java.util.Scanner;

public class tescon {
    public static void main(String[] args) {

        // Declaraciones
        boolean gam = true;
        Scanner sc = new Scanner(System.in);
        int hola;
        ArrayList<Char> perso;
        perso = new ArrayList<>();

        // Agregar instancias al array
        perso.add(new caballero("Sir Hugo", false));
        perso.add(new Barbaro("Bruk", true));

        // Prueba de ataque simple
        perso.get(0).atacar(perso.get(1));
        perso.get(1).atacar(perso.get(0));

        // Prueba de habilidad activa con selección
        System.out.println("Inserte comando:");
        hola = sc.nextInt();

        perso.get(0).ente(hola, perso);
    }
}
