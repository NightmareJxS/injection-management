package codes;

//import java.sql.Date;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Utils {

    public static String getString(String message) {
        String result = "";
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(message);
                String tmp = sc.nextLine();
                if (!tmp.isEmpty()) {
                    result = tmp;
                    check = false;
                }
            } catch (Exception e) {
            }
        } while (check);
        return result;
    }

    public static String updateString(String oldValue, String message) {

        String result = oldValue;
        Scanner sc = new Scanner(System.in);
        System.out.print(message);

        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;

        }

        return result;
    }

    public static int getInt(String message) {
        int result = 0;
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(message);
                result = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println("Wrong Input!");
            }
        } while (check);
        return result;
    }

    public static int updateInt(int oldValue, String message) {
        int result = oldValue;
        String tmp;
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = Integer.parseInt(tmp);
        } else {
            return result;
        }
        return result;
    }

    public static Date getDate(String message) {
        Date result = null;
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(message);
                String tmp = sc.nextLine();
                long t = toDate(tmp);
                Date d = new Date(t);
                if (!tmp.isEmpty()) {
                    if (t < 0) {
                        System.out.println("Inputted date is invalid!");
                    } else {
                        result = d;
                        check = false;
                    }

                }
            } catch (Exception e) {

            }
        } while (check);
        return result;
    }

    public static boolean isLeap(int y) {
        boolean result = false;
        if ((y % 400 == 0) || (y % 4 == 0) && (y % 100 != 0)) {
            result = true;
        }
        return result;
    }

    public static boolean isValidDate(int y, int m, int d) {
        if (y < 0 || m < 0 || m > 12 || d < 0 || d > 31) {
            return false;
        }
        int maxD = 31;
        if (m == 4 || m == 6 || m == 9 || m == 11) {
            maxD = 30;
        } else if (m == 2) {
            if (isLeap(y)) {
                maxD = 29;
            } else {
                maxD = 28;
            }
        }
        return d <= maxD;
    }

    //convert y/m/d or y-m-d to milisec
    public static long toDate(String ymd) {
        StringTokenizer stk = new StringTokenizer(ymd, "/-");
        int y = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int d = Integer.parseInt(stk.nextToken());
        if (!isValidDate(y, m, d)) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, d);
        long t = cal.getTime().getTime();
        return t;
    }

    public static int daysBetween(java.util.Date firstInjectionDate, java.util.Date secondInjectionDate) {
        int s1y = 0;
        int s1m = 0;
        int s1d = 0;

        if ((firstInjectionDate != null)) {
            s1y = firstInjectionDate.getYear() + 1900;
            s1m = firstInjectionDate.getMonth() + 1;
            s1d = firstInjectionDate.getDate();
        }

        int s2y = 0;
        int s2m = 0;
        int s2d = 0;

        if ((secondInjectionDate != null)) {
            s2y = secondInjectionDate.getYear() + 1900;
            s2m = secondInjectionDate.getMonth() + 1;
            s2d = secondInjectionDate.getDate();
        }

        //----------Using Julian date----------------
        int firstDate = 365 * s1y + (s1y / 4) - (s1y / 100) + (s1y / 400) + ((153 * s1m + 2) / 5) + s1d + 1721119;
        int secondDate = 365 * s2y + (s2y / 4) - (s2y / 100) + (s2y / 400) + ((153 * s2m + 2) / 5) + s2d + 1721119;
        return secondDate - firstDate;
    }
    
    public static void printMenu() {
        System.out.println("=================Injection Management=================");
        System.out.println("0. Input test example (3 inputs) ");
        System.out.println("1. Show information all students have been injected ");
        System.out.println("2. Add student's vaccine injection information ");
        System.out.println("3. Updating information of students' vaccine injection ");
        System.out.println("4. Delete student vaccine injection information ");
        System.out.println("5. Search for injection information ");
        System.out.println("6. Store data to file ");
        System.out.println("7. Real time update processing ");
        System.out.println("8. Infomation encryption (RSA) ");
        System.out.println("9. Infomation decryption (RSA) ");
        System.out.println("10. Quit ");
    }
    
    public static void printSubMenu(){
        System.out.println("==============Search Injection infomation==============");
        System.out.println("5.1. Search by StudentID ");
        System.out.println("5.2. Search by StudentName ");
    }
}
