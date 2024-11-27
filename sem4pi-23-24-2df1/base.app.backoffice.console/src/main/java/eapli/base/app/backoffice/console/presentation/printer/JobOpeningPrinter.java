package eapli.base.app.backoffice.console.presentation.printer;

import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.framework.visitor.Visitor;

public class JobOpeningPrinter implements Visitor<JobOpening>  {
    @Override
    public void visit(final JobOpening jo) {
        String customerName;
        if(jo.customer() == null){
            customerName = "No Customer";
        } else {
            customerName = jo.customer().user().name().toString();
        }
        System.out.printf("%-15s %-30s %-15s %-12s %-45s %-40s %-15s %-30s", jo.jobReference(), jo.jobTitle(), jo.contractType(), jo.mode(), jo.address().street() + " " + jo.address().doorNumber() + ", "+ jo.address().county() + " " + jo.address().postalCode().postalCode(), customerName, jo.numberOfVacancies(), jo.jobDescription());
    }
}