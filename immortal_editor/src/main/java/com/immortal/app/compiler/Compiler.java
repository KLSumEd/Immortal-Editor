package com.immortal.app.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import com.immortal.app.lex.ImmortalLexer;
import com.immortal.app.lex.Lexer;
import com.immortal.app.tokens.Token;

public class Compiler
{
    private static boolean hadError = false;
    
    public static void main(String[] args) throws IOException
    {
        
        if (args.length > 1)
        {
            System.out.println("Usage: ImmortalScript [script]");
            System.exit(64);
        }
        else if (args.length == 1) runFile(args[0]);
        else runPrompt();
    }
    
    private static void runFile(String path) throws IOException
    {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError) System.exit(65);
    }
    
    private static void runPrompt() throws IOException
    {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        
        System.out.println("Welcome to the ImmortalScript Parser!");
        
        for (;;)
        {
            System.out.print("> ");
            String line = reader.readLine();
            
            if (line.equals("\u0004"))
            { break; }
            
            run(line);
            hadError = false;
        }
    }
    
    private static void run(String source)
    {
        final Lexer lexer = new ImmortalLexer(source);
        final Collection<Token> tokens = lexer.lex();
        
        if (lexer.hasErrorOccurred())
        {
            error(lexer.getLastLine(), lexer.getErrorMessage());
        }
        else
        {
            // For now, just print the tokens.
            
            for (Token token : tokens)
            { System.out.println(token); }
        }
    }
    
    public static void error(int line, String message)
    { report(line, "", message); }
    
    private static void report(int line, String where, String message)
    {
        System.err
                .println("[line " + line + "] Error " + where + ": " + message);
        hadError = true;
    }
    
}