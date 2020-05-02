package pe.edu.upc.agendaroom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var contacts: MutableList<Contact>
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadContacts()

        contactAdapter = ContactAdapter(contacts, this)
        rvContact.adapter = contactAdapter
        rvContact.layoutManager = LinearLayoutManager(this)
    }

    private fun loadContacts() {
        contacts = AppDatabase.getInstance(this).getDao().getAll()
    }

    override fun onItemClicked(contact: Contact) {
        val intent = Intent(this, ContactActivity::class.java)
        val gson = Gson()
        intent.putExtra("keyContact", gson.toJson(contact))
        startActivity(intent)
    }
}
