package com.xeppelin.userservice.infrastructure.adapter.input.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Generic paginated response wrapper")
public class PagedResponse<T> {

    @Schema(
        description = "List of items in the current page"
    )
    private List<T> content;

    @Schema(
        description = "Pagination metadata including page information and totals"
    )
    private PageMetadata metadata;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Pagination metadata")
    public static class PageMetadata {

        @Schema(
            description = "Number of items per page",
            example = "20",
            minimum = "1"
        )
        private long size;

        @Schema(
            description = "Current page number (zero-based)",
            example = "0",
            minimum = "0"
        )
        private long number;

        @Schema(
            description = "Total number of items across all pages",
            example = "150",
            minimum = "0"
        )
        private long totalElements;

        @Schema(
            description = "Total number of pages",
            example = "8",
            minimum = "0"
        )
        private long totalPages;
    }
} 