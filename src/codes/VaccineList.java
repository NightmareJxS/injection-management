package codes;

import java.util.ArrayList;

public class VaccineList extends ArrayList<Vaccine> {

    //complete but can only find 1 (fixed)
    public String searchName(String target) {
        boolean check = false;
        for (Vaccine vc : this) {
            if ((vc.name.indexOf(target)) != -1) {
                System.out.println(vc.toString());
                check = true;
            }
        }
        if (check == false) {
            return "Not Found!";
        }
        return "Search completed!";
    }

    public void showAll() {
        System.out.println("================Vaccine List================\n");
        for (Vaccine vc : this) {
            System.out.println(vc.toString());
        }
    }

}
