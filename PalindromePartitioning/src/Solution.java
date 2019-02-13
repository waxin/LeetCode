import java.util.LinkedList;
class Solution {
    List<List<String>> partitions = new LinkedList<List<String>>();
    
    public List<List<String>> partition(String s) {
        List<String> onePartition = new ArrayList<String>(); 
        findPalindrome(s, onePartition);
        return partitions;
    }
    
    public void findPalindrome(String s, List<String> onePartition){
        String substring;
        int length = s.length();
        if(length == 0)
            partitions.add(new ArrayList<String>(onePartition));
        
        else{
            for(int i = 0; i < length; i++){
                substring = s.substring(0, i+1);
                //System.out.println(substring);
                if(isPalindrome(substring)){
                    onePartition.add(substring);
                    findPalindrome(s.substring(i+1, length), onePartition);
                    onePartition.remove(onePartition.size()-1);
                }
            }
        }
    }
    
    public boolean isPalindrome(String s){
        int length = s.length();
        int halfLength = length/2;
        //System.out.println(length);
        for(int i = 0; i < halfLength; i++)
            if(s.charAt(i) != s.charAt(length-i-1))
                return false;
        return true;
    }
}