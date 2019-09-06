package org.sitenv.service.ccda.smartscorecard.processor;

import java.util.ArrayList;
import java.util.List;

import org.sitenv.ccdaparsing.model.CCDAPatient;
import org.sitenv.ccdaparsing.model.CCDAXmlSnippet;
import org.sitenv.service.ccda.smartscorecard.cofiguration.SectionRule;
import org.sitenv.service.ccda.smartscorecard.model.CCDAScoreCardRubrics;
import org.sitenv.service.ccda.smartscorecard.model.Category;
import org.sitenv.service.ccda.smartscorecard.util.ApplicationConstants;
import org.sitenv.service.ccda.smartscorecard.util.ApplicationUtil;
import org.springframework.stereotype.Service;

@Service
public class PatientScorecard {
	
	public Category getPatientCategory(CCDAPatient patient,String ccdaVersion,List<SectionRule> sectionRules)
	{
		
		Category patientCategory = new Category();
		patientCategory.setCategoryName(ApplicationConstants.CATEGORIES.PATIENT.getCategoryDesc());
		
		List<CCDAScoreCardRubrics> patientScoreList = new ArrayList<CCDAScoreCardRubrics>();
		
		if (sectionRules==null || ApplicationUtil.isRuleEnabled(sectionRules, ApplicationConstants.RULE_IDS.P1)) {
			patientScoreList.add(getDOBTimePrecisionScore(patient, ccdaVersion));
		}
		if (sectionRules==null || ApplicationUtil.isRuleEnabled(sectionRules, ApplicationConstants.RULE_IDS.P2)) {
			patientScoreList.add(getLegalNameScore(patient, ccdaVersion));
		}
		
		patientCategory.setCategoryRubrics(patientScoreList);
		ApplicationUtil.calculateSectionGradeAndIssues(patientScoreList, patientCategory);
		ApplicationUtil.calculateNumberOfChecksAndFailedRubrics(patientScoreList, patientCategory);
		
		return patientCategory;
		
	}
	
	
	public static CCDAScoreCardRubrics getDOBTimePrecisionScore(CCDAPatient patient, String ccdaVersion)
	{
		CCDAScoreCardRubrics timePrecisionScore = new CCDAScoreCardRubrics();
		timePrecisionScore.setRule(ApplicationConstants.PATIENT_DOB_REQUIREMENT);
		
		int actualPoints = 0;
		int maxPoints = 1;
		int numberOfChecks = 1;
		List<CCDAXmlSnippet> issuesList = new ArrayList<CCDAXmlSnippet>();
		CCDAXmlSnippet issue= null;
		if(patient != null)
		{
			if(patient.getDob()!=null)
			{
				if((ApplicationUtil.validateDayFormat(patient.getDob().getValue()) || 
						ApplicationUtil.validateMinuteFormat(patient.getDob().getValue()) || 
						ApplicationUtil.validateSecondFormat(patient.getDob().getValue()))
						&& ApplicationUtil.validateBirthDate(patient.getDob().getValue()))
				{
					actualPoints++;
				}
				else
				{
					issue = new CCDAXmlSnippet();
					issue.setLineNumber(patient.getDob().getLineNumber());
					issue.setXmlString(patient.getDob().getXmlString());
					issuesList.add(issue);
				}
			}
			else
			{
				issue = new CCDAXmlSnippet();
				issue.setLineNumber(patient.getLineNumber());
				issue.setXmlString(patient.getXmlString());
				issuesList.add(issue);
			}
		}
		else
		{
			issue = new CCDAXmlSnippet();
			issue.setLineNumber("Patient section not present");
			issue.setXmlString("Patient section not present");
			issuesList.add(issue);
		}
		
		timePrecisionScore.setActualPoints(actualPoints);
		timePrecisionScore.setMaxPoints(maxPoints);
		timePrecisionScore.setRubricScore(ApplicationUtil.calculateRubricScore(maxPoints, actualPoints));
		timePrecisionScore.setIssuesList(issuesList);
		timePrecisionScore.setNumberOfIssues(issuesList.size());
		timePrecisionScore.setNumberOfChecks(numberOfChecks);
		if(issuesList.size() > 0)
		{
			timePrecisionScore.setDescription("Time precision Rubric failed for Patient DOB");
			if(ccdaVersion.equals("") || ccdaVersion.equals(ApplicationConstants.CCDAVersion.R21.getVersion()))
			{
				timePrecisionScore.getIgReferences().add(ApplicationConstants.IG_REFERENCES.RECORD_TARGET.getIgReference());
			}
			else if(ccdaVersion.equals(ApplicationConstants.CCDAVersion.R11.getVersion()))
			{
				timePrecisionScore.getIgReferences().add(ApplicationConstants.IG_REFERENCES_R1.RECORD_TARGET.getIgReference());
			}
			timePrecisionScore.getExampleTaskForceLinks().add(ApplicationConstants.TASKFORCE_LINKS.PATIENT.getTaskforceLink());
		}
		return timePrecisionScore;
	}
	
	public static CCDAScoreCardRubrics getLegalNameScore(CCDAPatient patient, String ccdaVersion)
	{
		CCDAScoreCardRubrics legalNameScore = new CCDAScoreCardRubrics();
		legalNameScore.setRule(ApplicationConstants.PATIENT_LEGAL_NAME_REQUIREMENT);
		
		int actualPoints = 0;
		int maxPoints = 0;
		int numberOfChecks = 0;
		List<CCDAXmlSnippet> issuesList = new ArrayList<CCDAXmlSnippet>();
		CCDAXmlSnippet issue= null;
		if(patient != null)
		{
			if(patient.getPatientLegalNameElement()!=null)
			{
				maxPoints++;
				numberOfChecks++;
				if(!(patient.getGivenNameElementList().size() > 2 || patient.isGivenNameContainsQualifier()))
				{
					actualPoints++;
				}
				else
				{
					issue = new CCDAXmlSnippet();
					issue.setLineNumber(patient.getPatientLegalNameElement().getLineNumber());
					issue.setXmlString(patient.getPatientLegalNameElement().getXmlString());
					issuesList.add(issue);
				}
			}
		}
		
		if(maxPoints ==0)
		{
			maxPoints =1;
			actualPoints =1;
		}
		
		legalNameScore.setActualPoints(actualPoints);
		legalNameScore.setMaxPoints(maxPoints);
		legalNameScore.setRubricScore(ApplicationUtil.calculateRubricScore(maxPoints, actualPoints));
		legalNameScore.setIssuesList(issuesList);
		legalNameScore.setNumberOfIssues(issuesList.size());
		legalNameScore.setNumberOfChecks(numberOfChecks);
		if(issuesList.size() > 0)
		{
			legalNameScore.setDescription(ApplicationConstants.PATIENT_LEGAL_NAME_DESCRIPTION);
			if(ccdaVersion.equals("") || ccdaVersion.equals(ApplicationConstants.CCDAVersion.R21.getVersion()))
			{
				legalNameScore.getIgReferences().add(ApplicationConstants.IG_REFERENCES.RECORD_TARGET.getIgReference());
			}
			else if(ccdaVersion.equals(ApplicationConstants.CCDAVersion.R11.getVersion()))
			{
				legalNameScore.getIgReferences().add(ApplicationConstants.IG_REFERENCES_R1.RECORD_TARGET.getIgReference());
			}
			legalNameScore.getExampleTaskForceLinks().add(ApplicationConstants.TASKFORCE_LINKS.PATIENT.getTaskforceLink());
		}
		return legalNameScore;
	}
}
