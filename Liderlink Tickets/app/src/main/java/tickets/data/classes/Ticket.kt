package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class Ticket(
    val attachments: String,
    val cc_emails: String,
    val company_id: Long,
    val deleted: Int,
    val description: String,
    val description_text: String,
    val due_by: String,
    val email: String,
    val email_config_id: String,
    val facebook_id: String,
    val fr_due_by: String,
    val fr_escalated: Int,
    val fwd_emails: String,
    val id: Long,
    val is_escalated: Int,
    val name: String,
    val phone: String,
    val priority: Int,
    val reply_cc_emails: String,
    val source: String,
    val spam: String,
    val status: String,
    val subject: String,
    val tags: String,
    val to_emails: String,
    val twitter_id: String,
    val type: String,
    val created_at: String,
    val updated_at: String,
    val contact_id: Long,
    val product_id: Long,
    val group_id: Long,
    val agent_id: Long
)

class TicketController {
    fun createTicketTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_TICKET + "("
                + COLUMN_ATTACHMENTS + " TEXT, "
                + COLUMN_CC_EMAILS + " TEXT, "
                + COLUMN_COMPANY_ID + " INTEGER, "
                + COLUMN_DELETED + " INTEGER, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_DESCRIPTION_TEXT + " TEXT, "
                + COLUMN_DUE_BY + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_EMAIL_CONFIG_ID + " TEXT, "
                + COLUMN_FACEBOOK_ID + " TEXT, "
                + COLUMN_FR_DUE_BY + " TEXT, "
                + COLUMN_FR_ESCALATED + " INTEGER, "
                + COLUMN_FWD_EMAILS + " TEXT, "
                + COLUMN_ID + " INTEGER, "
                + COLUMN_IS_ESCALATED + " INTEGER, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_PRIORITY + " TEXT, "
                + COLUMN_REPLY_CC_EMAILS + " TEXT, "
                + COLUMN_SOURCE + " TEXT, "
                + COLUMN_SPAM + " TEXT, "
                + COLUMN_STATUS + " TEXT, "
                + COLUMN_SUBJECT + " TEXT, "
                + COLUMN_TAGS + " TEXT, "
                + COLUMN_TO_EMAILS + " TEXT, "
                + COLUMN_TWITTER_ID + " TEXT, "
                + COLUMN_TYPE + " TEXT, "
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT, "
                + COLUMN_CONTACT_ID + " INTEGER, "
                + COLUMN_PRODUCT_ID + " INTEGER, "
                + COLUMN_GROUP_ID + " INTEGER, "
                + COLUMN_AGENT_ID + " INTEGER"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addTicket(ticket: Ticket, db: SQLiteDatabase): Boolean {
        val values = ContentValues()

        values.put(COLUMN_ATTACHMENTS, ticket.attachments)
        values.put(COLUMN_CC_EMAILS, ticket.cc_emails)
        values.put(COLUMN_COMPANY_ID, ticket.company_id)
        values.put(COLUMN_DELETED, ticket.deleted)
        values.put(COLUMN_DESCRIPTION, ticket.description)
        values.put(COLUMN_DESCRIPTION_TEXT, ticket.description_text)
        values.put(COLUMN_DUE_BY, ticket.due_by)
        values.put(COLUMN_EMAIL, ticket.email)
        values.put(COLUMN_EMAIL_CONFIG_ID, ticket.email_config_id)
        values.put(COLUMN_FACEBOOK_ID, ticket.facebook_id)
        values.put(COLUMN_FR_DUE_BY, ticket.fr_due_by)
        values.put(COLUMN_FR_ESCALATED, ticket.fr_escalated)
        values.put(COLUMN_FWD_EMAILS, ticket.fwd_emails)
        values.put(COLUMN_ID, ticket.id)
        values.put(COLUMN_IS_ESCALATED, ticket.is_escalated)
        values.put(COLUMN_NAME, ticket.name)
        values.put(COLUMN_PHONE, ticket.phone)
        values.put(COLUMN_PRIORITY, ticket.priority)
        values.put(COLUMN_REPLY_CC_EMAILS, ticket.reply_cc_emails)
        values.put(COLUMN_SOURCE, ticket.source)
        values.put(COLUMN_SPAM, ticket.spam)
        values.put(COLUMN_STATUS, ticket.status)
        values.put(COLUMN_SUBJECT, ticket.subject)
        values.put(COLUMN_TAGS, ticket.tags)
        values.put(COLUMN_TO_EMAILS, ticket.to_emails)
        values.put(COLUMN_TWITTER_ID, ticket.twitter_id)
        values.put(COLUMN_TYPE, ticket.type)
        values.put(COLUMN_CREATED_AT, ticket.created_at)
        values.put(COLUMN_UPDATED_AT, ticket.updated_at)
        values.put(COLUMN_CONTACT_ID, ticket.contact_id)
        values.put(COLUMN_PRODUCT_ID, ticket.product_id)
        values.put(COLUMN_GROUP_ID, ticket.group_id)
        values.put(COLUMN_AGENT_ID, ticket.agent_id)

        val success = db.insert(TABLE_TICKET, null, values)
        return success > 0
    }

    fun findTicket(ticketID: String, db: SQLiteDatabase): Ticket? {
        val query =
            "SELECT * FROM $TABLE_TICKET WHERE $COLUMN_ID =  \"$ticketID\""
        println(query)
        val cursor = db.rawQuery(query, null)
        var ticket: Ticket? = null
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val attachments = cursor.getString(0)
            val ccEmails = cursor.getString(1)
            val companyID = cursor.getString(2).toLong()
            val deleted = cursor.getString(3).toInt()
            val description = cursor.getString(4)
            val descriptionText = cursor.getString(5)
            val dueBy = cursor.getString(6)
            val email = cursor.getString(7)
            val emailConfigID = cursor.getString(8)
            val facebookID = cursor.getString(9)
            val frDueBy = cursor.getString(10)
            val frEscalated = cursor.getString(11).toInt()
            val fwdEmails = cursor.getString(12)
            val id = cursor.getString(13).toLong()
            val isEscalated = cursor.getString(14).toInt()
            val name = cursor.getString(15)
            val phone = cursor.getString(16)
            val priority = cursor.getString(17).toInt()
            val replyCCEmails = cursor.getString(18)
            val source = cursor.getString(19)
            val spam = cursor.getString(20)
            val status = cursor.getString(21)
            val subject = cursor.getString(22)
            val tags = cursor.getString(23)
            val toEmails = cursor.getString(24)
            val twitterID = cursor.getString(25)
            val type = cursor.getString(26)
            val createdAt = cursor.getString(27)
            val updatedAt = cursor.getString(28)
            val contactID = cursor.getString(29).toLong()
            val productID = cursor.getString(30).toLong()
            val groupID = cursor.getString(31).toLong()
            val agentID = cursor.getString(32).toLong()

            ticket = Ticket(
                attachments,
                ccEmails,
                companyID,
                deleted,
                description,
                descriptionText,
                dueBy,
                email,
                emailConfigID,
                facebookID,
                frDueBy,
                frEscalated,
                fwdEmails,
                id,
                isEscalated,
                name,
                phone,
                priority,
                replyCCEmails,
                source,
                spam,
                status,
                subject,
                tags,
                toEmails,
                twitterID,
                type,
                createdAt,
                updatedAt,
                contactID,
                productID,
                groupID,
                agentID
            )
            cursor.close()
        }
        db.close()
        return ticket
    }

    fun deleteTicket(ticketName: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_TICKET WHERE $COLUMN_NAME = \"$ticketName\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(1))
            db.delete(
                TABLE_TICKET, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}