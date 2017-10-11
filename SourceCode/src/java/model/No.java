package model;
import java.util.List;

public class No {

    private String id;

    public No(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static No getNoById(String id, List<No> nos) {
        for (No no : nos) {
            if (no.getId().equals(id)) {
                return no;
            }
        }
        return null;
    }

}
