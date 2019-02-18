package tickets.liderlink.cloudlink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.ticket_fragment.*

data class Ticket(val ID: Int, val subject: String, val from: String)

class TicketFragment : Fragment() {

    private val ticketTest = listOf(
        Ticket(0, "Testing the subject", "example@gmail.com"),
        Ticket(1, "Testing the subject", "example@gmail.com"),
        Ticket(2, "Testing the subject", "example@gmail.com")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ticket_fragment, container, false)
    }
    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        ticket_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = TicketAdapter(ticketTest)
        }
    }

    companion object {
        fun newInstance(): TicketFragment = TicketFragment()
    }
}



