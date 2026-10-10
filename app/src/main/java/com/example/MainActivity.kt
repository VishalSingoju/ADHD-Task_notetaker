package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.ui.components.ConfettiBurstOverlay
import com.example.ui.components.SparkQuestBottomBar
import com.example.ui.components.SparkQuestHeader
import com.example.ui.screens.NewQuestScreen
import com.example.ui.screens.OfflineSyncScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.QuestsScreen
import com.example.ui.screens.TrophyRoomScreen
import com.example.ui.theme.SparkQuestTheme
import com.example.ui.theme.SurfaceBackground
import com.example.ui.viewmodel.NavigationTab
import com.example.ui.viewmodel.QuestViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

  private val viewModel: QuestViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      SparkQuestTheme {
        SparkQuestApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun SparkQuestApp(viewModel: QuestViewModel) {
  val context = LocalContext.current
  val currentTab by viewModel.currentTab.collectAsState()
  val userProfile by viewModel.userProfile.collectAsState()
  val showCelebration by viewModel.showDopamineCelebration.collectAsState()

  // Handle system back button when in Profile screen
  BackHandler(enabled = currentTab == NavigationTab.Profile) {
    viewModel.navigateBackFromProfile()
  }

  // Observe toast messages
  LaunchedEffect(Unit) {
    viewModel.toastMessage.collectLatest { msg ->
      Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
  }

  val screenSubtitle = when (currentTab) {
    NavigationTab.Quests -> "Quests"
    NavigationTab.NewQuest -> "New Quest"
    NavigationTab.Trophy -> "Trophy Room"
    NavigationTab.Sync -> "Offline Sync"
    NavigationTab.Profile -> "Sensory Sanctuary"
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(SurfaceBackground)
  ) {
    Scaffold(
      modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.statusBars),
      containerColor = SurfaceBackground,
      topBar = {
        SparkQuestHeader(
          screenSubtitle = screenSubtitle,
          userProfile = userProfile,
          onProfileClick = { viewModel.setTab(NavigationTab.Profile) }
        )
      },
      bottomBar = {
        SparkQuestBottomBar(
          currentTab = currentTab,
          onTabSelected = { tab -> viewModel.setTab(tab) }
        )
      }
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
      ) {
        AnimatedContent(
          targetState = currentTab,
          transitionSpec = {
            fadeIn() togetherWith fadeOut()
          },
          label = "tabContent"
        ) { tab ->
          when (tab) {
            NavigationTab.Quests -> QuestsScreen(viewModel = viewModel)
            NavigationTab.NewQuest -> NewQuestScreen(viewModel = viewModel)
            NavigationTab.Trophy -> TrophyRoomScreen(viewModel = viewModel)
            NavigationTab.Sync -> OfflineSyncScreen(viewModel = viewModel)
            NavigationTab.Profile -> ProfileScreen(viewModel = viewModel)
          }
        }
      }
    }

    // Dopamine Confetti Burst Overlay
    ConfettiBurstOverlay(
      visible = showCelebration
    )
  }
}
