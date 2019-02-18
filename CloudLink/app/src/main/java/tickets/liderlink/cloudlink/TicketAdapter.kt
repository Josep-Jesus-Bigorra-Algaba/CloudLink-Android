package tickets.liderlink.cloudlink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ticket_card.view.*

class TicketAdapter(private val myDataset: List<Ticket>) :
    RecyclerView.Adapter<TicketAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TicketAdapter.MyViewHolder {
        // create a new view
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticket_card, parent, false) as CardView
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(cardView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cardView.ticket_id_field.text = myDataset[position].ID.toString()
        holder.cardView.ticket_subject_field.text = myDataset[position].from.toString()
        holder.cardView.ticket_from_field.text = myDataset[position].subject.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}