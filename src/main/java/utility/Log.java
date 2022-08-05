package utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static final Logger log = LogManager.getLogger(Log.class);
    public static void startTestCase (String sTestCaseName){
        log.info   ("-----------------------------------------------------------------------------------------------");
        log.info("**************  "+"\033[42;30;4m"+sTestCaseName+"\033[0m"+"************");
    }
    public static void endTestCase(String sTestCaseName){
        log.info("********************* "+"\033[42;30;4m"+sTestCaseName+"\033[0m"+"********************");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    public static void info(String message){
        log.info("\033[42;30;4m"+message+"\033[0m");
    }
    public static void debug(String message){
        log.debug("\033[42;30;4m"+message+"\033[0m");
    }
    public static void error(String message){
        log.error("\033[41;30;4m"+message+"\033[0m");
    }
    public static void warn(String message){
        log.warn(message);
    }


    public static void main(String[] args) {

        Log.info("INFO MESSAGE");
        Log.debug("debug MESSAGE");
        Log.warn("warn MESSAGE");
        Log.error("error MESSAGE");
        Log.endTestCase("test end MESSAGE");
    }
}
