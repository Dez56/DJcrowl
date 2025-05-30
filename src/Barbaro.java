import java.util.Random;

public class Barbaro extends Char{
    public Barbaro(String name, boolean tea) {
        super(name, 5, 3, 1, 0, 3, tea, false);
    }

    @Override
    protected boolean aco(Char objetivo) {
        if (this.tea == objetivo.tea) {
            frase(1);
            System.out.println("Ese personaje es de tu equipo, selecciona de nuevo.");
            return false;
        }

        // Ignora armadura y penetración, solo verifica impacto
        Random rand = new Random();
        int dado = rand.nextInt(6) + 1;

        if (dado >= MeS) {
            System.out.println(name + " impacta con un golpe brutal a " + objetivo.name + "!");

            int dano = Fue - (objetivo.Res / 2);
            if (dano < 1) dano = 1;

            objetivo.Hp -= dano;
            System.out.println(objetivo.name + " recibe " + dano + " de daño! HP restante: " + objetivo.Hp);
            objetivo.tiend();
        } else {
            System.out.println(name + " falla el ataque especial contra " + objetivo.name + ".");
        }

        return true;
    }

    @Override
    public void tiend() {
        if (this.Hp <= 0) {
            this.Hp = 1;
            frase(3);
            System.out.println("El barbaro ha enfurecido, y da el ultimo haz de vida en su cuerpo para una ultima barbarie");
        }
    }
}
