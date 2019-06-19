package alt.fed20calculator.Items;

public class Flute extends Item {
    private int expGranted;
    /**
     * Creates a flute item to allow allies to move again but cannot be equipped or used in battle.
     * @param name Item's name.
     * @param durability Amount of times the item can be used before breaking.
     * @param effect Description of the item's special effect if it has one.
     */
    public Flute(String name, int durability, int expGranted, String effect) {
        super(name, durability, effect);
        this.expGranted = expGranted;
    }
}
