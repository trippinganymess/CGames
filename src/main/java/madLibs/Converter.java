/*Uses jackson to convert json to List object and then send it for further processing to validators.*/
package madLibs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Converter implements Serializable {
    private List<String> adjective = Collections.emptyList();
    private List<String> verb = Collections.emptyList();

    public Converter() {
        ObjectMapper mpp = new ObjectMapper();
        try {
            File adjFile = new File("src/main/java/madLibs/Dictionary/adjs.json");
            if (adjFile.exists()) {
                adjective = mpp.readValue(adjFile, new TypeReference<List<String>>() {});
            } else {
                // fallback: try loading from classpath (if packaged differently)
                InputStream in = Converter.class.getResourceAsStream("/madLibs/Dictionary/adjs.json");
                if (in != null) {
                    adjective = mpp.readValue(in, new TypeReference<List<String>>() {});
                } else {
                    System.err.println("adjs.json not found at src/main/java/madLibs/Dictionary/adjs.json and not on classpath");
                    adjective = Collections.emptyList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         try {
            File verbFile = new File("src/main/java/madLibs/Dictionary/verbs.json");
            if (verbFile.exists()) {
                verb = mpp.readValue(verbFile, new TypeReference<List<String>>() {});
            } else {
                InputStream in = Converter.class.getResourceAsStream("/madLibs/Dictionary/verbs.json");
                if (in != null) {
                    verb = mpp.readValue(in, new TypeReference<List<String>>() {});
                } else {
                    System.err.println("verbs.json not found at src/main/java/madLibs/Dictionary/verbs.json and not on classpath");
                    verb = Collections.emptyList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAdjective() {
        return adjective;
    }
    public List<String> getVerb()
    {
        return verb;
    }

}
