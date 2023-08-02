import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {

    @Test
    void getTeacher() {
        Subject N_Sub = new Subject("Ivanov", "Math");

        //присвоюємо змінній значення, за допомогою метода GetTeacher
        String teacher_name = N_Sub.GetTeacher();

        //порівнюємо його з очікуваним значенням
        Assertions.assertEquals("Ivanov", teacher_name);
    }

    @Test
    void getSubjectName() {
        Subject N_Sub = new Subject("Ivanov", "Math");

        //присвоюємо змінній значення, за допомогою метода GetSubjectName
        String subject = N_Sub.GetSubjectName();

        //порівнюємо його з очікуваним значенням
        Assertions.assertEquals("Math", subject);
    }

    @Test
    void getSubID() {
        Subject N_Sub = new Subject("Ivanov", "Math");

        //присвоюємо змінним значення, за допомогою метода GetSubID
        String id_sub = N_Sub.GetSubID();
        String id_sub1 = N_Sub.GetSubID();
        String id_sub2 = N_Sub.GetSubID();

        //порівнюємо, якщо значення збігається то метод працює правильно
        Assertions.assertEquals(id_sub1, id_sub2, id_sub);
    }

    @Test
    void get_new_SubID() {
        Subject N_Sub = new Subject("Ivanov", "Math");

        //присвоюємо змінним значення, за допомогою метода Get_new_SubID
        String u_id = N_Sub.Get_new_SubID();
        String u_id1 = N_Sub.Get_new_SubID();
        String u_id2 = N_Sub.Get_new_SubID();

        //порівнюємо, якщо значення не збігаються то метод працює правильно
        Assertions.assertNotEquals(u_id1, u_id2, u_id);
    }
}