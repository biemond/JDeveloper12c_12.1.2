package nl.amis.hr.wadl;

import com.sun.jersey.api.wadl.config.WadlGeneratorConfig;
import com.sun.jersey.api.wadl.config.WadlGeneratorDescription;
import com.sun.jersey.server.wadl.generators.WadlGeneratorApplicationDoc;
import com.sun.jersey.server.wadl.generators.WadlGeneratorGrammarsSupport;
import com.sun.jersey.server.wadl.generators.resourcedoc.WadlGeneratorResourceDocSupport;
import com.sun.research.ws.wadl.Grammars;
import com.sun.research.ws.wadl.Include;
import com.sun.research.ws.wadl.ObjectFactory;

import java.util.List;

public class HrWadlGeneratorConfig extends WadlGeneratorConfig {

    @Override
    public List<WadlGeneratorDescription> configure() {
        ObjectFactory obj = new ObjectFactory()  ;  
        Grammars gram = obj.createGrammars();
        Include e = obj.createInclude();
        e.setHref("schema.xsd");
        gram.getInclude().add(e);
        
        WadlGeneratorConfigDescriptionBuilder builder = generator(WadlGeneratorApplicationDoc.class)
        .prop( "applicationDocsStream", "application-doc.xml" )
        .generator( WadlGeneratorGrammarsSupport.class )
        .prop( "grammarsStream", "application-grammars.xml" )
        .generator( WadlGeneratorResourceDocSupport.class )
        .prop( "resourceDocStream", "resourcedoc.xml" );
        
        return builder.descriptions();

    }

}

