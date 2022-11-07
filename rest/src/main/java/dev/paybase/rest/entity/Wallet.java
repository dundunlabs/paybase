package dev.paybase.rest.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dev.paybase.rest.entity.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wallets")
@Getter
@Setter
public class Wallet extends BaseEntity {
  @Column(nullable = false, unique = true)
  private String code;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "wallet")
  private List<ProjectWallet> projects;
}
