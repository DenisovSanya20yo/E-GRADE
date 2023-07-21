import java.util.ArrayList;
import java.util.Random;

public class Students
{
    private String id_student; //id студента

    private String F_name;//Ім'я

    private String L_name;//Прізвище

    private int Age;//Вік

    private String studGroup;//Група

    private ArrayList<Subject> subjects;//Список студентів

    //ініціалізуємо конструктор для створення об'єкта классу
    public Students (String F_name, String L_name, int Age, String studGroup)
    {
        this.id_student = Get_new_ID();
        this.F_name = F_name;
        this.L_name = L_name;
        this.Age = Age;
        this.studGroup = studGroup;

        this.subjects = new ArrayList<Subject>();
    }

    //функція для генерації id студента
    public String Get_new_ID ()
    {
        String newId;
        Random rand = new Random();
        boolean nonUnique;

        do {
            newId = ""; // створюємо порожній рядок в який додаємо випадкові цифри за допомогою класу Random
            for (int i = 0; i < 6; i++)
            {
                newId += ((Integer)rand.nextInt(10)).toString();
            }

            nonUnique = false;

            //перебераємо список студентів для перевірки id на унікальність
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

    public String GetStudID ()
    {
        return this.id_student;
    } //отримуємо id

    public void addSub (Subject aSub)
    {
        this.subjects.add(aSub);
    } //додає об'єкт класу Subject до списку предметів

    public void ListOFsub () // вивід списку студентів
    {
        System.out.println("List of subjects: ");

        for (Subject s : this.subjects)
        {
            System.out.println("Teacher: " + s.GetTeacher() + "; " + "Subject: " +
                    s.GetSubjectName() + "; " + "Grade for exame: " + s.GetGrade() + "; " +
                    "ID: " + s.GetSubID() + ";");
        }
        System.out.println();
    }

    public void PrintStudInfo () // виводить інформацію про студетів
    {
        System.out.println("First name: " + this.F_name);
        System.out.println("Last name: " + this.L_name);
        System.out.println("Age: " + this.Age);
        System.out.println("Group: " + this.studGroup);
        System.out.println("ID: " + this.id_student);
        System.out.println();
    }

    public void RemoveFromSubList (String r_subID) // видаляє предмет зі списку
    {
        int i = 0;
        for (Subject s : this.subjects)
        {
            if(s.GetSubID().equals(r_subID))
            {
                this.subjects.remove(i);
                break;
            }
            i++;
        }
        ListOFsub();
    }
}
