package br.gov.mme.web.rest;

public class ProfileInfoVM {

    private String[] activeProfiles;

    private String ribbonEnv;

    protected ProfileInfoVM(String[] activeProfiles, String ribbonEnv) {
        this.activeProfiles = activeProfiles.clone();
        this.ribbonEnv = ribbonEnv;
    }

    public String[] getActiveProfiles() {
        return activeProfiles.clone();
    }

    public String getRibbonEnv() {
        return ribbonEnv;
    }
}