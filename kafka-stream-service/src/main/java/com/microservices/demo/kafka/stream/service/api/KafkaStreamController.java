package com.microservices.demo.kafka.stream.service.api;


import com.microservices.demo.kafka.stream.service.model.KafkaStreamResponseModel;
import com.microservices.demo.kafka.stream.service.runner.StreamRunner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping(value = "/", produces = "application/vnd.api.v1+json")
public class KafkaStreamController {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaStreamController.class);
    private final StreamRunner<String,Long> kafkaStreamRunner;

    public KafkaStreamController(StreamRunner<String, Long> kafkaStreamRunner) {
        this.kafkaStreamRunner = kafkaStreamRunner;
    }

    @GetMapping("get-word-count-by-word/{word}")
    @Operation(summary = "Get word count by word.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success.", content = {
                    @Content(mediaType = "application/vnd.api.v1+json",
                            schema = @Schema(implementation = KafkaStreamResponseModel.class))
            }),
            @ApiResponse(responseCode = "400", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Unexpected error.")})
    public @ResponseBody
    ResponseEntity<KafkaStreamResponseModel> getWordCountByWord(
            @PathVariable @NotEmpty String word) {
        Long wordCount = kafkaStreamRunner.getValueByKey(word);
        LOG.info("Word count {} returned for word {}", wordCount, word);
        return ResponseEntity.ok(KafkaStreamResponseModel.builder()
                .word(word)
                .wordCount(wordCount)
                .build());
    }
}
