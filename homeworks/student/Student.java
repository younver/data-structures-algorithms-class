package homeworks.student;

public class Student implements Comparable{
    public int id;
    public String name;
    public int mathGrade;
    public int dsGrade;

    public Student(int id, String name, int mathGrade, int dsGrade){
        this.id = id;
        this.name = name;
        this.mathGrade = mathGrade;
        this.dsGrade = dsGrade;
    }
    public Student(String id, String name, String mathGrade, String dsGrade){
        this(Integer.parseInt(id), name, Integer.parseInt(mathGrade), Integer.parseInt(dsGrade));
    }

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;

        if (this.mathGrade + this.dsGrade > other.mathGrade + other.dsGrade)
            return 1;
        if (this.mathGrade + this.dsGrade < other.mathGrade + other.dsGrade)
            return -1;
        return 0;
    }

    public String toString(){
        return "" + id + " " + name + " " + mathGrade + " " + dsGrade;
    }
}
