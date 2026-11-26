package com.novaplayer.x.settings.screens.about

import android.content.Context
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.novaplayer.x.core.common.extensions.appIcon
import com.novaplayer.x.core.ui.components.NextTopAppBar
import com.novaplayer.x.core.ui.components.rememberTvListFocusRequester
import com.novaplayer.x.core.ui.components.tvFocusDown
import com.novaplayer.x.core.ui.components.tvListFocus
import com.novaplayer.x.core.ui.designsystem.NextIcons
import com.novaplayer.x.core.ui.R

private const val GITHUB_URL = "https://github.com/asubilla/Nova-Player-X"

@Composable
fun AboutPreferencesScreen(viewModel: AboutPreferencesViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AboutPreferencesScreenContent(
        state = state,
        onAction = viewModel::onAction,
    )
}

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class,
)
@Composable
private fun AboutPreferencesScreenContent(
    state: AboutPreferencesUiState,
    onAction: (AboutPreferencesAction) -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val listFocusRequester = rememberTvListFocusRequester()
    Scaffold(
        topBar = {
            NextTopAppBar(
                title = stringResource(id = R.string.about_name),
                navigationIcon = {
                    FilledTonalIconButton(
                        onClick = {
                            onAction(AboutPreferencesAction.NavigateUp)
                        },
                        modifier = Modifier.tvFocusDown(target = listFocusRequester),
                    ) {
                        Icon(
                            imageVector = NextIcons.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_up),
                        )
                    }
                },
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .tvListFocus(focusRequester = listFocusRequester)
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 16.dp)
                .padding(vertical = 16.dp),
        ) {
            AboutApp(
                appVersion = state.appVersion,
                onGithubClick = {
                    uriHandler.openUriOrShowToast(
                        uri = GITHUB_URL,
                        context = context,
                    )
                },
                onLibrariesClick = { onAction(AboutPreferencesAction.OpenLibraries) },
            )
        }
    }
}

@Composable
fun AboutApp(
    appVersion: String,
    modifier: Modifier = Modifier,
    onGithubClick: () -> Unit,
    onLibrariesClick: () -> Unit,
) {
    val context = LocalContext.current
    val appIcon = remember { context.appIcon()?.asImageBitmap() }

    val colorPrimary = MaterialTheme.colorScheme.primaryContainer
    val colorTertiary = MaterialTheme.colorScheme.tertiaryContainer

    val transition = rememberInfiniteTransition()
    val fraction by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000),
            repeatMode = RepeatMode.Reverse,
        ),
    )
    val cornerRadius = 24.dp

    Column(
        modifier = modifier
            .padding(
                vertical = 16.dp,
                horizontal = 8.dp,
            )
            .drawWithCache {
                val cx = size.width - size.width * fraction
                val cy = size.height * fraction

                val gradient = Brush.radialGradient(
                    colors = listOf(
                        colorPrimary,
                        colorTertiary,
                    ),
                    center = Offset(
                        x = cx,
                        y = cy,
                    ),
                    radius = 800f,
                )

                onDrawBehind {
                    drawRoundRect(
                        brush = gradient,
                        cornerRadius = CornerRadius(
                            cornerRadius.toPx(),
                        ),
                    )
                }
            }
            .padding(all = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            appIcon?.let {
                Image(
                    bitmap = it,
                    contentDescription = "App Logo",
                    modifier = Modifier.size(48.dp).clip(shape = CircleShape),
                )
            }
            Column {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = appVersion,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(
                onClick = onLibrariesClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = .12f),
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = .12f),
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .weight(weight = 1f),
            ) { Text(text = stringResource(R.string.libraries)) }
            Button(
                onClick = onGithubClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    disabledContentColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = .12f),
                    containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = .12f),
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .weight(weight = 1f),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_github),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.github))
            }
        }
    }
}

internal fun UriHandler.openUriOrShowToast(uri: String, context: Context) {
    try {
        openUri(uri = uri)
    } catch (e: Exception) {
        android.widget.Toast.makeText(
            context,
            context.getString(R.string.error_opening_link),
            android.widget.Toast.LENGTH_SHORT,
        ).show()
    }
}
