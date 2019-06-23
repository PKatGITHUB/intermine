package org.intermine.webservice.api;

import org.intermine.api.InterMineAPI;
import org.intermine.web.context.InterMineContext;
import org.intermine.webservice.model.JBrowseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.intermine.webservice.server.data.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-22T06:09:52.181+05:30[Asia/Kolkata]")
@Controller
public class DataApiController implements DataApi {

    private static final Logger log = LoggerFactory.getLogger(DataApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private HttpHeaders httpHeaders;

    private HttpStatus httpStatus;

    @org.springframework.beans.factory.annotation.Autowired
    public DataApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<JBrowseData> jBrowseSimpleData(@ApiParam(value = "The type of the object to find.",required=true) @PathVariable("type") String type,@ApiParam(value = "The range of values requested." ) @RequestHeader(value="Range", required=false) String range,@ApiParam(value = "An optional filter over the objects.") @Valid @RequestParam(value = "filter", required = false) List<String> filter) {
        String accept = request.getHeader("Accept");
        final InterMineAPI im = InterMineContext.getInterMineAPI();

        DataService dataService = new DataService(im,type);
        dataService.service(request);
        dataService.setFooter();
        JBrowseData jBrowseData = dataService.getjBrowseData();
        httpHeaders = dataService.getResponseHeaders();
        httpStatus = dataService.getHttpStatus();

        return new ResponseEntity<JBrowseData>(jBrowseData,httpHeaders,httpStatus);
    }

}
