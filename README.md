## DISCLAIMER
This repo is in a alfa stage. I don't recommend you use until we promote it for the nlp2rdf organization. Thank you for your patience!

# RDF to JSON-LD-NIF library

[![Build Status](https://travis-ci.org/sandroacoelho/xx.svg?branch=master)](https://travis-ci.org/sandroacoelho/xx)

RDF to JSON-LD-NIF is a bidirecional small library that intent to integrate the java system for RDF provided by JENA with NIF format under beautiful and flexible json formats.


## What is NIF (NLP Interchange Format) ?

The NLP Interchange Format (NIF) is an RDF/OWL-based format that aims to achieve interoperability between Natural Language Processing (NLP) tools, language resources and annotations. NIF consists of specifications, ontologies and software (overview), which are combined under the version identifier "NIF 2.0". NIF docs are available [here](http://persistence.uni-leipzig.org/nlp2rdf/)

## JSON-LD-NIF example


```
{  
   "context":[  
      {  
         "@alias":"dbpedia-fr",
         "@id":"http://fr.dbpedia.org/resource/"
      },
      {  
         "@alias":"dbpedia-es",
         "@id":"http://es.dbpedia.org/resource/"
      },
      {  
         "@alias":"xsd",
         "@id":"http://www.w3.org/2001/XMLSchema#"
      },
      {  
         "@alias":"itsrdf",
         "@id":"http://www.w3.org/2005/11/its/rdf#"
      },
      {  
         "@alias":"dbpedia",
         "@id":"http://dbpedia.org/resource/"
      },
      {  
         "@alias":"rdfs",
         "@id":"http://www.w3.org/2000/01/rdf-schema#"
      },
      {  
         "@alias":"nif",
         "@id":"http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
      },
      {  
         "@alias":"dbpedia-de",
         "@id":"http://de.dbpedia.org/resource/"
      },
      {  
         "@alias":"dbpedia-ru",
         "@id":"http://ru.dbpedia.org/resource/"
      },
      {  
         "@alias":"freme-onto",
         "@id":"http://freme-project.eu/ns#"
      },
      {  
         "@alias":"dbpedia-nl",
         "@id":"http://nl.dbpedia.org/resource/"
      },
      {  
         "@alias":"dcterms",
         "@id":"http://purl.org/dc/terms/"
      },
      {  
         "@alias":"dbpedia-it",
         "@id":"http://it.dbpedia.org/resource/"
      }
   ],
   "graph":[  
      {  
         "@id":"http://freme-project.eu/#char=0,33",
         "@type":[  
            "RFC5147String",
            "Context",
            "String"
         ],
         "nif:anchorOf":"",
         "beginIndex":"0",
         "endIndex":"33",
         "referenceContext":"",
         "taClassRef":[  
            ""
         ],
         "taIdentRef":""
      },
      {  
         "@id":"http://freme-project.eu/#char=0,14",
         "@type":[  
            "Word",
            "String",
            "Phrase",
            "RFC5147String"
         ],
         "nif:anchorOf":"Diego Maradona",
         "beginIndex":"0",
         "endIndex":"14",
         "referenceContext":"http://freme-project.eu/#char=0,33",
         "taClassRef":[  
            "SportsManager",
            "Person",
            "Person",
            "Agent",
            "SoccerManager"
         ],
         "taIdentRef":"http://dbpedia.org/resource/Diego_Maradona"
      },
      {  
         "@id":"http://freme-project.eu/#char=23,32",
         "@type":[  
            "String",
            "Word",
            "Phrase",
            "RFC5147String"
         ],
         "nif:anchorOf":"Argentina",
         "beginIndex":"23",
         "endIndex":"32",
         "referenceContext":"http://freme-project.eu/#char=0,33",
         "taClassRef":[  
            "Place",
            "PopulatedPlace",
            "Location",
            "Location",
            "Country"
         ],
         "taIdentRef":"http://dbpedia.org/resource/Argentina"
      }
   ]
}




```




##  Creating your JSON format

This library uses the powerfull Apache Velocity to create JSON formats based on Jena Models. So, you can extend or create a new one JSON format just building or changing our template file 

```
{

   "context":[
        #foreach( $ctx in $contextProperties)
   	     {
   	        "@alias" : "$ctx.name",
            "@id": "$ctx.uri"
         }#if( $foreach.hasNext ),
          #end
         #end
	],

	"graph": [
	        #foreach( $graph in $graphs)
	        {
    		"@id":  "$graph.id",
    		"@type": [ "$graph.types"],
    		"nif:anchorOf" : "$graph.nifAnchorOf",
    		"beginIndex": "$graph.beginIndex",
    		"endIndex": "$graph.endIndex",
    		"referenceContext": "$graph.referenceContext",
    		"taClassRef": ["$graph.taClassRef"],
    		"taIdentRef": "$graph.taIdentRef"
    	    }#if( $foreach.hasNext ),
           #end
          #end
    	  ]
}
```




## Maintainers

* Sandro Coelho [@sandroacoelho](https://github.com/sandroacoelho)
* ...
