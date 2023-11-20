
package publish;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataTypeOutingArray complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataTypeOutingArray">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="outings" type="{http://publish./}dataTypeOuting" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataTypeOutingArray", propOrder = {
    "outings"
})
public class DataTypeOutingArray {

    @XmlElement(nillable = true)
    protected List<DataTypeOuting> outings;

    /**
     * Gets the value of the outings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the outings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataTypeOuting }
     * 
     * 
     * @return
     *     The value of the outings property.
     */
    public List<DataTypeOuting> getOutings() {
        if (outings == null) {
            outings = new ArrayList<>();
        }
        return this.outings;
    }

}
