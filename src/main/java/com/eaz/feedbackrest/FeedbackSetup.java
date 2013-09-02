package com.eaz.feedbackrest;
 
import com.eaz.feedbackrest.model.Feedback;
import com.sun.jersey.api.core.PackagesResourceConfig;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;

/**
 *
 * @author javier
 */
@ApplicationPath("/")
public class FeedbackSetup extends PackagesResourceConfig {

    public static Map<BigInteger, Feedback> mapFeedback = new HashMap<BigInteger, Feedback>();
    
    public FeedbackSetup() throws Exception {
        super("com.eaz.feedbackrest");
        
    }
    
} 