package remoteConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class MainTest {

    public static void main(String[] args) {
        
        Boolean resultValue = null;
        System.out.println(resultValue);
        
        RemoteAgentConfig remoteAgentConfig = new RemoteAgentConfig();
        remoteAgentConfig.setAgentId(UUID.randomUUID().toString());
        
        List<RemoteKeyTransactionConfig> keyTranList = new ArrayList<RemoteKeyTransactionConfig>();
        for(int j=0; j<3; j++) {
            RemoteKeyTransactionConfig keyTran = new RemoteKeyTransactionConfig();
            keyTran.setUrl("/userController/test/*");
            keyTran.setUrls(new String[]{"p1", "p2"});
            keyTran.setHeader(new String[]{"h1", "h2"});
            keyTran.setCookie(new String[]{"c1", "c2"});
            keyTran.setSession(new String[]{"s1", "s2"});
            keyTran.setBody("body");
            keyTranList.add(keyTran);
        }
        remoteAgentConfig.setKeyTranList(keyTranList);
        
        RemoteBusiIdConfig busiId = new RemoteBusiIdConfig();
        busiId.setUrls(new String[]{"p1", "p2"});
        busiId.setHeader(new String[]{"h1", "h2"});
        busiId.setCookie(new String[]{"c1", "c2"});
        busiId.setSession(new String[]{"s1", "s2"});
        busiId.setBody("body");
        remoteAgentConfig.setBusiId(busiId);
            
        String json = JSON.toJSONString(remoteAgentConfig);
        System.out.println(json);
        
        try {
            json = IOUtils.toString(new FileInputStream(new File("E://test.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RemoteAgentConfig c = JSON.parseObject(json, new TypeReference<RemoteAgentConfig>() {});
        System.out.println(c.getBusiId().getBody());
    }
}
