package alt.fed20calculator.Characters

import android.support.v7.app.AppCompatActivity

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.ArrayList

class Job @Throws(JSONException::class)
constructor(job: String, jobObj: JSONObject, val hp: Int, val constitution: Int): AppCompatActivity() {
    val jobName: String
    private val mType: String
    val weakness = arrayOf("", "")
    var isSword = false
        private set
    var isLance = false
        private set
    var isAxe = false
        private set
    var isStaff = false
        private set
    var isLight = false
        private set
    var isDark = false
        private set
    var isAnima = false
        private set
    var isDagger = false
        private set
    private val sInv = ArrayList<String>()
    val movement: Int

    init {
        //Name
        jobName = jobObj.getString("name")
        movement = jobObj.getInt("movement")
        mType = jobObj.getString("mType")
        //Weapon proficiencies
        val wProficiencies = jobObj.getJSONArray("wProficiency")
        val wProficiency = ArrayList<String>()
        if (job == "Lord") {
            //Lord can choose their proficiency as any of the three physical weapons
            wProficiency.add(lordWP("Sword"))
        } else {
            for (i in 0 until wProficiencies.length()) {
                wProficiency.add(wProficiencies.getString(i))
            }
        }
        assignUsableWeapons(wProficiency)
        //Starting inventory
        val inventory = jobObj.getJSONArray("inventory")
        for (i in 0 until inventory.length()) {
            sInv.add(inventory.getString(i))
        }
        //Weaknesses
        if (jobObj.has("weaknesses")) {
            val weaknesses = jobObj.getJSONArray("weaknesses")
            for (i in 0..1) {
                weakness[i] = weaknesses.getString(i)
            }
        }

    }//HP, Constitution and Movement

    private fun lordWP(weapon: String): String {
        //Scan for weapon proficiency
        return weapon
    }

    fun getJSONJob(rank: String, jNo: Int): JSONObject? {
        val json: String
        try {
            val `is` = assets.open("Jobs.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read()
            `is`.close()

            json = String(buffer, StandardCharsets.UTF_8)
            val obj = JSONObject(json)
            val jsonArray: JSONArray = when (rank) {
                "unpromoted" -> obj.getJSONArray("unpromoted")
                "promoted" -> obj.getJSONArray("promoted")
                else -> obj.getJSONArray("trainee")
            }
            return jsonArray.getJSONObject(jNo)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }

    private fun assignUsableWeapons(weapons: ArrayList<String>) {
        for (weapon in weapons) {
            when (weapon) {
                "sword" -> isSword = true
                "lance" -> isLance = true
                "axe" -> isAxe = true
                "staff" -> isStaff = true
                "light" -> isLight = true
                "dark" -> isDark = true
                "anima" -> isAnima = true
                "dagger" -> isDagger = true
            }
        }
    }

    fun getsInv(): ArrayList<String> {
        return sInv
    }

    fun getmType(): String {
        return mType
    }


}
