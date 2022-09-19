package _Files_Common;

import Day02_APIChaining.API_Chaining;

public class AllPlacePayload {

//Payload body for Post Method
    public static String AddPlace (){
        return"{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }


    //Payload body for Update Method
    // "+classname.variablename+"
    public static String UpdatePlace(){
        return "{\n" +
                "\"place_id\":\""+ API_Chaining.placeId+"\",\n" +
                "\"address\":\""+API_Chaining.newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }

    //Payload body for Delete Method
    // "+classname.variablename+"
    public static String deleteAPIPayload(){
        return "{\n" +
                "        \"place_id\":\""+API_Chaining.placeId+"\"\n" +
                "    }";
    }



}
