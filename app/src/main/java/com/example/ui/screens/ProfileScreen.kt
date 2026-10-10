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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.LockPerson
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.SpoonState
import com.example.ui.theme.AccentLilac
import com.example.ui.theme.AccentLilacSubtle
import com.example.ui.theme.AccentPeach
import com.example.ui.theme.AccentPeachSubtle
import com.example.ui.theme.AccentSage
import com.example.ui.theme.AccentSageSubtle
import com.example.ui.theme.AccentSky
import com.example.ui.theme.AccentSkySubtle
import com.example.ui.theme.BorderMuted
import com.example.ui.theme.BorderSlate
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
import com.example.ui.viewmodel.NavigationTab
import com.example.ui.viewmodel.QuestViewModel

@Composable
fun ProfileScreen(
  viewModel: QuestViewModel,
  modifier: Modifier = Modifier
) {
  val userProfile by viewModel.userProfile.collectAsState()
  var showEditNoteDialog by remember { mutableStateOf(false) }
  var editedNoteText by remember { mutableStateOf(userProfile.compassionNote) }

  if (showEditNoteDialog) {
    AlertDialog(
      onDismissRequest = { showEditNoteDialog = false },
      title = {
        Text(
          text = "Kind words for your future self:",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
      },
      text = {
        OutlinedTextField(
          value = editedNoteText,
          onValueChange = { editedNoteText = it },
          label = { Text("Compassion note") },
          modifier = Modifier.fillMaxWidth()
        )
      },
      confirmButton = {
        TextButton(
          onClick = {
            viewModel.updateCompassionNote(editedNoteText)
            showEditNoteDialog = false
          }
        ) {
          Text("Save Note", color = PrimaryColor, fontWeight = FontWeight.Bold)
        }
      },
      dismissButton = {
        TextButton(onClick = { showEditNoteDialog = false }) {
          Text("Cancel", color = TextMuted)
        }
      }
    )
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(SurfaceBackground)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {

    // 1. Breadcrumb & Session Bar
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .clickable { viewModel.navigateBackFromProfile() }
          .padding(horizontal = 4.dp, vertical = 6.dp)
          .testTag("back_to_quests_button")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = TextMuted,
            modifier = Modifier.size(18.dp)
          )
          Text(
            text = "Quests",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Medium,
              color = TextMuted
            )
          )
        }
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Box(
          modifier = Modifier
            .clip(CircleShape)
            .background(AccentSageSubtle)
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
              text = "Calm Session",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = SecondaryColor
              )
            )
          }
        }

        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(PaperSurface)
            .border(1.dp, BorderMuted, CircleShape)
            .clickable { viewModel.setTab(NavigationTab.Sync) },
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            tint = TextMuted,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }

    // 2. Persona & Level Journey Card
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
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp),
          verticalAlignment = Alignment.Top
        ) {
          // Tactile Avatar Frame
          Box(modifier = Modifier.size(64.dp)) {
            Box(
              modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(AccentLilacSubtle)
                .border(1.dp, BorderSlate, CircleShape),
              contentAlignment = Alignment.Center
            ) {
              AsyncImage(
                model = userProfile.avatarUrl,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
              )
            }
            Box(
              modifier = Modifier
                .size(22.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .background(AccentSage),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Eco,
                contentDescription = "Eco badge",
                tint = Color.White,
                modifier = Modifier.size(14.dp)
              )
            }
          }

          Column(modifier = Modifier.weight(1f)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = userProfile.name,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = TextCharcoal
                )
              )
              Box(
                modifier = Modifier
                  .clip(CircleShape)
                  .background(AccentLilacSubtle)
                  .padding(horizontal = 8.dp, vertical = 2.dp)
              ) {
                Text(
                  text = "Lvl ${userProfile.level}",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    fontSize = 11.sp
                  )
                )
              }
            }
            Text(
              text = "${userProfile.handle} • ${userProfile.role}",
              style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              Box(
                modifier = Modifier
                  .clip(CircleShape)
                  .background(PaperRecessed)
                  .padding(horizontal = 8.dp, vertical = 2.dp)
              ) {
                Text(
                  text = "🌿 Sensory Calm",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
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
                  text = "🛡️ Zero Shame",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    color = TertiaryColor
                  )
                )
              }
            }
          }
        }

        // Momentum Overview Strip
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(PaperRecessed.copy(alpha = 0.6f))
            .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "🔥 ${userProfile.streakDays} Days",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = TertiaryColor
                )
              )
              Text(text = "Gentle Streak", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = TextFaint))
            }
            Box(
              modifier = Modifier
                .width(1.dp)
                .height(24.dp)
                .background(BorderMuted)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "🛡️ ${userProfile.activeShields} Shields",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = SecondaryColor
                )
              )
              Text(text = "Banked Rest", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = TextFaint))
            }
            Box(
              modifier = Modifier
                .width(1.dp)
                .height(24.dp)
                .background(BorderMuted)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "⚡ %,d XP".format(userProfile.currentXp),
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = PrimaryColor
                )
              )
              Text(text = "Kind Progress", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = TextFaint))
            }
          }
        }

        // Soft Level Progression Track
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "Level 14 → 15 (${userProfile.nextLevelRole})",
              style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
            )
            Text(
              text = "%,d / %,d XP".format(userProfile.currentXp, userProfile.nextLevelXp),
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
          }

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(8.dp)
              .clip(CircleShape)
              .background(PaperRecessed)
          ) {
            Box(
              modifier = Modifier
                .fillMaxWidth(fraction = (userProfile.currentXp.toFloat() / userProfile.nextLevelXp.toFloat()).coerceIn(0f, 1f))
                .fillMaxSize()
                .clip(CircleShape)
                .background(
                  Brush.horizontalGradient(listOf(AccentLilac, AccentSage))
                )
            )
          }

          Text(
            text = "♡ Consistent effort, zero guilt. Pace yourself anytime.",
            style = MaterialTheme.typography.labelSmall.copy(
              color = TextFaint,
              fontSize = 11.sp
            )
          )
        }
      }
    }

    // 3. Spoon Reserve & Capacity State
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
            Box(
              modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(AccentPeachSubtle),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.SoupKitchen,
                contentDescription = "Spoons",
                tint = TertiaryColor,
                modifier = Modifier.size(16.dp)
              )
            }
            Text(
              text = "Spoon Reserve & Rhythms",
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
          }
          Text(
            text = "Low-Arousal Active",
            style = MaterialTheme.typography.labelSmall.copy(color = TextFaint)
          )
        }

        Text(
          text = "TODAY'S CAPACITY STATE",
          style = MaterialTheme.typography.labelSmall.copy(
            color = TextMuted,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
          )
        )

        // Spoon Buttons (3 options)
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          SpoonCard(
            state = SpoonState.LOW,
            isSelected = userProfile.spoonState == SpoonState.LOW,
            modifier = Modifier.weight(1f),
            onClick = { viewModel.updateSpoonState(SpoonState.LOW) }
          )
          SpoonCard(
            state = SpoonState.BALANCED,
            isSelected = userProfile.spoonState == SpoonState.BALANCED,
            modifier = Modifier.weight(1f),
            onClick = { viewModel.updateSpoonState(SpoonState.BALANCED) }
          )
          SpoonCard(
            state = SpoonState.HIGH,
            isSelected = userProfile.spoonState == SpoonState.HIGH,
            modifier = Modifier.weight(1f),
            onClick = { viewModel.updateSpoonState(SpoonState.HIGH) }
          )
        }

        // Accommodations List
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          AccommodationRow(
            icon = Icons.Default.WbTwilight,
            iconTint = AccentSky,
            iconBg = AccentSkySubtle,
            title = "Afternoon Flow Window",
            desc = "Peak gentle clarity: 2:00 PM – 5:00 PM",
            trailing = {
              Icon(imageVector = Icons.Default.Schedule, contentDescription = null, tint = TextMuted, modifier = Modifier.size(18.dp))
            }
          )
          AccommodationRow(
            icon = Icons.Default.Bedtime,
            iconTint = AccentPeach,
            iconBg = AccentPeachSubtle,
            title = "Demand Avoidance Shield",
            desc = "Invitations only • No countdowns",
            trailing = {
              Box(
                modifier = Modifier
                  .clip(CircleShape)
                  .background(AccentSageSubtle)
                  .padding(horizontal = 8.dp, vertical = 2.dp)
              ) {
                Text(text = "Active", style = MaterialTheme.typography.labelSmall.copy(color = SecondaryColor))
              }
            }
          )
          AccommodationRow(
            icon = Icons.Default.VolumeDown,
            iconTint = PrimaryColor,
            iconBg = AccentLilacSubtle,
            title = "Whisper Feedback & Muted Haptics",
            desc = "Soft wooden clicks, no red badges",
            trailing = {
              Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = AccentSage, modifier = Modifier.size(18.dp))
            }
          )
        }
      }
    }

    // 4. Compassionate Milestones
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Compassionate Milestones",
          style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = TextCharcoal
          )
        )
        Text(
          text = "Lifetime log",
          style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        MilestoneCard(
          label = "Quests Done",
          value = "142",
          sub = "At your organic speed",
          icon = Icons.Default.TaskAlt,
          tint = PrimaryColor,
          modifier = Modifier.weight(1f)
        )
        MilestoneCard(
          label = "Shield Rescues",
          value = "5",
          sub = "Zero guilt resets",
          icon = Icons.Default.Shield,
          tint = TertiaryColor,
          modifier = Modifier.weight(1f)
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        MilestoneCard(
          label = "Longest Orbit",
          value = "24 d",
          sub = "Gentle consistency",
          icon = Icons.Default.SelfImprovement,
          tint = SecondaryColor,
          modifier = Modifier.weight(1f)
        )
        MilestoneCard(
          label = "Micro-Steps",
          value = "389",
          sub = "Tiny acts that mattered",
          icon = Icons.Default.DirectionsWalk,
          tint = AccentSky,
          modifier = Modifier.weight(1f)
        )
      }
    }

    // 5. Compassion Note to Future Self
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
              imageVector = Icons.Default.EditNote,
              contentDescription = "Note",
              tint = AccentLilac,
              modifier = Modifier.size(18.dp)
            )
            Text(
              text = "Compassion Note to Future Self",
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
          }

          Text(
            text = "Edit",
            style = MaterialTheme.typography.labelSmall.copy(
              color = PrimaryColor,
              fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
              .clickable {
                editedNoteText = userProfile.compassionNote
                showEditNoteDialog = true
              }
              .testTag("edit_compassion_note_button")
          )
        }

        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(PaperRecessed.copy(alpha = 0.8f))
            .padding(12.dp)
        ) {
          Text(
            text = "“${userProfile.compassionNote}”",
            style = MaterialTheme.typography.bodyMedium.copy(
              color = TextCharcoal,
              fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
              lineHeight = 20.sp
            )
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = "Read on days with zero dopamine spoons",
            style = MaterialTheme.typography.labelSmall.copy(
              color = TextFaint,
              fontSize = 11.sp
            )
          )
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(SecondaryColor)
            )
            Text(
              text = "Sealed with kindness",
              style = MaterialTheme.typography.labelSmall.copy(
                color = SecondaryColor,
                fontWeight = FontWeight.Medium
              )
            )
          }
        }
      }
    }

    // 6. Sanctuary & Vault Quick Hub
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
              imageVector = Icons.Default.LockPerson,
              contentDescription = "Sanctuary",
              tint = SecondaryColor,
              modifier = Modifier.size(18.dp)
            )
            Text(
              text = "Sanctuary & Vault",
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
          }

          Box(
            modifier = Modifier
              .clip(CircleShape)
              .background(AccentSageSubtle)
              .padding(horizontal = 8.dp, vertical = 2.dp)
          ) {
            Text(
              text = "100% On-Device",
              style = MaterialTheme.typography.labelSmall.copy(
                color = SecondaryColor,
                fontWeight = FontWeight.SemiBold
              )
            )
          }
        }

        Text(
          text = "Your neuro-rhythms, task milestones, and notes never touch advertising networks or external scrapers.",
          style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
        )

        // Quick Settings link
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PaperRecessed)
            .clickable { viewModel.setTab(NavigationTab.Sync) }
            .padding(12.dp)
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
              Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = null,
                tint = PrimaryColor,
                modifier = Modifier.size(18.dp)
              )
              Column {
                Text(
                  text = "Settings & Vault Diagnostics",
                  style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextCharcoal
                  )
                )
                Text(
                  text = "Tune haptics, font sizes & sensory filters",
                  style = MaterialTheme.typography.labelSmall.copy(color = TextFaint)
                )
              }
            }
            Icon(
              imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
              contentDescription = null,
              tint = TextMuted,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        // Quick Export button
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PaperRecessed)
            .clickable { viewModel.triggerHaptic() }
            .padding(12.dp)
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
              Icon(
                imageVector = Icons.Default.FileDownload,
                contentDescription = null,
                tint = SecondaryColor,
                modifier = Modifier.size(18.dp)
              )
              Column {
                Text(
                  text = "Export Personal Journey",
                  style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextCharcoal
                  )
                )
                Text(
                  text = "Download your journey log (JSON & CSV)",
                  style = MaterialTheme.typography.labelSmall.copy(color = TextFaint)
                )
              }
            }
            Icon(
              imageVector = Icons.Default.FileDownload,
              contentDescription = null,
              tint = TextMuted,
              modifier = Modifier.size(18.dp)
            )
          }
        }
      }
    }

    Text(
      text = "SparkQuest v2.4 • Built with sensory-friendly neuro-design",
      style = MaterialTheme.typography.labelSmall.copy(
        color = TextFaint,
        fontSize = 11.sp
      ),
      modifier = Modifier.align(Alignment.CenterHorizontally)
    )

    Spacer(modifier = Modifier.height(16.dp))
  }
}

@Composable
private fun SpoonCard(
  state: SpoonState,
  isSelected: Boolean,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  val bg = if (isSelected) AccentSageSubtle else PaperRecessed
  val borderCol = if (isSelected) AccentSage.copy(alpha = 0.4f) else Color.Transparent

  Box(
    modifier = modifier
      .clip(RoundedCornerShape(10.dp))
      .background(bg)
      .border(1.dp, borderCol, RoundedCornerShape(10.dp))
      .clickable { onClick() }
      .padding(vertical = 10.dp, horizontal = 6.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = state.emoji, fontSize = 20.sp)
      Text(
        text = state.title,
        style = MaterialTheme.typography.labelMedium.copy(
          fontWeight = FontWeight.Bold,
          color = if (isSelected) SecondaryColor else TextCharcoal,
          fontSize = 11.sp
        )
      )
      Text(
        text = state.subtitle,
        style = MaterialTheme.typography.labelSmall.copy(
          color = TextFaint,
          fontSize = 9.sp
        )
      )
    }
  }
}

@Composable
private fun AccommodationRow(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  iconTint: Color,
  iconBg: Color,
  title: String,
  desc: String,
  trailing: @Composable () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(10.dp))
      .background(PaperBase)
      .padding(10.dp)
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
        Box(
          modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(iconBg),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = icon,
            contentDescription = title,
            tint = iconTint,
            modifier = Modifier.size(16.dp)
          )
        }
        Column {
          Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
              fontWeight = FontWeight.Bold,
              color = TextCharcoal
            )
          )
          Text(
            text = desc,
            style = MaterialTheme.typography.labelSmall.copy(
              color = TextFaint,
              fontSize = 10.sp
            )
          )
        }
      }
      trailing()
    }
  }
}

@Composable
private fun MilestoneCard(
  label: String,
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
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = label, style = MaterialTheme.typography.labelSmall.copy(color = TextMuted))
        Icon(imageVector = icon, contentDescription = label, tint = tint, modifier = Modifier.size(16.dp))
      }
      Text(
        text = value,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = TextCharcoal
        )
      )
      Text(
        text = sub,
        style = MaterialTheme.typography.labelSmall.copy(
          color = TextFaint,
          fontSize = 10.sp
        )
      )
    }
  }
}
