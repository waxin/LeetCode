class Solution {
    public String longestPalindrome(String s) {
        Map<Integer, String> partialLongestPalindrome = new HashMap<Integer, String>();
        int length = s.length();
        if(length == 0)
            return "";
        String value = Character.toString(s.charAt(length-1));
        partialLongestPalindrome.put(length-1, value);
        for(int i = length-2; i >= 0; i--){
            int key = i;
            String valueCandidate1 = maxPalindromeLength(s.substring(key, length));
            String valueCandidate2 = partialLongestPalindrome.get(key+1);
            if(valueCandidate1.length() > valueCandidate2.length())
                partialLongestPalindrome.put(key, valueCandidate1);
            else
                partialLongestPalindrome.put(key, valueCandidate2);
        }
        return partialLongestPalindrome.get(0);
    }
    
    public String maxPalindromeLength(String s){
        int length = s.length();
        for(int i = 0; i < length; i++){
            String subString = s.substring(0, length-i);
            if(isPalindrome(subString))
                return subString;
        }
        return "";
    }
    
    public boolean isPalindrome(String s){
        int length = s.length();
        for(int i = 0; i < length/2; i++)
            if(s.charAt(i) != s.charAt(length-i-1))
                return false;
        return true;
    }
}
