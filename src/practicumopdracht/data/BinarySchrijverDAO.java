package practicumopdracht.data;

import practicumopdracht.models.Schrijver;

import java.io.*;

public class BinarySchrijverDAO extends SchrijverDAO{
    @Override
    public boolean save() {
        File file = new File("schrijvers.dat");

        try(
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        ) {
            dataOutputStream.writeInt(objects.size());

            for(Schrijver schrijver : objects) {
                dataOutputStream.writeUTF(schrijver.getVoornaam());
                dataOutputStream.writeUTF(schrijver.getAchternaam());
                dataOutputStream.writeInt(schrijver.getLeeftijd());
                dataOutputStream.writeBoolean(schrijver.isNogActief());
            }
        }
        catch (Exception ex) {
            System.out.println("Het werkt niet!");
        }
        return false;

    }

    @Override
    public boolean load() {
        File file = new File("schrijvers.dat");

        try(
                FileInputStream fileInputStream = new FileInputStream(file);
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        ) {
            int schrijversgrote = dataInputStream.readInt();

            for(int i = 0; i < schrijversgrote; i++) {
                String voornaam = dataInputStream.readUTF();
                String achternaam = dataInputStream.readUTF();
                int leeftijd = dataInputStream.readInt();
                boolean nogActief = dataInputStream.readBoolean();

                Schrijver schrijver = new Schrijver(voornaam, achternaam, leeftijd, nogActief);

                addOrUpdate(schrijver);
            }
        }
        catch (Exception ex) {

        }

        return false;
    }


}

