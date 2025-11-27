package madLibs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.Serializable;
import java.util.List;

public class Converter implements Serializable {
    List<String> adjective;

    Converter() {
        ObjectMapper mpp = new ObjectMapper();
        try {
            adjective = mpp.readValue(
                    new File("src/main/java/madLibs/Dictionary/adjs.json"),
                    new TypeReference<List<String>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAdjective() {
        return adjective;
    }

}
