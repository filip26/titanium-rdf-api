/**
 * Simple and universal RDF micro-interfaces enabling fast and efficient
 * integration of RDF processors.
 * 
 * <p>
 * This package provides dedicated interfaces optimized for different versions
 * of the RDF specification:
 * </p>
 * <ul>
 * <li>{@link com.apicatalog.rdf.api.Rdf11QuadConsumer} - Tailored for consuming
 * RDF 1.1 stream data.</li>
 * <li>{@link com.apicatalog.rdf.api.Rdf12QuadEmitter} - Tailored for
 * event-based production of RDF 1.2 data, adding support for Triple Terms and
 * directional language-tagged strings.</li>
 * </ul>
 * 
 * <p>
 * <b>Deprecation Notice:</b>
 * </p>
 * <p>
 * The legacy interface {@link com.apicatalog.rdf.api.RdfQuadConsumer} and the
 * custom checked exception {@link com.apicatalog.rdf.api.RdfConsumerException}
 * have been deprecated since version 2.0.0. Migrate to the specialized
 * consumer/emitter interfaces listed above, which utilize standard runtime
 * exceptions ({@link java.lang.IllegalArgumentException} and
 * {@link java.lang.IllegalStateException}) for lifecycle and validation errors.
 * </p>
 * 
 * <p>
 * Usage Example (Emitting RDF 1.2 with directional text in Czech):
 * </p>
 * 
 * <pre>{@code
 * Rdf12QuadEmitter emitter = ...;
 * 
 * emitter.beginQuad("http://example.org/graph");
 * emitter.subject("http://example.org/subject");
 * emitter.predicate("http://example.org/predicate");
 * emitter.literal("Ahoj", Rdf12QuadEmitter.DATATYPE_DIR_LANG_STRING, "cs", "ltr");
 * emitter.endQuad();
 * }</pre>
 */
package com.apicatalog.rdf.api;