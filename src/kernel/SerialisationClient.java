package kernel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class SerialisationClient {


    public void serialiseClient(List<Client> lesClients) {

        try (FileOutputStream fos = new FileOutputStream("clients.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos)
            ) {
            oos.writeInt(lesClients.size());
            
            for (Client client : lesClients) {
            	oos.writeObject(client);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Client> deserialiseClient() {
        List<Client> lesClients = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("clients.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            int nbClients = ois.readInt();
            
            for (int i = 0; i < nbClients; i++) {
                Client client = (Client) ois.readObject();
                lesClients.add(client);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier 'clients.ser' non trouvé. " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erreur I/O lors de la désérialisation. " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Classe Client non trouvée. " + e.getMessage());
            e.printStackTrace();
        }
        return lesClients;
    }
}
