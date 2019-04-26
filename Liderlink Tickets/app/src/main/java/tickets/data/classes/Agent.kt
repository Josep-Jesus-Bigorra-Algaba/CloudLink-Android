package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class Agent(
    val available: Int,
    val available_since: String,
    val id: Long,
    val occasional: Int,
    val signature: String,
    val ticket_scope: String,
    val created_at: String,
    val updated_at: String,
    val active: String,
    val email: String,
    val job_title: String,
    val language: String,
    val last_login_at: String,
    val mobile: String,
    val name: String,
    val phone: String,
    val time_zone: String
)

class AgentController {
    fun createAgentTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_AGENT + "("
                + COLUMN_AVAILABLE + " INTEGER, "
                + COLUMN_AVAILABLE_SINCE + " TEXT, "
                + COLUMN_ID + " INTEGER, "
                + COLUMN_OCCASIONAL + " INTEGER, "
                + COLUMN_SIGNATURE + " TEXT, "
                + COLUMN_TICKET_SCOPE + " TEXT, "
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT, "
                + COLUMN_ACTIVE + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_JOB_TITLE + " TEXT, "
                + COLUMN_LANGUAGE + " TEXT, "
                + COLUMN_LAST_LOGIN_AT + " TEXT, "
                + COLUMN_MOBILE + " TEXT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_TIME_ZONE + " TEXT"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addAgent(agent: Agent, db: SQLiteDatabase): Boolean {
        val values = ContentValues()
        values.put(COLUMN_AVAILABLE, agent.available)
        values.put(COLUMN_AVAILABLE_SINCE, agent.available_since)
        values.put(COLUMN_ID, agent.id)
        values.put(COLUMN_OCCASIONAL, agent.occasional)
        values.put(COLUMN_SIGNATURE, agent.signature)
        values.put(COLUMN_TICKET_SCOPE, agent.ticket_scope)
        values.put(COLUMN_CREATED_AT, agent.created_at)
        values.put(COLUMN_UPDATED_AT, agent.updated_at)
        values.put(COLUMN_ACTIVE, agent.active)
        values.put(COLUMN_EMAIL, agent.email)
        values.put(COLUMN_JOB_TITLE, agent.job_title)
        values.put(COLUMN_LANGUAGE, agent.language)
        values.put(COLUMN_LAST_LOGIN_AT, agent.last_login_at)
        values.put(COLUMN_MOBILE, agent.mobile)
        values.put(COLUMN_NAME, agent.name)
        values.put(COLUMN_PHONE, agent.phone)
        values.put(COLUMN_TIME_ZONE, agent.time_zone)
        val success = db.insert(TABLE_AGENT, null, values)
        db.close()
        return (success > 0)
    }

    fun findAgent(agentName: String, db: SQLiteDatabase): Agent? {
        val query =
            "SELECT * FROM $TABLE_AGENT WHERE $COLUMN_NAME =  \"$agentName\""

        val cursor = db.rawQuery(query, null)
        lateinit var agent: Agent
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val available = cursor.getString(0).toInt()
            val availableSince = cursor.getString(1)
            val id = cursor.getString(2).toLong()
            val occasional = cursor.getString(3).toInt()
            val signature = cursor.getString(4)
            val ticketScope = cursor.getString(5)
            val createdAt = cursor.getString(6)
            val updatedAt = cursor.getString(7)
            val active = cursor.getString(8)
            val email = cursor.getString(9)
            val jobTitle = cursor.getString(10)
            val language = cursor.getString(11)
            val lastLoginAt = cursor.getString(12)
            val mobile = cursor.getString(13)
            val name = cursor.getString(14)
            val phone = cursor.getString(15)
            val timeZone = cursor.getString(16)
            agent = Agent(
                available,
                availableSince,
                id,
                occasional,
                signature,
                ticketScope,
                createdAt,
                updatedAt,
                active,
                email,
                jobTitle,
                language,
                lastLoginAt,
                mobile,
                name,
                phone,
                timeZone
            )
            cursor.close()
        }
        db.close()
        return agent
    }

    fun deleteAgent(agentName: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_AGENT WHERE $COLUMN_NAME = \"$agentName\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(
                TABLE_AGENT, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}