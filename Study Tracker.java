/////////////////////////////////////////////////////////////////////////////
//
//  Function Name : StudyTrackerActivity
//  Description   : This function is used to track and maintain study logs 
//                  such as subjects, duration, and progress. It helps 
//                  monitor study sessions and generate performance summaries.
//  Input         : It may accept study details such as subject name, 
//                  duration (in hours), and date.
//  Output        : It stores or displays the study log details and 
//                  provides a summary or report of study progress.
//  Author        : Rukmini Gaikwad
//  Date          : 15/10/2025
//
/////////////////////////////////////////////////////////////////////////////


import java.util.*;
import java.time.LocalDate;
import java.io.*;

////////////////////////////////////////////////////////////////////////////////////
//
// Class Name   : StudyLog
// Description  : Used to represent a single study log record containing date,
//                subject, duration, and description.
//
////////////////////////////////////////////////////////////////////////////////////

class StudyLog {
    private LocalDate Date;
    private String Subject;
    private double Duration;
    private String Description;

    ///////////////////////////////////////////////////////////////////////////
    //
    // Function Name : StudyLog (Constructor)
    // Description   : Initializes the study log with given parameters.
    // Input         : LocalDate date, String subject, Double duration, String description
    // Output        : None
    //
    ///////////////////////////////////////////////////////////////////////////
    
    public StudyLog(LocalDate A, String B, Double C, String D) 
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // Function Name : toString
    // Description   : Converts the study log object into a readable string format.
    // Input         : None
    // Output        : String containing formatted study log data
    //
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public String toString() 
    {
        return Date + " | " + Subject + " | " + Description + " | " + Duration + " hrs";
    }

    // Getter Methods
    public LocalDate getDate()
     {  
        return Date;  
     }
    public String getSubject()
     {
         return Subject;
     }
    public Double getDuration() 
    { 
        return Duration; 
    }
    public String getDescription()
     { 
        return Description;
     }
}

////////////////////////////////////////////////////////////////////////////////////
//
// Class Name   : StudyTracker
// Description  : Handles study tracking functionalities like inserting logs,
//                displaying logs, exporting to CSV, and generating summaries.
//
////////////////////////////////////////////////////////////////////////////////////

class StudyTracker 
{

    // Data Structure to hold all study log entries
    private ArrayList<StudyLog> Database = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////
    //
    // Function Name : InsertLog
    // Description   : Accepts new study details from user and adds them to the database.
    // Input         : User input through console (subject, duration, description)
    // Output        : Updates the internal ArrayList with a new StudyLog record
    //
    ///////////////////////////////////////////////////////////////////////////
    
    public void InsertLog()
     {
        Scanner ScannerObj = new Scanner(System.in);

        System.out.println("------------------------------------------------------");
        System.out.println("------- Please Enter The Details of Your Study -------");
        System.out.println("------------------------------------------------------");

        LocalDate DateObj = LocalDate.now();

        System.out.print("Enter Subject (C/C++/Java/OS/DS): ");
        String sub = ScannerObj.nextLine();

        System.out.print("Enter Study Duration (in hours): ");
        Double dur = ScannerObj.nextDouble();
        ScannerObj.nextLine();

        System.out.print("Enter Description about your study: ");
        String desc = ScannerObj.nextLine();

        StudyLog StudyObj = new StudyLog(DateObj, sub, dur, desc);
        Database.add(StudyObj);

        System.out.println("------------------------------------------------------");
        System.out.println("Study Log stored successfully!");
        System.out.println("------------------------------------------------------");
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // Function Name : DisplayLog
    // Description   : Displays all the stored study logs from the database.
    // Input         : None
    // Output        : Prints all study log details to the console
    //
    ///////////////////////////////////////////////////////////////////////////
    
    public void DisplayLog()
     {
        System.out.println("-------------------------------------------------------");

        if (Database.isEmpty()) 
        {
            System.out.println("No records found. Database is empty.");
            System.out.println("-------------------------------------------------------");
            return;
        }

        System.out.println("------ Log Report from Marvellous Study Tracker -------");
        System.out.println("-------------------------------------------------------");

        for (StudyLog sobj : Database)
        {
            System.out.println(sobj);
        }

        System.out.println("-------------------------------------------------------");
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // Function Name : ExportCSV
    // Description   : Exports all study logs into a CSV file format.
    // Input         : None
    // Output        : Creates a CSV file named 'MarvellousStudy.csv'
    //
    ///////////////////////////////////////////////////////////////////////////
    
    public void ExportCSV() 
    {
        if (Database.isEmpty()) 
        {
            System.out.println("Nothing to export. Database is empty.");
            System.out.println("-------------------------------------------------------");
            return;
        }

        String FileName = "MarvellousStudy.csv";

        try (FileWriter fwobj = new FileWriter(FileName)) 
        {
            fwobj.write("Date,Subject,Duration,Description\n");

            for (StudyLog sobj : Database) 
            {
                fwobj.write(sobj.getDate() + "," +
                            sobj.getSubject().replace(",", " ") + "," +
                            sobj.getDuration() + "," +
                            sobj.getDescription().replace(",", " ") + "\n");
            }

            System.out.println("Log exported successfully to " + FileName);
        } 
        catch (Exception eobj) 
        {
            System.out.println("Exception occurred while creating the CSV file.");
            System.out.println("Please report this issue to Marvellous Infosystems.");
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // Function Name : SummaryByDate
    // Description   : Displays total study time grouped by each date.
    // Input         : None
    // Output        : Prints the total hours studied per date
    //
    ///////////////////////////////////////////////////////////////////////////
    
    public void SummaryByDate()
     {
        System.out.println("-------------------------------------------------------");

        if (Database.isEmpty()) 
        {
            System.out.println("No data available to summarize.");
            System.out.println("-------------------------------------------------------");
            return;
        }

        System.out.println("------ Summary by Date from Marvellous Study Tracker ------");
        System.out.println("-----------------------------------------------------------");

        TreeMap<LocalDate, Double> tobj = new TreeMap<>();

        for (StudyLog sobj : Database) 
        {
            LocalDate date = sobj.getDate();
            double duration = sobj.getDuration();

            tobj.put(date, tobj.getOrDefault(date, 0.0) + duration);
        }

        for (LocalDate date : tobj.keySet()) 
        {
            System.out.println("Date: " + date + " | Total Study: " + tobj.get(date) + " hrs");
        }

        System.out.println("-------------------------------------------------------");
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // Function Name : SummaryBySubject
    // Description   : Displays total study time grouped by subject.
    // Input         : None
    // Output        : Prints the total hours studied per subject
    //
    ///////////////////////////////////////////////////////////////////////////
    
    public void SummaryBySubject()
     {
        System.out.println("-------------------------------------------------------");

        if (Database.isEmpty()) 
        {
            System.out.println("No data available to summarize.");
            System.out.println("-------------------------------------------------------");
            return;
        }

        System.out.println("------ Summary by Subject from Marvellous Study Tracker ------");
        System.out.println("--------------------------------------------------------------");

        TreeMap<String, Double> tobj = new TreeMap<>();

        for (StudyLog sobj : Database)
         {
            String subject = sobj.getSubject();
            double duration = sobj.getDuration();

            tobj.put(subject, tobj.getOrDefault(subject, 0.0) + duration);
        }

        for (String subject : tobj.keySet()) 
        {
            System.out.println("Subject: " + subject + " | Total Study: " + tobj.get(subject) + " hrs");
        }

        System.out.println("-------------------------------------------------------");
    }
}

////////////////////////////////////////////////////////////////////////////////////
//
// Class Name   : StudyTracker
// Description  : Main driver class for the Study Tracker Application.
//                It provides a user menu to interact with StudyTracker functionalities.
//
////////////////////////////////////////////////////////////////////////////////////

class StudyTracker
{
    public static void main(String A[]) 
    {
        StudyTracker stobj = new StudyTracker();
        Scanner ScannerObj = new Scanner(System.in);

        int iChoice = 0;

        System.out.println("-------------------------------------------------------");
        System.out.println("---- Welcome to Marvellous Study Tracker Application ----");
        System.out.println("-------------------------------------------------------");

        do 
        {
            System.out.println("\nPlease select the appropriate option:");
            System.out.println("1 : Insert new Study Log");
            System.out.println("2 : View all Study Logs");
            System.out.println("3 : Summary of Study Log by Date");
            System.out.println("4 : Summary of Study Log by Subject");
            System.out.println("5 : Export Study Log to CSV file");
            System.out.println("6 : Exit the Application");
            System.out.print("Enter your choice: ");

            iChoice = ScannerObj.nextInt();

            switch (iChoice) 
            {
                case 1:
                    stobj.InsertLog();
                    break;

                case 2:
                    stobj.DisplayLog();
                    break;

                case 3:
                    stobj.SummaryByDate();
                    break;

                case 4:
                    stobj.SummaryBySubject();
                    break;

                case 5:
                    stobj.ExportCSV();
                    break;

                case 6:
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Thank you for using Marvellous Study Tracker Application!");
                    System.out.println("-------------------------------------------------------");
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while (iChoice != 6);
    }
}
