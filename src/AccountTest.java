import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void get_new_ID() {

        Account anAccount = new Account("teacher", "Leslie", "Mann", 27, "Denalex_123");

        String test1 = anAccount.Get_new_ID();
        String test2 = anAccount.Get_new_ID();
        String test3 = anAccount.Get_new_ID();

        Assertions.assertNotEquals(test1, test2, test3);
    }

    @Test
    void getStatus() {

        Account anAccount = new Account("teacher", "Leslie", "Mann", 27, "Denalex_123");

        String test_status = anAccount.GetStatus();

        Assertions.assertEquals("teacher", test_status);

    }

    @Test
    void getPass() {

        Account anAccount = new Account("teacher", "Leslie", "Mann", 27, "Denalex_123");

        String test_pass = anAccount.GetPass();

        Assertions.assertEquals("Denalex_123", test_pass);
    }
}