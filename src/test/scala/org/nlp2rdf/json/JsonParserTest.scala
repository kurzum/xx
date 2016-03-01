package org.nlp2rdf.json

import java.io.ByteArrayOutputStream

import org.apache.jena.riot.{Lang, RDFDataMgr}
import org.junit.{Assert, Test}



class JsonParserTest {


  @Test  def convertToJsonFremeMustProducesJson() {

    //Init
    val parser = new JsonParser()
    val model = RDFDataMgr.loadModel(parser.filepath("data/maradona.json-ld"),Lang.JSONLD)

    //Action
    val result = parser.convert(model)

    //Assert
    Assert.assertTrue(!"".equalsIgnoreCase(result))

  }


  @Test def convertFremeJsonToObjectMustProducesAvalidRDFModel(): Unit = {

    //Init
    val parser = new JsonParser()
    val originalModel = RDFDataMgr.loadModel(parser.filepath("data/maradona.json-ld"),Lang.JSONLD)
    val originalModelOutputStream = new ByteArrayOutputStream()

    val fremeJSON = parser.convert(originalModel)
    val fremeModelOutputStream = new ByteArrayOutputStream()

    //Action
    val jsonGraphObject = parser.convert(fremeJSON)

    RDFDataMgr.write(originalModelOutputStream, originalModel, Lang.NTRIPLES)
    RDFDataMgr.write(fremeModelOutputStream, jsonGraphObject.toModel(), Lang.NTRIPLES)

    //Assert
    Assert.assertEquals( new String(originalModelOutputStream.toByteArray(),"UTF-8"),
      new String(fremeModelOutputStream.toByteArray(),"UTF-8"))

  }



}
