package com.example.lifecycleorientationsimpledemo.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.ScreenRotation
import androidx.compose.material.icons.rounded.Smartphone
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lifecycleorientationsimpledemo.ui.theme.LifeCycleOrientationSimpleDemoTheme
import com.example.lifecycleorientationsimpledemo.MainUiState
import com.example.lifecycleorientationsimpledemo.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreenContent(
        uiState = uiState,
        onIncrementCounter = viewModel::incrementCounter,
        onDecrementCounter = viewModel::decrementCounter,
        onResetCounter = viewModel::resetCounter,
        onClearLogs = viewModel::clearLogs,
        modifier = modifier,
    )
}

@Composable
fun MainScreenContent(
    uiState: MainUiState,
    onIncrementCounter: () -> Unit,
    onDecrementCounter: () -> Unit,
    onResetCounter: () -> Unit,
    onClearLogs: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        LandscapeLayout(
            uiState = uiState,
            onIncrementCounter = onIncrementCounter,
            onDecrementCounter = onDecrementCounter,
            onResetCounter = onResetCounter,
            onClearLogs = onClearLogs,
            modifier = modifier
        )
    } else {
        PortraitLayout(
            uiState = uiState,
            onIncrementCounter = onIncrementCounter,
            onDecrementCounter = onDecrementCounter,
            onResetCounter = onResetCounter,
            onClearLogs = onClearLogs,
            modifier = modifier
        )
    }
}

@Composable
private fun PortraitLayout(
    uiState: MainUiState,
    onIncrementCounter: () -> Unit,
    onDecrementCounter: () -> Unit,
    onResetCounter: () -> Unit,
    onClearLogs: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderCard(isLandscape = false)
        InteractiveControlsCard(
            uiState = uiState,
            onIncrementCounter = onIncrementCounter,
            onDecrementCounter = onDecrementCounter,
            onResetCounter = onResetCounter,
        )
        LifecycleLogsCard(
            logs = uiState.lifecycleLogs,
            onClearLogs = onClearLogs,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
private fun LandscapeLayout(
    uiState: MainUiState,
    onIncrementCounter: () -> Unit,
    onDecrementCounter: () -> Unit,
    onResetCounter: () -> Unit,
    onClearLogs: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HeaderCard(isLandscape = true)
            InteractiveControlsCard(
                uiState = uiState,
                onIncrementCounter = onIncrementCounter,
                onDecrementCounter = onDecrementCounter,
                onResetCounter = onResetCounter
            )
        }
        Column(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight()
        ) {
            LifecycleLogsCard(
                logs = uiState.lifecycleLogs,
                onClearLogs = onClearLogs,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun HeaderCard(
    isLandscape: Boolean,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Lifecycle & Orientation Demo",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Real-time state and event tracking",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = if (isLandscape) Icons.Rounded.ScreenRotation else Icons.Rounded.Smartphone,
                        contentDescription = "Orientation Indicator",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = if (isLandscape) "Landscape" else "Portrait",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun InteractiveControlsCard(
    uiState: MainUiState,
    onIncrementCounter: () -> Unit,
    onDecrementCounter: () -> Unit,
    onResetCounter: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Interactive Controls & State",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            // Counter Section
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Counter Value:",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = uiState.counter.toString(),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilledTonalButton(
                            onClick = onIncrementCounter,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Rounded.Add, contentDescription = "Increment", modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("+1")
                        }
                        OutlinedButton(
                            onClick = onDecrementCounter,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Rounded.Remove, contentDescription = "Decrement", modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("-1")
                        }
                        IconButton(onClick = onResetCounter) {
                            Icon(Icons.Rounded.Refresh, contentDescription = "Reset Counter")
                        }
                    }
                }
            }



            // Input Field Section

        }
    }
}

@Composable
fun LifecycleLogsCard(
    logs: List<String>,
    onClearLogs: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.size - 1)
        }
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Lifecycle Logs",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Badge(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ) {
                        Text(logs.size.toString())
                    }
                }
                TextButton(
                    onClick = onClearLogs,
                    enabled = logs.isNotEmpty()
                ) {
                    Icon(
                        Icons.Rounded.Delete,
                        contentDescription = "Clear logs",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("Clear")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (logs.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Rounded.Info,
                            contentDescription = null,
                            modifier = Modifier.size(36.dp),
                            tint = MaterialTheme.colorScheme.outline
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "No lifecycle events logged yet",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    itemsIndexed(logs) { index, log ->
                        LogItemRow(index = index + 1, logText = log)
                    }
                }
            }
        }
    }
}

@Composable
private fun LogItemRow(
    index: Int,
    logText: String
) {
    val isImportant = logText.contains("onCreate") || logText.contains("onDestroy")

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = if (isImportant) {
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
        } else {
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#$index",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.width(32.dp)
            )
            Text(
                text = logText,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(name = "Portrait", showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun MainScreenPortraitPreview() {
    LifeCycleOrientationSimpleDemoTheme {
        MainScreenContent(
            uiState = MainUiState(
                counter = 3,
                lifecycleLogs = listOf("onCreate called", "onStart called", "onResume called"),
            ),
            onIncrementCounter = {},
            onDecrementCounter = {},
            onResetCounter = {},
            onClearLogs = {}
        )
    }
}

@Preview(name = "Landscape", showBackground = true, widthDp = 740, heightDp = 360)
@Composable
fun MainScreenLandscapePreview() {
    LifeCycleOrientationSimpleDemoTheme {
        MainScreenContent(
            uiState = MainUiState(
                counter = 5,
                lifecycleLogs = listOf("onCreate called", "onStart called", "onResume called", "onPause called"),

            ),
            onIncrementCounter = {},
            onDecrementCounter = {},
            onResetCounter = {},
            onClearLogs = {}
        )
    }
}
