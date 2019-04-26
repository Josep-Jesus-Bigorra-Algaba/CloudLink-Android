package tickets.data.classes

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

data class Conversation(
    val attachments: String,
    val body: String,
    val body_text: String,
    val id: Long,
    val incoming: String,
    val to_emails: String,
    val private: Int,
    val source: Int,
    val support_email: String,
    val user_id: Long,
    val created_at: String,
    val updated_at: String,
    val from_email: String,
    val cc_emails: String,
    val bcc_emails: String,
    val ticketID: Long
)

class ConversationController {
    fun createConversationTable(db: SQLiteDatabase) {
        val createdQuery = ("CREATE TABLE " +
                TABLE_CONVERSATION + "("
                + COLUMN_ATTACHMENTS + " INTEGER, "
                + COLUMN_BODY + " INTEGER, "
                + COLUMN_BODY_TEXT + " INTEGER, "
                + COLUMN_ID + " INTEGER, "
                + COLUMN_INCOMING + " TEXT, "
                + COLUMN_TO_EMAILS + " TEXT, "
                + COLUMN_PRIVATE + " TEXT, "
                + COLUMN_SOURCE + " TEXT, "
                + COLUMN_SUPPORT_EMAIL + " TEXT, "
                + COLUMN_USER_ID + " TEXT, "
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT, "
                + COLUMN_FROM_EMAIL + " TEXT, "
                + COLUMN_CC_EMAILS + " TEXT, "
                + COLUMN_BCC_EMAILS + " TEXT, "
                + COLUMN_TICKET_ID + " TEXT"
                + ")")
        println(createdQuery)
        db.execSQL(createdQuery)
    }

    fun addConversation(conversation: Conversation, db: SQLiteDatabase): Boolean {
        val values = ContentValues()

        values.put(COLUMN_ATTACHMENTS, conversation.attachments)
        values.put(COLUMN_BODY, conversation.body)
        values.put(COLUMN_BODY_TEXT, conversation.body_text)
        values.put(COLUMN_ID, conversation.id)
        values.put(COLUMN_INCOMING, conversation.incoming)
        values.put(COLUMN_TO_EMAILS, conversation.to_emails)
        values.put(COLUMN_PRIVATE, conversation.private)
        values.put(COLUMN_SOURCE, conversation.source)
        values.put(COLUMN_SUPPORT_EMAIL, conversation.support_email)
        values.put(COLUMN_USER_ID, conversation.user_id)
        values.put(COLUMN_CREATED_AT, conversation.created_at)
        values.put(COLUMN_UPDATED_AT, conversation.updated_at)
        values.put(COLUMN_FROM_EMAIL, conversation.from_email)
        values.put(COLUMN_CC_EMAILS, conversation.cc_emails)
        values.put(COLUMN_BCC_EMAILS, conversation.bcc_emails)
        values.put(COLUMN_TICKET_ID, conversation.ticketID)
        val success = db.insert(TABLE_CONVERSATION, null, values)
        db.close()
        return success > 0
    }

    fun findConversation(ticketID: String, db: SQLiteDatabase): Conversation? {
        val query =
            "SELECT * FROM $TABLE_CONVERSATION WHERE $COLUMN_NAME =  \"$ticketID\""

        val cursor = db.rawQuery(query, null)
        lateinit var conversation: Conversation
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val attachments = cursor.getString(0)
            val body = cursor.getString(1)
            val bodyText = cursor.getString(2)
            val id = cursor.getString(3).toLong()
            val incoming = cursor.getString(4)
            val toEmails = cursor.getString(5)
            val private = cursor.getString(6).toInt()
            val source = cursor.getString(7).toInt()
            val supportEmail = cursor.getString(8)
            val userID = cursor.getString(9).toLong()
            val createdAt = cursor.getString(10)
            val updatedAt = cursor.getString(11)
            val fromEmail = cursor.getString(12)
            val ccEmails = cursor.getString(13)
            val bccEmails = cursor.getString(14)
            val ticketID = cursor.getString(15).toLong()

            conversation = Conversation(
                attachments,
                body,
                bodyText,
                id,
                incoming,
                toEmails,
                private,
                source,
                supportEmail,
                userID,
                createdAt,
                updatedAt,
                fromEmail,
                ccEmails,
                bccEmails,
                ticketID
            )
            cursor.close()
        }
        db.close()
        return conversation
    }

    fun deleteConversation(ticketID: String, db: SQLiteDatabase): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_CONVERSATION WHERE $COLUMN_NAME = \"$ticketID\""

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(
                TABLE_CONVERSATION, "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}