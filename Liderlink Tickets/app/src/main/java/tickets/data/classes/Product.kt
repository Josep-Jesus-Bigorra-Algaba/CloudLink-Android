package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class Product(
    val description: String,
    val id: Long,
    val name: String,
    val created_at: String,
    val updated_at: String
)

class ProductController {
    fun createProductTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_PRODUCT + "("
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_ID + " INTEGER, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addProduct(product: Product, db: SQLiteDatabase): Boolean {
        val values = ContentValues()

        values.put(COLUMN_DESCRIPTION, product.description)
        values.put(COLUMN_ID, product.id)
        values.put(COLUMN_NAME, product.name)
        values.put(COLUMN_CREATED_AT, product.created_at)
        values.put(COLUMN_UPDATED_AT, product.updated_at)

        val success = db.insert(TABLE_PRODUCT, null, values)
        db.close()
        return success > 0
    }

    fun findProduct(productName: String, db: SQLiteDatabase): Product? {
        val query =
            "SELECT * FROM $TABLE_PRODUCT WHERE $COLUMN_NAME =  \"$productName\""

        val cursor = db.rawQuery(query, null)
        lateinit var product: Product
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val description = cursor.getString(0)
            val id = cursor.getString(1).toLong()
            val name = cursor.getString(2)
            val createdAt = cursor.getString(3)
            val updatedAt = cursor.getString(4)

            product = Product(
                description,
                id,
                name,
                createdAt,
                updatedAt
            )
            cursor.close()
        }
        db.close()
        return product
    }

    fun deleteProduct(productName: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_PRODUCT WHERE $COLUMN_NAME = \"$productName\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(
                TABLE_PRODUCT, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}