package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class AgentGroup(
    val agent_id: Long,
    val group_id: Long
)

class AgentGroupController {
    fun createAgentGroupTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_AGENT_GROUP + "("
                + COLUMN_AGENT_ID + " INTEGER, "
                + COLUMN_GROUP_ID + " INTEGER"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addAgentGroup(agentGroup: AgentGroup, db: SQLiteDatabase): Boolean {
        val values = ContentValues()

        values.put(COLUMN_AGENT_ID, agentGroup.agent_id)
        values.put(COLUMN_GROUP_ID, agentGroup.group_id)


        val success = db.insert(TABLE_AGENT_GROUP, null, values)
        db.close()
        return success > 0
    }

    fun findAgentGroup(agentID: String, db: SQLiteDatabase): AgentGroup? {
        val query =
            "SELECT * FROM $TABLE_AGENT_GROUP WHERE $COLUMN_NAME =  \"$agentID\""

        val cursor = db.rawQuery(query, null)
        lateinit var agentGroup: AgentGroup
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val agentID = cursor.getString(0).toLong()
            val groupID = cursor.getString(1).toLong()


            agentGroup = AgentGroup(
                agentID,
                groupID
            )
            cursor.close()
        }
        db.close()
        return agentGroup
    }

    fun deleteAgentGroup(agentID: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_AGENT_GROUP WHERE $COLUMN_NAME = \"$agentID\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(1))
            db.delete(
                TABLE_AGENT_GROUP, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}