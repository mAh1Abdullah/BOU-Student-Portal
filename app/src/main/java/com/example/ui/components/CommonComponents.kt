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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PortalDestination
import com.example.model.SavedStudentCredential
import com.example.ui.theme.BouPrimary
import com.example.ui.theme.BouPrimaryDark

@Composable
fun UniversityLogoEmblem(
    modifier: Modifier = Modifier,
    size: Int = 40
) {
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(BouPrimary, Color(0xFF004D40))
                )
            )
            .border(1.5.dp, Color(0xFFFFD54F), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.School,
            contentDescription = "BOU Emblem",
            tint = Color.White,
            modifier = Modifier.size((size * 0.58).dp)
        )
    }
}

@Composable
fun PortalIconButtonCard(
    destination: PortalDestination,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Dynamic Bento tinting per destination
    val (bentoBg, bentoBorder, iconBg, iconColor) = when (destination.id) {
        "student_profile" -> QuadColor(Color(0xFFE8F5E9), Color(0xFFA5D6A7), Color(0xFFC8E6C9), Color(0xFF1B5E20))
        "bou_home" -> QuadColor(Color(0xFFE0F2F1), Color(0xFF80CBC4), Color(0xFFB2DFDB), Color(0xFF00695C))
        "result_page" -> QuadColor(Color(0xFFE1F5FE), Color(0xFF90CAF9), Color(0xFFB3E5FC), Color(0xFF0277BD))
        "notice_board" -> QuadColor(Color(0xFFE8EAF6), Color(0xFFC5CAE9), Color(0xFFD1C4E9), Color(0xFF283593))
        "online_admission" -> QuadColor(Color(0xFFFFF8E1), Color(0xFFFFE082), Color(0xFFFFECB3), Color(0xFFE65100))
        "student_handbook" -> QuadColor(Color(0xFFFFF3E0), Color(0xFFFFCC80), Color(0xFFFFE0B2), Color(0xFFEF6C00))
        "ebook_library" -> QuadColor(Color(0xFFEDE7F6), Color(0xFFB39DDB), Color(0xFFD1C4E9), Color(0xFF512DA8))
        "marks_calculator" -> QuadColor(Color(0xFFF3E5F5), Color(0xFFCE93D8), Color(0xFFE1BEE7), Color(0xFF6A1B9A))
        "contact_support" -> QuadColor(Color(0xFFFFEBEE), Color(0xFFFFCDD2), Color(0xFFFFCDD2), Color(0xFFC2185B))
        else -> QuadColor(Color(0xFFF0FDF4), Color(0xFFBBF7D0), Color(0xFFDCFCE7), Color(0xFF15803D))
    }

    Card(
        modifier = modifier
            .testTag("portal_btn_${destination.id}")
            .clip(RoundedCornerShape(22.dp))
            .border(1.2.dp, bentoBorder.copy(alpha = 0.8f), RoundedCornerShape(22.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = bentoBg
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(22.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(iconBg)
                        .border(1.dp, bentoBorder.copy(alpha = 0.5f), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.title,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }

                destination.badge?.let { badgeText ->
                    Surface(
                        color = iconBg,
                        shape = RoundedCornerShape(10.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, bentoBorder.copy(alpha = 0.6f))
                    ) {
                        Text(
                            text = badgeText,
                            style = MaterialTheme.typography.labelSmall,
                            color = iconColor,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Column {
                Text(
                    text = destination.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = destination.bengaliTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = iconColor,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = destination.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF475569),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 11.sp,
                    lineHeight = 15.sp
                )
            }
        }
    }
}

private data class QuadColor(
    val bg: Color,
    val border: Color,
    val iconBg: Color,
    val iconColor: Color
)

@Composable
fun StudentProfileBanner(
    credential: SavedStudentCredential,
    onEditClick: () -> Unit,
    onCopyId: () -> Unit,
    onOpenOsaps: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .border(1.5.dp, Color(0xFFA7F3D0), RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFECFDF5)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF059669)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = "Student Info",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Student OSAPS Profile",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF064E3B)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = Color(0xFFD1FAE5),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "Active",
                                    color = Color(0xFF047857),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "osapsnew.bou.ac.bd",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF059669),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFA7F3D0)),
                    modifier = Modifier.size(36.dp)
                ) {
                    IconButton(
                        onClick = onEditClick,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Profile Info",
                            tint = Color(0xFF059669),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            if (credential.studentId.isNotBlank()) {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFA7F3D0)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "SAVED STUDENT ID",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF6B7280),
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                            Text(
                                text = credential.studentId,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF111827)
                            )
                            if (credential.programName.isNotBlank()) {
                                Text(
                                    text = credential.programName,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF059669),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Surface(
                            color = Color(0xFFF0FDF4),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFA7F3D0))
                        ) {
                            IconButton(
                                onClick = onCopyId,
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ContentCopy,
                                    contentDescription = "Copy Student ID",
                                    tint = Color(0xFF059669),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            } else {
                Surface(
                    color = Color.White.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Save your Student ID & Program details for 1-tap autofill & quick access during OSAPS login.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF065F46),
                        modifier = Modifier.padding(12.dp),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onOpenOsaps,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("btn_quick_osaps_login"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF059669)
                    )
                ) {
                    Text(
                        text = "Login to OSAPS",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }

                if (credential.studentId.isBlank()) {
                    OutlinedButton(
                        onClick = onEditClick,
                        modifier = Modifier.height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFF059669)
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF059669))
                    ) {
                        Text("Add My ID", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun EditStudentProfileDialog(
    initialCredential: SavedStudentCredential,
    onDismiss: () -> Unit,
    onSave: (studentId: String, programName: String, regionalCenter: String, note: String) -> Unit
) {
    var studentId by remember { mutableStateOf(initialCredential.studentId) }
    var programName by remember { mutableStateOf(initialCredential.programName) }
    var regionalCenter by remember { mutableStateOf(initialCredential.regionalCenter) }
    var note by remember { mutableStateOf(initialCredential.note) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                UniversityLogoEmblem(size = 32)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Student Profile Details", fontWeight = FontWeight.Bold)
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Save your BOU Student ID and program info locally on your device for quick copy-paste into OSAPS portal.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedTextField(
                    value = studentId,
                    onValueChange = { studentId = it },
                    label = { Text("BOU Student ID (e.g. 21-0-11-001-001)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = programName,
                    onValueChange = { programName = it },
                    label = { Text("Program (e.g. BA/BSS, BBA, LLB, SSC, HSC)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = regionalCenter,
                    onValueChange = { regionalCenter = it },
                    label = { Text("Regional Center / Sub-Center") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Personal Notes (Optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(studentId, programName, regionalCenter, note)
                    onDismiss()
                }
            ) {
                Text("Save Profile")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
