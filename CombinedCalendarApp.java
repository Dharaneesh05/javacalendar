import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
class Event {
    int day;
    int month;
    int year;
    String description;
    public Event(int day, int month, int year, String description) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.description = description;
    }
    public String toString() {
        return "Event on " + day + "/" + month + "/" + year + ": " + description;
    }
}
public class CombinedCalendarApp {
    private ArrayList<Event> events; 
    private LocalDate currentDate;
    public CombinedCalendarApp() {
        events = new ArrayList<>();
        currentDate = LocalDate.now();  
    }
    public void addEvent(int day, int month, int year, String description) {
        Event event = new Event(day, month, year, description);
        events.add(event);
        System.out.println("Event added successfully.");
    }
    public void viewEvents(int day, int month, int year) {
        System.out.println("Events on " + day + "/" + month + "/" + year + ":");
        boolean found = false;
        for (Event event : events) {
            if (event.day == day && event.month == month && event.year == year) {
                System.out.println(event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No events found for this date.");
        }
    }
    public void displayCalendar() {
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int lengthOfMonth = currentDate.lengthOfMonth();
        int startingDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        System.out.println("Calendar - " + currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentDate.getYear());
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
        for (int i = 1; i < startingDayOfWeek; i++) {
            System.out.print("    "); 
        }
        for (int day = 1; day <= lengthOfMonth; day++) {
            System.out.printf("%3d ", day);
            if ((startingDayOfWeek + day - 1) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println(); 
    }
    public void navigateMonth(int increment) {
        currentDate = currentDate.plusMonths(increment);
    }
    public static void main(String[] args) {
        CombinedCalendarApp calendarApp = new CombinedCalendarApp();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nCalendar Application");
            System.out.println("1. Display Calendar");
            System.out.println("2. Add Event");
            System.out.println("3. View Events");
            System.out.println("4. Next Month");
            System.out.println("5. Previous Month");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    calendarApp.displayCalendar();
                    break;
                case 2:
                    System.out.print("Enter day: ");
                    int day = scanner.nextInt();
                    System.out.print("Enter month: ");
                    int month = scanner.nextInt();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter event description: ");
                    String description = scanner.nextLine();
                    calendarApp.addEvent(day, month, year, description);
                    break;
                case 3:
                    System.out.print("Enter day: ");
                    day = scanner.nextInt();
                    System.out.print("Enter month: ");
                    month = scanner.nextInt();
                    System.out.print("Enter year: ");
                    year = scanner.nextInt();
                    calendarApp.viewEvents(day, month, year);
                    break;
                case 4:
                    calendarApp.navigateMonth(1);
                    break;
                case 5:
                    calendarApp.navigateMonth(-1);
                    break;
                case 6:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
