
package publish;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataTypeOuting complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataTypeOuting">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="entryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="freePlaces" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="hour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="image" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         <element name="inscriptions" type="{http://publish./}dataTypeInscription" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="maxTourist" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="place" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataTypeOuting", propOrder = {
    "date",
    "entryDate",
    "freePlaces",
    "hour",
    "image",
    "inscriptions",
    "maxTourist",
    "name",
    "place"
})
public class DataTypeOuting {

    protected String date;
    protected String entryDate;
    protected int freePlaces;
    protected String hour;
    protected byte[] image;
    @XmlElement(nillable = true)
    protected List<DataTypeInscription> inscriptions;
    protected int maxTourist;
    protected String name;
    protected String place;

    /**
     * Obtiene el valor de la propiedad date.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Define el valor de la propiedad date.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Obtiene el valor de la propiedad entryDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryDate() {
        return entryDate;
    }

    /**
     * Define el valor de la propiedad entryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryDate(String value) {
        this.entryDate = value;
    }

    /**
     * Obtiene el valor de la propiedad freePlaces.
     * 
     */
    public int getFreePlaces() {
        return freePlaces;
    }

    /**
     * Define el valor de la propiedad freePlaces.
     * 
     */
    public void setFreePlaces(int value) {
        this.freePlaces = value;
    }

    /**
     * Obtiene el valor de la propiedad hour.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHour() {
        return hour;
    }

    /**
     * Define el valor de la propiedad hour.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHour(String value) {
        this.hour = value;
    }

    /**
     * Obtiene el valor de la propiedad image.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Define el valor de la propiedad image.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImage(byte[] value) {
        this.image = value;
    }

    /**
     * Gets the value of the inscriptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the inscriptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInscriptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataTypeInscription }
     * 
     * 
     * @return
     *     The value of the inscriptions property.
     */
    public List<DataTypeInscription> getInscriptions() {
        if (inscriptions == null) {
            inscriptions = new ArrayList<>();
        }
        return this.inscriptions;
    }

    /**
     * Obtiene el valor de la propiedad maxTourist.
     * 
     */
    public int getMaxTourist() {
        return maxTourist;
    }

    /**
     * Define el valor de la propiedad maxTourist.
     * 
     */
    public void setMaxTourist(int value) {
        this.maxTourist = value;
    }

    /**
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtiene el valor de la propiedad place.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlace() {
        return place;
    }

    /**
     * Define el valor de la propiedad place.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlace(String value) {
        this.place = value;
    }

}
