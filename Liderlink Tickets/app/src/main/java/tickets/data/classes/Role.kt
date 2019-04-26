package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class Role(
    val description: String,
    val id: Long,
    val name: String,
    val default: String,
    val created_at: String,
    val updated_at: String
)

class RoleController {
    fun createRoleTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_ROLE + "("
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_ID + " INTEGER, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DEFAULT + " TEXT, "
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addRole(role: Role, db: SQLiteDatabase): Boolean {
        val values = ContentValues()

        values.put(COLUMN_DESCRIPTION, role.description)
        values.put(COLUMN_ID, role.id)
        values.put(COLUMN_NAME, role.name)
        values.put(COLUMN_DEFAULT, role.default)
        values.put(COLUMN_CREATED_AT, role.created_at)
        values.put(COLUMN_UPDATED_AT, role.updated_at)

        val success = db.insert(TABLE_ROLE, null, values)
        db.close()
        return success > 0
    }

    fun findRole(roleName: String, db: SQLiteDatabase): Role? {
        val query =
            "SELECT * FROM $TABLE_ROLE WHERE $COLUMN_NAME =  \"$roleName\""

        val cursor = db.rawQuery(query, null)
        lateinit var role: Role
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val description = cursor.getString(0)
            val id = cursor.getString(1).toLong()
            val name = cursor.getString(2)
            val default = cursor.getString(3)
            val createdAt = cursor.getString(4)
            val updatedAt = cursor.getString(5)

            role = Role(
                description,
                id,
                default,
                name,
                createdAt,
                updatedAt
            )
            cursor.close()
        }
        db.close()
        return role
    }

    fun deleteRole(roleName: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_ROLE WHERE $COLUMN_NAME = \"$roleName\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(
                TABLE_ROLE, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}