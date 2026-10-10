package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.QuestEntity
import com.example.data.model.BadgeItem
import com.example.data.model.HapticIntensity
import com.example.data.model.SoundAesthetic
import com.example.data.model.SpoonState
import com.example.data.model.UserProfile
import com.example.data.repository.QuestRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed class NavigationTab {
  object Quests : NavigationTab()
  object NewQuest : NavigationTab()
  object Trophy : NavigationTab()
  object Sync : NavigationTab()
  object Profile : NavigationTab()
}

data class TinyStep(val emoji: String, val text: String)

class QuestViewModel(application: Application) : AndroidViewModel(application) {

  private val database = AppDatabase.getDatabase(application)
  private val repository = QuestRepository(database.questDao())

  val quests: StateFlow<List<QuestEntity>> = repository.allQuests
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

  private val _currentTab = MutableStateFlow<NavigationTab>(NavigationTab.Quests)
  val currentTab: StateFlow<NavigationTab> = _currentTab.asStateFlow()

  private val _previousTab = MutableStateFlow<NavigationTab>(NavigationTab.Quests)

  private val _selectedFilter = MutableStateFlow("All Quests")
  val selectedFilter: StateFlow<String> = _selectedFilter.asStateFlow()

  private val _userProfile = MutableStateFlow(UserProfile())
  val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

  // New Quest Form State
  private val _questTitle = MutableStateFlow("✨ PM Skincare")
  val questTitle: StateFlow<String> = _questTitle.asStateFlow()

  private val _questXp = MutableStateFlow(75)
  val questXp: StateFlow<Int> = _questXp.asStateFlow()

  private val _questCadence = MutableStateFlow("daily") // "daily", "weekly", "onetime"
  val questCadence: StateFlow<String> = _questCadence.asStateFlow()

  private val _escalatingAlerts = MutableStateFlow(true)
  val escalatingAlerts: StateFlow<Boolean> = _escalatingAlerts.asStateFlow()

  private val _urgencyLevel = MutableStateFlow("persistent") // "gentle", "persistent", "emergency"
  val urgencyLevel: StateFlow<String> = _urgencyLevel.asStateFlow()

  private val _soundAesthetic = MutableStateFlow("harp") // "chime", "harp", "lofi"
  val soundAesthetic: StateFlow<String> = _soundAesthetic.asStateFlow()

  // Celebration & Toast Event
  private val _toastMessage = MutableSharedFlow<String>()
  val toastMessage: SharedFlow<String> = _toastMessage.asSharedFlow()

  private val _showDopamineCelebration = MutableStateFlow(false)
  val showDopamineCelebration: StateFlow<Boolean> = _showDopamineCelebration.asStateFlow()

  // Roll Tiny Step State
  private val tinySteps = listOf(
    TinyStep("📱", "Just unlock your phone and type \"Hey Mom, thinking of you!\" without sending yet."),
    TinyStep("⏱️", "Put on a 2-minute timer and just open the text thread. When timer rings, you can stop."),
    TinyStep("🎙️", "Send a quick 5-second voice note saying you love her and will catch up soon."),
    TinyStep("📸", "Send a funny photo of your desk or a pet with zero caption required."),
    TinyStep("🧘", "Take one deep exhale, unclench your jaw, and tap Mom's contact icon."),
    TinyStep("🧦", "Put 1 pair of socks in the laundry hamper. That's the whole quest."),
    TinyStep("🪴", "Give one house plant a splash of water and listen to the soil soak it in.")
  )

  private val _currentTinyStep = MutableStateFlow(
    TinyStep("💡", "Pick a 2-minute micro-action to bypass brain friction.")
  )
  val currentTinyStep: StateFlow<TinyStep> = _currentTinyStep.asStateFlow()

  // Hall of Sparks Badges
  val badges = listOf(
    BadgeItem(
      id = "b1",
      title = "3-Day Spark",
      description = "Completed first 3 days without zero days.",
      rarity = "COMMON",
      emoji = "🔥",
      isUnlocked = true
    ),
    BadgeItem(
      id = "b2",
      title = "7-Day Nova",
      description = "Sustained 7 days of dopamine micro-habits.",
      rarity = "RARE",
      emoji = "⚡",
      isUnlocked = true
    ),
    BadgeItem(
      id = "b3",
      title = "Month Titan",
      description = "30-day consistency.",
      rarity = "EPIC",
      emoji = "👑",
      isUnlocked = false,
      progressText = "18 / 30 Days",
      progressFraction = 0.6f
    ),
    BadgeItem(
      id = "b4",
      title = "Shield Master",
      description = "Safely used a freeze shield without giving up.",
      rarity = "RARE",
      emoji = "🛡️",
      isUnlocked = true
    ),
    BadgeItem(
      id = "b5",
      title = "Hydro Hero",
      description = "Drank fresh water 10 days in a row.",
      rarity = "COMMON",
      emoji = "💧",
      isUnlocked = true
    ),
    BadgeItem(
      id = "b6",
      title = "100-Day Odyssey",
      description = "Master of effortless automated routine loops.",
      rarity = "LEGEND",
      emoji = "🌌",
      isUnlocked = false,
      progressText = "12 / 100 Days",
      progressFraction = 0.12f
    )
  )

  init {
    viewModelScope.launch {
      repository.initializeDefaultsIfNeeded()
    }
  }

  fun setTab(tab: NavigationTab) {
    if (_currentTab.value != NavigationTab.Profile && tab == NavigationTab.Profile) {
      _previousTab.value = _currentTab.value
    }
    _currentTab.value = tab
  }

  fun navigateBackFromProfile() {
    _currentTab.value = _previousTab.value
  }

  fun setFilter(filter: String) {
    _selectedFilter.value = filter
  }

  fun onQuestTitleChange(newTitle: String) {
    _questTitle.value = newTitle
  }

  fun setQuestCadence(cadence: String) {
    _questCadence.value = cadence
  }

  fun setAlertUrgency(level: String) {
    _urgencyLevel.value = level
  }

  fun setSoundAesthetic(sound: String) {
    _soundAesthetic.value = sound
  }

  fun toggleEscalatingAlerts() {
    _escalatingAlerts.update { !it }
  }

  fun applyPreset(title: String, xp: Int, cadence: String = "daily") {
    _questTitle.value = title
    _questXp.value = xp
    _questCadence.value = cadence
    triggerHaptic()
  }

  fun rollTinyStep() {
    val nextStep = tinySteps.filter { it != _currentTinyStep.value }.random()
    _currentTinyStep.value = nextStep
    triggerHaptic()
  }

  fun surpriseLowSpoonPrompt() {
    val surpriseItems = listOf(
      Pair("🧦 Put 1 pair of socks in laundry", 50),
      Pair("🪴 Give plant a splash of water", 50),
      Pair("🪟 Open curtains for natural sunlight", 40),
      Pair("🧘 Take 3 intentional deep breaths", 50),
      Pair("🗑️ Throw away 2 pieces of trash", 60),
      Pair("📱 Unsubscribe from 1 junk email", 50),
      Pair("💧 Drink a cold glass of lemon water", 50)
    )
    val randomItem = surpriseItems.random()
    _questTitle.value = randomItem.first
    _questXp.value = randomItem.second
    triggerHaptic()
  }

  fun toggleQuest(quest: QuestEntity) {
    viewModelScope.launch {
      val nowCompleted = repository.toggleQuestCompletion(quest)
      if (nowCompleted) {
        _userProfile.update {
          it.copy(
            currentXp = it.currentXp + quest.xp,
            todayXpGained = it.todayXpGained + quest.xp
          )
        }
        triggerCelebration(quest.xp)
      } else {
        _userProfile.update {
          it.copy(
            currentXp = maxOf(0, it.currentXp - quest.xp),
            todayXpGained = maxOf(0, it.todayXpGained - quest.xp)
          )
        }
      }
      triggerHaptic()
    }
  }

  fun forgeCurrentQuest() {
    val title = _questTitle.value.trim()
    if (title.isBlank()) return

    viewModelScope.launch {
      val category = when {
        title.contains("water", ignoreCase = true) || title.contains("hydration", ignoreCase = true) -> "Hydration"
        title.contains("skin", ignoreCase = true) -> "Skincare"
        title.contains("walk", ignoreCase = true) || title.contains("run", ignoreCase = true) -> "Movement"
        title.contains("call", ignoreCase = true) || title.contains("text", ignoreCase = true) -> "Connection"
        title.contains("med", ignoreCase = true) || title.contains("pill", ignoreCase = true) -> "Health"
        title.contains("tidy", ignoreCase = true) || title.contains("clean", ignoreCase = true) -> "Low Energy"
        else -> "Daily Orbit"
      }
      val emoji = when {
        title.contains("💧") -> "💧"
        title.contains("✨") -> "✨"
        title.contains("📞") -> "📞"
        title.contains("👟") -> "👟"
        title.contains("💊") -> "💊"
        title.contains("🧹") -> "🧹"
        else -> "⚡"
      }

      repository.insertQuest(
        title = title,
        category = category,
        categoryEmoji = emoji,
        xp = _questXp.value,
        cadence = _questCadence.value,
        isLowSpoon = _questXp.value <= 60,
        subText = if (_escalatingAlerts.value) "Escalating alert: ${_urgencyLevel.value.capitalize(Locale.ROOT)}" else "Gentle pace"
      )

      _toastMessage.emit("Quest Activated! +${_questXp.value} XP Queued for completion")
      triggerHaptic()
      _currentTab.value = NavigationTab.Quests
    }
  }

  fun bankInBrainDump() {
    viewModelScope.launch {
      val title = _questTitle.value.trim()
      if (title.isNotBlank()) {
        repository.insertQuest(
          title = title,
          category = "Brain Dump",
          categoryEmoji = "📥",
          xp = 40,
          cadence = "onetime",
          isLowSpoon = true,
          subText = "Banked with zero pressure"
        )
      }
      _toastMessage.emit("Saved to Brain Dump — No due date, zero guilt!")
      triggerHaptic()
      _currentTab.value = NavigationTab.Quests
    }
  }

  fun triggerCelebration(xpAmount: Int = 120) {
    viewModelScope.launch {
      _showDopamineCelebration.value = true
      delay(2600)
      _showDopamineCelebration.value = false
    }
  }

  fun updateSpoonState(state: SpoonState) {
    _userProfile.update { it.copy(spoonState = state) }
    triggerHaptic()
  }

  fun updateCompassionNote(note: String) {
    _userProfile.update { it.copy(compassionNote = note) }
    triggerHaptic()
  }

  fun toggleSettingInstantBackup() {
    _userProfile.update { it.copy(instantBackup = !it.instantBackup) }
  }

  fun toggleSettingNudges() {
    _userProfile.update { it.copy(gentleNudges = !it.gentleNudges) }
  }

  fun toggleSettingAdhdSafeClear() {
    _userProfile.update { it.copy(adhdSafeClear = !it.adhdSafeClear) }
  }

  fun setHapticFeedback(intensity: HapticIntensity) {
    _userProfile.update { it.copy(hapticFeedback = intensity) }
    triggerHaptic(force = true)
  }

  fun setSoundSetting(sound: SoundAesthetic) {
    _userProfile.update { it.copy(soundAesthetic = sound) }
  }

  fun syncLocalVault() {
    viewModelScope.launch {
      triggerHaptic()
      delay(400)
      val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
      val timeStr = timeFormat.format(Date())
      _userProfile.update {
        it.copy(lastLocalSnapshot = "Today, $timeStr (just now)")
      }
      _toastMessage.emit("Vault synced cleanly in 4ms. No network used.")
    }
  }

  fun simulateMissedDay() {
    viewModelScope.launch {
      triggerHaptic()
      _toastMessage.emit("🛡️ Zero-Guilt Auto-Shield burned: 12-day streak protected!")
    }
  }

  fun triggerHaptic(force: Boolean = false) {
    val intensity = _userProfile.value.hapticFeedback
    if (!force && intensity == HapticIntensity.OFF) return

    try {
      val context = getApplication<Application>()
      val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        manager?.defaultVibrator
      } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
      }

      val duration = when (intensity) {
        HapticIntensity.SUBTLE -> 15L
        HapticIntensity.SOFT -> 35L
        HapticIntensity.OFF -> if (force) 20L else 0L
      }

      if (duration > 0 && vibrator?.hasVibrator() == true) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
          @Suppress("DEPRECATION")
          vibrator.vibrate(duration)
        }
      }
    } catch (_: Exception) {
      // Gracefully ignore if vibrator not available
    }
  }
}
