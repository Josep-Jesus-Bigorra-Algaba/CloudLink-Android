package tickets.liderlink.pt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_notification.view.*

class AdapterNotificationList(private val myDataset: List<SimpleNotification>) :
    RecyclerView.Adapter<AdapterNotificationList.MyViewHolder>() {

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterNotificationList.MyViewHolder {

        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_notification, parent, false) as CardView
        return MyViewHolder(cardView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.cardView.notification_type_field.text = myDataset[position].type
        holder.cardView.notification_desc_field.text = myDataset[position].description
        holder.cardView.notification_time_field.text = myDataset[position].time
    }

    override fun getItemCount() = myDataset.size
}