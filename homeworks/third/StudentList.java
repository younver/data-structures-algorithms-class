package homeworks.third;

import structures.linkedlist.DoublyLinkedList;
import structures.node.DNode;

public class StudentList extends DoublyLinkedList<Student>{

    @Override
    public void addToEnd(Student val){
        // check if id exists
        if (findBy("id", val.id).count() < 1)
            super.addToEnd(val);
        else
            System.out.println("~~ failed: student id already exists");
    }

    public void delete(int id, DNode<Student> iterator){

        if (head.value.id == id){
            if (head.next != null){
                head.next.prev = null;
            }
            head = head.next;
        }

        if (iterator.value.id == id){
            if (iterator.prev != null)
                iterator.prev.next = iterator.next;
            if (iterator.next != null)
                iterator.next.prev = iterator.prev;
            return;
        }

        delete(id, iterator.next);
    }
    public void delete(int id){
        delete(id, head);
    }
    
    public int countBy(String by, Object val){
        int count = 0;
        DNode<Student> iterator = head;
        
        while (iterator != null){
            Student student = iterator.value;
            
            // compare by
            switch (by){
                // data structures
                case "ds":
                    if (student.dsGrade == (Integer)val)
                        count++;
                    break;
    
                // math
                case "math":
                    if (student.mathGrade == (Integer)val)
                        count++;
                    break;
    
                // all count
                default:{
                    count++;
                }
            }

            iterator = iterator.next;
        }

        return count;
    }

    public StudentList findBy(String by, Object val){
        StudentList output = new StudentList();
        DNode<Student> iterator = head;

        // itearate through list
        while (iterator != null){
            Student student = iterator.value;
            
            // compare by
            switch (by){
                // data structures
                case "ds":
                    if (student.dsGrade == (Integer)val)
                        output.addToEnd(student);
                    break;
    
                // math
                case "math":
                    if (student.mathGrade == (Integer)val)
                        output.addToEnd(student);
                    break;
                case "id":
                    if (student.id == (Integer)val)
                        output.addToEnd(student);
            }

            iterator = iterator.next;
        }

        return output;
    }
 
    protected DNode<Student> minNodeBy(DNode<Student> startNode, String by){
        DNode<Student> minNode = startNode;
        DNode<Student> iterator = startNode;

        // iterate through students
        while (iterator != null){

            // compare by
            switch (by){
                // data structures
                case "ds":
                    if (iterator.value.dsGrade < minNode.value.dsGrade)
                        minNode = iterator;
                    break;

                // math
                case "math":
                    if (iterator.value.mathGrade < minNode.value.mathGrade)
                        minNode = iterator;
                    break;
            }

            // next student
            iterator = iterator.next;
        }

        return minNode;
    }
    protected DNode<Student> minNodeBy(String by){
        return minNodeBy(head, by);
    }
    
    public Student minBy(String by){
        if (head == null)
            return null;
        return minNodeBy(by).value;
    }

    protected void sortBy(DNode<Student> iterator, String by){
        if (iterator == null) return;
        DNode<Student> minNode = minNodeBy(iterator, by);
        swap(iterator, minNode);
        sortBy(iterator.next, by);
    }
    public void sortBy(String by){
        sortBy(head, by);
    }

    public float avgBy(String by){
        float sum = 0;
        DNode<Student> iterator = head;

        // iterate through list
        while (iterator != null){
            Student student = iterator.value;

            // sum
            switch (by) {
                case "ds":
                    sum += student.dsGrade;
                    break;
                case "math":
                    sum += student.mathGrade;
                    break;
            }

            iterator = iterator.next;
        }

        return (sum / count());
    }

    public StudentList successfulStudents(String courseName){
        StudentList output = new StudentList();
        DNode<Student> iterator = head;
        float avg;

        while (iterator != null){
            Student student = iterator.value;

            switch (courseName) {
                case "ds":
                    if (student.dsGrade > avgBy(courseName))
                        output.addToEnd(student);
                    break;
                case "math":
                    if (student.mathGrade > avgBy(courseName))
                        output.addToEnd(student);
                    break;
            }

            iterator = iterator.next;
        }

        return output;
    }

    public void displayNames(){
        DNode<Student> iterator = head;

        while (iterator != null){
            Student student = iterator.value;
            System.out.println(student.name);
            iterator = iterator.next;
        }
    }
}
