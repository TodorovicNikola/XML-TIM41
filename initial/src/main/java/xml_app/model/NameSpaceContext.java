package xml_app.model;

import java.util.Iterator;
import java.util.Map;

public class NameSpaceContext implements javax.xml.namespace.NamespaceContext {

    private Map<String, String> prefixes;

    public String getNamespaceURI(String prefix) {

        String uri = null;

        if (prefixes.containsKey(prefix))
            uri = prefixes.get(prefix);
        return uri;

    }

    @Override
    public String getPrefix(String arg0) {
        return null;
    }

    @Override
    public Iterator<?> getPrefixes(String arg0) {
        return null;
    }

    public void setPrefixes(Map<String, String> prefixes) {
        this.prefixes = prefixes;
    }

    public NameSpaceContext() { }

    public NameSpaceContext(Map<String, String> prefixes) {
        this.setPrefixes(prefixes);
    }

};
