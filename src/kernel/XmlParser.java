package kernel;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import graphique.ClubFrame;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public List<Club> parseClubs(String filePath) {
        List<Club> clubs = new ArrayList<>();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            
            NodeList nodeList = document.getElementsByTagName("club");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    
                    int id = Integer.parseInt(element.getAttribute("id"));
                    String nom = element.getElementsByTagName("nom").item(0).getTextContent();
                    String adresse = element.getElementsByTagName("adresse").item(0).getTextContent();
                    String contact = element.getElementsByTagName("contact").item(0).getTextContent();
                    String tel = element.getElementsByTagName("tel").item(0).getTextContent();
                    String mail = element.getElementsByTagName("mail").item(0).getTextContent();
                    String site = element.getElementsByTagName("site").item(0).getTextContent();
                    
                    Club club = new Club(id, nom, adresse, contact, tel, mail, site);
                    clubs.add(club);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
    }
    
    public static void main(String[] args) {
        XmlParser parser = new XmlParser();
        List<Club> clubs = parser.parseClubs("C:\\Users\\nassi\\Desktop\\club.xml");

        SwingUtilities.invokeLater(() -> new ClubFrame(clubs));
    }
}
