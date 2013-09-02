package com.eaz.feedbackrest.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author javier
 */
@XmlRootElement
public class FeedbackReport {
    
    protected Collection<Feedback> cFeedback;
    
    public FeedbackReport() {
        cFeedback = new ArrayList<Feedback>();
    }

    public Collection<Feedback> getcFeedback() {
        return cFeedback;
    }

    public void setcFeedback(Collection<Feedback> cFeedback) {
        this.cFeedback = cFeedback;
    }
    
}