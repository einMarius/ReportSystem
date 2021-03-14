//File created in 2021.

package me.marius.report;

public enum ReportCause {

    Hacking, Teaming, Bugusing, Boosting, Name, Skin, Clan, Trolling, Hunting;

    public static String getReportCause(String cause){

        for(ReportCause rc : values()){
            if(cause.equalsIgnoreCase(rc.toString()))
                return rc.toString();
        }
        return null;
    }

    public static ReportCause[] enumValue = values();
}
