package tickets.liderlink.pt

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val fab by lazy { findViewById<FloatingActionButton>(R.id.main_fab) }
    private val fab1 by lazy { findViewById<FrameLayout>(R.id.fab1) }
    private val fab2 by lazy { findViewById<FrameLayout>(R.id.fab2) }
    private val fab3 by lazy { findViewById<FrameLayout>(R.id.fab3) }
    private val bottomNav by lazy { findViewById<BottomNavigationView>(R.id.my_bottom_navigation) }
    private val myNavigationView by lazy { findViewById<NavigationView>(R.id.nav_view) }
    private val fragmentManager by lazy { supportFragmentManager }
    private val contactFragment by lazy { ContactFragment.newInstance() }
    private val ticketFragment by lazy { TicketFragment.newInstance() }
    private val settingsFragment by lazy { SettingsFragment.newInstance() }
    private val newTicketFragment by lazy { FragmentNewTicket.newInstance() }
    private val newContactFragment by lazy { FragmentNewContact.newInstance() }
    private val newCompanyFragment by lazy { FragmentNewCompany.newInstance() }
    private val notificationFragment by lazy { NotificationFragment.newInstance() }
    private var selectedFragmentID = 999
    private var isFABOpen = false

    /**
     * Acts upon orders of the fragment change listeners
     * And sets the desired properties
     * @param item
     * @param title
     * @param selection
     * @sample deployDrawerUpdate
     */
    private fun deployDrawerUpdate(
        item: Int,
        title: String,
        selection: Int
    ) {
        myNavigationView.setCheckedItem(item)
        supportActionBar!!.title = title
        selectedFragmentID = selection
    }

    /**
     * Handles UI changes and compares hash codes to determine the selected fragment
     * @param fragment Receives the newly create fragment to update title and drawer checked item
     * @sample updateTitleAndDrawer
     */
    private fun updateTitleAndDrawer(fragment: Fragment) {
        when (fragment.hashCode()) {
            ticketFragment.hashCode() -> deployDrawerUpdate(R.id.nav_tickets, "Tickets", 0)
            contactFragment.hashCode() -> deployDrawerUpdate(R.id.nav_contacts, "Contacts", 1)
            notificationFragment.hashCode() -> deployDrawerUpdate(R.id.nav_contacts, "Notifications", 3)
            settingsFragment.hashCode() -> deployDrawerUpdate(R.id.nav_contacts, "Settings", 4)
            newCompanyFragment.hashCode() -> deployDrawerUpdate(R.id.nav_contacts, "New Company", 5)
            newContactFragment.hashCode() -> deployDrawerUpdate(R.id.nav_contacts, "New Contact", 6)
            newTicketFragment.hashCode() -> deployDrawerUpdate(R.id.nav_contacts, "New Ticket", 7)
        }
    }

    private fun showFABMenu() {
        isFABOpen = true
        fab1.animate().translationY(-resources.getDimension(R.dimen.standard_125))
        fab1.animate().alpha(1.0F)
        fab2.animate().translationY(-resources.getDimension(R.dimen.standard_175))
        fab2.animate().alpha(1.0F)
        fab3.animate().translationY(-resources.getDimension(R.dimen.standard_225))
        fab3.animate().alpha(1.0F)
    }

    private fun closeFABMenu() {
        isFABOpen = false
        fab1.animate().translationY(0.0F)
        fab1.animate().alpha(0.0F)
        fab2.animate().translationY(0.0F)
        fab2.animate().alpha(0.0F)
        fab3.animate().translationY(0.0F)
        fab3.animate().alpha(0.0F)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        closeFABMenu()

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_tickets -> {
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.MainContent, ticketFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.nav_contacts -> {
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.MainContent, contactFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.nav_notifications -> {
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.MainContent, notificationFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.nav_settings -> {
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.MainContent, settingsFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        fab.setOnClickListener {
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            println(isFABOpen)
            if (isFABOpen) {
                closeFABMenu()
            } else {
                showFABMenu()
            }
            println(isFABOpen)
        }

        fab1.setOnClickListener {
            closeFABMenu()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.MainContent, newTicketFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        fab2.setOnClickListener {
            closeFABMenu()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.MainContent, newContactFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        fab3.setOnClickListener {
            closeFABMenu()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.MainContent, newCompanyFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        supportFragmentManager.addOnBackStackChangedListener {
            val theFragment = supportFragmentManager.findFragmentById(R.id.MainContent)
            when {
                theFragment!!.isVisible -> updateTitleAndDrawer(theFragment)
            }
        }

        if (savedInstanceState == null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.MainContent, ticketFragment)
            transaction.commit()
            updateTitleAndDrawer(ticketFragment)
        } else when (selectedFragmentID) {
            0 -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, ticketFragment)
                transaction.commit()
                nav_view.menu.getItem(0).isChecked = true
            }
            1 -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, contactFragment)
                transaction.commit()
                nav_view.menu.getItem(1).isChecked = true
            }
            3 -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, notificationFragment)
                transaction.commit()
                nav_view.menu.getItem(3).isChecked = true
            }
            4 -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, settingsFragment)
                transaction.commit()
                nav_view.menu.getItem(4).isChecked = true
            }
            5 -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, newCompanyFragment)
                transaction.commit()
                nav_view.menu.getItem(5).isChecked = true
            }
            6 -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, newContactFragment)
                transaction.commit()
                nav_view.menu.getItem(6).isChecked = true
            }
            7 -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, newTicketFragment)
                transaction.commit()
                nav_view.menu.getItem(7).isChecked = true
            }
        }
    }

    override fun onBackPressed() {
        val myBottomNavView = findViewById<BottomNavigationView>(R.id.my_bottom_navigation)
        if (isFABOpen) {
            closeFABMenu()
        } else {
            when {
                drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(
                    GravityCompat.START
                )

                else -> when {
                    supportFragmentManager.backStackEntryCount == 0 -> finish()
                    else -> {
                        super.onBackPressed()
                        val theFragment = supportFragmentManager.findFragmentById(R.id.MainContent)
                        when {
                            theFragment!!.isVisible -> updateTitleAndDrawer(theFragment)
                        }
                        when (theFragment.hashCode()) {
                            ticketFragment.hashCode() -> {
                                val myView = myBottomNavView.findViewById<View>(R.id.nav_tickets)
                                myView.performClick()
                            }
                            contactFragment.hashCode() -> {
                                val myView = myBottomNavView.findViewById<View>(R.id.nav_contacts)
                                myView.performClick()
                            }
                            notificationFragment.hashCode() -> {
                                val myView = myBottomNavView.findViewById<View>(R.id.nav_notifications)
                                myView.performClick()
                            }
                            settingsFragment.hashCode() -> {
                                val myView = myBottomNavView.findViewById<View>(R.id.nav_settings)
                                myView.performClick()
                            }
                        }
                    }
                }
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState!!.putInt("SELECTED", selectedFragmentID)
    }

    /**
     * Acts when restoring the state
     * @param savedInstanceState
     * @sample onRestoreInstanceState
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        selectedFragmentID = savedInstanceState!!.getInt("SELECTED")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onUserInteraction() {

        if (isFABOpen) {
            closeFABMenu()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (isFABOpen) {
            closeFABMenu()
        }
        when (item.itemId) {

            R.id.nav_one -> {
                Snackbar.make(myNavigationView, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            R.id.nav_two -> {
                Snackbar.make(myNavigationView, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            R.id.nav_three -> {
                Snackbar.make(myNavigationView, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            R.id.nav_four -> {
                Snackbar.make(myNavigationView, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
