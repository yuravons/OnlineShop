package online.patologia.shop.repo;

import online.patologia.shop.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepo extends JpaRepository<Contact,Long> {

}
