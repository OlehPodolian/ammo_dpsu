package oleg.podolyan.ammodpsu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Data
public abstract class AbstractAuditor implements Serializable {
    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    @JsonProperty
    private Long createdBy;

    @CreatedDate
    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @JsonProperty(value = "createdDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime created;

    @LastModifiedBy
    @Column(name = "modified_by")
    @JsonProperty
    private Long lastModifiedBy;

    @LastModifiedDate
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP", nullable = false)
    @JsonProperty(value = "lastModifiedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime lastModified;

}
