package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.UserProfile
import com.example.ui.theme.AccentLilac
import com.example.ui.theme.AccentLilacSubtle
import com.example.ui.theme.BorderMuted
import com.example.ui.theme.BorderSlate
import com.example.ui.theme.PaperSurface
import com.example.ui.theme.PrimaryColor
import com.example.ui.theme.SecondaryColor
import com.example.ui.theme.SurfaceBackground
import com.example.ui.theme.TertiaryColor
import com.example.ui.theme.TextCharcoal
import com.example.ui.theme.TextMuted

@Composable
fun SparkQuestHeader(
  screenSubtitle: String,
  userProfile: UserProfile,
  onProfileClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(SurfaceBackground.copy(alpha = 0.95f))
      .padding(horizontal = 16.dp, vertical = 8.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    // Top Row: Logo + App Name + Subtitle + Profile Avatar
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        // Spark Icon Emblem
        Box(
          modifier = Modifier
            .size(34.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(PrimaryColor),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.AutoAwesome,
            contentDescription = "SparkQuest Star",
            tint = Color(0xFFFFD6A5),
            modifier = Modifier.size(20.dp)
          )
        }

        Column {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Text(
              text = "SparkQuest",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
                letterSpacing = (-0.5).sp
              )
            )
            Text(
              text = screenSubtitle,
              style = MaterialTheme.typography.labelSmall.copy(
                color = TextMuted,
                fontWeight = FontWeight.Medium
              )
            )
          }
          Text(
            text = "Lvl ${userProfile.level} ${userProfile.role}",
            style = MaterialTheme.typography.labelSmall.copy(
              color = SecondaryColor,
              fontWeight = FontWeight.SemiBold
            )
          )
        }
      }

      // Profile avatar circle
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(CircleShape)
          .background(AccentLilacSubtle)
          .border(1.dp, BorderSlate, CircleShape)
          .clickable { onProfileClick() }
          .testTag("profile_avatar_button"),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.Person,
          contentDescription = "Profile",
          tint = PrimaryColor,
          modifier = Modifier.size(20.dp)
        )
      }
    }

    // Stat Capsule Strip
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(CircleShape)
        .background(PaperSurface)
        .border(1.dp, BorderMuted, CircleShape)
        .padding(horizontal = 16.dp, vertical = 7.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Streak Days
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(text = "🔥", fontSize = 13.sp)
          Text(
            text = "${userProfile.streakDays} Days",
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.SemiBold,
              color = TertiaryColor
            )
          )
        }

        // Divider
        Box(
          modifier = Modifier
            .width(1.dp)
            .height(14.dp)
            .background(BorderMuted)
        )

        // Active Shields
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(text = "🛡️", fontSize = 13.sp)
          Text(
            text = "${userProfile.activeShields} Active",
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.SemiBold,
              color = SecondaryColor
            )
          )
        }

        // Divider
        Box(
          modifier = Modifier
            .width(1.dp)
            .height(14.dp)
            .background(BorderMuted)
        )

        // XP
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(text = "⚡", fontSize = 13.sp)
          Text(
            text = "%,d XP".format(userProfile.currentXp),
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.SemiBold,
              color = PrimaryColor
            )
          )
        }
      }
    }
  }
}
