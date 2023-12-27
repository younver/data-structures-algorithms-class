package homeworks.student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    // ~~design of readFile
    public static DoublyLinkedList<Student> readStudentFile(String fileName){
        // initialize reader and output dlinkedlist
        File file = new File(fileName);
        DoublyLinkedList<Student> output = new DoublyLinkedList<>();
        
        // read the file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            // iterate through lines
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");

                // create student
                Student student = new Student(values[0], values[1], values[2], values[3]);
                
                // add to output dlinkedlist
                output.addToEnd(student);
            }

            // close reader
            br.close();
        } 
        catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        
        return output;
    }
    
    public static void main(String[] args) {
        // init
        Scanner scanner = new Scanner(System.in);
        String PATH = "homeworks/student/students.txt";

        DoublyLinkedList<Student> initialStudentList = readStudentFile(PATH);
        DoublyLinkedList<Student> studentList = readStudentFile(PATH);
    
        // program
        boolean isRunning = true;
        while (isRunning){
            // display menu
            System.out.println();
            System.out.println("0) exit");
            System.out.println("1) display the initial student list");
            System.out.println("2) display the students sorted by overall average");
            System.out.println("3) display the student names sorted by data structure grades");
            System.out.println("4) display the math average");
            System.out.println("5) display student(s) with lowest data structures grade");
            System.out.println("6) delete the student");
            System.out.println("7) insert new student");
            System.out.println("8) display student count");
            System.out.println("9) display successful students");

            // input
            System.out.print("~~ what's on your mind: ");

            int option = -1;
            try{
                option = Integer.parseInt(scanner.nextLine()); // nextInt doesn't read newline
            } catch (NumberFormatException ex){}
            
            // operation
            System.out.println();
            switch(option){
                case 0:
                    isRunning = false;
                    System.out.println("~~ terminating session");
                    break;
                case 1:
                    //~~ display initial
                    initialStudentList.display();
                    System.out.println("~~ initial student list");
                    break;
                case 2:
                    //~~ sorted display
                    studentList.sort();
                    studentList.display();
                    System.out.println("~~ sorted student list");
                    break;
                case 3:
                    //~~ ds sorted name display
                    studentList.sortBy("ds");
                    studentList.displayNames();
                    
                    System.out.println("~~ ds sorted student names");
                    break;
                case 4:
                    //~~ display math avg
                    float avgMath = studentList.avgBy("math");
                    System.out.println("~~ math average: " + avgMath);
                    break;
                case 5:
                    //~~ display students ds lowest
                    Student minStudent = studentList.minBy("ds");
                    if (minStudent != null){
                        DoublyLinkedList<Student> minStudents = studentList.findBy("ds", minStudent.dsGrade);
                        minStudents.display();
                    }
                    System.out.println("~~ students with lowest ds grade");
                    break;
                case 6:
                    //~~ delete by id
                    System.out.print("~~ delete student: ");
                    try{
                        int id = Integer.parseInt(scanner.nextLine());
                        studentList.deleteByID(id);
                    } catch (NumberFormatException ex){
                        System.out.println("~~ failed: invalid id format");
                    } catch (NullPointerException ez){
                        System.out.println("~~ failed: invalid id");
                    }
                    break;
                case 7:
                    //~~ add student
                    System.out.print("~~ add student: ");

                    try {
                        String input = scanner.nextLine();
                        String[] values = input.split(" ");
                        Student student = new Student(values[0], values[1], values[2], values[3]);
                        studentList.addToEnd(student);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                        System.out.println("~~ failed: invalid student format");
                    }
                    break;
                case 8:
                    //~~ count
                    System.out.println("~~ student count: " + studentList.count());
                    break;
                case 9:
                    //~~ display successful
                    studentList.successfulStudents("ds").display();
                    System.out.println("~~ successful students of ds");
                    
                    studentList.successfulStudents("math").display();
                    System.out.println("~~ successful students of math");
                default:
                    break;
            }
        }
    
        scanner.close();
    }
}
