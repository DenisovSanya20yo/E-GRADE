import java.util.Random;

public class Subject
{
    private String id_subject;//id предмету

    private String teacher;//ім'я вчителя

    private String subject_name;//назва предмету

    private int grade;//оцінка

    //ініціалізуємо конструктор для створення об'єкта классу
    public Subject (String teacher, String subject_name, int grade)
    {
        this.id_subject = Get_new_SubID();
        this.teacher = teacher;
        this.subject_name = subject_name;
        this.grade = grade;
    }

    public String GetTeacher ()
    {
        return this.teacher;
    }//отримання ім'я вчителя

    public String GetSubjectName ()
    {
        return this.subject_name;
    }//отримання назву предмету

    public int GetGrade ()
    {
        return this.grade;
    }//отримання оцінки

    public String GetSubID ()
    {
        return this.id_subject;
    }//отримання id

    public String Get_new_SubID ()//генерація id предмету
    {
        String newId;
        Random rand = new Random();
        boolean nonUnique;

        do {
            newId = "";
            for (int i = 0; i < 6; i++)
            {
                newId += ((Integer)rand.nextInt(10)).toString();
            }

            nonUnique = false;

            for (Students s : E_GRADE.StudentList)
            {
                if(newId.compareTo(s.GetStudID()) == 0)
                {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return newId;
    }
}
