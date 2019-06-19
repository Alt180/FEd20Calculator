package alt.fed20calculator.Items;

public class Staff extends Item {
    private int minRange;
    private int maxRange;
    private int expGranted;
    private char rank;
    /**
     * Creates a staff item to heal allies but cannot be equipped or used in battle.
     * @param name Item's name.
     * @param durability Amount of times the item can be used before breaking.
     * @param rank Only for staffs. Weapon level required to use this item.
     * @param effect Description of the item's special effect if it has one.
     */
    public Staff(String name, int minRange, int maxRange, int durability, char rank, int expGranted, String effect) {
        super(name, durability, effect);
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.rank = rank;
        this.expGranted = expGranted;
    }
}
