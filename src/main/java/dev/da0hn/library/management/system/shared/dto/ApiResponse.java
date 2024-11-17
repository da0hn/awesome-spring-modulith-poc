package dev.da0hn.library.management.system.shared.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiResponse<T> {

  Boolean success;

  String message;

  T data;

  public static <T> ApiResponse<T> of(final T data) {
    return new ApiResponse<>(true, null, data);
  }

  public static ApiResponse<Void> empty() {
    return new ApiResponse<>(true, null, null);
  }

  public static ApiResponse<Void> failure(final String message) {
    return new ApiResponse<>(false, message, null);
  }

}
