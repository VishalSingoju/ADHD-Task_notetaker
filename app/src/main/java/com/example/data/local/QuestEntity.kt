package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quests")
data class QuestEntity(
  @PrimaryKey val id: String,
  val title: String,
  val category: String,
  val categoryEmoji: String,
  val xp: Int,
  val cadence: String = "daily", // daily, weekly, onetime
  val isCompleted: Boolean = false,
  val isExtraSpark: Boolean = false,
  val subText: String = "",
  val isLowSpoon: Boolean = false,
  val createdAt: Long = System.currentTimeMillis(),
  val completedAt: Long? = null
)
