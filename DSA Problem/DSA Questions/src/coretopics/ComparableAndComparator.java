package coretopics;

import java.sql.Array;
import java.util.*;

public class ComparableAndComparator {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(3, "a"));
        studentList.add(new Student(2, "c"));
        studentList.add(new Student(1, "b"));
        studentList.add(new Student(4, "d"));

//        Collections.sort(studentList);
        Collections.sort(studentList,new RollComparator());

        List<Integer> arr = Arrays.asList(10,17,8,200,5);
        Collections.sort(arr,new BigComparator());

        System.out.println(arr.stream().map(c -> c).toList());
    }
}

class Student implements Comparable<Student> {
    int roll;
    String name;

    public Student(int roll, String name) {
        this.roll = roll;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        if (this.roll > o.roll) return 1;
        else if (this.roll < o.roll) return -1;
        else return 0;
    }
}

class RollComparator implements Comparator<Student>
{
    @Override
    public int compare(Student o1, Student o2) {
        return o1.name.compareTo(o2.name);
    }
}

class BigComparator implements Comparator<Integer>
{
//    @Override
//    public int compare(Student o1, Student o2) {
//        return o1.name.compareTo(o2.name);
//    }
//
//    @Override
//    public int compare(String o1, String o2) {
//        return 0;
//    }

    @Override
    public int compare(Integer a, Integer b) {
        // Convert to string for concatenation
        String ab = a.toString() + b.toString();
        String ba = b.toString() + a.toString();
        // Compare in descending order
        return ba.compareTo(ab);
    }
}
