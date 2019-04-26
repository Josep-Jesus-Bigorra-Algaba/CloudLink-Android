package tickets.liderlink.pt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler_only.*
import tickets.data.classes.ContactController


data class SimpleContact
    (
    val ID: String,
    val name: String,
    val company: String
)

class ContactFragment : Fragment() {

    private var contactTest: ArrayList<SimpleContact> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        val dbHandler = DatabaseHandler(this.context!!)
        val myContact = ContactController().findContact("Zé Toi", dbHandler.readableDatabase)
        //val myContact = URL("").readText()
        if (myContact != null) {
            println("Found a match in the database")
            contactTest.add(
                SimpleContact(
                    myContact.id.toString(),
                    myContact.name,
                    myContact.company_id.toString()
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
            adapter = AdapterContact(contactTest)
        }
    }

    companion object {
        fun newInstance(): ContactFragment = ContactFragment()
    }
}



