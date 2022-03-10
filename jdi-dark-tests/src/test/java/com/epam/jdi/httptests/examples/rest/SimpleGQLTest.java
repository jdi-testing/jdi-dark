package com.epam.jdi.httptests.examples.rest;

import com.epam.http.response.RestResponse;
import com.epam.jdi.services.GQLService;
import org.testng.annotations.Test;

import static com.epam.http.requests.ServiceInit.init;

public class SimpleGQLTest {

    @Test
    public void simpleTest() {
        String body = "{\"query\":\"{\\n "+
                " allPersons {\\n " +
                " name\\n  " +
                " age\\n " +
                " posts {\\n "+
                " title\\n    }\\n  }\\n}\",\"variables\":{}}";
        RestResponse resp = init(GQLService.class).simple.body(body)
                .call();
    }
}
