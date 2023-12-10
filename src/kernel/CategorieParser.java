package kernel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategorieParser implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static List<Categorie> parseCategoriesFromXML(String filePath) {
        List<Categorie> categories = new ArrayList<>();

        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("categorie");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                int id = Integer.parseInt(element.getAttribute("id"));
                String nom = element.getElementsByTagName("nom").item(0).getTextContent();
                String code = element.getElementsByTagName("code").item(0).getTextContent();
                int anneeMin = Integer.parseInt(element.getElementsByTagName("annee_min").item(0).getTextContent());
                int anneeMax = Integer.parseInt(element.getElementsByTagName("annee_max").item(0).getTextContent());

                Categorie categorie = new Categorie(id, nom, code, anneeMin, anneeMax);
                categories.add(categorie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }
}

