import java.util.Random;

public class Arquero extends Char {
    public Arquero(String name, boolean tea) {
        super(name, 4, 3, 1, 1, 2, tea, false);
    }

    @Override
    protected boolean aco(Char objetivo) {
        if (cooldown > 0) {
            System.out.println(name + " aún no puede usar su habilidad. Cooldown restante: " + cooldown);
            return false;
        }

        cooldown = 1;

        Random rand = new Random();
        int dado = rand.nextInt(6) + 1;

        if (dado >= MeS) {
            System.out.println(name + " lanza una flecha potenciada a " + objetivo.name);
            int dano = Fue + 1 - (objetivo.Res / 2);
            if (dano < 1) dano = 1;
            objetivo.Hp -= dano;
            System.out.println(objetivo.name + " recibe " + dano + " de daño! HP restante: " + objetivo.Hp);
        } else {
            System.out.println(name + " falla su disparo especial contra " + objetivo.name + ".");
        }

        return true;
    }
}
