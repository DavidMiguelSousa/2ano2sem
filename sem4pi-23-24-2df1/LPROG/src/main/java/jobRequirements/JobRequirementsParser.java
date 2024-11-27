package jobRequirements;

import interviewModel.InterviewModelGrammarLexer;
import interviewModel.InterviewModelGrammarParser;
import interviewModel.InterviewModelVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.*;
import question.QuestionAnswer;
import question.QuestionSolution;
import requirements.RequirementAnswer;
import requirements.RequirementSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JobRequirementsParser {
       public List<RequirementSolution> jobRequirementsJobOpening(Path path) throws Exception {

           String content = Files.readString(path);

           CharStream input = CharStreams.fromString(content);

           JobRequirementsGrammarLexer lexer = new JobRequirementsGrammarLexer(input);
           CommonTokenStream tokens = new CommonTokenStream(lexer);
           JobRequirementsGrammarParser parser = new JobRequirementsGrammarParser(tokens);

           ParseTree tree = parser.start();
           JobRequirementsVisitor visitor = new JobRequirementsVisitor();
           visitor.visit(tree);

           return visitor.jobRequirementsJobOpening();
       }

       public boolean validateGrammarJobRequirements(String filename) {
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

           JobRequirementsGrammarLexer lexer = new JobRequirementsGrammarLexer(inputCharStream);
           lexer.removeErrorListeners();

           CommonTokenStream tokens = new CommonTokenStream(lexer);

           JobRequirementsGrammarParser parser = new JobRequirementsGrammarParser(tokens);
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

           if (!content.isEmpty() && content.charAt(content.length() - 1) == '\n') {
               content.setLength(content.length() - 1); // Remove the last newline
           }

           reader.close();
           return content.toString();
       }

       public List<RequirementAnswer> jobRequirementsJobApplication(Path path) throws Exception {
           CharStream input = CharStreams.fromPath(path);
           JobRequirementsGrammarLexer lexer = new JobRequirementsGrammarLexer(input);
           CommonTokenStream tokens = new CommonTokenStream(lexer);
           JobRequirementsGrammarParser parser = new JobRequirementsGrammarParser(tokens);

           ParseTree tree = parser.start(); // Start parsing at the root of the grammar
           JobRequirementsVisitor visitor = new JobRequirementsVisitor();
           visitor.visit(tree); // Visit the parse tree with the visitor

           return visitor.jobRequirementsJobApplication();
       }

   }
