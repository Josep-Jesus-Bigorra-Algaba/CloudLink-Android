package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class Group(
    val auto_ticket_assign: Int,
    val business_hour_id: String,
    val description: String,
    val escalate_to: String,
    val id: Long,
    val name: String,
    val unassigned_for: String,
    val created_at: String,
    val updated_at: String
)

class GroupController {
    fun createGroupTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_GROUP + "("
                + COLUMN_AUTO_TICKET_ASSIGN + " INTEGER, "
                + COLUMN_BUSINESS_HOUR_ID + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_ESCALATE_TO + " TEXT, "
                + COLUMN_ID + " INTEGER, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_UNASSIGNED_FOR + " TEXT, "
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addGroup(group: Group, db: SQLiteDatabase): Boolean {
        val values = ContentValues()

        values.put(COLUMN_AUTO_TICKET_ASSIGN, group.auto_ticket_assign)
        values.put(COLUMN_BUSINESS_HOUR_ID, group.business_hour_id)
        values.put(COLUMN_DESCRIPTION, group.description)
        values.put(COLUMN_ESCALATE_TO, group.escalate_to)
        values.put(COLUMN_ID, group.id)
        values.put(COLUMN_NAME, group.name)
        values.put(COLUMN_UNASSIGNED_FOR, group.unassigned_for)
        values.put(COLUMN_CREATED_AT, group.created_at)
        values.put(COLUMN_UPDATED_AT, group.updated_at)

        val success = db.insert(TABLE_GROUP, null, values)
        db.close()
        return success > 0
    }

    fun findGroup(groupName: String, db: SQLiteDatabase): Group? {
        val query =
            "SELECT * FROM $TABLE_GROUP WHERE $COLUMN_NAME =  \"$groupName\""

        val cursor = db.rawQuery(query, null)
        lateinit var group: Group
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val autoTicketAssign = cursor.getString(0).toInt()
            val businessHourID = cursor.getString(1)
            val description = cursor.getString(2)
            val escalateTo = cursor.getString(3)
            val id = cursor.getString(4).toLong()
            val name = cursor.getString(5)
            val unassignedFor = cursor.getString(6)
            val createdAt = cursor.getString(7)
            val updatedAt = cursor.getString(8)

            group = Group(
                autoTicketAssign,
                businessHourID,
                description,
                escalateTo,
                id,
                name,
                unassignedFor,
                createdAt,
                updatedAt
            )
            cursor.close()
        }
        db.close()
        return group
    }

    fun deleteGroup(groupName: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_GROUP WHERE $COLUMN_NAME = \"$groupName\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(
                TABLE_GROUP, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}