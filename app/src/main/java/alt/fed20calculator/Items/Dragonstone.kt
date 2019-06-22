package alt.fed20calculator.Items

import android.util.Log

class Dragonstone
/**
 * Creates a new Dragonstone weapon.
 * @param builder Weapon builder.
 * @param bonuses Stats that increment thanks to the stone's power.
 */
(builder: Weapon.Builder, val bonuses: IntArray) : Weapon(builder)
