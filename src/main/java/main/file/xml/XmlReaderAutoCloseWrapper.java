package main.file.xml;

import javax.xml.stream.XMLStreamReader;

class XmlReaderAutoCloseWrapper implements AutoCloseable {

    private final XMLStreamReader reader;

    public XmlReaderAutoCloseWrapper(XMLStreamReader reader) {
        this.reader = reader;
    }

    XMLStreamReader reader() {
        return reader;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
