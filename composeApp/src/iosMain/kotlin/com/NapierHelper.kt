package com

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

/**
 * Created by dinopriyano on 17/03/24.
 */

fun debugBuild() {
    Napier.base(DebugAntilog())
}

fun releaseBuild(antilog: Antilog) {
    Napier.base(antilog)
}