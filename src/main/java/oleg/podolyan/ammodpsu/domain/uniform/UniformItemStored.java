package oleg.podolyan.ammodpsu.domain.uniform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "uniform_items_stored")
@SQLDelete(sql = "UPDATE uniform_items_stored SET deleted = true WHERE item_id = ?")
@Loader(namedQuery = "findAllStored")
@NamedQuery(name = "findAllStored", query = "SELECT t FROM UniformItemStored t WHERE deleted = false")
@Getter
@Setter
public class UniformItemStored extends UniformItem {

    @Column(name = "shipped_at", columnDefinition = "DATE")
    @JsonProperty(value = "shipmentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate shipped;

    @Column(name = "details", columnDefinition = "TEXT")
    @JsonProperty
    private String details;

    @Transient
    public double getTotalCost(int quantity){
        BigDecimal bPrice = new BigDecimal(getPrice());
        return bPrice.multiply(new BigDecimal(quantity)).doubleValue();
    }

    public void ship(){
        this.shipped = LocalDate.now();
    }

    public void ship(LocalDate date){
        this.shipped = date;
    }
}
