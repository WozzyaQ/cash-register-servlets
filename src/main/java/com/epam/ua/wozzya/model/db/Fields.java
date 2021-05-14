package com.epam.ua.wozzya.model.db;

public class Fields {
    private Fields() {

    }
    public static final String ENTITY__ID = "id";

    public static final String ROLE__NAME = "role";

    public static final String WORKER__LOGIN = "login";
    public static final String WORKER__NAME = "name";
    public static final String WORKER__SURNAME = "surname";
    public static final String WORKER__EMAIL = "email";
    public static final String WORKER__PASSWORD = "password";

    public static final String ORDER__CREATED_BY_WORKER = "created_by_worker_id";
    public static final String ORDER__IS_CLOSED = "is_closed";
    public static final String ORDER__DATE_CLOSED = "date_closed";
    public static final String ORDER__DATE_CREATED = "date_created";
    public static final String ORDER__COMMENT = "comment";
    public static final String ORDER__LAST_EDITED = "last_edited";
    public static final String ORDER__ORDERED_BY = "ordered_by";
    public static final String ORDER__TOTAL = "total";

}
