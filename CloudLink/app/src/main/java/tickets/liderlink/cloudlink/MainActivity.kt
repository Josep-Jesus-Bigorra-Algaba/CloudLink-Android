package tickets.liderlink.cloudlink

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val myNavigationView by lazy { findViewById<NavigationView>(R.id.nav_view) }
    private val fragmentManager by lazy { supportFragmentManager }
    private val homeFragment by lazy { HomeFragment() }
    private val ticketFragment by lazy { TicketFragment.newInstance() }
    private var selectedFragmentID = 999

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
            homeFragment.hashCode() -> deployDrawerUpdate(R.id.nav_dashboard, "CloudLink Dashboard :)", 0)
            ticketFragment.hashCode() -> deployDrawerUpdate(R.id.nav_tickets, "View Tickets", 1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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
            transaction.replace(R.id.MainContent, homeFragment)
            transaction.commit()
            updateTitleAndDrawer(homeFragment)
        }
        else if (savedInstanceState != null) when (selectedFragmentID) {
            0 -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, homeFragment)
                transaction.commit()
                nav_view.menu.getItem(0).isChecked = true
            }
            1 -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, ticketFragment)
                transaction.commit()
                nav_view.menu.getItem(1).isChecked = true
            }
        }
    }

    /**
     * An override of the BackPressed method, using the backstack, with custom options
     * @sample onBackPressed
     */
    override fun onBackPressed() {
        when {
            drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(
                GravityCompat.START
            )
            else -> when {
                supportFragmentManager.backStackEntryCount == 1 -> finish()
                else -> super.onBackPressed()
            }
        }
    }

    /**
     * Acts when saving the state
     * @param outState
     * @sample onSaveInstanceState
     */
    override fun onSaveInstanceState(outState: Bundle?) {
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
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_dashboard -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, homeFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            R.id.nav_tickets -> {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.MainContent, ticketFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            R.id.nav_contacts -> {

            }
            R.id.nav_companies -> {

            }
            R.id.nav_agents -> {

            }
            R.id.nav_account -> {

            }
            R.id.nav_settings -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
