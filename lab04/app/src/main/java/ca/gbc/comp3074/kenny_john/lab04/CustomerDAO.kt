package ca.gbc.comp3074.kenny_john.lab04

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(customer: Customer)

    @Delete
    suspend fun delete(customer:Customer)

    @Query("SELECT * FROM customers order by id")
    fun getAll(): Flow<List<Customer>>

    @Query("SELECT * FROM customers "+
            "WHERE name LIKE '%'||:name||'%'"+
            " order by name")
    fun getByName(name: String): Flow<List<Customer>>
}