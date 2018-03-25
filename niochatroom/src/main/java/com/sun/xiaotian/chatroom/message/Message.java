package com.sun.xiaotian.chatroom.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "messageType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextMessage.class, name = "text")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Message {

    private long clientId;

    private Date sendTime;

    private Date acceptTime;

    public Message() {

    }

    public Message(long clientId, Date sendTime, Date acceptTime) {
        this.clientId = clientId;
        this.sendTime = sendTime;
        this.acceptTime = acceptTime;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "clientId=" + clientId +
                ", sendTime=" + sendTime +
                ", acceptTime=" + acceptTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (clientId != message.clientId) return false;
        if (sendTime != null ? !sendTime.equals(message.sendTime) : message.sendTime != null) return false;
        return acceptTime != null ? acceptTime.equals(message.acceptTime) : message.acceptTime == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (acceptTime != null ? acceptTime.hashCode() : 0);
        return result;
    }
}
