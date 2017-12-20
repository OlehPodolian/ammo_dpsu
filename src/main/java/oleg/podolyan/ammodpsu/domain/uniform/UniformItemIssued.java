package oleg.podolyan.ammodpsu.domain.uniform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "uniform_items_issued")
@SQLDelete(sql = "UPDATE uniform_items_issued SET deleted = true WHERE item_id = ?")
@Loader(namedQuery = "findAllIssued")
@NamedQuery(name = "findAllIssued", query = "SELECT t FROM UniformItemIssued t WHERE deleted = false")
@Getter
@Setter
public class UniformItemIssued extends UniformItem {

    @Column(name = "expires_at", columnDefinition = "DATE", nullable = false)
    @JsonProperty(value = "expiryDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate expires;

    public boolean isNotExpired(){
        return expires.isAfter(LocalDate.now());
    }
}
