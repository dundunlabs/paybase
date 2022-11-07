package dev.paybase.rest.util;

import org.modelmapper.ModelMapper;

public class Mapper {
  private static final ModelMapper modelMapper = new ModelMapper();

  public static <T> T fromObject(Object object, Class<T> type) {
    return modelMapper.map(object, type);
  }
}
