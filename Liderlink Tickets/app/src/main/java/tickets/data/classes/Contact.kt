package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class Contact(
    val active: Int,
    val address: String,
    val view_all_tickets: Int,
    val deleted: Int,
    val description: String,
    val email: String,
    val id: Long,
    val job_title: String,
    val language: String,
    val mobile: String,
    val name: String,
    val other_emails: String,
    val phone: String,
    val tags: String,
    val time_zone: String,
    val twitter_id: String,
    val unique_external_id: String,
    val other_companies: String,
    val created_at: String,
    val updated_at: String,
    val company_id: Long
)

class ContactController {
    fun createContactTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_CONTACT + "("
                + COLUMN_ACTIVE + " INTEGER, "
                + COLUMN_ADDRESS + " INTEGER, "
                + COLUMN_VIEW_ALL_TICKETS + " INTEGER, "
                + COLUMN_DELETED + " INTEGER, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_ID + " TEXT, "
                + COLUMN_JOB_TITLE + " TEXT, "
                + COLUMN_LANGUAGE + " INTEGER, "
                + COLUMN_MOBILE + " TEXT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_OTHER_EMAILS + " TEXT, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_TAGS + " TEXT, "
                + COLUMN_TIME_ZONE + " TEXT, "
                + COLUMN_TWITTER_ID + " TEXT, "
                + COLUMN_UNIQUE_EXTERNAL_ID + " TEXT, "
                + COLUMN_OTHER_COMPANIES + " TEXT, "
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT, "
                + COLUMN_COMPANY_ID + " TEXT"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addContact(contact: Contact, db: SQLiteDatabase): Boolean {
        val values = ContentValues()

        values.put(COLUMN_ACTIVE, contact.active)
        values.put(COLUMN_ADDRESS, contact.address)
        values.put(COLUMN_VIEW_ALL_TICKETS, contact.view_all_tickets)
        values.put(COLUMN_DELETED, contact.deleted)
        values.put(COLUMN_DESCRIPTION, contact.description)
        values.put(COLUMN_EMAIL, contact.email)
        values.put(COLUMN_ID, contact.id)
        values.put(COLUMN_JOB_TITLE, contact.job_title)
        values.put(COLUMN_LANGUAGE, contact.language)
        values.put(COLUMN_MOBILE, contact.mobile)
        values.put(COLUMN_NAME, contact.name)
        values.put(COLUMN_OTHER_EMAILS, contact.other_emails)
        values.put(COLUMN_PHONE, contact.phone)
        values.put(COLUMN_TAGS, contact.tags)
        values.put(COLUMN_TIME_ZONE, contact.time_zone)
        values.put(COLUMN_TWITTER_ID, contact.twitter_id)
        values.put(COLUMN_UNIQUE_EXTERNAL_ID, contact.unique_external_id)
        values.put(COLUMN_OTHER_COMPANIES, contact.other_companies)
        values.put(COLUMN_CREATED_AT, contact.created_at)
        values.put(COLUMN_UPDATED_AT, contact.updated_at)
        values.put(COLUMN_COMPANY_ID, contact.company_id)

        val success = db.insert(TABLE_CONTACT, null, values)
        return success > 0
    }

    fun findContact(contactName: String, db: SQLiteDatabase): Contact? {
        val query =
            "SELECT * FROM $TABLE_CONTACT WHERE $COLUMN_NAME =  \"$contactName\""

        val cursor = db.rawQuery(query, null)
        var contact: Contact? = null
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val active = cursor.getString(0).toInt()
            val address = cursor.getString(1)
            val viewAllTickets = cursor.getString(2).toInt()
            val deleted = cursor.getString(3).toInt()
            val description = cursor.getString(4)
            val email = cursor.getString(5)
            val id = cursor.getString(6).toLong()
            val jobTitle = cursor.getString(7)
            val language = cursor.getString(8)
            val mobile = cursor.getString(9)
            val name = cursor.getString(10)
            val otherEmails = cursor.getString(11)
            val phone = cursor.getString(12)
            val tags = cursor.getString(13)
            val timeZone = cursor.getString(14)
            val twitterID = cursor.getString(15)
            val uniqueExternalID = cursor.getString(16)
            val otherCompanies = cursor.getString(17)
            val createdAt = cursor.getString(18)
            val updatedAt = cursor.getString(19)
            val companyID = cursor.getString(20).toLong()

            contact = Contact(
                active,
                address,
                viewAllTickets,
                deleted,
                description,
                email,
                id,
                jobTitle,
                language,
                mobile,
                name,
                otherEmails,
                phone,
                tags,
                timeZone,
                twitterID,
                uniqueExternalID,
                otherCompanies,
                createdAt,
                updatedAt,
                companyID
            )

            cursor.close()
        }
        db.close()
        return contact
    }

    fun deleteContact(contactName: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_CONTACT WHERE $COLUMN_NAME = \"$contactName\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(
                TABLE_CONTACT, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}