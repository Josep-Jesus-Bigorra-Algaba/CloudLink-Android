package tickets.liderlink.pt

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler_only.*

data class SimpleSetting(val name: String, val icon: Int, val color: Int)

class SettingsFragment : Fragment() {

    private val settingTest = listOf(
        SimpleSetting("My Profile", R.drawable.ic_contact, Color.BLACK),
        SimpleSetting("Ticket Notifications", R.drawable.ic_ticket, Color.BLACK),
        SimpleSetting("Write a review", R.drawable.ic_menu_share, Color.BLACK),
        SimpleSetting("Help", R.drawable.ic_contact, Color.BLACK),
        SimpleSetting("About this app", R.drawable.ic_menu_slideshow, Color.BLACK),
        SimpleSetting("Sign out", R.drawable.ic_menu_share, Color.RED)
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
            adapter = AdapterSettings(settingTest)
        }
    }

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}



