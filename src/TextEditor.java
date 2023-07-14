import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //declaring components of text editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    //file menu items
    JMenuItem newFile, openFile, saveFile, close;
    //edit menu items
    JMenuItem cut, copy,paste, selectAll;
    JTextArea textArea;
    TextEditor(){
        //initialize a frame
        frame = new JFrame();

        //initialize menubar
        menuBar = new JMenuBar();

        //initialize textarea
        textArea = new JTextArea();

        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialize file Menu items
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        close = new JMenuItem("Close");

        //add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        close.addActionListener(this);

        //add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(close);

        //initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");

        //add action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        //add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        //add menus to menubar;
        menuBar.add(file);
        menuBar.add(edit);

        // set Menubar to frame
        frame.setJMenuBar(menuBar);

        //Create COntent pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0,0));
        // add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scrollpane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);

        //set diemensions of frame
        frame.setBounds(400, 200,600, 500);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }

        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }

        if(actionEvent.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }

        if(actionEvent.getSource()==selectAll){
            //perform select all operation
            textArea.selectAll();
        }

        if(actionEvent.getSource()==close){
            //perform close operation
            System.exit(0);
        }

        if(actionEvent.getSource()==newFile){
            //perform cut operation
            TextEditor textEditor = new TextEditor();
        }

        if(actionEvent.getSource()==openFile){
            //perform open file operation
            // open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we clicked in open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialized file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialized Buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate +"\n";
                    }
                    //Set the output string to text area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }

        }

        if(actionEvent.getSource()==saveFile){
            //perform cut operation
            //Initialize file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //Get choose option from file Chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clkicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

    }
}