package persistence.dao.xml;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import metier.classes.Candidature;
import utils.Properties;

public class CandidatureDAO extends XmlDAO {

    public  CandidatureDAO() throws JAXBException {
        super();
        context = JAXBContext.newInstance(Candidature.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        unmarshaller = context.createUnmarshaller();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean addObject(Object o) {
        Candidature cand = (Candidature) o;
        try {
        	System.out.println("Debut de création");
            String path =  Properties.XML_DESTINATION_2+"/"+(cand.examen.getDate().getYear()+1900)+"/"
                            +(cand.examen.getDate().getMonth()+1)+"/";
            new File(path).mkdirs();
            marshaller.marshal(cand,new File(path+cand.examen.getCode()+"_"+cand.utilisateur.getCin()+".xml"));
            System.out.println("bien créée");
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modifyObject(Object o_new) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeObject(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Candidature> getAllObject() {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<Candidature> getAllObjectByCIN(String cin) {
        ArrayList<Candidature> cands = new ArrayList<>();
        try {
            ArrayList<File> files = new ArrayList<>();
            System.out.println(cin+"***************************************");
            this.listf(Properties.XML_DESTINATION_2,files,cin);
            for(File file : files){
                System.out.println(file.getAbsolutePath()+"==="+file.getName());
                cands.add((Candidature) unmarshaller.unmarshal(file));
            }
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
        }
        return cands;
    }

    private void listf(String directoryName, ArrayList<File> files,String cin) {
        File directory = new File(directoryName);

        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile() && file.getName().contains(cin) ) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files,cin);
            }
        }
    }

    public ArrayList<Candidature> getAllObjectByCode(int code) {
        ArrayList<Candidature> cands = new ArrayList<>();
        try {
            ArrayList<File> files = new ArrayList<>();
            this.listf(Properties.XML_DESTINATION_2,files,code);
            for(File file : files){
                System.out.println(file.getAbsolutePath()+"==="+file.getName());
                cands.add((Candidature) unmarshaller.unmarshal(file));
            }
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
        }
        return cands;
    }

    private void listf(String directoryName, ArrayList<File> files,int code) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file : fList) {
            String[] s = file.getName().split("_");
            if (file.isFile() && s[0].equals(String.valueOf(code)) ) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files,code);
            }
        }
    }

}
