package tickets.liderlink.pt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_settings.view.*

class AdapterSettings(private val myDataset: List<SimpleSetting>) :
    RecyclerView.Adapter<AdapterSettings.MyViewHolder>() {

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterSettings.MyViewHolder {
        // create a new view
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_settings, parent, false) as CardView
        return MyViewHolder(cardView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.cardView.setting_action.text = myDataset[position].name
        holder.cardView.setting_action_icon.setImageDrawable(
            ContextCompat.getDrawable(
                holder.cardView.context,
                myDataset[position].icon
            )
        )
        holder.cardView.setting_action.setTextColor(myDataset[position].color)
    }


    override fun getItemCount() = myDataset.size
}