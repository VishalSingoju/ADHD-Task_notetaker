package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material.icons.filled.OfflineBolt
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentLilac
import com.example.ui.theme.AccentLilacSubtle
import com.example.ui.theme.AccentPeach
import com.example.ui.theme.AccentPeachSubtle
import com.example.ui.theme.AccentSage
import com.example.ui.theme.AccentSageSubtle
import com.example.ui.theme.BorderMuted
import com.example.ui.theme.BorderSlate
import com.example.ui.theme.OnPrimaryColor
import com.example.ui.theme.PaperBase
import com.example.ui.theme.PaperRecessed
import com.example.ui.theme.PaperSurface
import com.example.ui.theme.PrimaryColor
import com.example.ui.theme.SecondaryColor
import com.example.ui.theme.SurfaceBackground
import com.example.ui.theme.TertiaryColor
import com.example.ui.theme.TextCharcoal
import com.example.ui.theme.TextFaint
import com.example.ui.theme.TextMuted
import com.example.ui.viewmodel.QuestViewModel

@Composable
fun NewQuestScreen(
  viewModel: QuestViewModel,
  modifier: Modifier = Modifier
) {
  val questTitle by viewModel.questTitle.collectAsState()
  val questXp by viewModel.questXp.collectAsState()
  val questCadence by viewModel.questCadence.collectAsState()
  val escalatingAlerts by viewModel.escalatingAlerts.collectAsState()
  val urgencyLevel by viewModel.urgencyLevel.collectAsState()
  val soundAesthetic by viewModel.soundAesthetic.collectAsState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(SurfaceBackground)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {

    // 1. Header Section
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier
              .size(28.dp)
              .clip(CircleShape)
              .background(AccentLilacSubtle),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.AutoFixHigh,
              contentDescription = "Forge",
              tint = PrimaryColor,
              modifier = Modifier.size(16.dp)
            )
          }
          Text(
            text = "FRICTIONLESS FORGE",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = SecondaryColor,
              letterSpacing = 1.sp
            )
          )
        }

        Box(
          modifier = Modifier
            .clip(CircleShape)
            .background(PaperSurface)
            .border(1.dp, BorderMuted, CircleShape)
            .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
          Text(
            text = "Zero-Guilt Mode",
            style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
          )
        }
      }

      Text(
        text = "Summon a New Quest",
        style = MaterialTheme.typography.headlineMedium.copy(
          fontWeight = FontWeight.Bold,
          color = TextCharcoal
        )
      )
      Text(
        text = "Keep it tiny. Tiny wins build unbreakable momentum.",
        style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
      )
    }

    // 2. Micro Coach Banner / Low Spoon Affirmation
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(PaperSurface)
        .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
        .padding(12.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Box(
          modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AccentPeachSubtle),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Spa,
            contentDescription = "Low spoons",
            tint = TertiaryColor,
            modifier = Modifier.size(22.dp)
          )
        }
        Column {
          Text(
            text = "Feeling low spoons today?",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = TextCharcoal
            )
          )
          Text(
            text = "Pick a 1-tap preset below. Zero typing required.",
            style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
          )
        }
      }
    }

    // 3. 1-Tap Instant Quests (2x3 Grid)
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(text = "⚡", fontSize = 14.sp)
          Text(
            text = "1-Tap Instant Quests",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = TextCharcoal
            )
          )
        }
        Text(
          text = "Tap to auto-fill",
          style = MaterialTheme.typography.labelSmall.copy(
            color = PrimaryColor,
            fontWeight = FontWeight.SemiBold
          )
        )
      }

      // Presets Rows
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        PresetCard(
          title = "💧 Drink Water",
          xp = 50,
          desc = "500ml glass",
          category = "Hydration",
          modifier = Modifier.weight(1f),
          onClick = { viewModel.applyPreset("💧 Drink Water", 50) }
        )
        PresetCard(
          title = "✨ Skincare",
          xp = 75,
          desc = "Wash & cream",
          category = "Self-Care",
          modifier = Modifier.weight(1f),
          onClick = { viewModel.applyPreset("✨ PM Skincare", 75) }
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        PresetCard(
          title = "📞 Quick Call",
          xp = 100,
          desc = "Say quick hi",
          category = "Connection",
          modifier = Modifier.weight(1f),
          onClick = { viewModel.applyPreset("📞 Quick Call", 100, "weekly") }
        )
        PresetCard(
          title = "👟 10m Walk",
          xp = 100,
          desc = "Step outside",
          category = "Movement",
          modifier = Modifier.weight(1f),
          onClick = { viewModel.applyPreset("👟 10-Min Walk", 100) }
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        PresetCard(
          title = "💊 Take Meds",
          xp = 50,
          desc = "Pill & gulp",
          category = "Health",
          modifier = Modifier.weight(1f),
          onClick = { viewModel.applyPreset("💊 Take Meds", 50) }
        )
        PresetCard(
          title = "🧹 3m Tidy",
          xp = 60,
          desc = "1 surface",
          category = "Low Energy",
          modifier = Modifier.weight(1f),
          onClick = { viewModel.applyPreset("🧹 3-Min Tidy", 60) }
        )
      }
    }

    // 4. Custom Quest Objective Input Well
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperBase),
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderMuted, RoundedCornerShape(16.dp))
    ) {
      Column(
        modifier = Modifier.padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = Icons.Default.EditNote,
              contentDescription = "Objective",
              tint = SecondaryColor,
              modifier = Modifier.size(20.dp)
            )
            Text(
              text = "Quest Objective",
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
          }

          Text(
            text = "⚡ +$questXp XP",
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.Bold,
              color = PrimaryColor
            )
          )
        }

        // Rapid Single-Line Input Container
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PaperSurface)
            .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            TextField(
              value = questTitle,
              onValueChange = { viewModel.onQuestTitleChange(it) },
              placeholder = {
                Text(text = "e.g. Put dirty cup in sink...", color = TextFaint)
              },
              colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
              ),
              modifier = Modifier
                .weight(1f)
                .testTag("quest_title_input")
            )

            // Voice to text simulation icon
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                  viewModel.onQuestTitleChange("💧 Drink a cold glass of lemon water")
                  viewModel.triggerHaptic()
                },
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Mic Voice",
                tint = PrimaryColor,
                modifier = Modifier.size(20.dp)
              )
            }

            // Clear icon
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                  viewModel.onQuestTitleChange("")
                  viewModel.triggerHaptic()
                },
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Backspace,
                contentDescription = "Clear",
                tint = TextFaint,
                modifier = Modifier.size(18.dp)
              )
            }
          }
        }

        // Surprise Me Button
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .background(AccentPeachSubtle)
              .border(1.dp, AccentPeach.copy(alpha = 0.25f), RoundedCornerShape(8.dp))
              .clickable { viewModel.surpriseLowSpoonPrompt() }
              .padding(horizontal = 10.dp, vertical = 6.dp)
              .testTag("surprise_me_button")
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Casino,
                contentDescription = "Dice",
                tint = TertiaryColor,
                modifier = Modifier.size(16.dp)
              )
              Text(
                text = "Surprise me with a low-spoon task",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = TertiaryColor
                )
              )
            }
          }

          Text(
            text = "Micro step only",
            style = MaterialTheme.typography.labelSmall.copy(
              color = TextFaint,
              fontSize = 11.sp
            )
          )
        }
      }
    }

    // 5. Cadence Selector
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperBase),
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderMuted, RoundedCornerShape(16.dp))
    ) {
      Column(
        modifier = Modifier.padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Cached,
              contentDescription = "Cadence",
              tint = PrimaryColor,
              modifier = Modifier.size(18.dp)
            )
            Text(
              text = "Quest Cadence",
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
          }

          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Shield,
              contentDescription = "Shield",
              tint = SecondaryColor,
              modifier = Modifier.size(14.dp)
            )
            Text(
              text = "Freeze Active",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = SecondaryColor
              )
            )
          }
        }

        // Segmented Control
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PaperSurface)
            .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
            .padding(4.dp),
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          CadenceOption(
            title = "Daily",
            subtitle = "RHYTHM",
            icon = Icons.Default.Cached,
            isSelected = questCadence == "daily",
            modifier = Modifier.weight(1f),
            onClick = { viewModel.setQuestCadence("daily") }
          )
          CadenceOption(
            title = "Weekly",
            subtitle = "PICK DAYS",
            icon = Icons.Default.DateRange,
            isSelected = questCadence == "weekly",
            modifier = Modifier.weight(1f),
            onClick = { viewModel.setQuestCadence("weekly") }
          )
          CadenceOption(
            title = "One-Time",
            subtitle = "SINGLE SPARK",
            icon = Icons.Default.OfflineBolt,
            isSelected = questCadence == "onetime",
            modifier = Modifier.weight(1f),
            onClick = { viewModel.setQuestCadence("onetime") }
          )
        }

        // Guilt-Free Assurance Banner
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(AccentSageSubtle)
            .border(1.dp, AccentSage.copy(alpha = 0.25f), RoundedCornerShape(10.dp))
            .padding(10.dp)
        ) {
          Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.VerifiedUser,
              contentDescription = "Shield guarantee",
              tint = SecondaryColor,
              modifier = Modifier.size(18.dp)
            )
            Text(
              text = "Guilt-free guarantee: Skipping a day automatically triggers your Streak Freeze shield without resetting your counter!",
              style = MaterialTheme.typography.bodySmall.copy(
                color = SecondaryColor,
                lineHeight = 16.sp
              )
            )
          }
        }
      }
    }

    // 6. ADHD-Friendly Escalating Reminder Engine
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperBase),
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderMuted, RoundedCornerShape(16.dp))
    ) {
      Column(
        modifier = Modifier.padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        // Toggle Row
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                imageVector = Icons.Default.NotificationsActive,
                contentDescription = "Alerts",
                tint = TertiaryColor,
                modifier = Modifier.size(18.dp)
              )
              Text(
                text = "Escalating Alerts",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = TextCharcoal
                )
              )
            }
            Text(
              text = "Gentle micro-nudges that adapt",
              style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
            )
          }

          Switch(
            checked = escalatingAlerts,
            onCheckedChange = { viewModel.toggleEscalatingAlerts() },
            colors = SwitchDefaults.colors(
              checkedThumbColor = Color.White,
              checkedTrackColor = AccentSage,
              uncheckedThumbColor = Color.White,
              uncheckedTrackColor = BorderMuted
            ),
            modifier = Modifier.testTag("escalating_alerts_switch")
          )
        }

        if (escalatingAlerts) {
          // Nudge Urgency Level
          Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
              text = "NUDGE URGENCY LEVEL",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextMuted,
                letterSpacing = 0.5.sp
              )
            )

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              UrgencyButton(
                title = "Gentle",
                sub = "Every 15m",
                isSelected = urgencyLevel == "gentle",
                modifier = Modifier.weight(1f),
                onClick = { viewModel.setAlertUrgency("gentle") }
              )
              UrgencyButton(
                title = "Persistent",
                sub = "Every 30m",
                isSelected = urgencyLevel == "persistent",
                modifier = Modifier.weight(1f),
                onClick = { viewModel.setAlertUrgency("persistent") }
              )
              UrgencyButton(
                title = "Emergency",
                sub = "Hourly",
                isSelected = urgencyLevel == "emergency",
                modifier = Modifier.weight(1f),
                onClick = { viewModel.setAlertUrgency("emergency") }
              )
            }
          }

          // Sound Aesthetic
          Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
              text = "SOUND AESTHETIC",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextMuted,
                letterSpacing = 0.5.sp
              )
            )

            Row(
              modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              SoundPill(
                label = "Chime",
                icon = Icons.Default.VolumeUp,
                isSelected = soundAesthetic == "chime",
                onClick = { viewModel.setSoundAesthetic("chime") }
              )
              SoundPill(
                label = "Gentle Harp",
                icon = Icons.Default.MusicNote,
                isSelected = soundAesthetic == "harp",
                onClick = { viewModel.setSoundAesthetic("harp") }
              )
              SoundPill(
                label = "Lofi Pop",
                icon = Icons.Default.Headphones,
                isSelected = soundAesthetic == "lofi",
                onClick = { viewModel.setSoundAesthetic("lofi") }
              )
            }
          }

          // Micro Explainer
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(PaperSurface)
              .border(1.dp, BorderMuted, RoundedCornerShape(8.dp))
              .padding(8.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                imageVector = Icons.Default.NotificationsOff,
                contentDescription = "Cancel note",
                tint = PrimaryColor,
                modifier = Modifier.size(16.dp)
              )
              Text(
                text = "Cancels immediately the second you tick the quest. No phantom alarm irritation!",
                style = MaterialTheme.typography.bodySmall.copy(
                  color = TextMuted,
                  fontSize = 11.sp
                )
              )
            }
          }
        }
      }
    }

    // 7. Actions: Forge Quest & Brain Dump
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      // Primary Forge Button
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(58.dp)
          .clip(RoundedCornerShape(16.dp))
          .background(AccentLilac)
          .clickable { viewModel.forgeCurrentQuest() }
          .padding(horizontal = 20.dp)
          .testTag("forge_quest_button"),
        contentAlignment = Alignment.Center
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text(text = "⚡", fontSize = 18.sp)
            Text(
              text = "Forge Quest",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = OnPrimaryColor
              )
            )
          }

          Box(
            modifier = Modifier
              .clip(CircleShape)
              .background(Color.White.copy(alpha = 0.2f))
              .padding(horizontal = 10.dp, vertical = 4.dp)
          ) {
            Text(
              text = "+$questXp XP",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = OnPrimaryColor
              )
            )
          }
        }
      }

      // Brain Dump Button
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(PaperSurface)
          .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
          .clickable { viewModel.bankInBrainDump() }
          .testTag("bank_in_brain_dump_button"),
        contentAlignment = Alignment.Center
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Icon(
            imageVector = Icons.Default.BookmarkAdd,
            contentDescription = "Bookmark",
            tint = SecondaryColor,
            modifier = Modifier.size(18.dp)
          )
          Text(
            text = "Bank in Brain Dump (No Due Date)",
            style = MaterialTheme.typography.labelLarge.copy(
              fontWeight = FontWeight.SemiBold,
              color = TextMuted
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))
  }
}

@Composable
private fun PresetCard(
  title: String,
  xp: Int,
  desc: String,
  category: String,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Box(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .background(PaperBase)
      .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
      .clickable { onClick() }
      .padding(10.dp)
  ) {
    Column(
      verticalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = TextCharcoal
          ),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Box(
          modifier = Modifier
            .clip(CircleShape)
            .background(AccentLilacSubtle)
            .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
          Text(
            text = "+$xp XP",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.SemiBold,
              color = PrimaryColor,
              fontSize = 10.sp
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = desc,
          style = MaterialTheme.typography.bodySmall.copy(
            color = TextMuted,
            fontSize = 11.sp
          ),
          maxLines = 1
        )
        Text(
          text = category,
          style = MaterialTheme.typography.labelSmall.copy(
            color = SecondaryColor,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp
          )
        )
      }
    }
  }
}

@Composable
private fun CadenceOption(
  title: String,
  subtitle: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  isSelected: Boolean,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  val bg = if (isSelected) AccentLilac else Color.Transparent
  val textCol = if (isSelected) Color.White else TextMuted

  Box(
    modifier = modifier
      .height(52.dp)
      .clip(RoundedCornerShape(8.dp))
      .background(bg)
      .clickable { onClick() },
    contentAlignment = Alignment.Center
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Icon(
          imageVector = icon,
          contentDescription = title,
          tint = textCol,
          modifier = Modifier.size(14.dp)
        )
        Text(
          text = title,
          style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = textCol
          )
        )
      }
      Text(
        text = subtitle,
        style = MaterialTheme.typography.labelSmall.copy(
          fontSize = 9.sp,
          color = textCol.copy(alpha = 0.8f),
          letterSpacing = 0.5.sp
        )
      )
    }
  }
}

@Composable
private fun UrgencyButton(
  title: String,
  sub: String,
  isSelected: Boolean,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  val bg = if (isSelected) AccentLilac else PaperSurface
  val textCol = if (isSelected) Color.White else TextCharcoal
  val subCol = if (isSelected) Color.White.copy(alpha = 0.85f) else TextMuted

  Box(
    modifier = modifier
      .height(50.dp)
      .clip(RoundedCornerShape(10.dp))
      .background(bg)
      .border(1.dp, if (isSelected) Color.Transparent else BorderMuted, RoundedCornerShape(10.dp))
      .clickable { onClick() },
    contentAlignment = Alignment.Center
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(
        text = title,
        style = MaterialTheme.typography.labelMedium.copy(
          fontWeight = FontWeight.Bold,
          color = textCol
        )
      )
      Text(
        text = sub,
        style = MaterialTheme.typography.labelSmall.copy(
          fontSize = 10.sp,
          color = subCol
        )
      )
    }
  }
}

@Composable
private fun SoundPill(
  label: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  val bg = if (isSelected) AccentLilacSubtle else PaperSurface
  val textCol = if (isSelected) PrimaryColor else TextMuted
  val borderCol = if (isSelected) AccentLilac.copy(alpha = 0.4f) else BorderMuted

  Box(
    modifier = Modifier
      .height(36.dp)
      .clip(CircleShape)
      .background(bg)
      .border(1.dp, borderCol, CircleShape)
      .clickable { onClick() }
      .padding(horizontal = 12.dp),
    contentAlignment = Alignment.Center
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Icon(
        imageVector = icon,
        contentDescription = label,
        tint = textCol,
        modifier = Modifier.size(14.dp)
      )
      Text(
        text = label,
        style = MaterialTheme.typography.labelSmall.copy(
          fontWeight = FontWeight.SemiBold,
          color = textCol
        )
      )
    }
  }
}
