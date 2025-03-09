package com.apicatalog.rdf.api;

/**
 * RDF quad consumer interface designed for high-performance processing and
 * seamless integration with third-party libraries.
 * <p>
 * This interface minimizes unnecessary object instantiation, improving
 * efficiency when handling RDF data at scale.
 * <p>
 * Use the provided static helper methods to analyze and validate consumer
 * parameters.
 */
@FunctionalInterface
public interface RdfQuadConsumer {

    /**
     * Consumes an RDF quad where the {@code object} may be an IRI, blank node,
     * typed literal, or language-tagged literal.
     * <p>
     * This method provides fine-grained control over RDF quad data, allowing
     * precise handling of datatypes, language tags, and text direction.
     *
     * @param subject   the subject of the quad; must be an IRI or blank node
     *                  identifier prefixed with "<code>_:</code>". Must not be
     *                  {@code null}.
     * @param predicate the predicate of the quad; must be an IRI. Must not be
     *                  {@code null}.
     * @param object    the object of the quad; must be either:
     *                  <ul>
     *                  <li>an IRI</li>
     *                  <li>a blank node identifier prefixed with
     *                  "<code>_:</code>"</li>
     *                  <li>a literal value, when {@code datatype} is not
     *                  {@code null}</li>
     *                  </ul>
     *                  Must not be {@code null}.
     *                  <p>
     *                  Use {@link #isValidObject(String, String, String)},
     *                  {@link #isLiteral(String, String, String)},
     *                  {@link #isLangString(String, String, String)}, and
     *                  {@link #isDirLangString(String, String, String)} to validate
     *                  and classify the input.
     * @param datatype  the datatype IRI of the literal. Must be {@code null} if
     *                  {@code object} is not a literal. Must not be {@code null}
     *                  when {@code language} or {@code direction} is provided.
     * @param language  the language tag of the literal. May be {@code null}, but
     *                  must not be {@code null} if {@code direction} is provided.
     * @param direction the text direction of the literal. Optional; may be
     *                  {@code null}.
     * @param graph     the graph name of the quad; must be an IRI or blank node
     *                  identifier prefixed with "<code>_:</code>". May be
     *                  {@code null} to indicate the default graph.
     *
     * @return a reference to this consumer, enabling fluent chaining; never
     *         {@code null}.
     *
     * @throws RdfConsumerException if an error occurs while processing the quad
     *                              statement.
     */
    RdfQuadConsumer quad(
            String subject,
            String predicate,
            String object,
            String datatype,
            String language,
            String direction,
            String graph) throws RdfConsumerException;

    /**
     * Determines if the provided combination of {@code datatype}, {@code language},
     * and {@code direction} qualifies the object as RDF literal.
     *
     * @param datatype  the datatype IRI
     * @param language  the language tag
     * @param direction the text direction
     * @return {@code true} indicating a literal, otherwise {@code false}.
     */
    static boolean isLiteral(String datatype, String language, String direction) {
        return datatype != null;
    }

    /**
     * Determines if the provided combination of {@code datatype}, {@code language},
     * and {@code direction} qualifies the object as an RDF language-tagged string
     * literal with no specified direction.
     * 
     * @param datatype  the datatype IRI
     * @param language  the language tag
     * @param direction the text direction
     * @return {@code true} if the provided object is RDF language-tagged literal,
     *         otherwise {@code false}.
     */
    static boolean isLangString(String datatype, String language, String direction) {
        return datatype != null && language != null && direction == null;
    }

    /**
     * Determines if the provided combination of {@code datatype}, {@code language},
     * and {@code direction} qualifies the object as an RDF directional
     * language-tagged string literal with a specified direction.
     *
     * @param datatype  the datatype IRI
     * @param language  the language tag
     * @param direction the text direction
     * @return {@code true} if the provided object is RDF directional
     *         language-tagged literal, otherwise {@code false}.
     */
    static boolean isDirLangString(String datatype, String language, String direction) {
        return datatype != null && language != null && direction != null;
    }

    /**
     * Validates whether the object is a valid RDF object based on the presence of
     * {@code datatype}, {@code language}, and {@code direction}.
     *
     * @param datatype  the datatype IRI
     * @param language  the language tag
     * @param direction the text direction
     * @return {@code true} if the object is valid according to RDF term rules.
     */
    static boolean isValidObject(String datatype, String language, String direction) {
        return datatype != null
                ? (language != null || direction == null)
                : (language == null && direction == null);
    }

    /**
     * Checks whether the provided resource identifier represents a blank node. A
     * blank node identifier must start with "<code>_:</code>".
     *
     * @param resource the resource identifier to check; may be {@code null}.
     * @return {@code true} if the resource is a non-null blank node identifier;
     *         otherwise {@code false}.
     */
    static boolean isBlank(String resource) {
        return resource != null && resource.startsWith("_:");
    }
}
