public class caballero extends Char{
    public caballero(String name, boolean tea) {
        super(name, 5, 2, 2, 2, 3, tea, true);
    }

    @Override
    protected boolean aco(Char asa) {
        this.Hp--;
        return true;
    }
}
