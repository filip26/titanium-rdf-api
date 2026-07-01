package com.apicatalog.rdf.api;

@FunctionalInterface
public interface Rdf11QuadConsumer {

    String DATATYPE_LANG_STRING = "http://www.w3.org/1999/02/22-rdf-syntax-ns#langString";

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
     *                  Use {@link #isLiteral(String, String, String)},
     *                  {@link #isLangString(String, String, String)} to validate
     *                  and classify the input.
     * @param datatype  the datatype IRI of the literal. Must be {@code null} if
     *                  {@code object} is not a literal. Must not be {@code null}
     *                  when {@code language} or {@code direction} is provided.
     * @param language  the language tag of the literal. May be {@code null}, but
     *                  must not be {@code null} if {@code direction} is provided.
     * @param graph     the graph name of the quad; must be an IRI or blank node
     *                  identifier prefixed with "<code>_:</code>". May be
     *                  {@code null} to indicate the default graph.
     *
     * @throws IllegalArgumentException if an error occurs while processing the quad
     *                                  statement.
     */
    void quad(
            String subject,
            String predicate,
            String object,
            String datatype,
            String language,
            String graph);

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
    public static boolean isLangString(String datatype, String language) {
        return DATATYPE_LANG_STRING.equals(datatype) && language != null;
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