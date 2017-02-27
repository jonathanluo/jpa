package org.mw.java7;

/**
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/enhancements.html#javase7
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/strings-switch.html
 */
public class StringsInSwitch {

    public String getTypeOfDayWithSwitchStatement(String dayOfWeekArg) {
         System.out.println("dayOfWeekArg: " + dayOfWeekArg);
         String typeOfDay;
         switch (dayOfWeekArg) {
             case "Monday":
                 typeOfDay = "Start of work week";
                 break;
             case "Tuesday":
             case "Wednesday":
             case "Thursday":
                 typeOfDay = "Midweek";
                 break;
             case "Friday":
                 typeOfDay = "End of work week";
                 break;
             case "Saturday":
             case "Sunday":
                 typeOfDay = "Weekend";
                 break;
             default:
                 throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
         }
         return typeOfDay;
    }

    public static void main(String[] argv) {
        StringsInSwitch test= new StringsInSwitch();
        System.out.println(test.getTypeOfDayWithSwitchStatement("Monday"));
        System.out.println(test.getTypeOfDayWithSwitchStatement("Sunday"));
        System.out.println(test.getTypeOfDayWithSwitchStatement("Tuesday"));
        System.out.println(test.getTypeOfDayWithSwitchStatement("Friday"));
    }
}
