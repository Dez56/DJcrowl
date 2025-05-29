import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Char {
    protected String name;  //nombre de unidad
    protected int Hp;       //Vida
    protected int MeS;      //Habilidad Melee, ahora general
    protected int Res;      //Resistencia a impactos
    protected int Arm;      //Armadura en caso de que la unidad tenga
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

    public void ente(int rech, ArrayList<Char> perso){

        //validación

        String entra;

        if(rech > 0 && rech < 4){
            entra = String.valueOf(rech);
        }else{
            System.out.println("Invalido, vuelve a intentar meter otro comando");
            return;
        }

        switch (entra) {
            case "1":

                //declaracion

                System.out.println("seleccione a que personaje atacar");
                Scanner sc = new Scanner(System.in);
                int sele;
                String conf;

                //recoleccion

                sele = sc.nextInt();

                //valido el ataque?

                if (tea != perso.get(sele).tea)
                {
                    atacar(perso.get(sele));
                } else {
                    System.out.println("Ese personaje es de tu equipo, seguro que quieres continuar?");
                    System.out.println("S / N");
                    conf = sc.next();
                    conf = conf.toLowerCase();
                    if(conf == "si" || conf == "s"){
                        atacar(perso.get(sele));
                    }else{
                        System.out.println("bien, vuelve a seleccionar una acción");
                        //ente()
                    }
                }
            break;
            case "2":

            break;
            case "3":

            break;
        }
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

                // Aramdura
                if (objetivo.Arm != 0) {
                    int dad3 = 1 ;//rand.nextInt(1) + 1;  25 de proteger
                    if (dad3 == 1) {
                        objetivo.Arm--;
                        System.out.println(objetivo.name + " bloqueó el daño con su armadura! (Quedan " + objetivo.Arm + " usos)");
                        return; //si tienes suerte, ignora daño
                    }
                }

                // Aplicar daño
                objetivo.Hp--;
                System.out.println(objetivo.name + " recibe daño! HP restante: " + objetivo.Hp);
            } else {
                System.out.println(name + " no logró herir a " + objetivo.name + ".");
            }
        } else {
            System.out.println(name + " falla el ataque contra " + objetivo.name + ".");
        }
    }

    // Método para determinar el número mínimo que debe sacarse en el dado para herir
    private int calc(int fuerza, int resistencia) {
        if (fuerza >= resistencia * 2) return 2; //83
        if (fuerza > resistencia) return 3;      //66
        if (fuerza == resistencia) return 4;     //50
        if (fuerza * 2 <= resistencia) return 6; //16
        return 5;                                //33
    }

}
