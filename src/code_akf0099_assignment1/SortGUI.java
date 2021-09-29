// Anna Fortenberry
// CSCE4110
// Assignment 1: Sorting Algorithms
// This program includes algorithms and utility functions for sorting a randomly ordered
// array of lines, which vary in length, into ascending order.

package code_akf0099_assignment1;

// importing the libraries that will be needed in this program

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

// class with buttons and main method

public class SortGUI {

    public static double selectionTime = 0.0;       // execution time of selection sort
    public static double rmergeTime = 0.0;          // execution time of merge sort
    public static double imergeTime = 0.0;          // execution time of iterative merge sort
    public static double bubbleTime = 0.0;          // execution time of bubble sort

    public boolean Selection_Done = false;          // state of selection bubble
    public boolean Recersive_Merge_Done = false;    // state of recursive button
    public boolean Iterative_Merge_Done = false;    // state of iterative button
    public boolean BubbleDone = false;              // state of bubble button

    public static final Color grey = new Color(35, 35, 35);
    public static final Color lightGrey = new Color(61, 61, 61);
    public static final Color lightBlue = new Color(102, 255, 240);
    public static final Color lightRed = new Color(225, 29, 43, 255);

    SortShow sortArea = new SortShow();             // SortShow object

    //Default constructor for SortGUI

    public SortGUI() {

        MyScreen screen = new MyScreen();                                       // MyScreen object
        screen.setTitle("Assignment-1: Anna Fortenberry");                      // Setting title of window
        screen.setSize(975+sortArea.total_number_of_lines, 650);    // Size of window
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                  // Operation when frame closed
        screen.setVisible(true);                                                // True = display frame

    }

    // A public class that extends JFrame

    public class MyScreen extends JFrame {

        JButton scramble_button = new JButton("Scramble Lines");    // "Scramble Lines" button
        JRadioButton selection = new JRadioButton("Selection");     // "Selection" button
        JRadioButton rmerge = new JRadioButton("Merge Recursive");  // "Merge Recursive" button
        JRadioButton imerge = new JRadioButton("Merge Iterative");  // "Merge Iterative" button
        JRadioButton bubble = new JRadioButton("Bubble");           // "Bubble" button
        JRadioButton reset = new JRadioButton("Reset");             // "Reset" button

        JLabel selection_time_label = new JLabel("Selection Time"); // "Selection Time" label
        JLabel selection_time_taken = new JLabel("");               // selection sort execution time

        JLabel rmerge_time_label = new JLabel("Merge-Rec Time");    // "Merge-Rec Time" label
        JLabel rmerge_time_taken = new JLabel("");                  // recursive merge execution time

        JLabel imerge_time_label = new JLabel("Merge-Ita Time");    // "Merge-Ita Time" label
        JLabel imerge_time_taken = new JLabel("");                  // iterative merge execution time

        JLabel bubbleTimeLabel = new JLabel("Bubble Time");         // "Bubble Time" label
        JLabel bubbleTimeTaken = new JLabel("");                    // bubble sort execution time

        // Default constructor for class MyScreen

        public MyScreen() {

            // Setting Foreground and Backgrounds

            sortArea.setBackground(grey);

            selection_time_label.setForeground(Color.white);
            selection_time_taken.setForeground(lightRed);
            selection_time_taken.setBackground(grey);

            rmerge_time_label.setForeground(Color.white);
            rmerge_time_taken.setForeground(lightRed);
            rmerge_time_taken.setBackground(grey);

            imerge_time_label.setForeground(Color.white);
            imerge_time_taken.setForeground(lightRed);
            imerge_time_taken.setBackground(grey);

            bubbleTimeLabel.setForeground(Color.white);
            bubbleTimeTaken.setForeground(lightRed);
            bubbleTimeTaken.setBackground(grey);

            selection.setBackground(grey);
            selection.setForeground(lightBlue);

            rmerge.setBackground(grey);
            rmerge.setForeground(lightBlue);

            imerge.setBackground(grey);
            imerge.setForeground(lightBlue);

            bubble.setBackground(grey);
            bubble.setForeground(lightBlue);

            scramble_button.setBackground(grey);
            scramble_button.setForeground(lightBlue);
            scramble_button.setBorder(BorderFactory.createBevelBorder(0));

            reset.setBackground(grey);
            reset.setForeground(lightBlue);

            // Font of scramble button

            scramble_button.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

            ///////////////////////////////////////////////////////////////////////////////////////////////////////

            /* Radio Sorting Algorithm Buttons */

            JPanel radio_button_selection_Panel = new JPanel(new GridLayout(5, 1, 10, 3));

            // Button Additions
            radio_button_selection_Panel.add(selection);    // selection button to the radio_button_selection_Panel
            radio_button_selection_Panel.add(rmerge);       // recursive merge button to the radio_button_selection_Panel
            radio_button_selection_Panel.add(imerge);       // iterative merge button to the radio_button_selection_Panel
            radio_button_selection_Panel.add(bubble);       // bubble merge button to the radio_button_selection_Panel
            radio_button_selection_Panel.add(reset);        // reset button to the radio_button_selection_Panel

            // Background and Border

            TitledBorder titledBorder = BorderFactory.createTitledBorder("Sort Algorithms");
            radio_button_selection_Panel.setBorder(titledBorder);
            titledBorder.setTitleColor(lightBlue);

            radio_button_selection_Panel.setForeground(Color.white);
            radio_button_selection_Panel.setBackground(grey);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////

            /* Time Panel */

            JPanel time_Panel = new JPanel(new GridLayout(8, 1, 10, 3));

            // Button Additions
            time_Panel.add(selection_time_label);       // selection_time_label to time_panel
            time_Panel.add(selection_time_taken);       // selection_time_taken to the time_Panel

            time_Panel.add(rmerge_time_label);          // rmerge_time_label to the time_Panel
            time_Panel.add(rmerge_time_taken);          // rmerge_time_taken to the time_Panel

            time_Panel.add(imerge_time_label);          // imerge_time_label to the time_Panel
            time_Panel.add(imerge_time_taken);          // imerge_time_taken to the time_Panel

            time_Panel.add(bubbleTimeLabel);            // bubbleTimeLabel to time_panel
            time_Panel.add(bubbleTimeTaken);            // bubbleTimeTaken to time_panel

            // Background and Border

            TitledBorder titleBorder = BorderFactory.createTitledBorder("Time Analysis");
            time_Panel.setBorder(titleBorder);
            titleBorder.setTitleColor(lightBlue);
            time_Panel.setBackground(grey);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////

            /* Compile All Buttons into One Panel */
            // buttons_area_Panel: scrambleButton, radio_button_selection, time_panel

            JPanel buttons_area_Panel = new JPanel(new GridLayout(4, 1, 5, 5));

            // Panel Additions
            buttons_area_Panel.add(scramble_button);                // scramble button
            buttons_area_Panel.add(radio_button_selection_Panel);   // radio buttons
            buttons_area_Panel.add(time_Panel);                     // time panel

            // Background
            buttons_area_Panel.setBackground(grey);

            // Panel Location
            add(buttons_area_Panel, BorderLayout.EAST);
            add(sortArea, BorderLayout.CENTER);

            // Button States to False
            Set_Available_Chooses(false, false, false, false, false);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////

            /* Creating listeners for each GUI element */

            // Action Listener: Scramble Button
            /* This button calls the function to order the lines in an array randomly.
               All four sort methods use this same random set up.
             */
            scramble_button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    sortArea.scramble_the_lines();      // call scramble function
                    scramble_button.setEnabled(false);  // disable button after click

                    // setting all booleans true except for reset

                    Set_Available_Chooses(true, true, true, false, true);
                }
            });

            // Action Listener: Selection Button

            selection.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    sortArea.SelectionSort();                                           // call selection sort
                    Selection_Done = true;                                              // set sort to complete
                    selection_time_taken.setText(selectionTime / 1000 + " Seconds");    // execution time

                    // setting all booleans false except for reset

                    Set_Available_Chooses(false, false, false, true, false);
                }
            });

            // Action Listener: Recursive Merge Button

            rmerge.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Sorting the array in the recursive merge sort method
                    sortArea.R_MergeSort();                                         // call merge sort
                    rmerge_time_taken.setText((rmergeTime / 1000) + " Seconds");    // execution time
                    Recersive_Merge_Done = true;                                    // set sort to complete

                    // setting all booleans false except for reset

                    Set_Available_Chooses(false, false, false, true, false);
                }
            });

            // Action Listener: Iterative Merge Button

            imerge.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    sortArea.I_MergeSort();                                         // call Iterative Merge Sort
                    imerge_time_taken.setText((imergeTime / 1000) + " Seconds");    // execution time
                    Iterative_Merge_Done = true;                                    // set click state to true

                    //setting all booleans false except for reset

                    Set_Available_Chooses(false, false, false, true, false);
                }
            });

            // Action Listener: Bubble Button

            bubble.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    sortArea.BubbleSort();                                         // call BubbleSort
                    bubbleTimeTaken.setText((bubbleTime / 1000) + " Seconds");     // BubbleSort execution time
                    BubbleDone = true;                                             // set click state to true

                    //setting all booleans false except for reset

                    Set_Available_Chooses(false, false, false, true, false);
                }
            });

            // Action Listener: Reset Button

            reset.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    reset.setEnabled(false);                    // disable reset after click
                    sortArea.reset();                           // reset lines_lengths to scrambled

                    // All combinations of button selections

                    // 1. 4/4
                    if (Selection_Done && Recersive_Merge_Done && Iterative_Merge_Done && BubbleDone) {

                        scramble_button.setEnabled(true);
                        Recersive_Merge_Done = false;
                        Iterative_Merge_Done = false;
                        Selection_Done = false;
                        BubbleDone = false;

                        Set_Available_Chooses(false, false, false, false, false);
                        selection_time_taken.setText("");
                        rmerge_time_taken.setText("");
                        imerge_time_taken.setText("");
                        bubbleTimeTaken.setText("");

                    // 2. 3/4
                    } else if (Recersive_Merge_Done && Iterative_Merge_Done && Selection_Done) {
                        Set_Available_Chooses(false, false, false, false, true);

                    } else if (Recersive_Merge_Done && Iterative_Merge_Done && BubbleDone) {
                        Set_Available_Chooses(true, false, false, false, false);

                    } else if (Recersive_Merge_Done && Selection_Done && BubbleDone) {
                        Set_Available_Chooses(false, false, true, false, false);

                    } else if (Selection_Done && BubbleDone && Iterative_Merge_Done) {
                        Set_Available_Chooses(false, true, false, false, false);

                    // 3. 2/4
                    } else if (Recersive_Merge_Done && Iterative_Merge_Done) {
                        Set_Available_Chooses(true, false, false, false, true);

                    } else if (Selection_Done && Recersive_Merge_Done) {
                        Set_Available_Chooses(false, false, true, false, true);

                    } else if (BubbleDone && Recersive_Merge_Done) {
                        Set_Available_Chooses(true, false, true, false, false);

                    } else if (Selection_Done && Iterative_Merge_Done) {
                        Set_Available_Chooses(false, true, false, false, true);

                    } else if (BubbleDone && Iterative_Merge_Done) {
                        Set_Available_Chooses(true, true, false, false, false);

                    } else if (BubbleDone && Selection_Done) {
                        Set_Available_Chooses(false, true, true, false, false);

                    // 4. 1/4
                    } else if (Selection_Done) {
                        Set_Available_Chooses(false, true, true, false, true);

                    } else if (Recersive_Merge_Done) {
                        Set_Available_Chooses(true, false, true, false, true);

                    } else if (Iterative_Merge_Done) {
                        Set_Available_Chooses(true, true, false, false, true);

                    } else {
                        Set_Available_Chooses(true, true, true, false, false);
                    }
                }
            });
        }

        // Func: Set State
        /*   Desc: this function sets whether a button is enabled or disabled
         */
        public void Set_Available_Chooses(boolean selection_state, boolean rmerge_state, boolean imerge_state,
                                          boolean reset_state, boolean bubbleState) {

            this.selection.setEnabled(selection_state);
            this.rmerge.setEnabled(rmerge_state);
            this.imerge.setEnabled(imerge_state);
            this.reset.setEnabled(reset_state);
            this.bubble.setEnabled(bubbleState);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /* Main Method */

    public static void main(String[] args) {
        SortGUI sort_GUI = new SortGUI();       // initialize class
    }

}


