package dev5.chermenin.rest.security.model;

/**
 * Created by Ancarian on 18.02.2018.
 */
public class TokenPayload {
    private Long userId;
    private long exp;

    public TokenPayload() {
    }

    public TokenPayload(final Long userId, final long exp) {
        this.userId = userId;
        this.exp = exp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }
}