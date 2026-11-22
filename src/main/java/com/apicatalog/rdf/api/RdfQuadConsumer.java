package com.apicatalog.rdf.api;

/**
 * A consumer of RDF quads with support for RDF 1.2 directional language-tagged
 * strings and internationalized strings.
 *
 * <p>
 * An RDF quad consists of a subject, predicate, object, and graph name. The
 * object may be an IRI, a blank node, or a literal. Literal values are always
 * associated with a datatype IRI, including string literals, which use
 * {@link #DATATYPE_STRING} ({@code xsd:string}) when no other datatype applies.
 * 
 * <p>
 * Language-tagged and directional language-tagged strings are represented using
 * their respective RDF datatypes, with additional language and text-direction
 * metadata where applicable.
 *
 * <p>
 * The interface provides constants for commonly used RDF datatype IRIs and
 * utility methods for classifying RDF objects.
 *
 * @see <a href="https://www.w3.org/TR/rdf11-concepts/">RDF 1.1 Concepts and
 *      Abstract Syntax</a>
 * @see <a href="https://www.w3.org/TR/rdf12-concepts/">RDF 1.2 Concepts and
 *      Abstract Data Model</a>
 */
@FunctionalInterface
public interface RdfQuadConsumer {

    /**
     * The XML Schema datatype IRI for string literals, {@code xsd:string}.
     */
    static final String DATATYPE_STRING = "http://www.w3.org/2001/XMLSchema#string";

    /**
     * The RDF datatype IRI for language-tagged string literals,
     * {@code rdf:langString}.
     *
     * @see <a href="https://www.w3.org/TR/rdf11-concepts/#section-Graph-Literal">
     *      RDF 1.1 Concepts, Literals</a>
     */
    static final String DATATYPE_LANG_STRING = "http://www.w3.org/1999/02/22-rdf-syntax-ns#langString";

    /**
     * The RDF datatype IRI for directional language-tagged string literals,
     * {@code rdf:dirLangString}, introduced by RDF 1.2.
     *
     * @see <a href="https://www.w3.org/TR/rdf12-concepts/">RDF 1.2 Concepts and
     *      Abstract Data Model</a>
     */
    static final String DATATYPE_DIR_LANG_STRING = "http://www.w3.org/1999/02/22-rdf-syntax-ns#dirLangString";

    /**
     * The namespace IRI for datatypes representing internationalized strings with
     * language and direction information.
     *
     * <p>
     * Datatype IRIs in this namespace are used to represent language-tagged strings
     * with text direction.
     *
     * @see <a href="https://www.w3.org/ns/i18n"> Internationalization Namespace</a>
     */
    static final String DATATYPE_I18N_BASE = "https://www.w3.org/ns/i18n#";

    /**
     * Consumes an RDF quad, including the metadata required to represent literal
     * objects.
     *
     * <p>
     * An RDF quad consists of a subject, predicate, object, and graph name. The
     * object is represented by its value and, when it is a literal, its datatype
     * IRI and optional language and text-direction metadata.
     * 
     * <p>
     * The {@code datatype} parameter identifies the datatype of a literal and is
     * always required for literal objects. The {@code language} parameter carries
     * the language tag, while {@code direction} carries the text direction. These
     * parameters provide the metadata necessary to represent ordinary
     * language-tagged strings, directional language-tagged strings, and
     * internationalized strings.
     * 
     * <p>
     * For IRI and blank node objects, all three metadata parameters must be
     * {@code null}.
     * 
     * <p>
     * A {@code null} graph name represents the default graph. A non-null graph name
     * identifies a named graph and is represented by an IRI or blank node
     * identifier.
     *
     * @param subject   the subject of the quad; an IRI or blank node identifier,
     *                  must not be {@code null}
     * @param predicate the predicate of the quad; an IRI, must not be {@code null}
     * @param object    the object of the quad; an IRI, blank node identifier, or
     *                  literal value, must not be {@code null}
     * @param datatype  the datatype IRI of the literal; must not be {@code null}
     *                  for literal objects
     * @param language  the language tag of the literal; may be {@code null}
     * @param direction the text direction of the literal; may be {@code null}
     * @param graph     the graph name; an IRI or blank node identifier, or
     *                  {@code null} to indicate the default graph
     * @throws IllegalArgumentException if an error occurs while processing the quad
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
     * Determines whether the supplied metadata represents an RDF resource object.
     *
     * <p>
     * An object is classified as a resource object when none of the literal
     * metadata fields is present. This includes both IRI and blank node objects;
     * the distinction between them is determined by the object identifier itself.
     *
     * @param datatype  the datatype IRI, or {@code null}
     * @param language  the language tag, or {@code null}
     * @param direction the text direction, or {@code null}
     * @return {@code true} if the provided object is RDF resource object;
     *         {@code false} otherwise
     */
    static boolean isObject(String datatype, String language, String direction) {
        return datatype == null && language == null && direction == null;
    }

    /**
     * Determines whether the supplied metadata identifies an RDF literal.
     *
     * <p>
     * A literal is identified by the presence of a datatype IRI. The language tag
     * and text direction are additional metadata and do not affect this
     * classification.
     *
     * @param datatype  the datatype IRI, or {@code null}
     * @param language  the language tag, or {@code null}
     * @param direction the text direction, or {@code null}
     * @return {@code true} if the provided object is RDF literal; {@code false}
     *         otherwise
     */
    static boolean isLiteral(String datatype, String language, String direction) {
        return datatype != null;
    }

    /**
     * Determines whether the supplied metadata identifies an RDF language-tagged
     * literal.
     *
     * <p>
     * Such a literal uses {@link #DATATYPE_LANG_STRING} as its datatype, has a
     * non-null language tag, and has no associated text direction.
     *
     * @param datatype  the datatype IRI
     * @param language  the language tag, or {@code null}
     * @param direction the text direction, or {@code null}
     * @return {@code true} if the provided object is RDF language-tagged literal;
     *         {@code false} otherwise
     */
    static boolean isLangString(String datatype, String language, String direction) {
        return language != null
                && direction == null
                && DATATYPE_LANG_STRING.equals(datatype);
    }

    /**
     * Determines whether the supplied metadata identifies an RDF directional
     * language-tagged literal.
     *
     * <p>
     * Such a literal uses {@link #DATATYPE_DIR_LANG_STRING} as its datatype and has
     * an explicitly specified text direction.
     *
     * @param datatype  the datatype IRI
     * @param language  the language tag
     * @param direction the text direction, or {@code null}
     * @return {@code true} if the provided object is RDF directional
     *         language-tagged literal; {@code false} otherwise
     */
    static boolean isDirLangString(String datatype, String language, String direction) {
        return direction != null && DATATYPE_DIR_LANG_STRING.equals(datatype);
    }

    /**
     * Determines whether the supplied metadata identifies an internationalized
     * string using the W3C internationalization datatype namespace.
     *
     * <p>
     * Such a literal has a datatype identified by {@link #DATATYPE_I18N_BASE},
     * together with a non-null text direction.
     *
     * @param datatype  the datatype IRI
     * @param language  the language tag, or {@code null}
     * @param direction the text direction, or {@code null}
     * @return {@code true} if the provided object is an internationalized string
     *         literal; {@code false} otherwise
     */
    static boolean isI18nString(String datatype, String language, String direction) {
        return direction != null && DATATYPE_I18N_BASE.equals(datatype);
    }

    /**
     * Determines whether the supplied resource identifier represents an RDF blank
     * node.
     *
     * <p>
     * Blank node identifiers are recognized by the {@code _:} prefix. This method
     * performs a prefix check and does not validate the complete blank node
     * identifier syntax.
     *
     * @param resource the resource identifier to examine; may be {@code null}
     * @return {@code true} if the resource is a blank node identifier;
     *         {@code false} otherwise
     */
    static boolean isBlank(String resource) {
        return resource != null && resource.startsWith("_:");
    }
}