package com.beornot2be.docsEE.db;



import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.List;

public class UnfoundRelationshipsException extends RuntimeException implements GraphQLError {

    private final String message;

    public UnfoundRelationshipsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "The "+ message + " relationships haven't been found";
    }

    @Override
    public List<Object> getPath() {
        return null;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }
}