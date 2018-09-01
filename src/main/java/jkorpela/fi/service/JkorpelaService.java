package jkorpela.fi.service;

import jkorpela.fi.request.DocumentBody;
import jkorpela.fi.response.DocumentStatusResponse;

/**
 * Created by KAI on 9/1/18.
 * Copyright 2018 by jkorpela-integrate
 * All rights reserved.
 */
public interface JkorpelaService {
    DocumentStatusResponse fetchDocumentStatus(DocumentBody documentBody);
}
