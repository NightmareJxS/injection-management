package codes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import javax.crypto.Cipher;

public class InjectionManagement {

    public static void createFile(String file) {
        try {
            File f = new File(file);
            f.createNewFile();
        } catch (Exception e) {
        }
    }

    public static boolean writeFile(String file, String data) throws IOException {
        boolean check = false;
        BufferedWriter bw = null;
        try {
            Writer w = new FileWriter(file);
            bw = new BufferedWriter(w);
            bw.write(data);
            check = true;
        } catch (Exception e) {
        } finally {
            if (bw != null) {
                bw.close();
            }
        }

        return check;
    }

    public static void readFile(String file) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                br.close();
            }

        }
    }

    private static File createKeyFile(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

//---------------------------------------------------------------------------------------------------------------------    
    public static void main(String[] args) throws IOException {

        try {
            InjectionList iList = new InjectionList();
            int choice = 0;
            String fileName = "Injection.txt";
            createFile(fileName);
            int tmpID = 1;
            boolean updateFile = false;
            do {
                Utils.printMenu();
                choice = Utils.getInt("Input your choice: ");
                Injection ij = null;
                String id = "";
                String name = "";
                int index = 0;
                boolean check = false;
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                switch (choice) {
                    case 0:
                        ij = new Injection();
                        ij.readVaccineFile("Vaccines.txt");
                        ij.readStudentFile("Students.txt");
//                        In order :  Injection(id, studentID, studentName, VaccineID_1, place, date, VaccineID_2, place, date))
                        for (Student st : ij.getsList()) {
                            if (tmpID == 4) {
                                break;
                            }
                            String tmpS = String.valueOf(tmpID);
                            iList.add(new Injection(tmpS, st.id, st.name, null, null, null, null, null, null));
                            tmpID++;

                        }

                        //Note : add direct input and make sure to check. (Done)
                        iList.get(0).vaccineID_1 = "Covid-V004";
                        iList.get(0).firstInjectionPlace = "Da Nang";
                        iList.get(0).firstInjectionDate = dateFormat.parse("2021-05-20");

                        iList.get(1).vaccineID_1 = "Covid-V001";
                        iList.get(1).firstInjectionPlace = "Ho Chi Minh";
                        iList.get(1).firstInjectionDate = dateFormat.parse("2021-12-31");
                        iList.get(1).vaccineID_2 = "Covid-V001";
                        iList.get(1).secondInjectionPlace = "Dong Nai";
                        iList.get(1).secondInjectionDate = dateFormat.parse("2022-04-12");

                        iList.get(2).vaccineID_1 = "Covid-V003";
                        iList.get(2).firstInjectionPlace = "Ho Chi Minh";
                        iList.get(2).firstInjectionDate = dateFormat.parse("2020-09-12");

                        System.out.println("3 sample inputs added!");

                        if (updateFile) {
//----------------------------Update File---------------------------------                         
                            String content = "";
                            for (Injection ijs : iList) {
                                content += ijs.toString();

                            }
                            try {
                                writeFile(fileName, content);
                            } catch (Exception e) {
                            }

                            System.out.println("File saved!");
//-------------------------------------------------------------------------                            
                        }

                        break;

                    case 1:
                        System.out.println("");
                        readFile("Injection.txt");
                        break;
                    case 2:
                        String choice_c = "";
                        do {

                            System.out.println("Please choose a student by Student Name to add first injection: ");
                            ij = new Injection();
                            ij.readStudentFile("Students.txt");
                            ij.printStudentList();
                            System.out.println("Please choose a student by Student Name to add first injection: ");
                            name = Utils.getString("Your choice : ");

                            String search = iList.searchName(name);
                            if ((search == "Not Found!\n")) {

                                if (ij.getsList().searchName(name) != "Not Found!\n") {

                                    System.out.println("Student haven't got an injection ");
                                    String tmpS = String.valueOf(tmpID);
                                    iList.add(new Injection(tmpS, ij.getsList().getIDthroughName(name), name, null, null, null, null, null, null));

                                    ij = iList.get(tmpID - 1);
                                    boolean checkComplete = false;
                                    do {
                                        ij.updateFirstInjection();
                                        if (ij.getVaccineID_1() != null) {
                                            checkComplete = true;
                                        }
                                    } while (checkComplete == false);

                                    iList.set(tmpID - 1, ij);

                                    tmpID++;

                                } else {
                                    System.out.println("Student name not found in Student List!");
                                    System.out.println("Please enter the name exactly to the name in the Student List!");
                                }

                            } else {
                                System.out.println("The Student had already got the first injection! ");
                            }

                            if (updateFile) {

//----------------------------Update File---------------------------------                        
                                String content2 = "";
                                for (Injection ijs : iList) {
                                    content2 += ijs.toString();
//                            
                                }
                                try {
                                    writeFile(fileName, content2);
                                } catch (Exception e) {
                                }

                                System.out.println("File saved!");
//-------------------------------------------------------------------------                        
                            }

                            choice_c = Utils.getString("Countinue Add 1st Injection ? (y/n): ");

                        } while (choice_c.equalsIgnoreCase("y"));

                        break;
                    case 3:
                        System.out.println("Please choose a student by Injection ID to add Second injection: ");

                        iList.showAll();
                        System.out.println("Please choose a student by Injection ID to add Second injection: ");
                        id = Utils.getString("Your choice : ");

                        index = iList.indexOf(new Injection(id));

                        if (index != -1) {

                            ij = iList.get(index);
                            String checkIf = ij.getVaccineID_1();
                            String choice_c2 = "";

                            if (checkIf == null) {
                                System.out.println("The Student haven't got the first injection!");
                                choice_c2 = Utils.getString("Do you want to add the student first injection? (y/n): ");
                                if (choice_c2.equalsIgnoreCase("y")) {
                                    ij.updateFirstInjection();
                                    iList.set(index, ij);

                                } else {
                                    break;
                                }

                            } else {
                                ij.updateSecondInjection();
                                iList.set(index, ij);

                            }

                            System.out.println("Injection List Updated !");
                        } else {
                            System.out.println("Injection ID not Found !");
                        }

                        if (updateFile) {

//----------------------------Update File---------------------------------                        
                            String content2 = "";
                            for (Injection ijs : iList) {
                                content2 += ijs.toString();
//                            
                            }
                            try {
                                writeFile(fileName, content2);
                            } catch (Exception e) {
                            }

                            System.out.println("File saved!");
//-------------------------------------------------------------------------                           
                        }
                        break;
                    case 4:
                        iList.showAll();
                        id = Utils.getString("Enter the Injection ID you want to delete : ");
                        int choiceContinue = -1;
                        System.out.println("Are you sure you want to delete injection ID : " + id);
                        choiceContinue = Utils.getInt("Enter 1 to countinue or 0 to cancel : ");
                        if (choiceContinue == 1) {
                            check = iList.remove(new Injection(id));
                            if (check) {
                                System.out.println("Removed !");
                            } else {
                                System.out.println("ID not Found");
                            }
                        } else {
                            System.out.println("Delete cancel!");
                        }

                        if (updateFile) {

//----------------------------Update File---------------------------------                        
                            String content3 = "";
                            for (Injection ijs : iList) {
                                content3 += ijs.toString();
//                            
                            }
                            try {
                                writeFile(fileName, content3);
                            } catch (Exception e) {
                            }

                            System.out.println("File saved!");
//-------------------------------------------------------------------------                         
                        }
                        break;
                    case 5:
//                        Date tmps = Utils.getDate("Input Date : ");
//                        System.out.println(iList.searchDate(tmps));
                        ij = new Injection();
                        ij.readStudentFile("Students.txt");
                        ij.printStudentList();

                        int choiceSubMenu = 0;
                        Utils.printSubMenu();
                        choiceSubMenu = Utils.getInt("Input your choice : ");
                        switch (choiceSubMenu) {
                            case 1:
                                id = Utils.getString("Please enter the student id you want to search for injection infomation : ");
                                System.out.println(iList.searchStudentID(id));

                                break;
                            case 2:
                                name = Utils.getString("Please enter the student place you want to search for injection infomation : ");
                                System.out.println(iList.searchPlace(name));

                                break;
                        }

                        break;
                    case 6:

//----------------------------Update File---------------------------------                         
                        String content4 = "";
                        for (Injection ijs : iList) {
                            content4 += ijs.toString();

                        }
                        try {
                            writeFile(fileName, content4);
                        } catch (Exception e) {
                        }

                        System.out.println("File saved!");
//-------------------------------------------------------------------------
                        break;
                    case 7:

                        String tmpUp = Utils.getString("Do you want to turn on Real time update processing (y/n) : ");
                        if (tmpUp.equalsIgnoreCase("y")) {
                            System.out.println("Real time update processing turned on !!!");
                            updateFile = true;
                        } else {
                            if (tmpUp.equalsIgnoreCase("n")) {
                                System.out.println("Real time update processing turned off !!!");
                                updateFile = false;
                            } else {
                                System.out.println("Nothing had change. Please enter the correct input!");
                            }
                        }

                        break;

                    case 8:
                        //----------------Public and Private keys-----------------
                        try {
                            //Create Public key and Private key
                            SecureRandom sr = new SecureRandom();
                            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                            kpg.initialize(8192, sr);

                            //Create key pair
                            KeyPair kp = kpg.genKeyPair();
                            //Public key
                            PublicKey publicKey = kp.getPublic();
                            //Private key
                            PrivateKey privateKey = kp.getPrivate();

                            File publicKeyFile = createKeyFile(new File("publicKey.rsa"));
                            File privateKeyFile = createKeyFile(new File("privateKey.rsa"));

                            //Save Puclic key
                            FileOutputStream fos = new FileOutputStream(publicKeyFile);
                            fos.write(publicKey.getEncoded());
                            fos.close();

                            //Save Private key
                            fos = new FileOutputStream(privateKeyFile);
                            fos.write(privateKey.getEncoded());
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //-------------------Encode Data--------------------------
                        try {
                            //read Publickey File
                            FileInputStream fis = new FileInputStream("publicKey.rsa");
                            byte[] b = new byte[fis.available()];
                            fis.read(b);
                            fis.close();

                            //Create public key
                            X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
                            KeyFactory factory = KeyFactory.getInstance("RSA");
                            PublicKey pubKey = factory.generatePublic(spec);

                            //Encode Data
                            Cipher c = Cipher.getInstance("RSA");
                            c.init(Cipher.ENCRYPT_MODE, pubKey);

                            String message = "";
                            for (Injection ijs : iList) {
                                message += ijs.toString();
                            }

                            byte encryptOut[] = c.doFinal(message.getBytes());
                            String strEnypt = Base64.getEncoder().encodeToString(encryptOut);

                            //write out to Injection file
                            try {
                                writeFile(fileName, strEnypt);
                            } catch (Exception e) {
                            }
                            
                            System.out.println("Encryption complete!");
                            System.out.println("File saved!");

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case 9:
                        //-------------------Decode Data--------------------------
                        try {
                            //read PrivateKey File
                            FileInputStream fis = new FileInputStream("privateKey.rsa");
                            byte[] b = new byte[fis.available()];
                            fis.read(b);
                            fis.close();

                            //create Private key
                            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
                            KeyFactory factory = KeyFactory.getInstance("RSA");
                            PrivateKey priKey = factory.generatePrivate(spec);

                            //Decode Data
                            Cipher c = Cipher.getInstance("RSA");
                            c.init(Cipher.DECRYPT_MODE, priKey);

                            BufferedReader br = new BufferedReader(new FileReader("Injection.txt"));
                            String line = br.readLine();

                            byte decryptOut[] = c.doFinal(Base64.getDecoder().decode(line));
                            System.out.println(new String(decryptOut));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;

                }

            } while (choice != 10);

        } catch (Exception e) {
        }

    }
}
