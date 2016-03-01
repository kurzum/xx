package org.nlp2rdf.json

import org.apache.jena.rdf.model.Model

import scala.beans.BeanProperty


package object obj {

  case class Graph (@BeanProperty id: String,
                    @BeanProperty types: String,
                    @BeanProperty nifAnchorOf: String,
                    @BeanProperty beginIndex: String,
                    @BeanProperty endIndex: String,
                    @BeanProperty referenceContext: String,
                    @BeanProperty taClassRef: String,
                    @BeanProperty taIdentRef: String)

  case class Context(@BeanProperty name: String,
                     @BeanProperty uri: String)


  case class JSONGraph (@BeanProperty context:List[Context],
                        @BeanProperty graph: List[Graph]) {

      def toModel () :Model = {
        val model: Model = null

        model
      }
  }

}
