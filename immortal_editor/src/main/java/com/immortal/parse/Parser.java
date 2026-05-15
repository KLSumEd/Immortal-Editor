package com.immortal.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.immortal.scan.Scanner;

public class Parser
{
    private static boolean hadError = false;

    public static void main(String[] args) throws IOException
    {
        if (args.length > 1)
        {
            System.out.println("Usage: ImmortalScript [script]");
            System.exit(64);
        }
        else if (args.length == 1)
        {
            runFile(args[0]);
        }
        else
        {
            runPrompt();
        }

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
        Scanner scanner = new Scanner(source);
        // Let's think, is the scanner ever used here?
        // No?
        // So why am I making it
        // No good reason
        // The parser acts like it is connecting disparate objects
        // But the lexing process does not exist without the scanner
        // It is a *weak* object

        // Tokenizer tokenizer = new Tokenizer();
        // Lexer lexer = new Lexer(scanner, tokenizer);
        // Collection<Token> tokens = lexer.lexAll();
        if (hadError) return;
        // For now, just print the tokens.
        // for (Token token : tokens) {System.out.println(token);}
    }

    public static void error(int line, String message)
    { report(line, "", message); }

    private static void report(int line, String where, String message)
    {
        System.err.println("[line " + line + "] Error " + where + ": " + message);
        hadError = true;
    }

}