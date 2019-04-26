package tickets.liderlink.pt

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import tickets.data.classes.*

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(
        context,
        DatabaseHandler.DB_NAME,
        null,
        DatabaseHandler.DB_VERSION
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        AgentController().createAgentTable(db)
        AgentGroupController().createAgentGroupTable(db)
        AgentRoleController().createAgentRoleTable(db)
        CompanyController().createCompanyTable(db)
        ContactController().createContactTable(db)
        ConversationController().createConversationTable(db)
        GroupController().createGroupTable(db)
        ProductController().createProductTable(db)
        RoleController().createRoleTable(db)
        SolutionController().createSolutionTable(db)
        TicketController().createTicketTable(db)
        TicketController().addTicket(
            Ticket(
                "ATTACHMENTS",
                "CC_EMAILS",
                191919,
                1,
                "THIS IS A TEST TICKET",
                "THIS IS SIMPLY A TEXT TICKET",
                "19-12-1505",
                "zezere@gmail.com",
                "123",
                "joe.big.5",
                "14-2-1506",
                1,
                "seila2liderlink.pt",
                2828,
                1,
                "hotel",
                "914914914",
                2,
                "someone, another one",
                "company",
                "SPAM",
                "Open for 5 days",
                "Android is beautiful",
                "android, broing, another",
                "@liderlink.pt",
                "twitter_id",
                "hardware problem",
                "19-5-1066",
                "14-3-1604",
                1919919191,
                1919919191,
                19199191,
                19191991
            ), db
        )
        TicketController().addTicket(
            Ticket(
                "ATTACHMENTS",
                "CC_EMAILS",
                191919,
                1,
                "THIS IS A TEST TICKET",
                "THIS IS SIMPLY A TEXT TICKET",
                "19-12-1505",
                "zezere@gmail.com",
                "123",
                "joe.big.5",
                "14-2-1506",
                1,
                "seila2liderlink.pt",
                2829,
                1,
                "hotel",
                "914914914",
                1,
                "someone, another one",
                "company",
                "SPAM",
                "Open for 5 days",
                "Android is beautiful",
                "android, broing, another",
                "@liderlink.pt",
                "twitter_id",
                "hardware problem",
                "19-5-1066",
                "14-3-1604",
                1919919191,
                1919919191,
                19199191,
                19191991
            ), db
        )
        ContactController().addContact(
            Contact(
                1,
                "Sitio da Berlavista",
                1,
                1,
                "",
                "@something",
                1895134,
                "",
                "",
                "",
                "Zé Toi",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                19191919
            ), db
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val query =
            "DROP TABLE IF EXISTS $TABLE_AGENT; " +
                    "DROP TABLE IF EXISTS $TABLE_AGENT_ROLE; " +
                    "DROP TABLE IF EXISTS $TABLE_AGENT_GROUP; " +
                    "DROP TABLE IF EXISTS $TABLE_COMPANY; " +
                    "DROP TABLE IF EXISTS $TABLE_CONTACT; " +
                    "DROP TABLE IF EXISTS $TABLE_CONVERSATION; " +
                    "DROP TABLE IF EXISTS $TABLE_GROUP; " +
                    "DROP TABLE IF EXISTS $TABLE_PRODUCT; " +
                    "DROP TABLE IF EXISTS $TABLE_ROLE; " +
                    "DROP TABLE IF EXISTS $TABLE_SOLUTION; " +
                    "DROP TABLE IF EXISTS $TABLE_TICKET;"
        println(query)
        //execute the query
        db.execSQL(query, null)
        //re create the database
        onCreate(db)
    }

    companion object {
        const val DB_VERSION = 2
        const val DB_NAME = "liderlink.db"
    }
}