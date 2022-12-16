package com.ntloc.customer;

public class CustomerConstant {

    public static final String URI_REST_API_VERSION_CUSTOMER = "/api/v1/customer";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";

    public static final String PREFERRED_USERNAME = "preferred_username";
    public static final String AUTHORIZATION = "authorization";
    public static final String AUTHENTICATION_FAILED = "Authentication failed";

    public class RoleConstant {

        public static final String USER = "user";
        public static final String ADMIN = "admin";
        public static final String MANAGER = "manager";

        public static final String HAS_AUTHORITY_USER = "hasAuthority('" + USER + "')";
        public static final String HAS_AUTHORITY_ADMIN = "hasAuthority('" + ADMIN + "')";
        public static final String HAS_AUTHORITY_MANAGER = "hasAuthority('" + MANAGER + "')";

        public static final String HAS_ANY_AUTHORITY_USER_MANAGER = "hasAnyAuthority('" + USER + "','" + MANAGER + "')";
        public static final String HAS_ANY_AUTHORITY_ADMIN_MANAGER = "hasAnyAuthority('" + ADMIN + "','" + MANAGER + "')";

    }

}
