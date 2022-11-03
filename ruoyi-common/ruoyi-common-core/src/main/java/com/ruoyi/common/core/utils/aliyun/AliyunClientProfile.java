package com.ruoyi.common.core.utils.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阿里云滑动验证请求工具类
 *
 * @author MoChen
 * @time 2022/11/3
 */
public class AliyunClientProfile {

    private static final String REGION_ID = "cn-hangzhou";

    private static final String ENDPOINT_AFS = "afs.aliyuncs.com";

    private static final String ENDPOINT_DM = "dm.aliyuncs.com";
    public static final String PRODUCT_AFS = "afs";
    public static final String PRODUCT_DM = "Dm";

    private String remoteIp = "0.0.0.0";

    private IAcsClient client;

    public AliyunClientProfile() throws ClientException {
        try {
            remoteIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private IAcsClient getClient(String accessKeyId, String accessKeySecret) throws ClientException {
        if (client != null) {
            return client;
        }
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            if (client != null) {
                return client;
            }
            IClientProfile profile = DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret);
            client = new DefaultAcsClient(profile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return client;
    }

    public IAcsClient getAfsClient(String accessKeyId, String accessKeySecret) throws ClientException{
        getClient(accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint(REGION_ID, PRODUCT_AFS, ENDPOINT_AFS);
        return client;
    }

    public IAcsClient getDmClient(String accessKeyId, String accessKeySecret) throws ClientException{
        getClient(accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint(REGION_ID, PRODUCT_DM, ENDPOINT_DM);
        return client;
    }

    public String getRemoteIp() {
        return remoteIp;
    }
}
