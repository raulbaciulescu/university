import java.io.File;

public class Constants {
    public static class Db {
        private static final File file = new File("D:\\Facultate\\MPP\\mpp-proiect-repository-raulbaciulescu\\travelAgency\\Persistence\\src\\main\\resources\\bd.config");
        public static String filename = file.getAbsolutePath();

        public enum Queries {
            ADD,
            DELETE,
            FIND_BY_ID,
            GET_ALL,
            FIND2,
            UPDATE
        }
        public enum Tables {
            FLIGHT,
            LOCATION,
            PURCHASE,
            USER,
            FLIGHT_NEW
        }
    }
}
