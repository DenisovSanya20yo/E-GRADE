import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest{

    @Test
    void get_new_ID() {

        Students N_Stud = new Students("Lee", "Brand", 20, "K21");

        //генеруємо випадкове ID та присвоюємо змінним
        String rand_ID = N_Stud.Get_new_ID();
        String rand_ID1 = N_Stud.Get_new_ID();
        String rand_ID2 = N_Stud.Get_new_ID();

        //так як метод має генерувати унікальне значення при кожному виклику,
        //перевіряємо, щоб значення в змінних були різними
        Assertions.assertNotEquals(rand_ID, rand_ID1, rand_ID2);
    }

    @Test
    void getStudID() {
        Students N_Stud = new Students("Lee", "Brand", 20, "K21");

        //створюємо три змінних, до яких присвоюємо значення що повертає метод GetStudID
        //метод повертає id студента N_Stud
       String chek_id1 = N_Stud.GetStudID();
       String chek_id2 = N_Stud.GetStudID();
       String chek_id3 = N_Stud.GetStudID();

       //перевіряємо, якщо значення змінних збігаються то метод працює правильно
       Assertions.assertEquals(chek_id1, chek_id2, chek_id3);
    }
}