
package publish;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataTypeInscription complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataTypeInscription">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="inscriptionDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="touristNickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="touristNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataTypeInscription", propOrder = {
    "costo",
    "inscriptionDate",
    "touristNickname",
    "touristNumber"
})
public class DataTypeInscription {

    protected float costo;
    protected String inscriptionDate;
    protected String touristNickname;
    protected int touristNumber;

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad inscriptionDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInscriptionDate() {
        return inscriptionDate;
    }

    /**
     * Define el valor de la propiedad inscriptionDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInscriptionDate(String value) {
        this.inscriptionDate = value;
    }

    /**
     * Obtiene el valor de la propiedad touristNickname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTouristNickname() {
        return touristNickname;
    }

    /**
     * Define el valor de la propiedad touristNickname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTouristNickname(String value) {
        this.touristNickname = value;
    }

    /**
     * Obtiene el valor de la propiedad touristNumber.
     * 
     */
    public int getTouristNumber() {
        return touristNumber;
    }

    /**
     * Define el valor de la propiedad touristNumber.
     * 
     */
    public void setTouristNumber(int value) {
        this.touristNumber = value;
    }

}
