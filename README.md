# Titanium RDF API

A collection of straightforward micro-interfaces for processing RDF statements and facilitating seamless interoperability and data exchange across various libraries.

[![Maven Central](https://img.shields.io/maven-central/v/com.apicatalog/titanium-rdf-api.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.apicatalog%20AND%20a:titanium-rdf-api)
[![javadoc](https://javadoc.io/badge2/com.apicatalog/titanium-rdf-api/javadoc.svg)](https://javadoc.io/doc/com.apicatalog/titanium-rdf-api)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Example

This example demonstrates how to utilize the `RdfQuadConsumer` interface with Titanium JSON-LD, RDF Canonicalization, and the N-Quads writer to transform JSON-LD into canonicalized RDF and represent it in the N-QUADS format.

```javascript
// Step 1: Create an RDF Canonicalizer
var canon = RdfCanon.create(...);

// Step 2: Convert JSON-LD to RDF and pass statements to the canonicalizer
JsonLd.toRdf(...).provide(canon);

// Step 3: Canonicalize the received RDF statements and output the canonical version in N-QUADS format
var writer = new NQuadsWriter(...);
canon.provide(writer);

```

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


## Libraries

* [Titanium JSON-LD](https://github.com/filip26/titanium-json-ld)
* [Titanium RDF N-QUADS](https://github.com/filip26/titanium-rdf-n-quads)
* [Titanium RDF Dataset Canonicalization](https://github.com/filip26/titanium-rdf-canon)

