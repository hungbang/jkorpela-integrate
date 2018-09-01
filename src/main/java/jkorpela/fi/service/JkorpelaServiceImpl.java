package jkorpela.fi.service;

import jkorpela.fi.request.BaseProperties;
import jkorpela.fi.request.DocumentBody;
import jkorpela.fi.response.DocumentStatusResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by KAI on 9/1/18.
 * Copyright 2018 by jkorpela-integrate
 * All rights reserved.
 */
public class JkorpelaServiceImpl implements JkorpelaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(JkorpelaServiceImpl.class);

    private final String BASE_URL = "http://jkorpela.fi/cgi-bin/echo.cgi";
    private BaseProperties baseProperties;
    private RestTemplate restTemplate = new RestTemplate();

    public JkorpelaServiceImpl() {
        this.baseProperties = new BaseProperties();
    }

    public JkorpelaServiceImpl(String proxyHost, int proxyPort) {
        this.baseProperties = new BaseProperties(proxyHost, proxyPort);
    }

    @Override
    public DocumentStatusResponse fetchDocumentStatus(DocumentBody documentBody) {

        //build request body
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        FileSystemResource fileSystemResource = new FileSystemResource(documentBody.getDatafile());
        map.add("datafile", fileSystemResource);
        map.add("textline", documentBody.getTextLine());

        // build headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", USER_AGENT);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        final HttpEntity httpEntity = new HttpEntity(map, headers);

        setProxy();

        ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.POST, httpEntity, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            LOGGER.info("Document is NO BLOCK. Result data {}", responseEntity.getBody());
            String s = responseEntity.getBody();
            Document document = Jsoup.parse(s);
            String processed = document.select("p").text();
            Elements tables = document.select("table");
            Elements tds = tables.get(0).select("td");
            String textline = tds.get(0).text();
            String datafine = tds.get(1).text();
            return DocumentStatusResponse.builder().datafile(datafine)
                    .textLine(textline).forcePoint(processed)
                    .build();
        } else if (responseEntity.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
            LOGGER.info("Document is BLOCK. Result data {}, status code is {}", responseEntity.getBody(), responseEntity.getStatusCode());
            return DocumentStatusResponse.builder()
                    .reason("The request was blocked because it violates corporate policy")
                    .options("Click Go Back or use the browser's Back button to return to the previous page.")
                    .forcePoint("FORCEPOINT").block(true).build();
        }
        return null;
    }

    //TODO[HBQ, S1] setting proxy for resttemplate
    private void setProxy() {
        if (baseProperties.proxy() != null)
            restTemplate.setRequestFactory(baseProperties.proxy());
    }
}
