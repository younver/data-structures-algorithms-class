package homeworks.student;

public class DoublyLinkedList<T extends Comparable> {
    DNode<T> head;

    public boolean isEmpty(){
        return head == null;
    }

    public void addToFront(T val){
        if (isEmpty()){
            head = new DNode<T>(val);
            return;
        }

        DNode<T> temp = new DNode<T>(val);
        temp.next = head;
        head.prev = temp;
        head = temp;
    }

    public void addToEnd(T val){
        DNode<T> newNode = new DNode<T>(val);

        if (isEmpty()){
            head = newNode;
            return;
        }

        DNode<T> iterator = head;
        while (iterator.next != null){
            iterator = iterator.next;
        }

        iterator.next = newNode;
        newNode.prev = iterator;
    }

    public void delete(T val){
        if (isEmpty()){
            System.out.println("~~ dlinkedlist is empty");
            return;
        }
        
        DNode<T> iterator = head;
        while (iterator != null){

            if (iterator.value.compareTo(val) == 0){
                if (iterator.prev != null)
                    iterator.prev.next = iterator.next;
                if (iterator.next != null)
                    iterator.next.prev = iterator.prev;
                break;
            }

            iterator = iterator.next;
        }
    }

    public T pop(){
        DNode<T> iterator = head;
        
        // empty case
        if (isEmpty()){
            System.out.println("~~ dlinkedlist is empty");
            return null;
        }

        // head case
        if (head.next == null){
            T val = head.value;
            head = null;
            return val;
        }

        // iterate 'til last
        while (iterator.next != null){
            iterator = iterator.next;
        }

        T val = iterator.value;
        iterator.prev.next = null;

        return val;
    }

    protected DNode<T> findNode(T val){
        DNode<T> iterator = head;

        while(iterator != null){
            if (iterator.value.compareTo(val) == 0){
                return iterator;
            }

            iterator = iterator.next;
        }

        return null;
    }

    protected int count(DNode<T> iterator, int count){
        if (iterator == null)
            return count;
        return count(iterator.next, ++count);
    }
    public int count(){
        return count(head, 0);
    }

    protected void swap(DNode<T> node1, DNode<T> node2){
        T temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }
    
    protected DNode<T> minNode(DNode<T> startNode){
        DNode<T> minNode = startNode;
        DNode<T> iterator = startNode;

        while (iterator != null){
            if (iterator.value.compareTo(minNode.value) == -1){
                minNode = iterator;
            }

            iterator = iterator.next;
        }

        return minNode;
    }

    protected void sort(DNode<T> iterator){
        if (iterator == null) return;
        DNode<T> minNode = minNode(iterator);
        swap(iterator, minNode);
        sort(iterator.next);
    }
    public void sort(){
        sort(head);
    }

    public void display(){
        DNode<T> iterator = head;
        while (iterator != null){
            iterator.display();

            iterator = iterator.next;
        }
    }

    //~~ student list methods
    public void addStudent(T val){
        if (!(head.value instanceof Student)) return;

        // check if id exists
        if (findBy("id", ((Student)val).id).count() < 1)
            addToEnd(val);
        else
            System.out.println("~~ failed: student id already exists");
    }

    private void deleteByID(int id, DNode<T> iterator){
        if (((Student)head.value).id == id){
            if (head.next != null){
                head.next.prev = null;
            }
            head = head.next;
        }

        if (((Student)iterator.value).id == id){
            if (iterator.prev != null)
                iterator.prev.next = iterator.next;
            if (iterator.next != null)
                iterator.next.prev = iterator.prev;
            return;
        }

        deleteByID(id, iterator.next);
    }
    public void deleteByID(int id){
        if (head.value instanceof Student)
            deleteByID(id, head);
    }
    
    private int countBy(String by, Object val){
        if (!(head.value instanceof Student))
            return -1;

        int count = 0;
        DNode<T> iterator = head;
        
        while (iterator != null){
            Student student = (Student) iterator.value;
            
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

    public DoublyLinkedList<Student> findBy(String by, Object val){
        if (!(head.value instanceof Student))
            return null;

        DoublyLinkedList<Student> output = new DoublyLinkedList<>();
        DNode<T> iterator = head;

        // itearate through list
        while (iterator != null){
            Student student = (Student) iterator.value;
            
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
 
    private DNode<T> minNodeBy(DNode<T> startNode, String by){
        if (!(head.value instanceof Student))
            return null;

        DNode<T> minNode = startNode;
        DNode<T> iterator = startNode;

        // iterate through students
        while (iterator != null){

            // compare by
            switch (by){
                // data structures
                case "ds":
                    if (((Student)iterator.value).dsGrade < ((Student)minNode.value).dsGrade)
                        minNode = iterator;
                    break;

                // math
                case "math":
                    if (((Student)iterator.value).mathGrade < ((Student)minNode.value).mathGrade)
                        minNode = iterator;
                    break;
            }

            // next student
            iterator = iterator.next;
        }

        return minNode;
    }
    private DNode<T> minNodeBy(String by){
        return minNodeBy(head, by);
    }
    
    public Student minBy(String by){
        if (!(head.value instanceof Student))
            return null;
        if (head == null)
            return null;
        return (Student)minNodeBy(by).value;
    }

    private void sortBy(DNode<T> iterator, String by){
        if (!(head.value instanceof Student)) return;
        if (iterator == null) return;
        DNode<T> minNode = minNodeBy(iterator, by);
        swap(iterator, minNode);
        sortBy(iterator.next, by);
    }
    public void sortBy(String by){
        sortBy(head, by);
    }

    public float avgBy(String by){
        if (!(head.value instanceof Student)) return -1;

        float sum = 0;
        DNode<T> iterator = head;

        // iterate through list
        while (iterator != null){
            Student student = (Student)iterator.value;

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

    public DoublyLinkedList<Student> successfulStudents(String courseName){
        if (!(head.value instanceof Student)) return null;

        DoublyLinkedList<Student> output = new DoublyLinkedList<>();
        DNode<T> iterator = head;

        while (iterator != null){
            Student student = (Student)iterator.value;

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
        if (!(head.value instanceof Student)) return;

        DNode<T> iterator = head;

        while (iterator != null){
            Student student = (Student)iterator.value;
            System.out.println(student.name);
            iterator = iterator.next;
        }
    }
}
