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
@Table(name = "projects")
@Getter
@Setter
public class Project extends BaseEntity {
  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "project")
  private List<ProjectWallet> wallets;
}
