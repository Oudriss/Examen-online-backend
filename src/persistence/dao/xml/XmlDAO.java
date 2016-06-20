package persistence.dao.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import persistence.dao.AbstractDAO;

public abstract class XmlDAO extends AbstractDAO {

    protected JAXBContext context;

    protected Marshaller marshaller;

    protected Unmarshaller unmarshaller;

}
