//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.08 at 09:30:35 PM EDT 
//


package com.tracelink.prodsec.plugin.veracode.dast.api.data.detailedreport;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The element describes a screen shot for a flaw. There is a
 * description of the screen shot, and a element for the
 * data and type.
 *
 *
 * <p>Java class for AppendixType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AppendixType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="description" type="{https://www.veracode.com/schema/reports/export/1.0}LongTextType"/&gt;
 *         &lt;element name="screenshot" type="{https://www.veracode.com/schema/reports/export/1.0}ScreenshotType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppendixType", namespace = "https://www.veracode.com/schema/reports/export/1.0", propOrder = {
		"description",
		"screenshot",
		"code"
})
public class AppendixType {

	@XmlElement(namespace = "https://www.veracode.com/schema/reports/export/1.0", required = true)
	protected String description;
	@XmlElement(namespace = "https://www.veracode.com/schema/reports/export/1.0")
	protected List<ScreenshotType> screenshot;
	@XmlElement(namespace = "https://www.veracode.com/schema/reports/export/1.0")
	protected List<String> code;

	/**
	 * Gets the value of the description property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Gets the value of the screenshot property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the screenshot property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getScreenshot().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ScreenshotType }
	 *
	 * @return value of the screenshot property, or an empty list if the screenshot property is null
	 */
	public List<ScreenshotType> getScreenshot() {
		if (screenshot == null) {
			screenshot = new ArrayList<>();
		}
		return this.screenshot;
	}

	/**
	 * Gets the value of the code property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the code property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getCode().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link String }
	 *
	 * @return value of the code property, or an empty list if the code property is null
	 */
	public List<String> getCode() {
		if (code == null) {
			code = new ArrayList<>();
		}
		return this.code;
	}

}
