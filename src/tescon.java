import java.util.ArrayList;
import java.util.Scanner;

public class tescon {
    public static void main(String[] args) {

        //Declaraciones

        boolean gam = true;
        Scanner sc = new Scanner(System.in);
        int hola;
        ArrayList<Char> perso;
        perso = new ArrayList<>();

        //Agregar instancias al array

        perso.add(new Char("Guerrero", 7, 1, 3, 0, 3, false));
        perso.add(new Char("Enemigo", 4, 1, 5, 1, 4, true));

        //ataques

        perso.get(0).atacar(perso.get(1));
        perso.get(1).atacar(perso.get(0));

        //Prueba de metodos dinamicos

        System.out.println("inserte comando");
        hola = sc.nextInt();

        perso.get(0).ente(hola, perso);
    }
}
