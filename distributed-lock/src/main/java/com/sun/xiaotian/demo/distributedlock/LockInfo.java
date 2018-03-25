package com.sun.xiaotian.demo.distributedlock;

public class LockInfo {

    /**
     * 要锁定的资源
     */
    private String resourcesId;

    private String macAddress;
    private String ipAddress;
    private String jvmId;
    private Long threadId;

    /**
     * 锁的过期时间，可以设置的尽量大，确保每一个操作搜能在过期时间之内完成
     * 但同时也要足够的小，以保证获取锁的系统宕机之后，其他系统不用回等待很长时间
     * 单位:秒
     */
    private int expireDate;

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getJvmId() {
        return jvmId;
    }

    public void setJvmId(String jvmId) {
        this.jvmId = jvmId;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public int getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(int expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LockInfo lockInfo = (LockInfo) o;

        if (resourcesId != null ? !resourcesId.equals(lockInfo.resourcesId) : lockInfo.resourcesId != null)
            return false;
        if (macAddress != null ? !macAddress.equals(lockInfo.macAddress) : lockInfo.macAddress != null) return false;
        if (ipAddress != null ? !ipAddress.equals(lockInfo.ipAddress) : lockInfo.ipAddress != null) return false;
        if (jvmId != null ? !jvmId.equals(lockInfo.jvmId) : lockInfo.jvmId != null) return false;
        return threadId != null ? threadId.equals(lockInfo.threadId) : lockInfo.threadId == null;
    }

    @Override
    public int hashCode() {
        int result = macAddress != null ? macAddress.hashCode() : 0;
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (jvmId != null ? jvmId.hashCode() : 0);
        result = 31 * result + (threadId != null ? threadId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LockInfo{" +
                "resourcesId='" + resourcesId + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", jvmId='" + jvmId + '\'' +
                ", threadId=" + threadId +
                '}';
    }
}
