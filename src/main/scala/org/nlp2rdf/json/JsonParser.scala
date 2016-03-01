package org.nlp2rdf.json

import java.io.StringWriter
import java.util

import org.apache.jena.rdf.model.{Statement, Model}
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.nlp2rdf.json.obj._
import org.nlp2rdf.nif.URLProperties
import org.nlp2rdf.util.{PropertiesManager, FileManager}
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.parsing.json.JSON


class JsonParser extends FileManager with PropertiesManager with URLProperties {

  private def extractContext(model: Model) : util.List[Context] = {

    val result = new util.ArrayList[Context]
    val nodeInterator = model.getNsPrefixMap.keySet().iterator()


    while(nodeInterator.hasNext){
      val node = nodeInterator.next()
      var obj = new Context(node,model.getNsPrefixMap.get(node))
      result.add(obj)
    }
    result
  }

  private def extractGraph(model: Model): util.List[Graph] = {

    val result = new util.ArrayList[Graph]

    val statements = model.listStatements().toList.asScala

    val subjectIterator = model.listSubjects()

    while(subjectIterator.hasNext){

      val subject = subjectIterator.next().toString

      model.listStatements()

      val graph = new Graph(subject,
                            extractArray(subject,TYPE_LABEL, statements),
                            extractValue(subject,ANCHOR_OF, statements),
                            extractValueAsLiteralValue(subject,BEGIN_INDEX, statements),
                            extractValueAsLiteralValue(subject,END_INDEX, statements),
                            extractValue(subject,REFERENCE_CONTEXT, statements),
                            extractArray(subject,CLASS_REF, statements),
                            extractValue(subject,IDENT_REF, statements))
      result.add(graph)

    }
    result
  }

  def extract(filter: String, predicate:String, statements:mutable.Buffer[Statement]): mutable.Buffer[Statement] = {
    statements.filter(s => predicate.equalsIgnoreCase(s.getPredicate.getLocalName) && filter.equals(s.getSubject.toString))
  }

  private def extractValueAsLiteralValue(filter: String, predicate:String, statements:mutable.Buffer[Statement]): String = {
    extract(filter,predicate, statements).map(_.getObject.asLiteral().getValue).mkString
  }


  private def extractValue(filter: String, predicate:String, statements:mutable.Buffer[Statement]): String = {
    extract(filter,predicate, statements).map(_.getObject.toString).mkString
  }


  private def extractArray(filter: String, predicate:String, statements:mutable.Buffer[Statement]):  String = {
    extract(filter,predicate, statements).map(_.getObject.asResource().getLocalName).mkString("\",\"")
  }


  def convert(model: Model): String = {

    val ve = new VelocityEngine()
    ve.init(properties(VELOCITY_PROPERTIES))

    val template = ve.getTemplate(TEMPLATE_FREME_PATH)
    val context = new VelocityContext()

    context.put(CONTEXT_PROPERTIES, extractContext(model))
    context.put(GRAPHS, extractGraph(model))

    val writer = new StringWriter()
    template.merge(context, writer )

    writer.toString

  }

  def convert(json: String) : JSONGraph = {
    val result = JSON.parseFull(json)

    null
  }

}
