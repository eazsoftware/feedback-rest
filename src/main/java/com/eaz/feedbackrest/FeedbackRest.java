package com.eaz.feedbackrest;
 
import com.eaz.feedbackrest.model.Feedback;
import com.eaz.feedbackrest.model.FeedbackReport;
import com.eaz.feedbackrest.util.FeedbackUtil;
import java.math.BigInteger;
import java.util.Date;
import javax.ws.rs.Consumes; 
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
/**
 *
 * @author javier
 */
@Path("/feedback")
public class FeedbackRest 
{    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("feed")
    @Produces({"application/json"})
    public Response createFeedback( @FormParam("name") String name,
                                    @FormParam("score") Integer score,
                                    @FormParam("comment") String comment) {
        if (name!=null 
                && score != null 
                && comment != null 
                && name.trim().length() > 0 
                && (score.intValue() > 0 && score.intValue() < 11 )
                && comment.trim().length() > 0 ) {

            BigInteger bdId = FeedbackUtil.nextRandomBigInteger();
            
            Feedback feedback = new Feedback();
            feedback.setId(bdId);
            feedback.setName(name);
            feedback.setScore(score);
            feedback.setDate(new Date());
            feedback.setComment(comment);
            
            FeedbackSetup.mapFeedback.put(bdId, feedback);
            
            return Response.ok(feedback, MediaType.APPLICATION_JSON_TYPE).build();
        }
        
        return Response.status(Response.Status.PRECONDITION_FAILED).build();
    }                   

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("feed")
    @Produces({"application/json"})
    public Response modifyFeedback( @FormParam("id") String id,
                                    @FormParam("name") String name,
                                    @FormParam("score") Integer score,
                                    @FormParam("comment") String comment) {
        if (id != null
                && name!=null 
                && score != null 
                && comment != null 
                && name.trim().length() > 0 
                && (score.intValue() > 0 && score.intValue() < 11 )
                && comment.trim().length() > 0 ) {

            BigInteger bdId = new BigInteger(id);
            
            if (FeedbackSetup.mapFeedback.containsKey(bdId)) {
                Feedback feedback = FeedbackSetup.mapFeedback.get(bdId);
                feedback.setName(name);
                feedback.setScore(score);
                feedback.setComment(comment);
                feedback.setDate(new Date());
                
                FeedbackSetup.mapFeedback.remove(bdId);
                FeedbackSetup.mapFeedback.put(bdId, feedback);
                
                FeedbackReport cFeedback = new FeedbackReport();
                cFeedback.getcFeedback().add(feedback);
                
                return Response.ok(cFeedback, MediaType.APPLICATION_JSON_TYPE).build();
            }
            else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        
        return Response.status(Response.Status.PRECONDITION_FAILED).build();
    }    
    
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("feed")
    @Produces({"application/json"})
    public Response deleteFeedback( @FormParam("id") String id) {
        try {
            if (id!=null) {
                if (FeedbackSetup.mapFeedback.containsKey(new BigInteger(id))) {                
                    FeedbackSetup.mapFeedback.remove(new BigInteger(id));
                    
                    FeedbackReport cFeedback = new FeedbackReport();
                    cFeedback.getcFeedback().addAll(FeedbackSetup.mapFeedback.values());      

                    return Response.ok(cFeedback, MediaType.APPLICATION_JSON_TYPE).build();   
                }
                else {
                    return Response.status(Response.Status.NOT_FOUND).build();        
                }
            }        
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }       
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("allfeedback")
    @Produces({"application/json"})
    public Response allFeedback() {  
        
        FeedbackReport cFeedback = new FeedbackReport();
        cFeedback.getcFeedback().addAll(FeedbackSetup.mapFeedback.values());      
        
        return Response.ok(cFeedback, MediaType.APPLICATION_JSON_TYPE).build();
    } 
    
}