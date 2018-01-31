package web;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sessionproperties database table.
 * 
 */
@Entity
@Table(name="sessionproperties")
@NamedQuery(name="Sessionproperty.findAll", query="SELECT s FROM Sessionproperty s")
public class Sessionproperty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="property_id")
	private int propertyId;

	@Column(name="session_id")
	private int sessionId;

	@Column(name="value_bool")
	private byte valueBool;

	@Column(name="value_number")
	private int valueNumber;

	@Column(name="value_string")
	private String valueString;

	public Sessionproperty() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public byte getValueBool() {
		return this.valueBool;
	}

	public void setValueBool(byte valueBool) {
		this.valueBool = valueBool;
	}

	public int getValueNumber() {
		return this.valueNumber;
	}

	public void setValueNumber(int valueNumber) {
		this.valueNumber = valueNumber;
	}

	public String getValueString() {
		return this.valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

}