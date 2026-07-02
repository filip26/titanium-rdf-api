# Titanium RDF API

A collection of straightforward micro-interfaces for processing RDF statements and facilitating seamless interoperability and data exchange across various libraries.

[![Maven Central](https://img.shields.io/maven-central/v/com.apicatalog/titanium-rdf-api.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.apicatalog%20AND%20a:titanium-rdf-api)
[![javadoc](https://javadoc.io/badge2/com.apicatalog/titanium-rdf-api/javadoc.svg)](https://javadoc.io/doc/com.apicatalog/titanium-rdf-api)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Examples

This section demonstrates how to directly utilize the micro-interfaces for both RDF 1.1 processing and advanced RDF 1.2 data emission.

### RDF 1.1: Consuming Quads

This example shows how to implement and use the `Rdf11QuadConsumer` interface to process incoming RDF 1.1 statements, utilizing the static helper methods to validate data types.

```java
// 1. Implement a custom consumer
var logger = (subject, predicate, object, datatype, language, graph) -> {
    
    // Use static helpers to identify language-tagged strings
    if (Rdf11QuadConsumer.isLangString(datatype, language)) {
        System.out.println("Language string detected: " + object + "@" + language);
    } else if (Rdf11QuadConsumer.isBlank(subject)) {
        System.out.println("Blank node subject detected: " + subject);
    }
    
    // Additional processing logic here
};

// 2. Feed quads into the consumer
logger.quad(
    "[http://example.org/subject](http://example.org/subject)",
    "[http://example.org/predicate](http://example.org/predicate)",
    "Ahoj",
    Rdf11QuadConsumer.DATATYPE_LANG_STRING,
    "cs",
    nul
);
```

### RDF 1.2: Event-Based Data Emission

This example demonstrates how to use the `Rdf12QuadEmitter` interface to produce complex RDF 1.2 structures, including directional language-tagged strings and nested Triple Terms.

```java
// Initialize a writer or processor implementing Rdf12QuadEmitter
Rdf12QuadEmitter emitter = ...;

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
emitter.literal("Ahoj", Rdf12QuadEmitter.DATATYPE_DIR_LANG_STRING, "cs", "ltr");
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

## Supported By

* [Apache Jena](https://jena.apache.org/)
* [Jelly-JVM](https://w3id.org/jelly/jelly-jvm) – high-performance binary RDF serialization format
* [Titanium JSON-LD](https://github.com/filip26/titanium-json-ld)
* [Titanium RDF N-QUADS](https://github.com/filip26/titanium-rdf-n-quads)
* [Titanium RDF Dataset Canonicalization](https://github.com/filip26/titanium-rdf-canon)
* [Titanium RDF Primitives](https://github.com/filip26/titanium-rdf-primitives)

Add an implementation that supports this API - open a PR!


## Installation

### Maven

```xml
<dependency>
    <groupId>com.apicatalog</groupId>
    <artifactId>titanium-rdf-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```gradle
implementation("com.apicatalog:titanium-rdf-api:1.0.0")
```

## Contributing

All PR's welcome!


### Building

Fork and clone the project repository.

```bash
> cd titanium-rdf-api
> mvn package
```

