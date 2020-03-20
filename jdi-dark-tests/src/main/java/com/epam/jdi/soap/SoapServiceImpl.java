package com.epam.jdi.soap;

import javax.jws.WebService;
import java.util.LinkedHashMap;
import java.util.Map;

@WebService(endpointInterface = "com.epam.jdi.soap.SoapServiceInterface")
public class SoapServiceImpl implements SoapServiceInterface {
    private Map<Integer, Student> students
            = new LinkedHashMap<Integer, Student>();

    public String hello(String name) {
        return "Hello " + name;
    }

    public String helloStudent(Student student) {
        students.put(students.size() + 1, student);
        return "Hello " + student.getName();
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }
}