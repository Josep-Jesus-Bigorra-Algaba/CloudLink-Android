package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class AgentRole(
    val agent_id: Long,
    val role_id: Long
)

class AgentRoleController {
    fun createAgentRoleTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_AGENT_ROLE + "("
                + COLUMN_AGENT_ID + " INTEGER, "
                + COLUMN_ROLE_ID + " INTEGER"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addAgentRole(agentGroup: AgentRole, db: SQLiteDatabase): Boolean {
        val values = ContentValues()

        values.put(COLUMN_AGENT_ID, agentGroup.agent_id)
        values.put(COLUMN_ROLE_ID, agentGroup.role_id)

        val success = db.insert(TABLE_AGENT_ROLE, null, values)
        db.close()
        return success > 0
    }

    fun findAgentRole(agentID: String, db: SQLiteDatabase): AgentRole? {
        val query =
            "SELECT * FROM $TABLE_AGENT_ROLE WHERE $COLUMN_NAME =  \"$agentID\""

        val cursor = db.rawQuery(query, null)
        lateinit var agentRole: AgentRole
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val agentID = cursor.getString(0).toLong()
            val roleID = cursor.getString(1).toLong()

            agentRole = AgentRole(
                agentID,
                roleID
            )
            cursor.close()
        }
        db.close()
        return agentRole
    }

    fun deleteAgentRole(agentID: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_AGENT_ROLE WHERE $COLUMN_NAME = \"$agentID\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(1))
            db.delete(
                TABLE_AGENT_ROLE, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}