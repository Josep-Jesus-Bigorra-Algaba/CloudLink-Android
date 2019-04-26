package tickets.liderlink.pt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler_only.*
import tickets.data.classes.TicketController

data class SimpleTicket(
    val name: String,
    val subject: String,
    val ID: String,
    val timeStatus: String,
    val responseDue: String,
    val priority: Int,
    val agent: String,
    val status: String
)

class TicketFragment : Fragment() {

    private var ticketTest: ArrayList<SimpleTicket> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        val dbHandler = DatabaseHandler(this.context!!)
        val myTicket = TicketController().findTicket("2828", dbHandler.readableDatabase)

        if (myTicket != null) {
            println("Found a match in the database")
            ticketTest.add(
                SimpleTicket(
                    myTicket.name,
                    myTicket.subject,
                    myTicket.id.toString(),
                    myTicket.fr_due_by,
                    myTicket.due_by,
                    myTicket.priority,
                    myTicket.agent_id.toString(),
                    myTicket.status
                )
            )
        } else {
            println("No match found in the database")
        }
        val myTicket2 = TicketController().findTicket("2829", dbHandler.readableDatabase)

        if (myTicket2 != null) {
            println("Found a match in the database")
            ticketTest.add(
                SimpleTicket(
                    myTicket2.name,
                    myTicket2.subject,
                    myTicket2.id.toString(),
                    myTicket2.fr_due_by,
                    myTicket2.due_by,
                    myTicket2.priority.toInt(),
                    myTicket2.agent_id.toString(),
                    myTicket2.status
                )
            )
        } else {
            println("No match found in the database")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recycler_only, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = AdapterTickets(ticketTest.toList())
        }
    }

    companion object {
        fun newInstance(): TicketFragment = TicketFragment()
    }
}



