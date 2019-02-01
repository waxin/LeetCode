class Solution {
    public List<List<String>> results = new ArrayList<List<String>>();
    public Map<String, List<String>> reverseOrderTree = new HashMap<String, List<String>>();
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> result = new ArrayList<String>();
        boolean endWordIn = checkEndwordExist(endWord, wordList);
        //Map<String, List<String>> reverseOrderTree = new HashMap<String, List<String>>();

        reverseOrderTree = createReverseOrderTree(beginWord, endWord, wordList);
        //System.out.println(reverseOrderTree.get(endWord));
        if(!endWordIn)
            return results;
        
        findOneLadder(endWord, beginWord, result);
        return results;
    }
    
    public void findOneLadder(String beginWord, String endWord, List<String> result){
        List<String> parentNodes = reverseOrderTree.get(beginWord);
        //System.out.println(parentNodes);
        if(beginWord.equals(endWord)){
            result.add(0, endWord);
            results.add(new ArrayList(result));
            result.remove(endWord);
        }
        else{
            for(String parent : parentNodes){
                result.add(0, beginWord);
                findOneLadder(parent, endWord, result);
                result.remove(beginWord);
            }
        } 
    }
    
    public Map<String, List<String>> createReverseOrderTree(String beginWord, String endWord, List<String> wordList){
        Map<String, List<String>> reverseOrderTree = new HashMap<String, List<String>>();
        Map<String, Integer> nodeDepth = new HashMap<String, Integer>();
        Queue<String> exploredQueue = new LinkedList<String>();
        
        for(String word : wordList){
            nodeDepth.put(word, -1);
            reverseOrderTree.put(word, new ArrayList<String>());
        }
        nodeDepth.put(beginWord, 0);
        exploredQueue.add(beginWord);
        
        while(!exploredQueue.isEmpty()){
            String parentNode = exploredQueue.remove();
            int parentDepth = nodeDepth.get(parentNode);
            
            if(parentNode.equals(endWord))
                break;
            for(String word : wordList){
                if(checkIsNeighbor(word, parentNode)){
                    if(nodeDepth.get(word) == -1){//node that has not been visited
                        nodeDepth.put(word, parentDepth+1);
                        reverseOrderTree.get(word).add(parentNode);
                        exploredQueue.add(word);
                    }
                    else if(nodeDepth.get(word) == parentDepth+1)
                        reverseOrderTree.get(word).add(parentNode);
                }
            }
        }
        //System.out.println(nodeDepth.get(endWord));
        return reverseOrderTree;
    }
    
    public boolean checkIsNeighbor(String word1, String word2){
        int diffCharNum = 0;
        if(word1.length() == word2.length()){
            for(int i = 0; i < word1.length(); i++){
                if(word1.charAt(i) != word2.charAt(i))
                    diffCharNum += 1;
            }
        }
        if(diffCharNum == 1)
            return true;
        return false;
    }
    
    public boolean checkEndwordExist(String endWord, List<String> wordList){
        for(String word : wordList){
            if(word.equals(endWord))
                return true;
        }
        return false;
    }
}
