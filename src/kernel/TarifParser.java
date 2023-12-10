package kernel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TarifParser {

    public static List<Tarif> parseTarifsFromXML(String filePath) {
        List<Tarif> tarifs = new ArrayList<>();

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("tarif");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                String nom = element.getElementsByTagName("nom").item(0).getTextContent();
                String categorieMin = element.getElementsByTagName("categorieMin").item(0).getTextContent();
                String categorieMax = element.getElementsByTagName("categorieMax").item(0).getTextContent();
                double montantInscription = Double.parseDouble(element.getElementsByTagName("montantInscription").item(0).getTextContent());
                double montantLicence = Double.parseDouble(element.getElementsByTagName("montantLicence").item(0).getTextContent());

                double licenceSansAssurance = Double.parseDouble(element.getElementsByTagName("LicenceSansAssurance").item(0).getTextContent());
                double licenceAssuranceRenforcee = Double.parseDouble(element.getElementsByTagName("LicenceAssuranceRenforcee").item(0).getTextContent());
                double deuxiemeMembreFamille = Double.parseDouble(element.getElementsByTagName("DeuxiemeMembreFamille").item(0).getTextContent());
                double troisiemeMembreFamille = Double.parseDouble(element.getElementsByTagName("TroisiemeMembreFamille").item(0).getTextContent());

                Tarif tarif = new Tarif(nom, categorieMin, categorieMax, montantInscription, montantLicence, 
                    licenceSansAssurance, licenceAssuranceRenforcee, deuxiemeMembreFamille, troisiemeMembreFamille);
                tarifs.add(tarif);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tarifs;
    }
}
