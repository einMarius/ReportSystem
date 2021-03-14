//File created in 2021.

package me.marius.report;

import java.util.Arrays;

public enum ReportCause {

    Hacking, Teaming, Bugusing, Boosting, Name, Skin, Clan, Trolling, Hunting;

    public static String getReportCause(String cause){

        for(ReportCause rc : values()){
            if(cause.equalsIgnoreCase(rc.toString()))
                return rc.toString();
        }
        return null;
    }
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
