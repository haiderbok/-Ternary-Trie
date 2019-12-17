import javax.management.monitor.Monitor;
import java.util.*;

/**
 * A Simple Word Processing class
 *
 * <p>Purdue University -- CS25100 -- Fall 2019 -- Tries</p>
 *
 */
public class WordProcessor {

    private Node wordTrie;  //Root Node of the Trie
    private int words = 0;

    /**
     * A simple Node class representing each
     * individual node of the trie
     */
    public class Node {

        protected char c;
        protected Node left, equal, right;
        protected boolean isEnd = false;

        /**
         * Constructor for Node class
         * @param ca Character assigned to the node
         */
        public Node(char ca) {
            this.c = ca;
            this.left = null;
            this.equal = null;
            this.right = null;
        }
    }

    /**
     * Defualt constructor for the WordProcessor class
     */
    public WordProcessor() {

        wordTrie = null;
    }

    /**
     * Method to add a string to the trie
     * @param s String to be added
     */

    static int no_of_char = 0;
    public void addWord(String s) {

        //System.out.println(s);

        //boolean flag to check if we are reached a null node
        boolean flag = false;



        // Inserting the first word
        if (words  == 0) {
            Node prev = null;
            for (int i = 0; i < s.length(); i++) {

                Node node = new Node(s.charAt(i));

                //Set end of the word to be true

                if (i == s.length() - 1){
                    node.isEnd = true;
                }

                if (i == 0) {
                    wordTrie = node;
                    prev = node;
                    no_of_char++;
                } else {
                    prev.equal = node;
                    prev = node;
                    no_of_char++;
                }
            }
        }
        // Insert if there is already a word in the trie
        else {
            Node current = wordTrie;
            for (int i = 0; i < s.length(); i++) {


                // Upper case lower case difference in ascii value
               // char c1 = Character.toLowerCase(current.c);
                //char c2 = Character.toLowerCase(s.charAt(i));
                char c1 = current.c;
                char c2 = s.charAt(i);

//
//                if (c1 == c2){
//                    if (Character.isUpperCase(current.c) && Character.isLowerCase(s.charAt(i))){
//                        c1 =100;
//                        c2 =99;
//                    } else if (Character.isLowerCase(current.c) && Character.isUpperCase(s.charAt(i))){
//                        c1 = 99;
//                        c2 = 100;
//                    }
//                }
                // Once we hit a null link just add the rest of the characters
                if (flag){
                    Node node = new Node(s.charAt(i));

                    //Set end of the word to be true

                    if (i == s.length() -1){
                        node.isEnd = true;
                    }

                    current.equal = node;
                    current = current.equal;
                    no_of_char++;
                    continue;
                }

               // after inserting the element in the null position we just insert every thing down below


                // ---------Equals-------------

                //If the character is the same as the current node
                if (c1 == c2){



                    // if the child of the current node is null, create a new node and add the character

                    if (current.equal == null && i != s.length() - 1){

                        i++;

                        // Create a new node
                        Node node = new Node(s.charAt(i));

                        //Set end of the word to be true

                        if (i == s.length() -1){
                            node.isEnd = true;
                        }

                        //make it the child of the current node
                        current.equal = node;

                        no_of_char++;
                        // set the current node as the new node
                        current = node;

                        //Set flag to true
                        flag = true;
                    } else {

                        // If this word is a prefix of another word.
                        if (i == s.length() - 1) {
                            current.isEnd = true;
                            break;
                        }

                        // setting the current to the child of the one if its not null
                        current = current.equal;


                    }

                    continue;

                }


                //--------Greater than -------

                if (c1  > c2) {

                    if (current.left == null){



                        // Create a new node
                        Node node = new Node(s.charAt(i));

                        //Set end of the word to be true

                        if (i == s.length() -1){
                            node.isEnd = true;
                        }

                        //make it the child of the current node
                        current.left = node;
                        no_of_char++;
                        // set the current node as the new node
                        current = node;

                        //Set flag to true
                        flag = true;

                    } else {
                        // setting the current to the child of the one if its not null
                        current = current.left;
                        i--;

                    }

                    continue;
                }


                //--------- Less-than --------
                if (c1 < c2){
                    if (current.right == null){


                        // Create a new node
                        Node node = new Node(s.charAt(i));

                        //Set end of the word to be true

                        if (i == s.length() -1){
                            node.isEnd = true;
                        }

                        //make it the child of the current node
                        current.right = node;
                        no_of_char++;
                        // set the current node as the new node
                        current = node;

                        //Set flag to true
                        flag = true;

                    } else {
                        // setting the current to the child of the one if its not null
                        current = current.right;
                        i--;
                    }


                    continue;
                }

            }

        }

       // System.out.println(no_of_char);
        words++;
    }



    /**
     * Method to add an array of strings to the trie
     * @param s Array of strings to be added
     */
    public void addAllWords(String[] s) {
        for (int i = 0; i < s.length; i++) {
            addWord(s[i]);
        }
    }

    /**
     * Method to check of a string exists in the trie
     * @param s String to be checked
     * @return true if string exists
     */
    public boolean wordSearch(String s) {
            if (s == null){
                return false;
            }

            Node current = wordTrie;

        for (int i = 0; i < s.length(); i++) {

            //if current is equal to null at any point and we haven't finished checking all the characters return false
            if (current == null){
                return false;
            }

                // Upper case lower case difference in ascii value
             //   char c1 = Character.toLowerCase(current.c);
              //  char c2 = Character.toLowerCase(s.charAt(i));
            char c1 = current.c;
            char c2 = s.charAt(i);

//                if (c1 == c2){
//                    if (Character.isUpperCase(current.c) && Character.isLowerCase(s.charAt(i))){
//                        c1 =100;
//                        c2 =99;
//                    } else if (Character.isLowerCase(current.c) && Character.isUpperCase(s.charAt(i))){
//                        c1 = 99;
//                        c2 = 100;
//                    }
//                }


            // case one: if the character is equal to the node move down
            if (c1 == c2){

                // if we are at the end
                if (i == s.length() - 1  && current.isEnd){
                    return true;
                }

                // Set the current pointer to the node below it
                current = current.equal;

                continue;
            }

            //case two: if the character is greater than the node move right
            if (c1 > c2){
                //Set current to right
                current = current.left;
                i--;
                continue;
            }

            //case two: if the character is greater than the node move left
            if (c1 < c2){

                //set current to left of the node
                current = current.right;
                i--;

            }

        }

        return false;
    }



    /**
     * Method to check if the trie if empty
     * (No stirngs added yet)
     * @return
     */
    public boolean isEmpty()
    {
        return (words == 0);
    }

    /**
     * Method to empty the trie
     */
    public void clear()
    {
        wordTrie.equal = null;
        wordTrie = null;
        words = 0;
    }


    /**
     * Getter for wordTire
     * @return Node wordTrie
     */
    public Node getWordTrie() { return wordTrie; }


    /**
     * Method to search autocomplete options
     * @param s Prefix string being searched for
     * @return List of strings representing autocomplete options
     */
    public List<String> autoCompleteOptions(String s) {

        LinkedList<String> linkedList = new LinkedList<>();

        String st = "";

        if (s == null){
            return linkedList;
        }

        // if the word already exists return empty list
        if (wordSearch(s)){
            return linkedList;
        }

        // Go to the node of the prefix

        Node current = wordTrie;

        for (int i = 0; i < s.length(); i++) {

            //if the prefix does not exist in the trie
            if (current == null){
                return linkedList;
            }



            if (current.c == s.charAt(i)){

                // we have reached the point break out

                if (i == s.length() - 1){

                    st += current.c;

                    break;
                }

                st += current.c;


                current = current.equal;
                continue;
            }

            if (current.c < s.charAt(i)){
                current = current.right;
                i--;
                continue;
            }

            if (current.c > s.charAt(i)){
                current = current.left;
                i--;
                continue;
            }

        }


        // Set the current equal to current. equal so only the nodes that come after the point are considered

        current = current.equal;
        recur(current,st,linkedList,false);





return linkedList;

    }


    private void recur (Node current, String string, LinkedList<String> linkedList, boolean flag){

        // Base case
        if (current == null){
            return;
        }

        if (flag){
            string = string.substring(0,string.length() - 1);
        }

        string += current.c;

        if (current.isEnd){
            linkedList.add(string);
        }


        recur(current.left,string,linkedList,true);

        recur(current.equal,string,linkedList, false);

        recur(current.right, string,linkedList,true);

    }

    public static void main(String[] args) {
        WordProcessor wordProcessor = new WordProcessor();

//        wordProcessor.addWord("ka");
//        wordProcessor.addWord("ma");

//        wordProcessor.addWord("testing");
//        wordProcessor.clear();
//        wordProcessor.addWord("ABCDE");
//     //   wordProcessor.addWord("ABLM");
//
//
//
//        wordProcessor.addAllWords(new String[] {"ABC", "Jay", "Jeff"});
//
//        wordProcessor.autoCompleteOptions("J");

//        String [] amankoyin = {"onur", "is" , "shit"};
//
//        wordProcessor.addAllWords(amankoyin);
//        for (int i = 0; i < amankoyin.length; i++) {
//            System.out.println(wordProcessor.wordSearch(amankoyin[i]));
//        }

        //System.out.println(wordProcessor.wordSearch(""));
        wordProcessor.addWord("Monitor");
        wordProcessor.addWord("-");
        wordProcessor.addWord("monitor");
        wordProcessor.addWord("monitor");
        wordProcessor.addWord("Mango");
        wordProcessor.addWord("cat");
        wordProcessor.addWord("bug");
        wordProcessor.addWord("cats");
        System.out.println(wordProcessor.wordSearch("Mango"));
        System.out.println(wordProcessor.wordSearch("cat"));
        System.out.println(wordProcessor.wordSearch("bug"));
        System.out.println(wordProcessor.wordSearch("cats"));
       wordProcessor.addAllWords(new String[] {"ABCDE", "ABC", "JAY", "JEFF", "JOHN", "JOE", "Jeremy", "Jeremiah", "Mongo", "Mango",
               "mousepad", "monitor", "tab",
                "Word", "box", "Navigation", "Emphasis", "Intense"});
        System.out.println(wordProcessor.wordSearch("ABCDE"));
        System.out.println(wordProcessor.wordSearch("ABC"));
        System.out.println(wordProcessor.wordSearch("JAY"));
        System.out.println(wordProcessor.wordSearch("JEFF"));
        System.out.println(wordProcessor.wordSearch("JOHN"));
        System.out.println(wordProcessor.wordSearch("JOE"));
        System.out.println(wordProcessor.wordSearch("Jeremy"));
        System.out.println(wordProcessor.wordSearch("Jeremiah"));
        System.out.println(wordProcessor.wordSearch("Mongo"));
        System.out.println(wordProcessor.wordSearch("Mango"));
        System.out.println(wordProcessor.wordSearch("mousepad"));
        System.out.println(wordProcessor.wordSearch("monitor"));
        System.out.println(wordProcessor.wordSearch("tab"));
        System.out.println(wordProcessor.wordSearch("Word"));
        System.out.println(wordProcessor.wordSearch("box"));
        System.out.println(wordProcessor.wordSearch("Navigation"));
        System.out.println(wordProcessor.wordSearch("Emphasis"));
        System.out.println(wordProcessor.wordSearch("Intense"));
        System.out.println(wordProcessor.wordSearch("-"));

    }
}
