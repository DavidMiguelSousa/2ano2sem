package interviewModel;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import question.QuestionAnswer;
import question.QuestionSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InterviewModelParser {
    public List<QuestionSolution> interviewJobOpening(Path path) throws Exception {

        String content = Files.readString(path);

        CharStream input = CharStreams.fromString(content);

        InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);

        ParseTree tree = parser.start();
        InterviewModelVisitor visitor = new InterviewModelVisitor();
        visitor.visit(tree);

        return visitor.interviewJobOpening();
    }

    public boolean validateGrammarInterview(String filename) {
        try {
            return validateGrammar(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateGrammar(String filename) throws IOException {
        String fileContent = readFile(filename);

        CharStream inputCharStream = CharStreams.fromString(fileContent);

        InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(inputCharStream);
        lexer.removeErrorListeners();

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);
        parser.removeErrorListeners();

        parser.setErrorHandler(new BailErrorStrategy());

        try {
            parser.start();
            return true;
        } catch (ParseCancellationException e) {
            return false;
        }
    }

    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                content.append(line);
                firstLine = false;
            } else {
                content.append("\n").append(line);
            }
        }

        reader.close();
        return content.toString();
    }

    public List<QuestionAnswer> interviewJobApplication(Path path) throws Exception {
        CharStream input = CharStreams.fromPath(path);
        InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);

        ParseTree tree = parser.start(); // Start parsing at the root of the grammar
        InterviewModelVisitor visitor = new InterviewModelVisitor();
        visitor.visit(tree); // Visit the parse tree with the visitor

        return visitor.interviewJobApplication();
    }

}
