class Solution {
    public String longestPalindrome(String s) {
        String partialLongestPalindrome;
        int length = s.length(), maxPalindromeLen = 1;
        if(length < 2)
            return s;
        partialLongestPalindrome = Character.toString(s.charAt(length-1));
        for(int i = length-2; i >= 0; i--){
            String candidate = candidatePalindrome(s, maxPalindromeLen, i);
            if(candidate != null){
                partialLongestPalindrome = candidate;
                maxPalindromeLen = partialLongestPalindrome.length();
            }
        }
        return partialLongestPalindrome;
    }
    
    public String candidatePalindrome(String s, int maxLength, int startIndex){
        String candidate;
        for(int i = 2; i > 0; i--)
            if(startIndex+maxLength+i <= s.length()){
                candidate = s.substring(startIndex, startIndex+maxLength+i);
                if(isPalindrome(candidate))
                    return candidate;
            }
        return null;
    }
    
    public boolean isPalindrome(String s){
        int length = s.length();
        for(int i = 0; i < length/2; i++)
            if(s.charAt(i) != s.charAt(length-i-1))
                return false;
        return true;
    }
}
