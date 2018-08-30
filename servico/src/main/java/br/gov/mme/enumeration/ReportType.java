package br.gov.mme.enumeration;

public enum ReportType {
    XLS(".xls");

    private String type;

    ReportType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
