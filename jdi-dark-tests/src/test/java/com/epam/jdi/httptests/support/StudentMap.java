package com.epam.jdi.httptests.support;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "StudentMap")
public class StudentMap {
    private List<StudentEntry> entries = new ArrayList<StudentEntry>();

    @XmlElement(nillable = false, name = "entry")
    public List<StudentEntry> getEntries() {
        return entries;
    }

    @XmlType(name = "StudentEntry")
    public static class StudentEntry {
        private Integer id;
        private Student student;

        public void setStudent(Student student) {
            this.student = student;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Student getStudent() {
            return student;
        }

        public Integer getId() {
            return id;
        }
    }
}