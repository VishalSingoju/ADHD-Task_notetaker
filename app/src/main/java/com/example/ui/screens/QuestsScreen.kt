package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.QuestEntity
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
import com.example.ui.viewmodel.QuestViewModel

@Composable
fun QuestsScreen(
  viewModel: QuestViewModel,
  modifier: Modifier = Modifier
) {
  val quests by viewModel.quests.collectAsState()
  val selectedFilter by viewModel.selectedFilter.collectAsState()
  val currentTinyStep by viewModel.currentTinyStep.collectAsState()
  val userProfile by viewModel.userProfile.collectAsState()

  val completedCount = quests.count { it.isCompleted }
  val totalCount = maxOf(1, quests.size)
  val progressFraction = completedCount.toFloat() / totalCount.toFloat()

  val filteredQuests = when (selectedFilter) {
    "Low Spoon" -> quests.filter { it.isLowSpoon }
    "Hydration & Body" -> quests.filter {
      it.category.contains("Hydration", ignoreCase = true) ||
          it.category.contains("Fitness", ignoreCase = true) ||
          it.category.contains("Body", ignoreCase = true)
    }
    "Deep Focus" -> quests.filter { !it.isLowSpoon }
    else -> quests
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(SurfaceBackground)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {

    // 1. Top Sensory Haptic Micro-Bar (from Image 7)
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(PaperSurface)
        .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
        .padding(horizontal = 12.dp, vertical = 8.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Vibration,
          contentDescription = "Haptics",
          tint = SecondaryColor,
          modifier = Modifier.size(16.dp)
        )
        Text(
          text = "Haptic Micro-Pulse",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            color = TextCharcoal
          )
        )
        Box(
          modifier = Modifier
            .size(6.dp)
            .clip(CircleShape)
            .background(AccentSage)
        )
      }

      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .background(AccentLilacSubtle)
          .border(1.dp, BorderSlate, RoundedCornerShape(8.dp))
          .clickable { viewModel.triggerHaptic(force = true) }
          .padding(horizontal = 10.dp, vertical = 4.dp)
          .testTag("test_pulse_button")
      ) {
        Text(
          text = "Test Pulse",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            color = PrimaryColor
          )
        )
      }
    }

    // 2. Momentum Track Hero Card (Today's Orbit)
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperBase),
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderMuted, RoundedCornerShape(16.dp))
    ) {
      Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(8.dp)
                  .clip(CircleShape)
                  .background(AccentSage)
              )
              Text(
                text = "MOMENTUM TRACK",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = SecondaryColor,
                  letterSpacing = 1.sp
                )
              )
            }
            Text(
              text = "Today's Orbit",
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
            Text(
              text = if (completedCount == quests.size) {
                "All done! Beautiful momentum today."
              } else {
                "No guilt, just momentum. ${quests.size - completedCount} gentle quest left!"
              },
              style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
            )
          }

          // Circular Orbit Ring
          Box(
            modifier = Modifier.size(76.dp),
            contentAlignment = Alignment.Center
          ) {
            val animatedFraction by animateFloatAsState(
              targetValue = progressFraction,
              animationSpec = tween(600),
              label = "orbitProgress"
            )
            val ringColor by animateColorAsState(
              targetValue = if (progressFraction >= 1f) AccentSage else AccentLilac,
              label = "ringColor"
            )

            Canvas(modifier = Modifier.size(72.dp)) {
              // Background track
              drawCircle(
                color = PaperRecessed,
                style = Stroke(width = 7.dp.toPx())
              )
              // Foreground arc
              drawArc(
                color = ringColor,
                startAngle = -90f,
                sweepAngle = animatedFraction * 360f,
                useCenter = false,
                style = Stroke(width = 7.dp.toPx(), cap = StrokeCap.Round)
              )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "${(animatedFraction * 100).toInt()}%",
                style = MaterialTheme.typography.labelLarge.copy(
                  fontWeight = FontWeight.Bold,
                  color = PrimaryColor
                )
              )
              Text(
                text = "$completedCount of $totalCount",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 10.sp,
                  color = TextMuted
                )
              )
            }
          }
        }

        // Streak & Shield Guarantee Banner
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(PaperSurface)
            .border(1.dp, BorderMuted, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
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
              Text(text = "🔥", fontSize = 12.sp)
              Text(
                text = "${userProfile.streakDays}-Day Spark Streak",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = TertiaryColor
                )
              )
            }
            Box(
              modifier = Modifier
                .width(1.dp)
                .height(12.dp)
                .background(BorderMuted)
            )
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Text(text = "🛡️", fontSize = 12.sp)
              Text(
                text = "1 Primed Shield",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = SecondaryColor
                )
              )
            }
          }
        }
      }
    }

    // 3. Filter Chips Row
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      FilterChip(
        label = "All Quests",
        count = quests.size,
        isSelected = selectedFilter == "All Quests",
        onClick = { viewModel.setFilter("All Quests") }
      )
      FilterChip(
        label = "Low Spoon",
        emoji = "⚡",
        count = quests.count { it.isLowSpoon },
        isSelected = selectedFilter == "Low Spoon",
        onClick = { viewModel.setFilter("Low Spoon") }
      )
      FilterChip(
        label = "Hydration & Body",
        emoji = "💧",
        count = quests.count {
          it.category.contains("Hydration", true) || it.category.contains("Fitness", true)
        },
        isSelected = selectedFilter == "Hydration & Body",
        onClick = { viewModel.setFilter("Hydration & Body") }
      )
      FilterChip(
        label = "Deep Focus",
        emoji = "🧠",
        count = quests.count { !it.isLowSpoon },
        isSelected = selectedFilter == "Deep Focus",
        onClick = { viewModel.setFilter("Deep Focus") }
      )
    }

    // 4. Quest Cards List
    Column(
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      filteredQuests.forEach { quest ->
        QuestItemCard(
          quest = quest,
          onToggle = { viewModel.toggleQuest(quest) }
        )
      }
    }

    // 5. Demand Avoidance Rescue Toolkit (Roll for Next Tiny Step)
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperBase),
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderMuted, RoundedCornerShape(16.dp))
    ) {
      Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
              imageVector = Icons.Default.PsychologyAlt,
              contentDescription = "Rescue",
              tint = PrimaryColor,
              modifier = Modifier.size(24.dp)
            )
            Column {
              Text(
                text = "Feeling stuck or frozen?",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = TextCharcoal
                )
              )
              Text(
                text = "Demand avoidance rescue toolkit",
                style = MaterialTheme.typography.bodySmall.copy(color = TextMuted)
              )
            }
          }

          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(12.dp))
              .background(AccentSageSubtle)
              .border(1.dp, AccentSage.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = "Zero Guilt",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = SecondaryColor
              )
            )
          }
        }

        // Tiny Step Card
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
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Text(text = currentTinyStep.emoji, fontSize = 24.sp)
            Text(
              text = currentTinyStep.text,
              style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = TextCharcoal
              ),
              modifier = Modifier.weight(1f)
            )
          }
        }

        // Roll Button
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(PaperSurface)
            .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
            .clickable { viewModel.rollTinyStep() }
            .testTag("roll_tiny_step_button"),
          contentAlignment = Alignment.Center
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Casino,
              contentDescription = "Roll dice",
              tint = PrimaryColor,
              modifier = Modifier.size(20.dp)
            )
            Text(
              text = "Roll for Next Tiny Step",
              style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                color = PrimaryColor
              )
            )
          }
        }
      }
    }

    // 6. Home & Lock Screen Widget Companion Card
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
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Widgets,
              contentDescription = "Widget",
              tint = PrimaryColor,
              modifier = Modifier.size(20.dp)
            )
            Column {
              Text(
                text = "Home & Lock Screen Widget",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = TextCharcoal
                )
              )
              Text(
                text = "Live synced • 1-tap quick complete",
                style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
              )
            }
          }

          Box(
            modifier = Modifier
              .clip(CircleShape)
              .background(AccentSageSubtle)
              .padding(horizontal = 8.dp, vertical = 2.dp)
          ) {
            Text(
              text = "• Synced",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = SecondaryColor
              )
            )
          }
        }

        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PaperSurface)
            .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
            .padding(10.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "🔥 12d Streak • ⚡ SparkQuest Widget",
                style = MaterialTheme.typography.labelSmall.copy(
                  color = TextMuted,
                  fontSize = 11.sp
                )
              )
              Text(
                text = "Mom: Quick text or call...",
                style = MaterialTheme.typography.bodySmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = TextCharcoal
                )
              )
            }

            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(AccentPeachSubtle)
                .border(1.dp, AccentPeach.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                .clickable {
                  val momQuest = quests.find { it.title.contains("Mom", true) }
                  if (momQuest != null && !momQuest.isCompleted) {
                    viewModel.toggleQuest(momQuest)
                  }
                }
                .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
              Text(
                text = "✓ Quick Done",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = TertiaryColor
                )
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))
  }
}

@Composable
private fun FilterChip(
  label: String,
  count: Int,
  isSelected: Boolean,
  emoji: String? = null,
  onClick: () -> Unit
) {
  val bg = if (isSelected) AccentLilac else PaperSurface
  val textCol = if (isSelected) Color.White else TextMuted
  val badgeBg = if (isSelected) Color.White.copy(alpha = 0.25f) else PaperRecessed

  Box(
    modifier = Modifier
      .height(42.dp)
      .clip(CircleShape)
      .background(bg)
      .border(1.dp, if (isSelected) Color.Transparent else BorderMuted, CircleShape)
      .clickable { onClick() }
      .padding(horizontal = 14.dp),
    contentAlignment = Alignment.Center
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      if (emoji != null) {
        Text(text = emoji, fontSize = 12.sp)
      }
      Text(
        text = label,
        style = MaterialTheme.typography.labelMedium.copy(
          fontWeight = FontWeight.SemiBold,
          color = textCol
        )
      )
      Box(
        modifier = Modifier
          .clip(CircleShape)
          .background(badgeBg)
          .padding(horizontal = 6.dp, vertical = 1.dp)
      ) {
        Text(
          text = count.toString(),
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            color = textCol,
            fontSize = 11.sp
          )
        )
      }
    }
  }
}

@Composable
private fun QuestItemCard(
  quest: QuestEntity,
  onToggle: () -> Unit
) {
  val categoryColor = when {
    quest.category.contains("Hydration", true) -> AccentSage
    quest.category.contains("Skin", true) -> AccentLilac
    quest.category.contains("Fitness", true) || quest.category.contains("Movement", true) -> AccentPeach
    quest.category.contains("Family", true) || quest.category.contains("Social", true) -> AccentPeach
    else -> AccentLilac
  }

  val categorySubtle = when {
    quest.category.contains("Hydration", true) -> AccentSageSubtle
    quest.category.contains("Skin", true) -> AccentLilacSubtle
    else -> AccentPeachSubtle
  }

  val isHeroPending = !quest.isCompleted && quest.isExtraSpark

  val cardBorder = if (isHeroPending) {
    AccentPeach.copy(alpha = 0.6f)
  } else {
    BorderMuted
  }

  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = PaperBase),
    modifier = Modifier
      .fillMaxWidth()
      .border(if (isHeroPending) 1.5.dp else 1.dp, cardBorder, RoundedCornerShape(14.dp))
      .testTag("quest_card_${quest.id}")
  ) {
    Column {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Left category pill accent + text info
        Row(
          modifier = Modifier.weight(1f),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          // Left vertical colored pill indicator
          Box(
            modifier = Modifier
              .width(4.dp)
              .height(44.dp)
              .clip(RoundedCornerShape(2.dp))
              .background(categoryColor)
          )

          Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier.weight(1f)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .clip(CircleShape)
                  .background(categorySubtle)
                  .padding(horizontal = 8.dp, vertical = 2.dp)
              ) {
                Text(
                  text = "${quest.categoryEmoji} ${quest.category}",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = categoryColor,
                    fontSize = 11.sp
                  )
                )
              }
              Text(
                text = if (quest.isExtraSpark) "+${quest.xp} XP • Extra Spark!" else "+${quest.xp} XP",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = categoryColor,
                  fontSize = 11.sp
                )
              )
            }

            Text(
              text = quest.title,
              style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (quest.isCompleted) FontWeight.Normal else FontWeight.Bold,
                color = if (quest.isCompleted) TextFaint else TextCharcoal,
                textDecoration = if (quest.isCompleted) TextDecoration.LineThrough else TextDecoration.None
              ),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )

            if (!quest.isCompleted && quest.subText.isNotBlank()) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Alarm,
                  contentDescription = "Alert",
                  tint = AccentPeach,
                  modifier = Modifier.size(13.dp)
                )
                Text(
                  text = quest.subText,
                  style = MaterialTheme.typography.bodySmall.copy(
                    color = TextMuted,
                    fontSize = 11.sp
                  )
                )
              }
            }
          }
        }

        // Right side completion button
        if (quest.isCompleted) {
          Box(
            modifier = Modifier
              .size(46.dp)
              .clip(CircleShape)
              .background(categorySubtle)
              .border(1.dp, categoryColor.copy(alpha = 0.4f), CircleShape)
              .clickable { onToggle() },
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = "Completed",
              tint = categoryColor,
              modifier = Modifier.size(24.dp)
            )
          }
        } else {
          // Oversized 56dp tactile check circle
          Box(
            modifier = Modifier
              .size(56.dp)
              .clip(CircleShape)
              .background(PaperSurface)
              .border(1.dp, BorderMuted, CircleShape)
              .clickable { onToggle() }
              .testTag("quest_toggle_${quest.id}"),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.RadioButtonUnchecked,
              contentDescription = "Mark Complete",
              tint = TextMuted,
              modifier = Modifier.size(28.dp)
            )
          }
        }
      }

      // If hero pending: prompt banner below
      if (isHeroPending) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(PaperSurface)
            .border(1.dp, BorderMuted)
            .padding(horizontal = 12.dp, vertical = 7.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(
                imageVector = Icons.Default.TouchApp,
                contentDescription = "Tap",
                tint = PrimaryColor,
                modifier = Modifier.size(14.dp)
              )
              Text(
                text = "Tap button to complete & burst confetti",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Medium,
                  color = PrimaryColor,
                  fontSize = 11.sp
                )
              )
            }
            Text(
              text = "Low pressure",
              style = MaterialTheme.typography.labelSmall.copy(
                color = TextMuted,
                fontSize = 11.sp
              )
            )
          }
        }
      }
    }
  }
}
