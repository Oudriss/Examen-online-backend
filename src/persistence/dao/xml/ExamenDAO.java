package persistence.dao.xml;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import metier.classes.Examen;
import utils.Properties;

public class ExamenDAO  extends XmlDAO {

    public ExamenDAO() throws JAXBException {
        super();
        context = JAXBContext.newInstance(Examen.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        unmarshaller = context.createUnmarshaller();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean addObject(Object o){
        Examen examen = (Examen) o;

        try {
            String path =  Properties.XML_DESTINATION_1+"/"+(examen.getDate().getYear()+1900)+"/"
                            +(examen.getDate().getMonth()+1)+"/";
            new File(path).mkdirs();
            marshaller.marshal(examen,new File(path+examen.getCode()+".xml"));
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @SuppressWarnings({ "unchecked", "hiding" })
    @Override
    public  <Examen> ArrayList<Examen> getAllObject() {
        ArrayList<Examen> examens = new ArrayList<>();

        try {
            ArrayList<File> files = new ArrayList<>();
            this.listf(Properties.XML_DESTINATION_1,files);
            for (File file : files) {
                examens.add((Examen) unmarshaller.unmarshal(file));
            }
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
        }

        return examens;
    }

    private void listf(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);

        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files);
            }
        }
    }

    @Override
    public boolean modifyObject(Object o_new) {
        // TODO Auto-generated method stub
        return false;
    }

    @SuppressWarnings("deprecation")
	@Override
    public boolean removeObject(Object o) {
		Examen examen = (Examen) o;
		String path =  Properties.XML_DESTINATION_1+"/"+(examen.getDate().getYear()+1900)+"/"+(examen.getDate().getMonth()+1)+"/";
		File file = new File(path+examen.getCode()+".xml");
		return file.delete();
    }
    
    

}
