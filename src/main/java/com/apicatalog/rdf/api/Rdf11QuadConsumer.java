package com.apicatalog.rdf.api;

/**
 * Represents a consumer of RDF 1.1 quads, extended with RDF 1.2 text direction.
 * <p>
 * This functional interface provides a mechanism to process or consume RDF
 * statements represented as quads, with support for datatypes, language tags,
 * and directional language-tagged strings.
 */
@FunctionalInterface
public interface Rdf11QuadConsumer {

    /**
     * The datatype IRI for RDF language-tagged strings.
     */
    String DATATYPE_LANG_STRING = "http://www.w3.org/1999/02/22-rdf-syntax-ns#langString";

    String DATATYPE_DIR_LANG_STRING = "http://www.w3.org/1999/02/22-rdf-syntax-ns#dirLangString";

    String I18N_BASE = "https://www.w3.org/ns/i18n#";

    /**
     * Consumes an RDF quad where the {@code object} may be an IRI, blank node,
     * typed literal, or language-tagged literal.
     * <p>
     * This method provides fine-grained control over RDF quad data, allowing
     * precise handling of datatypes and language tags.
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
     *                  Use {@link #isLiteral(String)},
     *                  {@link #isLangString(String, String, String)},
     *                  {@link #isDirLangString(String, String, String)}, and
     *                  {@link #isI18nString(String, String, String)} to validate
     *                  and classify the input.
     * @param datatype  the datatype IRI of the literal. Must be {@code null} if
     *                  {@code object} is not a literal. Must not be {@code null}
     *                  when {@code language} is provided.
     * @param language  the language tag of the literal. May be {@code null}.
     * @param direction the text direction, or null
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
            String direction,
            String graph);

    /**
     * Determines if the absence of {@code datatype}, {@code language}, and
     * {@code direction} qualifies the entity as a general non-literal RDF object.
     *
     * @param datatype  the datatype IRI
     * @param language  the language tag
     * @param direction the text direction
     * @return {@code true} if all parameters are {@code null}, indicating a
     *         non-literal object, otherwise {@code false}.
     */
    static boolean isObject(String datatype, String language, String direction) {
        return datatype == null && language == null && direction == null;
    }

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
        return datatype != null || language != null || direction != null;
    }

    /**
     * Determines if the provided combination of {@code datatype}, and
     * {@code language} qualifies the object as an RDF language-tagged string
     * literal with no specified direction.
     * 
     * @param datatype  the datatype IRI
     * @param language  the language tag
     * @param direction the text direction
     * @return {@code true} if the provided object is RDF language-tagged literal,
     *         otherwise {@code false}.
     */
    static boolean isLangString(String datatype, String language, String direction) {
        return DATATYPE_LANG_STRING.equals(datatype) && language != null && direction == null;
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
        return DATATYPE_DIR_LANG_STRING.equals(datatype) && language != null && direction != null;
    }

    /**
     * Determines if the provided combination of {@code datatype}, {@code language},
     * and {@code direction} qualifies the object as an internationalized string
     * literal with a specified direction.
     *
     * @param datatype  the datatype IRI
     * @param language  the language tag
     * @param direction the text direction
     * @return {@code true} if the provided object is an internationalized string
     *         literal, otherwise {@code false}.
     */
    static boolean isI18nString(String datatype, String language, String direction) {
        return I18N_BASE.equals(datatype) && direction != null;
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