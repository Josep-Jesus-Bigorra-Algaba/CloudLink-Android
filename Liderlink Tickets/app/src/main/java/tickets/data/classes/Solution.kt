package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class Solution(
    val description: String,
    val id: Long,
    val name: String,
    val created_at: String,
    val updated_at: String
)

class SolutionController {
    fun createSolutionTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_SOLUTION + "("
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_ID + " INTEGER, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addSolution(solution: Solution, db: SQLiteDatabase): Boolean {
        val values = ContentValues()

        values.put(COLUMN_DESCRIPTION, solution.description)
        values.put(COLUMN_ID, solution.id)
        values.put(COLUMN_NAME, solution.name)
        values.put(COLUMN_CREATED_AT, solution.created_at)
        values.put(COLUMN_UPDATED_AT, solution.updated_at)

        val success = db.insert(TABLE_SOLUTION, null, values)
        db.close()
        return success > 0
    }

    fun findSolution(solutionName: String, db: SQLiteDatabase): Solution? {
        val query =
            "SELECT * FROM $TABLE_SOLUTION WHERE $COLUMN_NAME =  \"$solutionName\""

        val cursor = db.rawQuery(query, null)
        lateinit var solution: Solution
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val description = cursor.getString(0)
            val id = cursor.getString(1).toLong()
            val name = cursor.getString(2)
            val createdAt = cursor.getString(3)
            val updatedAt = cursor.getString(4)

            solution = Solution(
                description,
                id,
                name,
                createdAt,
                updatedAt
            )
            cursor.close()
        }
        db.close()
        return solution
    }

    fun deleteSolution(solutionName: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_SOLUTION WHERE $COLUMN_NAME = \"$solutionName\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(1))
            db.delete(
                TABLE_SOLUTION, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}