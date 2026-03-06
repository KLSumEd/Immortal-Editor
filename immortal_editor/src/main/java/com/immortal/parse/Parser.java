package com.immortal.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.immortal.scan._Scanner;
import com.immortal.tokens.Token;

public class Parser 
{
    private static boolean hadError = false;

    public static void main(String[] args) throws IOException 
    {
        if (args.length > 1) 
        {
            System.out.println("Usage: ImmortalScript [script]");
            System.exit(64); 
        } else if (args.length == 1) {runFile(args[0]);} 
        else {runPrompt();}
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
            if (line.equals("\u0004")) { break; }
            run(line);
            hadError = false;
        }
    }

    private static void run(String source) 
    {
        _Scanner scanner = new _Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // For now, just print the tokens.
        for (Token token : tokens) {System.out.println(token);}
    }

    public static void error(int line, String message) {report(line, "", message);}

    private static void report(int line, String where, String message) 
    {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

}