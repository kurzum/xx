package org.nlp2rdf.util

import java.io.InputStream


trait FileManager {

  val TEMPLATE_FREME_PATH = "./src/main/resources/templates/freme-json.vm"

  def filepath(filename: String): String = {
    getClass.getClassLoader.getResource(filename).toURI.getPath
  }

  private def file(filename: String): InputStream = {
    getClass.getClassLoader.getResourceAsStream(filename)
  }

}
