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
    


    //SECURITY CONFIG
    // Authorities
    public static final String AUTHORITY_ADMIN = "ADMIN";
    public static final String AUTHORITY_AUX_BODEGA = "AUX_BODEGA";


    // URL paths

    public static final String CATEGORY_PATH = "/category/**";
    public static final String BRAND_PATH = "/brand/**";
    public static final String ITEM_PATH = "/item/**";


    // ITEM REST CONTROLLER
    public static final String OPERATION_SUMMARY_GET_ALL_ITEMS = "Retrieve all items with pagination, sorting, and filtering";
    public static final String OPERATION_DESCRIPTION_GET_ALL_ITEMS = "Fetches a paginated list of items from the database. The results can be sorted by a specific field, filtered by table, and ordered in ascending or descending order.";
    public static final String RESPONSE_200_DESCRIPTION_GET_ALL_ITEMS = "Successfully retrieved the list of items";

    public static final String OPERATION_SUMMARY_INCREASE_ITEM_QUANTITY = "Increase Item Quantity";
    public static final String OPERATION_DESCRIPTION_INCREASE_ITEM_QUANTITY = "Increases the quantity of an item by the specified amount";
    public static final String RESPONSE_201_DESCRIPTION_INCREASE_ITEM = "Item quantity increased successfully";
    public static final String RESPONSE_400_DESCRIPTION_INCREASE_ITEM = "Invalid input or item not found";



    // CATEGORY REST CONTROLLER
    public static final String OPERATION_SUMMARY_SAVE_CATEGORY = "Save a new category";
    public static final String OPERATION_DESCRIPTION_SAVE_CATEGORY = "Creates a new category and saves it to the database.";
    public static final String RESPONSE_201_DESCRIPTION_SAVE_CATEGORY = "Category successfully created";

    public static final String OPERATION_SUMMARY_GET_CATEGORY_BY_NAME = "Get a category by name";
    public static final String OPERATION_DESCRIPTION_GET_CATEGORY_BY_NAME = "Fetches the details of a specific category by its name.";
    public static final String RESPONSE_200_DESCRIPTION_GET_CATEGORY_BY_NAME = "Category found";

    public static final String OPERATION_SUMMARY_GET_ALL_CATEGORIES = "Get all categories with pagination and sorting";
    public static final String OPERATION_DESCRIPTION_GET_ALL_CATEGORIES = "Fetches all categories with optional pagination and sorting parameters.";
    public static final String RESPONSE_200_DESCRIPTION_GET_ALL_CATEGORIES = "Categories retrieved successfully";

    public static final String RESPONSE_404_DESCRIPTION_CATEGORY = "Category not found";


    // BRAND REST CONTROLLER
    public static final String OPERATION_SUMMARY_CREATE_BRAND = "Create a new brand";
    public static final String OPERATION_DESCRIPTION_CREATE_BRAND = "Saves a new brand in the database.";
    public static final String RESPONSE_201_DESCRIPTION_CREATE_BRAND = "Brand created successfully";


    public static final String OPERATION_SUMMARY_GET_BRAND_BY_NAME = "Get brand by name";
    public static final String OPERATION_DESCRIPTION_GET_BRAND_BY_NAME = "Fetches a brand's details by its name.";
    public static final String RESPONSE_200_DESCRIPTION_GET_BRAND_BY_NAME = "Brand found";
    public static final String RESPONSE_404_DESCRIPTION_BRRAND = "Brand not found";

    public static final String OPERATION_SUMMARY_GET_ALL_BRANDS = "Retrieve all brands";
    public static final String OPERATION_DESCRIPTION_GET_ALL_BRANDS = "Returns a paginated list of brands, sorted by a specific field.";
    public static final String RESPONSE_200_DESCRIPTION_GET_ALL_BRANDS = "Successful operation, list of brands returned";


}
