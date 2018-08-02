package br.gov.mme.enumeration;

public enum JasperFiles {
    PAGAMENTO("/reports/PagamentoReport.jasper");

    private String path;

    JasperFiles(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return this.path;
    }

}
