package com.example.data.repository

import com.example.data.local.QuestDao
import com.example.data.local.QuestEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.util.UUID

class QuestRepository(private val questDao: QuestDao) {

  val allQuests: Flow<List<QuestEntity>> = questDao.getAllQuests()
  val totalQuestCount: Flow<Int> = questDao.getQuestCount()
  val completedQuestCount: Flow<Int> = questDao.getCompletedQuestCount()

  suspend fun initializeDefaultsIfNeeded() {
    val existing = questDao.getAllQuests().first()
    if (existing.isEmpty()) {
      val defaultQuests = listOf(
        QuestEntity(
          id = "quest_1",
          title = "Drink 500ml cold water",
          category = "Hydration",
          categoryEmoji = "💧",
          xp = 50,
          cadence = "daily",
          isCompleted = true,
          isExtraSpark = false,
          subText = "Drink 500ml cold water",
          isLowSpoon = false
        ),
        QuestEntity(
          id = "quest_2",
          title = "PM Skincare (Cleanser + Cream)",
          category = "Skincare",
          categoryEmoji = "✨",
          xp = 75,
          cadence = "daily",
          isCompleted = true,
          isExtraSpark = false,
          subText = "PM Skincare (Cleanser + Cream)",
          isLowSpoon = false
        ),
        QuestEntity(
          id = "quest_3",
          title = "10-minute sunshine walk",
          category = "Fitness & Body",
          categoryEmoji = "🏃",
          xp = 100,
          cadence = "daily",
          isCompleted = true,
          isExtraSpark = false,
          subText = "10-minute sunshine walk",
          isLowSpoon = false
        ),
        QuestEntity(
          id = "quest_4",
          title = "Quick text or call to Mom",
          category = "Family & Social",
          categoryEmoji = "📞",
          xp = 120,
          cadence = "daily",
          isCompleted = false,
          isExtraSpark = true,
          subText = "Escalating chime in 20m if paused",
          isLowSpoon = true
        )
      )
      questDao.insertQuests(defaultQuests)
    }
  }

  suspend fun insertQuest(
    title: String,
    category: String,
    categoryEmoji: String,
    xp: Int,
    cadence: String,
    isLowSpoon: Boolean = false,
    subText: String = ""
  ): QuestEntity {
    val quest = QuestEntity(
      id = UUID.randomUUID().toString(),
      title = title,
      category = category,
      categoryEmoji = categoryEmoji,
      xp = xp,
      cadence = cadence,
      isCompleted = false,
      isExtraSpark = xp >= 100,
      subText = subText.ifBlank { "Gentle reminder queued" },
      isLowSpoon = isLowSpoon
    )
    questDao.insertQuest(quest)
    return quest
  }

  suspend fun toggleQuestCompletion(quest: QuestEntity): Boolean {
    val nextState = !quest.isCompleted
    questDao.setQuestCompleted(
      id = quest.id,
      completed = nextState,
      completedAt = if (nextState) System.currentTimeMillis() else null
    )
    return nextState
  }

  suspend fun deleteQuest(quest: QuestEntity) {
    questDao.deleteQuest(quest)
  }

  suspend fun resetAllToDefaults() {
    questDao.deleteAll()
    initializeDefaultsIfNeeded()
  }
}
