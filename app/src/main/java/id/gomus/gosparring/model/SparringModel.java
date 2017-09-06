package id.gomus.gosparring.model;

/**
 * Created by idn on 9/4/2017.
 */

public class SparringModel {
    String teamA;
    String kotaA;
    String teamB;
    String kotaB;
    String skorA;
    String skorB;
    String lapangan;
    String hari;
    String waktu;
    String status;


    public SparringModel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getKotaA() {
        return kotaA;
    }

    public void setKotaA(String kotaA) {
        this.kotaA = kotaA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getKotaB() {
        return kotaB;
    }

    public void setKotaB(String kotaB) {
        this.kotaB = kotaB;
    }

    public String getSkorA() {
        return skorA;
    }

    public void setSkorA(String skorA) {
        this.skorA = skorA;
    }

    public String getSkorB() {
        return skorB;
    }

    public void setSkorB(String skorB) {
        this.skorB = skorB;
    }

    public String getLapangan() {
        return lapangan;
    }

    public void setLapangan(String lapangan) {
        this.lapangan = lapangan;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
