public class Trie {
    private TrieNode root;
    public Trie(){
        root = new TrieNode();
        System.out.println("The Trie has been created ");
    }
    public void insert(String word){
        TrieNode current = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            //here if the element or character which is present in the Map then we take that character from the map or if the
            //it is not character present in the Map then it return the null
            if(node == null){
                node = new TrieNode();
                current.children.put(ch,node);
            }
            current = node;
        }
        current.endofString = true;
        System.out.println("Successsfully inserted");
    }
    public boolean search(String word){
        TrieNode currentNode = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            TrieNode node = currentNode.children.get(ch);
            if(node == null){
                System.out.println("This String does not exist in Trie");
                return false;
            }
            currentNode = node;
        }
        if(currentNode.endofString == true){
            System.out.println("Word is exist in trie");
        }else{
            System.out.println("Word does not exist in trie");
        }
        return currentNode.endofString;
    }

    private boolean delete(TrieNode parentNode,String word,int index){
        char ch = word.charAt(index);
        TrieNode current = parentNode.children.get(ch);
        boolean canthisnodebedeleted;
        if(current.children.size() > 1){
            //first case is the same prefix is common between two word like apple and app here app is common
            delete(current,word,index++);
            return false;
        }
        //we are reach to last character of word but this word is the prefix of another word
        if(index  == word.length()-1)//here we reach at last of string
        {
            if (current.children.size() >= 1) {
                //the String which we want to delete is the prefix of another String
                current.endofString = false;
                return false;
            } else {
                //here when we reach to the last letter of that word then we check there is no any letter that depend on this
                //here we delete that letter
                parentNode.children.remove(ch);
                return true;
            }
        }
        if(current.endofString == true){
            //3 rd case
            //here we want to delete the String which contain the another String there for here we recursive call to the
            //deleter method here we just change the value of Stringof to false
            boolean ty = delete(current,word,index+1);
            System.out.println(ty);
            return false;
        }
        //here we use this veriable canthisnodebedeleted to check wether any word is depend on this word
        canthisnodebedeleted = delete(current,word,index+1);
        if(canthisnodebedeleted == true){
            //No any word is depend on this word
            parentNode.children.remove(ch);
            System.out.println("fd");
            return true;
        }else{
            return false;
        }
    }
    public void delete(String word){
        if(search(word) == true){
          boolean t =   delete(root,word,0);
            System.out.println(t);
        }
    }
}

