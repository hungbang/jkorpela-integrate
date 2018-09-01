package jkorpela.fi;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by KAI on 9/1/18.
 * Copyright 2018 by jkorpela-integrate
 * All rights reserved.
 */
public class JkorpelaService {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        final String url = "http://jkorpela.fi/cgi-bin/echo.cgi";
        final String path = "/Users/KAI/Downloads/temp14_20-Aug-2018_09-49-22-497.docx";
        final String linux = "/home/iclouduser1/payloadfiles/block.txt";
        final String block = "C:\\Users\\icloud.user1\\Downloads\\block.txt";

        File file = new File(linux);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        map.add("datafile", fileSystemResource);
        map.add("textline", "testfile");

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", USER_AGENT);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        final HttpEntity httpEntity = new HttpEntity(map, headers);

        setProxy();

        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println(responseEntity.getBody());
        }
        else{
            System.out.println(responseEntity);
        }

    }

    //TODO[HBQ, S1] setting proxy for resttemplate
    private static void setProxy() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.12.136", 80));
        requestFactory.setProxy(proxy);
        restTemplate.setRequestFactory(requestFactory);
    }
}
