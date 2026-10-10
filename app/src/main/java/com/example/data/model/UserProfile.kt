package com.example.data.model

data class UserProfile(
  val name: String = "Robin V.",
  val handle: String = "@spark_robin",
  val role: String = "Spark Ranger",
  val level: Int = 14,
  val nextLevelRole: String = "Orbit Navigator",
  val currentXp: Int = 1450,
  val nextLevelXp: Int = 2000,
  val todayXpGained: Int = 225,
  val streakDays: Int = 12,
  val activeShields: Int = 2,
  val shieldRechargeDaysLogged: Int = 2,
  val shieldRechargeTotal: Int = 3,
  val spoonState: SpoonState = SpoonState.BALANCED,
  val compassionNote: String = "When stuck, doing 1% still counts as winning. Drinking water or putting one sock away is enough today. The spark will come back.",
  val avatarUrl: String = "https://lh3.googleusercontent.com/aida-public/AB6AXuD24yLAiw1sjz5hM2KRuhpo_Objo4hNUFYaQJmO0zIatx866h0ZvVargox9Edr3d7JQKceLu7weoZ897qnvoJTkDsvTY2ZbE5UdSVi2mb3pJEyZ6RrJSU6Y8NGE8rqhFANY3M65w3tvzGZn_nZsuv_lUUIJltHAOKvvEKdNL_r14fWt8xzfR4qlt7pJYU0qRRXldSl3uZbWvDOSLOn2F2umPXBIJQoNys4R1zrdI5f2qpgBGVScrwW4tA",
  val chestTipImageUrl: String = "https://lh3.googleusercontent.com/aida-public/AB6AXuAkecnSubPmHPrgZhUrOUkfaJfVKJXC989ambvrz8ClZ0dGSE59nvo9ZE5ROrMi1Vn4lzDGUOfXBVrIYCgWGxJ2F1h1Aou4nLmV97RpCQq_jUV1iaZy1mvNQr_SXdbKeR2Gn1QZSs8uBQc9dSjEUBUvDPSOgsO7-pEkJfonkLkM5rHyPvSRdPLQFNp6oGcxhsDgcqJxxqlqGHZAkPbbHrxLmEbIi89Pdaj3NYA63gT9hPN37RONOjoiOw",
  // Settings
  val instantBackup: Boolean = true,
  val gentleNudges: Boolean = true,
  val adhdSafeClear: Boolean = true,
  val soundAesthetic: SoundAesthetic = SoundAesthetic.CHIME,
  val hapticFeedback: HapticIntensity = HapticIntensity.SUBTLE,
  val lastLocalSnapshot: String = "Today, 09:41 AM (auto)"
)

enum class SpoonState(val title: String, val subtitle: String, val emoji: String) {
  LOW("Low Spoon", "1-2 Mini tasks", "🥄"),
  BALANCED("Balanced", "Gentle cadence", "🍵"),
  HIGH("Hyperfocus", "Ready to flow", "⚡")
}

enum class SoundAesthetic(val label: String) {
  CHIME("Gentle Chime"),
  LOFI("Lofi Pop"),
  HARP("Warm Harp")
}

enum class HapticIntensity(val label: String) {
  SUBTLE("Subtle Pulse"),
  SOFT("Soft Tap"),
  OFF("Muted / Off")
}

data class BadgeItem(
  val id: String,
  val title: String,
  val description: String,
  val rarity: String, // COMMON, RARE, EPIC, LEGEND
  val emoji: String,
  val isUnlocked: Boolean,
  val progressText: String? = null,
  val progressFraction: Float? = null
)
