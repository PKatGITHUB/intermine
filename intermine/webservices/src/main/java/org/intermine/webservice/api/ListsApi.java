/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.5).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.intermine.webservice.api;

import org.intermine.webservice.model.JaccardIndex;
import org.intermine.webservice.model.ListAppend;
import org.intermine.webservice.model.ListOperations;
import org.intermine.webservice.model.ListRename;
import org.intermine.webservice.model.ListsDelete;
import org.intermine.webservice.model.ListsGet;
import org.intermine.webservice.model.ListsPost;
import io.swagger.annotations.*;
import org.intermine.webservice.model.Tags;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-16T22:35:41.810+05:30[Asia/Kolkata]")
@Api(value = "lists", description = "the lists API")
public interface ListsApi {

    @ApiOperation(value = "Rename an Existing List.", nickname = "listRenameGet", notes = "This service allows users to change the name of an existing list that they are the owner of.", response = ListRename.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListRename.class)})
    @RequestMapping(value = "/lists/rename",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ListRename> listRenameGet(@NotNull @ApiParam(value = "The current name of the list.", required = true) @Valid @RequestParam(value = "oldname", required = true) String oldname, @NotNull @ApiParam(value = "The name the list should have.", required = true) @Valid @RequestParam(value = "newname", required = true) String newname);


    @ApiOperation(value = "Rename an Existing List.", nickname = "listRenamePost", notes = "This service allows users to change the name of an existing list that they are the owner of.", response = ListRename.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListRename.class)})
    @RequestMapping(value = "/lists/rename",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<ListRename> listRenamePost(@NotNull @ApiParam(value = "The current name of the list.", required = true) @Valid @RequestParam(value = "oldname", required = true) String oldname, @NotNull @ApiParam(value = "The name the list should have.", required = true) @Valid @RequestParam(value = "newname", required = true) String newname);


    @ApiOperation(value = "Add items to an existing list by identifiers.", nickname = "listsAppendPost", notes = "This service allows users to add new items to an existing     list by uploading a set of identifiers. This is equivalent to     creating a new list from a set of identifiers, and then performing     the union operation on that list an existing one, if the resulant     list replaced the existing list.", response = ListAppend.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListAppend.class)})
    @RequestMapping(value = "/lists/append",
            produces = {"application/json"},
            consumes = {"text/plain"},
            method = RequestMethod.POST)
    ResponseEntity<?> listsAppendPost(@ApiParam(value = "Identifiers for objects to add to the list, tab, comma or newline separated.", required = true) @Valid @RequestBody String body, @NotNull @ApiParam(value = "The name of the list to append to. The list must exist, and belong to you.", required = true) @Valid @RequestParam(value = "name", required = true) String name, @ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Delete an existing list.", nickname = "listsDelete", notes = "This service allows users to delete         lists that belong to them.", response = ListsDelete.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")}, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListsDelete.class)})
    @RequestMapping(value = "/lists",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<?> listsDelete(@NotNull @ApiParam(value = "The name of the list to delete.", required = true) @Valid @RequestParam(value = "name", required = true) String name, @ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Find lists on the server.", nickname = "listsGet", notes = "This service allows users to get back a list of             lists that they have access to. The lists can be optionally             be filtered on the server by name, thus returning just a single             name, or if '*' wildcards are used, a subset of all lists.", response = ListsGet.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListsGet.class)})
    @RequestMapping(value = "/lists",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<?> listsGet(@ApiParam(value = "An optional filter by name.") @Valid @RequestParam(value = "name", required = false) String name, @ApiParam(value = "", allowableValues = "json, html, text, csv, tab") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Create a new list.", nickname = "listsPost", notes = "This service allows users to create a new       list by uploading a set of identifiers.         The list is created by using the default settings         of the ID resolution mechanism and then taking         all the good matches.", response = ListsPost.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")}, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListsPost.class)})
    @RequestMapping(value = "/lists",
            produces = {"application/json"},
            consumes = {"text/plain"},
            method = RequestMethod.POST)
    ResponseEntity<?> listsPost(@ApiParam(value = "Identifiers for objects to add to the list, tab, comma or newline separated.", required = true) @Valid @RequestBody String body, @NotNull @ApiParam(value = "The name of the new list.", required = true) @Valid @RequestParam(value = "name", required = true) String name, @NotNull @ApiParam(value = "The type of the list.", required = true) @Valid @RequestParam(value = "type", required = true) String type, @ApiParam(value = "Whether or not to replace any existing list of this name.") @Valid @RequestParam(value = "replaceExisting", required = false) Boolean replaceExisting, @ApiParam(value = "A disambiguating value (such as organism name).") @Valid @RequestParam(value = "extraValue", required = false) String extraValue, @ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);

    @ApiOperation(value = "Get the tags for a list, or all the tags for a given user.", nickname = "listTagsGet", notes = "Fetch an up-to-date list of all tags associated with a list, or all lists.", response = Tags.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Tags.class)})
    @RequestMapping(value = "/list/tags",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<?> listTagsGet(@ApiParam(value = "The name of a list whose tags to retrieve. If no list is provided, then all the tags associated with the authenticating user will be returned.") @Valid @RequestParam(value = "name", required = false) String name, @ApiParam(value = "", allowableValues = "xml, json, tab, csv") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Add one or more tags to a list.", nickname = "listTagsPost", notes = "", response = Tags.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")}, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Tags.class)})
    @RequestMapping(value = "/list/tags",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<?> listTagsPost(@NotNull @ApiParam(value = "The name of a list to add the tag(s) to..", required = true) @Valid @RequestParam(value = "name", required = true) String name, @NotNull @ApiParam(value = "The name of the tags to add. It should take to from of a semi-colon delimited concatenation of the tag names.", required = true) @Valid @RequestParam(value = "tags", required = true) String tags, @ApiParam(value = "", allowableValues = "xml, json, tab, csv") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Delete one or more tags from a list.", nickname = "listTagsDelete", notes = "", response = Tags.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")}, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Tags.class)})
    @RequestMapping(value = "/list/tags",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<?> listTagsDelete(@NotNull @ApiParam(value = "The name of a list to add the tag(s) to.", required = true) @Valid @RequestParam(value = "name", required = true) String name, @NotNull @ApiParam(value = "The name of the tags to remove. It should take to from of a semi-colon delimited concatenation of the tag names.", required = true) @Valid @RequestParam(value = "tags", required = true) String tags, @ApiParam(value = "", allowableValues = "xml, json, tab, csv") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);

    @ApiOperation(value = "Combine Two or More Lists through Difference.", nickname = "listsDifferencePost", notes = "This service allows users to create new lists which only contain members which are not shared by an even number of lists (see: http://en.wikipedia.org/wiki/Symmetric_difference). The user must have access to all the input lists, but need not be the owner of any of them.", response = ListOperations.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")    }, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListOperations.class) })
    @RequestMapping(value = "/lists/difference",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> listsDifferencePost(@NotNull @ApiParam(value = "The name of the list to create.", required = true) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @ApiParam(value = "The name of a source list, or multiple list names concatenated with a ';' separator.", required = true) @Valid @RequestParam(value = "lists", required = true) List<String> lists,@ApiParam(value = "A description of this new list.") @Valid @RequestParam(value = "description", required = false) String description,@ApiParam(value = "A set of tags to apply to the new list.") @Valid @RequestParam(value = "tags", required = false) List<String> tags,@ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Combine Two or More Lists through Intersection.", nickname = "listsIntersectPost", notes = "This service allows users to create new lists which contain only those items     which are members of all the source lists. The user must have access to all the input     lists, but need not be the owner of any of them. An intersection of a single list     may be considered a copy.", response = ListOperations.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")    }, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListOperations.class) })
    @RequestMapping(value = "/lists/intersect",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> listsIntersectPost(@NotNull @ApiParam(value = "The name of the list to create.", required = true) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @ApiParam(value = "The name of a source list, or multiple list names concatenated with a ';' separator.", required = true) @Valid @RequestParam(value = "lists", required = true) List<String> lists,@ApiParam(value = "A description of this new list.") @Valid @RequestParam(value = "description", required = false) String description,@ApiParam(value = "A set of tags to apply to the new list.") @Valid @RequestParam(value = "tags", required = false) List<String> tags,@ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Subtract one List From Another.", nickname = "listsSubtractGet", notes = "This service allows users to create new lists which contain only those elements     which are present in one set of lists, and none of those elements which are present     in another set of lists. This is what is typically thought of as subtraction, or more     technically, the asymmetric difference of two sets. The user must supply the names of     the lists to be used as either the source lists or the subtraction lists, as well as     details for the new list to be created. The user must have access to all the named lists,     but need not be the owner of any of them.", response = ListOperations.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")    }, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListOperations.class) })
    @RequestMapping(value = "/lists/subtract",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<?> listsSubtractGet(@NotNull @ApiParam(value = "The name of the list to create.", required = true) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @ApiParam(value = "The name of a source list, or multiple list names concatenated with a ';' separator.", required = true) @Valid @RequestParam(value = "references", required = true) List<String> references,@NotNull @ApiParam(value = "The name of a list to exclude.", required = true) @Valid @RequestParam(value = "subtract", required = true) List<String> subtract,@ApiParam(value = "A description of this new list.") @Valid @RequestParam(value = "description", required = false) String description,@ApiParam(value = "A set of tags to apply to the new list.") @Valid @RequestParam(value = "tags", required = false) List<String> tags,@ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Subtract one List From Another.", nickname = "listsSubtractPost", notes = "This service allows users to create new lists which contain only those elements     which are present in one set of lists, and none of those elements which are present     in another set of lists. This is what is typically thought of as subtraction, or more     technically, the asymmetric difference of two sets. The user must supply the names of     the lists to be used as either the source lists or the subtraction lists, as well as     details for the new list to be created. The user must have access to all the named lists,     but need not be the owner of any of them.", response = ListOperations.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")    }, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListOperations.class) })
    @RequestMapping(value = "/lists/subtract",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> listsSubtractPost(@NotNull @ApiParam(value = "The name of the list to create.", required = true) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @ApiParam(value = "The name of a source list, or multiple list names concatenated with a ';' separator.", required = true) @Valid @RequestParam(value = "references", required = true) List<String> references,@NotNull @ApiParam(value = "The name of a list to exclude.", required = true) @Valid @RequestParam(value = "subtract", required = true) List<String> subtract,@ApiParam(value = "A description of this new list.") @Valid @RequestParam(value = "description", required = false) String description,@ApiParam(value = "A set of tags to apply to the new list.") @Valid @RequestParam(value = "tags", required = false) List<String> tags,@ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Combine Two or More Lists through Union.", nickname = "listsUnionGet", notes = "This service allows users to create new lists which contain all the members     contained in the set of input lists. The user must have access to all the input     lists, but need not be the owner of any of them. A union of a single list with     itself may be considered a copy.", response = ListOperations.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")    }, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListOperations.class) })
    @RequestMapping(value = "/lists/union",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<?> listsUnionGet(@NotNull @ApiParam(value = "The name of the list to create.", required = true) @Valid @RequestParam(value = "name", required = true) String name, @NotNull @ApiParam(value = "The name of a source list, or multiple list names concatenated with a ';' separator.", required = true) @Valid @RequestParam(value = "lists", required = true) List<String> lists, @ApiParam(value = "A description of this new list.") @Valid @RequestParam(value = "description", required = false) String description, @ApiParam(value = "A set of tags to apply to the new list.") @Valid @RequestParam(value = "tags", required = false) List<String> tags, @ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Combine Two or More Lists through Union.", nickname = "listsUnionPost", notes = "This service allows users to create new lists which contain all the members     contained in the set of input lists. The user must have access to all the input     lists, but need not be the owner of any of them. A union of a single list with     itself may be considered a copy.", response = ListOperations.class, authorizations = {
            @Authorization(value = "ApiKeyAuthToken"),
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "JWTBearerAuth")    }, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListOperations.class) })
    @RequestMapping(value = "/lists/union",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> listsUnionPost(@NotNull @ApiParam(value = "The name of the list to create.", required = true) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @ApiParam(value = "The name of a source list, or multiple list names concatenated with a ';' separator.", required = true) @Valid @RequestParam(value = "lists", required = true) List<String> lists,@ApiParam(value = "A description of this new list.") @Valid @RequestParam(value = "description", required = false) String description,@ApiParam(value = "A set of tags to apply to the new list.") @Valid @RequestParam(value = "tags", required = false) List<String> tags,@ApiParam(value = "", allowableValues = "json, text") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);

    @ApiOperation(value = "Measure similarity of lists using Jaccard Index.", nickname = "jaccardIndexGet", notes = "This service compares the named list with all available lists. (public lists and private ones if the user is logged in). Its returns the name of each list compared plus a number representing the Jaccard Index. See https://en.wikipedia.org/wiki/Jaccard_index", response = JaccardIndex.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = JaccardIndex.class) })
    @RequestMapping(value = "/lists/jaccard-index",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<JaccardIndex> jaccardIndexGet(@ApiParam(value = "The name of the list.") @Valid @RequestParam(value = "list", required = false) String list, @ApiParam(value = "The list of InterMine IDs.") @Valid @RequestParam(value = "ids", required = false) String ids, @ApiParam(value = "If the Jaccard Index is lower than this value, discard.") @Valid @RequestParam(value = "min", required = false, defaultValue = "0.05") BigDecimal min, @ApiParam(value = "The type of InterMine objects (if providing IDs).") @Valid @RequestParam(value = "type", required = false) String type);


    @ApiOperation(value = "Measure similarity of lists using Jaccard Index.", nickname = "jaccardIndexPost", notes = "This service compares the named list with all available lists. (public lists and private ones if the user is logged in). Its returns the name of each list compared plus a number representing the Jaccard Index. See https://en.wikipedia.org/wiki/Jaccard_index", response = JaccardIndex.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = JaccardIndex.class) })
    @RequestMapping(value = "/lists/jaccard-index",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<JaccardIndex> jaccardIndexPost(@ApiParam(value = "The name of the list.") @Valid @RequestParam(value = "list", required = false) String list,@ApiParam(value = "The list of InterMine IDs.") @Valid @RequestParam(value = "ids", required = false) String ids,@ApiParam(value = "If the Jaccard Index is lower than this value, discard.") @Valid @RequestParam(value = "min", required = false, defaultValue = "0.05") BigDecimal min,@ApiParam(value = "The type of InterMine objects (if providing IDs).") @Valid @RequestParam(value = "type", required = false) String type);

    @ApiOperation(value = "Find lists on the server containing an object.", nickname = "listsWithObjectGet", notes = "This service allows users to get back a list of             lists that contain a given object, either defined by an             internal ID, or looked up from stable identifiers.             If the request does not authenticate to a user account,             then only relevant public lists will be returned.", response = ListsGet.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListsGet.class) })
    @RequestMapping(value = "/listswithobject",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<?> listsWithObjectGet(@ApiParam(value = "A stable identifier that can be used to find the object.") @Valid @RequestParam(value = "publicId", required = false) String publicId,@ApiParam(value = "The internal DB id (changes on each re-release).") @Valid @RequestParam(value = "id", required = false) Integer id,@ApiParam(value = "The type of object (required if using a public id).") @Valid @RequestParam(value = "type", required = false) String type,@ApiParam(value = "An extra value to disambiguate objects.") @Valid @RequestParam(value = "extraValue", required = false) String extraValue,@ApiParam(value = "", allowableValues = "json, html, text, csv, tab") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);


    @ApiOperation(value = "Find lists on the server containing an object.", nickname = "listsWithObjectPost", notes = "This service allows users to get back a list of             lists that contain a given object, either defined by an             internal ID, or looked up from stable identifiers.             If the request does not authenticate to a user account,             then only relevant public lists will be returned.", response = ListsGet.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ListsGet.class) })
    @RequestMapping(value = "/listswithobject",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> listsWithObjectPost(@ApiParam(value = "A stable identifier that can be used to find the object.") @Valid @RequestParam(value = "publicId", required = false) String publicId,@ApiParam(value = "The internal DB id (changes on each re-release).") @Valid @RequestParam(value = "id", required = false) Integer id,@ApiParam(value = "The type of object (required if using a public id).") @Valid @RequestParam(value = "type", required = false) String type,@ApiParam(value = "An extra value to disambiguate objects.") @Valid @RequestParam(value = "extraValue", required = false) String extraValue,@ApiParam(value = "", allowableValues = "json, html, text, csv, tab") @Valid @RequestParam(value = "format", required = false, defaultValue = "json") String format);

}