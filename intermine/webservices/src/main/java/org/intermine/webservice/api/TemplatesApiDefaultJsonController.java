package org.intermine.webservice.api;

import org.apache.log4j.Logger;
import org.intermine.api.InterMineAPI;
import org.intermine.web.context.InterMineContext;
import org.intermine.webservice.model.QueryResultsJson;
import org.intermine.webservice.model.QueryResultsJsonCount;
import org.intermine.webservice.model.QueryResultsJsonObject;
import org.intermine.webservice.model.SavedTemplate;
import org.intermine.webservice.model.SimpleJsonModel;
import org.intermine.webservice.model.Tags;
import org.intermine.webservice.model.Templates;
import org.intermine.webservice.model.TemplatesSystem;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.intermine.webservice.model.ToList;
import org.intermine.webservice.server.Format;
import org.intermine.webservice.server.WebServiceRequestParser;
import org.intermine.webservice.server.template.AvailableTemplatesService;
import org.intermine.webservice.server.template.SingleTemplateService;
import org.intermine.webservice.server.template.SystemTemplatesService;
import org.intermine.webservice.server.template.TemplateListAppendService;
import org.intermine.webservice.server.template.TemplateRemovalService;
import org.intermine.webservice.server.template.TemplateTagAddingService;
import org.intermine.webservice.server.template.TemplateTagRemovalService;
import org.intermine.webservice.server.template.TemplateTagService;
import org.intermine.webservice.server.template.TemplateToListService;
import org.intermine.webservice.server.template.TemplateUploadService;
import org.intermine.webservice.server.template.result.TemplateResultService;
import org.intermine.webservice.util.ResponseUtilSpring;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang.StringEscapeUtils.escapeJava;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-19T01:19:48.599+05:30[Asia/Kolkata]")
@Controller
public class TemplatesApiDefaultJsonController extends InterMineController implements TemplatesApiDefaultJson {

    private static final org.apache.log4j.Logger LOG = Logger.getLogger(TemplatesApiController.class);

    private static final String FILE_BASE_NAME = "templates";


    @org.springframework.beans.factory.annotation.Autowired
    public TemplatesApiDefaultJsonController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
    }


    public ResponseEntity<?> templateAppendToListGet(@NotNull @ApiParam(value = "The name of the template to run.", required = true) @Valid @RequestParam(value = "name", required = true) String name, @NotNull @ApiParam(value = "The path to use to compose the results. This should be one of the selected view paths.", required = true) @Valid @RequestParam(value = "path", required = true) String path, @NotNull @ApiParam(value = "The name for the new list. There must be no existing list of this name.", required = true) @Valid @RequestParam(value = "listName", required = true) String listName, @ApiParam(value = "One of a variable set of parameters used to supply constraint paths.") @Valid @RequestParam(value = "constraint1", required = false) String constraint1, @ApiParam(value = "One of a variable set of parameters used to supply constraint operators.") @Valid @RequestParam(value = "op1", required = false) String op1, @ApiParam(value = "One of a variable set of parameters used to supply constraint values.") @Valid @RequestParam(value = "value1", required = false) String value1, @ApiParam(value = "One of a variable set of parameters used to supply constraint codes. Codes are only required if two constraints have the same path.") @Valid @RequestParam(value = "code1", required = false) String code1, @ApiParam(value = "One of a variable set of parameters used to supply extra constraint values (used by LOOKUP constraints).") @Valid @RequestParam(value = "extra1", required = false) String extra1, @ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format) {
        initController();
        return serveAppendToList(format, listName);
    }

    public ResponseEntity<?> templateAppendToListPost(@NotNull @ApiParam(value = "The name of the template to run.", required = true) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @ApiParam(value = "The path to use to compose the results. This should be one of the selected view paths.", required = true) @Valid @RequestParam(value = "path", required = true) String path,@NotNull @ApiParam(value = "The name for the new list. There must be no existing list of this name.", required = true) @Valid @RequestParam(value = "listName", required = true) String listName,@ApiParam(value = "One of a variable set of parameters used to supply constraint paths.") @Valid @RequestParam(value = "constraint1", required = false) String constraint1,@ApiParam(value = "One of a variable set of parameters used to supply constraint operators.") @Valid @RequestParam(value = "op1", required = false) String op1,@ApiParam(value = "One of a variable set of parameters used to supply constraint values.") @Valid @RequestParam(value = "value1", required = false) String value1,@ApiParam(value = "One of a variable set of parameters used to supply constraint codes. Codes are only required if two constraints have the same path.") @Valid @RequestParam(value = "code1", required = false) String code1,@ApiParam(value = "One of a variable set of parameters used to supply extra constraint values (used by LOOKUP constraints).") @Valid @RequestParam(value = "extra1", required = false) String extra1,@ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format) {
        initController();
        return serveAppendToList(format, listName);
    }

    private ResponseEntity<?> serveAppendToList(String format, String listName){
        final InterMineAPI im = InterMineContext.getInterMineAPI();

        setHeaders();
        TemplateListAppendService templateListAppendService = new TemplateListAppendService(im, getFormat(), listName);
        try {
            templateListAppendService.service(request);
        } catch (Throwable throwable) {
            sendError(throwable);
        }

        ToList toList = templateListAppendService.getToList();

        if(format.equals("json")){
            setFooter(toList);
            return new ResponseEntity<ToList>(toList,httpHeaders,httpStatus);
        }
        ResponseUtilSpring.setPlainTextHeader(httpHeaders,"results.txt");
        return new ResponseEntity<Integer>(toList.getListSize(),httpHeaders,httpStatus);
    }

    public ResponseEntity<?> templateResultsGet(@NotNull @ApiParam(value = "The name of the template to run.", required = true) @Valid @RequestParam(value = "name", required = true) String name, @ApiParam(value = "One of a variable set of parameters used to supply constraint paths.") @Valid @RequestParam(value = "constraint1", required = false) String constraint1, @ApiParam(value = "One of a variable set of parameters used to supply constraint operators.") @Valid @RequestParam(value = "op1", required = false) String op1, @ApiParam(value = "One of a variable set of parameters used to supply constraint values.") @Valid @RequestParam(value = "value1", required = false) String value1, @ApiParam(value = "One of a variable set of parameters used to supply constraint codes. Codes are only required if two constraints have the same path.") @Valid @RequestParam(value = "code1", required = false) String code1, @ApiParam(value = "One of a variable set of parameters used to supply extra constraint values (used by LOOKUP constraints).") @Valid @RequestParam(value = "extra1", required = false) String extra1, @ApiParam(value = "The index of the first result to return.") @Valid @RequestParam(value = "start", required = false) Integer start, @ApiParam(value = "The maximum size of the result set.") @Valid @RequestParam(value = "size", required = false) Integer size, @ApiParam(value = "Include column headers. Use friendly for human readable paths. (Only for flat-file formats).", allowableValues = "none, path, friendly") @Valid @RequestParam(value = "columnheaders", required = false) String columnheaders, @ApiParam(value = "", allowableValues = "tab, csv, count, json, jsonobject, jsoncount, xml, html") @Valid @RequestParam(value = "format", required = false, defaultValue = "tab") String format) {
        initController();
        return serveTemplateResults(format);
    }

    public ResponseEntity<?> templateResultsPost(@NotNull @ApiParam(value = "The name of the template to run.", required = true) @Valid @RequestParam(value = "name", required = true) String name,@ApiParam(value = "One of a variable set of parameters used to supply constraint paths.") @Valid @RequestParam(value = "constraint1", required = false) String constraint1,@ApiParam(value = "One of a variable set of parameters used to supply constraint operators.") @Valid @RequestParam(value = "op1", required = false) String op1,@ApiParam(value = "One of a variable set of parameters used to supply constraint values.") @Valid @RequestParam(value = "value1", required = false) String value1,@ApiParam(value = "One of a variable set of parameters used to supply constraint codes. Codes are only required if two constraints have the same path.") @Valid @RequestParam(value = "code1", required = false) String code1,@ApiParam(value = "One of a variable set of parameters used to supply extra constraint values (used by LOOKUP constraints).") @Valid @RequestParam(value = "extra1", required = false) String extra1,@ApiParam(value = "The index of the first result to return.") @Valid @RequestParam(value = "start", required = false) Integer start,@ApiParam(value = "The maximum size of the result set.") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Include column headers. Use friendly for human readable paths. (Only for flat-file formats).", allowableValues = "none, path, friendly") @Valid @RequestParam(value = "columnheaders", required = false) String columnheaders,@ApiParam(value = "", allowableValues = "tab, csv, count, json, jsonobject, jsoncount, xml, html") @Valid @RequestParam(value = "format", required = false, defaultValue = "tab") String format) {
        initController();
        return serveTemplateResults(format);
    }

    private ResponseEntity<?> serveTemplateResults(String format){
        final InterMineAPI im = InterMineContext.getInterMineAPI();

        setHeaders();
        if(format.equals("tab")){
            this.format = Format.TSV;
        }
        TemplateResultService templateResultService = new TemplateResultService(im, getFormat());
        try {
            templateResultService.service(request);
        } catch (Throwable throwable) {
            sendError(throwable);
        }
        if(format.equals("json")){
            String outputString = templateResultService.getOutputString();
            //QueryResultsJson queryResultsJson = templateResultService.getQueryResultsJson();
            ResponseUtilSpring.setJSONHeader(httpHeaders, "results.json", false);
            outputString = setFooterTemplateResults(outputString);
            return new ResponseEntity<String>(outputString,httpHeaders,httpStatus);

        } else if(format.equals("jsonobject")) {
            String outputString = templateResultService.getOutputString();
            //QueryResultsJsonObject queryResultsJsonObject = templateResultService.getQueryResultsJsonObject();
            ResponseUtilSpring.setJSONHeader(httpHeaders, "results.json", false);
            outputString = setFooterTemplateResults(outputString);
            return new ResponseEntity<String>(outputString,httpHeaders,httpStatus);
        } else if(format.equals("jsoncount")) {
            String outputString = templateResultService.getCountString();
            //QueryResultsJsonCount queryResultsJsonCount = templateResultService.getQueryResultsJsonCount();
            ResponseUtilSpring.setJSONHeader(httpHeaders, "results.json", false);
            outputString = setFooterTemplateResults(outputString);
            return new ResponseEntity<String>(outputString,httpHeaders,httpStatus);
        } else if(format.equals("count")) {
            String outputString = templateResultService.getCountString();
            //QueryResultsJsonCount queryResultsJsonCount = templateResultService.getQueryResultsJsonCount();
            ResponseUtilSpring.setPlainTextHeader(httpHeaders, "results");
            return new ResponseEntity<Integer>(Integer.parseInt(outputString),httpHeaders,httpStatus);

        } else if(format.equals("tab")) {
            ResponseUtilSpring.setTabHeader(httpHeaders, "results.tsv");

        } else if(format.equals("csv")) {
            ResponseUtilSpring.setCSVHeader(httpHeaders, "results.csv");

        } else if(format.equals("xml")) {
            ResponseUtilSpring.setXMLHeader(httpHeaders, "results.xml");

        }
        //QueryResultsJsonObject queryResultsJsonObject = templateResultService.getQueryResultsJsonObject();
        String outputString = templateResultService.getResultsString();
        return new ResponseEntity<Object>(outputString,httpHeaders,httpStatus);
    }

    public ResponseEntity<?> templateToListGet(@NotNull @ApiParam(value = "The name of the template to run.", required = true) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @ApiParam(value = "The path to use to compose the results. This should be one of the selected view paths.", required = true) @Valid @RequestParam(value = "path", required = true) String path,@NotNull @ApiParam(value = "The name for the new list. There must be no existing list of this name.", required = true) @Valid @RequestParam(value = "listName", required = true) String listName,@ApiParam(value = "One of a variable set of parameters used to supply constraint paths.") @Valid @RequestParam(value = "constraint1", required = false) String constraint1,@ApiParam(value = "One of a variable set of parameters used to supply constraint operators.") @Valid @RequestParam(value = "op1", required = false) String op1,@ApiParam(value = "One of a variable set of parameters used to supply constraint values.") @Valid @RequestParam(value = "value1", required = false) String value1,@ApiParam(value = "One of a variable set of parameters used to supply constraint codes. Codes are only required if two constraints have the same path.") @Valid @RequestParam(value = "code1", required = false) String code1,@ApiParam(value = "One of a variable set of parameters used to supply extra constraint values (used by LOOKUP constraints).") @Valid @RequestParam(value = "extra1", required = false) String extra1,@ApiParam(value = "A description to attach to the new list.") @Valid @RequestParam(value = "description", required = false) String description,@ApiParam(value = "A set of tags to use to categorise the new list separated by semicolon(;).") @Valid @RequestParam(value = "tags", required = false) String tags,@ApiParam(value = "Whether or not to replace any existing list of this name.") @Valid @RequestParam(value = "replaceExisting", required = false, defaultValue = "false") Boolean replaceExisting,@ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format) {
        initController();
        return serveToList(format, listName);
    }

    public ResponseEntity<?> templateToListPost(@NotNull @ApiParam(value = "The name of the template to run.", required = true) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @ApiParam(value = "The path to use to compose the results. This should be one of the selected view paths.", required = true) @Valid @RequestParam(value = "path", required = true) String path,@NotNull @ApiParam(value = "The name for the new list. There must be no existing list of this name.", required = true) @Valid @RequestParam(value = "listName", required = true) String listName,@ApiParam(value = "One of a variable set of parameters used to supply constraint paths.") @Valid @RequestParam(value = "constraint1", required = false) String constraint1,@ApiParam(value = "One of a variable set of parameters used to supply constraint operators.") @Valid @RequestParam(value = "op1", required = false) String op1,@ApiParam(value = "One of a variable set of parameters used to supply constraint values.") @Valid @RequestParam(value = "value1", required = false) String value1,@ApiParam(value = "One of a variable set of parameters used to supply constraint codes. Codes are only required if two constraints have the same path.") @Valid @RequestParam(value = "code1", required = false) String code1,@ApiParam(value = "One of a variable set of parameters used to supply extra constraint values (used by LOOKUP constraints).") @Valid @RequestParam(value = "extra1", required = false) String extra1,@ApiParam(value = "A description to attach to the new list.") @Valid @RequestParam(value = "description", required = false) String description,@ApiParam(value = "A set of tags to use to categorise the new list separated by semicolon(;).") @Valid @RequestParam(value = "tags", required = false) String tags,@ApiParam(value = "Whether or not to replace any existing list of this name.") @Valid @RequestParam(value = "replaceExisting", required = false, defaultValue = "false") Boolean replaceExisting,@ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format) {
        initController();
        return serveToList(format, listName);
    }

    private ResponseEntity<?> serveToList(String format, String listName){
        final InterMineAPI im = InterMineContext.getInterMineAPI();

        setHeaders();
        TemplateToListService templateToListService = new TemplateToListService(im, getFormat(), listName);
        try {
            templateToListService.service(request);
        } catch (Throwable throwable) {
            sendError(throwable);
        }

        ToList toList = templateToListService.getToList();

        if(format.equals("json")){
            setFooter(toList);
            return new ResponseEntity<ToList>(toList,httpHeaders,httpStatus);
        }
        ResponseUtilSpring.setPlainTextHeader(httpHeaders,"results.txt");
        return new ResponseEntity<Integer>(toList.getListSize(),httpHeaders,httpStatus);
    }

    public String setFooterTemplateResults(String outputString){
        Date now = Calendar.getInstance().getTime();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm::ss");
        String executionTime = dateFormatter.format(now);
        outputString = outputString.concat("\"executionTime\" : \""+executionTime+"\"").concat("\n");


        if (status >= 400) {
            outputString = outputString.concat("\"wasSuccessful\" : \""+false+"\"").concat("\n");
            outputString = outputString.concat("\"error\" : \""+escapeJava(errorMessage)+"\"").concat("\n");
        } else {
            outputString = outputString.concat("\"wasSuccessful\" : \""+true+"\"").concat("\n");
        }
        outputString = outputString.concat("\"statusCode\" : \""+status+"\"").concat("\n");
        outputString = outputString.concat("}");
        return outputString;
    }

    @Override
    protected Format getDefaultFormat() {
        return Format.JSON;
    }

    @Override
    protected boolean canServe(Format format) {
        return Format.BASIC_FORMATS.contains(format);
    }


}
