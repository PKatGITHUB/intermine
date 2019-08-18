/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.5).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.intermine.bio.webservice.api;

import org.intermine.bio.webservice.model.ListGenomicIntervals;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-04T21:58:55.348+05:30[Asia/Kolkata]")
@Api(value = "regions", description = "the regions API")
public interface RegionsApi {

    @ApiOperation(value = "Get Genomic Features overlapping given Regions.", nickname = "regionsBedGet", notes = "This service returns information about features found overlapping a set of genomic intervals. The results are returned in the [UCSC BED](http://genome.ucsc.edu/FAQ/FAQformat.html) format where each row represents a feature. <br/><br/> The input to this service is a `json` object that satisfies the following [json-schema](http://json-schema.org/) schema: <br/><br/> { <br/>   &nbsp; \"type\": \"object\", <br/>   &nbsp;   \"required\": [\"regions\", \"featureTypes\", \"organism\"], <br/>   &nbsp;   \"properties\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      \"regions\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"featureTypes\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"isInterbase\": { \"type\": \"boolean\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"extension\": { \"type\": \"number\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"organism\": { \"type\": \"string\" } <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  }, <br/>   &nbsp;  \"definitions\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"stringset\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"type\": \"array\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;          \"items: { \"type: \"string\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"minItems\": 1, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;            \"uniqueItems\": true <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        } <br/>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;    } <br/> }", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class) })
    @RequestMapping(value = "/regions/bed",
        produces = { "*/*" },
        method = RequestMethod.GET)
    ResponseEntity<String> regionsBedGet(@NotNull @ApiParam(value = "The region search input.", required = true) @Valid @RequestParam(value = "query", required = true) String query);


    @ApiOperation(value = "Get Genomic Features overlapping given Regions.", nickname = "regionsBedPost", notes = "This service returns information about features found overlapping a set of genomic intervals. The results are returned in the [UCSC BED](http://genome.ucsc.edu/FAQ/FAQformat.html) format where each row represents a feature. <br/><br/> The input to this service is a `json` object that satisfies the following [json-schema](http://json-schema.org/) schema: <br/><br/> { <br/>   &nbsp; \"type\": \"object\", <br/>   &nbsp;   \"required\": [\"regions\", \"featureTypes\", \"organism\"], <br/>   &nbsp;   \"properties\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      \"regions\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"featureTypes\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"isInterbase\": { \"type\": \"boolean\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"extension\": { \"type\": \"number\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"organism\": { \"type\": \"string\" } <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  }, <br/>   &nbsp;  \"definitions\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"stringset\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"type\": \"array\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;          \"items: { \"type: \"string\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"minItems\": 1, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;            \"uniqueItems\": true <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        } <br/>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;    } <br/> }", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class) })
    @RequestMapping(value = "/regions/bed",
        produces = { "*/*" },
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<String> regionsBedPost(@ApiParam(value = "A representation of the search request.", required = true) @Valid @RequestBody String body);


    @ApiOperation(value = "Get Genomic Features overlapping given Regions.", nickname = "regionsGff3Get", notes = "This service returns information about features found overlapping a set of genomic intervals. The results are returned in the General Feature Format [GFF3](http://en.wikipedia.org/wiki/General_feature_format) where each row represents a feature. <br/><br/> The input to this service is a `json` object that satisfies the following [json-schema](http://json-schema.org/) schema: <br/><br/> { <br/>   &nbsp; \"type\": \"object\", <br/>   &nbsp;   \"required\": [\"regions\", \"featureTypes\", \"organism\"], <br/>   &nbsp;   \"properties\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      \"regions\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"featureTypes\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"isInterbase\": { \"type\": \"boolean\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"extension\": { \"type\": \"number\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"organism\": { \"type\": \"string\" } <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  }, <br/>   &nbsp;  \"definitions\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"stringset\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"type\": \"array\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;          \"items: { \"type: \"string\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"minItems\": 1, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;            \"uniqueItems\": true <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        } <br/>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;    } <br/> }", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class) })
    @RequestMapping(value = "/regions/gff3",
        produces = { "*/*" },
        method = RequestMethod.GET)
    ResponseEntity<String> regionsGff3Get(@NotNull @ApiParam(value = "The region search input.", required = true) @Valid @RequestParam(value = "query", required = true) String query);


    @ApiOperation(value = "Get Genomic Features overlapping given Regions.", nickname = "regionsGff3Post", notes = "This service returns information about features found overlapping a set of genomic intervals. The results are returned in the General Feature Format [GFF3](http://en.wikipedia.org/wiki/General_feature_format) where each row represents a feature. <br/><br/> The input to this service is a `json` object that satisfies the following [json-schema](http://json-schema.org/) schema: <br/><br/> { <br/>   &nbsp; \"type\": \"object\", <br/>   &nbsp;   \"required\": [\"regions\", \"featureTypes\", \"organism\"], <br/>   &nbsp;   \"properties\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      \"regions\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"featureTypes\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"isInterbase\": { \"type\": \"boolean\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"extension\": { \"type\": \"number\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"organism\": { \"type\": \"string\" } <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  }, <br/>   &nbsp;  \"definitions\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"stringset\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"type\": \"array\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;          \"items: { \"type: \"string\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"minItems\": 1, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;            \"uniqueItems\": true <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        } <br/>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;    } <br/> }", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class) })
    @RequestMapping(value = "/regions/gff3",
        produces = { "*/*" },
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<String> regionsGff3Post(@ApiParam(value = "A representation of the search request.", required = true) @Valid @RequestBody String body);


    @ApiOperation(value = "Create a New List from Features found in Genomic Intervals.", nickname = "regionsListGet", notes = "This service creates a list of features found overlapping a set of genomic intervals. All features found will be saved in a list of the given name. The type of this list will be inferred from the types provided in the search input. <br/><br/> The input to this service is a `json` object that satisfies the following [json-schema](http://json-schema.org/) schema: <br/><br/> { <br/>   &nbsp; \"type\": \"object\", <br/>   &nbsp;   \"required\": [\"regions\", \"featureTypes\", \"organism\"], <br/>   &nbsp;   \"properties\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      \"regions\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"featureTypes\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"isInterbase\": { \"type\": \"boolean\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"extension\": { \"type\": \"number\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"organism\": { \"type\": \"string\" } <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  }, <br/>   &nbsp;  \"definitions\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"stringset\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"type\": \"array\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;          \"items: { \"type: \"string\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"minItems\": 1, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;            \"uniqueItems\": true <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        } <br/>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;    } <br/> }", response = ListGenomicIntervals.class, authorizations = {
        @Authorization(value = "ApiKeyAuthToken"),
@Authorization(value = "BasicAuth"),
@Authorization(value = "JWTBearerAuth")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ListGenomicIntervals.class) })
    @RequestMapping(value = "/regions/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ListGenomicIntervals> regionsListGet(@NotNull @ApiParam(value = "The name of the list to save these features to.", required = true) @Valid @RequestParam(value = "name", required = true) String name, @NotNull @ApiParam(value = "The region search input.", required = true) @Valid @RequestParam(value = "query", required = true) String query, @ApiParam(value = "An optional description for this list.") @Valid @RequestParam(value = "description", required = false) String description, @ApiParam(value = "A set of tags to apply to this list (separated by semi-colon).") @Valid @RequestParam(value = "tags", required = false) String tags, @ApiParam(value = "Whether or not to replace any existing list of this name.") @Valid @RequestParam(value = "replaceExisting", required = false, defaultValue = "false") Boolean replaceExisting);


    @ApiOperation(value = "Create a New List from Features found in Genomic Intervals.", nickname = "regionsListPost", notes = "This service creates a list of features found overlapping a set of genomic intervals. All features found will be saved in a list of the given name. The type of this list will be inferred from the types provided in the search input. <br/><br/> The input to this service is a `json` object that satisfies the following [json-schema](http://json-schema.org/) schema: <br/><br/> { <br/>   &nbsp; \"type\": \"object\", <br/>   &nbsp;   \"required\": [\"regions\", \"featureTypes\", \"organism\"], <br/>   &nbsp;   \"properties\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      \"regions\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"featureTypes\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"isInterbase\": { \"type\": \"boolean\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"extension\": { \"type\": \"number\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"organism\": { \"type\": \"string\" } <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  }, <br/>   &nbsp;  \"definitions\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"stringset\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"type\": \"array\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;          \"items: { \"type: \"string\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"minItems\": 1, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;            \"uniqueItems\": true <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        } <br/>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;    } <br/> }", response = ListGenomicIntervals.class, authorizations = {
        @Authorization(value = "ApiKeyAuthToken"),
@Authorization(value = "BasicAuth"),
@Authorization(value = "JWTBearerAuth")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ListGenomicIntervals.class) })
    @RequestMapping(value = "/regions/list",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ListGenomicIntervals> regionsListPost(@ApiParam(value = "A representation of the search request.", required = true) @Valid @RequestBody String body, @NotNull @ApiParam(value = "The name of the list to save these features to.", required = true) @Valid @RequestParam(value = "listName", required = true) String listName, @ApiParam(value = "An optional description for this list.") @Valid @RequestParam(value = "description", required = false) String description, @ApiParam(value = "A set of tags to apply to this list (separated by semi-colon).") @Valid @RequestParam(value = "tags", required = false) String tags, @ApiParam(value = "Whether or not to replace any existing list of this name.") @Valid @RequestParam(value = "replaceExisting", required = false, defaultValue = "false") Boolean replaceExisting);


    @ApiOperation(value = "Get Genomic Features overlapping given Regions.", nickname = "regionsSequenceGet", notes = "This service returns information about features found overlapping a set of genomic intervals. The results are returned in the [FASTA](http://en.wikipedia.org/wiki/FASTA_format) format where each record represents a feature, including the sequence associated with that feature. <br/><br/> The input to this service is a `json` object that satisfies the following [json-schema](http://json-schema.org/) schema: <br/><br/> { <br/>   &nbsp; \"type\": \"object\", <br/>   &nbsp;   \"required\": [\"regions\", \"featureTypes\", \"organism\"], <br/>   &nbsp;   \"properties\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      \"regions\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"featureTypes\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"isInterbase\": { \"type\": \"boolean\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"extension\": { \"type\": \"number\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"organism\": { \"type\": \"string\" } <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  }, <br/>   &nbsp;  \"definitions\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"stringset\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"type\": \"array\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;          \"items: { \"type: \"string\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"minItems\": 1, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;            \"uniqueItems\": true <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        } <br/>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;    } <br/> }", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class) })
    @RequestMapping(value = "/regions/sequence",
        produces = { "*/*" },
        method = RequestMethod.GET)
    ResponseEntity<String> regionsSequenceGet(@NotNull @ApiParam(value = "The region search input.", required = true) @Valid @RequestParam(value = "query", required = true) String query);


    @ApiOperation(value = "Get Genomic Features overlapping given Regions.", nickname = "regionsSequencePost", notes = "This service returns information about features found overlapping a set of genomic intervals. The results are returned in the [FASTA](http://en.wikipedia.org/wiki/FASTA_format) format where each record represents a feature, including the sequence associated with that feature. <br/><br/> The input to this service is a `json` object that satisfies the following [json-schema](http://json-schema.org/) schema: <br/><br/> { <br/>   &nbsp; \"type\": \"object\", <br/>   &nbsp;   \"required\": [\"regions\", \"featureTypes\", \"organism\"], <br/>   &nbsp;   \"properties\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      \"regions\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"featureTypes\": \"#/definitions/stringset\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"isInterbase\": { \"type\": \"boolean\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"extension\": { \"type\": \"number\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"organism\": { \"type\": \"string\" } <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  }, <br/>   &nbsp;  \"definitions\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;       \"stringset\": { <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"type\": \"array\", <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;          \"items: { \"type: \"string\" }, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;           \"minItems\": 1, <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;            \"uniqueItems\": true <br/>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        } <br/>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;    } <br/> }", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class) })
    @RequestMapping(value = "/regions/sequence",
        produces = { "*/*" },
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<String> regionsSequencePost(@ApiParam(value = "A representation of the search request.", required = true) @Valid @RequestBody String body);

}
