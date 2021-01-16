package com.salary;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TenthTeamApplicationTests {

    @Test
    void contextLoads() throws Exception {
        String decrypt = ConfigTools.decrypt("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAITPQYSVJrrFP0X32oYdVJTfe+7+B8TisuU4MKfKcf1ScfSb8iHs4f/Jvzx0T/jAY3tJaF++xrW94BHZ2iHRwGECAwEAAQ==", "PD1pb/hSKoJHV/4WnrvEJfIppau+9nIKyZ9kkLPQpjwcThgbeT9XmOVtpxp41KR6kNmZ5IN6kw+7wnM7QhdMRQ==");
        System.out.println("decrypt = " + decrypt);
    }

}
