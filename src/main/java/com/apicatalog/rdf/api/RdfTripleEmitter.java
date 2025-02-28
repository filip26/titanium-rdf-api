package com.apicatalog.rdf.api;

import java.util.Objects;

public class RdfTripleEmitter implements RdfQuadConsumer {

    protected final RdfTripleConsumer consumer;
    protected String emittedGraph;

    protected RdfTripleEmitter(RdfTripleConsumer consumer) {
        this.consumer = consumer;
        this.emittedGraph = "---"; // triggers graph name emitters for the first call
    }

    public static RdfQuadConsumer newInstance(RdfTripleConsumer consumer) {
        return new RdfTripleEmitter(consumer);
    }

    @Override
    public RdfQuadConsumer quad(String subject, String predicate, String object, String graph) throws RdfConsumerException {
        emitGraphChange(graph);
        consumer.triple(subject, predicate, object);
        return this;
    }

    @Override
    public RdfQuadConsumer quad(String subject, String predicate, String literal, String datatype, String graph) throws RdfConsumerException {
        emitGraphChange(graph);
        consumer.triple(subject, predicate, literal, datatype);
        return this;
    }

    @Override
    public RdfQuadConsumer quad(String subject, String predicate, String literal, String language, String direction, String graph) throws RdfConsumerException {
        emitGraphChange(graph);
        consumer.triple(subject, predicate, literal, language, direction);
        return this;
    }

    protected void emitGraphChange(final String graphName) throws RdfConsumerException {
        if (Objects.equals(emittedGraph, graphName)) {
            return;
        }
        this.emittedGraph = graphName;
        if (emittedGraph != null) {
            consumer.namedGraph(emittedGraph);
        } else {
            consumer.defaultGraph();
        }
    }
}
