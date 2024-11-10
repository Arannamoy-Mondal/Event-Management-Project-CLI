
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class test {
    public static void main(String[] args) {
        task task1 = new task("Task1", "Testing1");
        // System.out.println(task1.getTitle() + " " + task1.getDescription() + " " +
        // task1.getStatus() + " "
        // + task1.getStartDate() + " " + task1.getEndDate() + " " + task1);
        // task1.startTask();
        // System.out.println(task1.getTitle() + " " + task1.getDescription() + " " +
        // task1.getStatus() + " "
        // + task1.getStartDate() + " " + task1.getEndDate() + " " + task1);
        // task1.completeTask();
        // System.out.println(task1.getTitle() + " " + task1.getDescription() + " " +
        // task1.getStatus() + " "
        // + task1.getStartDate() + " " + task1.getEndDate() + " " + task1);

        // Event class

        // Event event1=new Event("Event1","012345678912","11/11/2024",25,20);

        CorporateEvent cc1 = new CorporateEvent("CC1", "012345678912",
                LocalDate.parse("12/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), 25, 20);
        System.out.println(cc1.geTeventTitle() + " " +
                cc1.geTcustomerContact() + " " + cc1.geTeventDate() + " " +
                cc1.geTdurationInDays() + " " + cc1.geTnumOfParticipants() + " " + cc1.geTeventId() +
                " " + cc1.geTeventManager() + " " + cc1.geTunitPrice() + " " + cc1.getTasks());

        cc1.addTask("Boarding", "Boarding from DAC");
        task tmp = cc1.findTask("Boarding");
        System.out.println(tmp);
        tmp = cc1.findTask("null");
        System.out.println(tmp);
        System.out.println(cc1.geTeventTitle() + " " +
                cc1.geTcustomerContact() + " " + cc1.geTeventDate() + " " +
                cc1.geTdurationInDays() + " " + cc1.geTnumOfParticipants() + " " + cc1.geTeventId() +
                " " + cc1.geTeventManager() + " " + cc1.geTunitPrice() + " " + cc1.getTasks());

        if (cc1.startTask("Boarding")) {
            System.out.println("Start the task");
        }

        if (!cc1.startTask("Go to pub")) {
            System.out.println("Not found");
        }
        System.out.println(cc1.getTasks());
        cc1.completeTask("Boarding");
        cc1.completeTask("Pub");
        cc1.setUnitPrice(150);
        cc1.setEventManager("Manager1");
        System.out.println(cc1);
        System.out.println(cc1.getBill());
    }
}
