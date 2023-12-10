package graphique;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import kernel.Client;
import kernel.Club;
import kernel.ListeTarifs;
import kernel.SerialisationClient;
import kernel.XmlParser;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClubFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private ClubTableModel tableModel;
    private JButton createAdherentButton;
    private JButton searchAdherentButton;
    private JButton generatePDFButton;
    private LesClients lesClients = new LesClients();
    private ListeTarifs listeTarifs; // Ajout de l'attribut listeTarifs

    public ClubFrame(List<Club> clubs) {
        // Charger les tarifs depuis le fichier XML
        listeTarifs = new ListeTarifs();
        listeTarifs.chargerTarifsDepuisXML("C:\\Users\\nassi\\Desktop\\tarif.xml");
        
        
        SerialisationClient serialisationClient = new SerialisationClient();
        List<Client> clientsDeserialises = serialisationClient.deserialiseClient();
        lesClients.setListeClients(clientsDeserialises);
		
        
        tableModel = new ClubTableModel(clubs);
        table = new JTable(tableModel);

        createAdherentButton = new JButton("Créer Adhérent");
        searchAdherentButton = new JButton("Rechercher Adhérent");
        generatePDFButton = new JButton("Générer PDF");

        createAdherentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JClient jClientFrame = new JClient(lesClients, listeTarifs); // Passer listeTarifs au JClient
                jClientFrame.setVisible(true);
            }
        });

        searchAdherentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRechercheClient jRechercheClientFrame = new JRechercheClient(); // Assurez-vous d'avoir une classe JRechercheClient définie
                jRechercheClientFrame.setVisible(true);
            }
        });
        
        generatePDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genererPDF();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createAdherentButton);
        buttonPanel.add(searchAdherentButton);
        buttonPanel.add(generatePDFButton);

        this.setTitle("Liste des Clubs");
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);  
    } 
    
    private void genererPDF() {
        // Création d'une instance de XmlParser pour lire les données des clubs
        XmlParser parser = new XmlParser();
        List<Club> clubs = parser.parseClubs("C:\\Users\\nassi\\Desktop\\club.xml");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Création d'un flux de contenu pour dessiner sur la page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Définir les marges et la taille initiale de la ligne
            final float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
            float yPosition = yStart;

            // Définir les largeurs des colonnes
            float[] columnWidths = {100, 100, 100, 100, 100, 100}; // Assurez-vous que cela correspond à votre tableau

            // Dessiner les lignes du tableau et ajouter les en-têtes
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            float nextX = margin;
            String[] headers = {"Nom", "Adresse", "Contact", "Tel", "Mail", "Site"}; // En-têtes de votre tableau
            for (int i = 0; i < headers.length; i++) {
                contentStream.moveTo(nextX, yPosition);
                contentStream.lineTo(nextX, yPosition - 20);
                contentStream.stroke();

                String text = headers[i];
                contentStream.beginText();
                contentStream.newLineAtOffset(nextX + 5, yPosition - 15);
                contentStream.showText(text);
                contentStream.endText();

                nextX += columnWidths[i];
            }

            // Dessiner la dernière ligne verticale de l'en-tête
            contentStream.moveTo(nextX, yPosition);
            contentStream.lineTo(nextX, yPosition - 20);
            contentStream.stroke();

            // Dessiner la ligne horizontale inférieure de l'en-tête
            contentStream.moveTo(margin, yPosition - 20);
            contentStream.lineTo(margin + tableWidth, yPosition - 20);
            contentStream.stroke();

            // Réinitialiser le style pour le contenu du tableau
            contentStream.setFont(PDType1Font.HELVETICA, 12);

            // Commencer à ajouter les données du tableau
            float yLinePosition = yPosition - 20; // Commencer sous l'en-tête
            float rowHeight = 20;

            for (Club club : clubs) {
                yLinePosition -= rowHeight;

                // Vérifier si une nouvelle page est nécessaire
                if (yLinePosition <= margin) {
                    contentStream.close(); // Fermer le flux de contenu actuel

                    // Créer une nouvelle page et un nouveau contentStream
                    PDPage newPage = new PDPage();
                    document.addPage(newPage);
                    contentStream = new PDPageContentStream(document, newPage);
                    yLinePosition = newPage.getMediaBox().getHeight() - margin - rowHeight;

                    // Répéter les étapes pour dessiner l'en-tête sur la nouvelle page
                    // (Omission pour la brièveté, mais inclure si nécessaire)
                }

                // Dessiner une ligne horizontale pour la nouvelle ligne
                contentStream.moveTo(margin, yLinePosition);
                contentStream.lineTo(margin + tableWidth, yLinePosition);
                contentStream.stroke();

                // Ajouter les données du club dans chaque colonne
                float textX = margin;
                String[] clubData = {
                    club.getNom(),
                    club.getAdresse(),
                    club.getContact(),
                    club.getTel(),
                    club.getMail(),
                    club.getSite()
                };

                for (int i = 0; i < clubData.length; i++) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(textX + 5, yLinePosition + 5);
                    contentStream.showText(clubData[i]);
                    contentStream.endText();
                    textX += columnWidths[i];
                }
            }

            contentStream.close(); // Assurez-vous de fermer le dernier contentStream
            document.save("ListeDesClubs.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

