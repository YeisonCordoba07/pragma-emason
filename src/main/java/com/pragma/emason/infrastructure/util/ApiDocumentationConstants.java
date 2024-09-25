package com.pragma.emason.infrastructure.util;

public class ApiDocumentationConstants {


    private ApiDocumentationConstants() {
    }



    public static final String STATUS_200 = "200";
    public static final String STATUS_201 = "201";
    public static final String STATUS_400 = "400";
    public static final String STATUS_404 = "404";
    public static final String STATUS_500 = "500";

    public static final String OPERATION_SUMMARY_CREATE = "Create a new user account";

    public static final String OPERATION_DESCRIPTION_CREATE =
            "This endpoint creates a new user account based on the provided UserAccountRequestDTO. " +
                    "The account information must be valid, and if successfully created, a 201 status code is returned.";
    public static final String RESPONSE_201_DESCRIPTION = "User account created successfully";
    public static final String RESPONSE_400_DESCRIPTION = "Invalid input";
    public static final String RESPONSE_500_DESCRIPTION = "Server error";

    public static final String OPERATION_SUMMARY_GET_BY_ID = "Get role by ID";
    public static final String OPERATION_DESCRIPTION_GET_BY_ID =
            "This endpoint retrieves a role based on its ID. If the role is found, it returns the role details.";
    public static final String RESPONSE_200_DESCRIPTION = "Role found successfully";
    public static final String RESPONSE_404_DESCRIPTION = "Role not found";
    public static final String PATH_PARAM_DESCRIPTION = "ID of the role to be retrieved";



    // AUTHENTICATION CONTROLLER
    // Resumen de las operaciones
    public static final String LOGIN_SUMMARY = "Login a user";
    public static final String REGISTER_SUMMARY = "Register a user";

    // Descripciones de las operaciones
    public static final String LOGIN_DESCRIPTION = "Authenticates a user with username and password";
    public static final String REGISTER_DESCRIPTION = "Registers a new user in the system";

    // Respuestas de éxito
    public static final String SUCCESSFULLY_AUTHENTICATED = "Successfully authenticated";
    public static final String SUCCESSFULLY_REGISTERED = "Successfully registered";

    // Respuestas de error
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String INVALID_INPUT_DATA = "Invalid input data";
    public static final String SERVER_ERROR = "Server error";



    //SECURITY CONFIG
    // Authorities
    public static final String AUTHORITY_ADMIN = "ADMIN";
    public static final String AUTHORITY_AUX_BODEGA = "AUX_BODEGA";
    public static final String AUTHORITY_CLIENTE = "CLIENTE";

    // URL paths
    public static final String AUTH_PATH = "/auth/**";
    public static final String ROLE_PATH = "/role/**";
    public static final String CATEGORY_PATH = "/category/**";
    public static final String BRAND_PATH = "/brand/**";
    public static final String ITEM_PATH = "/item/**";
    public static final String SUPPLY_PATH = "/supply/**";
    public static final String CART_PATH = "/cart/**";
    public static final String USER_PATH = "/user/**";

}
