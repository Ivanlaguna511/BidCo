package com.bidco.api_rest.dto.usuario;

public class StatsDTO {
    private int participatedBids;
    private int wonBids;
    private int createdBids;
    private int participatedDraws;
    private int wonDraws;
    private int createdDraws;

    public StatsDTO(int countParticipatedBidsByUsuarioId, int countWonBidsByUsuarioId, int countCreatedBidsByUsuarioId,
        int countParticipatedDrawsByUsuarioId, int countWonDrawsByUsuarioId, int countCreatedDrawsByUsuarioId) {
        this.participatedBids = countParticipatedBidsByUsuarioId;
        this.wonBids = countWonBidsByUsuarioId;
        this.createdBids = countCreatedBidsByUsuarioId;
        this.participatedDraws = countParticipatedDrawsByUsuarioId;
        this.wonDraws = countWonDrawsByUsuarioId;
        this.createdDraws = countCreatedDrawsByUsuarioId;
    }

    public int getParticipatedBids() {
        return participatedBids;
    }

    public void setParticipatedBids(int participatedBids) {
        this.participatedBids = participatedBids;
    }

    public int getWonBids() {
        return wonBids;
    }

    public void setWonBids(int wonBids) {
        this.wonBids = wonBids;
    }

    public int getCreatedBids() {
        return createdBids;
    }

    public void setCreatedBids(int createdBids) {
        this.createdBids = createdBids;
    }

    public int getParticipatedDraws() {
        return participatedDraws;
    }

    public void setParticipatedDraws(int participatedDraws) {
        this.participatedDraws = participatedDraws;
    }

    public int getWonDraws() {
        return wonDraws;
    }

    public void setWonDraws(int wonDraws) {
        this.wonDraws = wonDraws;
    }

    public int getCreatedDraws() {
        return createdDraws;
    }

    public void setCreatedDraws(int createdDraws) {
        this.createdDraws = createdDraws;
    }
}