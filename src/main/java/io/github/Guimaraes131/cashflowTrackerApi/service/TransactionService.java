package io.github.Guimaraes131.cashflowTrackerApi.service;

import io.github.Guimaraes131.cashflowTrackerApi.model.Transaction;
import io.github.Guimaraes131.cashflowTrackerApi.model.TransactionType;
import io.github.Guimaraes131.cashflowTrackerApi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    @Autowired
    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void create(Transaction transaction) {
        repository.save(transaction);
    }

    public Optional<Transaction> get(Long id) {
        return repository.findById(id);
    }

    public void destroy(Transaction transaction) {
        repository.delete(transaction);
    }

    public List<Transaction> index(TransactionType type, String category) {
        if (type != null && category != null) {
            return repository.findAllByTypeAndCategory(type, category);
        }

        if (type != null) {
            return repository.findAllByType(type);
        }

        if (category != null) {
            return repository.findAllByCategory(category);
        }

        return repository.findAll();
    }

    public void update(Transaction transaction) {
        repository.save(transaction);
    }
}
