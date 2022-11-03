package com.ruoyi.gateway.config.aliyun;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.ruoyi.common.core.utils.aliyun.AliyunClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GatewayAliyunClientProfile extends AliyunClientProfile {

    @Value(value = "${aliyun.sdk.afs.accessKeyId}")
    private String accessKeyId;

    @Value(value = "${aliyun.sdk.afs.accessKeySecret}")
    private String accessKeySecret;

    @Value(value = "${aliyun.sdk.afs.appKey}")
    private String appKey;

    public GatewayAliyunClientProfile() throws ClientException {
        super();
    }

    public IAcsClient getAfsClient() throws ClientException {
        return super.getAfsClient(accessKeyId, accessKeySecret);
    }

    public IAcsClient getDmClient() throws ClientException {
        return super.getDmClient(accessKeyId, accessKeySecret);
    }

    public String getAppKey() {
        return appKey;
    }

}
