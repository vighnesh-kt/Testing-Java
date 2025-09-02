package com.v.testingrest.security;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

abstract class te{
    void run(){
        System.out.println("Bhagoo");
    }
}

class Employee implements Comparable<Employee>{

    private int id;
    private String name;
    private char gender;
    private Date dob;
    private String city;
    private String designation;
    private Date joiningDate;
    private double salary;

    public Employee(int id, String name, char gender, Date dob, String city, String designation,
                    Date joiningDate, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.city = city;
        this.designation = designation;
        this.joiningDate = joiningDate;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", city=" + city
                + ", designation=" + designation + ", joiningDate=" + joiningDate + ", salary=" + salary + "]";
    }

    @Override
    public int compareTo(Employee o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.id, o.id);
    }

//	@Override
//	public int compareTo(Employee o) {
//
//		if(this.id>)
//	}

}


public class Temp {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Employee e1 = new Employee(101, "Alice", 'F',
                sdf.parse("15-05-1995"), "Mumbai", "Developer",
                sdf.parse("01-07-2020"), 55000);

        Employee e2 = new Employee(102, "Bob", 'M',
                sdf.parse("10-12-1990"), "Pune", "Manager",
                sdf.parse("15-03-2015"), 85000);

        Employee e3 = new Employee(103, "Charlie", 'M',
                sdf.parse("22-08-1992"), "Delhi", "Tester",
                sdf.parse("20-09-2018"), 45000);

        Employee e4 = new Employee(104, "Diana", 'F',
                sdf.parse("01-01-1998"), "Bangalore", "HR",
                sdf.parse("10-11-2021"), 40000);

        Employee e5 = new Employee(105, "Ethan", 'M',
                sdf.parse("05-07-1993"), "Hyderabad", "Team Lead",
                sdf.parse("25-04-2017"), 75000);


//        List<String> l=new ArrayList<>(Arrays.asList("123","23","v","v",null));
//
//        Predicate<String>p=lst->lst.contains("v");
//        System.out.println(filterList(l,p));
//
//        Consumer<String> c=s-> System.out.println(s);
//        consu(c,l);
//
//        Supplier<String>sup=()-> UUID.randomUUID().toString();
//        suplier(sup);
//
//        Function<String,Integer> funct=st->st.length();
//        fun(funct,l);

//        Stream.of(5,5,5,5,5)
//        .filter(s->s%2==0).forEach(s-> System.out.println(s));
//
//        Map<Integer,String>maps=new HashMap<>();
//        maps.put(1, "Raj");
//        maps.put(2, "Jay");
//
//        maps.entrySet().stream().forEach((k)-> System.out.println(k.getValue()));
//        IntSummaryStatistics sum1 = Stream.of(5, 5, 5, 5).mapToInt(s -> s).summaryStatistics();
//        System.out.println(sum1);
//
//        String a="1231deada";

        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5);

        employees.stream().collect(Collectors.groupingBy(s -> s.getGender(), Collectors.counting()));
//        Map<String, Long> collect = employees.stream().
//                collect(Collectors.groupingBy(group->group.getDesignation(),Collectors.counting()));
//        collect.forEach((s,t)->System.out.println(s+" "+t));

//        System.out.println(collect);







    }
    public static List<String> filterList(List<String> l,Predicate<String> p) {
        List<String> newList=new ArrayList<>();
        for(String s:l) {
            Optional<String> t= Optional.of(s);
            System.out.println(t);
            if(p.test(s))newList.add(s);
        }
        return newList;


    }

    static void consu(Consumer<String> s,List<String> t){
        for(String p:t){
            s.accept(p);
        }
    }
    static void suplier(Supplier<String>s){
        for (int i = 0; i < 10; i++) {
            System.out.println(s.get());
        }
    }
    static void fun(Function<String,Integer> fu,List<String> lu){
        for(String s:lu){
            System.out.println(fu.apply(s));
        }
    }
}
