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
