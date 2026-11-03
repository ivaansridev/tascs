@file:OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.ExperimentalFoundationApi::class, kotlin.time.ExperimentalTime::class, androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.ivaansridev.tascs.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.ivaansridev.tascs.model.Task
import dev.ivaansridev.tascs.storage.AppData
import dev.ivaansridev.tascs.storage.decodeAppData
import dev.ivaansridev.tascs.storage.encodeAppData
import dev.ivaansridev.tascs.storage.loadAppData
import dev.ivaansridev.tascs.storage.saveAppData
import dev.ivaansridev.tascs.util.dueLabel
import dev.ivaansridev.tascs.util.isOverdue
import dev.ivaansridev.tascs.util.toLocalDateUtc
import dev.ivaansridev.tascs.util.toUtcMillis
import io.github.vinceglb.confettikit.compose.ConfettiKit
import io.github.vinceglb.confettikit.core.Angle
import io.github.vinceglb.confettikit.core.Party
import io.github.vinceglb.confettikit.core.Position
import io.github.vinceglb.confettikit.core.emitter.Emitter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlin.time.Clock
import kotlin.time.Duration.Companion.milliseconds

private enum class Screen { Tasks, Settings, About }

private enum class SortField(val label: String) {
    DueDate("Due date"),
    Name("Name"),
    Created("Created"),
    Modified("Modified"),
}

private data class ConfettiBurst(val id: Int, val position: Position)

/** Material "sort" icon (not available in material-icons-core). */
private val SortIcon: ImageVector = ImageVector.Builder(
    name = "Sort",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(fill = SolidColor(Color.Black)) {
        moveTo(3f, 18f)
        horizontalLineTo(9f)
        verticalLineTo(16f)
        horizontalLineTo(3f)
        close()
        moveTo(3f, 6f)
        verticalLineTo(8f)
        horizontalLineTo(21f)
        verticalLineTo(6f)
        close()
        moveTo(3f, 13f)
        horizontalLineTo(15f)
        verticalLineTo(11f)
        horizontalLineTo(3f)
        close()
    }
}.build()

private const val TascsVersion = "1.0.0"
private val TascsLogoGold = Color(0xFFFFE97A)
private val TascsLogoBrown = Color(0xFF4A3F00)

private fun pathNodes(pathData: String) = PathParser().parsePathString(pathData).toNodes()

/** The Tascs task-list glyph, matching the launcher icon artwork. */
private val TascsLogo: ImageVector = ImageVector.Builder(
    name = "TascsLogo",
    defaultWidth = 108.dp,
    defaultHeight = 108.dp,
    viewportWidth = 512f,
    viewportHeight = 512f,
).apply {
    addPath(
        pathData = pathNodes(
            "M77.2,61.3 H174.8 A22.4,22.4 0 0 1 197.2,83.7 V136.5 " +
                "A22.4,22.4 0 0 1 174.8,158.9 H77.2 A22.4,22.4 0 0 1 54.8,136.5 " +
                "V83.7 A22.4,22.4 0 0 1 77.2,61.3 Z",
        ),
        stroke = SolidColor(TascsLogoGold),
        strokeLineWidth = 34.32f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
    )
    addPath(
        pathData = pathNodes("M111.5,110.1 L123.4,122.0 L143.2,95.6"),
        stroke = SolidColor(TascsLogoGold),
        strokeLineWidth = 26.4f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
    )
    addPath(
        pathData = pathNodes("M229.0,110.1 H429.6"),
        stroke = SolidColor(TascsLogoGold),
        strokeLineWidth = 44.88f,
        strokeLineCap = StrokeCap.Round,
    )
    addPath(
        pathData = pathNodes(
            "M126.0,260.6 m-48.8,0 a48.8,48.8 0 1,0 97.6,0 " +
                "a48.8,48.8 0 1,0 -97.6,0",
        ),
        stroke = SolidColor(TascsLogoGold),
        strokeLineWidth = 34.32f,
    )
    addPath(
        pathData = pathNodes("M229.0,260.6 H429.6"),
        stroke = SolidColor(TascsLogoGold),
        strokeLineWidth = 44.88f,
        strokeLineCap = StrokeCap.Round,
    )
    addPath(
        pathData = pathNodes(
            "M126.0,362.2 L172.5,395.9 L154.7,450.6 L97.3,450.6 L79.5,395.9 Z",
        ),
        stroke = SolidColor(TascsLogoGold),
        strokeLineWidth = 34.32f,
        strokeLineJoin = StrokeJoin.Round,
    )
    addPath(
        pathData = pathNodes("M229.0,411.1 H429.6"),
        stroke = SolidColor(TascsLogoGold),
        strokeLineWidth = 44.88f,
        strokeLineCap = StrokeCap.Round,
    )
}.build()

/** GitHub mark (Simple Icons path) for the About link. */
private val GithubIcon: ImageVector = ImageVector.Builder(
    name = "GitHub",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    addPath(
        pathData = pathNodes(
            "M12 .297c-6.63 0-12 5.373-12 12 0 5.303 3.438 9.8 8.205 11.385.6.113.82-.258.82-.577 " +
                "0-.285-.01-1.04-.015-2.04-3.338.724-4.042-1.61-4.042-1.61C4.422 18.07 3.633 17.7 " +
                "3.633 17.7c-1.087-.744.084-.729.084-.729 1.205.084 1.838 1.236 1.838 1.236 " +
                "1.07 1.835 2.809 1.305 3.495.998.108-.776.417-1.305.76-1.605-2.665-.3-5.466-1.332-5.466-5.93 " +
                "0-1.31.465-2.38 1.235-3.22-.135-.303-.54-1.523.105-3.176 0 0 1.005-.322 3.3 1.23 " +
                ".96-.267 1.98-.399 3-.405 1.02.006 2.04.138 3 .405 2.28-1.552 3.285-1.23 3.285-1.23 " +
                ".645 1.653.24 2.873.12 3.176.765.84 1.23 1.91 1.23 3.22 0 4.61-2.805 5.625-5.475 5.92 " +
                ".42.36.81 1.096.81 2.22 0 1.606-.015 2.896-.015 3.286 0 .315.21.69.825.57C20.565 22.092 24 17.592 24 12.297c0-6.627-5.373-12-12-12",
        ),
        fill = SolidColor(Color.Black),
    )
}.build()

private fun sortedTasks(tasks: List<Task>, field: SortField, ascending: Boolean): List<Task> {
    return when (field) {
        // The closest due date comes first when ascending, undated tasks last.
        SortField.DueDate -> tasks.sortedWith(
            if (ascending) {
                compareBy(nullsLast()) { it.dueDate }
            } else {
                compareBy(nullsLast(reverseOrder())) { it.dueDate }
            },
        )
        SortField.Name -> tasks.sortedWith(
            if (ascending) compareBy { it.title.lowercase() } else compareByDescending { it.title.lowercase() },
        )
        SortField.Created -> tasks.sortedWith(
            if (ascending) compareBy { it.createdAt } else compareByDescending { it.createdAt },
        )
        SortField.Modified -> tasks.sortedWith(
            if (ascending) compareBy { it.modifiedAt } else compareByDescending { it.modifiedAt },
        )
    }
}

private val HashtagPattern = Regex("#[\\p{L}\\p{N}_]+")

private fun extractHashtags(tasks: List<Task>): List<String> = tasks
    .flatMap { task ->
        buildList {
            HashtagPattern.findAll(task.title).forEach { add(it.value) }
            task.subtitle?.let { subtitle ->
                HashtagPattern.findAll(subtitle).forEach { add(it.value) }
            }
        }
    }
    .distinctBy { it.lowercase() }
    .sortedBy { it.lowercase() }

private fun Task.hasHashtag(tag: String): Boolean {
    val needle = tag.lowercase()
    fun String.containsTag(): Boolean =
        HashtagPattern.findAll(this).any { it.value.lowercase() == needle }
    return title.containsTag() || subtitle?.containsTag() == true
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TascsApp() {
    val tasks = remember { mutableStateListOf<Task>() }
    var nextTaskId by remember { mutableStateOf(1) }

    var screen by remember { mutableStateOf(Screen.Tasks) }
    var selectedHashtag by remember { mutableStateOf<String?>(null) }

    var searchActive by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var confettiId by remember { mutableStateOf(0) }
    val confettiBursts = remember { mutableStateListOf<ConfettiBurst>() }
    var showConfetti by remember { mutableStateOf(true) }
    var sortField by remember { mutableStateOf(SortField.DueDate) }
    var sortAscending by remember { mutableStateOf(true) }
    var showSortMenu by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var showTaskSheet by remember { mutableStateOf(false) }
    var editingTask by remember { mutableStateOf<Task?>(null) }
    var showCompleted by remember { mutableStateOf(true) }
    var pendingDelete by remember { mutableStateOf<Task?>(null) }

    // Bulk selection (long-press a task to enter).
    var selectionMode by remember { mutableStateOf(false) }
    val selectedTaskIds = remember { mutableStateListOf<Int>() }
    var showBulkDeleteConfirm by remember { mutableStateOf(false) }

    var blurEnabled by remember { mutableStateOf(true) }

    var dataLoaded by remember { mutableStateOf(false) }

    // Load saved data on first launch, keeping the loading animation visible
    // long enough to be seen.
    LaunchedEffect(Unit) {
        val start = Clock.System.now()
        loadAppData()?.let { saved ->
            tasks.clear()
            tasks.addAll(saved.tasks)
            nextTaskId = saved.nextTaskId
        }
        val elapsed = Clock.System.now() - start
        val minimum = 600.milliseconds
        if (elapsed < minimum) delay(minimum - elapsed)
        dataLoaded = true
    }

    // Auto-save whenever task data changes (never before the first load).
    LaunchedEffect(dataLoaded, tasks.toList(), nextTaskId) {
        if (!dataLoaded) return@LaunchedEffect
        saveAppData(
            AppData(
                tasks = tasks.toList(),
                nextTaskId = nextTaskId,
            ),
        )
    }

    fun celebrate(position: Position) {
        if (!showConfetti) return
        confettiBursts.add(ConfettiBurst(id = confettiId++, position = position))
    }

    fun addExampleTasks() {
        val now = Clock.System.now().toEpochMilliseconds()
        val exampleTitles = listOf(
            "Buy groceries #shopping",
            "Call dentist #health",
            "Finish report #work",
            "Walk the dog #pets",
            "Pay bills #finance",
            "Read a book #personal",
            "Clean kitchen #home",
            "Meeting with team #work",
            "Pick up dry cleaning #errands",
            "Water plants #home",
            "Schedule vacation #personal",
            "Fix leaky faucet #home",
            "Learn Kotlin #learning",
            "Backup photos #tech",
            "Organize closet #home",
            "Plan weekend trip #personal",
            "Review pull requests #work",
            "Update dependencies #tech",
            "Meditate #health",
            "Write blog post #writing",
            "Call mom #personal",
            "Buy birthday gift #shopping",
            "Renew passport #errands",
            "Clean car #errands",
            "Practice piano #learning",
            "Check tire pressure #errands",
            "Organize desktop #tech",
            "Plan meals for week #home",
            "Stretch break #health",
            "Review goals #personal",
        )
        repeat(30) { index ->
            val title = exampleTitles[index % exampleTitles.size]
            tasks.add(
                Task(
                    id = nextTaskId,
                    title = title,
                    subtitle = "Example #task${index + 1}",
                    dueDate = LocalDate(2026, 11, 1 + (index % 28)),
                    createdAt = now + index,
                    modifiedAt = now + index,
                ),
            )
            nextTaskId++
        }
    }

    // Show the Material expressive loading indicator while storage is read.
    if (!dataLoaded) {
        LoadingScreen()
        return
    }

    val hashtags = remember(tasks.toList()) { extractHashtags(tasks) }

    LaunchedEffect(hashtags, selectedHashtag) {
        if (selectedHashtag != null && selectedHashtag !in hashtags) {
            selectedHashtag = null
        }
    }

    val activeHashtag = selectedHashtag
    val visibleTasks = sortedTasks(
        tasks = tasks.filter { task ->
            val matchesCompleted = showCompleted || !task.done
            val matchesSearch = searchQuery.isBlank() ||
                task.title.contains(searchQuery, ignoreCase = true) ||
                (task.subtitle?.contains(searchQuery, ignoreCase = true) == true)
            val matchesHashtag = activeHashtag == null || task.hasHashtag(activeHashtag)
            matchesCompleted && matchesSearch && matchesHashtag
        },
        field = sortField,
        ascending = sortAscending,
    )
    val completedCount = tasks.count { it.done }
    val deleting = pendingDelete
    val onTasks = screen == Screen.Tasks

    // Auto-focus the search field when it appears.
    if (searchActive) {
        androidx.compose.runtime.LaunchedEffect(searchActive) {
            delay(150)
            focusRequester.requestFocus()
        }
    }

    val overlayOpen = showSortMenu || showTaskSheet
    val blurRadius by animateDpAsState(
        targetValue = if (overlayOpen && blurEnabled) 12.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "overlay-blur",
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                HashtagDrawerContent(
                    hashtags = hashtags,
                    selectedHashtag = selectedHashtag,
                    onSelectHashtag = { tag ->
                        selectedHashtag = tag
                        scope.launch { drawerState.close() }
                    },
                )
            }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(blurRadius),
            ) {
                Scaffold(
                    topBar = {
                        if (onTasks) {
                            if (selectionMode) {
                                CenterAlignedTopAppBar(
                                    title = { Text("${selectedTaskIds.size} selected") },
                                    navigationIcon = {
                                        IconButton(
                                            onClick = {
                                                selectionMode = false
                                                selectedTaskIds.clear()
                                            },
                                        ) {
                                            Icon(
                                                Icons.Filled.Close,
                                                contentDescription = "Cancel selection",
                                            )
                                        }
                                    },
                                    actions = {
                                        IconButton(
                                            onClick = { showBulkDeleteConfirm = true },
                                        ) {
                                            Icon(
                                                Icons.Filled.Delete,
                                                contentDescription = "Delete selected",
                                                tint = MaterialTheme.colorScheme.error,
                                            )
                                        }
                                    },
                                )
                            } else {
                                CenterAlignedTopAppBar(
                                    title = { Text(selectedHashtag ?: "Tascs") },
                                    navigationIcon = {
                                        IconButton(
                                            onClick = { scope.launch { drawerState.open() } },
                                        ) {
                                            Icon(Icons.Filled.Menu, contentDescription = "Hashtags")
                                        }
                                    },
                                    actions = {
                                        if (selectedHashtag != null) {
                                            IconButton(
                                                onClick = { selectedHashtag = null },
                                            ) {
                                                Icon(
                                                    Icons.Filled.Refresh,
                                                    contentDescription = "Show all tasks",
                                                    tint = MaterialTheme.colorScheme.primary,
                                                )
                                            }
                                        }
                                        IconButton(onClick = { screen = Screen.Settings }) {
                                            Icon(Icons.Filled.Settings, contentDescription = "Settings")
                                        }
                                    },
                                )
                            }
                        } else {
                            TopAppBar(
                                title = {
                                    Text(if (screen == Screen.About) "About" else "Settings")
                                },
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            screen = if (screen == Screen.About) {
                                                Screen.Settings
                                            } else {
                                                Screen.Tasks
                                            }
                                        },
                                    ) {
                                        Icon(
                                            Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back",
                                        )
                                    }
                                },
                            )
                        }
                    },
        bottomBar = {
            if (!onTasks) {
                // Nothing on the settings screen.
            } else if (deleting != null) {
                DeleteConfirmBar(
                    task = deleting,
                    onCancel = { pendingDelete = null },
                    onConfirm = {
                        tasks.removeAll { it.id == deleting.id }
                        pendingDelete = null
                    },
                )
            } else {
                // Search lives inside the pill tab at the bottom now.
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            androidx.compose.animation.AnimatedContent(
                targetState = screen,
                transitionSpec = {
                    val forward = targetState.ordinal > initialState.ordinal
                    // Expressive spring-based slide: quick pull, gentle settle
                    val fadeSpec = tween<Float>(160, 0, FastOutSlowInEasing)
                    if (forward) {
                        (slideInHorizontally { it / 4 } + fadeIn(fadeSpec)) togetherWith
                            (slideOutHorizontally { -it / 4 } + fadeOut(fadeSpec))
                    } else {
                        (slideInHorizontally { -it / 4 } + fadeIn(fadeSpec)) togetherWith
                            (slideOutHorizontally { it / 4 } + fadeOut(fadeSpec))
                    }
                },
                label = "screen",
            ) { target ->
                when (target) {
                    Screen.Tasks -> TasksContent(
                        searchActive = searchActive,
                        searchQuery = searchQuery,
                        onSearchQueryChange = { searchQuery = it },
                        focusRequester = focusRequester,
                        showCompleted = showCompleted,
                        onToggleShowCompleted = { showCompleted = !showCompleted },
                        searchToggle = {
                            searchActive = !searchActive
                            if (!searchActive) searchQuery = ""
                        },
                        tasks = visibleTasks,
                        emptyMessage = when {
                            selectedHashtag != null -> "No tasks tagged $selectedHashtag."
                            !showCompleted -> "No open tasks. Everything is done."
                            else -> "Nothing here yet. Tap + to add a task."
                        },
                        onToggle = { task, origin ->
                            val index = tasks.indexOfFirst { it.id == task.id }
                            if (index >= 0) {
                                val becomingDone = !tasks[index].done
                                tasks[index] = tasks[index].copy(
                                    done = becomingDone,
                                    modifiedAt = Clock.System.now().toEpochMilliseconds(),
                                )
                                if (becomingDone) {
                                    celebrate(Position.Absolute(origin.x, origin.y))
                                }
                            }
                        },
                        onRequestDelete = { pendingDelete = it },
                        onEditTask = { task ->
                            editingTask = task
                            showTaskSheet = true
                        },
                        onAddTask = {
                            editingTask = null
                            showTaskSheet = true
                        },
                        sortField = sortField,
                        sortAscending = sortAscending,
                        sortMenuOpen = showSortMenu,
                        onToggleSortMenu = { showSortMenu = !showSortMenu },
                        onDismissSortMenu = { showSortMenu = false },
                        onSortFieldChange = { sortField = it },
                        onSortDirectionChange = { sortAscending = it },
                        sortActive = sortField != SortField.DueDate || !sortAscending,
                        selectedHashtag = selectedHashtag,
                        onSelectHashtag = { selectedHashtag = it },
                        selectionMode = selectionMode,
                        selectedTaskIds = selectedTaskIds,
                        onEnterSelection = { task ->
                            selectionMode = true
                            selectedTaskIds.clear()
                            selectedTaskIds.add(task.id)
                        },
                        onToggleSelection = { task ->
                            if (selectedTaskIds.contains(task.id)) {
                                selectedTaskIds.remove(task.id)
                            } else {
                                selectedTaskIds.add(task.id)
                            }
                            if (selectedTaskIds.isEmpty()) selectionMode = false
                        },
                    )

                    Screen.Settings -> SettingsContent(
                        showCompleted = showCompleted,
                        onShowCompletedChange = { showCompleted = it },
                        showConfetti = showConfetti,
                        onShowConfettiChange = { showConfetti = it },
                        blurEnabled = blurEnabled,
                        onBlurEnabledChange = { blurEnabled = it },
                        completedCount = completedCount,
                        totalCount = tasks.size,
                        onClearCompleted = { tasks.removeAll { it.done } },
                        onImportJson = { json ->
                            decodeAppData(json)?.let { loaded ->
                                tasks.clear()
                                tasks.addAll(loaded.tasks)
                                nextTaskId = loaded.nextTaskId
                            }
                        },
                        onExportJson = {
                            encodeAppData(
                                AppData(
                                    tasks = tasks.toList(),
                                    nextTaskId = nextTaskId,
                                ),
                            )
                        },
                        onAddExampleTasks = { addExampleTasks() },
                        onOpenAbout = { screen = Screen.About },
                    )

                    Screen.About -> AboutContent()
                }
            }
        }
    }
    }
    }
    }
    ConfettiBurstsOverlay(
        bursts = confettiBursts,
        onFinished = { id -> confettiBursts.removeAll { it.id == id } },
    )

    if (showBulkDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showBulkDeleteConfirm = false },
            title = {
                Text(
                    if (selectedTaskIds.size == 1) {
                        "Delete 1 task?"
                    } else {
                        "Delete ${selectedTaskIds.size} tasks?"
                    },
                )
            },
            text = { Text("This cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        tasks.removeAll { it.id in selectedTaskIds }
                        selectedTaskIds.clear()
                        selectionMode = false
                        showBulkDeleteConfirm = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error,
                    ),
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showBulkDeleteConfirm = false }) {
                    Text("Cancel")
                }
            },
        )
    }

    if (showTaskSheet) {
        TaskEditorSheet(
            task = editingTask,
            onDismiss = {
                showTaskSheet = false
                editingTask = null
            },
            onSave = { title, subtitle, dueDate ->
                val existing = editingTask
                if (existing == null) {
                    val now = Clock.System.now().toEpochMilliseconds()
                    tasks.add(
                        Task(
                            id = nextTaskId,
                            title = title,
                            subtitle = subtitle,
                            dueDate = dueDate,
                            createdAt = now,
                            modifiedAt = now,
                        ),
                    )
                    nextTaskId++
                } else {
                    val index = tasks.indexOfFirst { it.id == existing.id }
                    if (index >= 0) {
                        tasks[index] = tasks[index].copy(
                            title = title,
                            subtitle = subtitle,
                            dueDate = dueDate,
                            modifiedAt = Clock.System.now().toEpochMilliseconds(),
                        )
                    }
                }
                showTaskSheet = false
                editingTask = null
            },
            onDelete = editingTask?.let { existing ->
                {
                    pendingDelete = existing
                    showTaskSheet = false
                    editingTask = null
                }
            },
        )
    }

}

@Composable
private fun TasksContent(
    searchActive: Boolean,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    focusRequester: FocusRequester,
    showCompleted: Boolean,
    onToggleShowCompleted: () -> Unit,
    searchToggle: () -> Unit,
    tasks: List<Task>,
    emptyMessage: String,
    onToggle: (Task, Offset) -> Unit,
    onRequestDelete: (Task) -> Unit,
    onEditTask: (Task) -> Unit,
    onAddTask: () -> Unit,
    sortField: SortField,
    sortAscending: Boolean,
    sortMenuOpen: Boolean,
    onToggleSortMenu: () -> Unit,
    onDismissSortMenu: () -> Unit,
    onSortFieldChange: (SortField) -> Unit,
    onSortDirectionChange: (Boolean) -> Unit,
    sortActive: Boolean,
    selectedHashtag: String?,
    onSelectHashtag: (String?) -> Unit,
    selectionMode: Boolean,
    selectedTaskIds: List<Int>,
    onEnterSelection: (Task) -> Unit,
    onToggleSelection: (Task) -> Unit,
) {
    val listState = rememberLazyListState()
    val transition = updateTransition(true, label = "tasks-enter")
    val staggerDelay by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(300, 0, FastOutSlowInEasing),
        label = "stagger",
    )
    var fabExpanded by remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
        var previousIndex = listState.firstVisibleItemIndex
        var previousOffset = listState.firstVisibleItemScrollOffset
        snapshotFlow {
            listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset
        }.collect { (index, offset) ->
            when {
                index > previousIndex || (index == previousIndex && offset > previousOffset) ->
                    fabExpanded = false
                index < previousIndex || (index == previousIndex && offset < previousOffset) ->
                    fabExpanded = true
            }
            previousIndex = index
            previousOffset = offset
        }
    }

    val compact = !fabExpanded
    val buttonSize by animateDpAsState(
        targetValue = if (compact) BottomButtonCompactSize else BottomButtonExpandedSize,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "bottom-button-size",
    )
    val buttonIconSize by animateDpAsState(
        targetValue = if (compact) BottomButtonCompactIcon else BottomButtonExpandedIcon,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "bottom-button-icon",
    )
    val fabInFrame by animateFloatAsState(
        targetValue = if (compact) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "fab-in-frame",
    )
    val fabShadow by animateDpAsState(
        targetValue = if (compact) 0.dp else 6.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "fab-shadow",
    )

    val frameHeight = buttonSize + BottomBarPadding * 2
    val frameWidth = BottomBarPadding * 2 + buttonSize * 3 + BottomBarGap * 2 +
        (buttonSize + BottomBarGap) * fabInFrame
    // Keep the list's end padding stable while the buttons shrink, otherwise the
    // content would shift every time the compact/expanded state flips.
    val bottomInset = BottomBarBottomPadding + BottomButtonExpandedSize +
        BottomBarPadding * 2 + 12.dp

    // Two captured copies let the bottom strip ramp from a light blur to a
    // stronger one at the very bottom.
    val softBlurLayer = rememberGraphicsLayer()
    val strongBlurLayer = rememberGraphicsLayer()
    val density = LocalDensity.current
    LaunchedEffect(density) {
        val soft = with(density) { 5.dp.toPx() }
        val strong = with(density) { 11.dp.toPx() }
        softBlurLayer.renderEffect =
            BlurEffect(soft, soft, TileMode.Clamp).takeIf { it.isSupported() }
        strongBlurLayer.renderEffect =
            BlurEffect(strong, strong, TileMode.Clamp).takeIf { it.isSupported() }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val expandedFabCenterX = maxWidth / 2 - 16.dp - buttonSize / 2
        val compactFabCenterX = frameWidth / 2 - BottomBarPadding - buttonSize / 2
        val fabCenterX = expandedFabCenterX +
            (compactFabCenterX - expandedFabCenterX) * fabInFrame
        val expandedFabCenterY = BottomBarBottomPadding + frameHeight + 4.dp + buttonSize / 2
        val compactFabCenterY = BottomBarBottomPadding + frameHeight / 2
        val fabCenterY = expandedFabCenterY +
            (compactFabCenterY - expandedFabCenterY) * fabInFrame

        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    softBlurLayer.record { this@drawWithContent.drawContent() }
                    strongBlurLayer.record { this@drawWithContent.drawContent() }
                    drawContent()
                },
        ) {
        Column(modifier = Modifier.fillMaxSize()) {
        // Search bar under the header, animated open and closed.
        AnimatedVisibility(
            visible = searchActive,
            enter = expandVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow,
                ),
                expandFrom = Alignment.Top,
            ) + fadeIn(),
            exit = shrinkVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow,
                ),
                shrinkTowards = Alignment.Top,
            ) + fadeOut(),
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text("Search tasks") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .focusRequester(focusRequester),
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Filled.Close, contentDescription = "Clear")
                        }
                    }
                },
            )
        }

        // Tasks (fill remaining space). The FAB is overlaid here so it stays
        // above both bottom bars instead of covering them.
        Box(modifier = Modifier.weight(1f)) {
            if (tasks.isEmpty()) {
                EmptyState(message = emptyMessage)
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = bottomInset + 8.dp),
                ) {
                    itemsIndexed(tasks, key = { index, task -> task.id }) { index, task ->
                        val itemProgress = (staggerDelay * 20f - index).coerceIn(0f, 1f)
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItem()
                                .graphicsLayer {
                                    alpha = itemProgress
                                    translationY = (1f - itemProgress) * 24f
                                    scaleX = 0.96f + itemProgress * 0.04f
                                    scaleY = 0.96f + itemProgress * 0.04f
                                },
                        ) {
                            if (selectionMode) {
                                TaskRow(
                                    task = task,
                                    onToggle = { origin -> onToggle(task, origin) },
                                    onClick = { onEditTask(task) },
                                    onLongClick = { onEnterSelection(task) },
                                    selectedHashtag = selectedHashtag,
                                    onSelectHashtag = onSelectHashtag,
                                    selectionMode = true,
                                    selected = task.id in selectedTaskIds,
                                    onToggleSelection = { onToggleSelection(task) },
                                )
                            } else {
                                SwipeToDelete(onRequestDelete = { onRequestDelete(task) }) {
                                    TaskRow(
                                        task = task,
                                        onToggle = { origin -> onToggle(task, origin) },
                                        onClick = { onEditTask(task) },
                                        onLongClick = { onEnterSelection(task) },
                                        selectedHashtag = selectedHashtag,
                                        onSelectHashtag = onSelectHashtag,
                                        selectionMode = false,
                                        selected = false,
                                        onToggleSelection = {},
                                    )
                                }
                            }
                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                            )
                        }
                    }
                }
            }
        }
        }
        }

        // Bottom-of-screen blur: a softly faded strip of the task content
        // behind the navbar. The navbar itself stays fully opaque.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    // Follow the animated navbar height so the strip shrinks
                    // when the bar compacts during scrolling.
                    val bleed = 28.dp * (buttonSize / BottomButtonExpandedSize)
                    val bandHeight = (BottomBarBottomPadding + frameHeight + bleed).toPx()
                    val bandTop = (size.height - bandHeight).coerceAtLeast(0f)
                    if (bandTop >= size.height) return@drawBehind

                    val bleedPx = bleed.toPx()
                    // Soft blur fades in across the whole strip; the stronger
                    // one is at full strength right where the navbar starts.
                    drawFadedLayer(
                        layer = softBlurLayer,
                        top = bandTop,
                        fadeEnd = bandTop + bleedPx * 1.6f,
                        bottom = size.height,
                    )
                    drawFadedLayer(
                        layer = strongBlurLayer,
                        top = bandTop,
                        fadeEnd = bandTop + bleedPx,
                        bottom = size.height,
                    )
                },
        )

        // Frame around the three actions; it grows when the FAB slides in.
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = -BottomBarBottomPadding),
        ) {
            PillTab(
                showCompleted = showCompleted,
                onToggleShowCompleted = onToggleShowCompleted,
                searchActive = searchActive,
                onSearch = searchToggle,
                onSort = onToggleSortMenu,
                sortActive = sortActive,
                sortField = sortField,
                sortAscending = sortAscending,
                sortMenuOpen = sortMenuOpen,
                onDismissSortMenu = onDismissSortMenu,
                onSortFieldChange = onSortFieldChange,
                onSortDirectionChange = onSortDirectionChange,
                buttonSize = buttonSize,
                buttonIconSize = buttonIconSize,
                trailingSlotWidth = (buttonSize + BottomBarGap) * fabInFrame,
                fabInFrame = fabInFrame,
            )
        }

        ExpressiveCircleButton(
            selected = false,
            icon = Icons.Filled.Add,
            contentDescription = "Add task",
            onClick = onAddTask,
            size = buttonSize,
            iconSize = buttonIconSize,
            startFrameBlend = 0f,
            endFrameBlend = fabInFrame.coerceIn(0f, 1f),
            backgroundOverride = MaterialTheme.colorScheme.primary,
            contentColorOverride = MaterialTheme.colorScheme.onPrimary,
            shadowElevation = fabShadow,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(x = fabCenterX, y = buttonSize / 2 - fabCenterY),
        )
    }
}

/**
 * Draws [layer] inside a horizontal band, fading its top edge out with a
 * destination-in gradient so the blur blends into the sharp content above.
 */
private fun DrawScope.drawFadedLayer(
    layer: GraphicsLayer,
    top: Float,
    fadeEnd: Float,
    bottom: Float,
) {
    val clipTop = top.coerceIn(0f, bottom)
    if (bottom <= clipTop) return
    val fadeBottom = fadeEnd
        .coerceAtLeast(clipTop + 1f)
        .coerceAtMost(bottom)

    drawIntoCanvas { canvas ->
        canvas.saveLayer(
            Rect(0f, clipTop, size.width, bottom),
            Paint(),
        )
        clipRect(left = 0f, top = clipTop, right = size.width, bottom = bottom) {
            drawLayer(layer)
            drawRect(
                brush = Brush.verticalGradient(
                    0f to Color.Transparent,
                    1f to Color.White,
                    startY = clipTop,
                    endY = fadeBottom,
                ),
                blendMode = BlendMode.DstIn,
            )
        }
        canvas.restore()
    }
}

/** Bottom action frame: sort, show completed and search as matching circles. */
@Composable
private fun PillTab(
    showCompleted: Boolean,
    onToggleShowCompleted: () -> Unit,
    searchActive: Boolean,
    onSearch: () -> Unit,
    onSort: () -> Unit,
    sortActive: Boolean,
    sortField: SortField,
    sortAscending: Boolean,
    sortMenuOpen: Boolean,
    onDismissSortMenu: () -> Unit,
    onSortFieldChange: (SortField) -> Unit,
    onSortDirectionChange: (Boolean) -> Unit,
    buttonSize: Dp,
    buttonIconSize: Dp,
    trailingSlotWidth: Dp,
    fabInFrame: Float,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(percent = 50),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        tonalElevation = 2.dp,
    ) {
        Row(
            modifier = Modifier.padding(BottomBarPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
        // Sort button with expressive press feedback and dropdown menu.
        ExpressiveCircleButton(
            selected = sortActive || sortMenuOpen,
            icon = if (sortMenuOpen) Icons.Filled.Close else SortIcon,
            contentDescription = if (sortMenuOpen) "Close sort menu" else "Sort tasks",
            onClick = onSort,
            size = buttonSize,
            iconSize = buttonIconSize,
            startFrameBlend = 1f,
            endFrameBlend = 0f,
        ) {
            DropdownMenu(
                expanded = sortMenuOpen,
                onDismissRequest = onDismissSortMenu,
                modifier = Modifier.width(212.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                SortDirectionPill(
                    ascending = sortAscending,
                    onChange = onSortDirectionChange,
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                )
                SortField.entries.forEach { option ->
                    val highlighted = option == sortField
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSortFieldChange(option) }
                            .padding(horizontal = 12.dp, vertical = 9.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = option.label,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (highlighted) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            },
                            modifier = Modifier.weight(1f),
                        )
                        if (highlighted) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp),
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(BottomBarGap))
        // Show completed toggle.
        PillCircleItem(
            selected = showCompleted,
            icon = Icons.Filled.CheckCircle,
            contentDescription = if (showCompleted) {
                "Hide completed tasks"
            } else {
                "Show completed tasks"
            },
            onClick = onToggleShowCompleted,
            size = buttonSize,
            iconSize = buttonIconSize,
        )
        Spacer(modifier = Modifier.width(BottomBarGap))
        // Search button with expressive press feedback.
        ExpressiveCircleButton(
            selected = searchActive,
            icon = if (searchActive) Icons.Filled.Close else Icons.Filled.Search,
            contentDescription = if (searchActive) "Close search" else "Search",
            onClick = onSearch,
            size = buttonSize,
            iconSize = buttonIconSize,
            startFrameBlend = 0f,
            endFrameBlend = 1f - fabInFrame.coerceIn(0f, 1f),
        )
        // Reserved slot that grows when the FAB joins the frame.
        Spacer(modifier = Modifier.width(trailingSlotWidth))
        }
    }
}

/** Shared sizing for the bottom action frame and the add button. */
private val BottomButtonExpandedSize = 56.dp
private val BottomButtonCompactSize = 42.dp
private val BottomButtonExpandedIcon = 24.dp
private val BottomButtonCompactIcon = 20.dp
private val BottomBarPadding = 6.dp
private val BottomBarGap = 8.dp
private val BottomBarBottomPadding = 8.dp

/** Round icon-only tab button matching the sort/search button size. */
@Composable
private fun PillCircleItem(
    selected: Boolean,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    size: Dp = BottomButtonExpandedSize,
    iconSize: Dp = BottomButtonExpandedIcon,
    startFrameBlend: Float = 0f,
    endFrameBlend: Float = 0f,
) {
    ExpressiveCircleButton(
        selected = selected,
        icon = icon,
        contentDescription = contentDescription,
        onClick = onClick,
        size = size,
        iconSize = iconSize,
        startFrameBlend = startFrameBlend,
        endFrameBlend = endFrameBlend,
    )
}

/**
 * Circle button that becomes a rounded rectangle when selected or pressed, and
 * scales up while pressed.
 *
 * [startFrameBlend] / [endFrameBlend] are 1f for a side flush with the bottom
 * frame: that side keeps its full circular radius so it follows the frame
 * border. They are 0f for a free side, which morphs to the rounded-rect corner.
 */
@Composable
private fun ExpressiveCircleButton(
    selected: Boolean,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    size: Dp = BottomButtonExpandedSize,
    iconSize: Dp = BottomButtonExpandedIcon,
    startFrameBlend: Float = 0f,
    endFrameBlend: Float = 0f,
    backgroundOverride: Color? = null,
    contentColorOverride: Color? = null,
    shadowElevation: Dp = 0.dp,
    modifier: Modifier = Modifier,
    additionalContent: @Composable () -> Unit = {},
) {
    val resolvedBackground = backgroundOverride ?: if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceContainerHigh
    }
    val background by animateColorAsState(
        targetValue = resolvedBackground,
        label = "expr-circle-bg",
    )
    val resolvedContent = contentColorOverride ?: if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
    val contentColor by animateColorAsState(
        targetValue = resolvedContent,
        label = "expr-circle-content",
    )

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    var lingering by remember { mutableStateOf(false) }

    LaunchedEffect(pressed) {
        if (pressed) {
            lingering = true
        } else {
            delay(140)
            lingering = false
        }
    }

    val morphed = pressed || lingering
    val innerRadius by animateDpAsState(
        targetValue = if (selected || morphed) size * 0.18f else size / 2,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "expr-circle-inner-corner",
    )
    val fullRadius = size / 2
    val startRadius = innerRadius +
        (fullRadius - innerRadius) * startFrameBlend.coerceIn(0f, 1f)
    val endRadius = innerRadius +
        (fullRadius - innerRadius) * endFrameBlend.coerceIn(0f, 1f)
    val shape = RoundedCornerShape(
        topStart = startRadius,
        topEnd = endRadius,
        bottomEnd = endRadius,
        bottomStart = startRadius,
    )
    val scale by animateFloatAsState(
        targetValue = if (morphed) 1.15f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "expr-circle-scale",
    )

    Box(modifier = modifier) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = { PlainTooltip { Text(contentDescription) } },
            state = rememberTooltipState(),
        ) {
            Surface(
                shape = shape,
                color = background,
                onClick = onClick,
                interactionSource = interactionSource,
                shadowElevation = shadowElevation,
                modifier = Modifier.size(size),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { scaleX = scale; scaleY = scale },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription,
                        tint = contentColor,
                        modifier = Modifier.size(iconSize),
                    )
                }
            }
        }
        additionalContent()
    }
}

@Composable
private fun PillTabItem(
    selected: Boolean,
    icon: ImageVector,
    label: String,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    compact: Boolean = false,
    onClick: () -> Unit,
) {
    val background by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
        label = "pill-item-bg",
    )
    val contentColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "pill-item-content",
    )

    Surface(
        shape = CircleShape,
        color = background,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMediumLow))
                .padding(
                    start = when {
                        compact && selected -> 10.dp
                        compact -> 7.dp
                        selected && isFirst -> 18.dp
                        selected -> 14.dp
                        else -> 10.dp
                    },
                    end = when {
                        compact && selected -> 10.dp
                        compact -> 7.dp
                        selected && isLast -> 18.dp
                        selected -> 14.dp
                        else -> 10.dp
                    },
                    top = if (compact) 4.dp else 10.dp,
                    bottom = if (compact) 4.dp else 10.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(18.dp),
            )
            if (selected) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = contentColor,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun RowScope.PillTabSegment(
    modifier: Modifier,
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val contentColor by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        label = "pill-tab-content",
    )

    Row(
        modifier = modifier
            .height(40.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(16.dp),
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/** Side drawer listing hashtags found in task titles and subtitles. */
@Composable
private fun HashtagDrawerContent(
    hashtags: List<String>,
    selectedHashtag: String?,
    onSelectHashtag: (String?) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
    ) {
        Text(
            text = "Hashtags",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 28.dp),
        )
        Text(
            text = if (hashtags.isEmpty()) {
                "No hashtags yet. Add #tags to task titles or subtitles."
            } else {
                "Tap a tag to filter your tasks."
            },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 4.dp),
        )
        Spacer(modifier = Modifier.height(12.dp))
        NavigationDrawerItem(
            label = { Text("All tasks") },
            selected = selectedHashtag == null,
            onClick = { onSelectHashtag(null) },
            modifier = Modifier.padding(horizontal = 12.dp),
        )
        if (hashtags.isNotEmpty()) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 8.dp),
            )
            hashtags.forEach { tag ->
                NavigationDrawerItem(
                    label = { Text(tag) },
                    selected = selectedHashtag == tag,
                    onClick = { onSelectHashtag(tag) },
                    modifier = Modifier.padding(horizontal = 12.dp),
                )
            }
        }
    }
}

@Composable
private fun SettingsContent(
    showCompleted: Boolean,
    onShowCompletedChange: (Boolean) -> Unit,
    showConfetti: Boolean,
    onShowConfettiChange: (Boolean) -> Unit,
    blurEnabled: Boolean,
    onBlurEnabledChange: (Boolean) -> Unit,
    completedCount: Int,
    totalCount: Int,
    onClearCompleted: () -> Unit,
    onImportJson: (String) -> Unit,
    onExportJson: () -> String,
    onAddExampleTasks: () -> Unit,
    onOpenAbout: () -> Unit,
) {
    var showImportDialog by remember { mutableStateOf(false) }
    var importText by remember { mutableStateOf("") }
    var exportedJson by remember { mutableStateOf<String?>(null) }
    var showDebugDialog by remember { mutableStateOf(false) }

    val transition = updateTransition(true, label = "settings-enter")
    val staggerDelay by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(300, 0, FastOutSlowInEasing),
        label = "stagger",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 8.dp),
    ) {
        StaggeredRow(index = 0, progress = staggerDelay) {
            SettingsRow(
                title = "Show completed tasks",
                description = "Hide the tasks you already finished",
                trailing = {
                    Switch(checked = showCompleted, onCheckedChange = onShowCompletedChange)
                },
            )
        }

        SettingsDivider()

        StaggeredRow(index = 1, progress = staggerDelay) {
            SettingsRow(
                title = "Show confetti effect on task completion",
                description = "Celebrate finished tasks and new tasks",
                trailing = {
                    Switch(checked = showConfetti, onCheckedChange = onShowConfettiChange)
                },
            )
        }

        SettingsDivider()

        StaggeredRow(index = 2, progress = staggerDelay) {
            SettingsRow(
                title = "Enable blur",
                description = "Blur background when menus and sheets are open",
                trailing = {
                    Switch(checked = blurEnabled, onCheckedChange = onBlurEnabledChange)
                },
            )
        }

        SettingsDivider()

        StaggeredRow(index = 3, progress = staggerDelay) {
            SettingsRow(
                title = "Clear completed tasks",
                description = when (completedCount) {
                    0 -> "Nothing to clear"
                    1 -> "Remove 1 finished task"
                    else -> "Remove $completedCount finished tasks"
                },
                trailing = {
                    TextButton(onClick = onClearCompleted, enabled = completedCount > 0) {
                        Text("Clear")
                    }
                },
            )
        }

        SettingsDivider()

        // Backup: export all data as JSON shown in a dialog
        StaggeredRow(index = 4, progress = staggerDelay) {
            SettingsRow(
                title = "Backup data",
                description = "Export all tasks as JSON",
                trailing = {
                    TextButton(onClick = {
                        exportedJson = onExportJson()
                    }) { Text("Export") }
                },
            )
        }

        SettingsDivider()

        // Restore: import from JSON pasted by user
        StaggeredRow(index = 5, progress = staggerDelay) {
            SettingsRow(
                title = "Restore data",
                description = "Import tasks from a JSON backup",
                trailing = {
                    TextButton(onClick = { showImportDialog = true }) { Text("Import") }
                },
            )
        }

        SettingsDivider()

        StaggeredRow(index = 6, progress = staggerDelay) {
            SettingsRow(
                title = "Tasks",
                description = when (totalCount) {
                    0 -> "No tasks yet"
                    1 -> "1 task in total"
                    else -> "$totalCount tasks in total"
                },
            )
        }

        SettingsDivider()

        StaggeredRow(index = 7, progress = staggerDelay) {
            SettingsRow(
                title = "Debug",
                description = "Testing tools for Tascs",
                trailing = {
                    TextButton(onClick = { showDebugDialog = true }) { Text("Open") }
                },
            )
        }

        SettingsDivider()

        StaggeredRow(index = 8, progress = staggerDelay) {
            SettingsRow(
                title = "About",
                description = "Version $TascsVersion · credits and links",
                trailing = {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                modifier = Modifier.clickable(onClick = onOpenAbout),
            )
        }
    }

    if (showDebugDialog) {
        AlertDialog(
            onDismissRequest = { showDebugDialog = false },
            title = { Text("Debug") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    DebugOption(
                        title = "30 Example tasks",
                        description = "Insert 30 tasks with hashtags and due dates",
                    ) {
                        onAddExampleTasks()
                        showDebugDialog = false
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showDebugDialog = false }) { Text("Close") }
            },
        )
    }

    // Export result dialog – shows JSON for copying
    exportedJson?.let { json ->
        AlertDialog(
            onDismissRequest = { exportedJson = null },
            title = { Text("Backup JSON") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Copy and save this JSON to restore your data later.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    OutlinedTextField(
                        value = json,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp),
                        maxLines = 10,
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { exportedJson = null }) { Text("Done") }
            },
        )
    }

    // Import dialog – paste JSON
    if (showImportDialog) {
        AlertDialog(
            onDismissRequest = { showImportDialog = false; importText = "" },
            title = { Text("Restore from backup") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Paste your JSON backup below. This will overwrite all current data.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    OutlinedTextField(
                        value = importText,
                        onValueChange = { importText = it },
                        placeholder = { Text("Paste JSON here…") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp),
                        maxLines = 8,
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (importText.isNotBlank()) {
                            onImportJson(importText.trim())
                            importText = ""
                            showImportDialog = false
                        }
                    },
                    enabled = importText.isNotBlank(),
                ) { Text("Import") }
            },
            dismissButton = {
                TextButton(onClick = { showImportDialog = false; importText = "" }) {
                    Text("Cancel")
                }
            },
        )
    }
}

@Composable
private fun DebugOption(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun StaggeredRow(
    index: Int,
    progress: Float,
    content: @Composable BoxScope.() -> Unit,
) {
    val itemProgress = (progress * 18f - index).coerceIn(0f, 1f)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                alpha = itemProgress
                translationY = (1f - itemProgress) * 20f
                scaleX = 0.97f + itemProgress * 0.03f
                scaleY = 0.97f + itemProgress * 0.03f
            },
        content = content,
    )
}

@Composable
private fun AboutContent() {
    val uriHandler = LocalUriHandler.current

    val transition = updateTransition(true, label = "about-enter")
    val staggerDelay by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(300, 0, FastOutSlowInEasing),
        label = "stagger",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        StaggeredAbout(index = 0, progress = staggerDelay) {
            Spacer(modifier = Modifier.height(8.dp))
        }

        StaggeredAbout(index = 1, progress = staggerDelay) {
            // Pebble icon: generous inner margin so the glyph stays clear of the
            // rounded edge, matching the launcher artwork.
            Surface(
                shape = RoundedCornerShape(percent = 32),
                color = TascsLogoBrown,
                modifier = Modifier.size(128.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = TascsLogo,
                        contentDescription = "Tascs logo",
                        tint = Color.Unspecified,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }

        StaggeredAbout(index = 2, progress = staggerDelay) {
            Spacer(modifier = Modifier.height(20.dp))
        }

        StaggeredAbout(index = 3, progress = staggerDelay) {
            Text(
                text = "Tascs",
                style = MaterialTheme.typography.headlineMedium,
            )
        }

        StaggeredAbout(index = 4, progress = staggerDelay) {
            Text(
                text = "A tiny task list",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        StaggeredAbout(index = 5, progress = staggerDelay) {
            Spacer(modifier = Modifier.height(28.dp))
        }

        StaggeredAbout(index = 6, progress = staggerDelay) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    AboutInfoRow(label = "Version", value = TascsVersion)
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                    )
                    AboutInfoRow(label = "Made by", value = "Ivaan Srivastava")
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                uriHandler.openUri("https://github.com/ivaansridev/tascs")
                            }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = GithubIcon,
                            contentDescription = "GitHub",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(22.dp),
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "ivaansridev/tascs",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Built with Compose Multiplatform",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun StaggeredAbout(
    index: Int,
    progress: Float,
    content: @Composable BoxScope.() -> Unit,
) {
    val itemProgress = (progress * 12f - index).coerceIn(0f, 1f)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                alpha = itemProgress
                translationY = (1f - itemProgress) * 24f
                scaleX = 0.95f + itemProgress * 0.05f
                scaleY = 0.95f + itemProgress * 0.05f
            },
        content = content,
    )
}

@Composable
private fun AboutInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun SettingsDivider() {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
        modifier = Modifier.padding(horizontal = 16.dp),
    )
}

@Composable
private fun SettingsRow(
    title: String,
    description: String,
    trailing: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp),
            )
        }
        if (trailing != null) {
            Spacer(modifier = Modifier.width(12.dp))
            trailing()
        }
    }
}

/** Bottom bar that only deletes once the slider is dragged all the way. */
@Composable
private fun DeleteConfirmBar(
    task: Task,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
) {
    var progress by remember(task.id) { mutableStateOf(0f) }

    Surface(
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        tonalElevation = 3.dp,
        shadowElevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 8.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Delete task?", style = MaterialTheme.typography.titleSmall)
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                TextButton(onClick = onCancel) { Text("Cancel") }
            }

            Slider(
                value = progress,
                onValueChange = { value ->
                    progress = value
                    if (value >= 0.98f) onConfirm()
                },
                valueRange = 0f..1f,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.error,
                    activeTrackColor = MaterialTheme.colorScheme.error,
                    inactiveTrackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                ),
            )

            Text(
                text = "Slide to delete",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
            )
        }
    }
}

@Composable
private fun TaskRow(
    task: Task,
    onToggle: (Offset) -> Unit,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    selectedHashtag: String?,
    onSelectHashtag: (String?) -> Unit,
    selectionMode: Boolean,
    selected: Boolean,
    onToggleSelection: () -> Unit,
) {
    val overdue = !task.done && task.dueDate?.isOverdue() == true
    var checkboxCenter by remember { mutableStateOf(Offset.Zero) }

    // Extract hashtags from this task
    val taskHashtags = remember(task) { extractHashtags(listOf(task)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (selected) {
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                } else {
                    MaterialTheme.colorScheme.surface
                },
            )
            .combinedClickable(
                onClick = {
                    if (selectionMode) onToggleSelection() else onClick()
                },
                onLongClick = {
                    if (!selectionMode) onLongClick()
                },
            )
            .padding(start = 4.dp, end = 16.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (selectionMode) {
            Checkbox(
                checked = selected,
                onCheckedChange = { onToggleSelection() },
            )
        } else {
            Checkbox(
                checked = task.done,
                onCheckedChange = { onToggle(checkboxCenter) },
                modifier = Modifier.onGloballyPositioned {
                    checkboxCenter = it.boundsInRoot().center
                },
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = if (task.done) TextDecoration.LineThrough else null,
                color = if (task.done) {
                    MaterialTheme.colorScheme.onSurfaceVariant
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
            )

            task.subtitle?.takeIf { it.isNotBlank() }?.let { subtitle ->
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 1.dp),
                )
            }

            // Hashtags as clickable chips
            if (taskHashtags.isNotEmpty()) {
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    taskHashtags.forEach { tag ->
                        val isSelected = selectedHashtag == tag
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.surfaceContainerHigh
                            },
                            border = if (isSelected) {
                                BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                            } else {
                                null
                            },
                            onClick = { onSelectHashtag(if (isSelected) null else tag) },
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = tag,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.onPrimaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    },
                                )
                                if (isSelected) {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Clear filter",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(12.dp),
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Due date is always shown as a ticker, even when it's the default.
            task.dueDate?.let { dueDate ->
                Text(
                    text = dueDate.dueLabel(),
                    style = MaterialTheme.typography.labelSmall,
                    color = when {
                        overdue -> MaterialTheme.colorScheme.error
                        task.done -> MaterialTheme.colorScheme.onSurfaceVariant
                        else -> MaterialTheme.colorScheme.primary
                    },
                    modifier = Modifier.padding(top = 2.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SwipeToDelete(
    onRequestDelete: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) onRequestDelete()
            // Never delete on swipe alone: the bottom slider confirms it.
            false
        },
    )

    SwipeToDismissBox(
        state = state,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                )
            }
        },
        content = content,
    )
}

@Composable
private fun EmptyState(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(24.dp),
        )
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LoadingIndicator(
            modifier = Modifier.size(64.dp),
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

/**
 * Bottom sheet used both for creating a task and for editing an existing one.
 * When [task] is set the sheet is in edit mode and shows a delete action.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskEditorSheet(
    task: Task?,
    onDismiss: () -> Unit,
    onSave: (title: String, subtitle: String?, dueDate: LocalDate?) -> Unit,
    onDelete: (() -> Unit)? = null,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var title by remember(task?.id) { mutableStateOf(task?.title.orEmpty()) }
    var subtitle by remember(task?.id) { mutableStateOf(task?.subtitle.orEmpty()) }
    var dueDate by remember(task?.id) { mutableStateOf(task?.dueDate) }
    var showDatePicker by remember { mutableStateOf(false) }

    // Springy entrance for the sheet content on top of the sheet's slide.
    var entered by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entered = true }
    val contentScale by animateFloatAsState(
        targetValue = if (entered) 1f else 0.92f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "task-sheet-scale",
    )
    val contentAlpha by animateFloatAsState(
        targetValue = if (entered) 1f else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "task-sheet-alpha",
    )

    fun submit() {
        val trimmed = title.trim()
        if (trimmed.isEmpty()) return
        onSave(
            trimmed,
            subtitle.trim().takeIf { it.isNotEmpty() },
            dueDate,
        )
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    scaleX = contentScale
                    scaleY = contentScale
                    alpha = contentAlpha
                }
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = if (task == null) "New task" else "Edit task",
                style = MaterialTheme.typography.titleLarge,
            )
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text("Subtitle (optional, #tags welcome)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedButton(
                    onClick = { showDatePicker = true },
                    modifier = Modifier.weight(1f),
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = dueDate?.dueLabel() ?: "Set due date")
                }
                if (dueDate != null) {
                    IconButton(onClick = { dueDate = null }) {
                        Icon(Icons.Filled.Close, contentDescription = "Clear due date")
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (onDelete != null) {
                    TextButton(
                        onClick = onDelete,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.error,
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Delete")
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = onDismiss) { Text("Cancel") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { submit() },
                    enabled = title.isNotBlank(),
                ) {
                    Text(if (task == null) "Add" else "Save")
                }
            }
        }
    }

    if (showDatePicker) {
        val pickerState = rememberDatePickerState(
            initialSelectedDateMillis = dueDate?.toUtcMillis(),
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        pickerState.selectedDateMillis?.let { dueDate = it.toLocalDateUtc() }
                        showDatePicker = false
                    },
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
            },
        ) {
            DatePicker(state = pickerState)
        }
    }
}

/**
 * Hosts one ConfettiKit canvas per active burst. A key per burst restarts the
 * library's animation for every celebration.
 */
@Composable
private fun ConfettiBurstsOverlay(
    bursts: List<ConfettiBurst>,
    onFinished: (Int) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        bursts.forEach { burst ->
            key(burst.id) {
                LaunchedEffect(burst.id) {
                    delay(2200)
                    onFinished(burst.id)
                }
                ConfettiKit(
                    modifier = Modifier.fillMaxSize(),
                    parties = listOf(confettiParty(burst.position)),
                )
            }
        }
    }
}

private fun confettiParty(position: Position): Party = Party(
    angle = Angle.TOP,
    spread = 360,
    speed = 10f,
    maxSpeed = 24f,
    damping = 0.91f,
    colors = listOf(
        0xFFFFD54F.toInt(), 0xFFFF8A65.toInt(), 0xFF4FC3F7.toInt(), 0xFF81C784.toInt(),
        0xFFBA68C8.toInt(), 0xFFFFF176.toInt(), 0xFFFF5252.toInt(), 0xFF40C4FF.toInt(),
    ),
    position = position,
    timeToLive = 1500,
    fadeOutDuration = 650,
    emitter = Emitter(duration = 120.milliseconds).max(80),
)

@Composable
private fun SortDirectionPill(
    ascending: Boolean,
    onChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
        ) {
            Row(
                modifier = Modifier.padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PillTabItem(
                    selected = ascending,
                    icon = Icons.Filled.KeyboardArrowUp,
                    label = "Ascending",
                    isFirst = true,
                    compact = true,
                    onClick = { onChange(true) },
                )
                PillTabItem(
                    selected = !ascending,
                    icon = Icons.Filled.KeyboardArrowDown,
                    label = "Descending",
                    isLast = true,
                    compact = true,
                    onClick = { onChange(false) },
                )
            }
        }
    }
}


