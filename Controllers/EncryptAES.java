package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.util.Store;

import Interfaces.Cypher;

public class EncryptAES implements Cypher {

    private SecretKey secretKey;
    private Cipher cipher;
    private String transformation = "AES/CBC/PKCS5Padding";

    public EncryptAES(){

        
        try {
            this.secretKey = KeyGenerator.getInstance("AES").generateKey();
            this.cipher = Cipher.getInstance(transformation);
        } catch (Exception e) {
    
            e.printStackTrace();

        } 

    }

    public void cifrar (String content, String fileName){

        try {
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            byte[] iv = cipher.getIV();
            FileOutputStream fileOut = new FileOutputStream(fileName,true);
            CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher);
            fileOut.write(iv);
            cipherOut.write(content.getBytes());

            cipherOut.close();
            fileOut.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }

    }
    @Override
    public void decifrarArchivo(String fileName){

        
        try{
            File file = new File("clientes.txt");
            FileWriter fileOut = new FileWriter("temp.txt",false);
            FileInputStream fileIn = new FileInputStream(fileName+".txt");
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

            CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
            InputStreamReader inputReader = new InputStreamReader(cipherIn);
            BufferedReader reader = new BufferedReader(inputReader);

            String line;
            while((line = reader.readLine()) != null){

                fileOut.write(line);
                System.out.println(line);

            }
            fileOut.close();
            reader.close();
            inputReader.close();
            cipherIn.close();
            File dFile = new File("temp.txt");
            System.out.println(dFile.renameTo(file)); 
            

        }catch(Exception e){

            e.printStackTrace();	

        }

    }
    @Override
    public void cifrarArchivo(String fileName){

        File file = new File(fileName+".txt");
        File cFile = new File("temp.txt");

        try {
            cFile.createNewFile();
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){

                String data = reader.nextLine();
                cifrar(data,"temp.txt");
                

            }
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        file.delete();
        file = new File(fileName+".txt");
        cFile.renameTo(file);
      

        
    }

    
}