package com.example.lab4todolist

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.concurrent.LinkedBlockingQueue

//legacy
data class ToDoTask(var status: Boolean, var task: String, var info: String)

@Entity
data class TaskDB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "task") val task: String?,
    @ColumnInfo(name = "info") val info: String?,
    @ColumnInfo(name = "status") var status: Boolean?
)

@Dao
interface TaskDBDao {
    @Query("SELECT * FROM taskdb")
    fun getAll(): List<TaskDB>

    @Insert
    suspend fun insertAll(vararg notes: TaskDB)

    @Delete
    suspend fun delete(note: TaskDB)

    @Update
    suspend fun update(note: TaskDB)

    @Insert
    suspend fun insert(note: TaskDB)

}


@Database(entities = [TaskDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDBDao

}

class AppRepository(private val noteDao: TaskDBDao) {
    fun getAll(): List<TaskDB> {
        var res:List<TaskDB> = listOf();
        /*CoroutineScope(Dispatchers.IO).launch{
            res = noteDao.getAll()
        }
        return res*/
        val hren = LinkedBlockingQueue<List<TaskDB>>()
        Thread {
            hren.add(noteDao.getAll())
        }.start()
        res = hren.take()
        return res
    }

    suspend fun insert(note: TaskDB) {
        withContext(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }

    suspend fun update(note: TaskDB) {
        withContext(Dispatchers.IO) {
            noteDao.update(note)
        }
    }
    suspend fun delete(note: TaskDB) {
        withContext(Dispatchers.IO) {
            noteDao.delete(note)
        }
    }

}