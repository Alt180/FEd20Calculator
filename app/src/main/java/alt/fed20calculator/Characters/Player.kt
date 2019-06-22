package alt.fed20calculator.Characters

import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap

import alt.fed20calculator.Items.Item
import alt.fed20calculator.Items.Weapon

/**
 * Defines a playable character that shall fight in the army.
 */
class Player @Throws(JSONException::class)
private constructor(builder: Builder, jobObj: JSONObject) {
    private val job: Job
    private val name: String?
    private val affinity: String?
    private var alive = true
    private val level = 1
    private var exp = 0
    private val maxHp = 0
    private var currHp: Int = 0
    private val mov: Int = 0
    private val str: Int
    private val mag: Int
    private val spd: Int
    private val skl: Int
    private val lck: Int
    private val def: Int
    private val res: Int
    private val sword = 'E'
    private val lance = 'E'
    private val axe = 'E'
    private val bow = 'E'
    private val dagger = 'E'
    private val anima = 'E'
    private val light = 'E'
    private val dark = 'E'
    private val staff = 'E'

    private val inventory: ArrayList<Item>? = null
    private val skills: ArrayList<String>? = null
    private val hpG: Char
    private val strG: Char
    private val magG: Char
    private val spdG: Char
    private val sklG: Char
    private val lckG: Char
    private val defG: Char
    private val resG: Char
    private val swordExp = 0
    private val lanceExp = 0
    private val axeExp = 0
    private val bowExp = 0
    private val daggerExp = 0
    private val animaExp = 0
    private val lightExp = 0
    private val darkExp = 0
    private val staffExp = 0

    init {
        //Value assignation
        name = builder.name
        affinity = builder.affinity

        str = builder.str
        mag = builder.mag
        spd = builder.spd
        skl = builder.skl
        lck = builder.lck
        def = builder.def
        res = builder.res

        hpG = builder.hpG
        strG = builder.strG
        magG = builder.magG
        spdG = builder.spdG
        sklG = builder.sklG
        lckG = builder.lckG
        defG = builder.defG
        resG = builder.resG

        job = Job(builder.job!!, jobObj, builder.hp, builder.constitution)
        for (item in job.getsInv()) {
            //Add items to inventory
        }
    }

    /**
     * Gets the character's data necessary to perform a battle.
     * @param wIndex Index pointing to the equipped weapon.
     * @return Character's data containing speed, hit rate, evasion, attack, weapon might, crit chance, evasion, def, res, weapon type and weapon's effectiveness.
     */
    fun getBattle(wIndex: Int): HashMap<String, String> {
        val eWeapon = inventory!![wIndex] as Weapon
        val battleData = HashMap<String, String>()
        //Speed
        val weight = Integer.parseInt(eWeapon.weight.toString())
        val speed: Int
        if (weight > job.constitution)
            speed = spd + (job.constitution - weight)
        else
            speed = spd
        battleData["speed"] = speed.toString()
        //Hit
        battleData["hit"] = ((2 * skl).toDouble() + 0.5 * lck + Integer.parseInt(eWeapon.hit.toString()).toDouble() + eWeapon.type.let { aHit(it).toDouble() }).toString()
        //Attack. Since weapon damage can be triple if effective it must be separated from unit's attack.
        if (java.lang.Boolean.parseBoolean(eWeapon.isdType().toString()))
            battleData["attack"] = (str + aAttack(eWeapon.type)).toString()
        else
            battleData["attack"] = (mag + aAttack(eWeapon.type)).toString()
        battleData["might"] = eWeapon.might.toString()
        //Critical
        battleData["critical"] = (Integer.parseInt(eWeapon.critical.toString()) + 0.5 * skl).toString()
        //Evasion
        battleData["evasion"] = (2 * speed + lck).toString()
        //Def and Res
        battleData["def"] = def.toString()
        battleData["res"] = res.toString()
        //Type
        battleData["type"] = eWeapon.type
        //Effective
        battleData["effective"] = eWeapon.effective

        return battleData
    }

    /**
     * Calculates hit bonuses depending on the weapon proficiency.
     * @param type Type of weapon used.
     * @return Hit bonus given by character's weapon proficiency.
     */
    private fun aHit(type: String): Int {
        when (type) {
            "Sword" -> if (sword == 'S') {
                return 5
            }
            "Axe" -> when (axe) {
                'C' -> return 5
                'B', 'A' -> return 10
                'S' -> return 15
            }
            "Lance" -> when (lance) {
                'B', 'A' -> return 5
                'S' -> return 10
            }
            "Dagger" -> if (dagger == 'S') {
                return 5
            }
            "Bow" -> when (bow) {
                'B', 'A' -> return 5
                'S' -> return 10
            }
            "Fire", "Thunder", "Wind" -> when (anima) {
                'B', 'A' -> return 5
                'S' -> return 10
            }
            "Dark" -> when (dark) {
                'B', 'A' -> return 5
                'S' -> return 10
            }
            "Light" -> when (light) {
                'B', 'A' -> return 5
                'S' -> return 10
            }
        }
        return 0
    }

    /**
     * Calculates attack bonuses depending on the weapon proficiency.
     * @param type Type of weapon used.
     * @return Attack bonus given by character's weapon proficiency.
     */
    private fun aAttack(type: String): Int {
        when (type) {
            "Sword" -> when (sword) {
                'C' -> return 1
                'B' -> return 2
                'A' -> return 3
                'S' -> return 4
            }
            "Axe" -> when (axe) {
                'A' -> return 1
                'S' -> return 2
            }
            "Lance" -> when (lance) {
                'C', 'B' -> return 1
                'A' -> return 2
                'S' -> return 3
            }
            "Dagger" -> when (dagger) {
                'C' -> return 1
                'B' -> return 2
                'A' -> return 3
                'S' -> return 4
            }
            "Bow" -> when (bow) {
                'C', 'B' -> return 1
                'A' -> return 2
                'S' -> return 3
            }
            "Fire", "Thunder", "Wind" -> when (anima) {
                'C', 'B' -> return 1
                'A' -> return 2
                'S' -> return 3
            }
            "Dark" -> when (dark) {
                'C', 'B' -> return 1
                'A' -> return 2
                'S' -> return 3
            }
            "Light" -> when (light) {
                'C', 'B' -> return 1
                'A' -> return 2
                'S' -> return 3
            }
        }
        return 0
    }

    /**
     * Calculates the amount of exp gained from a battle when hitting the enemy.
     * @param eLevel Enemy's level.
     * @return Unit's total exp after the battle.
     */
    fun hitExp(eLevel: Int): Int {
        var jValue = 3
        if (job.jobName == "Soldier" || job.jobName == "Priest" || job.jobName == "Thief") jValue = 2
        val rewardedExp = (31 - (level - eLevel)) / jValue
        levelUp(rewardedExp)
        return exp
    }

    /**
     * Calculates the amount of exp gained from a victory in battle.
     * @param eLevel Enemy's level.
     * @param eJob Enemy's class name.
     * @return Unit's total exp after the battle.
     */
    fun killExp(eLevel: Int, eJob: String): Int {
        var jValue = 3
        var eValue = 3
        if (job.jobName == "Soldier" || job.jobName == "Priest" || job.jobName == "Thief") jValue = 2
        if (eJob == "Soldier" || eJob == "Priest" || eJob == "Thief") eValue = 2
        val rewardedExp = 30 + eValue * eLevel - jValue * level
        levelUp(rewardedExp)
        return exp
    }

    /**
     * Procedure to add a fixed amount of exp from certain actions like using a staff or missing in battle.
     * @return Unit's total exp after the battle
     */
    fun otherExp(expGained: Int): Int {
        levelUp(expGained)
        return exp
    }

    /**
     * Adds the gained exp to the total counter and compares if the character has leveled up ans performs the stats growth if so.
     * @param _rewardedExp Exp gained by the character.
     */
    private fun levelUp(_rewardedExp: Int) {
        var rewardedExp = _rewardedExp
        if (rewardedExp < 1)
            rewardedExp = 1
        else if (rewardedExp > 100) rewardedExp = 100
        exp += rewardedExp
        if (exp >= 100) {
            exp -= 100
            //Roll to improve stats.
        }
    }

    /**
     * Sets character's HP to max. Should be done at the beelining of a battle.
     */
    fun resetHp() {
        currHp = maxHp
    }

    /**
     * When a character is hit or healed this method should be called to reflect HP change.
     * @param damage Amount of HP that shall be subtracted from character's health. Must be negative if healing.
     * @return Remaining HP.
     */
    fun damageReceived(damage: Int): Int {
        currHp -= damage
        if (currHp > maxHp) {
            currHp = maxHp
        } else if (currHp <= 0) {
            //Character dies
            currHp = 0
            alive = false
        }
        return currHp
    }

    /**
     * Similar to damageReceived, but this method prevents a character from dying. Should be used for environmental damage.
     * @param damage Amount of HP that shall be subtracted from character's health. Must be negative if healing.
     * @return Remaining HP.
     */
    fun enviromentalDamage(damage: Int): Int {
        currHp -= damage
        if (currHp <= 0) currHp = 1
        return currHp
    }

    /**
     * Adds an item to the character's inventory.
     * @param item Item to add to the inventory.
     * @return True if the item could be saved in the character's inventory, false if its full.
     */
    fun setInventory(item: Item): Boolean {
        var success = false
        if (inventory!!.size < 5) {
            inventory.add(item)
            success = true
        }
        return success
    }

    /**
     * Builder class for Player instances.
     */
    class Builder private constructor() {
        var name: String? = null
            private set
        internal var affinity: String? = null
            private set
        internal var job: String? = null
            private set
        var hp: Int = 0
            private set
        var constitution: Int = 0
            private set
        internal var str: Int = 0
            private set
        internal var mag: Int = 0
            private set
        internal var spd: Int = 0
            private set
        internal var skl: Int = 0
            private set
        internal var lck: Int = 0
            private set
        internal var def: Int = 0
            private set
        internal var res: Int = 0
            private set
        internal var hpG: Char = ' '
            private set
        internal var strG: Char = ' '
            private set
        internal var magG: Char = ' '
            private set
        internal var spdG: Char = ' '
            private set
        internal var sklG: Char = ' '
            private set
        internal var lckG: Char = ' '
            private set
        internal var defG: Char = ' '
            private set
        internal var resG: Char = ' '
            private set

        /**
         * Sets primary properties for the character.
         * @param name Character's name.
         * @param affinity Character's affinity.
         * @param job Character's class.
         * @return Builder object
         */
        fun setName(name: String, affinity: String, job: String): Builder {
            this.name = name
            this.affinity = affinity
            this.job = job
            return this
        }

        fun setJobRNGs(hp: Int, constitution: Int): Builder {
            this.hp = hp
            this.constitution = constitution
            return this
        }

        /**
         * Sets initial stats for the character.
         * @param str Strength
         * @param mag Magic
         * @param spd Speed
         * @param skl Skill
         * @param lck Luck
         * @param def Defense
         * @param res Resistance
         * @return Builder object
         */
        fun setStats(str: Int, mag: Int, spd: Int, skl: Int, lck: Int, def: Int, res: Int): Builder {
            this.str = str
            this.mag = mag
            this.spd = spd
            this.skl = skl
            this.lck = lck
            this.def = def
            this.res = res
            return this
        }

        /**
         * Sets growths for each stat that can increment in level ups.
         * @param hpG HP increment
         * @param strG Strength increment
         * @param magG Magic increment
         * @param spdG Speed increment
         * @param sklG Skill increment
         * @param lckG Luck increment
         * @param defG Defense increment
         * @param resG Luck increment
         * @return Builder object
         */
        fun setGrowths(hpG: Char, strG: Char, magG: Char, spdG: Char, sklG: Char, lckG: Char, defG: Char, resG: Char): Builder {
            this.hpG = hpG
            this.strG = strG
            this.magG = magG
            this.spdG = spdG
            this.sklG = sklG
            this.lckG = lckG
            this.defG = defG
            this.resG = resG

            return this
        }

        /**
         * Builds player object
         * @return Player instance
         */
        @Throws(JSONException::class)
        fun build(jobObj: JSONObject): Player {
            return Player(this, jobObj)
        }

        companion object {
            //Instance fields
            fun newInstance(): Builder {
                return Builder()
            }
        }
    }

    companion object {
        private val TAG = "Player"
    }

}
