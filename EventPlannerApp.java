import java.time.*;
import java.time.format.*;
import java.util.*;
class task {
    private String title, description, status;
    private LocalDate startDate, endDate;

    task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "New";
    }

    // getter methods
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // setter method
    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void startTask() {
        status = "Progress";
        startDate = LocalDate.now();
    }

    public void completeTask() {
        status = "Completed";
        endDate = LocalDate.now();
    }

    public String toString() {
        return "{ Title: " + title + ",\nDescription: " + description + ",\nStatus: " + status + " }";
    }

}
abstract class Event {
    private String eventTitle, eventId, eventManager, customerContact;
    private LocalDate eventDate;
    private int durationInDays, numOfParticipants, unitPrice;
    private ArrayList<task> tasks;

    public Event(String eventTitle, String customerContact, LocalDate eventDate, int durationInDays,
            int numOfParticipants) {
        this.eventTitle = eventTitle;
        this.customerContact = customerContact;
        this.eventDate = eventDate;
        this.durationInDays = durationInDays;
        this.numOfParticipants = numOfParticipants;
        this.eventId = String.format("%d", new Random().nextInt(1000));
        this.eventManager = null;
        this.tasks = new ArrayList<>();
    };

    // getter methods

    public String geTeventTitle() {
        return eventTitle;
    }

    public String geTeventId() {
        return eventId;
    }

    public String geTeventManager() {
        return eventManager;
    }

    public String geTcustomerContact() {
        return customerContact;
    }

    public int geTdurationInDays() {
        return durationInDays;
    }

    public int geTnumOfParticipants() {
        return numOfParticipants;
    }

    public int geTunitPrice() {
        return unitPrice;
    }

    public LocalDate geTeventDate() {
        return eventDate;
    }

    // setter methods
    protected void addPrefixCodeToId(String prefix) {
        eventId = prefix + eventId;
    };

    public void setEventManager(String name) {
        eventManager = name;
    }

    public void addTask(String title, String description) {
        tasks.add(new task(title, description));
    };

    public task findTask(String title) {
        for (task it : tasks) {
            if (it.getTitle().equals(title)) {
                return it;
            }
        }
        return null;
    };

    public boolean startTask(String title) {
        task tmp = findTask(title);
        if (findTask(title) != null) {
            tmp.startTask();
            return true;
        }
        return false;
    }

    public boolean completeTask(String title) {
        task tmp = findTask(title);
        if (findTask(title) != null) {
            tmp.completeTask();
            return true;
        }
        return false;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = LocalDate.parse(eventDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String toString() {
        return "Event title: " + eventTitle + "\nEvent id: " + eventId +
                "\nEvent manager: " + eventManager +
                "\nCsutomer contact: " + customerContact + "\nEvent date: " + eventDate + "\nDuration in days: "
                + durationInDays + "\nNum of participants: " + numOfParticipants + "\nUnit price: " + unitPrice
                + "\nTasks " + tasks;
    }

    public abstract double getBill();
}
class CorporateEvent extends Event{
    private String venue;
    private boolean hasDiscount;

    public CorporateEvent(String eventTitle, String customerContact, LocalDate eventDate,
            int durationInDays, int numOfParticipants) {
        super(eventTitle, customerContact, eventDate, durationInDays, numOfParticipants);
        addPrefixCodeToId("CC");
    }

    @Override
    public double getBill() {
        if (hasDiscount == true) {
            return 0;
        }
        return 0;
    };
}

class TourPackage extends Event {
    ArrayList<String> placesToVisit;
    ArrayList<String> customerContacts;
    private int numOfRegisteredParticipants,perPersonPrice;
    public TourPackage(String eventTitle, String customerContact,LocalDate eventDate, int durationInDays, int numOfParticipants,
            int perPersonPrice) {
        super(eventTitle, customerContact, eventDate, durationInDays, numOfParticipants);
        this.placesToVisit = new ArrayList<>();
        this.customerContacts = new ArrayList<>();
        this.perPersonPrice=perPersonPrice;
        addPrefixCodeToId("TP");
    }
    
    // getter methods

    public int geTperPersonPrice(){return perPersonPrice;}


    public boolean registerForTour(int participants, String contactNo){
        if(geTnumOfParticipants()<=(numOfRegisteredParticipants+participants)){
            numOfRegisteredParticipants+=participants;
            customerContacts.add(contactNo);
            return true;
        }
        return false;
    }


    public void addPlacesToVisit(String placeToVisit){
        placesToVisit.add(placeToVisit);
    }


    public double getBill() {
        int totalBill = numOfRegisteredParticipants*perPersonPrice;
        return totalBill;
    }
}

class EventPlanner {
    private String name;
    private ArrayList<Event> events, requestedEvents;

    public EventPlanner(String name) {
        this.name = name;
        this.events = new ArrayList<>();
        this.requestedEvents = new ArrayList<>();
    }

    public void viewEvents() {
        System.out.println(events);
    }

    public Event findEvent(String id) {
        for (Event it : events) {
            if (it.geTeventId().equals(id)) {
                return it;
            }
        }
        return null;
    }

    public Event findRequestedEvent(String id) {
        for (Event it : requestedEvents) {
            if (it.geTeventId().equals(id)) {
                return it;
            }
        }
        return null;
    }

    public ArrayList<Event> searchEvents(String title) {
        ArrayList<Event> ans = new ArrayList<>();
        for (Event it : events) {
            if (it.geTeventTitle().equals(ans)) {
                ans.add(it);
            }
        }
        return ans;
    }

    public ArrayList<TourPackage> searchForTourPackages(String title) {
        ArrayList<TourPackage> ans = new ArrayList<>();
        for (Event it : events) {
            String chk = it.geTeventId();
            if (chk.charAt(0) == 'T' && chk.charAt(1) == 'P' && it.geTeventTitle().equals(title)) {
                ans.add(new TourPackage(it.geTeventTitle(), it.geTcustomerContact(), it.geTeventDate(),
                        it.geTdurationInDays(),
                        it.geTnumOfParticipants(), it.geTunitPrice()));
            }
        }
        return ans;
    }

    public String requestEvent(String eventTitle, String customerContact, LocalDate eventDate, int durationInDays,
            int numOfParticipants) {
        CorporateEvent chk = new CorporateEvent(eventTitle, customerContact, eventDate, durationInDays,
                numOfParticipants);
        requestedEvents.add(chk);
        return chk.geTeventId();
    }

    public void acceptEvent(String eventID) {
        Event chk = findRequestedEvent(eventID);
        if (chk != null) {
            requestedEvents.remove(chk);
        }
    }

    public void acceptedEvent() {
        for (Event it : events) {
            System.out.println(it);
        }
    }

    public void shoWrequestedEvents() {
        System.out.println(requestedEvents);
    }

    public String offerTourPackage(String eventTitle, LocalDate eventDate, int durationInDays, int numOfParticipants,
            int perPersonPrice,
            ArrayList<String> placesToVisit) {
        TourPackage chk = new TourPackage(eventTitle, null, eventDate, durationInDays, numOfParticipants,
                perPersonPrice);
        events.add(chk);
        return chk.geTeventId();
    }

    public String offerTourPackage(String eventTitle, LocalDate eventDate, int durationInDays, int numOfParticipants,
            int perPersonPrice) {
        TourPackage chk = new TourPackage(eventTitle, null, eventDate, durationInDays, numOfParticipants,
                perPersonPrice);
        events.add(chk);
        return chk.geTeventId();
    }

    public void registerForTour(String tourId, int participants, String contact) {
        Event chk = findEvent(tourId);
        if (chk != null) {
            TourPackage tk = new TourPackage(tourId, contact, null, participants, participants, participants);
            tk.registerForTour(participants, contact);
        }
    }

    public void assignEventManager(String eventID, String managerName) {
        Event chk = findEvent(eventID);
        if (chk != null) {
            chk.setEventManager(managerName);
        }
    }

    public void addEventTask(String eventID, String title, String description) {
        Event chk = findEvent(eventID);
        if (chk != null) {
            chk.addTask(title, description);
        }
    }

    public void startEventTask(String eventID, String title) {
        Event chk = findEvent(eventID);
        if (chk != null) {
            chk.startTask(title);
        }
    }

    public void completeEventTask(String eventID, String title) {
        Event chk = findEvent(eventID);
        if (chk != null) {
            chk.completeTask(title);
        }
    }

    public double payBill(String eventId) {
        Event chk = findEvent(eventId);
        if (chk != null) {
            return chk.getBill();
        }
        return 0;
    }
}
public class EventPlannerApp {
    public static void main(String[] args) {
        EventPlanner evp1 = new EventPlanner("EVP1");
        while (true) {
            System.out.println("1 to login as employee\n2 as customer\n3 to switch role\n0 to exit");
            System.out.println("Role:");
            int role = new Scanner(System.in).nextInt();
            if (role == 1) {
                while (true) {
                    System.out.println("Log in as employee");
                    System.out.println("1 to offer tour package event\n2 to view accepted event\n" +
                            "3 to view requested event\n" +
                            "4 to accept event\n" +
                            "5 to assign event manager\n" +
                            "6 to add task\n" +
                            "7 to start task\n" +
                            "8 to complete task\n" +
                            "0 to logout\n");
                    int op = new Scanner(System.in).nextInt();
                    if (op == 0) {
                        System.out.println("Log out successfully.");
                        break;
                    } else if (op == 1) {
                        System.out.println("Eventtitle:");
                        String eventTitle = new Scanner(System.in).next();
                        System.out.println("Customer Contact:");
                        String customerContact = new Scanner(System.in).next();
                        System.out.println("Day no (1-31):");
                        String date = new Scanner(System.in).next();
                        System.out.println("Month no(1-12):");
                        String month = new Scanner(System.in).next();
                        System.out.println("Enter year:");
                        String year = new Scanner(System.in).next();
                        System.out.println("Duration days:");
                        int durationInDays = new Scanner(System.in).nextInt();
                        System.out.println("Number of participants:");
                        int numOfParticipants = new Scanner(System.in).nextInt();
                        System.out.println("Per person price:");
                        int perPersonPrice = new Scanner(System.in).nextInt();
                        if (date.length() == 1)
                            date = "0" + date;
                        if (month.length() == 1)
                            month = "0" + month;
                        if (year.length() != 4) {
                            System.out.println("Please enter correct date:");
                            continue;
                        }
                        LocalDate eventDate = LocalDate.parse(date + "/" + month + "/" + year,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        evp1.offerTourPackage(eventTitle, eventDate, durationInDays, numOfParticipants, perPersonPrice);

                    } else if (op == 2) {
                        evp1.acceptedEvent();

                    } else if (op == 3) {
                        evp1.shoWrequestedEvents();

                    } else if (op == 4) {
                        System.out.println("Event id:");
                        String eventId = new Scanner(System.in).next();
                        evp1.acceptEvent(eventId);
                    } else if (op == 5) {
                        String eventId = new Scanner(System.in).next(), managerName = new Scanner(System.in).next();
                        ;
                        evp1.assignEventManager(eventId, managerName);
                    } else if (op == 6) {
                        String eventId = new Scanner(System.in).next(), title = new Scanner(System.in).next(),
                                description = new Scanner(System.in).next();
                        evp1.addEventTask(eventId, title, description);
                    } else if (op == 7) {
                        String eventId = new Scanner(System.in).next(), title = new Scanner(System.in).next();
                        evp1.startEventTask(eventId, title);
                    } else if (op == 8) {
                        String eventId = new Scanner(System.in).next(), title = new Scanner(System.in).next();
                        evp1.completeEventTask(eventId, title);
                    }
                }

            }

            else if (role == 2) {
                while (true) {
                    System.out.println("1 to request for corporate event\n2 to search for tour packages\n" + //
                            "3 to register for tour package\n4 to view the detail of their event\n5 to pay bill\n" +
                            "0 to logout");
                    int op = new Scanner(System.in).nextInt();
                    if (op == 0) {
                        break;
                    } else if (op == 1) {
                        System.out.println("eventTitle:");
                        String eventTitle = new Scanner(System.in).next();
                        System.out.println("customerContact:");
                        String customerContact = new Scanner(System.in).next();
                        System.out.println("durationInDays:");
                        int durationInDays = new Scanner(System.in).nextInt();
                        System.out.println("numOfParticipants:");
                        int numOfParticipants = new Scanner(System.in).nextInt();
                        System.out.println("Day no (1-31):");
                        String date = new Scanner(System.in).next();
                        System.out.println("Month no(1-12):");
                        String month = new Scanner(System.in).next();
                        System.out.println("Enter year:");
                        String year = new Scanner(System.in).next();
                        if (date.length() == 1)
                            date = "0" + date;
                        if (month.length() == 1)
                            month = "0" + month;
                        if (year.length() != 4) {
                            System.out.println("Please enter correct date:");
                            continue;
                        }
                        LocalDate eventDate = LocalDate.parse(date + "/" + month + "/" + year,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        evp1.requestEvent(eventTitle, customerContact, eventDate, durationInDays, numOfParticipants);
                    } else if (op == 2) {
                        System.out.println("Event title:");
                        String title = new Scanner(System.in).next();
                        System.out.println(evp1.searchForTourPackages(title));
                    } else if (op == 3) {
                        System.out.println("TourId:");
                        String tourId = new Scanner(System.in).next();
                        System.out.println("Participants:");
                        int participants = new Scanner(System.in).nextInt();
                        System.out.println("Contact:");
                        String contact = new Scanner(System.in).next();
                        evp1.registerForTour(tourId, participants, contact);
                    } else if (op == 4) {
                        evp1.viewEvents();
                    } else if (op == 5) {
                        System.out.println("Event id:");
                        String eventId = new Scanner(System.in).next();
                        evp1.payBill(eventId);
                    }
                }
            } else if (role == 0) {
                System.out.println("Exit done.");
                break;
            } else
                System.out.println("Invalid role.");
        }
    }

}