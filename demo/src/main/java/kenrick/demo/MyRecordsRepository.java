package kenrick.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MyRecordsRepository extends CrudRepository<MyRecord, Integer>, PagingAndSortingRepository<MyRecord, Integer> {
    Page<MyRecord> findByOwner(String owner, PageRequest pageRequest);
}
