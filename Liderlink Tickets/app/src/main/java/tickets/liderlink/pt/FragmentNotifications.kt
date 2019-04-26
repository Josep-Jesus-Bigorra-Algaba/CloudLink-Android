package tickets.liderlink.pt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler_only.*

data class SimpleNotification(val type: String, val description: String, val time: String)

class NotificationFragment : Fragment() {

    private val notTest = listOf(
        SimpleNotification(
            "Assigned to you",
            "system assigned the ticket #2864 Criação de Utilizador Daniela na Máquina virtual to you",
            "5 hours ago"
        ),
        SimpleNotification(
            "Status updated",
            "system updated the ticket status of #2824 Instalação de Office - Recep2 to Open",
            "22 hours ago"
        ),
        SimpleNotification(
            "Customer responded",
            "Nuno Leandro | Jupiter Lisboa Hotel responded to ticket #2824 Instalação de Office - Recep2",
            "22 hours ago"
        ),
        SimpleNotification("Ticket created", "Carlos Sousa created a new ticket #2866 Reset Password", "a day ago")
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

        return inflater.inflate(R.layout.fragment_recycler_only, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = AdapterNotificationList(notTest)
        }
    }

    companion object {
        fun newInstance(): NotificationFragment = NotificationFragment()
    }
}



