// Anna Fortenberry
// CSCE4110
// Assignment 1: Sorting Algorithms
// This program includes algorithms and utility functions for sorting a randomly ordered
// array of lines, which vary in length, into ascending order.

package code_akf0099_assignment1;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

// Class: SortShow
/* This class includes the following sorting algorithms:
 * Selection sort, Iterative-Merge Sort, Recursive-Merge Sort, and Bubble Sort.
 * Utility functions for these sorts are defined here as well.
 */

public class SortShow extends JPanel {

    public final int total_number_of_lines = 256;       // number of lines to sort
    public final int largestIndex = 255;                // last index of array
    public int[] lines_lengths;                         // array of lines_lengths to be sorted
    public int[] scramble_lines;                        // array for scrambled lines_lengths

    // Custom Colors

    public static final Color indigo = new Color(117, 102, 255);
    public static final Color green = new Color(85, 213, 91);
    public static final Color lightRed = new Color(239, 99, 110, 255);
    public static final Color lightBlue = new Color(102, 255, 240);
    //public static final Color orange = new Color(234, 179, 68);
    public static final Color yellow = new Color(253, 225, 78, 255);
    public static final Color aqua = new Color(102, 255, 156);
    public static final Color purple = new Color(133, 71, 245);
    public static final Color pink = new Color(246, 147, 200, 255);

    /* Default Constructor */

    public SortShow() {

        lines_lengths = new int[total_number_of_lines]; // lines_lengths = 256;
        for (int i = 0; i < total_number_of_lines; i++)  // length of lines is 5, 6, 7, 8...
            lines_lengths[i] =  i+5;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /* Setup and Reset */

    // Func: Scramble Lines
    /* Desc: This function randomly orders the previously generated array of lines.
             It stores a copy into another array to retain the scrambled array for
             all sorting algorithms to sort. This ensures measured execution times
             of algorithms are compared fairly.
     */
    public void scramble_the_lines(){

        Random num = new Random();                         // generate random number

        // randomly switch lines

        for (int i = 0; i < total_number_of_lines; i++){
            int j = num.nextInt(i + 1);             // get random number with nextInt (num 0:i+1)
            swap(i, j);                                    // swap elements
        }

        scramble_lines = new int[total_number_of_lines];   // scrambled array = size of num lines

        // copy scrambled lines into array to store for later use
        // want all algorithms to sort identical unsorted arrays for fair eval of run time


        System.arraycopy(lines_lengths, 0, scramble_lines, 0, total_number_of_lines);

        /*
        for (int i = 0; i < total_number_of_lines; i++)
        {
            scramble_lines[i] = lines_lengths[i];
        }*/

        paintComponent(this.getGraphics());                 // draw scrambled lines_lengths
    }

    //This method resets the window to the scrambled lines display
    public void reset(){
        if(scramble_lines != null)
        {
            //copying the old scrambled lines into lines_lengths
            for (int i = 0; i < total_number_of_lines; i++)
            {
                lines_lengths[i] = scramble_lines[i] ;
            }
            //Drawing the now scrambled lines_lengths
            paintComponent(this.getGraphics());
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /* Utility Functions */

    // Func: Swap Values
    /* Desc: This function accepts two indexes and swaps the values stored in them
             in the lines_lengths array.
     */
    public void swap(int i, int j) {

        int temp = lines_lengths[i];            // temporarily store value in index i
        lines_lengths[i] = lines_lengths[j];    // replace value at index i with value in index j
        lines_lengths[j] = temp;                // replace value in j with value that was in i
    }

    // Func: Find Index of Shortest Line
    /* Desc: This function iterates through a given range of the array lines_lengths and
             returns the index of the shortest line.
     */
    public int getIndexOfSmallest(int firstIndex, int lastIndex) {

        int indexOfShortest = firstIndex;   // function assumes shortest line is located at first index to start

        for (int i = firstIndex; i < lastIndex; i++) {

            /* If the length of the line stored at indexOfShortest is greater
               than the length of the line stored at index i+1, then
               indexOfShortest updates to i+1 */

            if (lines_lengths[indexOfShortest] > lines_lengths[i+1] ) {
                indexOfShortest = i+1;
            }
        }

        return indexOfShortest;
    }

    // Func: Merge
    /* Desc: This function is utilized by both the iterative and recursive merge sorting algorithms.
             It merges two array halves into one array until the lines_lengths array is restored to
             its full length. Pattern is like a binary tree converging to root.

     */
    public void Merge(int front, int mid, int back) {

        int leftSize = mid - front + 1;             // sizes of halves
        int rightSize = back - mid;

        int left[] = new int[leftSize];             // temp arrays
        int right[] = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {        // copy left data
            left[i] = lines_lengths[front + i];
        }

        for (int i = 0; i < rightSize; i++) {       // copy right data
            right[i] = lines_lengths[mid + 1 + i];
        }

        int l = 0;                                  // initial indexes
        int r = 0;
        int k = front;                              // iterator set to first index of given array

        /* Converge data of two arrays in a manner that places values in ascending order.
           Repeat until data from both arrays has been fully copied.
         */

        while (l < leftSize && r < rightSize) {
            if (left[l] <= right[r]) {
                lines_lengths[k] = left[l];
                l++;
            }
            else {
                lines_lengths[k] = right[r];
                r++;
            }
            k++;
        }

        /* Account for any remaining data elements (sometimes two arrays differ in length
           by one element
        */

        while (l < leftSize) {
            lines_lengths[k] = left[l];
            l++;
            k++;
        }

        while (r < rightSize) {
            lines_lengths[k] = right[r];
            r++;
            k++;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /* Sorting Algorithms */

    // Func: Selection Sort
    /* Desc: This algorithm uses the utility functions getIndexOfSmallest() and swap() to sort the array
             of lines via Selection Sort in ascending order. For the length of the array minus one, the
             function will locate the smallest value within the unsorted array and swap it with the first
             unsorted element.
    */
    public void SelectionSort() {

        Calendar start = Calendar.getInstance();    // date and time record for start of function
        int shortest;

        // Selection Sort Algorithm

        for (int i = 0; i < largestIndex; i++) {
            shortest = getIndexOfSmallest(i, (largestIndex));   // find index of shortest line in unsorted array
            swap(i, shortest);                                  // swap shortest with first index of unsorted array
            paintComponent(this.getGraphics());                 // repaint new order
            delay(15);
        }

        Calendar end = Calendar.getInstance();      // date and time record for end of function

        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();  // execution time
    }

    // Func: Recursive Merge Sort
    /* Desc: This function serves to track the execution time of the recursive-merge sort.
    */
    public void R_MergeSort() {

        Calendar start = Calendar.getInstance();     // date and time record for start of function

        RecursiveSort(0, (largestIndex));

        Calendar end = Calendar.getInstance();       // date and time record for end of function
        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();   // execution time
    }

    // Func: Recursive Sort
    /* Desc: This function recursively divides an array and its subcomponents by two until all elements
             of the array are stored in arrays of length one. It calls the utility merge function to
             converge the elements into ascending order.
     */
    public void RecursiveSort(int front, int back) {

        /* If the first index is less than the last index, the arrays can be
           divided further. After length one is achieved, midpoint will calculate
           to 0.5, which is 0 in computer integer arithmetic. At this point,
           front (0) will no be < back (0).
         */

        if(front < back) {

            int mid = front + (back - front)/2;     // find midpoint

            RecursiveSort(front, mid);              // recursively divide halves
            RecursiveSort((mid + 1), back);

            Merge(front, mid, back);                // merge sorted halves

            paintComponent(this.getGraphics());     // display
            delay(15);
        }
    }

    // Func: Iterative Merge Sort
    /* Desc: This function divides and calls the merge function within a for loop,
             rather than calling itself recursively.
     */
    public void I_MergeSort()
    {
        Calendar start = Calendar.getInstance();    // date and time record for start of function

        int size;   // iterator
        int front;  // first index

        /* Converging data with the merge function is like converging a binary tree to its root.
           This means the size will divide in half by size each time. Thus, the size of the array
           as it merges is size = 2 * size, until it reaches original size.
         */

        for (size = 1; size <= largestIndex; size = 2 * size) {

            /* Merge halves in correct order by computing the mid and last indexes of an array.
               Call merge function and redisplay the new state.
             */

            for (front = 0; front < largestIndex; front += 2 * size) {
                int mid = Math.min(front + size - 1, largestIndex);
                int back = Math.min(front + 2 * size - 1, largestIndex);

                Merge(front, mid, back);

                paintComponent(this.getGraphics());     // display
                delay(15);
            }
        }
        Calendar end = Calendar.getInstance();      // date and time record for end of function
        SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();   // execution time
    }

    // Func: Bubble Sort
    /* Desc: This function simply swaps a value with the value next to it, if it is larger.
             The swap repeats until it is less than the value stored to its right, thus
             ordering the values into ascending order.
     */
    public void BubbleSort(){

        Calendar start = Calendar.getInstance();     // date and time record for start of function

        /* Each loop, the tallest line is moved to the back. A j element is evaluated with the one
           to the right of it, and if it is larger, they swap. If it is not larger, then j+1 becomes the
           new element to compare with elements to its right. This repeats for each index of the array.
         */

        for (int i = 0; i <= largestIndex; i++) {

            for (int j = 0; j < largestIndex; j++) {
                if (lines_lengths[j] > lines_lengths[j + 1]) {
                    swap((j + 1), j);   // lines_lengths[j+1] and lines_lengths[j]
                }
            }
            paintComponent(this.getGraphics()); // display new state
            delay(15);
        }

        Calendar end = Calendar.getInstance();       // date and time record for end of function
        SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();   // execution time
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /* Display Functions */

    // Func: Paint Component
    /* Desc: This function uses a pattern of 8 colors to draw lines as a visual representation of the
             data stored in lines_lengths.
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        for(int i = 0; i < total_number_of_lines; i++) {

            if(i % 8 == 0){
                g.setColor(lightRed);
            } else if(i % 8 == 1){
                g.setColor(indigo);
            } else if(i % 8 == 2){
                g.setColor(aqua);
            } else if(i%8 == 3){
                g.setColor(yellow);
            } else if(i%8 == 4){
                g.setColor(lightBlue);
            } else if(i%8 == 5){
                g.setColor(pink);
            } else if(i%8 == 6){
                g.setColor(purple);
            } else
                g.setColor(green);

            // Drawing the lines using the x and y-components

            g.drawLine(4*i + 25, 300, 4*i + 25, 300 - lines_lengths[i]);
        }
    }

    // Func: Delay
    /* Desc: This function pauses the execution for the milliseconds time given as a parameter
     */
    public void delay(int time) {

        try{
            Thread.sleep(time);
        }catch(InterruptedException ie){
            Thread.currentThread().interrupt();
        }
    }

}

