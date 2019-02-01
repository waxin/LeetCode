class Solution {
    public List<List<String>> results = new ArrayList<List<String>>();
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordList = deleteBeginWord(beginWord, wordList);
        boolean endWordIn = checkEndwordExist(endWord, wordList);
        Map<String, List<String>> allWordNeighbors = findAllNeighbors(beginWord, wordList);
        Map<String, Integer> visited = new HashMap<String, Integer>();
        List<List<String>> resultLast = new ArrayList<List<String>>();
        int lenMin = 2*wordList.size();
        int length = 2*endWord.length()+1;
        //System.out.println("endWord exist: " + endWordIn);
        if(!endWordIn)
            return results;
        
        
        for(String word : wordList)
            visited.put(word, 0);
        visited.put(beginWord, 0);
        List<String> result = new ArrayList<String>();
        while(results.size() == 0){
            findOneLadder(beginWord, endWord, allWordNeighbors, visited, result, 11);
            length *= 2;
        }
        
        for(List<String> res : results)
            if(res.size() < lenMin)
                lenMin = res.size();
        for(List<String> res : results)
            if(res.size() == lenMin) 
                resultLast.add(res);
        
        return resultLast;
        
    }
    
    public void findOneLadder(String beginWord, String endWord, Map<String, List<String>> allWordNeighbors, Map<String, Integer> visited, List<String> result, int length){
        List<String> neighbors = allWordNeighbors.get(beginWord);
        //System.out.println("neigbor:"+neighbors);
        //System.out.println(result);
        if(length > 0){
            if(beginWord.equals(endWord)){
                result.add(beginWord);
                results.add(new ArrayList(result));
                /*            
                System.out.println("***********************");
                System.out.println(result);
                System.out.println("beginWord:" + beginWord);
                System.out.println("***********************");
                */          
                result.remove(beginWord);
            }
            else{
                for(String neighbor : neighbors){
                    if(visited.get(neighbor) == 0){
                        result.add(beginWord);
                        visited.replace(beginWord, 1);
                        findOneLadder(neighbor, endWord, allWordNeighbors, visited, result, length-1);
                        result.remove(beginWord);
                        visited.replace(beginWord, 0);
                    }
                }
            }
        }
        
    }
    
    public boolean checkEndwordExist(String endWord, List<String> wordList){
        for(String word : wordList){
            if(word.equals(endWord))
                return true;
        }
        return false;
    }
    
    public Map<String, List<String>> findAllNeighbors(String beginWord, List<String> wordList){
        Map<String, List<String>> allWordNeighbors = new HashMap<String, List<String>>();
        for(String word : wordList){
            List<String> newValue = new ArrayList<String>();
            allWordNeighbors.put(word, newValue);
        }
        for(int i = 0; i < wordList.size()-1; i++){
            String word = wordList.get(i);
            for(int j = i+1; j < wordList.size(); j++){
                String candidate = wordList.get(j);
                if(checkIsNeighbor(word, candidate)){
                    allWordNeighbors.get(word).add(candidate);
                    allWordNeighbors.get(candidate).add(word);
                }
            }
        }
        
        List<String> neighbors = new ArrayList<String>();
        for(String word : wordList)
            if(checkIsNeighbor(word, beginWord))
                neighbors.add(word);
        allWordNeighbors.put(beginWord, neighbors);
        
        return allWordNeighbors;
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
    
    public List<String> deleteBeginWord(String beginWord, List<String> wordList){
        for(String word : wordList)
            if(word.equals(beginWord)){
                wordList.remove(word);
                break;  
            }
        return wordList;
    }
}