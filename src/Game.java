import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final ArrayList<Char> equipo1 = new ArrayList<>();
    private final ArrayList<Char> equipo2 = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("=== Iniciando juego ===");
        crearEquipos();

        int turno = 0;

        while (!juegoTerminado()) {
            System.out.println("\n--- Turno #" + (turno + 1) + " ---");

            // Jugador actual
            ArrayList<Char> activos = (turno % 2 == 0) ? equipo1 : equipo2;
            ArrayList<Char> objetivos = (turno % 2 == 0) ? equipo2 : equipo1;
            String jugador = (turno % 2 == 0) ? "Jugador 1" : "Jugador 2";

            System.out.println(jugador + ": elige un personaje para actuar:");

            for (int i = 0; i < activos.size(); i++) {
                Char c = activos.get(i);
                if (c.Hp > 0) {
                    System.out.println(i + ". " + c.name + " (HP: " + c.Hp + ", CD: " + c.cooldown + ")");
                }
            }

            int indice;
            while (true) {
                indice = sc.nextInt();
                if (indice >= 0 && indice < activos.size() && activos.get(indice).Hp > 0) {
                    break;
                } else {
                    System.out.println("Índice inválido o personaje muerto. Intenta de nuevo.");
                }
            }

            Char actual = activos.get(indice);

            boolean valido = false;
            while (!valido) {
                System.out.println("1. Atacar  2. Usar habilidad especial");
                int opcion = sc.nextInt();
                valido = actual.ente(opcion, objetivos);
                if (!valido) {
                    System.out.println("Acción inválida, intenta de nuevo.");
                }
            }

            // Reducir cooldown en todos los personajes
            for (Char c : equipo1) c.tick();
            for (Char c : equipo2) c.tick();

            turno++;
        }

        System.out.println("¡El juego ha terminado!");
        mostrarGanador();
    }

    private void crearEquipos() {
        System.out.println("=== JUGADOR 1: Selecciona 3 personajes ===");
        seleccionarPersonajes(equipo1);

        System.out.println("\n=== JUGADOR 2: Selecciona 3 personajes ===");
        seleccionarPersonajes(equipo2);
    }

    private void seleccionarPersonajes(ArrayList<Char> equipo) {
        boolean esJugador2 = equipo == equipo2;

        System.out.println("0. Bárbaro");
        System.out.println("1. Caballero");
        System.out.println("2. Nigromante");
        System.out.println("3. Arquero");

        while (equipo.size() < 3) {
            System.out.print("Ingresa el número del personaje que quieres añadir: ");
            int selec = sc.nextInt();

            switch (selec) {
                case 0 -> equipo.add(new Barbaro("Barbaro" + (equipo.size() + 1), esJugador2));
                case 1 -> equipo.add(new caballero("Caballero" + (equipo.size() + 1), esJugador2));
                case 2 -> equipo.add(new Nigromante("Nigromante" + (equipo.size() + 1), esJugador2));
                case 3 -> equipo.add(new Arquero("Arquero" + (equipo.size() + 1), esJugador2));
                default -> System.out.println("Número inválido.");
            }
        }
    }


    private boolean juegoTerminado() {
        boolean vivos1 = equipo1.stream().anyMatch(c -> c.Hp > 0);
        boolean vivos2 = equipo2.stream().anyMatch(c -> c.Hp > 0);
        return !(vivos1 && vivos2);
    }

    private void mostrarGanador() {
        boolean vivos1 = equipo1.stream().anyMatch(c -> c.Hp > 0);
        boolean vivos2 = equipo2.stream().anyMatch(c -> c.Hp > 0);

        if (vivos1 && !vivos2) {
            System.out.println("¡Gana el Jugador 1!");
        } else if (!vivos1 && vivos2) {
            System.out.println("¡Gana el Jugador 2!");
        } else {
            System.out.println("Empate.");
        }
    }
}
