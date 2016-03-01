package org.nlp2rdf.util

import java.util.Properties


trait PropertiesManager {

  val VELOCITY_PROPERTIES = "velocity.properties"

  val CONTEXT_PROPERTIES = "contextProperties"
  val GRAPHS = "graphs"

  def properties(filename: String):Properties = {
    val prop = new Properties()
    val in = getClass.getClassLoader.getResourceAsStream(filename)
    prop.load(in)
    prop
  }

}
