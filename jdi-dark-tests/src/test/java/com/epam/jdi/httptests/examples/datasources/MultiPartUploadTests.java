package com.epam.jdi.httptests.examples.datasources;

import com.epam.jdi.httptests.JettyService;
import com.epam.jdi.httptests.support.WithJetty;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestDataInfo.multiparts;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.tools.map.MapArray.map;
import static com.epam.jdi.tools.pairs.Pair.$;
import static org.hamcrest.Matchers.is;

public class MultiPartUploadTests extends WithJetty {

    @BeforeTest
    public void before() {
        init(JettyService.class);
    }

    @Test
    public void multiPartUploadingWorksForByteArrays() throws Exception {
        final byte[] bytes = IOUtils.toByteArray(getClass().getResourceAsStream("/car-records.xsd"));
        JettyService.postMultipartFile(bytes).assertThat()
            .statusCode(200)
            .body(is(new String(bytes)));
    }

    @Test
    public void multiPartUploadingWorksForMultipleStrings() {
        JettyService.postMultipartText.call(multiparts()
            .addAll(map($("Some text", "text"), $("Some other text", "text"))))
        .assertThat()
            .statusCode(200)
            .body(is("Some text,Some other text"));
    }
}
