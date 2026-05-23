package com.immortal.app;

import javax.swing.UnsupportedLookAndFeelException;

import com.immortal.app.editor.TextEditor;

public class Main
{
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException
    {
        @SuppressWarnings("unused")
        TextEditor editor = new TextEditor();
    }
}