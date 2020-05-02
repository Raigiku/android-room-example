package pe.edu.upc.agendaroom

import androidx.room.*

@Dao
interface ContactDao {

    @Query("SELECT * FROM Contact")
    fun getAll(): MutableList<Contact>

    @Insert
    fun insertContact(vararg contacts: Contact)

    @Delete
    fun deleteContact(vararg contacts: Contact)

    @Update
    fun updateContact(vararg contacts: Contact)
}