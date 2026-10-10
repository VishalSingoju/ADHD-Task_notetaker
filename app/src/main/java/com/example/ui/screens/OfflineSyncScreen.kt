package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AutoMode
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EnhancedEncryption
import androidx.compose.material.icons.filled.FolderZip
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.OfflineBolt
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.HapticIntensity
import com.example.data.model.SoundAesthetic
import com.example.ui.theme.AccentLilac
import com.example.ui.theme.AccentLilacSubtle
import com.example.ui.theme.AccentPeach
import com.example.ui.theme.AccentPeachSubtle
import com.example.ui.theme.AccentSage
import com.example.ui.theme.AccentSageSubtle
import com.example.ui.theme.AccentSky
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
fun OfflineSyncScreen(
  viewModel: QuestViewModel,
  modifier: Modifier = Modifier
) {
  val userProfile by viewModel.userProfile.collectAsState()
  val quests by viewModel.quests.collectAsState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(SurfaceBackground)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {

    // 1. Sub-Header & Status
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "Settings & Metrics",
          style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold,
            color = TextCharcoal
          )
        )
        Text(
          text = "Local-first architecture & engine diagnostics",
          style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
        )
      }

      Box(
        modifier = Modifier
          .clip(CircleShape)
          .background(AccentSageSubtle)
          .border(1.dp, AccentSage.copy(alpha = 0.3f), CircleShape)
          .padding(horizontal = 10.dp, vertical = 4.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Box(
            modifier = Modifier
              .size(6.dp)
              .clip(CircleShape)
              .background(AccentSage)
          )
          Text(
            text = "Vault Healthy",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.SemiBold,
              color = SecondaryColor
            )
          )
        }
      }
    }

    // 2. Health & Sanctuary Card
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperSurface),
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderMuted, RoundedCornerShape(16.dp))
    ) {
      Column(
        modifier = Modifier.padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Row(
          verticalAlignment = Alignment.Top,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Box(
            modifier = Modifier
              .size(42.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(AccentSageSubtle)
              .border(1.dp, AccentSage.copy(alpha = 0.3f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.VerifiedUser,
              contentDescription = "Shield",
              tint = SecondaryColor,
              modifier = Modifier.size(24.dp)
            )
          }

          Column {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Text(
                text = "Local Sanctuary Active",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = TextCharcoal
                )
              )
              Box(
                modifier = Modifier
                  .clip(CircleShape)
                  .background(AccentSageSubtle)
                  .padding(horizontal = 6.dp, vertical = 2.dp)
              ) {
                Text(
                  text = "100% On-Device",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryColor
                  )
                )
              }
            }
            Text(
              text = "AES-GCM-256 encrypted hardware sandbox. Zero cloud telemetry or external pings.",
              style = MaterialTheme.typography.bodySmall.copy(
                color = TextMuted,
                lineHeight = 16.sp
              )
            )
          }
        }

        // Diagnostic Pills
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          DiagnosticPill(icon = Icons.Default.Speed, label = "0 ms Latency", tint = AccentSage)
          DiagnosticPill(icon = Icons.Default.Lock, label = "Secure Enclave", tint = AccentLilac)
          DiagnosticPill(icon = Icons.Default.WifiOff, label = "Air-Gap Capable", tint = AccentPeach)
        }
      }
    }

    // 3. Storage & Vault Footprint (2x2 Bento Metrics Grid)
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
            imageVector = Icons.Default.CloudDone,
            contentDescription = "Storage",
            tint = AccentLilac,
            modifier = Modifier.size(18.dp)
          )
          Text(
            text = "Storage & Vault Footprint",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = TextCharcoal
            )
          )
        }
        Text(
          text = "Live Readout",
          style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        MetricCard(
          title = "Local Quests",
          value = maxOf(42, quests.size).toString(),
          sub = "Stored & indexed locally",
          icon = Icons.Default.Inventory2,
          tint = AccentLilac,
          modifier = Modifier.weight(1f)
        )
        MetricCard(
          title = "Cloud Leaks",
          value = "0 KB",
          sub = "Zero network tracking",
          icon = Icons.Default.Shield,
          tint = AccentSage,
          modifier = Modifier.weight(1f)
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        MetricCard(
          title = "Engine Delay",
          value = "0 ms",
          sub = "In-memory query bus",
          icon = Icons.Default.Timer,
          tint = AccentSky,
          modifier = Modifier.weight(1f)
        )
        MetricCard(
          title = "Storage Size",
          value = "1.2 MB",
          sub = "Room SQLite cache limit",
          icon = Icons.Default.FolderZip,
          tint = AccentPeach,
          modifier = Modifier.weight(1f)
        )
      }

      // Breakdown Details Bar
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(12.dp))
          .background(PaperRecessed)
          .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
          .padding(12.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "Storage Engine", style = MaterialTheme.typography.bodySmall.copy(color = TextMuted))
            Text(
              text = "• Room Native (SQLite bridge)",
              style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = TextCharcoal
              )
            )
          }
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "Key Derivation", style = MaterialTheme.typography.bodySmall.copy(color = TextMuted))
            Text(
              text = "PBKDF2 · 100,000 Rounds",
              style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = TextCharcoal
              )
            )
          }
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "Last Local Snapshot", style = MaterialTheme.typography.bodySmall.copy(color = TextMuted))
            Text(
              text = userProfile.lastLocalSnapshot,
              style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                color = TextMuted
              )
            )
          }
        }
      }
    }

    // 4. Data Management & Sync Actions
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperSurface),
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
              imageVector = Icons.Default.AutoMode,
              contentDescription = "Backup",
              tint = SecondaryColor,
              modifier = Modifier.size(18.dp)
            )
            Column {
              Text(
                text = "Instant Local Backup",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = TextCharcoal
                )
              )
              Text(
                text = "Persist changes after every completed quest",
                style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
              )
            }
          }

          Switch(
            checked = userProfile.instantBackup,
            onCheckedChange = { viewModel.toggleSettingInstantBackup() },
            colors = SwitchDefaults.colors(
              checkedThumbColor = Color.White,
              checkedTrackColor = AccentSage,
              uncheckedThumbColor = Color.White,
              uncheckedTrackColor = BorderMuted
            ),
            modifier = Modifier.testTag("instant_backup_switch")
          )
        }

        // Primary Sync Action Button
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(AccentLilac)
            .clickable { viewModel.syncLocalVault() }
            .testTag("sync_to_local_storage_button"),
          contentAlignment = Alignment.Center
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.OfflineBolt,
              contentDescription = "Sync",
              tint = OnPrimaryColor,
              modifier = Modifier.size(20.dp)
            )
            Text(
              text = "Sync to Private Local Storage",
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = OnPrimaryColor
              )
            )
          }
        }

        // Export JSON & Encrypted Archive
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Box(
            modifier = Modifier
              .weight(1f)
              .height(48.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(PaperBase)
              .border(1.dp, BorderMuted, RoundedCornerShape(10.dp))
              .clickable {
                viewModel.triggerHaptic()
              }
              .testTag("export_json_button"),
            contentAlignment = Alignment.Center
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Download,
                contentDescription = "Export JSON",
                tint = AccentLilac,
                modifier = Modifier.size(16.dp)
              )
              Text(
                text = "Export JSON",
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = TextCharcoal
                )
              )
            }
          }

          Box(
            modifier = Modifier
              .weight(1f)
              .height(48.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(PaperBase)
              .border(1.dp, BorderMuted, RoundedCornerShape(10.dp))
              .clickable {
                viewModel.triggerHaptic()
              }
              .testTag("encrypted_archive_button"),
            contentAlignment = Alignment.Center
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                imageVector = Icons.Default.EnhancedEncryption,
                contentDescription = "Encrypted",
                tint = AccentPeach,
                modifier = Modifier.size(16.dp)
              )
              Text(
                text = "Encrypted Archive",
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = TextCharcoal
                )
              )
            }
          }
        }
      }
    }

    // 5. Zero-Guilt Streak Shield Auto-Guard Timeline (from Image 9)
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperSurface),
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
              imageVector = Icons.Default.Shield,
              contentDescription = "Shield Guard",
              tint = TertiaryColor,
              modifier = Modifier.size(18.dp)
            )
            Text(
              text = "Zero-Guilt Streak Shield",
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
          }

          Box(
            modifier = Modifier
              .clip(CircleShape)
              .background(AccentPeachSubtle)
              .padding(horizontal = 8.dp, vertical = 2.dp)
          ) {
            Text(
              text = "Auto-Guard",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TertiaryColor
              )
            )
          }
        }

        Text(
          text = "Executive fatigue happens. SparkQuest never resets hard-earned momentum to zero over an empty dopamine day.",
          style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
        )

        // Timeline Flow Diagram
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PaperBase)
            .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          TimelineStep(
            emoji = "🔥",
            title = "Day 1: Completed",
            sub = "11 Day Momentum",
            tag = "✓",
            tagColor = AccentSage
          )
          Text(text = "↓", modifier = Modifier.align(Alignment.CenterHorizontally), color = TextMuted)
          TimelineStep(
            emoji = "🧠",
            title = "Day 2: Executive Fatigue",
            sub = "Zero spoons remaining",
            tag = "Rest",
            tagColor = TextMuted
          )
          Text(text = "↓", modifier = Modifier.align(Alignment.CenterHorizontally), color = TextMuted)
          TimelineStep(
            emoji = "🛡️",
            title = "Streak Shield Deployed",
            sub = "Auto-applied • No penalty",
            tag = "Active",
            tagColor = AccentSage
          )
          Text(text = "↓", modifier = Modifier.align(Alignment.CenterHorizontally), color = TextMuted)
          TimelineStep(
            emoji = "⚡",
            title = "Day 3: Journey Resumed",
            sub = "🔥 12 Maintained • Zero Guilt!",
            tag = "★",
            tagColor = PrimaryColor
          )
        }

        // Simulate Missed Day Button
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(PaperBase)
            .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
            .clickable { viewModel.simulateMissedDay() }
            .testTag("simulate_missed_day_button"),
          contentAlignment = Alignment.Center
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text(text = "⚡", fontSize = 16.sp)
            Text(
              text = "Simulate Missed Day & Auto-Rescue",
              style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
          }
        }
      }
    }

    // 6. Sensory & Attention Pacing (from Image 13)
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperSurface),
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderMuted, RoundedCornerShape(16.dp))
    ) {
      Column(
        modifier = Modifier.padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.NotificationsActive,
            contentDescription = "Sensory",
            tint = AccentPeach,
            modifier = Modifier.size(18.dp)
          )
          Text(
            text = "Sensory & Attention Pacing",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = TextCharcoal
            )
          )
        }

        // Gentle Nudges
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Gentle Push Nudges",
              style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = TextCharcoal
              )
            )
            Text(
              text = "Non-urgent, whisper-style prompt",
              style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
            )
          }
          Switch(
            checked = userProfile.gentleNudges,
            onCheckedChange = { viewModel.toggleSettingNudges() },
            colors = SwitchDefaults.colors(
              checkedThumbColor = Color.White,
              checkedTrackColor = AccentLilac,
              uncheckedThumbColor = Color.White,
              uncheckedTrackColor = BorderMuted
            )
          )
        }

        // Sound Selector
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Text(
            text = "Acoustic Signature",
            style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
          )
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            SoundChoice(
              label = "Gentle Chime",
              isSelected = userProfile.soundAesthetic == SoundAesthetic.CHIME,
              modifier = Modifier.weight(1f),
              onClick = { viewModel.setSoundSetting(SoundAesthetic.CHIME) }
            )
            SoundChoice(
              label = "Lofi Pop",
              isSelected = userProfile.soundAesthetic == SoundAesthetic.LOFI,
              modifier = Modifier.weight(1f),
              onClick = { viewModel.setSoundSetting(SoundAesthetic.LOFI) }
            )
            SoundChoice(
              label = "Warm Harp",
              isSelected = userProfile.soundAesthetic == SoundAesthetic.HARP,
              modifier = Modifier.weight(1f),
              onClick = { viewModel.setSoundSetting(SoundAesthetic.HARP) }
            )
          }
        }

        // ADHD Safe Clear Queue
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Text(
                text = "ADHD Safe-Clear Queue",
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = TextCharcoal
                )
              )
              Box(
                modifier = Modifier
                  .clip(CircleShape)
                  .background(AccentPeachSubtle)
                  .padding(horizontal = 6.dp, vertical = 1.dp)
              ) {
                Text(
                  text = "No Guilt",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.sp,
                    color = TertiaryColor
                  )
                )
              }
            }
            Text(
              text = "Dissolves overdue tasks after 48h",
              style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
            )
          }
          Switch(
            checked = userProfile.adhdSafeClear,
            onCheckedChange = { viewModel.toggleSettingAdhdSafeClear() },
            colors = SwitchDefaults.colors(
              checkedThumbColor = Color.White,
              checkedTrackColor = AccentPeach,
              uncheckedThumbColor = Color.White,
              uncheckedTrackColor = BorderMuted
            )
          )
        }

        // Haptic Feedback Engine
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Haptic Engine Response",
              style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
            )
            Box(
              modifier = Modifier
                .clip(CircleShape)
                .background(AccentLilacSubtle)
                .clickable { viewModel.triggerHaptic(force = true) }
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .testTag("test_haptic_button")
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Vibration,
                  contentDescription = "Test",
                  tint = PrimaryColor,
                  modifier = Modifier.size(12.dp)
                )
                Text(
                  text = "Test Haptic",
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold
                  )
                )
              }
            }
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            SoundChoice(
              label = "Subtle Pulse",
              isSelected = userProfile.hapticFeedback == HapticIntensity.SUBTLE,
              modifier = Modifier.weight(1f),
              onClick = { viewModel.setHapticFeedback(HapticIntensity.SUBTLE) }
            )
            SoundChoice(
              label = "Soft Tap",
              isSelected = userProfile.hapticFeedback == HapticIntensity.SOFT,
              modifier = Modifier.weight(1f),
              onClick = { viewModel.setHapticFeedback(HapticIntensity.SOFT) }
            )
            SoundChoice(
              label = "Muted / Off",
              isSelected = userProfile.hapticFeedback == HapticIntensity.OFF,
              modifier = Modifier.weight(1f),
              onClick = { viewModel.setHapticFeedback(HapticIntensity.OFF) }
            )
          }

          Text(
            text = "Calibrated for low sensory over-stimulation.",
            style = MaterialTheme.typography.labelSmall.copy(
              color = TextFaint,
              fontSize = 11.sp
            )
          )
        }
      }
    }

    // 7. Zero Cloud, Zero Guilt Guarantee Banner
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(14.dp))
        .background(AccentSageSubtle)
        .border(1.dp, AccentSage.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
        .padding(14.dp)
    ) {
      Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(AccentSage),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Spa,
            contentDescription = "Calm",
            tint = Color.White,
            modifier = Modifier.size(18.dp)
          )
        }

        Column {
          Text(
            text = "Zero Cloud, Zero Guilt",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = SecondaryColor
            )
          )
          Text(
            text = "Your habit paths, pause moments, and focus metrics never leave your hardware. No accounts, no advertising identifiers, and no algorithms judging your pace.",
            style = MaterialTheme.typography.bodySmall.copy(
              color = TextCharcoal,
              lineHeight = 17.sp
            )
          )
        }
      }
    }

    // Engine Build & Quota Readout
    Text(
      text = "SparkQuest Engine v2.4.1 (Local Build) • Storage Quota: 1.2MB / 50MB",
      style = MaterialTheme.typography.labelSmall.copy(
        color = TextFaint,
        fontSize = 10.sp
      ),
      modifier = Modifier.align(Alignment.CenterHorizontally)
    )

    Spacer(modifier = Modifier.height(16.dp))
  }
}

@Composable
private fun DiagnosticPill(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  label: String,
  tint: Color
) {
  Box(
    modifier = Modifier
      .clip(CircleShape)
      .background(PaperBase)
      .border(1.dp, BorderMuted, CircleShape)
      .padding(horizontal = 8.dp, vertical = 4.dp)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Icon(
        imageVector = icon,
        contentDescription = label,
        tint = tint,
        modifier = Modifier.size(14.dp)
      )
      Text(
        text = label,
        style = MaterialTheme.typography.labelSmall.copy(
          fontWeight = FontWeight.Medium,
          color = TextCharcoal,
          fontSize = 11.sp
        )
      )
    }
  }
}

@Composable
private fun MetricCard(
  title: String,
  value: String,
  sub: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  tint: Color,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .background(PaperSurface)
      .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
      .padding(12.dp)
  ) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = title,
          style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
        )
        Icon(
          imageVector = icon,
          contentDescription = title,
          tint = tint,
          modifier = Modifier.size(16.dp)
        )
      }
      Text(
        text = value,
        style = MaterialTheme.typography.titleLarge.copy(
          fontWeight = FontWeight.Bold,
          color = TextCharcoal
        )
      )
      Text(
        text = sub,
        style = MaterialTheme.typography.labelSmall.copy(
          color = TextMuted,
          fontSize = 10.sp
        )
      )
    }
  }
}

@Composable
private fun TimelineStep(
  emoji: String,
  title: String,
  sub: String,
  tag: String,
  tagColor: Color
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
      Text(text = emoji, fontSize = 16.sp)
      Column {
        Text(
          text = title,
          style = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Bold,
            color = TextCharcoal
          )
        )
        Text(
          text = sub,
          style = MaterialTheme.typography.labelSmall.copy(
            color = TextMuted,
            fontSize = 10.sp
          )
        )
      }
    }

    Box(
      modifier = Modifier
        .clip(CircleShape)
        .background(PaperRecessed)
        .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
      Text(
        text = tag,
        style = MaterialTheme.typography.labelSmall.copy(
          fontWeight = FontWeight.Bold,
          color = tagColor,
          fontSize = 10.sp
        )
      )
    }
  }
}

@Composable
private fun SoundChoice(
  label: String,
  isSelected: Boolean,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  val bg = if (isSelected) AccentLilacSubtle else PaperRecessed
  val textCol = if (isSelected) PrimaryColor else TextMuted
  val borderCol = if (isSelected) AccentLilac.copy(alpha = 0.4f) else Color.Transparent

  Box(
    modifier = modifier
      .height(38.dp)
      .clip(RoundedCornerShape(8.dp))
      .background(bg)
      .border(1.dp, borderCol, RoundedCornerShape(8.dp))
      .clickable { onClick() },
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall.copy(
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
        color = textCol,
        fontSize = 11.sp
      )
    )
  }
}
