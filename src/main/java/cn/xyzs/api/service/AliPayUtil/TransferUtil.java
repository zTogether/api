package cn.xyzs.api.service.AliPayUtil;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;

public class TransferUtil {

    //appid
    private static final String APP_ID = "2016082600316638";
    //测试网关
    private static final String SERVER_URL = "https://openapi.alipaydev.com/gateway.do";
    //正式网关
    //private static final String SERVER_URL = "https://openapi.alipay.com/gateway.do";
    //私钥
    private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsNBoCLtfNFMuh4cnRyNS7V4PUR3xs6WnoFp9QkIoa8QZpnP39cchS+B9VG2tgHDt61af9u415CYi9XDA/4lS+5WSP2iVCvTFAufhGO5GaWjjt+CK5Cladosvh7yX+kIE+psxGPf/Uhk4Y62T32SlUL0WTGvJciR0roMrU7Crfd4imuShSlgh7TEdXxazkrtfsJBxp3O2GR5lNquJNsmEzF1w6LBIsJvqxzPRBA1b2hdIlRR99zRpbvKjVdU528T+oaOoU1k7pRPAJVqzDOxnRvTEWG647daDk4IYL40g/uJ08Emvw97oyJtiy5GFQ2UFWKAXJ8fcb1+J5RL9dnW4rAgMBAAECggEBAIghJ+5R2YblyZRPSZvthz4SEMTfPh1a573xJEAsAwEJSGh1EtQn/pSYUSyWYGu55ctU21hZy5zDTLVlCz91TEkcTkLp0ErSBP2GBh5/OLssV5THc6uT4KT87O/OfdqfoYxFj3Z3zKXVBifs7gSAR4ulUvrXcoB0Y/r5QV7Q05OTOpRfnfV9rzWwUG6QEE7NyC2mfKHejJLIvixXHJQ2DZ092x2p9OKWn6a220p9C+mVbKUIo9TAlfOud6bRP+g0YQpltufJJE16TASzvTu0Sk3x4rWgqYIZzZI2iB0i9zXwwFNsyUkB92iYqQaxECjGbrjwFwRsnDYgClioneDphgECgYEA96fR68iyTq3bb6LrQGT4T5lqkmZum6EWbfNF60nzEzGfru/f8NMHEriYQoXVq1FdDs8lCRnz1n+IrvytnRku7etXBNvvaUDp4KR3C0rXQx7qURJHiHDafz/03nQ24njVAs4NhfIXgMfvWmzr/jyeznHYGujYy2tosJDvWVzBmUkCgYEAsgF2Px9WLuAaANLk3Y/Z1p3CqM5TXiSm4vyuSiGKUyDgjL06K7jjzwDXtVyAQfyYISFdjaCLu3Vm1dkNZDwjC2FTWgIIhHVyfo8X6afl8ZF6j35bWLFyu5iRFCAUBnbXAGXj3ftXckln8yD/tLNYiLdA0SmAGuoq4j//bTraX9MCgYBjk6+9PVosdo1HytsKbk7FOqt2KAeLPe8V0H4ph/dbnVVWco65dzBd36kg/k0WcEWZgcKjcTLw6SCdM23fP9P1x+2aP5B44qfnMlnHSZVaIKEYbeAjRTUleyLLGzFCSFFlqlIXrGq01i8wTaFp/RfhbDb3Qu6AhDR44h3K8ZfKOQKBgFfWyNcVS54OEEbfyvhaiBCUAP7z8ce/JmtUbV0/d9bVnnlU12PD9QatT8S7RL9LOnb+0/+TfeCfaoOUfDs+QbzX5h3AaxuSKVGyowY2SA4Qz/4HSGRYDFnuS4k22yrlcetMXEmqQdivyI/9dLFi3wl4BJAyfnjJnSfQlOb958zpAoGBAOxJASY8toVgAWMrGAxhaG1/46gmp1mEOb+/qruWSvLMJRiNW9UslJDKrNB/mXVDYN8Jg5/7Ad54uXK3vaR4PLsGtUwCemB/7nOnPJcvN2MLfUALlfjjywxTIHGCz0Z3Iix9aXr/baq0heIiJCL6CeuIZ2C9gp8Q4+xWhGg3GqxQ";
    //公钥
    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArDQaAi7XzRTLoeHJ0cjUu1eD1Ed8bOlp6BafUJCKGvEGaZz9/XHIUvgfVRtrYBw7etWn/buNeQmIvVwwP+JUvuVkj9olQr0xQLn4RjuRmlo47fgiuQpWnaLL4e8l/pCBPqbMRj3/1IZOGOtk99kpVC9FkxryXIkdK6DK1Owq33eIprkoUpYIe0xHV8Ws5K7X7CQcadzthkeZTariTbJhMxdcOiwSLCb6scz0QQNW9oXSJUUffc0aW7yo1XVOdvE/qGjqFNZO6UTwCVaswzsZ0b0xFhuuO3Wg5OCGC+NIP7idPBJr8Pe6MibYsuRhUNlBVigFyfH3G9fieUS/XZ1uKwIDAQAB";
    //请求参数类型
    private static final String FORMAT = "json";
    //编码格式
    private static final String CHARSET = "GBK";
    //签名算法类型
    private static final String SIGN_TYPE = "RSA2";

    public static void transfer(){
        AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL,APP_ID,PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" +
                "\"out_biz_no\":\"3142321423432\"," +
                "\"payee_type\":\"ALIPAY_LOGONID\"," +
                "\"payee_account\":\"egtnsw0542@sandbox.com\"," +
                "\"amount\":\"1\"," +
                "\"payer_show_name\":\"测试红包\"," +
                "\"payee_real_name\":\"沙箱环境\"," +
                "\"remark\":\"转账备注\"" +
                "  }");
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            response = alipayClient.execute(request);

            System.out.println(response);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /*public static void main(String args[]) {
        transfer();
    }*/
}
