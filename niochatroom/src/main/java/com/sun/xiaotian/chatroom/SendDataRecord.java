package com.sun.xiaotian.chatroom;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 记录服务器已经给各个客户端发送消息的长度信息
 */

public class SendDataRecord {

    private final static Map<Long, ClientRecord> recordsMap = new ConcurrentHashMap<>();

    public void addOrUpdate(Long clientId, int index, Date acceptDate) {
        recordsMap.put(clientId, new ClientRecord(clientId, index, acceptDate));
    }

    public int getIndex(Long clientId) {
        ClientRecord clientRecord = recordsMap.get(clientId);
        return clientRecord == null ? 0 : clientRecord.getCurrIndex();
    }

    public long getCurrAcceptDate(Long clientId) {
        ClientRecord clientRecord = recordsMap.get(clientId);
        return clientRecord == null ? 0 : clientRecord.getCurrAcceptDate().getTime();
    }

    public boolean isEmpty() {
        return recordsMap.isEmpty();
    }

    /**
     * 记录客户端Id和最后发送消息的Index，以及对应的消息的接受时间
     */
    class ClientRecord {

        private long clientId;
        private int currIndex;
        private Date currAcceptDate;

        public ClientRecord(long clientId, int position, Date currAcceptDate) {
            this.clientId = clientId;
            this.currIndex = position;
            this.currAcceptDate = currAcceptDate;
        }

        public long getClientId() {
            return clientId;
        }

        public void setClientId(long clientId) {
            this.clientId = clientId;
        }

        public int getCurrIndex() {
            return currIndex;
        }

        public void setCurrIndex(int currIndex) {
            this.currIndex = currIndex;
        }

        public Date getCurrAcceptDate() {
            return currAcceptDate;
        }

        public void setCurrAcceptDate(Date currAcceptDate) {
            this.currAcceptDate = currAcceptDate;
        }
    }
}
