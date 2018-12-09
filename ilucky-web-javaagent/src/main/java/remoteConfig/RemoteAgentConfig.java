package remoteConfig;

import java.util.ArrayList;
import java.util.List;

public class RemoteAgentConfig {
   
    private String agentId;
    private List<RemoteKeyTransactionConfig> keyTranList = new ArrayList<RemoteKeyTransactionConfig>();
    private RemoteBusiIdConfig busiId = new RemoteBusiIdConfig();
    
    public String getAgentId() {
        return agentId;
    }
    public List<RemoteKeyTransactionConfig> getKeyTranList() {
        return keyTranList;
    }
    public RemoteBusiIdConfig getBusiId() {
        return busiId;
    }
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
    public void setKeyTranList(List<RemoteKeyTransactionConfig> keyTranList) {
        this.keyTranList = keyTranList;
    }
    public void setBusiId(RemoteBusiIdConfig busiId) {
        this.busiId = busiId;
    }
}

