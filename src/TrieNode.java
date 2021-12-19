import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character,TrieNode> children;
    boolean endofString;
    public TrieNode(){
        children = new HashMap<>();
        endofString = false;
    }
}
