package dev.paybase.rest.dto;

import lombok.Data;

@Data
public class ProjectWalletDTO {
  private Long id;
  private WalletDTO wallet;
  private Object config;
}
