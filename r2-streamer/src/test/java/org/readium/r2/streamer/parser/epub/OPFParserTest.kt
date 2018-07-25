package org.readium.r2.streamer.parser.epub

import android.util.Log
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.readium.r2.shared.parser.xml.XmlParser
import java.io.File
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
class OPFParserTest {

    @Test
    fun testsWork() {
        val epubVersion = 3.0
        val opfFilePath = "src/test/resources/package.opf"
        val opfParser = OPFParser()

        val xmlParser = XmlParser()
        xmlParser.parseXml(File(opfFilePath).inputStream())

        val publication = opfParser.parseOpf(xmlParser, opfFilePath, epubVersion)

        System.out.print(publication?.manifest())

        assertNotNull(publication)
    }
}