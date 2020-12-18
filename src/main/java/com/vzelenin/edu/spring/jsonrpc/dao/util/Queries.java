package com.vzelenin.edu.spring.jsonrpc.dao.util;

public interface Queries {

    String CREATE_NEW_USER = "INSERT INTO public.\"user\" values (:username,:firstname,:password)";
    String SELECT_USERS_COUNT = "SELECT COUNT(1) FROM public.\"user\"";
    String FIND_USER_BY_USER_NAME = "SELECT \"user_name\", \"first_name\", \"password\" FROM public.\"user\"\n" +
            "where \"user_name\" = ?";
}
