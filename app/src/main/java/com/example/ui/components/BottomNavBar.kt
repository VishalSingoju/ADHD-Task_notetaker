package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentLilac
import com.example.ui.theme.AccentLilacSubtle
import com.example.ui.theme.BorderMuted
import com.example.ui.theme.BorderSlate
import com.example.ui.theme.OnPrimaryColor
import com.example.ui.theme.PrimaryColor
import com.example.ui.theme.SurfaceBackground
import com.example.ui.theme.TextCharcoal
import com.example.ui.theme.TextMuted
import com.example.ui.viewmodel.NavigationTab

@Composable
fun SparkQuestBottomBar(
  currentTab: NavigationTab,
  onTabSelected: (NavigationTab) -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(SurfaceBackground.copy(alpha = 0.95f))
      .border(1.dp, BorderMuted)
      .windowInsetsPadding(WindowInsets.navigationBars)
      .height(68.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // 1. Quests
      BottomNavItem(
        title = "Quests",
        icon = Icons.Default.CheckCircle,
        isSelected = currentTab == NavigationTab.Quests,
        testTag = "tab_quests",
        onClick = { onTabSelected(NavigationTab.Quests) }
      )

      // 2. New Quest
      BottomNavItem(
        title = "New Quest",
        icon = Icons.Default.Add,
        isActionIcon = true,
        isSelected = currentTab == NavigationTab.NewQuest,
        testTag = "tab_new_quest",
        onClick = { onTabSelected(NavigationTab.NewQuest) }
      )

      // 3. Trophy
      BottomNavItem(
        title = "Trophy",
        icon = Icons.Default.EmojiEvents,
        isSelected = currentTab == NavigationTab.Trophy,
        testTag = "tab_trophy",
        onClick = { onTabSelected(NavigationTab.Trophy) }
      )

      // 4. Sync
      BottomNavItem(
        title = "Sync",
        icon = Icons.Default.CloudDone,
        isSelected = currentTab == NavigationTab.Sync,
        testTag = "tab_sync",
        onClick = { onTabSelected(NavigationTab.Sync) }
      )
    }
  }
}

@Composable
private fun BottomNavItem(
  title: String,
  icon: ImageVector,
  isSelected: Boolean,
  testTag: String,
  isActionIcon: Boolean = false,
  onClick: () -> Unit
) {
  val interactionSource = remember { MutableInteractionSource() }

  val itemModifier = if (isSelected) {
    Modifier
      .clip(RoundedCornerShape(12.dp))
      .background(AccentLilacSubtle)
      .border(1.dp, BorderSlate.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
  } else {
    Modifier.clip(RoundedCornerShape(12.dp))
  }

  Column(
    modifier = itemModifier
      .size(width = 66.dp, height = 54.dp)
      .clickable(
        interactionSource = interactionSource,
        indication = null
      ) { onClick() }
      .testTag(testTag),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    if (isActionIcon) {
      Box(
        modifier = Modifier
          .size(26.dp)
          .clip(CircleShape)
          .background(AccentLilac),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = title,
          tint = OnPrimaryColor,
          modifier = Modifier.size(18.dp)
        )
      }
    } else {
      Icon(
        imageVector = icon,
        contentDescription = title,
        tint = if (isSelected) PrimaryColor else TextMuted,
        modifier = Modifier.size(22.dp)
      )
    }

    Text(
      text = title,
      style = MaterialTheme.typography.labelSmall.copy(
        fontSize = 11.sp,
        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
        color = if (isSelected) PrimaryColor else TextMuted
      )
    )
  }
}
