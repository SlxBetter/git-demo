package com.sunlx.learn.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.region.Region;
import com.sunlx.learn.utils.ConstantPropertiesUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileServiceImp  implements FileService{
    @Override
    public String upload(MultipartFile file) {
        String regionName = ConstantPropertiesUtil.REGION;
        String secretid = ConstantPropertiesUtil.SECRETID;
        String secretkey = ConstantPropertiesUtil.SECRETKEY;
        String buckname = ConstantPropertiesUtil.BUCKNAME;


        COSCredentials cosCredentials=new BasicCOSCredentials(secretid,secretkey);

        Region region=new Region(regionName);
        ClientConfig clientConfig=new ClientConfig(region);

        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient=new COSClient(cosCredentials,clientConfig);

        


        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            System.out.println(bucketName);
            String bucketLocation = bucketElement.getLocation();
        }

        return null;
    }
}
