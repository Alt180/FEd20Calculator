package alt.fed20calculator.Items

open class Weapon(builder: Builder) : Item(builder.name, builder.durability, builder.effect) {

    private val dType: Boolean
    /**
     * Checks if this is an inverted weapon.
     * @return Boolean value true if this is an inverted weapon.
     */
    var isInverted: Boolean = false
        private set
    var might: Int = 0
        private set
    var hit: Int = 0
        private set
    var critical: Int = 0
        private set
    private var minRange = 0
    private var maxRange = 0
    var weight: Int = 0
        private set
    val effective: String
    val type: String
    val triangle: String
    private val rank: Char

    init {
        dType = builder.isdType()
        isInverted = builder.isInverted
        might = builder.might
        hit = builder.hit
        critical = builder.critical
        effective = builder.effective!!
        type = builder.type!!
        triangle = builder.triangle!!
        minRange = builder.minRange
        maxRange = builder.maxRange
        weight = builder.weight
        rank = builder.rank
    }

    /**
     * Improves the weapon's attributes.
     * @param might Amount of points added to the weapon's might.
     * @param hit Amount of points added to the weapon's hit.
     * @param critical Amount of points added to the weapon's crit.
     * @param weight Amount of points subtracted to the weapon's weight. Final result cannot be lower than 1.
     */
    fun forge(might: Int, hit: Int, critical: Int, weight: Int) {
        this.might += might
        this.hit += hit
        this.critical += critical
        if (this.weight - weight >= 1)
            this.weight -= weight
        else
            this.weight = 1
    }

    fun isdType(): Boolean {
        return dType
    }

    /**
     * Sets the weapon as inverted. Weapon triangle and trinity of magic is inverted during battles.
     */
    fun setInverted() {
        isInverted = true
    }

    class Builder private constructor() {
        //Properties definition pending

        val durability: Int = 0
        val might: Int = 0
        val hit: Int = 0
        val critical: Int = 0
        val minRange: Int = 0
        val maxRange: Int = 0
        val weight: Int = 0
        var name: String? = null
            private set
        val effective: String? = null
        var type: String? = null
            private set
        var triangle: String? = null
            private set
        val effect: String? = null
        var rank: Char = ' '
            private set
        private val dType: Boolean = false
        val isInverted: Boolean = false

        fun basics(name: String, rank: Char, type: String, triangle: String): Builder {
            this.name = name
            this.rank = rank
            this.type = type
            this.triangle = triangle
            return this
        }

        fun isdType(): Boolean {
            return dType
        }

        companion object {

            fun newInstance(): Builder {
                return Builder()
            }
        }
    }
}
