/*
 * Name: TODO
 * PID:  TODO
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Search Engine implementation.
 * 
 * @author TODO
 * @since  TODO
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                addValues(cast, movie, movieTree);
                addValues(studios, movie, studioTree);
                addValues(cast, rating, ratingTree);

                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    private static void addValues(String[] keys, String value, BSTree<String> bst){
        value = value.toLowerCase();
        for(int i = 0; i < keys.length; i++){
            bst.insert(keys[i]);
            if(!bst.findDataList(keys[i]).contains(value))//If data is not already in dataList
                bst.insertData(keys[i], value);

        }
    }
    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // process query
        String[] keys = query.toLowerCase().split(" ");

        LinkedList<String> allElems = new LinkedList<>();
        for(int i = 0; i < keys.length; i++){
            if(!searchTree.findKey(keys[i]))//If not in list, skip adding this one's data
                continue;
            allElems.removeAll(searchTree.findDataList(keys[i]));
            allElems.addAll(searchTree.findDataList(keys[i]));
            //Remove all then add all to prevent duplicates
        }
        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
        LinkedList<String> intersection = new LinkedList<>(allElems);
        for(int i = 0; i < intersection.size(); i++) {
            if (!searchTree.findKey(keys[i].toLowerCase()))//If not in list, skip retaining this one's data
                continue;
            intersection.retainAll(searchTree.findDataList(keys[i].toLowerCase()));
        }
        print(query, intersection);
        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        if(keys.length > 1){
            for(int i = 0; i < keys.length; i++){//Outer loop, printing each individual result
                if (!searchTree.findKey(keys[i].toLowerCase())) {//Don't get unique elements of this list if key is not in BST
                    print(keys[i], null);
                    continue;
                }
                LinkedList<String> unique = new LinkedList<>(allElems);
                for(int j = 0; j < keys.length; j++){//Removing all elements that are in other lists
                    if(j == i)//except for this list
                        continue;
                    unique.removeAll(searchTree.findDataList(keys[j].toLowerCase()));
                }
                if(unique.size() == 0)
                    continue;
                print(keys[i], unique);
            }
        }

    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {


        // initialize search trees
        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();
        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);
        // choose the right tree to query
        searchMyQuery(ratingTree, args[2] + " " + args[3]);

    }
}
