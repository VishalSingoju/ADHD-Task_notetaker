package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentLilac
import com.example.ui.theme.BorderSlate
import com.example.ui.theme.OnPrimaryColor
import com.example.ui.theme.PaperBase
import com.example.ui.theme.TextCharcoal
import kotlinx.coroutines.delay
import kotlin.random.Random

private data class Particle(
  var x: Float,
  var y: Float,
  var vx: Float,
  var vy: Float,
  val size: Float,
  val color: Color,
  var rotation: Float,
  val vRot: Float,
  var alpha: Float
)

@Composable
fun ConfettiBurstOverlay(
  visible: Boolean,
  modifier: Modifier = Modifier
) {
  val colors = remember {
    listOf(
      Color(0xFF8B7EC8),
      Color(0xFF7FA890),
      Color(0xFFDF9982),
      Color(0xFF7EA6C7),
      Color(0xFFC1EDD2),
      Color(0xFFECE9F6)
    )
  }

  val particles = remember { mutableStateListOf<Particle>() }
  var animProgress by remember { mutableStateOf(0f) }

  LaunchedEffect(visible) {
    if (visible) {
      particles.clear()
      for (i in 0 until 50) {
        val angle = Random.nextFloat() * (Math.PI * 2).toFloat()
        val speed = Random.nextFloat() * 12f + 4f
        particles.add(
          Particle(
            x = 0.5f,
            y = 0.45f,
            vx = (kotlin.math.cos(angle.toDouble()) * speed).toFloat() * 0.003f,
            vy = (kotlin.math.sin(angle.toDouble()) * speed - 6f).toFloat() * 0.003f,
            size = Random.nextFloat() * 16f + 8f,
            color = colors.random(),
            rotation = Random.nextFloat() * 360f,
            vRot = (Random.nextFloat() - 0.5f) * 16f,
            alpha = 1f
          )
        )
      }

      val startTime = System.currentTimeMillis()
      while (System.currentTimeMillis() - startTime < 2400) {
        particles.forEach { p ->
          p.x += p.vx
          p.y += p.vy
          p.vy += 0.00035f // gentle gravity
          p.rotation += p.vRot
          p.alpha = maxOf(0f, p.alpha - 0.012f)
        }
        animProgress += 0.01f
        delay(16)
      }
    }
  }

  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    if (visible) {
      Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        particles.forEach { p ->
          if (p.alpha > 0f) {
            rotate(p.rotation, pivot = Offset(p.x * w, p.y * h)) {
              drawRect(
                color = p.color.copy(alpha = p.alpha),
                topLeft = Offset(p.x * w - p.size / 2, p.y * h - p.size / 2),
                size = Size(p.size, p.size * 0.7f)
              )
            }
          }
        }
      }
    }

    // Dopamine Floater Badge
    AnimatedVisibility(
      visible = visible,
      enter = fadeIn() + slideInVertically(initialOffsetY = { 60 }),
      exit = fadeOut() + slideOutVertically(targetOffsetY = { -60 })
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Box(
          modifier = Modifier
            .clip(CircleShape)
            .background(AccentLilac)
            .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text(text = "⚡", fontSize = 18.sp)
            Text(
              text = "+120 XP UNLOCKED!",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = OnPrimaryColor
              )
            )
          }
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(PaperBase)
            .border(1.dp, BorderSlate, RoundedCornerShape(20.dp))
            .padding(horizontal = 14.dp, vertical = 5.dp)
        ) {
          Text(
            text = "Orbit Completed! 4 of 4",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.SemiBold,
              color = TextCharcoal
            )
          )
        }
      }
    }
  }
}
