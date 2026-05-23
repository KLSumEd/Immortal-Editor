package com.immortal.app.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * The main class for the Text Editor. Extends the {@link javax.swing.JFrame
 * JFrame} class.
 * 
 * @author {@link https://github.com/KLSumEd KLSumEd}
 * @see javax.swing.JFrame
 */
public final class TextEditor extends JFrame
{
    private final JFrame frame = new JFrame(); // Main Editor Frame
    
    // Width of the main editor frame (px)
    private final int FRAME_WIDTH = 640;
    // Height of the main editor frame (px)
    private final int FRAME_HEIGHT = 480;
    // Main editor frame text area
    private final JEditorPane main_editor_pane = new JEditorPane();
    // Main editor menu-bar
    private final JMenuBar main_menu_bar = new JMenuBar();
    
    // Main menu-bar 'file' submenu
    private final JMenu file_submenu = new JMenu("File");
    
    // 'New' item in 'File' sumbmenu
    private final JMenuItem file_menuitem_new = new JMenuItem("New");
    // 'Open' item in 'File' sumbmenu
    private final JMenuItem file_menuitem_open = new JMenuItem("Open");
    // 'Save' item in 'File' sumbmenu
    private final JMenuItem file_menuitem_save = new JMenuItem("Save");
    // 'Save As' item in 'File' sumbmenu
    private final JMenuItem file_menuitem_saveas = new JMenuItem("Save As...");
    // 'Quit' item in 'File' sumbmenu
    private final JMenuItem file_menuitem_quit = new JMenuItem("Quit");
    
    private final JFileChooser jfc = new JFileChooser(
            FileSystemView.getFileSystemView().getDefaultDirectory());
    private String filename = "untitled";
    private String absolute_filepath;
    private boolean modified = false;
    private EditorDocumentListener dl;
    
    /**
     * Creates a new instance of the TextEditor.
     * 
     * @throws ClassNotFoundException          if the Editor cannot find the
     *                                         {@link javax.swing.UIManager
     *                                         UIManager}
     * @throws InstantiationException          if the TextEditor cannot be
     *                                         instantiated
     * @throws IllegalAccessException          if the editor does not have
     *                                         permissions to access the Look
     *                                         And Feel
     * @throws UnsupportedLookAndFeelException if the System Look And Feel is
     *                                         unsupported by the
     *                                         {@link javax.swing.UIManager
     *                                         UIManager}
     */
    public TextEditor() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException
    {
        
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        
        // Set attributes of the app window
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(main_editor_pane);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        
        main_menu_bar.add(file_submenu);
        
        file_submenu.setMnemonic(KeyEvent.VK_F);
        file_submenu.add(file_menuitem_new);
        file_submenu.add(file_menuitem_open);
        file_submenu.add(file_menuitem_save);
        file_submenu.add(file_menuitem_saveas);
        file_submenu.add(file_menuitem_quit);
        
        file_menuitem_new.setMnemonic(KeyEvent.VK_N);
        file_menuitem_open.setMnemonic(KeyEvent.VK_O);
        file_menuitem_save.setMnemonic(KeyEvent.VK_S);
        file_menuitem_saveas.setMnemonic(KeyEvent.VK_A);
        file_menuitem_quit.setMnemonic(KeyEvent.VK_Q);
        
        file_menuitem_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.CTRL_DOWN_MASK));
        file_menuitem_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_DOWN_MASK));
        file_menuitem_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK));
        file_menuitem_saveas.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        file_menuitem_quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
                InputEvent.ALT_DOWN_MASK));
        
        frame.setJMenuBar(main_menu_bar);
        jfc.setFileFilter(new FileNameExtensionFilter(
                "Plain Text & Immortal Code Files", "txt", "imc"));
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        run();
    }
    
    private void run()
    {
        final MenuBarActionListener mbl = new MenuBarActionListener();
        file_menuitem_new.addActionListener(mbl);
        file_menuitem_open.addActionListener(mbl);
        file_menuitem_save.addActionListener(mbl);
        file_menuitem_saveas.addActionListener(mbl);
        file_menuitem_quit.addActionListener(mbl);
        
        this.dl = new EditorDocumentListener();
        main_editor_pane.getDocument().addDocumentListener(this.dl);
        setFrameTitle();
        frame.setVisible(true);
    }
    
    private void setFrameTitle()
    {
        final String APP_TITLE = " - Immortal Code Editor";
        String title = this.filename + APP_TITLE;
        title = this.modified ? "*" + title : title;
        frame.setTitle(title);
    }
    
    private void setDocumentModified()
    {
        this.modified = true;
        setFrameTitle();
        main_editor_pane.getDocument().removeDocumentListener(this.dl);
    }
    
    private void setDocumentUnmodified()
    {
        this.modified = false;
        setFrameTitle();
        main_editor_pane.getDocument().addDocumentListener(this.dl);
    }
    
    /**
     * Checks if the current document has been modified.
     * 
     * @return <code>true</code> if the document has been modified
     */
    public boolean isModified()
    { return this.modified; }
    
    private void refactorWindow(
            final String text, final String absoluteFilePath,
            final String fileName
    )
    {
        
        if (text != null)
        { main_editor_pane.setText(text); }
        this.absolute_filepath = absoluteFilePath;
        this.filename = fileName;
        setDocumentUnmodified();
    }
    
    /**
     * Get the name of the currently open file
     * 
     * @return a <code>String</code> containing the name of the current file
     */
    public String getFilename()
    { return this.filename; }
    
    /**
     * Get the absolute filepath of the currently opened file
     * 
     * @return a <code>String</code> containing the absolute filepath of the
     *         current file
     */
    public String getAbsoluteFilepath()
    { return this.absolute_filepath; }
    
    ///// NESTED CLASSES /////
    
    /**
     * A {@link javax.swing.event.DocumentListener DocumentListener} Adapter for
     * the mainEditorPane
     * 
     * @see javax.swing.event.DocumentListener
     */
    private class EditorDocumentListener implements DocumentListener
    {
        @Override public void insertUpdate(final DocumentEvent e)
        { setDocumentModified(); }
        
        @Override public void removeUpdate(final DocumentEvent e)
        { setDocumentModified(); }
        
        @Override public void changedUpdate(final DocumentEvent e)
        {}
    }
    
    /**
     * An {@link java.awt.event.ActionListener ActionListener} Adapter for the
     * mainMenuBar
     * 
     * @see java.awt.event.ActionListener
     */
    private class MenuBarActionListener implements ActionListener
    {
        @Override public void actionPerformed(final ActionEvent e)
        {
            
            // Switch case to call appropriate event handler method
            switch (e.getActionCommand())
            {
                case "Open" ->
                    { onActionOpen(); }
                case "Save As..." ->
                    { onActionSave(true); }
                case "Save" ->
                    { onActionSave(false); }
                case "New" ->
                    { onActionNew(); }
                case "Quit" ->
                    { System.exit(0); }
                default ->
                    {}
            }
        }
        
        // Runs on Selecting main_menu_bar -> File -> Open
        private void onActionOpen()
        {
            
            // Prevents open if unsaved changes
            if (isModified()
                    && warnUnsavedChanges() == JOptionPane.CANCEL_OPTION)
            { return; }
            
            // Create & Show JFileChooser Open Dialog
            jfc.setDialogTitle("Choose file to open...");
            final int returnValue = jfc.showOpenDialog(file_menuitem_open);
            
            // If user chose File to Open...
            if (returnValue == JFileChooser.APPROVE_OPTION)
            {
                // Open Selected File
                final String new_absolute_filepath = jfc.getSelectedFile()
                        .getAbsolutePath(); // Get filepath of selected file
                final File f = new File(new_absolute_filepath);
                
                // Read Selected File to Editor Pane
                String ingest = ""; // Empty String to take file data
                
                try (FileReader read = new FileReader(f);)
                {
                    
                    // Loop through file reader with scanner
                    try (Scanner scan = new Scanner(read))
                    {
                        
                        while (scan.hasNextLine())
                        {
                            final String line = scan.nextLine() + "\n";
                            ingest = ingest + line;
                        }
                    }
                }
                catch (final FileNotFoundException ex)
                {
                    showErrorFileNotFound(file_menuitem_open);
                    return;
                }
                catch (final IOException ex)
                {
                    showErrorIO(file_menuitem_open);
                    return;
                }
                
                // Finalisation
                refactorWindow(ingest, new_absolute_filepath, f.getName());
            }
        }
        
        // Runs on Selecting main_menu_bar -> File -> Save / Save As...
        private void onActionSave(final boolean save_as)
        {
            
            if (!isModified() && !save_as)
            { return; }
            // Return if user didn't choose to save and doesn't need to
            
            try
            {
                File f;
                String new_absolute_filepath = getAbsoluteFilepath();
                
                // Initialises f through user input if necessary
                if (new_absolute_filepath == null || save_as)
                {
                    // Create JFileChooser Save Dialog
                    jfc.setDialogTitle("Choose file save...");
                    
                    // Show Dialog and receive input
                    final int return_value = jfc
                            .showSaveDialog(file_menuitem_save);
                    
                    if (return_value != JFileChooser.APPROVE_OPTION)
                    { return; } // Return if the user doesn't wish to save
                    
                    new_absolute_filepath = jfc.getSelectedFile()
                            .getAbsolutePath();
                }
                
                f = new File(new_absolute_filepath);
                
                // Create FileWriter and write file
                try (FileWriter out = new FileWriter(f))
                {
                    out.write(main_editor_pane.getText());
                    refactorWindow(null, new_absolute_filepath, f.getName());
                }
                catch (final FileNotFoundException ex)
                {
                    showErrorFileNotFound(file_menuitem_save);
                }
                catch (final IOException ex)
                {
                    showErrorIO(file_menuitem_save);
                }
                
            }
            catch (final NullPointerException ex)
            {
                showErrorNullPointer(file_menuitem_save);
            }
        }
        
        // Runs on Selecting main_menu_bar -> File -> New
        private void onActionNew()
        {
            
            // If file has been modified -> warn about unsaved changes
            // -> If user cancels warning -> return
            if (isModified()
                    && warnUnsavedChanges() == JOptionPane.CANCEL_OPTION)
            { return; }
            
            // Create New File
            refactorWindow("", null, "untitled");
        }
        
        ///// JDIALOGS /////
        
        private int warnUnsavedChanges()
        {
            final String MESSAGE = """
                                   You have unsaved changes!
                                   Would you like to save before continuing?
                                   (Any unsaved changes will be lost)""";
            final String TITLE = "Unsaved Changes";
            final String[] OPTIONS = { "Save", "Don't Save", "Cancel" };
            
            final int result = JOptionPane.showOptionDialog(main_menu_bar,
                    MESSAGE, TITLE, JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, OPTIONS, OPTIONS[0]);
            
            switch (result)
            {
                case JOptionPane.YES_OPTION ->
                    { onActionSave(false); }
                case JOptionPane.NO_OPTION ->
                    {}
                case JOptionPane.CANCEL_OPTION ->
                    {}
                case JOptionPane.CLOSED_OPTION ->
                    {}
                default -> throw new AssertionError("Invalid Result");
            }
            return result;
        }
        
        private void showErrorFileNotFound(final Component root)
        {
            final String MESSAGE = "Error! File not found.";
            final String TITLE = "FileNotFoundException";
            JOptionPane.showMessageDialog(root, MESSAGE, TITLE,
                    JOptionPane.ERROR_MESSAGE);
        }
        
        private void showErrorIO(final Component root)
        {
            final String MESSAGE = "Error! An unknown IOException has occurred.";
            final String TITLE = "IOException";
            JOptionPane.showMessageDialog(root, MESSAGE, TITLE,
                    JOptionPane.ERROR_MESSAGE);
        }
        
        private void showErrorNullPointer(final Component root)
        {
            final String MESSAGE = "Error! No filepath was given.";
            final String TITLE = "NullPointerException";
            JOptionPane.showMessageDialog(root, MESSAGE, TITLE,
                    JOptionPane.ERROR_MESSAGE);
        }
    }
};