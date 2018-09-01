# How to import jkorpela-integrate to your code
1.    Download source code 
2.    run 


     {
         mvn clean install
     }

3.    Import target/jkorpela-integrate-1.0-SNAPSHOT.jar to your source code and do like instruction

# jkorpela-integrate
- to init JkorpelaService with proxy

      {
          private JkorpelaService jkorpelaService = new JkorpelaServiceImpl("172.16.12.136", 80);
      }
    
# How to use?

    {
        //TODO[HBQ, S1] please change this file to run test this test case
        String fileTest = "/Users/KAI/Downloads/Block.docx";
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentBody documentBody = DocumentBody.builder().datafile(new File(fileTest)).textLine("test").build();
        DocumentStatusResponse documentStatusResponse = this.jkorpelaService.fetchDocumentStatus(documentBody);
        System.out.println(objectMapper.writeValueAsString(documentStatusResponse));
    }

# Response example
-    No block

    {  
     "datafile":"test",
     "block":false,
     "reason":null,
     "options":null,
     "forcePoint":"Processed 2018-09-01T15:44Z",
     "textline":"**Block.docx**file (application/vnd.openxmlformats-officedocument.wordprocessingml.document, first line or max. 40 octets shown): PK\u0003\u0004\u0014\u0000\u0006\u0000\b\u0000\u0000\u0000!\u0000ß¤ÒlZ\u0001\u0000\u0000 \u0005\u0000\u0000\u0013\u0000\b\u0002[Content_T"
     }
