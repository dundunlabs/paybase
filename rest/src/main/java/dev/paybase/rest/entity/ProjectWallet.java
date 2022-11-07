package dev.paybase.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import dev.paybase.rest.entity.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
  name = "project_wallets",
  indexes = @Index(columnList = "project_id, wallet_id", unique = true)
)
@Getter
@Setter
@Builder
public class ProjectWallet extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @ManyToOne
  @JoinColumn(name="wallet_id")
  private Wallet wallet;

  @Type(type = "jsonb")
  @Column(columnDefinition = "jsonb", nullable = false)
  private Object config;
}
