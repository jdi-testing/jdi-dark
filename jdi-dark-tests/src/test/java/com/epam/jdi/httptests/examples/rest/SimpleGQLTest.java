package com.epam.jdi.httptests.examples.rest;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.GQLService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class SimpleGQLTest {

    @BeforeClass
    public void before() {
        init(GQLService.class);
    }

    @Test
    public void simpleTest() {
        String body = "{\"query\":\"{\\n "+
                " allPersons {\\n " +
                " name\\n  " +
                " age\\n " +
                " posts {\\n "+
                " title\\n    }\\n  }\\n}\",\"variables\":{}}";
        RestResponse resp = GQLService.simple.body(body)
                .call();
    }
}
