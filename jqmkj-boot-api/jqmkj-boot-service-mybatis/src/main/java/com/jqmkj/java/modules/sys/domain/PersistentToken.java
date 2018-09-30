package com.jqmkj.java.modules.sys.domain;

import com.jqmkj.java.common.persistence.annotation.ManyToOne;
import com.jqmkj.java.common.persistence.domain.GeneralEntity;
import com.jqmkj.java.util.annotation.SearchField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
/**
 * Persistent tokens are used by Spring Security to automatically log in users.
 *
 */
@TableName("jhi_persistent_token")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class PersistentToken extends GeneralEntity<PersistentAuditEvent> {

    private static final long serialVersionUID = 1L;

    private static final int MAX_USER_AGENT_LEN = 255;

    @SearchField
    @TableId(value = GeneralEntity.F_SQL_ID, type = IdType.UUID)
    protected String id; // 编号

    @TableField("series_")
    private String series;

    @JsonIgnore
    @NotNull
    @TableField("token_value")
    private String tokenValue;

    @TableField("token_date")
    private Date tokenDate;

    //an IPV6 address max length is 39 characters
    @Size(min = 0, max = 39)
    @TableField("ip_address")
    private String ipAddress;

    @TableField("user_agent")
    private String userAgent;

    /*** 用户 */
    @Length(min = 0, max = 2000)
    @TableField("user_id")
    private String userId;
    /*** 用户  */
    @ManyToOne(name = "user_id")
    @TableField(exist = false)
    @JsonIgnore
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Date getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        if (userAgent.length() >= MAX_USER_AGENT_LEN) {
            this.userAgent = userAgent.substring(0, MAX_USER_AGENT_LEN - 1);
        } else {
            this.userAgent = userAgent;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersistentToken that = (PersistentToken) o;

        if (!series.equals(that.series)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return series.hashCode();
    }

    @Override
    public String toString() {
        return "PersistentToken{" +
                "series='" + series + '\'' +
                ", tokenValue='" + tokenValue + '\'' +
                ", tokenDate=" + tokenDate +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                "}";
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
