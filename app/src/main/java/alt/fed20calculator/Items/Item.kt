package alt.fed20calculator.Items

import android.util.Log

/**
 * Defines an item that a character can hold on their inventory. This item can be either a weapon or a consumable.
 */
open class Item
/**
 * Creates a consumable item that cannot be equipped or used in battle.
 * @param name Item's name.
 * @param durability Times the item can be used
 * @param effect Description of the item's effect.
 */(val name: String?, durability: Int, val effect: String?) : Cloneable {
    /**
     * Checks if the item is droppable.
     * @return Boolean value true if the item is droppable.
     */
    var isDrop = false
        private set
    var durability: Int = 0
        private set

    init {
        this.durability = durability
    }

    /**
     * Each time an item is used it reduces its durability. If it reaches 0 the item is wasted and cannot be used anymore.
     * @return uses left.
     */
    fun used(): Int {
        durability--
        if (durability <= 0) Log.d(TAG, "Weapon $name has broken.")
        return durability
    }

    /**
     * Sets the item as droppable.
     */
    fun setDrop() {
        isDrop = true
    }

    @Throws(CloneNotSupportedException::class)
    override fun clone(): Item {
        return super.clone() as Item
    }

    companion object {
        internal val TAG = "Item"
    }
}
