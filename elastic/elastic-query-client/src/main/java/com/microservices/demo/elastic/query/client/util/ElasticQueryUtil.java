package com.microservices.demo.elastic.query.client.util;

import com.microservices.demo.elastic.model.index.IndexModel;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.client.elc.Queries;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.lang.annotation.Native;
import java.util.Collections;

@Component
public class ElasticQueryUtil<T extends IndexModel> {

    public Query getSearchQueryById(String id){
        return NativeQuery.builder()
                .withIds(Collections.singleton(id))
                .build();
    }
    public Query getSearchQueryByFieldText(String field, String text){
        return NativeQuery.builder()
                .withQuery(Queries.matchQueryAsQuery(field,text,null,null))
                .build();
    }

    public Query getSearchQueryForAll(){
        return NativeQuery.builder()
                .withQuery(Queries.matchAllQueryAsQuery())
                .build();
    }
}
