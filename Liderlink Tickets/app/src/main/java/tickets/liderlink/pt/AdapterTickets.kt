package tickets.liderlink.pt

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_ticket.view.*

class AdapterTickets(private val myDataset: List<SimpleTicket>) :
    RecyclerView.Adapter<AdapterTickets.MyViewHolder>() {

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterTickets.MyViewHolder {

        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_ticket, parent, false) as CardView
        return MyViewHolder(cardView)
    }

    private fun decidePriorityColor(level: Int): Int {
        return when (level) {
            1 -> Color.RED
            2 -> Color.YELLOW
            3 -> Color.GREEN
            else -> Color.BLACK
        }
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.cardView.ticket_name_field.text = myDataset[position].name
        holder.cardView.ticket_subject_field.text = myDataset[position].subject
        holder.cardView.ticket_id_field.text = "#" + myDataset[position].ID
        holder.cardView.ticket_timestatus_field.text = myDataset[position].timeStatus
        holder.cardView.ticket_responsedue_field.text = myDataset[position].responseDue
        holder.cardView.status_indicator.setBackgroundColor(decidePriorityColor(myDataset[position].priority))
        holder.cardView.ticket_agent_field.text = myDataset[position].agent
        holder.cardView.ticket_status_field.text = myDataset[position].status
    }


    override fun getItemCount() = myDataset.size
}