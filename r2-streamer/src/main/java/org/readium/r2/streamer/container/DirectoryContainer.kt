/*
 * Module: r2-streamer-kotlin
 * Developers: Aferdita Muriqi, Clément Baumann
 *
 * Copyright (c) 2018. Readium Foundation. All rights reserved.
 * Use of this source code is governed by a BSD-style license which is detailed in the
 * LICENSE file present in the project repository where this source code is maintained.
 */

package org.readium.r2.streamer.container

import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream

interface DirectoryContainer : Container {

    override fun data(relativePath: String): ByteArray {
        val filePath = rootFile.rootPath + "/" + relativePath
        val epubFile = File(filePath)

        if (!epubFile.exists())
            throw Exception("Missing File at path: " + filePath)

        val fis = FileInputStream(epubFile)
        val buffer = ByteArrayOutputStream()
        var nRead: Int
        val data = ByteArray(16384)

        nRead = fis!!.read(data, 0, data.size)
        while (nRead != -1) {
            Log.i("YOOOO", "write to buffer " + nRead)
            buffer.write(data, 0, nRead)
            nRead = fis.read(data, 0, data.size)
        }

        buffer.flush()

        return buffer.toByteArray()
    }

    override fun dataLength(relativePath: String) =
            File(rootFile.toString() + "/" + relativePath).length()

    override fun dataInputStream(relativePath: String) =
            FileInputStream(File(rootFile.toString() + "/" + relativePath))
}

