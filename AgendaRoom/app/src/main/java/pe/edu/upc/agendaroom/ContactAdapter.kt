package pe.edu.upc.agendaroom

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prototype_contact.view.*

class ContactAdapter(
    private val contacts: MutableList<Contact>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ContactPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactPrototype {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_contact, parent, false)
        return ContactPrototype(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(prototype: ContactPrototype, position: Int) {
        prototype.bind(
            contacts[position],
            itemClickListener,
            DeleteButtonListener(contacts, this, position)
        )
    }

}

class ContactPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvName = itemView.tvName
    private val tvTelephone = itemView.tvTelephone
    private val cvContact = itemView.cvContact
    private val btDelete = itemView.btDelete


    fun bind(
        contact: Contact,
        itemClickListener: OnItemClickListener,
        deleteButtonListener: DeleteButtonListener
    ) {
        tvName.text = contact.name
        tvTelephone.text = contact.telephone

        cvContact.setOnClickListener {
            itemClickListener.onItemClicked(contact)
        }

        btDelete.setOnClickListener(deleteButtonListener)
    }
}

interface OnItemClickListener {
    fun onItemClicked(contact: Contact)
}
