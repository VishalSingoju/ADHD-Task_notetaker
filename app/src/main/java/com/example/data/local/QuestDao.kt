package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestDao {
  @Query("SELECT * FROM quests ORDER BY isCompleted ASC, createdAt DESC")
  fun getAllQuests(): Flow<List<QuestEntity>>

  @Query("SELECT COUNT(*) FROM quests")
  fun getQuestCount(): Flow<Int>

  @Query("SELECT COUNT(*) FROM quests WHERE isCompleted = 1")
  fun getCompletedQuestCount(): Flow<Int>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertQuest(quest: QuestEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertQuests(quests: List<QuestEntity>)

  @Update
  suspend fun updateQuest(quest: QuestEntity)

  @Delete
  suspend fun deleteQuest(quest: QuestEntity)

  @Query("UPDATE quests SET isCompleted = :completed, completedAt = :completedAt WHERE id = :id")
  suspend fun setQuestCompleted(id: String, completed: Boolean, completedAt: Long?)

  @Query("DELETE FROM quests")
  suspend fun deleteAll()
}
