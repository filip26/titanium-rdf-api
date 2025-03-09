package com.apicatalog.rdf.api;

public class RdfConsumerException extends Exception {

    private static final long serialVersionUID = -7712839468375996952L;
    
    String subject;
    String predicate;
    String object;
    String datatype;
    String language;
    String direction;
    String graph;

    public RdfConsumerException(String message) {
        super(message);
    }

    public RdfConsumerException(Throwable e) {
        super(e);
    }

    public RdfConsumerException(String message, Throwable e) {
        super(message, e);
    }

    public RdfConsumerException(String subject, String predicate, String object, String graph, Exception e) {
        this(subject, predicate, object, null, null, null, graph, e);
    }

    public RdfConsumerException(String subject, String predicate, String literal, String datatype, String graph, Exception e) {
        this(subject, predicate, literal, datatype, null, null, graph, e);
    }

    public RdfConsumerException(String subject, String predicate, String literal, String language, String direction, String graph, Exception e) {
        this(subject, predicate, literal, null, language, direction, graph, e);
    }

    public RdfConsumerException(String subject, String predicate, String object, String datatype, String language, String direction, String graph, Exception e) {
        super(e);
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
        this.datatype = datatype;
        this.language = language;
        this.direction = direction;
        this.graph = graph;
    }

    public String getGraph() {
        return graph;
    }

    public String getObject() {
        return object;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getSubject() {
        return subject;
    }
    
    public String getDatatype() {
        return datatype;
    }

    public String getDirection() {
        return direction;
    }

    public String getLanguage() {
        return language;
    }
}
