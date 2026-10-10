package com.example.ui.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.BadgeItem
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
fun TrophyRoomScreen(
  viewModel: QuestViewModel,
  modifier: Modifier = Modifier
) {
  val userProfile by viewModel.userProfile.collectAsState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(SurfaceBackground)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {

    // 1. XP & Level Progression Card
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
                .size(8.dp)
                .clip(CircleShape)
                .background(AccentSage)
            )
            Text(
              text = "Level ${userProfile.level} • ${userProfile.role}",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextCharcoal
              )
            )
          }

          Box(
            modifier = Modifier
              .clip(CircleShape)
              .background(AccentLilacSubtle)
              .border(1.dp, AccentLilac.copy(alpha = 0.3f), CircleShape)
              .padding(horizontal = 10.dp, vertical = 3.dp)
          ) {
            Text(
              text = "⚡ +${userProfile.todayXpGained} XP Today",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = PrimaryColor
              )
            )
          }
        }

        // Gradient XP Track
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(14.dp)
              .clip(RoundedCornerShape(7.dp))
              .background(PaperRecessed)
              .padding(2.dp)
          ) {
            val progress = (userProfile.currentXp.toFloat() / userProfile.nextLevelXp.toFloat()).coerceIn(0f, 1f)
            Box(
              modifier = Modifier
                .fillMaxWidth(fraction = progress)
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .background(
                  Brush.horizontalGradient(
                    listOf(AccentLilac, AccentSky, AccentSage)
                  )
                )
            )
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "%,d XP Current".format(userProfile.currentXp),
              style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
            )
            Text(
              text = "Next: Lvl 15 ${userProfile.nextLevelRole} (%,d XP)".format(userProfile.nextLevelXp),
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = TertiaryColor
              )
            )
          }
        }

        // Replay Party Button
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Every micro-step rewires your dopamine circuitry.",
            style = MaterialTheme.typography.bodySmall.copy(
              color = TextMuted,
              fontSize = 11.sp
            ),
            modifier = Modifier.weight(1f)
          )

          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .background(PaperBase)
              .border(1.dp, BorderMuted, RoundedCornerShape(8.dp))
              .clickable {
                viewModel.triggerCelebration()
                viewModel.triggerHaptic(force = true)
              }
              .padding(horizontal = 10.dp, vertical = 6.dp)
              .testTag("replay_party_button")
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Celebration,
                contentDescription = "Party",
                tint = PrimaryColor,
                modifier = Modifier.size(14.dp)
              )
              Text(
                text = "Replay Party",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = PrimaryColor
                )
              )
            }
          }
        }
      }
    }

    // 2. Streak Vault (ADHD Shame-Proof Armor) Hero Card
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperSurface),
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderMuted, RoundedCornerShape(16.dp))
    ) {
      Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Box(
              modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(AccentSageSubtle)
                .border(1.dp, AccentSage.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Shield,
                contentDescription = "Shield",
                tint = SecondaryColor,
                modifier = Modifier.size(24.dp)
              )
            }
            Column {
              Text(
                text = "Streak Vault",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = TextCharcoal
                )
              )
              Text(
                text = "ADHD Shame-Proof Armor",
                style = MaterialTheme.typography.labelSmall.copy(
                  color = SecondaryColor,
                  fontWeight = FontWeight.SemiBold
                )
              )
            }
          }

          Box(
            modifier = Modifier
              .clip(CircleShape)
              .background(PaperBase)
              .border(1.dp, BorderMuted, CircleShape)
              .padding(horizontal = 10.dp, vertical = 4.dp)
          ) {
            Text(
              text = "🛡️ ${userProfile.activeShields} Banked",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = SecondaryColor
              )
            )
          }
        }

        // Tactile Interactive Shield Orb
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
          contentAlignment = Alignment.Center
        ) {
          val infiniteTransition = rememberInfiniteTransition(label = "orbPulse")
          val pulseScale by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.05f,
            animationSpec = infiniteRepeatable(
              animation = tween(1400),
              repeatMode = RepeatMode.Reverse
            ),
            label = "pulseScale"
          )

          Box(
            modifier = Modifier
              .size(108.dp)
              .scale(pulseScale)
              .clip(CircleShape)
              .background(
                Brush.radialGradient(
                  listOf(
                    AccentSage.copy(alpha = 0.35f),
                    AccentLilac.copy(alpha = 0.2f),
                    Color.Transparent
                  )
                )
              )
              .padding(6.dp),
            contentAlignment = Alignment.Center
          ) {
            Box(
              modifier = Modifier
                .size(86.dp)
                .clip(CircleShape)
                .background(PaperBase)
                .border(1.dp, BorderMuted, CircleShape)
                .clickable {
                  viewModel.triggerCelebration()
                  viewModel.triggerHaptic(force = true)
                }
                .testTag("shield_orb_button"),
              contentAlignment = Alignment.Center
            ) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                  imageVector = Icons.Default.Security,
                  contentDescription = "Shield armed",
                  tint = SecondaryColor,
                  modifier = Modifier.size(34.dp)
                )
                Text(
                  text = "ARMED",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    fontSize = 11.sp,
                    letterSpacing = 0.5.sp
                  )
                )
              }
            }
          }
        }

        // Compassion First Banner
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PaperBase)
            .border(1.dp, BorderMuted, RoundedCornerShape(12.dp))
            .padding(12.dp)
        ) {
          Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Love",
                tint = SecondaryColor,
                modifier = Modifier.size(16.dp)
              )
              Text(
                text = "COMPASSION FIRST",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = SecondaryColor,
                  letterSpacing = 0.5.sp
                )
              )
            }
            Text(
              text = "Life happens. If executive dysfunction strikes, sensory overload peaks, or you rest, your shield automatically burns so your ${userProfile.streakDays}-day flame streak stays unharmed. Zero guilt. Zero resets.",
              style = MaterialTheme.typography.bodySmall.copy(
                color = TextMuted,
                lineHeight = 17.sp
              )
            )
          }
        }

        // Sub-Action: Next Shield Recharge
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Next Shield Recharge",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = TextCharcoal
              )
            )
            Text(
              text = "${userProfile.shieldRechargeDaysLogged} of ${userProfile.shieldRechargeTotal} Days Logged",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = SecondaryColor
              )
            )
          }

          // 3-Segment Bar
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Box(
              modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(CircleShape)
                .background(AccentSage)
            )
            Box(
              modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(CircleShape)
                .background(AccentSage)
            )
            Box(
              modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(CircleShape)
                .background(PaperRecessed)
                .border(0.5.dp, BorderMuted, CircleShape)
            )
          }

          Text(
            text = "Finish 1 more daily quest to earn +1 Shield",
            style = MaterialTheme.typography.labelSmall.copy(
              color = TextMuted,
              fontSize = 11.sp
            ),
            modifier = Modifier.align(Alignment.End)
          )
        }
      }
    }

    // 3. Hall of Sparks (Badges Grid)
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
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
            imageVector = Icons.Default.MilitaryTech,
            contentDescription = "Trophy",
            tint = TertiaryColor,
            modifier = Modifier.size(22.dp)
          )
          Text(
            text = "Hall of Sparks",
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = TextCharcoal
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
            text = "4 / 6 Claimed",
            style = MaterialTheme.typography.labelSmall.copy(color = TextMuted)
          )
        }
      }

      // 2-Column Badges Grid
      val badgePairs = viewModel.badges.chunked(2)
      badgePairs.forEach { pair ->
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          pair.forEach { badge ->
            BadgeCard(
              badge = badge,
              modifier = Modifier.weight(1f)
            )
          }
          if (pair.size == 1) {
            Spacer(modifier = Modifier.weight(1f))
          }
        }
      }
    }

    // 4. Celebratory Visual Reinforcement Banner
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = PaperSurface),
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderMuted, RoundedCornerShape(16.dp))
    ) {
      Row(
        modifier = Modifier.padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Coil loaded chest art with graceful fallback
        Box(
          modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(PaperRecessed)
            .border(1.dp, BorderMuted, RoundedCornerShape(10.dp)),
          contentAlignment = Alignment.Center
        ) {
          AsyncImage(
            model = userProfile.chestTipImageUrl,
            contentDescription = "Cozy glowing chest",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
          )
        }

        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "SELF-CARE TIP",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = TertiaryColor,
              letterSpacing = 0.5.sp
            )
          )
          Text(
            text = "Remember: 1% effort still beats 0%. Celebrate putting your shoes on or drinking half a glass.",
            style = MaterialTheme.typography.bodySmall.copy(
              color = TextCharcoal,
              lineHeight = 17.sp
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))
  }
}

@Composable
private fun BadgeCard(
  badge: BadgeItem,
  modifier: Modifier = Modifier
) {
  val badgeBg = if (badge.isUnlocked) PaperBase else PaperRecessed.copy(alpha = 0.7f)
  val badgeBorder = if (badge.isUnlocked) BorderMuted else BorderMuted.copy(alpha = 0.6f)

  Box(
    modifier = modifier
      .clip(RoundedCornerShape(14.dp))
      .background(badgeBg)
      .border(1.dp, badgeBorder, RoundedCornerShape(14.dp))
      .padding(12.dp)
  ) {
    Column(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Emoji icon in tinted square
        Box(
          modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
              when (badge.rarity) {
                "COMMON" -> AccentPeachSubtle
                "RARE" -> AccentLilacSubtle
                "EPIC" -> AccentPeachSubtle
                else -> PaperSurface
              }
            ),
          contentAlignment = Alignment.Center
        ) {
          Text(text = badge.emoji, fontSize = 20.sp)
        }

        // Rarity Tag
        Box(
          modifier = Modifier
            .clip(CircleShape)
            .background(
              when (badge.rarity) {
                "COMMON" -> AccentPeachSubtle
                "RARE" -> AccentLilacSubtle
                "EPIC" -> AccentPeachSubtle
                else -> PaperSurface
              }
            )
            .padding(horizontal = 7.dp, vertical = 2.dp)
        ) {
          Text(
            text = badge.rarity,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              color = when (badge.rarity) {
                "COMMON" -> TertiaryColor
                "RARE" -> PrimaryColor
                "EPIC" -> TertiaryColor
                else -> TextFaint
              }
            )
          )
        }
      }

      Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
          text = badge.title,
          style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = if (badge.isUnlocked) TextCharcoal else TextMuted
          ),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Text(
          text = badge.description,
          style = MaterialTheme.typography.bodySmall.copy(
            color = if (badge.isUnlocked) TextMuted else TextFaint,
            fontSize = 11.sp,
            lineHeight = 15.sp
          ),
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )
      }

      if (badge.progressFraction != null) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .clip(CircleShape)
            .background(PaperRecessed)
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth(fraction = badge.progressFraction)
              .fillMaxSize()
              .clip(CircleShape)
              .background(AccentPeach)
          )
        }
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        if (badge.isUnlocked) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Verified,
              contentDescription = "Unlocked",
              tint = if (badge.rarity == "RARE") PrimaryColor else SecondaryColor,
              modifier = Modifier.size(14.dp)
            )
            Text(
              text = "Unlocked",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = if (badge.rarity == "RARE") PrimaryColor else SecondaryColor,
                fontSize = 11.sp
              )
            )
          }
        } else {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Lock,
              contentDescription = "Locked",
              tint = TextMuted,
              modifier = Modifier.size(14.dp)
            )
            Text(
              text = badge.progressText ?: "Locked",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
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
