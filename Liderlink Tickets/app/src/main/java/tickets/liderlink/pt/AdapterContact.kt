package tickets.liderlink.pt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_contact.view.*

class AdapterContact(private val myDataset: List<SimpleContact>) :
    RecyclerView.Adapter<AdapterContact.MyViewHolder>() {

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterContact.MyViewHolder {

        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_contact, parent, false) as CardView
        return MyViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cardView.contact_name_field.text = myDataset[position].name
        holder.cardView.contact_company_field.text = myDataset[position].company
    }

    override fun getItemCount() = myDataset.size
}