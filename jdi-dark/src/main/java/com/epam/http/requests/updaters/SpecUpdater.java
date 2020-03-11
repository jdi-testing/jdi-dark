package com.epam.http.requests.updaters;

import com.epam.http.requests.RequestData;
import com.epam.jdi.tools.func.JFunc1;
import com.epam.jdi.tools.func.JFunc2;
import com.epam.jdi.tools.map.MapArray;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.epam.jdi.tools.LinqUtils.map;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public abstract class SpecUpdater<A extends Annotation, T> {
    JFunc1<Collection<T>, RequestData> store;
    JFunc1<A, T> transform;
    JFunc2<String, String, T> transformNameValue;
    public SpecUpdater(JFunc1<Collection<T>, RequestData> store, JFunc1<A, T> transform, JFunc2<String, String, T> transformNameValue) {
        this.store = store;
        this.transform = transform;
        this.transformNameValue = transformNameValue;
    }
    public RequestData add(A annotation) {
        return add(transform.execute(annotation));
    }
    public RequestData add(T types) {
        return addAll(singletonList(types));
    }
    public RequestData addAll(A... annotations) {
        return addAll(map(annotations, a -> transform.execute(a)));
    }
    public RequestData addKey(String name) {
        return add(name, "");
    }
    public RequestData add(String name, String... values) {
        List<T> list = map(values, value -> transformNameValue.execute(name, value));
        return addAll(list);
    }

    public RequestData addAll(Collection<T> list) {
        return store.execute(list);
    }
    public RequestData addAll(Object[][] array2D) {
        List<T> list = map(array2D, pair -> transformNameValue.execute(pair[0].toString(), pair[1].toString()));
        return addAll(list);
    }
    public RequestData addAll(Map map) {
        List<T> list = new MapArray<>(map).map((key, value) -> transformNameValue.execute(key.toString(), value.toString()));
        return addAll(list);
    }
    public RequestData addAll(T... array) {
        return addAll(asList(array));
    }

}
