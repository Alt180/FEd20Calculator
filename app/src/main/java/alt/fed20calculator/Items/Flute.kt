package alt.fed20calculator.Items

class Flute
/**
 * Creates a flute item to allow allies to move again but cannot be equipped or used in battle.
 * @param name Item's name.
 * @param durability Amount of times the item can be used before breaking.
 * @param effect Description of the item's special effect if it has one.
 */
(name: String, durability: Int, private val expGranted: Int, effect: String) : Item(name, durability, effect)
