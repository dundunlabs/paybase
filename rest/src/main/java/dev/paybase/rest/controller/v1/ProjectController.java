package dev.paybase.rest.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paybase.rest.dto.ProjectDTO;
import dev.paybase.rest.dto.ProjectWalletDTO;
import dev.paybase.rest.entity.Project;
import dev.paybase.rest.entity.ProjectWallet;
import dev.paybase.rest.entity.Wallet;
import dev.paybase.rest.repository.ProjectRepository;
import dev.paybase.rest.repository.ProjectWalletRepository;
import dev.paybase.rest.repository.WalletRepository;
import dev.paybase.rest.util.Mapper;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/projects")
@Slf4j
public class ProjectController {
  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private WalletRepository walletRepository;

  @Autowired
  private ProjectWalletRepository projectWalletRepository;

  @GetMapping
  public List<ProjectDTO> findAll() {
    return projectRepository.findAll()
      .stream()
      .map(p -> Mapper.fromObject(p, ProjectDTO.class))
      .collect(Collectors.toList());
  }

  @PostMapping
  public ProjectDTO create(@RequestBody ProjectDTO.CreateProjectDTO dto) {
    Project project = Mapper.fromObject(dto, Project.class);
    project = projectRepository.save(project);

    return Mapper.fromObject(project, ProjectDTO.class);
  }

  @PostMapping("/{id}/wallets")
  public ProjectWalletDTO addWallet(
    @PathVariable("id") Long id,
    @RequestBody ProjectDTO.AddWalletDTO dto
  ) {
    Project project = projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    Wallet wallet = walletRepository.findByCode(dto.getCode()).orElseThrow(EntityNotFoundException::new);
    ProjectWallet projectWallet = ProjectWallet.builder()
      .wallet(wallet)
      .project(project)
      .config(dto.getConfig())
      .build();

    projectWallet = projectWalletRepository.save(projectWallet);
    log.info("projectWallet: {}", projectWallet);

    return Mapper.fromObject(projectWallet, ProjectWalletDTO.class);
  }
}
