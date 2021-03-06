package com.amazonaws.services.neptune.io;

import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.T;

import java.io.IOException;
import java.util.Map;

public class EdgeWriter implements GraphElementHandler<Path> {

    private final Printer printer;

    public EdgeWriter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void handle(Path path, boolean allowStructuralElements) throws IOException {
        String from = path.get(3);
        String to = path.get(1);
        Map<?, Object> properties = path.get(0);
        String id = String.valueOf(properties.get(T.id));
        String label = String.valueOf(properties.get(T.label));

        printer.printStartRow();
        printer.printEdge(id, label, from, to);
        printer.printProperties(properties);
        printer.printEndRow();
    }

    @Override
    public void close() throws Exception {
        printer.close();
    }
}
