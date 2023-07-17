package ca.quantum.quants.it.housefy.components.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

sealed interface ActionMenuItem {
    val title: String
    val onClick: () -> Unit

    sealed interface IconMenuItem : ActionMenuItem {
        val icon: ImageVector
        val contentDescription: String?

        data class AlwaysShown(
            override val title: String,
            override val contentDescription: String?,
            override val onClick: () -> Unit,
            override val icon: ImageVector,
        ) : IconMenuItem

        data class ShownIfRoom(
            override val title: String,
            override val contentDescription: String?,
            override val onClick: () -> Unit,
            override val icon: ImageVector,
        ) : IconMenuItem
    }

    data class NeverShown(
        override val title: String,
        override val onClick: () -> Unit,
    ) : ActionMenuItem
}

@Composable
fun ActionsMenu(
    items: List<ActionMenuItem>,
    isOpen: Boolean,
    onToggleOverflow: () -> Unit,
    maxVisibleItems: Int,
) {
    val menuItems = remember(
        key1 = items,
        key2 = maxVisibleItems,
    ) {
        splitMenuItems(items, maxVisibleItems)
    }

    menuItems.alwaysShownItems.forEach { item ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(onClick = item.onClick)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.contentDescription,
                modifier = Modifier.size(20.dp),
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }

    if (menuItems.overflowItems.isNotEmpty()) {
        IconButton(onClick = onToggleOverflow) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Overflow",
            )
        }
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = onToggleOverflow,
        ) {
            menuItems.overflowItems.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(item.title)
                    },
                    onClick = item.onClick
                )
            }
        }
    }
}

private data class MenuItems(
    val alwaysShownItems: List<ActionMenuItem.IconMenuItem>,
    val overflowItems: List<ActionMenuItem>,
)

private fun splitMenuItems(
    items: List<ActionMenuItem>,
    maxVisibleItems: Int,
): MenuItems {
    val alwaysShownItems: MutableList<ActionMenuItem.IconMenuItem> =
        items.filterIsInstance<ActionMenuItem.IconMenuItem.AlwaysShown>().toMutableList()
    val ifRoomItems: MutableList<ActionMenuItem.IconMenuItem> =
        items.filterIsInstance<ActionMenuItem.IconMenuItem.ShownIfRoom>().toMutableList()
    val overflowItems = items.filterIsInstance<ActionMenuItem.NeverShown>()

    val hasOverflow = overflowItems.isNotEmpty() ||
            (alwaysShownItems.size + ifRoomItems.size - 1) > maxVisibleItems
    val usedSlots = alwaysShownItems.size + (if (hasOverflow) 1 else 0)
    val availableSlots = maxVisibleItems - usedSlots
    if (availableSlots > 0 && ifRoomItems.isNotEmpty()) {
        val visible = ifRoomItems.subList(0, availableSlots.coerceAtMost(ifRoomItems.size))
        alwaysShownItems.addAll(visible)
        ifRoomItems.removeAll(visible)
    }

    return MenuItems(
        alwaysShownItems = alwaysShownItems,
        overflowItems = ifRoomItems + overflowItems,
    )
}