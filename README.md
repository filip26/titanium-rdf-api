# Titanium RDF API

A collection of straightforward micro-interfaces for processing RDF statements and facilitating seamless interoperability and data exchange across various libraries.

[![Maven Central](https://img.shields.io/maven-central/v/com.apicatalog/titanium-rdf-api.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.apicatalog%20AND%20a:titanium-rdf-api)
[![javadoc](https://javadoc.io/badge2/com.apicatalog/titanium-rdf-api/javadoc.svg)](https://javadoc.io/doc/com.apicatalog/titanium-rdf-api)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Examples

This section demonstrates how to directly utilize the micro-interfaces for both RDF 1.1 processing and advanced RDF 1.2 data emission.

### RDF Flat Quads

This example shows how to implement and use the `RdfQuadConsumer` interface to process incoming RDF statements, utilizing the static helper methods to validate data types.

```java
// 1. Implement a custom consumer
var logger = (subject, predicate, object, datatype, language, graph) -> {
    
    // Use static helpers to identify language-tagged strings
    if (RdfQuadConsumer.isLangString(datatype, language)) {
        System.out.println("Language string detected: " + object + "@" + language);
    } else if (RdfQuadConsumer.isBlank(subject)) {
        System.out.println("Blank node subject detected: " + subject);
    }
    
    // Additional processing logic here
};

// 2. Feed quads into the consumer
logger.quad(
    "http://example.org/subject",
    "http://example.org/predicate",
    "Ahoj",
    RdfQuadConsumer.DATATYPE_DIR_LANG_STRING,
    "cs",
    "ltr",
    null
);
```

### RDF Triple Terms

This example demonstrates how to use the `RdfQuadEmitter` interface to produce nested Triple Terms.

```java
// Initialize a writer or processor implementing RdfQuadEmitter
RdfQuadEmitter emitter = ...;

// 1. Emitting a standard flat quad using convenience methods
emitter.quad(
    "http://example.org/subject", 
    "http://example.org/predicate", 
    "http://example.org/object", 
    "http://example.org/graph"
);

// 2. Streaming a quad with a directional language-tagged literal (Czech, Left-to-Right)
emitter.beginQuad("http://example.org/graph");
emitter.subject("http://example.org/subject");
emitter.predicate("http://example.org/predicate");
emitter.literal("Ahoj", RdfQuadEmitter.DATATYPE_DIR_LANG_STRING, "cs", "ltr");
emitter.endQuad();

// 3. Emitting an RDF 1.2 Triple Term in the subject position (<< :s :p :o >> :p2 :o2)
emitter.beginQuad(); // Default graph

emitter.beginSubject();
emitter.subject("http://example.org/s");
emitter.predicate("http://example.org/p");
emitter.object("http://example.org/o");
emitter.endSubject();

emitter.predicate("http://example.org/p2");
emitter.object("http://example.org/o2");
emitter.endQuad();
```

## 🎉 Supported By

* [Apache Jena](https://jena.apache.org/)
* [Jelly-JVM](https://w3id.org/jelly/jelly-jvm) – high-performance binary RDF serialization format
* [Titanium JSON-LD](https://github.com/filip26/titanium-json-ld)
* [Titanium RDF N-QUADS](https://github.com/filip26/titanium-rdf-n-quads)
* [Titanium RDF Dataset Canonicalization](https://github.com/filip26/titanium-rdf-canon)
* [Titanium RDF Primitives](https://github.com/filip26/titanium-rdf-primitives)

Add an implementation that supports this API - open a PR!


## 📦 Installation


```xml
<dependency>
    <groupId>com.apicatalog</groupId>
    <artifactId>titanium-rdf-api</artifactId>
    <version>${rdf.api.version}</version>
</dependency>
```

## 🤝 Contributing

Contributions are welcome! Please submit a pull request.

### Building

Fork and clone the project repository.

```bash
> cd titanium-rdf-api
> mvn package
```

## Resources

* [W3C RDF 1.1 Concepts and Abstract Syntax](https://www.w3.org/TR/rdf11-concepts/)
* [W3C RDF 1.2 Concepts and Abstract Data Model](https://www.w3.org/TR/rdf12-concepts/)


