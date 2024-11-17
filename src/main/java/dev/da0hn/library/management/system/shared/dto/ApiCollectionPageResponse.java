package dev.da0hn.library.management.system.shared.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Collection;

@Value
@Builder
public class ApiCollectionPageResponse<T> {

  Boolean success;

  Collection<T> data;

  Pagination pagination;

  public static <T> ApiCollectionPageResponse<T> of(final Page<T> page) {
    return new ApiCollectionPageResponse<>(true, page.getContent(), Pagination.of(page));
  }

  public static <T> ApiCollectionPageResponse<T> empty() {
    return new ApiCollectionPageResponse<>(true, new ArrayList<>(), Pagination.empty());
  }

  @Value
  public static class Pagination {

    int page;

    int pageSize;

    int totalPages;

    long totalRecords;

    public static Pagination of(final Page<?> page) {
      return new Pagination(
        page.getNumber(),
        page.getSize(),
        page.getTotalPages(),
        page.getTotalElements()
      );
    }

    public static Pagination empty() {
      return new Pagination(0, 0, 0, 0);
    }

  }

}
