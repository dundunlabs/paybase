package dev.paybase.rest.dto;

import lombok.Data;

@Data
public class ProjectDTO {
  private Long id;
  private String name;

  @Data
  public static class CreateProjectDTO {
    private String name;
  }

  @Data
  public static class AddWalletDTO {
    private String code;
    private Object config;
  }
}
