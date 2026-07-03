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
 * @see <a href="https://www.w3.org/TR/rdf11-concepts/">W3C RDF 1.1 Concepts and
 *      Abstract Syntax</a>
 * @see <a href="https://www.w3.org/TR/rdf12-concepts/">W3C RDF 1.2 Concepts and
 *      Abstract Data Model</a>
 */
package com.apicatalog.rdf.api;