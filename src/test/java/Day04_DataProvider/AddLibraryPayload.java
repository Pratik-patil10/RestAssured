package Day04_DataProvider;

public class AddLibraryPayload {
    public static String AddLibrary(String isbn, String aisle) {

        return "{\r\n"
                + "\"name\":\"Learn Appium Automation with Java\",\r\n"
                + "\"isbn\":\""+isbn+"\",\r\n"
                + "\"aisle\":\""+aisle+"\",\r\n"
                + "\"author\":\"John foer\"\r\n"
                + "}\r\n"
                + "";
    }

    public static String DeleteBook() {
        return "{\r\n"
                + "    \"ID\": \""+DynamicJsonDemo.bookId+"\"\r\n"
                + "}";
    }

    public static String DeleteBookD(String id) {
        return "{\r\n"
                + "    \"ID\": \""+id+"\"\r\n"
                + "}";
    }



}
