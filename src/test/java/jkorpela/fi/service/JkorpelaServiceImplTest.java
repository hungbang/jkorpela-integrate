package jkorpela.fi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jkorpela.fi.request.DocumentBody;
import jkorpela.fi.response.DocumentStatusResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

/**
 * Created by KAI on 9/1/18.
 * Copyright 2018 by jkorpela-integrate
 * All rights reserved.
 */
@RunWith(PowerMockRunner.class)
public class JkorpelaServiceImplTest {

    private JkorpelaService jkorpelaService;

    @Before
    public void setup() {
        /** TODO[HBQ, S1] if you want to setting proxy ,
         * please use bellow code instead of default constructor
         *  this.jkorpelaService = new JkorpelaServiceImpl("172.16.12.136", 80);
         */
        this.jkorpelaService = new JkorpelaServiceImpl();
    }

    @Test
    public void fetchDocumentStatus() throws Exception {

        //TODO[HBQ, S1] please change this file to run test this test case
        String fileTest = "/Users/KAI/Downloads/Block.docx";
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentBody documentBody = DocumentBody.builder().datafile(new File(fileTest)).textLine("test").build();
        DocumentStatusResponse documentStatusResponse = this.jkorpelaService.fetchDocumentStatus(documentBody);
        System.out.println(objectMapper.writeValueAsString(documentStatusResponse));
    }

}