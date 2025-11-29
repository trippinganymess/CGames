package madLibs;

import java.util.List;

public class Validators {
    private List<String> adjective;
    private List<String> verb;
    private boolean initialized = false;

    private void initializeIfNeeded() {
        if (!initialized) {
            Converter converter = new Converter();
            adjective = converter.getAdjective();
            verb = converter.getVerb();
            initialized = true;
            if (adjective == null) {
                System.out.println("No adjectives loaded (adjs.json missing or parse error).");
            }
            if (verb == null) {
                System.out.println("No verbs loaded (verbs.json missing or parse error).");
            }
        }
    }

    public List<String> useConAdj() {
        initializeIfNeeded();
        return adjective;
    }

    public List<String> useConVer() {
        initializeIfNeeded();
        return verb;
    }

    public boolean SearchWord(String target, List<String> listOfWords) {
        if (listOfWords == null || listOfWords.isEmpty() || target == null) return false;
        int start = 0, end = listOfWords.size() - 1, mid, cmp;
        while (start <= end) {
            mid = start + (end - start) / 2;
            cmp = listOfWords.get(mid).compareTo(target);
            if (cmp == 0) {
                return true;
            }
            if (cmp < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

}
