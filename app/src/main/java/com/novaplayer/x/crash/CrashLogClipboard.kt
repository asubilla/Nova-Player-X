package com.novaplayer.x.crash

import android.content.ClipboardManager
import android.content.ClipData

internal const val MAX_CLIPBOARD_REPORT_CHARS = 100_000
internal const val CLIPBOARD_REPORT_TRUNCATION_MARKER =
    "\n\n[Crash report truncated. Use Share for full logs.]"

internal fun crashReportForClipboard(report: String): String {
    if (report.length <= MAX_CLIPBOARD_REPORT_CHARS) return report

    val prefixLength = MAX_CLIPBOARD_REPORT_CHARS - CLIPBOARD_REPORT_TRUNCATION_MARKER.length
    return report.take(n = prefixLength) + CLIPBOARD_REPORT_TRUNCATION_MARKER
}

internal fun copyCrashReportToClipboard(clipboard: ClipboardManager, report: String) {
    clipboard.setPrimaryClip(
        ClipData.newPlainText(
            null,
            crashReportForClipboard(report = report),
        ),
    )
}
