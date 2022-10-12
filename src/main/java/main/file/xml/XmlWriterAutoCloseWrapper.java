package main.file.xml;

import javax.xml.stream.XMLStreamWriter;

class XmlWriterAutoCloseWrapper implements AutoCloseable {

    private final XMLStreamWriter writer;

    public XmlWriterAutoCloseWrapper(XMLStreamWriter writer) {
        this.writer = writer;
    }

    XMLStreamWriter writer() {
        return writer;
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
