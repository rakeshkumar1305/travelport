package com.travelport.core.workflow;


import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Binary;


@Component(
    service = WorkflowProcess.class,
        property = {
            "process.label=Loan Application Workflow"
        }
)
public class LoanApplication implements WorkflowProcess {

    public static  final  Logger logger= LoggerFactory.getLogger(LoanApplication.class);
    private static final String JCR_CONTENT="/data.xml/jcr:content";
    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

        logger.info("Process Step Started");

        String payload = workItem.getWorkflowData().getPayload().toString();

        logger.info("Payload :{}",payload);

        ResourceResolver resourceResolver = workflowSession.adaptTo(ResourceResolver.class);
        if(resourceResolver==null) return;

        Resource resource = resourceResolver.getResource(payload+JCR_CONTENT);

        ValueMap applicant = resource.adaptTo(ValueMap.class);
        if (applicant==null) return;
        String applicantData = applicant.get("jcr:data", String.class);
        
        logger.info("jcr:data :{}",applicantData);
        if (applicantData==null) return;

        ObjectMapper  mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(applicantData);

            String applicantName = jsonNode.get("applicant-name").asText();
            String applicantEmail = jsonNode.get("email").asText();
            String applicantPhone = jsonNode.get("phone").asText();
            String applicantLoanType = jsonNode.get("loan-type").asText();
            Double applicantLoanAmount = jsonNode.get("loanAmount").asDouble();
            String applicantEmployementType = jsonNode.get("employement-type").asText();


            logger.info("------------ APPLICANT DETAILS ------------");
            logger.info("applicant-name:{}",applicantName);
            logger.info("applicant-email:{}",applicantEmail);
            logger.info("applicant-phone:{}",applicantPhone);
            logger.info("applicant-loan-type:{}",applicantLoanType);
            logger.info("applicant-loan-amount:{}",applicantLoanAmount);
            logger.info("applicant-employement-type:{}",applicantEmployementType);

            logger.info("----------------------------------------------------");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.info("Process Step Completed");

    }
}
