package com.walmart.dctech.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "process")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AisProcess extends Auditable<String> implements Serializable {

  /** */
  private static final long serialVersionUID = 1L;

  @Id
  @NotNull
  @Column(name = "pdms_request_id")
  private String pdmsRequestId;

  @NotNull
  @Column(name = "process_instance_Id")
  private String processInstanceId;

  @Column(name = "privacy_request_id")
  private String privacyRequestId;

  @Column(name = "one_trust_id")
  private String oneTrustId;

  @Column(name = "request_type")
  private String requestType;

  @Size(max = 20)
  @Column(name = "status")
  private String status;

  @Size(max = 255)
  @Column(name = "remarks")
  private String remarks;

  @Column(name = "retry_count")
  private Integer retryCount;

  @JsonManagedReference(value = "AisProcess_AssetTask")
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "aisProcess", fetch = FetchType.LAZY)
  private List<AisAssetTask> assetTasks;

  public void addAisAssetTask(AisAssetTask aisAssetTask) {
    if (Objects.isNull(assetTasks)) {
      assetTasks = new ArrayList<>();
    }
    aisAssetTask.setAisProcess(this);
    assetTasks.add(aisAssetTask);
  }

  public void setRemarks(String remarks) {
    this.remarks =
        Objects.nonNull(remarks) && remarks.length() > 255 ? remarks.substring(0, 254) : remarks;
  }
}
