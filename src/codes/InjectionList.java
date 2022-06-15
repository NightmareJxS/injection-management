package codes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InjectionList extends ArrayList<Injection> {

//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public String searchPlace(String target) {
        boolean check = false;
        for (Injection ij : this) {

            if (ij.secondInjectionPlace == null) {
                if (((ij.firstInjectionPlace.indexOf(target)) != -1)) {
                    System.out.println(ij.toString());
                    check = true;
                }
            } else if (((ij.firstInjectionPlace.indexOf(target)) != -1) || ((ij.secondInjectionPlace.indexOf(target)) != -1)) {
                System.out.println(ij.toString());
                check = true;
            }

        }
        if (check == false) {
            return "Not Found!\n";
        }
        return "Search completed!\n";
    }

    public String searchName(String target) {
        boolean check = false;
        for (Injection ij : this) {
            if ((ij.studentName.indexOf(target)) != -1) {
                System.out.println(ij.toString());
                check = true;
            }
        }
        if (check == false) {
            return "Not Found!\n";
        }
        return "Search completed!\n";
    }

    public void showAll() {
        System.out.println("================Injection List================\n");
        for (Injection ij : this) {
            System.out.println(ij.toString());
        }
    }

    public String searchStudentID(String target) {
        boolean check = false;
        for (Injection ijs : this) {
            if ((ijs.studentID.indexOf(target)) != -1) {
                System.out.println(ijs.toString());
                check = true;
            }
        }
        if (check == false) {
            return "Not Found!\n";
        }
        return "Search complete!\n";
    }

    public String searchDate(Date target) {
        boolean check = false;
        for (Injection ijss : this) {

            int s1y = 0;
            int s1m = 0;
            int s1d = 0;

            if ((ijss.firstInjectionDate != null)) {
                s1y = ijss.firstInjectionDate.getYear() + 1900;
                s1m = ijss.firstInjectionDate.getMonth() + 1;
                s1d = ijss.firstInjectionDate.getDate();
            }

            int s2y = 0;
            int s2m = 0;
            int s2d = 0;

            if ((ijss.secondInjectionDate != null)) {
                s2y = ijss.secondInjectionDate.getYear() + 1900;
                s2m = ijss.secondInjectionDate.getMonth() + 1;
                s2d = ijss.secondInjectionDate.getDate();
            }

            int sty = target.getYear() + 1900;
            int stm = target.getMonth() + 1;
            int std = target.getDate();

            int firstDate = 365 * s1y + (s1y / 4) - (s1y / 100) + (s1y / 400) + ((153 * s1m + 2) / 5) + s1d + 1721119;
            int secondDate = 365 * s2y + (s2y / 4) - (s2y / 100) + (s2y / 400) + ((153 * s2m + 2) / 5) + s2d + 1721119;
            int targetDate = 365 * sty + (sty / 4) - (sty / 100) + (sty / 400) + ((153 * stm + 2) / 5) + std + 1721119;

            if ((firstDate == targetDate) || (secondDate == targetDate)) {
                System.out.println(ijss.toString());
                check = true;
            }

        }

        if (check == false) {
            return "Not Found!\n";
        }
        return "Search completed!\n";
    }

}
