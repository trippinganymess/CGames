package madLibs;

import java.util.List;

public class Validators {
    static List<String> adjective;
    static List<String> verb;
    public List<String> useConAdj() {
        Converter converter = new Converter();
        adjective = converter.getAdjective();
         if (adjective == null) {
            System.out.println("No adjectives loaded (adjs.json missing or parse error).");   
        }
        return adjective;
    }
    public List<String> useConVer()
    {
        Converter converter = new Converter();
        verb = converter.getVerb();
        if(verb == null)
        {
            System.out.println("No verbs loaded . {verbs.json missing}");  
        }
        return verb;
    }

    public boolean SearchWord(String target, List<String> listOfWords) {
        if (listOfWords == null || listOfWords.isEmpty() || target == null) return false;
        boolean result = false;
        int start = 0, end = listOfWords.size() - 1, mid, cmp;
        while (start <= end) {
            mid = start + (end - start) / 2;
            cmp = listOfWords.get(mid).compareTo(target);
            if (cmp == 0) {
                result = true;
            }
            if (cmp < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }

}
