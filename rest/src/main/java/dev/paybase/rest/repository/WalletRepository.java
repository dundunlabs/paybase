package dev.paybase.rest.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.paybase.rest.entity.Wallet;
import dev.paybase.rest.repository.common.BaseRepository;

@Repository
public interface WalletRepository extends BaseRepository<Wallet> {
  Optional<Wallet> findByCode(String code);
}
