package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import metier.classes.Examen;

/**
 * 
 * @author soufiane
 *
 */
public class PDFGenerator {

	public static void xmlToPdf(Examen examen,String destination) throws Exception {
		String path_src = Properties.XML_DESTINATION_1+"/"+(examen.getDate().getYear()+1900)+"/"+(examen.getDate().getMonth()+1)+"/"+
						examen.getCode();
		String path_dst = destination+"/"+examen.getCode();
	     TransformerFactory tFactory = TransformerFactory.newInstance();
	     Transformer transformer = tFactory.newTransformer(new StreamSource(Properties.RESSOURCES+"/Examen.xsl"));
	     transformer.transform(new StreamSource(path_src+".xml"),new StreamResult(new FileOutputStream(path_dst+".html")));
	     String File_To_Convert = path_dst+".html"; 
	     Document document = new Document();
	     PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path_dst+".pdf"));
	     document.open();
	     XMLWorkerHelper.getInstance().parseXHtml(writer, document,new FileInputStream(File_To_Convert)); 
	     document.close();
	     System.out.println( "PDF Created!" );
	}
	
}
