//{ Driver Code Starts
//Initial Template for Java
package medium;

import java.lang.*;

class ElemNBy2
{
    public static void main(String args[])
    {
       int[] arr = {};
        System.out.println(majorityElement(arr, arr.length));
    }
// } Driver Code Ends


//User function Template for Java
    static int majorityElement(int a[], int size)
    {
        // your code here
        int result= -1;
        int count = 0;

        for(int i=0; i<a.length; i++) {
            if(count == 0) {
                count = 1;
                result = a[i];
            } else if(result == a[i]){
                count++;
            } else {
                count --;
            }
        }

        count = 0;
        for(int i=0; i < a.length; i++){
            if(result == a[i]) count++;
            if (count > a.length/2) {
                break;
            }
        }

        if(count > a.length/2){
            return result;
        }

        return -1;
    }
}
