package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.results.Results;
import com.result_publishing_app.application.model.results.ResultsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultsMapper {
    public ResultsResponse modelToResponse(Results results) {
        if ( results == null ) {
            return null;
        }

        ResultsResponse resultsResponse = new ResultsResponse();

        resultsResponse.setId( results.getId() );
        resultsResponse.setCourse( results.getCourse() );
        resultsResponse.setSession(results.getSession());

        return resultsResponse;
    }

    public List<ResultsResponse> modelToResponse(List<Results> resultsList) {
        if ( resultsList == null ) {
            return null;
        }

        List<ResultsResponse> list = new ArrayList<>( resultsList.size() );
        for ( Results results : resultsList ) {
            list.add( modelToResponse( results ) );
        }

        return list;
    }
}
