/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.5).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.intermine.webservice.api;

import org.intermine.webservice.model.SimpleJsonModel;
import org.intermine.webservice.model.Templates;
import org.intermine.webservice.model.TemplatesSystem;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-19T01:19:48.599+05:30[Asia/Kolkata]")
@Api(value = "templates", description = "the templates API")
public interface TemplatesApi {

    @ApiOperation(value = "Get the list of system templates.", nickname = "templatesSystem", notes = "Get a listing of the templates configured in an InterMine instance tagged with the `im:converter` tag.", response = TemplatesSystem.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TemplatesSystem.class) })
    @RequestMapping(value = "/templates/system",
        method = RequestMethod.GET)
    ResponseEntity<?> templatesSystem(@ApiParam(value = "", allowableValues = "xml, json") @Valid @RequestParam(value = "format", required = false, defaultValue = "xml") String format);

    @ApiOperation(value = "Get the list of available templates.", nickname = "templatesGet", notes = "Get a listing of the templates configured in an InterMine instance. Each template contains a description of its properties, defining the parameters that must be provided to run it, as well as a definition of the output format for its results. If the request is authenticated to a user, then the templates that that has access to will also be returned.", response = Templates.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Templates.class) })
    @RequestMapping(value = "/templates",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<?> templatesGet(@ApiParam(value = "Whether or not to include invalid templates. Invalid templates cannot be run.") @Valid @RequestParam(value = "includeBroken", required = false) Boolean includeBroken, @ApiParam(value = "", allowableValues = "xml, json") @Valid @RequestParam(value = "format", required = false, defaultValue = "xml") String format);


    @ApiOperation(value = "Upload one or more templates.", nickname = "templatesPost", notes = "Upload a set of templates to the current user profile.", response = SimpleJsonModel.class,  authorizations = {
            @Authorization(value = "BasicAuth")    },  tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SimpleJsonModel.class) })
    @RequestMapping(value = "/templates",
            produces = { "application/json" },
            consumes = { "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<?> templatesPost(@ApiParam(value = "The templates to upload. If using body content."  )  @Valid @RequestBody String body,@ApiParam(value = "The xml or JSON to load, if using form parameters.") @Valid @RequestParam(value = "xml", required = false) String xml,@ApiParam(value = "", allowableValues = "xml, json") @Valid @RequestParam(value = "format", required = false, defaultValue = "xml") String format);

    @ApiOperation(value = "Save a Template, or Overwrite an Existing One.", nickname = "templateUploadGet", notes = "This service allows users to save templates they have authored on the server, associated with their account. The templates once saved can be used as any other template query.", response = SimpleJsonModel.class, authorizations = {
            @Authorization(value = "BasicAuth")    }, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SimpleJsonModel.class) })
    @RequestMapping(value = "/template/upload",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<?> templateUploadGet(@NotNull @ApiParam(value = "One or more templates, serialised in XML or JSON format.", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "The version of the XML format.") @Valid @RequestParam(value = "version", required = false) Integer version,@ApiParam(value = "", allowableValues = "text, json, html, xml") @Valid @RequestParam(value = "format", required = false, defaultValue = "text") String format);


    @ApiOperation(value = "Save a Template, or Overwrite an Existing One.", nickname = "templateUploadPost", notes = "This service allows users to save templates they have authored on the server, associated with their account. The templates once saved can be used as any other template query.", response = SimpleJsonModel.class, authorizations = {
            @Authorization(value = "BasicAuth")    }, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SimpleJsonModel.class) })
    @RequestMapping(value = "/template/upload",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> templateUploadPost(@NotNull @ApiParam(value = "One or more templates, serialised in XML or JSON format.", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "The version of the XML format.") @Valid @RequestParam(value = "version", required = false) Integer version,@ApiParam(value = "", allowableValues = "text, json, html, xml") @Valid @RequestParam(value = "format", required = false, defaultValue = "text") String format);

}
