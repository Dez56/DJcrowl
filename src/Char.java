import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Char {
    protected String name;  // nombre
    protected int Hp;       // Vida
    protected int MeS;      // Habilidad Melee (ahora general)
    protected int Res;      // Resistencia a impactos
    protected int Arm;      // Armadura
    protected int Fue;      // Fuerza del ataque
    protected boolean tea;  // Equipo (false = aliados, true = enemigos)
    protected boolean typ; // funcionamiento, ignoren

    protected int cooldown = 0;
    protected final int MAX_COOLDOWN = 2;

    // Constructor
    public Char(String name, int Hp, int MeS, int Res, int Arm, int Fue, boolean tea, boolean typ) {
        this.name = name;
        this.Hp = Hp;
        this.MeS = MeS;
        this.Res = Res;
        this.Arm = Arm;
        this.Fue = Fue;
        this.tea = tea;
        this.typ = typ;
    }

    public boolean ente(int rech, ArrayList<Char> perso) {
        if (rech < 1 || rech > 2) {
            frase(2);
            System.out.println("ese comando no existe, solo ingresa 1 o 2");
            return false;
        }

        switch (rech) {
            case 1:
                Char seleoir = chooie(perso);

                if (seleoir == null) return false;

                if (this.tea != seleoir.tea) {
                    atacar(seleoir);
                    return true;
                } else {
                    frase(1);
                    System.out.println("ese personaje es de tu equipo, selecciona de nuevo");
                    return false;
                }

            case 2:
                Char jooo = null;

                if (!this.typ) { // si es un personaje que requiere objetivo
                    jooo = chooie(perso);
                }

                if (jooo == null && !this.typ) return false;

                return aco(jooo);

        }

        return false; // fallback
    }

    public void tick() {
        if (cooldown > 0) cooldown--;
    }

    public void atacar(Char objetivo) {
        Random rand = new Random();
        int dad = rand.nextInt(6) + 1; // Dado de 6 caras

        if (dad >= MeS) {
            System.out.println(name + " impacta a " + objetivo.name + "!");

            int covv = objetivo.armo(); // Armadura?
            if (covv == 1) return;

            int dano = Fue - (objetivo.Res / 2);
            if (dano < 1) dano = 1;

            objetivo.Hp -= dano;
            System.out.println(objetivo.name + " recibe " + dano + " de daño! HP restante: " + objetivo.Hp);

            objetivo.tiend();
        } else {
            System.out.println(name + " falla el ataque contra " + objetivo.name + ".");
        }
    }

        protected int armo() {
        Random rand = new Random();
        if (Arm != 0) {
            int dad3 = rand.nextInt(4) + 1;  // 25% de bloquear
            if (dad3 == 1) {
                Arm--;
                System.out.println(name + " bloqueó el daño con su armadura! (Quedan " + Arm + " usos)");
                return 1;
            }
        }
        return 0;
    }

    // Habilidad activa
    protected boolean aco(Char asa) {
        System.out.println("Este personaje no tiene activa");
        return false;
    }

    // Selector de objetivo reutilizable
    protected Char chooie(ArrayList<Char> perso) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecciona un personaje: seleccionarObjetivo");

        for (int i = 0; i < perso.size(); i++) {
            Char c = perso.get(i);
            System.out.println("[" + i + "] " + c.name + " (Equipo " + c.tea + ", HP: " + c.Hp + ")");
        }

        int sele = sc.nextInt();

        if (sele < 0 || sele >= perso.size()) {
            System.out.println("eso no existe.");
            return null;
        }

        return perso.get(sele);
    }

    protected void frase(int sit) {

        String[] frases;

        switch (sit) {
            case 1-> //Mensaje fuego amigo
                frases = new String[]{
                        "¡Ey! ¿te pegaban de niño acaso?",
                        "Ese es de los tuyos, genio.",
                        "Nop",
                        "¿Que tienes en mente?",
                        "Tus neuronas pueden ser contadas con los dedos de un pingüino, y terminan sobrando",
                        "fuiste amamantado con redbull apenas naceiste",
                        "atencion de fuego amigo, selecciona a alguien más para atacar"
                };
            case 2-> //Comando erroneo
                frases = new String[]{
                        "¿Ocupas lentes para leer bien?",
                        "Tipico error de dedo",
                        "whoops he revisado en la libreria de alejandria y el comando que proporcionaste no aparece",
                        "¿Estas prestando atencion almenos?"
                };
            case 3-> //Barbaro
                frases = new String[]{
                        "Me parece que no le ha gustado",
                        "Y murio... pero se rehusó",
                        "oh oh",
                        "- Prepara el culo perra"
                };
            case 4-> //Caballero
                    frases = new String[]{
                            "Esa armadura sigue como siempre brillante",
                            "Ni un solo rasguño",
                            "Le pegaste con fuerza ¿no?",
                            "- Diria que pegas como una damisela, pero creo que les estaria faltando el respeto a las damas"
                    };
            case 5-> //Undead
                    frases = new String[]{
                            "La noche de los no muertos",
                            "¿Eso rimara con Grog?",
                            "- GRahhh!"
                    };
            case 6-> //Undead fucking dies
                    frases = new String[]{
                            "Vaya que muerden rapido el polvo",
                            "Definitivamente rimaba con grog",
                            "Que asco quitenlo de mi vista"
                    };
            default->
                frases = new String[]{
                        "tu no has visto nada"
                };
        }

        // Selecciona e imprime una frase al azar
        Random rand = new Random();
        System.out.println(frases[rand.nextInt(frases.length)]);
    }

    /*
    * activas raras
    * */

    //Barbaro
    public void tiend() {

    }
}

