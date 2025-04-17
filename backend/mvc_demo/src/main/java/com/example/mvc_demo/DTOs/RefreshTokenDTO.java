package com.example.mvc_demo.DTOs;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RefreshTokenDTO extends BaseDTO {
    private String token;
    private UUID userId;
    private String deviceId;
    private String ipAddress;
    private String userAgent;
    private boolean revoked;
    private Date expired;

    public RefreshTokenDTO() {
    }

    public RefreshTokenDTO(String token, UUID userId, String deviceId, String ipAddress, String userAgent, boolean revoked, Date expired) {
        this.token = token;
        this.userId = userId;
        this.deviceId = deviceId;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.revoked = revoked;
        this.expired = expired;    }
    public void setUser(UserDTO user){
        this.userId = user.getId();
    }
    public void setExpiryDate(Date expiryDate) {
        this.expired = expiryDate;
    }
    public Date getExpiryDate() {
        return this.expired;
    }

}
