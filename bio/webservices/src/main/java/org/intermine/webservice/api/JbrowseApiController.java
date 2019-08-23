package org.intermine.webservice.api;

import org.intermine.api.InterMineAPI;
import org.intermine.web.context.InterMineContext;
import org.intermine.webservice.model.JBrowseConfigTrackList;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.intermine.webservice.model.JBrowseFeatureDensityStats;
import org.intermine.webservice.model.JBrowseFeatureStats;
import org.intermine.webservice.model.JBrowseFeatures;
import org.intermine.webservice.server.Format;
import org.intermine.webservice.server.jbrowse.Config;
import org.intermine.webservice.server.jbrowse.Endpoint;
import org.intermine.webservice.server.jbrowse.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-22T21:46:57.726+05:30[Asia/Kolkata]")
@Controller
public class JbrowseApiController extends InterMineController implements JbrowseApi {

    private static final Logger log = LoggerFactory.getLogger(JbrowseApiController.class);

    @org.springframework.beans.factory.annotation.Autowired
    public JbrowseApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
    }

    public ResponseEntity<?> jBrowseConfigRefSeqs(@ApiParam(value = "The domain we are operating in. This will generally refer to an organism taxon-id.",required=true) @PathVariable("domain") String domain) {
        initController();
        return serveJBrowseConfig("refSeqs.json", domain);
    }

    public ResponseEntity<?> jBrowseConfigTrackList(@ApiParam(value = "The domain we are operating in. This will generally refer to an organism taxon-id.",required=true) @PathVariable("domain") String domain) {
        initController();
        return serveJBrowseConfig("trackList.json", domain);
    }

    private ResponseEntity<?> serveJBrowseConfig(String fileName, String domain){
        final InterMineAPI im = InterMineContext.getInterMineAPI();

        setHeaders();
        Config config = new Config(im, getFormat(), fileName, domain);
        try {
            config.service(request);
        } catch (Throwable throwable) {
            sendError(throwable);
        }
        if(fileName.equals("refSeqs.json")){
            return new ResponseEntity<List<Object>>(config.getRefSeqResult(),httpHeaders, httpStatus);
        }
        else if(fileName.equals("trackList.json")){
            return new ResponseEntity<JBrowseConfigTrackList>(config.getjBrowseConfigTrackList(), httpHeaders, httpStatus);
        }

        return null;
    }

    public ResponseEntity<?> jBrowseFeaturesGet(@ApiParam(value = "The domain we are operating in. This will generally refer to an organism taxon-id.",required=true) @PathVariable("domain") String domain, @ApiParam(value = "The identifier of the reference sequence to find features within.",required=true) @PathVariable("refseq") String refseq, @ApiParam(value = "The starting index.") @Valid @RequestParam(value = "start", required = false) Integer start, @ApiParam(value = "The end index.") @Valid @RequestParam(value = "end", required = false) Integer end, @ApiParam(value = "The type of feature to return.") @Valid @RequestParam(value = "type", required = false) String type, @ApiParam(value = "Make this a reference sequence request.") @Valid @RequestParam(value = "reference", required = false, defaultValue = "false") Boolean reference) {
        initController();
        return serveJBrowseEndpoint(domain, "features", refseq, null);
    }

    public ResponseEntity<List<Object>> jBrowseNamesGet(@ApiParam(value = "The domain for this search (generally an organism).",required=true) @PathVariable("domain") String domain,@ApiParam(value = "The search prefix string to look for. Supply either startswith or equals.") @Valid @RequestParam(value = "startswith", required = false) String startswith,@ApiParam(value = "The name of the item.Supply either startswith or equals.") @Valid @RequestParam(value = "equals", required = false) String equals) {
        initController();
        final InterMineAPI im = InterMineContext.getInterMineAPI();

        setHeaders();
        Names names = new Names(im, getFormat(), domain);
        try {
            names.service(request);
        } catch (Throwable throwable) {
            sendError(throwable);
        }
        return new ResponseEntity<List<Object>>(names.getResultList(),httpHeaders, httpStatus);
    }

    public ResponseEntity<?> jBrowseRegionFeatureDensitiesGet(@ApiParam(value = "The domain we are operating in. This will generally refer to an organism taxon-id.",required=true) @PathVariable("domain") String domain, @ApiParam(value = "The identifier of the reference sequence to find features within.",required=true) @PathVariable("refseq") String refseq, @ApiParam(value = "The starting index.") @Valid @RequestParam(value = "start", required = false) Integer start, @ApiParam(value = "The end index.") @Valid @RequestParam(value = "end", required = false) Integer end, @ApiParam(value = "The type of feature we are interested in.") @Valid @RequestParam(value = "type", required = false) String type, @ApiParam(value = "The number of bases per bin, used to determine the number of bins.") @Valid @RequestParam(value = "basesPerBin", required = false, defaultValue = "20000") String basesPerBin) {
        initController();
        return serveJBrowseEndpoint(domain, "stats", "regionFeatureDensities", refseq);
    }

    public ResponseEntity<?> jBrowseStatsGlobalGet(@ApiParam(value = "The domain we are operating in. This will generally refer to an organism taxon-id.",required=true) @PathVariable("domain") String domain,@ApiParam(value = "The type of feature we are interested in.") @Valid @RequestParam(value = "type", required = false) String type) {
        initController();
        return serveJBrowseEndpoint(domain, "stats", "global", null);
    }

    public ResponseEntity<?> jBrowseStatsRegionGet(@ApiParam(value = "The domain we are operating in. This will generally refer to an organism taxon-id.",required=true) @PathVariable("domain") String domain,@ApiParam(value = "The identifier of the reference sequence to find features within.",required=true) @PathVariable("refseq") String refseq,@ApiParam(value = "The starting index.") @Valid @RequestParam(value = "start", required = false) Integer start,@ApiParam(value = "The end index.") @Valid @RequestParam(value = "end", required = false) Integer end,@ApiParam(value = "The type of feature we are interested in.") @Valid @RequestParam(value = "type", required = false) String type) {
        initController();
        return serveJBrowseEndpoint(domain, "stats", "region", refseq);
    }

    private ResponseEntity<?> serveJBrowseEndpoint(String domain,String actionName,String section,String realSection){
        final InterMineAPI im = InterMineContext.getInterMineAPI();

        setHeaders();
        Endpoint endpoint = new Endpoint(im, getFormat(), domain, actionName, section, realSection);
        try {
            endpoint.service(request);
        } catch (Throwable throwable) {
            sendError(throwable);
        }
        if(actionName.equals("features")){

            JBrowseFeatures jBrowseFeatures = endpoint.getjBrowseFeatures();
            setFooter(jBrowseFeatures);
            return new ResponseEntity<JBrowseFeatures>(jBrowseFeatures,httpHeaders, httpStatus);

        }
        else if(actionName.equals("stats")) {

            if (section.equals("global") || section.equals("region")) {

                JBrowseFeatureStats jBrowseFeatureStats = endpoint.getjBrowseFeatureStats();
                setFooter(jBrowseFeatureStats);
                return new ResponseEntity<JBrowseFeatureStats>(jBrowseFeatureStats, httpHeaders, httpStatus);

            } else if (section.equals("regionFeatureDensities")) {

                JBrowseFeatureDensityStats jBrowseFeatureDensityStats = endpoint.getjBrowseFeatureDensityStats();
                setFooter(jBrowseFeatureDensityStats);
                return new ResponseEntity<JBrowseFeatureDensityStats>(jBrowseFeatureDensityStats, httpHeaders, httpStatus);

            }

        }

        return null;
    }

    @Override
    public Format getDefaultFormat() {
        return Format.JSON;
    }

}
