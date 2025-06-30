package io.github.Guimaraes131.cashflowTrackerApi.controller;

import io.github.Guimaraes131.cashflowTrackerApi.controller.dto.TransactionDTO;
import io.github.Guimaraes131.cashflowTrackerApi.model.Transaction;
import io.github.Guimaraes131.cashflowTrackerApi.model.TransactionType;
import io.github.Guimaraes131.cashflowTrackerApi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid TransactionDTO dto) {
        var transaction = dto.mapToTransaction();
        service.create(transaction);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transaction.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> get(@PathVariable String id) {
        Long transactionId = Long.valueOf(id);
        Optional<Transaction> optionalTransaction = service.get(transactionId);

        if (optionalTransaction.isPresent()) {
            Transaction entity = optionalTransaction.get();
            TransactionDTO dto = new TransactionDTO(
                    entity.getId(),
                    entity.getDescription(),
                    entity.getValue(),
                    entity.getDate(),
                    entity.getType(),
                    entity.getCategory()
            );

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable String id) {
        Long transactionId = Long.valueOf(id);

        Optional<Transaction> optionalTransaction = service.get(transactionId);

        if (optionalTransaction.isPresent()) {
            service.destroy(optionalTransaction.get());

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> index(
            @RequestParam(value = "type", required = false) TransactionType type,
            @RequestParam(value = "category", required = false) String category) {

        List<Transaction> queryResult = service.index(type, category);

        List<TransactionDTO> dtos = queryResult
                .stream()
                .map(t ->
                        new TransactionDTO(
                            t.getId(),
                            t.getDescription(),
                            t.getValue(),
                            t.getDate(),
                            t.getType(),
                            t.getCategory()
                        )
                ).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody TransactionDTO dto) {
        Long transactionId = Long.valueOf(id);
        Optional<Transaction> optionalTransaction = service.get(transactionId);

        if (optionalTransaction.isPresent()) {
            Transaction entity = optionalTransaction.get();

            entity.setCategory(dto.category());
            entity.setType(dto.type());
            entity.setDate(dto.date());
            entity.setValue(dto.value());
            entity.setDescription(dto.description());

            service.update(entity);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
