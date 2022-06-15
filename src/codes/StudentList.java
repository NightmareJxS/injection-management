package codes;

import java.util.ArrayList;

public class StudentList extends ArrayList<Student> {

    //complete but can only find 1 (fixed)
    public String searchName(String target) {
        for (Student st : this) {
            if (st.name.equals(target)) {
                return st.name;
            }
        }
        return "Not Found!\n";
    }

    public void showAll() {
        System.out.println("================Student List================\n");
        for (Student st : this) {
            System.out.println(st.toString());
        }
    }

    public String getIDthroughName(String target){
        for (Student st : this) {
            if (st.name.equals(target)) {
                return st.id;
            }
        }
        return null;
    }
    
}
