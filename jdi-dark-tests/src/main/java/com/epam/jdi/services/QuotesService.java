package com.epam.jdi.services;

import com.epam.http.annotations.GET;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

@ServiceDomain("https://quotes.rest/")
public class QuotesService {
    public static final String CATEGORY_PARAM = "category";

    public static final String LANGUAGE_PARAM = "language";
    public static final String DETAILED_PARAM = "detailed";

    @GET("/qod")
    public static RestMethod quoteOfTheDay;

    @GET("/qod/categories")
    public RestMethod quoteOfTheDayCategories;
    @GET("/qod/languages")
    public RestMethod quoteOfTheDayLanguages;

}
