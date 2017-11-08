package org.sitenv.service.ccda.smartscorecard.model;

import java.util.List;

public class Results {
	
	private String finalGrade;
	private int finalNumericalGrade;
	private List<Category> categoryList;
	private int numberOfIssues;
	private String igReferenceUrl;
	private int industryAverageScore;
	private String industryAverageGrade;
	private long numberOfDocumentsScored;
	private String docType;
	private Boolean passedCertification;
	private long numberOfDocsScoredPerCcdaDocumentType;
	private int industryAverageScoreForCcdaDocumentType;
	private String industryAverageGradeForCcdaDocumentType;
		
	public String getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public int getFinalNumericalGrade() {
		return finalNumericalGrade;
	}
	public void setFinalNumericalGrade(int finalNumericalGrade) {
		this.finalNumericalGrade = finalNumericalGrade;
	}
	public int getNumberOfIssues() {
		return numberOfIssues;
	}
	public void setNumberOfIssues(int numberOfIssues) {
		this.numberOfIssues = numberOfIssues;
	}
	public int getIndustryAverageScore() {
		return industryAverageScore;
	}
	public void setIndustryAverageScore(int industryAverageScore) {
		this.industryAverageScore = industryAverageScore;
	}
	public String getIndustryAverageGrade() {
		return industryAverageGrade;
	}
	public void setIndustryAverageGrade(String industryAverageGrade) {
		this.industryAverageGrade = industryAverageGrade;
	}
	public String getIgReferenceUrl() {
		return igReferenceUrl;
	}
	public void setIgReferenceUrl(String igReferenceUrl) {
		this.igReferenceUrl = igReferenceUrl;
	}
	public long getNumberOfDocumentsScored() {
		return numberOfDocumentsScored;
	}
	public void setNumberOfDocumentsScored(long numberOfDocumentsScored) {
		this.numberOfDocumentsScored = numberOfDocumentsScored;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public Boolean getPassedCertification() {
		return passedCertification;
	}
	public void setPassedCertification(Boolean passedCertification) {
		this.passedCertification = passedCertification;
	}
	public long getNumberOfDocsScoredPerCcdaDocumentType() {
		return numberOfDocsScoredPerCcdaDocumentType;
	}
	public void setNumberOfDocsScoredPerCcdaDocumentType(long numberOfDocsScoredPerCcdaDocumentType) {
		this.numberOfDocsScoredPerCcdaDocumentType = numberOfDocsScoredPerCcdaDocumentType;		
	}
	public int getIndustryAverageScoreForCcdaDocumentType() {
		return industryAverageScoreForCcdaDocumentType;
	}
	public void setIndustryAverageScoreForCcdaDocumentType(int industryAverageScoreForCcdaDocumentType) {
		this.industryAverageScoreForCcdaDocumentType = industryAverageScoreForCcdaDocumentType;
	}
	public String getIndustryAverageGradeForCcdaDocumentType() {
		return industryAverageGradeForCcdaDocumentType;
	}
	public void setIndustryAverageGradeForCcdaDocumentType(String industryAverageGradeForCcdaDocumentType) {
		this.industryAverageGradeForCcdaDocumentType = industryAverageGradeForCcdaDocumentType;
	}
	
}
