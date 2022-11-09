import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.region.Region;
import org.junit.Test;

import java.util.List;

public class test_oss {
    @Test
    public void testoss(){
        String secretId="AKIDmNima7NM4VfX8SVGkyvb07yXpqb4GBqD";
        String secretKey="7xWoqb4HfmcASSBdoiMksjJejX5ML4Wi";

        COSCredentials cosCredentials=new BasicCOSCredentials(secretId,secretKey);

        Region region=new Region("ap-beijing");
        ClientConfig clientConfig=new ClientConfig(region);

        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient=new COSClient(cosCredentials,clientConfig);
        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            System.out.println(bucketName);
            String bucketLocation = bucketElement.getLocation();
        }
    }

}
