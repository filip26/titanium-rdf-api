package com.apicatalog.rdf.api;

/**
 * Defines an event-based emitter supporting Triple Terms.
 * 
 * <p>
 * Usage Examples:
 * </p>
 * 
 * <pre>{@code
 * RdfQuadEmitter emitter = ...;
 *
 * // 1. Emitting a standard flat quad using convenience methods
 * emitter.quad(
 *     "http://example.org/subject", 
 *     "http://example.org/predicate", 
 *     "http://example.org/object", 
 *     "http://example.org/graph"
 * );
 *
 * // 2. Streaming a quad with a directional language-tagged literal (Czech)
 * emitter.beginQuad("http://example.org/graph");
 * emitter.subject("http://example.org/subject");
 * emitter.predicate("http://example.org/predicate");
 * emitter.literal("Ahoj", RdfQuadEmitter.DATATYPE_DIR_LANG_STRING, "cs", "ltr");
 * emitter.endQuad();
 *
 * // 3. Emitting Triple Term in the subject position (e.g., << :s :p :o >> :p2 :o2)
 * emitter.beginQuad(null); // Default graph
 * 
 * emitter.beginSubject();
 * emitter.subject("http://example.org/s");
 * emitter.predicate("http://example.org/p");
 * emitter.object("http://example.org/o");
 * emitter.endSubject();
 * 
 * emitter.predicate("http://example.org/p2");
 * emitter.object("http://example.org/o2");
 * emitter.endQuad();
 * }</pre>
 */
public interface RdfQuadEmitter extends RdfQuadConsumer {

    /**
     * Emits a complete quad statement using standard string values.
     *
     * @param subject   the subject IRI or blank node identifier
     * @param predicate the predicate IRI
     * @param object    the object IRI, blank node identifier
     * @param graph     the graph name, or null for default graph
     * @throws IllegalArgumentException if any argument is invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    default void quad(String subject, String predicate, String object, String graph) {
        beginQuad(graph);
        subject(subject);
        predicate(predicate);
        object(object);
        endQuad();
    }

    /**
     * Emits a complete quad statement using standard string values.
     *
     * @param subject   the subject IRI or blank node identifier
     * @param predicate the predicate IRI
     * @param literal   the lexical form of the literal
     * @param datatype  the datatype IRI
     * @param graph     the graph name, or null for default graph
     * @throws IllegalArgumentException if any argument is invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    default void quad(String subject, String predicate, String literal, String datatype, String graph) {
        beginQuad(graph);
        subject(subject);
        predicate(predicate);
        literal(literal, datatype);
        endQuad();
    }

    /**
     * Emits a complete quad statement using standard string values.
     *
     * @param subject   the subject IRI or blank node identifier
     * @param predicate the predicate IRI
     * @param literal   the lexical form of the literal
     * @param datatype  the datatype IRI
     * @param language  the language tag
     * @param graph     the graph name, or null for default graph
     * @throws IllegalArgumentException if any argument is invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    default void quad(String subject, String predicate, String literal, String datatype, String language,
            String graph) {
        beginQuad(graph);
        subject(subject);
        predicate(predicate);
        literal(literal, datatype, language);
        endQuad();
    }

    /**
     * Emits a complete quad statement using standard string values.
     *
     * @param subject   the subject IRI or blank node identifier
     * @param predicate the predicate IRI
     * @param literal   the lexical form of the literal
     * @param datatype  the datatype IRI
     * @param language  the language tag, or null
     * @param direction the text direction, or null
     * @param graph     the graph name, or null for default graph
     * @throws IllegalArgumentException if any argument is invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    default void quad(String subject, String predicate, String literal, String datatype, String language,
            String direction, String graph) {
        beginQuad(graph);
        subject(subject);
        predicate(predicate);
        literal(literal, datatype, language);
        endQuad();
    }

    /**
     * Starts a new quad in the default graph context.
     *
     * @throws IllegalArgumentException if the values cannot be processed
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    default void beginQuad() {
        beginQuad(null);
    }

    /**
     * Starts a new quad within the specified graph context.
     *
     * @param graph the graph name, or null for default graph
     * @throws IllegalArgumentException if the values cannot be processed
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    void beginQuad(String graph);

    /**
     * Closes the current quad context.
     *
     * @throws IllegalStateException if no quad context is currently open
     */
    void endQuad();

    /**
     * Starts a nested triple term in the subject position.
     *
     * @throws IllegalStateException if called in an invalid nesting context
     */
    void beginSubject();

    /**
     * Closes the nested triple term in the subject position.
     *
     * @throws IllegalStateException if no triple term context is open
     */
    void endSubject();

    /**
     * Starts a nested triple term in the object position.
     *
     * @throws IllegalStateException if called in an invalid nesting context
     */
    void beginObject();

    /**
     * Closes the nested triple term in the object position.
     *
     * @throws IllegalStateException if no triple term context is open
     */
    void endObject();

    /**
     * Sets the subject of the current triple term or quad.
     *
     * @param iri the subject IRI or blank node identifier
     * @throws IllegalArgumentException if the resource identifier is invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    void subject(String iri);

    /**
     * Sets the predicate of the current triple term or quad.
     *
     * @param iri the predicate IRI
     * @throws IllegalArgumentException if the predicate is invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    void predicate(String iri);

    /**
     * Sets the object of the current triple term or quad.
     *
     * @param iri the object IRI or blank node identifier
     * @throws IllegalArgumentException if the resource identifier is invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    void object(String iri);

    /**
     * Emits a literal value with a specified datatype.
     *
     * @param lexical  the lexical form of the literal
     * @param datatype the datatype IRI
     * @throws IllegalArgumentException if the values are invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    default void literal(
            String lexical,
            String datatype) {
        literal(lexical, datatype, null, null);
    }

    /**
     * Emits a language-tagged string literal.
     *
     * @param lexical  the lexical form
     * @param datatype the datatype IRI (e.g., {@link DATATYPE_LANG_STRING})
     * @param language the language tag
     * @throws IllegalArgumentException if the values are invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    default void literal(
            String lexical,
            String datatype,
            String language) {
        literal(lexical, datatype, language, null);
    }

    /**
     * Emits a full literal value with datatype, language, and direction.
     *
     * @param lexical   the lexical form
     * @param datatype  the datatype IRI
     * @param language  the language tag, or null
     * @param direction the text direction, or null
     * @throws IllegalArgumentException if the values are invalid
     * @throws IllegalStateException    if the method is called in an incorrect
     *                                  state
     */
    void literal(
            String lexical,
            String datatype,
            String language,
            String direction);
}
