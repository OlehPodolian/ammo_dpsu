package oleg.podolyan.ammodpsu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.envers.Audited;
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
@Audited
@EntityListeners({AuditingEntityListener.class})
public abstract class JpaAuditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    @JsonProperty
    private String createdBy;

    @CreatedDate
//    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @JsonProperty(value = "createdDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime created;

    @LastModifiedBy
    @Column(name = "modified_by")
    @JsonProperty
    private String lastModifiedBy;

    @LastModifiedDate
//    @Generated(GenerationTime.ALWAYS)
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP", nullable = false)
    @JsonProperty(value = "lastModifiedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime lastModified;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModified() {
        return lastModified == null ? created : lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
