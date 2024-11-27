package eapli.base.app.backoffice.console.presentation.printer;

import eapli.framework.visitor.Visitor;

import java.io.File;

public class FilePrinter implements Visitor<File> {
    @Override
    public void visit(File visitee) {
        System.out.println(visitee.getName());
    }
}
