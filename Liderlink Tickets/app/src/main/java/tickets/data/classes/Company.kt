package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class Company(
    val description: String,
    val domain: String,
    val id: Long,
    val name: String,
    val note: String,
    val health_score: String,
    val account_tier: String,
    val renewal_date: String,
    val industry: String,
    val created_at: String,
    val updated_at: String
)

class CompanyController {
    fun createCompanyTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_COMPANY + "("
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_DOMAIN + " TEXT, "
                + COLUMN_ID + " INTEGER, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_NOTE + " TEXT, "
                + COLUMN_HEALTH_SCORE + " TEXT, "
                + COLUMN_ACCOUNT_TIER + " TEXT, "
                + COLUMN_RENEWAL_DATE + " TEXT, "
                + COLUMN_INDUSTRY + " TEXT, "
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addCompany(company: Company, db: SQLiteDatabase): Boolean {
        val values = ContentValues()
        values.put(COLUMN_DESCRIPTION, company.description)
        values.put(COLUMN_DOMAIN, company.domain)
        values.put(COLUMN_ID, company.id)
        values.put(COLUMN_NAME, company.name)
        values.put(COLUMN_NOTE, company.note)
        values.put(COLUMN_HEALTH_SCORE, company.health_score)
        values.put(COLUMN_ACCOUNT_TIER, company.account_tier)
        values.put(COLUMN_RENEWAL_DATE, company.renewal_date)
        values.put(COLUMN_INDUSTRY, company.industry)
        values.put(COLUMN_CREATED_AT, company.created_at)
        values.put(COLUMN_UPDATED_AT, company.updated_at)

        val success = db.insert(TABLE_COMPANY, null, values)
        db.close()
        return success > 0
    }

    fun findCompany(companyName: String, db: SQLiteDatabase): Company? {
        val query =
            "SELECT * FROM $TABLE_COMPANY WHERE $COLUMN_NAME =  \"$companyName\""

        val cursor = db.rawQuery(query, null)
        lateinit var company: Company
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val description = cursor.getString(0)
            val domain = cursor.getString(1)
            val id = cursor.getString(2).toLong()
            val name = cursor.getString(3)
            val note = cursor.getString(4)
            val healthScore = cursor.getString(5)
            val accountTier = cursor.getString(6)
            val renewalDate = cursor.getString(7)
            val industry = cursor.getString(8)
            val createdAt = cursor.getString(9)
            val updatedAt = cursor.getString(10)

            company = Company(
                description,
                domain,
                id,
                name,
                note,
                healthScore,
                accountTier,
                renewalDate,
                industry,
                createdAt,
                updatedAt
            )
            cursor.close()
        }
        db.close()
        return company
    }

    fun deleteCompany(companyName: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_COMPANY WHERE $COLUMN_NAME = \"$companyName\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(
                TABLE_COMPANY, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}