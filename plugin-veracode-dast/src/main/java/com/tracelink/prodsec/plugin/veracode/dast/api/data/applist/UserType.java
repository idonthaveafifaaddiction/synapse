//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.08 at 09:30:33 PM EDT 
//


package com.tracelink.prodsec.plugin.veracode.dast.api.data.applist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * The user info type element contains attributes that reflect information for the current user.
 * <p>
 * * login_account_type - the type of login account of the current user, either 'user' or 'api'
 * * username - the username of the current user
 * * create_application_profile - If true, indicates that the current user can create application
 * profiles
 * * create_sandbox - If true, indicates that the current user can create sandboxes
 * * create_new_build - If true, indicates that the current user can create builds/scans
 * * create_policy_scan - If true, indicates that the current user can create policy scans
 * * create_sandbox_scan - If true, indicates that the current user can create sandbox scans
 * * assign_app_to_team - If true, indicates that the current user can assign application profiles
 * to the current user's team
 * * assign_app_to_any_team - If true, indicates that the current user can assign application
 * profiles to any team
 * * view_sandbox - If true, indicates that the current user can view sandboxes
 * * view_results - If true, indicates that the current user can view results
 * * approve_mitigations - If true, indicates that the current user can approve or reject
 * mitigations
 * * submit_static_scan - If true, indicates that the current user can submit static scans
 * * submit_policy_static_scan - If true, indicates that the current user can submit policy static
 * scans
 * * submit_sandbox_static_scan - If true, indicates that the current user can submit sandbox
 * static
 * scans
 *
 *
 * <p>Java class for UserType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="UserType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="login_account_type" use="required" type="{https://analysiscenter.veracode.com/schema/2.0/applist}LoginAccountType" /&gt;
 *       &lt;attribute name="username" use="required" type="{https://analysiscenter.veracode.com/schema/2.0/applist}LongTextType" /&gt;
 *       &lt;attribute name="create_application_profile" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="create_sandbox" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="create_new_build" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="create_policy_scan" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="create_sandbox_scan" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="assign_app_to_team" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="assign_app_to_any_team" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="view_sandbox" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="view_results" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="approve_mitigations" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="submit_static_scan" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="submit_policy_static_scan" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="submit_sandbox_static_scan" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserType", namespace = "https://analysiscenter.veracode.com/schema/2.0/applist")
public class UserType {

	@XmlAttribute(name = "login_account_type", required = true)
	protected LoginAccountType loginAccountType;
	@XmlAttribute(name = "username", required = true)
	protected String username;
	@XmlAttribute(name = "create_application_profile", required = true)
	protected boolean createApplicationProfile;
	@XmlAttribute(name = "create_sandbox", required = true)
	protected boolean createSandbox;
	@XmlAttribute(name = "create_new_build", required = true)
	protected boolean createNewBuild;
	@XmlAttribute(name = "create_policy_scan", required = true)
	protected boolean createPolicyScan;
	@XmlAttribute(name = "create_sandbox_scan", required = true)
	protected boolean createSandboxScan;
	@XmlAttribute(name = "assign_app_to_team", required = true)
	protected boolean assignAppToTeam;
	@XmlAttribute(name = "assign_app_to_any_team", required = true)
	protected boolean assignAppToAnyTeam;
	@XmlAttribute(name = "view_sandbox", required = true)
	protected boolean viewSandbox;
	@XmlAttribute(name = "view_results", required = true)
	protected boolean viewResults;
	@XmlAttribute(name = "approve_mitigations", required = true)
	protected boolean approveMitigations;
	@XmlAttribute(name = "submit_static_scan", required = true)
	protected boolean submitStaticScan;
	@XmlAttribute(name = "submit_policy_static_scan", required = true)
	protected boolean submitPolicyStaticScan;
	@XmlAttribute(name = "submit_sandbox_static_scan", required = true)
	protected boolean submitSandboxStaticScan;

	/**
	 * Gets the value of the loginAccountType property.
	 *
	 * @return possible object is
	 * {@link LoginAccountType }
	 */
	public LoginAccountType getLoginAccountType() {
		return loginAccountType;
	}

	/**
	 * Sets the value of the loginAccountType property.
	 *
	 * @param value allowed object is
	 *              {@link LoginAccountType }
	 */
	public void setLoginAccountType(LoginAccountType value) {
		this.loginAccountType = value;
	}

	/**
	 * Gets the value of the username property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the value of the username property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setUsername(String value) {
		this.username = value;
	}

	/**
	 * Gets the value of the createApplicationProfile property.
	 *
	 * @return value of the createApplicationProfile property
	 */
	public boolean isCreateApplicationProfile() {
		return createApplicationProfile;
	}

	/**
	 * Sets the value of the createApplicationProfile property.
	 *
	 * @param value value to set for the createApplicationProfile property
	 */
	public void setCreateApplicationProfile(boolean value) {
		this.createApplicationProfile = value;
	}

	/**
	 * Gets the value of the createSandbox property.
	 *
	 * @return value of the createSandbox property
	 */
	public boolean isCreateSandbox() {
		return createSandbox;
	}

	/**
	 * Sets the value of the createSandbox property.
	 *
	 * @param value value to set for the createSandbox property
	 */
	public void setCreateSandbox(boolean value) {
		this.createSandbox = value;
	}

	/**
	 * Gets the value of the createNewBuild property.
	 *
	 * @return value of the createNewBuild property
	 */
	public boolean isCreateNewBuild() {
		return createNewBuild;
	}

	/**
	 * Sets the value of the createNewBuild property.
	 *
	 * @param value value to set for the createNewBuild property
	 */
	public void setCreateNewBuild(boolean value) {
		this.createNewBuild = value;
	}

	/**
	 * Gets the value of the createPolicyScan property.
	 *
	 * @return value of the createPolicyScan property
	 */
	public boolean isCreatePolicyScan() {
		return createPolicyScan;
	}

	/**
	 * Sets the value of the createPolicyScan property.
	 *
	 * @param value value to set for the createPolicyScan property
	 */
	public void setCreatePolicyScan(boolean value) {
		this.createPolicyScan = value;
	}

	/**
	 * Gets the value of the createSandboxScan property.
	 *
	 * @return value of the createSandboxScan property
	 */
	public boolean isCreateSandboxScan() {
		return createSandboxScan;
	}

	/**
	 * Sets the value of the createSandboxScan property.
	 *
	 * @param value value to set for the createSandboxScan property
	 */
	public void setCreateSandboxScan(boolean value) {
		this.createSandboxScan = value;
	}

	/**
	 * Gets the value of the assignAppToTeam property.
	 *
	 * @return value of the assignAppToTeam property
	 */
	public boolean isAssignAppToTeam() {
		return assignAppToTeam;
	}

	/**
	 * Sets the value of the assignAppToTeam property.
	 *
	 * @param value value to set for the assignAppToTeam property
	 */
	public void setAssignAppToTeam(boolean value) {
		this.assignAppToTeam = value;
	}

	/**
	 * Gets the value of the assignAppToAnyTeam property.
	 *
	 * @return value of the assignAppToAnyTeam property
	 */
	public boolean isAssignAppToAnyTeam() {
		return assignAppToAnyTeam;
	}

	/**
	 * Sets the value of the assignAppToAnyTeam property.
	 *
	 * @param value value to set for the assignAppToAnyTeam property
	 */
	public void setAssignAppToAnyTeam(boolean value) {
		this.assignAppToAnyTeam = value;
	}

	/**
	 * Gets the value of the viewSandbox property.
	 *
	 * @return value of the viewSandbox property
	 */
	public boolean isViewSandbox() {
		return viewSandbox;
	}

	/**
	 * Sets the value of the viewSandbox property.
	 *
	 * @param value value to set for the viewSandbox property
	 */
	public void setViewSandbox(boolean value) {
		this.viewSandbox = value;
	}

	/**
	 * Gets the value of the viewResults property.
	 *
	 * @return value of the viewResults property
	 */
	public boolean isViewResults() {
		return viewResults;
	}

	/**
	 * Sets the value of the viewResults property.
	 *
	 * @param value value to set for the viewResults property
	 */
	public void setViewResults(boolean value) {
		this.viewResults = value;
	}

	/**
	 * Gets the value of the approveMitigations property.
	 *
	 * @return value of the approveMitigations property
	 */
	public boolean isApproveMitigations() {
		return approveMitigations;
	}

	/**
	 * Sets the value of the approveMitigations property.
	 *
	 * @param value value to set for the approveMitigations property
	 */
	public void setApproveMitigations(boolean value) {
		this.approveMitigations = value;
	}

	/**
	 * Gets the value of the submitStaticScan property.
	 *
	 * @return value of the submitStaticScan property
	 */
	public boolean isSubmitStaticScan() {
		return submitStaticScan;
	}

	/**
	 * Sets the value of the submitStaticScan property.
	 *
	 * @param value value to set for the submitStaticScan property
	 */
	public void setSubmitStaticScan(boolean value) {
		this.submitStaticScan = value;
	}

	/**
	 * Gets the value of the submitPolicyStaticScan property.
	 *
	 * @return value of the submitPolicyStaticScan property
	 */
	public boolean isSubmitPolicyStaticScan() {
		return submitPolicyStaticScan;
	}

	/**
	 * Sets the value of the submitPolicyStaticScan property.
	 *
	 * @param value value to set for the submitPolicyStaticScan property
	 */
	public void setSubmitPolicyStaticScan(boolean value) {
		this.submitPolicyStaticScan = value;
	}

	/**
	 * Gets the value of the submitSandboxStaticScan property.
	 *
	 * @return value of the submitSandboxStaticScan property
	 */
	public boolean isSubmitSandboxStaticScan() {
		return submitSandboxStaticScan;
	}

	/**
	 * Sets the value of the submitSandboxStaticScan property.
	 *
	 * @param value value to set for the submitSandboxStaticScan property
	 */
	public void setSubmitSandboxStaticScan(boolean value) {
		this.submitSandboxStaticScan = value;
	}

}
