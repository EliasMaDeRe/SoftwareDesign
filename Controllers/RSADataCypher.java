package Controllers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Interfaces.Cypher;

import java.util.ArrayList;

public class RSADataCypher implements Cypher{

    public final String RUTA_PUBLICA = "C:\\Users\\EMadera\\Documents\\GitHub\\SoftwareDesign\\public.key";
    public final String RUTA_PRIVADA = "C:\\Users\\EMadera\\Documents\\GitHub\\SoftwareDesign\\private.key";
    public final String RUTA_DATA = "C:\\Users\\EMadera\\Documents\\GitHub\\SoftwareDesign\\clientes.txt";
    public final String ALGORITHM = "RSA";
    
    // Metodo que genera la llave a partir KeyPairGenerator usando RSA, generando una pública y privada que será guardada en el archivo keys.

    public void generateKey(){
        
        try{

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom = new SecureRandom();
            keyPairGenerator.initialize(4096,secureRandom);
            KeyPair pair = keyPairGenerator.generateKeyPair();

            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();

            saveKey(publicKey,privateKey);

        }catch(Exception e){

            e.printStackTrace();

        }

    }

    // Metodo que escribe las llaves an sus archivos .key

    public void saveKey(PublicKey publicKey, PrivateKey privateKey) throws IOException{

        FileOutputStream writer = new FileOutputStream(RUTA_PUBLICA);
        writer.write(publicKey.getEncoded());
        writer.close();

        writer = new FileOutputStream(RUTA_PRIVADA);
        writer.write(privateKey.getEncoded());
        writer.close();

    }

    public PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, NullPointerException, InvalidKeySpecException{

        File file = new File(RUTA_PRIVADA);
        byte[] privateKeyBytes = Files.readAllBytes(file.toPath());
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey reconstructedPrivateKey = keyFactory.generatePrivate(privateKeySpec);

        return reconstructedPrivateKey;

    }

    public PublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, NullPointerException, InvalidKeySpecException{

        File file = new File(RUTA_PUBLICA);
        byte[] publicKeyBytes = Files.readAllBytes(file.toPath());
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey reconstructedPublicKey = keyFactory.generatePublic(publicKeySpec);

        return reconstructedPublicKey;

    }

    public ArrayList<String> getLines() throws InvalidKeyException, NoSuchAlgorithmException, NullPointerException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{

        decifrarArchivo();
        ArrayList<String> lines = new ArrayList<String>();

        File file = new File(RUTA_DATA);
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){

            lines.add(sc.nextLine());

        }
        sc.close();
        cifrarArchivo();
        return lines;
        

    }

    public void saveLines(ArrayList<String> lines) throws IOException{

        FileWriter writer = new FileWriter(RUTA_DATA,false);
        for(int i = 0; i < lines.size(); ++i){

            writer.write(lines.get(i));
            if(i != lines.size()-1) writer.write("\n");

        }
        writer.close();

    }

    public void cifrarArchivo() {

        try{
            File file = new File(RUTA_DATA);
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            Cipher rsaCipher = Cipher.getInstance(ALGORITHM);
            rsaCipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
            byte[] encryptedFileByte = rsaCipher.doFinal(fileBytes);
            FileOutputStream writer = new FileOutputStream(RUTA_DATA);
            writer.write(encryptedFileByte);
            writer.close();
        }catch(Exception e){

            e.printStackTrace();

        }

    }

    public void decifrarArchivo(){

        try{

            File file = new File(RUTA_DATA);
            byte[] encryptedFileBytes = Files.readAllBytes(file.toPath());
            Cipher rsaCipher = Cipher.getInstance(ALGORITHM);
            rsaCipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
            byte[] decryptedFileBytes = rsaCipher.doFinal(encryptedFileBytes);
            FileOutputStream writer = new FileOutputStream(RUTA_DATA);
            writer.write(decryptedFileBytes);
            writer.close();

        }catch(Exception e){

            e.printStackTrace();

        }
    }
    

}
