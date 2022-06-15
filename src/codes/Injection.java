package codes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Injection implements Serializable {

    protected String id;
    protected String studentID;
    protected String studentName;
    protected String vaccineID_1;
    protected String firstInjectionPlace;
    protected Date firstInjectionDate;
    protected String vaccineID_2;
    protected String secondInjectionPlace;
    protected Date secondInjectionDate;
    private String choosenVaccineID;
    private int index;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//----------------------------------------------------------------------------------------------------------------------   
    VaccineList vList = new VaccineList();
    StudentList sList = new StudentList();
    Vaccine vc = null;
    Student st = null;
//---------------------------------------------------------------------------------------------------------------------- 

    public Injection() {
    }

    public Injection(String id) {
        this.id = id;
    }

    public Injection(String id, String studentID, String studentName, String vaccineID_1, String firstInjectionPlace, Date firstInjectionDate, String vaccineID_2, String secondInjectionPlace, Date secondInjectionDate) {
        this.id = id;
        this.studentID = studentID;
        this.studentName = studentName;
        this.vaccineID_1 = vaccineID_1;
        this.firstInjectionPlace = firstInjectionPlace;
        this.firstInjectionDate = firstInjectionDate;
        this.vaccineID_2 = vaccineID_2;
        this.secondInjectionPlace = secondInjectionPlace;
        this.secondInjectionDate = secondInjectionDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getVaccineID_1() {
        return vaccineID_1;
    }

    public void setVaccineID_1(String vaccineID_1) {
        this.vaccineID_1 = vaccineID_1;
    }

    public String getFirstInjectionPlace() {
        return firstInjectionPlace;
    }

    public void setFirstInjectionPlace(String firstInjectionPlace) {
        this.firstInjectionPlace = firstInjectionPlace;
    }

    public Date getFirstInjectionDate() {
        return firstInjectionDate;
    }

    public void setFirstInjectionDate(Date firstInjectionDate) {
        this.firstInjectionDate = firstInjectionDate;
    }

    public String getVaccineID_2() {
        return vaccineID_2;
    }

    public void setVaccineID_2(String vaccineID_2) {
        this.vaccineID_2 = vaccineID_2;
    }

    public String getSecondInjectionPlace() {
        return secondInjectionPlace;
    }

    public void setSecondInjectionPlace(String secondInjectionPlace) {
        this.secondInjectionPlace = secondInjectionPlace;
    }

    public Date getSecondInjectionDate() {
        return secondInjectionDate;
    }

    public void setSecondInjectionDate(Date secondInjectionDate) {
        this.secondInjectionDate = secondInjectionDate;
    }

    public VaccineList getvList() {
        return vList;
    }

    public void setvList(VaccineList vList) {
        this.vList = vList;
    }

    public StudentList getsList() {
        return sList;
    }

    public void setsList(StudentList sList) {
        this.sList = sList;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Injection) obj).getId());
    }

    public boolean equalsVaccineID_1(Object obj) {
        return this.vaccineID_1.equals(((Injection) obj).getVaccineID_1());
    }

    @Override
    public String toString() {
        String tmp1D = "";
        String tmp2D = "";
        if (this.firstInjectionDate == null) {
            tmp1D = null;
        } else {
            tmp1D = dateFormat.format(this.firstInjectionDate);
        }

        if (this.secondInjectionDate == null) {
            tmp2D = null;
        } else {
            tmp2D = dateFormat.format(this.secondInjectionDate);
        }
        return "**********************************\n" + "Injection ID : " + this.id + "\nStudent ID : " + this.studentID + "\nStudent Name : " + this.studentName + "\n-----1st Injection-----" + "\nVaccine ID : " + this.vaccineID_1 + "\nPlace : " + this.firstInjectionPlace + "\nDate : " + tmp1D + "\n-----2nd Injection-----" + "\nVaccine ID : " + this.vaccineID_2 + "\nPlace : " + this.secondInjectionPlace + "\nDate : " + tmp2D + "\n";
    }

    public void updateFirstInjection() throws IOException {
        System.out.println("----------------------------------\n Add first injection: ");
        System.out.println("Please choose a Vaccine by ID to add first injection: ");
        if (vList.isEmpty()) {
            readVaccineFile("Vaccines.txt");
        }
        printVaccineList();
        System.out.println("Please choose a Vaccine by ID to add first injection: ");
        choosenVaccineID = Utils.getString("Your choice : ");
        index = vList.indexOf(new Vaccine(choosenVaccineID));
        if (index != -1) {
            this.vaccineID_1 = choosenVaccineID;
            this.firstInjectionPlace = Utils.updateString(this.firstInjectionPlace, "1st Injection place : ");
            this.firstInjectionDate = Utils.getDate("1st Injection date in format (yyyy-mm-dd) : ");
            System.out.println("1st Injection Updated !");
        } else {
            System.out.println("Vaccine ID not Found !");
        }

    }

    public void updateSecondInjection() throws IOException {
        boolean checkVaccine = false;

        System.out.println("----------------------------------\n Add second injection: ");
        System.out.println("Please choose a Vaccine by ID to add second injection: ");
        if (vList.isEmpty()) {
            readVaccineFile("Vaccines.txt");
        }

        printVaccineList();
        System.out.println("Please choose a Vaccine by ID to add second injection: ");

        do {
            choosenVaccineID = Utils.getString("Your choice : ");
            if (!choosenVaccineID.equals(vaccineID_1)) {
                System.out.println("The 2nd injection vaccine must match the 1st injection vaccine!");
            } else {
                checkVaccine = true;
            }
        } while (checkVaccine == false);

        index = vList.indexOf(new Vaccine(choosenVaccineID));
        if (index != -1) {

            this.vaccineID_2 = choosenVaccineID;
            this.secondInjectionPlace = Utils.updateString(this.secondInjectionPlace, "2nd Injection place : ");

            boolean checkDays = false;
            do {

                this.secondInjectionDate = Utils.getDate("2nd Injection date in format (yyyy-mm-dd) : ");
                int daysBetween = Utils.daysBetween(this.firstInjectionDate, this.secondInjectionDate);

                System.out.println("Days between 2 injection : " + daysBetween + " Days");

                if (daysBetween > 28 && daysBetween < 84) {
                    checkDays = true;
                } else {
                    System.out.println("Invalid date! 2nd Injection must be between 4-12 weeks of the first injection!");
                }
            } while (checkDays == false);

            System.out.println("2nd Injection Updated !");
        } else {
            System.out.println("Vaccine ID not Found !");
        }

    }

    public void readVaccineFile(String file) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split("\\s+");
                vList.add(new Vaccine(splitLine[0], splitLine[1]));
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                br.close();
            }

        }
    }

    public void printVaccineList() {
        vList.showAll();
    }

    public void readStudentFile(String file) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split("\\s+");
                sList.add(new Student(splitLine[0], splitLine[1]));
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                br.close();
            }

        }
    }

    public void printStudentList() {
        sList.showAll();
    }

}
