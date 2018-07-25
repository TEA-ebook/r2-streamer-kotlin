package org.readium.r2.streamer.parser.epub

import android.util.Log
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.readium.r2.shared.parser.xml.XmlParser
import java.io.File
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class OPFParserTest {

    private val epubVersion = 3.0
    private val opfFilePath = "src/test/resources/package.opf"

    @Test
    fun parsingOpf() {
        val opfParser = OPFParser()

        val xmlParser = XmlParser()
        xmlParser.parseXml(File(opfFilePath).inputStream())

        assertNotNull(opfParser.parseOpf(xmlParser, opfFilePath, epubVersion))
    }

    @Test
    fun findResourceWithHref() {
        val opfParser = OPFParser()
        val xmlParser = XmlParser()
        xmlParser.parseXml(File(opfFilePath).inputStream())
        val publication = opfParser.parseOpf(xmlParser, opfFilePath, epubVersion)

        val encodedUri = "/ebook.epub/src/test/resources/Image/cover.jpg"
        val offset = encodedUri.indexOf("/", 0)
        val startIndex = encodedUri.indexOf("/", offset + 1)
        val filePath = encodedUri.substring(startIndex + 1)
        val link = publication?.linkWithHref(filePath)

        assertNotNull(link, "relative href should be found")
    }
}