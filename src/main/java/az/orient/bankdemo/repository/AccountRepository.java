package az.orient.bankdemo.repository;

import az.orient.bankdemo.entity.Account;
import az.orient.bankdemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllByCustomerAndActive(Customer customer, Integer active);

    Account findAccountByIdAndActive(Long accountId,Integer active);

    @Query("SELECT a FROM Account a where a.customer.id=:id")
    List<Account> findAccounts(Long id);

}
