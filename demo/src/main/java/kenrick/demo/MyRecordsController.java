package kenrick.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/records")
public class MyRecordsController {
    @Autowired
    private MyRecordsRepository myRecordsRepository;

    @GetMapping
    public ResponseEntity<List<MyRecord>> getAllRecords(Pageable pageable){
        Page<MyRecord> page = myRecordsRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.DESC, "date"))
        ));

        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<MyRecord>> getRecordById(@PathVariable String name, Pageable pageable){
        Page<MyRecord> page = myRecordsRepository.findByOwner(name, PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.DESC, "date"))
        ));
//        Optional<MyRecord> myRecord = myRecordsRepository.findById(id);
//        if(myRecord.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping
    public ResponseEntity<MyRecord> createRecord(@RequestBody MyRecord newRecordRequest, UriComponentsBuilder ucb){
        MyRecord myRecord = new MyRecord();
        myRecord.setTitle(newRecordRequest.getTitle());
        myRecord.setAmount(newRecordRequest.getAmount());
        myRecord.setDate(newRecordRequest.getDate());
        myRecord.setOwner("Kenrick");
        MyRecord savedRecord = myRecordsRepository.save(myRecord);
//        URI locationOfNewRecord = ucb.path("records/{id}")
//                .buildAndExpand(savedRecord.getId()).toUri();
//        return ResponseEntity.created(locationOfNewRecord).build();
        return  ResponseEntity.ok(savedRecord);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateRecord(@PathVariable Integer id, @RequestBody MyRecord updatedRecordRequest){
        Optional<MyRecord> myRecord = myRecordsRepository.findById(id);
        if(myRecord.isEmpty()) return ResponseEntity.notFound().build();
        MyRecord updatedRecord = new MyRecord(
                myRecord.get().getId(),
                updatedRecordRequest.getTitle(),
                updatedRecordRequest.getAmount(),
                myRecord.get().getOwner(),
                updatedRecordRequest.getDate());
        myRecordsRepository.save(updatedRecord);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<MyRecord> deleteRecord(@PathVariable Integer id){
        Optional<MyRecord> myRecord = myRecordsRepository.findById(id);
        if(myRecord.isEmpty()) return ResponseEntity.notFound().build();
        myRecordsRepository.deleteById(myRecord.get().getId());
        return ResponseEntity.ok(myRecord.get());
    }
}
