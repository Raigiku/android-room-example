package pe.edu.upc.agendaroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {

    lateinit var contact: Contact
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        loadContact()
        fabDone.setOnClickListener {
            saveContact()
            finish()
        }
    }

    private fun saveContact() {
        contact.name = etName.text.toString()
        contact.telephone = etTelephone.text.toString()

        if (contact.id != null) {
            AppDatabase.getInstance(this).getDao().updateContact(contact)


        } else {
            AppDatabase.getInstance(this).getDao().insertContact(contact)

        }

    }

    private fun loadContact() {
        val gson = Gson()
        val stringObj = intent.getStringExtra("keyContact")

        contact = gson.fromJson(stringObj, Contact::class.java) ?: Contact(null, "", "")

        if (contact.id != null) {
            etName.setText(contact.name)
            etTelephone.setText(contact.telephone)
        }
    }

}
