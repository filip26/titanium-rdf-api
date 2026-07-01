package com.apicatalog.rdf.api;

public interface Rdf12Emitter {

    String DATATYPE_LANG_STRING = "http://www.w3.org/1999/02/22-rdf-syntax-ns#langString";
    String DATATYPE_DIR_LANG_STRING = "http://www.w3.org/1999/02/22-rdf-syntax-ns#dirLangString";

    default void quad(String subject, String predicate, String object) {
        quad(subject, predicate, object);
    }

    default void quad(String subject, String predicate, String object, String graph) {
        beginQuad(graph);
        subject(subject);
        predicate(predicate);
        object(object);
        endQuad();
    }

    default void triple(String subject, String predicate, String object) {
        subject(subject);
        predicate(predicate);
        object(object);
    }

    default void beginQuad() {
        beginQuad(null);
    }

    void beginQuad(String graph);

    void endQuad();

    void beginSubject();

    void endSubject();

    void beginObject();

    void endObject();

    void subject(String iri);

    void predicate(String iri);
    
    void object(String iri);

    default void literal(
            String lexical,
            String datatype) {
        literal(lexical, datatype, null, null);
    }

    default void literal(
            String lexical,
            String datatype,
            String language) {
        literal(lexical, datatype, language, null);
    }

    void literal(
            String lexical,
            String datatype,
            String language,
            String direction);

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
    public static boolean isLangString(String datatype, String language, String direction) {
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
    public static boolean isDirLangString(String datatype, String language, String direction) {
        return DATATYPE_DIR_LANG_STRING.equals(datatype) && language != null && direction != null;
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
