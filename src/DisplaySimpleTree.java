import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class DisplaySimpleTree extends JFrame {
    JScrollPane scrollpane;
    DisplayPanel panel;


    public DisplaySimpleTree(MyTree t) {
        panel = new DisplayPanel(t);
        panel.setPreferredSize(new Dimension(300, 300));
        scrollpane = new JScrollPane(panel);
        getContentPane().add(scrollpane, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();  // cleans up the window panel
    }

    public static void main(String[] args) {

        MyTree t = new MyTree(); // t is Binary tree we are displaying
        BufferedReader diskInput;
        String word;
        File filePath = getFileInput();
        try { //reads in words from a file
            diskInput = new BufferedReader(new InputStreamReader(
                    new FileInputStream(
                            filePath)));// file name is on command line
            Scanner input = new Scanner(diskInput);
            while (input.hasNext()) {
                word = input.next();
                word = word.toLowerCase(); // use lower case only
                t.root = t.insert(t.root, word);  //insert word into Binary Search Tree
                t.inputString = t.inputString + " " + word; // add word to input string
            }
        } catch (IOException e) {
            System.out.println("io exception");
        }

        t.computeNodePositions(); //finds x,y positions of the tree nodes
        t.maxheight = t.treeHeight(t.root); //finds tree height for scaling y axis
        DisplaySimpleTree dt = new DisplaySimpleTree(t);//get a display panel
        dt.setVisible(true); //show the display
    }
    private static File getFileInput() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File
                (System.getProperty("user.home") + System.getProperty("file.separator")+ "Downloads"));
        chooser.setDialogTitle("Select txt input file");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());

            File file = chooser.getSelectedFile();

            if (!file.exists()) {
                System.out.println("Folder does not exist");
                return null;
            } else {
                System.out.println("Folder does exist");
            }

            return chooser.getSelectedFile();
        }
        else {
            System.out.println("No Selection ");
            return null;
        }
    }
}
