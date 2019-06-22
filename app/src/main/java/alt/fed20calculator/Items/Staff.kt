package alt.fed20calculator.Items

class Staff
/**
 * Creates a staff item to heal allies but cannot be equipped or used in battle.
 * @param name Item's name.
 * @param durability Amount of times the item can be used before breaking.
 * @param rank Only for staffs. Weapon level required to use this item.
 * @param effect Description of the item's special effect if it has one.
 */
(name: String, private val minRange: Int, private val maxRange: Int, durability: Int, private val rank: Char, private val expGranted: Int, effect: String) : Item(name, durability, effect)
