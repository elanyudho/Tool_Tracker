package com.elanyudho.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.Tool
import kotlinx.coroutines.flow.Flow

@Dao
interface ToolDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFriend(friend: Friend)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFriend(friend: Friend)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTools(tool: Tool)

    @Transaction
    @Query("SELECT * from tool ORDER BY tool ASC")
    fun getAllTools(): Flow<List<Tool>> // Return Flow<List<Tool>>

    @Transaction
    @Query("SELECT * from friend ORDER BY name ASC")
    fun getAllFriends(): Flow<List<Friend>> // Return Flow<List<Friend>>

    @Transaction
    @Query("SELECT * FROM friend WHERE id = :friendId")
    fun getDetailFriend(friendId: Int): Flow<Friend> // Return Flow<Friend>

    @Transaction
    @Query("UPDATE tool SET borrowedTool = :borrowedQuantity + borrowedTool WHERE id = :toolId")
    suspend fun addBorrowedToolQuantity(toolId: Int, borrowedQuantity: Int)

    @Transaction
    @Query("UPDATE tool SET borrowedTool = borrowedTool - :returnedQuantity  WHERE id = :toolId")
    suspend fun substractBorrowedToolQuantity(toolId: Int, returnedQuantity: Int)

    @Transaction
    @Query("UPDATE tool SET availableTool = totalTool - borrowedTool  WHERE id = :toolId")
    suspend fun updateAvailableToolQuantity(toolId: Int)
}