package com.apicatalog.rdf.api;

@FunctionalInterface
public interface Rdf11QuadConsumer {

    String DATATYPE_LANG_STRING = "http://www.w3.org/1999/02/22-rdf-syntax-ns#langString";

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