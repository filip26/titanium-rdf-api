/**
 * Simple and universal RDF micro-interfaces enabling fast and efficient
 * integration of RDF processors.
 * 
 * <p>
 * This package provides dedicated interfaces optimized for different versions
 * of the RDF specification:
 * </p>
 * <ul>
 * <li>{@link com.apicatalog.rdf.api.RdfQuadConsumer} - Tailored for consuming
 * RDF stream data without Triple Terms.</li>
 * <li>{@link com.apicatalog.rdf.api.RdfQuadEmitter} - Tailored for event-based
 * production of Triple Terms.</li>
 * </ul>
 * 
 * <p>
 * Usage Example (Emitting Triple Term with directional text in Czech):
 * </p>
 * 
 * <pre>{@code
 * RdfQuadEmitter emitter = ...;
 * 
 * emitter.beginQuad("http://example.org/graph");
 * emitter.subject("http://example.org/subject");
 * emitter.predicate("http://example.org/predicate");
 * emitter.literal("Ahoj", RdfQuadEmitter.DATATYPE_DIR_LANG_STRING, "cs", "ltr");
 * emitter.endQuad();
 * }</pre>
 */
package com.apicatalog.rdf.api;