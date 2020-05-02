package pe.edu.upc.agendaroom

import android.view.View
import android.widget.Toast

class DeleteButtonListener(
    private val adapter: ContactAdapter,
    private val contacts: MutableList<Contact>,
    private val removeId: Int
) : View.OnClickListener {

    override fun onClick(v: View?) {
        if (v != null) {
            val removeIndex = contacts.indexOfFirst { it.id == removeId }
            AppDatabase.getInstance(v.context).getDao().deleteContact(contacts[removeIndex])
            contacts.removeAt(removeIndex)
            adapter.notifyItemRemoved(removeIndex)
            Toast.makeText(v.context, "bruh", Toast.LENGTH_SHORT).show()
        }
    }
}