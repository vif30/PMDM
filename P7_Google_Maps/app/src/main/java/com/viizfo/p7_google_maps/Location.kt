package com.viizfo.p7_google_maps

import android.content.Context

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.SAXException
import java.io.IOException
import java.lang.reflect.Constructor
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException


data class Location(
    var title: String,
    var fragment: String,
    var tag: String,
    var latitude: Double,
    var longitude: Double,

    ) {
    constructor():this("","","",0.0,0.0){

    }


    companion object {
        fun readLocations(context: Context): List<Location> {
            val locations = mutableListOf<Location>()

            try {
                val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
                val builder: DocumentBuilder = factory.newDocumentBuilder()
                val doc: Document =
                    builder.parse(context.resources.openRawResource(R.raw.locations))
                val root: Element = doc.documentElement
                val items: NodeList = root.getElementsByTagName("location")
                for (i in 0 until items.length) { //go through all elements
                    val locationNode: Node = items.item(i)
                    val location = Location()
                    for (j in 0 until locationNode.childNodes.length) { //go through children
                        val currentNode: Node = locationNode.childNodes.item(j)
                        //check if is an element
                        if (currentNode.nodeType === Node.ELEMENT_NODE) {
                            when (currentNode.nodeName.uppercase()) {
                                "TITLE" -> location.title = currentNode.childNodes.item(0).nodeValue
                                "FRAGMENT" -> location.fragment =
                                    currentNode.childNodes.item(0).nodeValue
                                "TAG" -> location.tag = currentNode.childNodes.item(0).nodeValue
                                "LATITUDE" -> location.latitude =
                                    currentNode.childNodes.item(0).nodeValue.toDouble()
                                "LONGITUDE" -> location.longitude =
                                    currentNode.childNodes.item(0).nodeValue.toDouble()
                            }
                        }

                    } //end for 2 (children)
                    locations.add(location)
                } //end for 1 (elementos)
            } catch (e: ParserConfigurationException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: SAXException) {
                e.printStackTrace()
            }


            return locations
        }
    }
}