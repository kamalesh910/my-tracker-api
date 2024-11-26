package com.example.tracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;

@RestController
public class FileController {

   @Autowired
    private MongoTemplate mongoTemplate;

    // Endpoint to list all collections
    @GetMapping("/collections")
    public ResponseEntity<List<String>> getCollections() {
        List<String> collections = mongoTemplate.getCollectionNames();
        return ResponseEntity.ok(collections);
    }

    // Endpoint to get all documents from a specific collection
    @GetMapping("/collection/{collectionName}")
    public ResponseEntity<List<Object>> getAllDocuments(@PathVariable String collectionName) {
        List<Object> documents = mongoTemplate.findAll(Object.class, collectionName);
        return ResponseEntity.ok(documents);
    }

    // Endpoint to query a collection with criteria
    @PostMapping("/query/{collectionName}")
    public ResponseEntity<List<Object>> queryCollection(
            @PathVariable String collectionName,
            @RequestBody Map<String, Object> criteriaMap) {

        Query query = new Query();
        for (Map.Entry<String, Object> entry : criteriaMap.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
        }

        List<Object> results = mongoTemplate.find(query, Object.class, collectionName);
        return ResponseEntity.ok(results);
    }

    // Endpoint to insert a document into a collection
    @PostMapping("/collection/{collectionName}")
    public ResponseEntity<String> addDocument(
            @PathVariable String collectionName,
            @RequestBody Map<String, Object> document) {

        mongoTemplate.insert(document, collectionName);
        return ResponseEntity.status(HttpStatus.CREATED).body("Document inserted successfully");
    }

    // Endpoint to update a document in a collection
    @PutMapping("/collection/{collectionName}")
    public ResponseEntity<String> updateDocument(
            @PathVariable String collectionName,
            @RequestParam String fieldName,
            @RequestParam String fieldValue,
            @RequestBody Map<String, Object> updates) {

        Query query = new Query(Criteria.where(fieldName).is(fieldValue));
        Update update = new Update();
        updates.forEach(update::set);

        mongoTemplate.updateFirst(query, update, collectionName);
        return ResponseEntity.ok("Document updated successfully");
    }

    // Endpoint to delete a document from a collection
    @DeleteMapping("/collection/{collectionName}")
    public ResponseEntity<String> deleteDocument(
            @PathVariable String collectionName,
            @RequestParam String fieldName,
            @RequestParam String fieldValue) {

        Query query = new Query(Criteria.where(fieldName).is(fieldValue));
        mongoTemplate.remove(query, collectionName);
        return ResponseEntity.ok("Document deleted successfully");
    }
}