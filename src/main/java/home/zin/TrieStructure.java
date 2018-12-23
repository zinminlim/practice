package home.zin;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zinlim on 12/22/18.
 *
 * run time is O(M) -- M is key size
 *
 */
public class TrieStructure {
    private static int ALPHABETSIZE = 26;

    TrieNode root = new TrieNode('r');

    public boolean search(String key){
        if (StringUtils.isNotEmpty(key)){
            TrieNode curr = root;
            for (int i=0; i < key.length(); i++){
                int index = key.charAt(i) - 'a';
                TrieNode child = curr.children[index];
                if (child == null){
                   return false;
                }
                curr = child;
            }
            if (curr.isEndOfWord){
                return true;
            }
        }
        return false;
    }


    public List<String> preFixSearch(String key){
        List<String> results = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)){
            TrieNode curr = root;
            for (int i=0; i < key.length(); i++){
                int index = key.charAt(i) - 'a';
                TrieNode child = curr.children[index];
                if (child == null){
                    return null;
                }
                curr = child;
            }
            recursePrefixSearch(key, results, curr);
        }
        return results;
    }


    public void recursePrefixSearch(String prefix, List<String> result, TrieNode currNode){
        String currPrefix = prefix+currNode.c;
        if (currNode.isEndOfWord){
            result.add(currPrefix);
        }
        for (TrieNode n: currNode.children){
            if (n != null){
                recursePrefixSearch(currPrefix, result, n);
            }
        }
    }

    public void insert(String key){
        if (StringUtils.isNotEmpty(key)){
            TrieNode curr = root;
            for (int i=0; i < key.length(); i++){
                int index = key.charAt(i) - 'a';
                TrieNode child = curr.children[index];
                if (child == null){
                    child = new TrieNode(key.charAt(i));
                    curr.children[index] = child;
                }
                curr = child;
            }
            curr.isEndOfWord = true;
        }
    }

    public static void main(String[] args) {
        TrieStructure ts = new TrieStructure();

        ts.insert("hello");
        ts.insert("world");
        ts.insert("wood");
        ts.insert("worn");

        System.out.println(ts.search("hello"));
        System.out.println(ts.search("hell"));
        System.out.println(ts.preFixSearch("wo"));
        System.out.println(ts.preFixSearch("wok"));
    }


    private static class TrieNode {
        char c;
        TrieNode (char c){
            this.c = c;
        }
        boolean isEndOfWord = false;
        TrieNode[] children = new TrieNode[ALPHABETSIZE];
    }
}
