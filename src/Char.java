import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Char {
    protected String name;  //nombre
    protected int Hp;       //Vida
    protected int MeS;      //Habilidad Melee, ahora general
    protected int Res;      //Resistencia a impactos
    protected int Arm;      //Armadura en caso de que tenga
    protected int Fue;      //Fuerza del ataque
    protected boolean tea;  //equipo 0 de aliados o 1 de enemigos

    // Constructor
    public Char(String name, int Hp, int MeS, int Res, int Arm, int Fue, boolean tea) {
        this.name = name;
        this.Hp = Hp;
        this.MeS = MeS;
        this.Res = Res;
        this.Arm = Arm;
        this.Fue = Fue;
        this.tea = tea;
    }

    public boolean ente(int rech, ArrayList<Char> perso) {
        Scanner sc = new Scanner(System.in);

        // Validación de opción
        if (rech < 1 || rech > 2) {
            System.out.println("Inválido, vuelve a intentar meter otro comando");
            return false;
        }

        switch (rech) {
            case 1:
                // Mostrar objetivos posibles
                System.out.println("Selecciona a qué personaje atacar:");
                for (int i = 0; i < perso.size(); i++) {
                    Char c = perso.get(i);
                    System.out.println("[" + i + "] " + c.name + " (Equipo " + c.tea + ", HP: " + c.Hp + ")");
                }

                int sele = sc.nextInt();

                // Validar índice
                if (sele < 0 || sele >= perso.size()) {
                    System.out.println("Índice inválido.");
                    return false;
                }

                // ¿Aliado o enemigo?
                Char objetivo = perso.get(sele);
                if (this.tea != objetivo.tea) {
                    atacar(objetivo);
                    return true;
                } else {
                    System.out.println("Ese personaje es de tu equipo. Vuelve a seleccionar...");
                    return false;
                }

            case 2:
                System.out.println("Este personaje no cuenta con habilidad pasiva");
                return false;
        }

        return false; // fallback
    }


    public void atacar(Char objetivo) {
        Random rand = new Random();
        int dad = rand.nextInt(6) + 1; // Dado de 6 caras

        // impactara?
        if (dad >= MeS) {
            System.out.println(name + " impacta a " + objetivo.name + "!");

            // tabla de datos con el otro metodo
            int dad2 = rand.nextInt(6) + 1;
            int result = calc(Fue, objetivo.Res);
            if (dad2 >= result) {
                System.out.println(name + " logra herir a " + objetivo.name + "!");

                // Aramdura?

                int covv = objetivo.armo();

                if (covv == 1){
                    return;
                }

                // Aplicar daño

                int dano = Fue - (objetivo.Res / 2);
                if (dano < 1) dano = 1; // QUE ALMENOS HAGA 1 DE DAÑO
                objetivo.Hp -= dano;
                System.out.println(objetivo.name + " recibe " + dano + " de daño! HP restante: " + objetivo.Hp);

            } else {
                System.out.println(name + " no logró herir a " + objetivo.name + ".");
            }
        } else {
            System.out.println(name + " falla el ataque contra " + objetivo.name + ".");
        }
    }

    // Método para determinar el número mínimo que debe sacarse en el dado para herir
    private int calc(int fuerza, int resistencia) {
        if (fuerza >= resistencia * 2) return 2; //83%
        if (fuerza > resistencia) return 3;      //66%
        if (fuerza == resistencia) return 4;     //50%
        if (fuerza * 2 <= resistencia) return 6; //16%
        return 5;                                //33%
    }

    protected int armo (){
        Random rand = new Random();
        if (Arm != 0) {
            int dad3 = rand.nextInt(4) + 1;  //25% de proteger
            if (dad3 == 1) {
                Arm--;
                System.out.println(name + " bloqueó el daño con su armadura! (Quedan " + Arm + " usos)");
                return 1; //si tienes suerte, ignora daño
            }
            return 0;
        }
        return 0;
    }

}
