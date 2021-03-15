public class Error {

    public static void Fatal(String msg){
        System.err.println(msg);
        System.exit(1);
    }

}
