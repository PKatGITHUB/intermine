/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.5).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.intermine.webservice.api;

import org.intermine.webservice.model.Session;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-18T03:15:59.349+05:30[Asia/Kolkata]")
@Api(value = "session", description = "the session API")
public interface SessionApi {

    @ApiOperation(value = "Open a new Web-Services Session.", nickname = "session", notes = "This service opens a new 24 session for users. If authenticated to an existing user, it has the same effect as requesting a new 24hr token. But for unauthenticated users, it allocates a new temporary profile, and assigns a 24hr usage token.", response = Session.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Session.class) })
    @RequestMapping(value = "/session",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Session> session();

}
